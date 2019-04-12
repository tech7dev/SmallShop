package cd.mercipro.smallshop.Models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product WHERE productID = :id LIMIT 1")
    Product findDirectorById(int id);

    @Query("SELECT * FROM product WHERE productName = :productName LIMIT 1")
    Product findProductByName(String productName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Product product);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Product... products);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void update(Product product);

    @Query("DELETE FROM product")
    void delete(Product product);

    @Query("DELETE FROM product")
    void deleteAllProducts();

    @Query("SELECT * FROM product ORDER BY productName ASC")
    LiveData<List<Product>> getAllProduct();
}
