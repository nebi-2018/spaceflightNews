package com.example.spaceflightnews

import com.example.common.domain.NewsResponseItem
import com.example.common.util.Resource
import com.example.network.RemoteNewsResponseItem
import com.example.spaceflightnews.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNewsRepository:NewsRepository {
    var isLoading = true
    var isError = false
    var news =   NewsResponseItem(
        id = "newsId",
        imageUrl = "image uri",
        newsSite = "news site",
        publishedAt = "date",
        summary = "summery",
        title = "title",
        url = "news uri"
    )
    override fun getNews(): Flow<Resource<List<NewsResponseItem>>>  = flow{
        emit(Resource.Loading())
        if (isError){
            emit(Resource.Error(data = null, throwable = Throwable()))
        }else{
            emit(Resource.Success(listOf(news)))
        }

    }
}