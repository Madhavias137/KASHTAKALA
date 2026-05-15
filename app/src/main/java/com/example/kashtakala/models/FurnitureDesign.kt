package com.example.kashtakala.models

data class FurnitureDesign(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val imageUrl: String,
    val dimensions: String,
    val materialType: String,
    val estimatedPrice: Double,
    val length: Double = 0.0,
    val width: Double = 0.0,
    val height: Double = 0.0
)
