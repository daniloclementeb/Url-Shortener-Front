package com.example.urlshortener.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Url (
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    val message:String,
    @SerializedName("url")
    val url:Array<Map<Object,Object>>
)