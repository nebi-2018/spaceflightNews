package com.example.network


import com.google.gson.annotations.SerializedName


data class RemoteNewsResponseItem(

    @SerializedName("id")
    val id: String,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("newsSite")
    val newsSite: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)