package com.example.multirecyclerview

data class Media(
    val id: Int,
    val title: String,
    val summary: String,
    val releaseDate: String,
    val rating: String,
    val posterImageUrl: String,
    val trailerUrl: String,
    val mediaType: String,
    val topRated: Boolean
)

data class MediaResponse(
    val media: Media,
    val errors: Any? // You can define proper error model if required
)

