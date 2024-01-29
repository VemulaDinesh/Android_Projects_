package com.example.image

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.image.UnsplashApi

object RetrofitClient {
    const val BASE_URL = "https://api.unsplash.com"
    //private const val CLIENT_ID = "Yudll0z4Q1SAIYfg25KLl5mfTOW-bEnc35I6U1d6Qcc"
    val instance: UnsplashApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(UnsplashApi::class.java)
    }
}
