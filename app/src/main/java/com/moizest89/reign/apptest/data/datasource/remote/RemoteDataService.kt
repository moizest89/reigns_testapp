package com.moizest89.reign.apptest.data.datasource.remote

import com.moizest89.reign.apptest.data.model.hits.Hits
import retrofit2.Response
import retrofit2.http.GET

interface RemoteDataService {

    @GET("search_by_date?query=mobile")
    suspend fun getHitsByQuery(): Response<Hits>

}