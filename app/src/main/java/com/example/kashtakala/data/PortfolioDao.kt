package com.example.kashtakala.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Query("SELECT * FROM portfolio_items ORDER BY timestamp DESC")
    fun getAllItems(): Flow<List<PortfolioItem>>

    @Insert
    suspend fun insertItem(item: PortfolioItem)

    @Delete
    suspend fun deleteItem(item: PortfolioItem)

    @Query("DELETE FROM portfolio_items")
    suspend fun deleteAll()
}
