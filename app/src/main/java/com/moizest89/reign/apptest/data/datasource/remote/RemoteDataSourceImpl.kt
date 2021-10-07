package com.moizest89.reign.apptest.data.datasource.remote

import com.moizest89.reign.apptest.data.model.hits.Hits
import com.moizest89.reign.apptest.data.utils.errorMessage
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private var remoteDataService: RemoteDataService
) : RemoteDataSource {

    override suspend fun getHitsByQuery(query: String): RemoteResult<Hits> {
        try {
            val response = remoteDataService.getHitsByQuery()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return RemoteResult.Success(body)
                }
            }
            return RemoteResult.Error(
                message = response.message(),
                code = response.code()
            )
        } catch (e: IOException) {
            return e.errorMessage()
        } catch (e: HttpException) {
            return e.errorMessage()
        }
    }

}