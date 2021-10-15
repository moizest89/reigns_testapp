package com.moizest89.reign.apptest.data.datasource.database

import com.moizest89.reign.apptest.data.model.db.NewsEntity
import javax.inject.Inject

class DataBaseDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao
) : DataBaseDataSource {
    override suspend fun insertAllNewsItems(itemList: MutableList<NewsEntity>): MutableList<NewsEntity> {
        this.deleteAllNewsItems()
        if (itemList.isNotEmpty()) this.newsDao.insertNewsItems(itemList)
        return itemList
    }

    override suspend fun getAllNewsItems(): MutableList<NewsEntity> {
        return newsDao.getNewsItems().toMutableList()
    }

    override suspend fun deleteAllNewsItems() {
        this.newsDao.deleteNewsItems()
    }

    override suspend fun deleteNewsItem(newsEntity: NewsEntity) {
        this.newsDao.deleteNewsItemById(newsEntity.id)
    }
}