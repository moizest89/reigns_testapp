package com.moizest89.reign.apptest.domain.repository

import com.moizest89.reign.apptest.data.utils.RepositoryResult
import com.moizest89.reign.apptest.domain.model.NewsItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsListSearchByDateWithQueryMobile() : Flow<RepositoryResult<MutableList<NewsItem>>>
}