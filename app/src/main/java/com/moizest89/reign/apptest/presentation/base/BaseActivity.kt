package com.moizest89.reign.apptest.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moizest89.reign.apptest.databinding.ActivityMainBinding

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
    }

    open fun inflateItemsView(binding: ActivityMainBinding){}
}