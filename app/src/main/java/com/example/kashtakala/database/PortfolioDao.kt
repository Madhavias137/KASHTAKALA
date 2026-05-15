package com.example.kashtakala.database

import androidx.room.*
import com.example.kashtakala.models.PortfolioItem
import kotlinx.coroutines.flow.Flow

@Dao
interface PortfolioDao {
    @Query("SELECT * FROM portfolio_items ORDER BY timestamp DESC")
    fun getAllPortfolioItems(): Flow<List<PortfolioItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortfolioItem(item: PortfolioItem)

    @Delete
    suspend fun deletePortfolioItem(item: PortfolioItem)
}
