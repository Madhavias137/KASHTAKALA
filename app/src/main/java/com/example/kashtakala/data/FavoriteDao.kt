package com.example.kashtakala.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT designId FROM favorite_designs")
    fun getAllFavoriteIds(): Flow<List<Int>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteDesign)

    @Delete
    suspend fun deleteFavorite(favorite: FavoriteDesign)

    @Query("SELECT EXISTS(SELECT * FROM favorite_designs WHERE designId = :id)")
    suspend fun isFavorite(id: Int): Boolean
}
