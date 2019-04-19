package cd.mercipro.smallshop.Models;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;
//import java.util.Date;
import static android.arch.persistence.room.ForeignKey.CASCADE;
import java.sql.Date;
import java.util.List;


@Entity(tableName = "Sale", foreignKeys = @ForeignKey(entity = Product.class,
        parentColumns = "productID",
        childColumns = "productID",
        onDelete = CASCADE))
public class Sale {

    @PrimaryKey(autoGenerate = true)
    public int saleID;

    @NonNull
    public int productID;

    @NonNull
    public double PT;

    @NonNull
    public double quantity;

    @NonNull
    public String dateSale;

    public Sale(@NonNull int productID, double PT, double quantity, @NonNull String dateSale) {
        this.productID = productID;
        this.PT = PT;
        this.quantity = quantity;
        this.dateSale = dateSale;
    }

    public void setSaleID(int saleID) {
        this.saleID = saleID;
    }

    public int getSaleID() {
        return saleID;
    }

    @NonNull
    public int getProductID() {
        return productID;
    }

    public double getPT() {
        return PT;
    }

    public double getQuantity() {
        return quantity;
    }

    @NonNull
    public String getDateSale() {
        return dateSale;
    }

//    @Query("SELECT * FROM Sale WHERE dateSale BETWEEN date(:from) AND date(:to)")
//    LiveData<List<Sale>> fetchSalesBetweenDate(String from, String to);

//    @Query("select * from Sale where dateSale=Date(:date)")
//    LiveData<List<Sale>> fetchSaleByDate(String date);
}
