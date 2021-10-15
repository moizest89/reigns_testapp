package com.moizest89.reign.apptest

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.moizest89.reign.apptest.presentation.base.BaseActivity
import com.moizest89.reign.apptest.databinding.ActivityMainBinding
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.moizest89.reign.apptest.presentation.State
import com.moizest89.reign.apptest.presentation.details.MewsDetailsActivity
import com.moizest89.reign.apptest.presentation.main.MainAdapter
import com.moizest89.reign.apptest.presentation.main.MainViewModel
import com.moizest89.reign.apptest.presentation.utils.Utils.SEND_MAIN_DATA
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val newsListViewModel by viewModels<MainViewModel>()

    private var mAdapter = MainAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        inflateItemsView(binding)

        binding.lifecycleOwner = this

        this.newsListViewModel.newsData.observe(this) {
            when (it) {
                is State.ErrorState -> {

                }
                is State.LoadingState -> {
                    progressAction(it.isLoading)
                    this.swipeRefreshLayout.isRefreshing = it.isLoading
                }
                is State.DataState<*> -> {
                    this.mAdapter.submitList(it.data as MutableList<NewsItem>)
                    this.swipeRefreshLayout.visibility = View.VISIBLE
                }
            }
        }

        if (this.mAdapter.isEmpty()) {
            this.newsListViewModel.getNewsList()
        } else {
            this.progressBar.visibility = View.GONE
            this.swipeRefreshLayout.visibility = View.VISIBLE
        }
        this.swipeRefreshLayout.setOnRefreshListener {
            this.newsListViewModel.getNewsList(false)
        }

        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun progressAction(isShowing: Boolean) {
        binding.includeItems.progressBar.visibility = if (isShowing) View.VISIBLE else View.GONE
        if (isShowing) {
//            if (this.linearLayoutEmptyState.visibility == View.VISIBLE) {
//                this.linearLayoutEmptyState.visibility = View.GONE
//            }
            if (this.swipeRefreshLayout.visibility == View.VISIBLE) {
                swipeRefreshLayout.isRefreshing = true
            }
        }
    }

    fun inflateItemsView(binding: ActivityMainBinding) {
        this.swipeRefreshLayout = binding.includeItems.swipeRefreshLayout
        this.recyclerView = binding.includeItems.recyclerViewData

        with(this.recyclerView) {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = mAdapter
            mAdapter.onItemClickListener { item, position ->
                showNewsDetails(item)
            }
            mAdapter.onItemDeleted { item, position ->
                newsListViewModel.deleteNewsItem(item)
            }
        }
    }

    private fun showNewsDetails(newsItem: NewsItem) {
        Intent(this, MewsDetailsActivity::class.java).apply {
            val bundle = Bundle()
            bundle.putParcelable(SEND_MAIN_DATA, newsItem)
            putExtras(bundle)
            startActivity(this)
        }
    }

    override fun onSaveInstanceState(
        outState: Bundle,
        outPersistentState: PersistableBundle
    ) {
        super.onSaveInstanceState(outState, outPersistentState)
        mAdapter.saveStates(outState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
        mAdapter.restoreStates(savedInstanceState);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}