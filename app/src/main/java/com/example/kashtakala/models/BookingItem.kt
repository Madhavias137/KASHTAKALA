package com.example.kashtakala.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class BookingItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerName: String,
    val furnitureType: String,
    val bookingDate: String,
    val estimatedDelivery: String,
    val amount: Double,
    val status: String, // "Pending", "In Progress", "Completed"
    val paymentMode: String // "Cash", "UPI", "Card", "Bank Transfer"
)
