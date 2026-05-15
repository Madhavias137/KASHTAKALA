package com.example.kashtakala.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "designs")
data class DesignItem(
    @PrimaryKey val id: Int,
    val name: String,
    val info: String,
    val category: String,
    val imageUrl: String,
    val price: Double = 0.0,
    val rating: Float = 0f,
    val reviewCount: Int = 0,
    val isNew: Boolean = false,
    val isTrending: Boolean = false,
    val material: String = "Teak Wood",
    val color: String = "Natural",
    val length: Double = 0.0, // in feet
    val width: Double = 0.0,  // in feet
    val height: Double = 0.0, // in feet
    val estimatedSqFt: Double = 0.0,
    val arModelUrl: String? = null,
    val threeDModelUrl: String? = null
) {
    val volumeCuFt: Double
        get() = length * width * height

    val boardFeet: Double
        get() = volumeCuFt * 12.0

    val surfaceAreaSqFt: Double
        get() = 2 * (length * width + width * height + height * length)
}
