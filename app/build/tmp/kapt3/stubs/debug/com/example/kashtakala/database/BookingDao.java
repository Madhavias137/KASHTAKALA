package com.example.kashtakala.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH\'J\u0016\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\f"}, d2 = {"Lcom/example/kashtakala/database/BookingDao;", "", "deleteBooking", "", "booking", "Lcom/example/kashtakala/models/BookingItem;", "(Lcom/example/kashtakala/models/BookingItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllBookings", "Lkotlinx/coroutines/flow/Flow;", "", "insertBooking", "updateBooking", "app_debug"})
@androidx.room.Dao()
public abstract interface BookingDao {
    
    @androidx.room.Query(value = "SELECT * FROM bookings ORDER BY bookingDate DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.kashtakala.models.BookingItem>> getAllBookings();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertBooking(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.BookingItem booking, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBooking(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.BookingItem booking, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateBooking(@org.jetbrains.annotations.NotNull()
    com.example.kashtakala.models.BookingItem booking, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}