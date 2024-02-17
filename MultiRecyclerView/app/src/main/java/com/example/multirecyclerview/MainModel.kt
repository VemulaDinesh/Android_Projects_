package com.example.multirecyclerview


data class Card(
    val title: String,
    val subtitle: String?,
    val imageUrl: String,
    val deeplink: String,
    val type: String,
    val id: Int
)

data class Section(
    val title: String,
    val subtitle: String,
    val type: String,
    val cards: List<Card>,
    val numberOfColumns: Int? = 1
)

data class MainData(
    val sections: List<Section>,
    val version: Int,
    val variant: String
)
