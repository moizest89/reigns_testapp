package com.moizest89.reign.apptest.di

import android.content.Context
import com.moizest89.reign.apptest.BuildConfig
import com.moizest89.reign.apptest.data.datasource.remote.ConnectivityInterceptor
import com.moizest89.reign.apptest.data.datasource.remote.RemoteDataService
import com.moizest89.reign.apptest.data.datasource.remote.RemoteDataSource
import com.moizest89.reign.apptest.data.datasource.remote.RemoteDataSourceImpl
import com.moizest89.reign.apptest.data.repository.NewsRepositoryImpl
import com.moizest89.reign.apptest.data.utils.WifiService
import com.moizest89.reign.apptest.domain.repository.NewsRepository
import com.moizest89.reign.apptest.presentation.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(connectivityInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideNetworkUtils(@ApplicationContext context: Context): NetworkUtils =
        NetworkUtils(context)

    @Singleton
    @Provides
    fun provideWifiService(@ApplicationContext context: Context): WifiService =
        WifiService(context)

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): RemoteDataService =
        retrofit.create(RemoteDataService::class.java)

    @Provides
    fun provideRemoteDataSourceImpl(
        remoteDataService: RemoteDataService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(remoteDataService)
    }

    //Repositories
    @Singleton
    @Provides
    fun provideNewsRepository(
        remoteDataSource: RemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(remoteDataSource)
    }

}