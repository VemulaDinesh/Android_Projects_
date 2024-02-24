package com.example.image.model

// Photo.kt
data class Photo(
    val id: String,
    val urls: Urls
)

data class Urls(
    val regular: String
)

