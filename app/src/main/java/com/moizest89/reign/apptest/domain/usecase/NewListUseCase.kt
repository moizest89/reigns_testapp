package com.moizest89.reign.apptest.domain.usecase

import com.moizest89.reign.apptest.data.utils.RepositoryResult
import com.moizest89.reign.apptest.domain.model.NewsItem
import com.moizest89.reign.apptest.domain.repository.NewsRepository
import com.moizest89.reign.apptest.presentation.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewListUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(): Flow<State> =
        newsRepository.getNewsListSearchByDateWithQueryMobile().map {
            when (it) {
                is RepositoryResult.Error -> {
                    State.ErrorState(message = it.message, code = it.code)
                }
                is RepositoryResult.Loading -> {
                    State.LoadingState(it.isLoading)
                }
                is RepositoryResult.Success -> {
                    State.DataState(data = it.data ?: mutableListOf())
                }
            }
        }.flowOn(Dispatchers.IO)

}