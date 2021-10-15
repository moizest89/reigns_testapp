package com.moizest89.reign.apptest.data.datasource.remote

import com.moizest89.reign.apptest.data.utils.WifiService
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class ConnectivityInterceptor @Inject constructor(
    private var wifiService: WifiService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!wifiService.isOnline()) {
            throw NoConnectivityException()
        } else {
            return chain.proceed(chain.request())
        }
    }

    inner class NoConnectivityException : IOException() {
        override val message: String
            get() = "No network available, please check your WiFi or Data connection"
    }
}
