package com.moizest89.reign.apptest.data.datasource.database

import com.moizest89.reign.apptest.data.model.db.NewsEntity

interface DataBaseDataSource {
    suspend fun insertAllNewsItems(itemList: MutableList<NewsEntity>): MutableList<NewsEntity>
    suspend fun getAllNewsItems() : MutableList<NewsEntity>
    suspend fun deleteAllAllItems()
}