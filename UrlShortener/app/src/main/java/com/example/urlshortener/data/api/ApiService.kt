package com.example.urlshortener.data.api

import com.example.urlshortener.data.model.Url
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PUT

interface ApiService {
    @PUT("/")
    suspend fun encurtaUrl(@Body url:Map<String, String>): Response<Url>
}