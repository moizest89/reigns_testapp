package com.moizest89.reign.apptest.data.datasource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moizest89.reign.apptest.data.model.db.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewsItems(counterItems: List<NewsEntity>)

    @Query("DELETE FROM NewsEntity")
    suspend fun deleteNewsItems()

    @Query("SELECT * FROM NewsEntity")
    suspend fun getNewsItems(): Array<NewsEntity>
}