package com.example.spaceflightnews

import com.example.network.RemoteNewsResponseItem
import com.example.network.RemoteToDomainMapper
import org.junit.Test

class RemoteToDomainMapperTest {
    private val mapper = RemoteToDomainMapper()
    private val remoteNewsResponseItem = RemoteNewsResponseItem(
        id = "newsId",
        imageUrl = "image uri",
        newsSite = "news site",
        publishedAt = "date",
        summary = "summery",
        title = "title",
        url = "news uri"
    )


    @Test
    fun remoteToDomainMapperShouldMapNews() {

        val news = mapper.mapFromRemoteEntity(remoteNewsResponseItem)
        assert(
            news.id == remoteNewsResponseItem.id &&
                    news.imageUrl == remoteNewsResponseItem.imageUrl &&
                    news.newsSite == remoteNewsResponseItem.newsSite &&
                    news.publishedAt == news.publishedAt &&
                    news.summary == news.summary &&
                    news.title == news.title
        )

    }
}