package com.example.kashtakala.database

import androidx.room.*
import com.example.kashtakala.models.FavoriteDesign
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite_designs")
    fun getAllFavorites(): Flow<List<FavoriteDesign>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteDesign)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteDesign)

    @Query("SELECT EXISTS(SELECT * FROM favorite_designs WHERE designId = :designId)")
    suspend fun isFavorite(designId: Int): Boolean
    
    @Query("DELETE FROM favorite_designs WHERE designId = :designId")
    suspend fun deleteByDesignId(designId: Int)
}
