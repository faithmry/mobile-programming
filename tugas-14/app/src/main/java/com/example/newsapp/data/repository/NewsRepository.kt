package com.example.newsapp.data.repository

import com.example.newsapp.data.api.RetrofitClient

class NewsRepository {
    suspend fun getNews() = RetrofitClient.apiService.getTopHeadlines(
        apiKey = "fa87d8aa09f748bc926a18ec56ed0212"
    )
}
