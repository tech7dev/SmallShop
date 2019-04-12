package cd.mercipro.smallshop.Models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface StockDao {
    @Query("SELECT * FROM stock WHERE stockID = :stockID LIMIT 1")
    Stock findStockById(int stockID);

    @Query("SELECT * FROM stock WHERE productID = :productID LIMIT 1")
    Stock findProductByName(String productID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Stock stock);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Stock... stocks);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Stock stock);

    @Query("DELETE FROM stock")
    void deleteAll();

    @Query("SELECT * FROM stock ORDER BY productID ASC")
    LiveData<List<Product>> getAllProduct();
}
