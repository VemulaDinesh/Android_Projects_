package com.example.multirecyclerview

import retrofit2.http.GET

interface ApiService {
    @GET("mass-media/home")
    suspend fun getSections(): MainData
}
