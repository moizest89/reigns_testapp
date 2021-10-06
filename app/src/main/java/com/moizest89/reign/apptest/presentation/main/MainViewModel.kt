package com.moizest89.reign.apptest.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moizest89.reign.apptest.domain.model.NewsItem
import javax.inject.Inject

class MainViewModel @Inject constructor(

) : ViewModel(){

    private val _hitsData: MutableLiveData<MutableList<NewsItem>> = MutableLiveData()
    val hitsData: MutableLiveData<MutableList<NewsItem>> = _hitsData

}