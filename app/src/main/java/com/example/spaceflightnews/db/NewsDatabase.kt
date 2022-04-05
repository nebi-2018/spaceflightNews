package com.example.spaceflightnews.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.common.domain.NewsResponseItem


private const val databaseVersion = 1

@Database(
    entities = [NewsResponseItem::class],
    version = databaseVersion
)
abstract class NewsDatabase : RoomDatabase(){

    abstract fun getNewsDao():NewsDao

}
