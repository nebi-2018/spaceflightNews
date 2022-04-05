package com.example.spaceflightnews.repository

import com.example.spaceflightnews.db.NewsDao
import com.example.network.NewsApi
import com.example.network.RemoteToDomainMapper
import com.example.network.networkBoundResource
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val api: NewsApi,
    private val newsDao: NewsDao,
    private val remoteToDomainMapper: RemoteToDomainMapper
) : NewsRepository {


    override fun getNews() = networkBoundResource(
        query = {
            newsDao.getAllNews()
        },
        fetch = {
            api.getNews()
        },
        saveFetchResult = { news ->

            newsDao.deleteNews()
            newsDao.upsert(remoteToDomainMapper.mapFromNetworkArticleList(news))

        }
    )
}