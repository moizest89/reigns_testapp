package com.moizest89.reign.apptest.data.datasource.remote

sealed class RemoteResult<T>{
    class Success<T>(var data: T) : RemoteResult<T>()
    class Error<T>(val message: String, val code: Int? = null) : RemoteResult<T>()
}
