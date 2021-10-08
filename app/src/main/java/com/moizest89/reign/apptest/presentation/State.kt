package com.moizest89.reign.apptest.presentation

sealed class State{
    data class LoadingState(var isLoading : Boolean = false) : State()
    data class ErrorState(
        var message: String?,
        var code: Int? = null
    ) : State()
    data class DataState<T>(var data: T) : State()
}
