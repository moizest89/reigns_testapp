package com.moizest89.reign.apptest.data.datasource.remote

import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private var remoteDataService: RemoteDataService
) : RemoteDataSource {

}