package com.example.wirda_go.Data.Model

data class NewsItem(
    val title: String,
    val publishedAt: String,
    val urlToImage: String?,
    val description: String?,
    val url: String
)