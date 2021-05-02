package com.example.urlshortener.data.api

import com.example.urlshortener.data.model.Url
import retrofit2.Response

interface ApiHelper {
    suspend fun encurtaUrl(url:Map<String, String>): Response<Url>
}