package com.moizest89.reign.apptest.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.moizest89.reign.apptest.domain.usecase.DeleteNewsItemUseCase
import com.moizest89.reign.apptest.domain.usecase.NewsListUseCase
import com.moizest89.reign.apptest.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsListUseCase: NewsListUseCase,
    private val deleteNewsItemUseCase: DeleteNewsItemUseCase
) : ViewModel() {

    private val _newsData: MutableLiveData<State> = MutableLiveData()
    val newsData: MutableLiveData<State> = _newsData

    fun getNewsList( fromCache: Boolean = true) {
        viewModelScope.launch {
            newsListUseCase.invoke(fromCache).collect {
                _newsData.value = it
            }
        }
    }

    fun deleteNewsItem(newsItem: NewsItem) {
        viewModelScope.launch {
            deleteNewsItemUseCase.invoke(newsItem).collect {
                _newsData.value = it
            }
        }
    }

}