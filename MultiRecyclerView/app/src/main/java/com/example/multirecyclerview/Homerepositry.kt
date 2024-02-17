package com.example.multirecyclerview

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {
    private val retroClient = RetrofitClient.apiService

    suspend fun getSections(): MainData? {
        val response = retroClient.getSections()
        return response
    }
}

