package com.example.urlshortener.data.api

import com.example.urlshortener.data.model.Url
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun encurtaUrl(url:Map<String, String>):Response<Url> = apiService.encurtaUrl(url)
}