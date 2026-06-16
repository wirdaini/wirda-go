package com.example.wirda_go.data.model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsItem>
)