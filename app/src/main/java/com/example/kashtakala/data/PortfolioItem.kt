package com.example.kashtakala.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "portfolio_items")
data class PortfolioItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String = "",
    val imageUri: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
