package com.moizest89.reign.apptest.data.repository

import com.moizest89.reign.apptest.data.datasource.database.DataBaseDataSource
import com.moizest89.reign.apptest.data.datasource.remote.RemoteDataSource
import com.moizest89.reign.apptest.data.mapper.toNewsEntities
import com.moizest89.reign.apptest.data.mapper.toNewsEntity
import com.moizest89.reign.apptest.data.mapper.toNewsItems
import com.moizest89.reign.apptest.data.mapper.toNewsListItem
import com.moizest89.reign.apptest.data.utils.RepositoryResult
import com.moizest89.reign.apptest.data.utils.mapToResult
import com.moizest89.reign.apptest.data.utils.saveResult
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.moizest89.reign.apptest.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataBaseDataSource: DataBaseDataSource
) : NewsRepository {

    override suspend fun getNewsListSearchByDateWithQueryMobile(reloadFromCache: Boolean): Flow<RepositoryResult<MutableList<NewsItem>>> =
        flow {
            emit(RepositoryResult.Loading(true))
            if (reloadFromCache) {
                emit(RepositoryResult.Success(data = dataBaseDataSource.getAllNewsItems().toNewsItems()))
                emit(RepositoryResult.Loading(true))
            }
            //remote
            val fetch = remoteDataSource.getHitsByQuery("mobile")
            fetch.saveResult { dataBaseDataSource.insertAllNewsItems(it.toNewsEntities()) }
            emit(fetch.mapToResult { it.toNewsListItem() })
            emit(RepositoryResult.Loading(false))
        }

    override suspend fun deleteNewsItem(newsItem: NewsItem): Flow<RepositoryResult<MutableList<NewsItem>>> =
        flow {
            emit(RepositoryResult.Loading(true))
            dataBaseDataSource.deleteNewsItem(newsItem.toNewsEntity())
            val fromDatabase = dataBaseDataSource.getAllNewsItems().toNewsItems()
            emit(RepositoryResult.Success(data = fromDatabase))
            emit(RepositoryResult.Loading(false))
        }

}