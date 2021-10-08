package com.moizest89.reign.apptest.data.utils

import com.moizest89.reign.apptest.data.datasource.remote.RemoteResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

fun <T> HttpException.errorMessage(): RemoteResult.Error<T> {
    return try {
        this.response()?.errorBody()?.byteString()?.utf8()?.also {
            RemoteResult.Error<T>(this.message ?: it)
        }
    } catch (e: Exception) {
        RemoteResult.Error<T>(this.message ?: e.toString())
    } as RemoteResult.Error<T>
}

fun <T> Exception.errorMessage(): RemoteResult.Error<T> {
    return try {
        if (this.message == Utils.INTERNET_CONNECTION_MESSAGE) {
            RemoteResult.Error(
                message = Utils.INTERNET_CONNECTION_MESSAGE,
                code = Utils.INTERNET_CONNECTION_CODE
            )
        } else {
            RemoteResult.Error(this.message ?: this.toString())
        }
    } catch (e: Exception) {
        RemoteResult.Error<T>(this.message ?: e.toString())
    }
}

fun <INPUT, OUTPUT> RemoteResult<INPUT>.mapToResult(
    func: RemoteResult<INPUT>.(INPUT) -> OUTPUT
): RepositoryResult<OUTPUT> {
    return when(this){
        is RemoteResult.Success -> {
            RepositoryResult.Success(data = func(this.data))
        }
        is RemoteResult.Error ->{
            RepositoryResult.Error( message = this.message)
        }
    }
}

suspend fun <INPUT> RemoteResult<INPUT>.saveResult(
    func: suspend (INPUT) -> Unit
): RemoteResult<INPUT> {
    when(this){
        is RemoteResult.Success -> {
            withContext(Dispatchers.IO){
                func(data)
            }
        }
    }
    return this
}