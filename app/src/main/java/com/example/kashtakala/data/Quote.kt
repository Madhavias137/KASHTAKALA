package com.example.kashtakala.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val materialCost: Double,
    val laborCost: Double,
    val total: Double,
    val timestamp: Long = System.currentTimeMillis()
)
