package com.example.kashtakala.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kashtakala.models.*

@Database(entities = [QuoteEntity::class, FavoriteDesign::class, PortfolioItem::class, BookingItem::class, SalesItem::class], version = 4, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun portfolioDao(): PortfolioDao
    abstract fun bookingDao(): BookingDao
    abstract fun salesDao(): SalesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kashtakala_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
