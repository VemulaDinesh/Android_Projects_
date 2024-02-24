package com.example.image

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.image.UnsplashApi

object RetrofitClient {
    const val BASE_URL = "https://api.unsplash.com"
    val instance: UnsplashApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(UnsplashApi::class.java)
    }
}
