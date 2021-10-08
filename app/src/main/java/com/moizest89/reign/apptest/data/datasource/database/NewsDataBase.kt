package com.moizest89.reign.apptest.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moizest89.reign.apptest.data.model.db.NewsEntity

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}