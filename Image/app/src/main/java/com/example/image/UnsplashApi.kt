package com.example.image

//import android.telecom.Call
import com.example.image.model.Photo
// UnsplashApi.kt
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call
import retrofit2.http.Headers

interface UnsplashApi {
    @Headers("Authorization: Client-ID "+"Yudll0z4Q1SAIYfg25KLl5mfTOW-bEnc35I6U1d6Qcc")
    @GET("/photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Photo>>
    @Headers("Authorization: Client-ID "+"Yudll0z4Q1SAIYfg25KLl5mfTOW-bEnc35I6U1d6Qcc")
    @GET("/photos/random")
    fun getRandomPhotos(
        @Query("count") count: Int
    ): Call<List<Photo>>
}

