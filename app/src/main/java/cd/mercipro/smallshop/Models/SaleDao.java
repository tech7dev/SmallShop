package cd.mercipro.smallshop.Models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SaleDao {

    @Query("SELECT * FROM Sale WHERE productID = :productID LIMIT 1")
    Sale findSaleByProductID(String productID);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sale sale);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sale... sales);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Sale sale);

    @Delete
    void delete(Sale sale);

    @Query("DELETE FROM Sale")
    void deleteAllSales();

    @Query("SELECT * FROM Sale ORDER BY DateSale ASC")
    LiveData<List<Sale>> getAllSales();
}
