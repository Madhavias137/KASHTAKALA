package com.example.kashtakala.database

import androidx.room.*
import com.example.kashtakala.models.SalesItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SalesDao {
    @Query("SELECT * FROM sales_records ORDER BY saleDate DESC")
    fun getAllSales(): Flow<List<SalesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSale(sale: SalesItem)

    @Delete
    suspend fun deleteSale(sale: SalesItem)
}
