package com.example.common.domain

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "news"
)
data class NewsResponseItem(
    @PrimaryKey
    val id: String,
    val imageUrl: String,
    val newsSite: String,
    val publishedAt: String,
    val summary: String,
    val title: String,
    val url: String
)
