package com.example.kashtakala.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_designs")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val image: String,
    val category: String
)
