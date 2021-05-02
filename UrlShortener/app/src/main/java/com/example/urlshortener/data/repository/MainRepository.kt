package com.example.urlshortener.data.repository

import com.example.urlshortener.data.api.ApiHelper
import com.example.urlshortener.data.model.Url

class MainRepository (private val apiHelper: ApiHelper){
    suspend fun encurtaUrl(url: Map<String, String>) = apiHelper.encurtaUrl(url)
}