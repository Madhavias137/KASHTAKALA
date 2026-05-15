package com.example.kashtakala.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.kashtakala.database.AppDatabase
import com.example.kashtakala.database.QuoteEntity
import com.example.kashtakala.models.*
import com.example.kashtakala.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MainRepository
    val allQuotes: LiveData<List<QuoteEntity>>
    val allFavorites: LiveData<List<FavoriteDesign>>
    val allPortfolioItems: LiveData<List<PortfolioItem>>
    val allBookings: LiveData<List<BookingItem>>
    val allSales: LiveData<List<SalesItem>>

    // Shared estimation data
    val currentMaterialEstimate = MutableLiveData<Double>(0.0)

    init {
        val db = AppDatabase.getDatabase(application)
        repository = MainRepository(db.quoteDao(), db.favoriteDao(), db.portfolioDao(), db.bookingDao(), db.salesDao())
        allQuotes = repository.allQuotes.asLiveData()
        allFavorites = repository.allFavorites.asLiveData()
        allPortfolioItems = repository.allPortfolioItems.asLiveData()
        allBookings = repository.allBookings.asLiveData()
        allSales = repository.allSales.asLiveData()
    }

    // Quotes
    fun insertQuote(quote: QuoteEntity) = viewModelScope.launch {
        repository.insertQuote(quote)
    }

    // Favorites
    fun toggleFavorite(design: FurnitureDesign) = viewModelScope.launch {
        if (repository.isFavorite(design.id)) {
            repository.deleteFavoriteByDesignId(design.id)
        } else {
            repository.addFavorite(FavoriteDesign(
                designId = design.id,
                title = design.title, 
                image = design.imageUrl, 
                category = design.category
            ))
        }
    }

    fun isFavorite(designId: Int): LiveData<Boolean> {
        return allFavorites.map { favorites ->
            favorites.any { it.designId == designId }
        }
    }

    // Portfolio
    fun addPortfolioItem(item: PortfolioItem) = viewModelScope.launch {
        repository.insertPortfolioItem(item)
    }

    fun deletePortfolioItem(item: PortfolioItem) = viewModelScope.launch {
        repository.deletePortfolioItem(item)
    }

    // Bookings
    fun addBooking(booking: BookingItem) = viewModelScope.launch {
        repository.insertBooking(booking)
    }

    fun updateBooking(booking: BookingItem) = viewModelScope.launch {
        repository.updateBooking(booking)
    }

    fun deleteBooking(booking: BookingItem) = viewModelScope.launch {
        repository.deleteBooking(booking)
    }

    // Sales
    fun addSale(sale: SalesItem) = viewModelScope.launch {
        repository.insertSale(sale)
    }

    fun deleteSale(sale: SalesItem) = viewModelScope.launch {
        repository.deleteSale(sale)
    }

    // Dummy Data for Catalog
    private val _furnitureDesigns = MutableLiveData<List<FurnitureDesign>>()
    val furnitureDesigns: LiveData<List<FurnitureDesign>> get() = _furnitureDesigns

    fun loadDummyDesigns() {
        if (_furnitureDesigns.value == null) {
            val dummyList = listOf(
                // TEAK COLLECTION (Premium Wood)
                FurnitureDesign(1, "Premium Teak Wood Sofa", "Luxury 3-seater solid teak wood sofa with premium finish.", "Sofas", "https://images.unsplash.com/photo-1555041469-a586c61ea9bc", "7.0ft x 3.0ft x 3.0ft", "Teak Wood", 45000.0, 7.0, 3.0, 3.0),
                FurnitureDesign(2, "Masterpiece Teak Table", "Handcrafted solid teak wood dining table.", "Dining Tables", "https://images.unsplash.com/photo-1577145946459-39a587ed50c4", "6.0ft x 4.0ft x 2.5ft", "Teak Wood", 35000.0, 6.0, 4.0, 2.5),
                FurnitureDesign(21, "Heritage Teak Wardrobe", "Elegant teak wood wardrobe with traditional carving.", "Cabinets", "https://images.unsplash.com/photo-1595428774223-ef52624120d2", "6.0ft x 2.0ft x 7.0ft", "Teak Wood", 52000.0, 6.0, 2.0, 7.0),
                FurnitureDesign(22, "Royal Teak King Bed", "Solid teak wood king-size bed built for generations.", "Beds", "https://images.unsplash.com/photo-1505693419148-40b1b6eb4e49", "6.5ft x 6.0ft x 4.0ft", "Teak Wood", 58000.0, 6.5, 6.0, 4.0),
                FurnitureDesign(27, "Teak Wood Corner Stand", "Decorative teak wood stand for luxury living rooms.", "Wooden Modern", "https://images.unsplash.com/photo-1592078615290-033ee584e267", "2.0ft x 2.0ft x 4.0ft", "Teak Wood", 8000.0, 2.0, 2.0, 4.0),
                
                // SOFAS
                FurnitureDesign(10, "L-Shaped Modern Sofa", "Spacious sectional sofa for large living rooms.", "Sofas", "https://images.unsplash.com/photo-1493663284031-b7e3aefcae8e", "10.0ft x 8.0ft x 3.0ft", "Fabric & Pine", 65000.0, 10.0, 8.0, 3.0),
                FurnitureDesign(11, "Urban Wooden Sofa", "Sleek wooden frame with comfortable cushions.", "Sofas", "https://images.unsplash.com/photo-1583847268964-b28dc2f51ac9", "6.0ft x 3.0ft x 2.5ft", "Oak & Fabric", 32000.0, 6.0, 3.0, 2.5),
                FurnitureDesign(14, "Classic Chesterfield", "Leather Chesterfield sofa with deep tufting.", "Sofas", "https://images.unsplash.com/photo-1550581190-9c1c48d21d6c", "8.0ft x 3.5ft x 3.0ft", "Leather & Mahogany", 75000.0, 8.0, 3.5, 3.0),
                
                // BEDS
                FurnitureDesign(3, "Majestic Royal Bed", "Premium carving work on solid rosewood headboard.", "Beds", "https://images.unsplash.com/photo-1505693309566-da81e3ff2158", "6.5ft x 6.0ft x 4.0ft", "Rosewood", 55000.0, 6.5, 6.0, 4.0),
                FurnitureDesign(7, "Storage Master Bed", "Modern bed with built-in hydraulic storage system.", "Beds", "https://images.unsplash.com/photo-1540518614846-7eded433c457", "6.0ft x 5.0ft x 3.5ft", "Engineered Wood", 42000.0, 6.0, 5.0, 3.5),
                FurnitureDesign(12, "Luxe Floating Bed", "Minimalist floating design with integrated LED lighting.", "Beds", "https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af", "6.5ft x 6.0ft x 1.5ft", "Walnut Wood", 48000.0, 6.5, 6.0, 1.5),

                // CABINETS
                FurnitureDesign(4, "Glass Door Wardrobe", "Modern modular wardrobe with transparent glass doors.", "Cabinets", "https://images.unsplash.com/photo-1595428774223-ef52624120d2", "6.0ft x 2.0ft x 7.0ft", "Plywood & Glass", 28000.0, 6.0, 2.0, 7.0),
                FurnitureDesign(6, "Vintage Oak Cabinet", "Solid oak storage cabinet with antique bronze handles.", "Cabinets", "https://images.unsplash.com/photo-1538688549894-f447320dd824", "4.0ft x 1.5ft x 5.0ft", "Oak Wood", 22000.0, 4.0, 1.5, 5.0),
                FurnitureDesign(18, "Artisan Display Cabinet", "Wooden cabinet with glass doors for collectibles.", "Cabinets", "https://images.unsplash.com/photo-1601760561441-16420502c7e0", "3.0ft x 1.5ft x 6.0ft", "Sheesham Wood", 25000.0, 3.0, 1.5, 6.0),
                
                // MODERN WOODEN
                FurnitureDesign(20, "Organic Coffee Table", "Natural live edge wooden table for modern homes.", "Wooden Modern", "https://images.unsplash.com/photo-1533090161767-e6ffed986c88", "4.0ft x 2.0ft x 1.5ft", "Acacia Wood", 12000.0, 4.0, 2.0, 1.5),
                FurnitureDesign(9, "Teak Media Console", "Minimalist teak wood TV unit with hidden storage.", "Wooden Modern", "https://images.unsplash.com/photo-1594913785162-e678ac052429", "6.0ft x 1.5ft x 1.5ft", "Teak Wood", 18000.0, 6.0, 1.5, 1.5),
                FurnitureDesign(26, "Scandinavian Desk", "Solid wood professional desk for home office.", "Wooden Modern", "https://images.unsplash.com/photo-1518455027359-f3f8164ba6bd", "5.0ft x 2.5ft x 2.5ft", "Birch Wood", 25000.0, 5.0, 2.5, 2.5)
            )
            _furnitureDesigns.value = dummyList
        }
    }

    fun getDesignById(id: Int): FurnitureDesign? {
        return _furnitureDesigns.value?.find { it.id == id }
    }
}
