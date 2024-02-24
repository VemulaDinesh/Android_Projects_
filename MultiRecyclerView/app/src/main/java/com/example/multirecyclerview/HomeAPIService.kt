package com.example.multirecyclerview

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("home")
    suspend fun getSections(): MainData
    @GET("media/{id}")
    suspend fun getMediaDetails(@Path("id") id: Int): MediaResponse
}
