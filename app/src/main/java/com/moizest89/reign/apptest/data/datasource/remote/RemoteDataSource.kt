package com.moizest89.reign.apptest.data.datasource.remote

import com.moizest89.reign.apptest.data.model.hits.Hits
interface RemoteDataSource {
    suspend fun getHitsByQuery( query : String ) : RemoteResult<Hits>
}