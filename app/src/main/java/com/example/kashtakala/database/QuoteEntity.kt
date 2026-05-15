package com.example.kashtakala.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerName: String,
    val materialCost: Double,
    val laborCost: Double,
    val finishingCost: Double,
    val transportationCost: Double,
    val totalCost: Double,
    val timestamp: Long = System.currentTimeMillis()
)
