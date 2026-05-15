package com.example.kashtakala.repositories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020 2\u0006\u0010\'\u001a\u00020(H\u0086@\u00a2\u0006\u0002\u0010)J\u0016\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010,J\u0016\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010/J\u0016\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u00102J\u0016\u00103\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u00104\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010,J\u0016\u00105\u001a\u00020 2\u0006\u0010.\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010/J\u0016\u00106\u001a\u00020 2\u0006\u00101\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u00102J\u0016\u00107\u001a\u0002082\u0006\u0010\'\u001a\u00020(H\u0086@\u00a2\u0006\u0002\u0010)J\u0016\u00109\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010\"J\u0016\u0010:\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010%R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lcom/example/kashtakala/repositories/MainRepository;", "", "quoteDao", "Lcom/example/kashtakala/database/QuoteDao;", "favoriteDao", "Lcom/example/kashtakala/database/FavoriteDao;", "portfolioDao", "Lcom/example/kashtakala/database/PortfolioDao;", "bookingDao", "Lcom/example/kashtakala/database/BookingDao;", "salesDao", "Lcom/example/kashtakala/database/SalesDao;", "(Lcom/example/kashtakala/database/QuoteDao;Lcom/example/kashtakala/database/FavoriteDao;Lcom/example/kashtakala/database/PortfolioDao;Lcom/example/kashtakala/database/BookingDao;Lcom/example/kashtakala/database/SalesDao;)V", "allBookings", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/kashtakala/models/BookingItem;", "getAllBookings", "()Lkotlinx/coroutines/flow/Flow;", "allFavorites", "Lcom/example/kashtakala/models/FavoriteDesign;", "getAllFavorites", "allPortfolioItems", "Lcom/example/kashtakala/models/PortfolioItem;", "getAllPortfolioItems", "allQuotes", "Lcom/example/kashtakala/database/QuoteEntity;", "getAllQuotes", "allSales", "Lcom/example/kashtakala/models/SalesItem;", "getAllSales", "addFavorite", "", "favorite", "(Lcom/example/kashtakala/models/FavoriteDesign;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBooking", "booking", "(Lcom/example/kashtakala/models/BookingItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteFavoriteByDesignId", "designId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePortfolioItem", "item", "(Lcom/example/kashtakala/models/PortfolioItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteQuote", "quote", "(Lcom/example/kashtakala/database/QuoteEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteSale", "sale", "(Lcom/example/kashtakala/models/SalesItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertBooking", "insertPortfolioItem", "insertQuote", "insertSale", "isFavorite", "", "removeFavorite", "updateBooking", "app_debug"})
public final class MainRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.kashtakala.database.QuoteDao quoteDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.kashtakala.database.FavoriteDao favoriteDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.kashtakala.database.PortfolioDao portfolioDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.kashtakala.database.BookingDao bookingDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.kashtakala.database.SalesDao salesDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.database.QuoteEntity>> allQuotes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.FavoriteDesign>> allFavorites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.PortfolioItem>> allPortfolioItems = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.BookingItem>> allBookings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.SalesItem>> allSales = null;
    
    public MainRepository(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.database.QuoteDao quoteDao, @org.jetbrains.annotations.NotNull()
    com.example.kashtakala.database.FavoriteDao favoriteDao, @org.jetbrains.annotations.NotNull()
    com.example.kashtakala.database.PortfolioDao portfolioDao, @org.jetbrains.annotations.NotNull()
    com.example.kashtakala.database.BookingDao bookingDao, @org.jetbrains.annotations.NotNull()
    com.example.kashtakala.database.SalesDao salesDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.database.QuoteEntity>> getAllQuotes() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertQuote(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.database.QuoteEntity quote, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteQuote(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.database.QuoteEntity quote, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.FavoriteDesign>> getAllFavorites() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addFavorite(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.FavoriteDesign favorite, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeFavorite(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.FavoriteDesign favorite, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isFavorite(int designId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteFavoriteByDesignId(int designId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.PortfolioItem>> getAllPortfolioItems() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertPortfolioItem(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.PortfolioItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deletePortfolioItem(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.PortfolioItem item, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.BookingItem>> getAllBookings() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertBooking(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.BookingItem booking, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateBooking(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.BookingItem booking, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteBooking(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.BookingItem booking, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.SalesItem>> getAllSales() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertSale(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.SalesItem sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteSale(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.SalesItem sale, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}