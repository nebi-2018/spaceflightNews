package com.example.spaceflightnews.db

import androidx.room.*
import com.example.common.domain.NewsResponseItem
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(news: List<NewsResponseItem>)

    @Query("SELECT * FROM news ORDER BY id DESC")
    fun getAllNews():Flow<List<NewsResponseItem>>

    @Query("DELETE FROM news")
    suspend fun deleteNews()

}
