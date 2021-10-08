package com.moizest89.reign.apptest.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.moizest89.reign.apptest.domain.usecase.NewListUseCase
import com.moizest89.reign.apptest.presentation.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsListUseCase: NewListUseCase
) : ViewModel(){

    private val _newsData: MutableLiveData<State> = MutableLiveData()
    val newsData: MutableLiveData<State> = _newsData

    fun getNewsList(){
        viewModelScope.launch {
            newsListUseCase.invoke().collect {
                _newsData.value = it
            }
        }
    }

}