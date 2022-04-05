package com.example.network

import com.example.common.domain.NewsResponseItem
import com.example.common.util.EntityMapper

class RemoteToDomainMapper:EntityMapper<RemoteNewsResponseItem,NewsResponseItem> {
    override fun mapFromRemoteEntity(entity: RemoteNewsResponseItem): NewsResponseItem {
        return NewsResponseItem(
            id = entity.id,
            imageUrl = entity.imageUrl,
            newsSite = entity.newsSite,
            publishedAt = entity.publishedAt,
            summary = entity.summary,
            title = entity.title,
            url = entity.url
        )
    }

    fun mapFromNetworkArticleList(entities: List<RemoteNewsResponseItem>): List<NewsResponseItem> {
        return entities.map {
            mapFromRemoteEntity(it)
        }
    }
}