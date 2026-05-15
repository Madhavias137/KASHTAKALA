package com.example.kashtakala.models

data class Furniture(
    val id: Int,
    val name: String,
    val price: String,
    val category: String,
    val imageUrl: String,
    var isFavorite: Boolean = false
)
