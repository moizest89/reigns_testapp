package com.moizest89.reign.apptest

import android.os.Bundle
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
import com.moizest89.reign.apptest.presentation.main.MainAdapter
import com.moizest89.reign.apptest.presentation.main.MainViewModel
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
                    this.progressBar.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                    if(!it.isLoading)
                        this.swipeRefreshLayout.visibility = View.VISIBLE
                }
                is State.DataState<*> -> {
                    this.mAdapter.setData(it.data as MutableList<NewsItem>)
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
    }

    override fun inflateItemsView(binding: ActivityMainBinding) {
        this.swipeRefreshLayout = binding.includeItems.swipeRefreshLayout
        this.recyclerView = binding.includeItems.recyclerViewData
        this.progressBar = binding.includeItems.progressBar

        with(this.recyclerView) {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = mAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}