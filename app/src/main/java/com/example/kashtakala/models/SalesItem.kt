package com.example.kashtakala.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sales_records")
data class SalesItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val customerName: String,
    val saleDate: String,
    val price: Double,
    val paymentMethod: String // "Cash", "Online", "Pending"
)
