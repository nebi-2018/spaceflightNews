package com.example.spaceflightnews.repository

import com.example.common.domain.NewsResponseItem
import com.example.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews():Flow<Resource<List<NewsResponseItem>>>
}