package com.example.kashtakala.database

import androidx.room.*
import com.example.kashtakala.models.BookingItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BookingDao {
    @Query("SELECT * FROM bookings ORDER BY bookingDate DESC")
    fun getAllBookings(): Flow<List<BookingItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: BookingItem)

    @Delete
    suspend fun deleteBooking(booking: BookingItem)

    @Update
    suspend fun updateBooking(booking: BookingItem)
}
