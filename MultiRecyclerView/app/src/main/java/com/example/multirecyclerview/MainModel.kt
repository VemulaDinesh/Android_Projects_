package com.example.multirecyclerview

data class Card(
    val type: String,
    val deeplink: String,
    val imageUrl: String
)

data class Section(
    val type: String,
    val no_of_coloumns:Int,
    val title: String,
    val subtitle: String,
    val cards: List<Card>
)

data class MainData(
    val sections: List<Section>,
    val version: String,
    val variant: String
)

