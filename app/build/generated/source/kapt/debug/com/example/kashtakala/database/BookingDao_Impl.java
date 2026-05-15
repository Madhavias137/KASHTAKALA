package com.example.kashtakala.database;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.kashtakala.models.BookingItem;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BookingDao_Impl implements BookingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BookingItem> __insertionAdapterOfBookingItem;

  private final EntityDeletionOrUpdateAdapter<BookingItem> __deletionAdapterOfBookingItem;

  private final EntityDeletionOrUpdateAdapter<BookingItem> __updateAdapterOfBookingItem;

  public BookingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBookingItem = new EntityInsertionAdapter<BookingItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `bookings` (`id`,`customerName`,`furnitureType`,`bookingDate`,`estimatedDelivery`,`amount`,`status`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookingItem entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCustomerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCustomerName());
        }
        if (entity.getFurnitureType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFurnitureType());
        }
        if (entity.getBookingDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBookingDate());
        }
        if (entity.getEstimatedDelivery() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEstimatedDelivery());
        }
        statement.bindDouble(6, entity.getAmount());
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
      }
    };
    this.__deletionAdapterOfBookingItem = new EntityDeletionOrUpdateAdapter<BookingItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `bookings` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookingItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfBookingItem = new EntityDeletionOrUpdateAdapter<BookingItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `bookings` SET `id` = ?,`customerName` = ?,`furnitureType` = ?,`bookingDate` = ?,`estimatedDelivery` = ?,`amount` = ?,`status` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BookingItem entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCustomerName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCustomerName());
        }
        if (entity.getFurnitureType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getFurnitureType());
        }
        if (entity.getBookingDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getBookingDate());
        }
        if (entity.getEstimatedDelivery() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getEstimatedDelivery());
        }
        statement.bindDouble(6, entity.getAmount());
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
        statement.bindLong(8, entity.getId());
      }
    };
  }

  @Override
  public Object insertBooking(final BookingItem booking,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBookingItem.insert(booking);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBooking(final BookingItem booking,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBookingItem.handle(booking);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateBooking(final BookingItem booking,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfBookingItem.handle(booking);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<BookingItem>> getAllBookings() {
    final String _sql = "SELECT * FROM bookings ORDER BY bookingDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"bookings"}, new Callable<List<BookingItem>>() {
      @Override
      @NonNull
      public List<BookingItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCustomerName = CursorUtil.getColumnIndexOrThrow(_cursor, "customerName");
          final int _cursorIndexOfFurnitureType = CursorUtil.getColumnIndexOrThrow(_cursor, "furnitureType");
          final int _cursorIndexOfBookingDate = CursorUtil.getColumnIndexOrThrow(_cursor, "bookingDate");
          final int _cursorIndexOfEstimatedDelivery = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedDelivery");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<BookingItem> _result = new ArrayList<BookingItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BookingItem _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpCustomerName;
            if (_cursor.isNull(_cursorIndexOfCustomerName)) {
              _tmpCustomerName = null;
            } else {
              _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            }
            final String _tmpFurnitureType;
            if (_cursor.isNull(_cursorIndexOfFurnitureType)) {
              _tmpFurnitureType = null;
            } else {
              _tmpFurnitureType = _cursor.getString(_cursorIndexOfFurnitureType);
            }
            final String _tmpBookingDate;
            if (_cursor.isNull(_cursorIndexOfBookingDate)) {
              _tmpBookingDate = null;
            } else {
              _tmpBookingDate = _cursor.getString(_cursorIndexOfBookingDate);
            }
            final String _tmpEstimatedDelivery;
            if (_cursor.isNull(_cursorIndexOfEstimatedDelivery)) {
              _tmpEstimatedDelivery = null;
            } else {
              _tmpEstimatedDelivery = _cursor.getString(_cursorIndexOfEstimatedDelivery);
            }
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new BookingItem(_tmpId,_tmpCustomerName,_tmpFurnitureType,_tmpBookingDate,_tmpEstimatedDelivery,_tmpAmount,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
