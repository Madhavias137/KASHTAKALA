package com.example.kashtakala.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_designs")
data class FavoriteDesign(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val image: String,
    val category: String,
    val designId: Int // Reference to the design in catalog
)
