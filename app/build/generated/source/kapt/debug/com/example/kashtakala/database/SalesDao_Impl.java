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
import com.example.kashtakala.models.SalesItem;
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
public final class SalesDao_Impl implements SalesDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SalesItem> __insertionAdapterOfSalesItem;

  private final EntityDeletionOrUpdateAdapter<SalesItem> __deletionAdapterOfSalesItem;

  public SalesDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSalesItem = new EntityInsertionAdapter<SalesItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `sales_records` (`id`,`itemName`,`customerName`,`saleDate`,`price`,`paymentMethod`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SalesItem entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getItemName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getItemName());
        }
        if (entity.getCustomerName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCustomerName());
        }
        if (entity.getSaleDate() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSaleDate());
        }
        statement.bindDouble(5, entity.getPrice());
        if (entity.getPaymentMethod() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPaymentMethod());
        }
      }
    };
    this.__deletionAdapterOfSalesItem = new EntityDeletionOrUpdateAdapter<SalesItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `sales_records` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SalesItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertSale(final SalesItem sale, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSalesItem.insert(sale);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteSale(final SalesItem sale, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSalesItem.handle(sale);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SalesItem>> getAllSales() {
    final String _sql = "SELECT * FROM sales_records ORDER BY saleDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"sales_records"}, new Callable<List<SalesItem>>() {
      @Override
      @NonNull
      public List<SalesItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "itemName");
          final int _cursorIndexOfCustomerName = CursorUtil.getColumnIndexOrThrow(_cursor, "customerName");
          final int _cursorIndexOfSaleDate = CursorUtil.getColumnIndexOrThrow(_cursor, "saleDate");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfPaymentMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentMethod");
          final List<SalesItem> _result = new ArrayList<SalesItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SalesItem _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpItemName;
            if (_cursor.isNull(_cursorIndexOfItemName)) {
              _tmpItemName = null;
            } else {
              _tmpItemName = _cursor.getString(_cursorIndexOfItemName);
            }
            final String _tmpCustomerName;
            if (_cursor.isNull(_cursorIndexOfCustomerName)) {
              _tmpCustomerName = null;
            } else {
              _tmpCustomerName = _cursor.getString(_cursorIndexOfCustomerName);
            }
            final String _tmpSaleDate;
            if (_cursor.isNull(_cursorIndexOfSaleDate)) {
              _tmpSaleDate = null;
            } else {
              _tmpSaleDate = _cursor.getString(_cursorIndexOfSaleDate);
            }
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpPaymentMethod;
            if (_cursor.isNull(_cursorIndexOfPaymentMethod)) {
              _tmpPaymentMethod = null;
            } else {
              _tmpPaymentMethod = _cursor.getString(_cursorIndexOfPaymentMethod);
            }
            _item = new SalesItem(_tmpId,_tmpItemName,_tmpCustomerName,_tmpSaleDate,_tmpPrice,_tmpPaymentMethod);
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
