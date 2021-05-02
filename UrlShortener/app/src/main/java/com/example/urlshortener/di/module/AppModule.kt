package com.example.urlshortener.di.module

import android.content.Context
import com.example.urlshortener.data.api.ApiHelper
import com.example.urlshortener.data.api.ApiHelperImpl
import com.example.urlshortener.data.api.ApiService
import com.example.urlshortener.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit("http://ec2-34-239-107-56.compute-1.amazonaws.com") }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
} else OkHttpClient.Builder().build()

private fun provideApiService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)
private fun provideRetrofit(
    BASE_URL:String):Retrofit {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL).client(okHttpClient).build()
}



