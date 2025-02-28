package com.geeks.chat.model.core

import com.geeks.chat.model.data.ChatService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

object RetrofitClient {

    private const val BASE_URL = "https://nomadnova.com.kg/api/"
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }
    val chatService: ChatService by lazy {
        retrofit.create(ChatService::class.java)
    }
}