package com.moizest89.reign.apptest.presentation.details

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.moizest89.reign.apptest.R
import com.moizest89.reign.apptest.databinding.ActivityMewsDetailsBinding
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.moizest89.reign.apptest.presentation.base.BaseActivity
import com.moizest89.reign.apptest.presentation.utils.Utils.SEND_MAIN_DATA

class MewsDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityMewsDetailsBinding

    private lateinit var progressBar: ProgressBar

    private var newsItem: NewsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_mews_details)
        setContentView(binding.root)

        this.newsItem = intent.getParcelableExtra(SEND_MAIN_DATA)

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
                binding.webview.visibility = View.VISIBLE
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                binding.progressBar.visibility = View.VISIBLE
                binding.webview.visibility = View.GONE
            }
        }
        this.newsItem?.url?.let {
            binding.webview.loadUrl(it)
        }

        this.newsItem?.title?.let {
            binding.toolbar.navigationIcon =
                ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
            binding.textViewTitle.text = it
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_share_action, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_share_action -> {
                shareNewItem(newsItem)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareNewItem(newsItem: NewsItem?) {
        if (newsItem != null) {
            val text = getString(R.string.news_details_share_item, newsItem.title, newsItem.url)
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(intent, getString(R.string.share)))
        }
    }
}