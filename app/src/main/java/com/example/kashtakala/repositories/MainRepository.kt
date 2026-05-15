package com.example.kashtakala.repositories

import com.example.kashtakala.database.*
import com.example.kashtakala.models.*
import kotlinx.coroutines.flow.Flow

class MainRepository(
    private val quoteDao: QuoteDao,
    private val favoriteDao: FavoriteDao,
    private val portfolioDao: PortfolioDao,
    private val bookingDao: BookingDao,
    private val salesDao: SalesDao
) {
    // Quotes
    val allQuotes: Flow<List<QuoteEntity>> = quoteDao.getAllQuotes()
    suspend fun insertQuote(quote: QuoteEntity) = quoteDao.insertQuote(quote)
    suspend fun deleteQuote(quote: QuoteEntity) = quoteDao.deleteQuote(quote)

    // Favorites
    val allFavorites: Flow<List<FavoriteDesign>> = favoriteDao.getAllFavorites()
    suspend fun addFavorite(favorite: FavoriteDesign) = favoriteDao.insertFavorite(favorite)
    suspend fun removeFavorite(favorite: FavoriteDesign) = favoriteDao.deleteFavorite(favorite)
    suspend fun isFavorite(designId: Int) = favoriteDao.isFavorite(designId)
    suspend fun deleteFavoriteByDesignId(designId: Int) = favoriteDao.deleteByDesignId(designId)

    // Portfolio
    val allPortfolioItems: Flow<List<PortfolioItem>> = portfolioDao.getAllPortfolioItems()
    suspend fun insertPortfolioItem(item: PortfolioItem) = portfolioDao.insertPortfolioItem(item)
    suspend fun deletePortfolioItem(item: PortfolioItem) = portfolioDao.deletePortfolioItem(item)

    // Bookings
    val allBookings: Flow<List<BookingItem>> = bookingDao.getAllBookings()
    suspend fun insertBooking(booking: BookingItem) = bookingDao.insertBooking(booking)
    suspend fun updateBooking(booking: BookingItem) = bookingDao.updateBooking(booking)
    suspend fun deleteBooking(booking: BookingItem) = bookingDao.deleteBooking(booking)

    // Sales
    val allSales: Flow<List<SalesItem>> = salesDao.getAllSales()
    suspend fun insertSale(sale: SalesItem) = salesDao.insertSale(sale)
    suspend fun deleteSale(sale: SalesItem) = salesDao.deleteSale(sale)
}
