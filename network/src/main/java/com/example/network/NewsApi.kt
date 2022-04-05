package com.example.network


import retrofit2.http.GET
import retrofit2.http.Query

private const val defaultNumberOfNews = 7

interface NewsApi {


    @GET("v3/articles")
    suspend fun getNews(@Query("_limit") limit:Int= defaultNumberOfNews):List<RemoteNewsResponseItem>
}