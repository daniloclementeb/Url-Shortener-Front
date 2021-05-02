package com.example.urlshortener.utils

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build

class NetworkHelper constructor(private val context: Context) {
    fun isNetworkConnected():Boolean {
        var result = false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.run {
            connectivityManager.activeNetworkInfo.run {
                result = when (this!!.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return result
    }
}