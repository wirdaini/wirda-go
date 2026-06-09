package com.example.wirda_go.Data.Model

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsItem>
)