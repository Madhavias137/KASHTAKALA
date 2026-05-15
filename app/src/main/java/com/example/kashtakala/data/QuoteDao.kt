package com.example.kashtakala.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT * FROM quotes ORDER BY timestamp DESC")
    fun getAllQuotes(): Flow<List<Quote>>

    @Insert
    suspend fun insertQuote(quote: Quote)

    @Query("DELETE FROM quotes")
    suspend fun deleteAll()
}
