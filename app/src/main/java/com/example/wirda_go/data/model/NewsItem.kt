package com.example.wirda_go.data.model

data class NewsItem(
    val title: String,
    val publishedAt: String,
    val urlToImage: String?,
    val description: String?,
    val url: String
)