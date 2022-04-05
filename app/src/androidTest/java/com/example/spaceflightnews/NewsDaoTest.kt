package com.example.spaceflightnews

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.common.domain.NewsResponseItem
import com.example.spaceflightnews.db.NewsDao
import com.example.spaceflightnews.db.NewsDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class NewsDaoTest {
    private lateinit var newsDatabase:NewsDatabase
    private lateinit var dao: NewsDao
    private val news = NewsResponseItem(id = "newsId" , imageUrl = "image uri", newsSite = "news site", publishedAt = "date", summary = "summery", title = "title", url = "news uri" )
    private val news2 = NewsResponseItem(id = "newsId" , imageUrl = "image uri", newsSite = "news site", publishedAt = "date", summary = "summery", title = "title two", url = "news uri" )
    private val newsList = listOf(news)

    @Before
    fun setUp(){
        newsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = newsDatabase.getNewsDao()
    }
    @After
    fun removeDatabase(){
        newsDatabase.close()
    }

    @Test
    fun daoShouldInsertTheNews() = runBlocking {

        dao.upsert(newsList)
        val newsList = dao.getAllNews().first()
        assert(newsList.contains(news))

    }


    @Test
    fun daoShouldUpdateTheNews() = runBlocking {

        dao.upsert(newsList)
        dao.upsert(listOf(news2))
        val newsList = dao.getAllNews().first()
        assert(newsList.size == 1 )
        assert(newsList.contains(news2) )
    }

    @Test
    fun daoShouldDeleteNews() = runBlocking {

        dao.upsert(newsList)
        assert(newsList.contains(news) )
        dao.deleteNews()
        val news =  dao.getAllNews().first()
        assert(news.isEmpty())

    }

}