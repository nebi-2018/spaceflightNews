package com.example.spaceflightnews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.common.domain.NewsResponseItem
import com.example.common.util.Resource
import com.example.spaceflightnews.ui.NewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var repository: FakeNewsRepository
    private val newsResponse = listOf(
        NewsResponseItem(
            id = "newsId",
            imageUrl = "image uri",
            newsSite = "news site",
            publishedAt = "date",
            summary = "summery",
            title = "title",
            url = "news uri"
        )
    )


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = TestCoroutineRule()


    @Before
    fun setUp() {
        repository = FakeNewsRepository()
        newsViewModel = NewsViewModel(repository)
    }

    @Test
    fun viewModelShouldGetNewsFromRepository() = runBlocking {
        newsViewModel.getNews()
        val newsList = newsViewModel.news.getOrAwaitValueTest()
        assert(newsList.data == newsResponse)

    }

    @Test
    fun viewModelShouldGetErrorResponse() = runBlocking {
        repository.isError = true
        val mm = newsViewModel.getNews()
        val newsList = newsViewModel.news.getOrAwaitValueTest()
        assert(newsList is Resource.Error)
        assert(newsList.data == null)


    }

}