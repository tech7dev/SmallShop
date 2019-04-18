package cd.mercipro.smallshop.Models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;
//import java.sql.Date;


@Entity(tableName = "Sale", foreignKeys = @ForeignKey(entity = Product.class,
        parentColumns = "productID",
        childColumns = "productID",
        onDelete = CASCADE))
public class Sale {

    @PrimaryKey(autoGenerate = true)
    public int saleID;

    @NonNull
    public String productID;

    @NonNull
    public double PT;

    @NonNull
    public double quantity;

    @NonNull
    public Date dateSale;

    public Sale(@NonNull String productID, double PT, double quantity, @NonNull Date dateSale) {
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
    public String getProductID() {
        return productID;
    }

    public double getPT() {
        return PT;
    }

    public double getQuantity() {
        return quantity;
    }

    @NonNull
    public Date getDateSale() {
        return dateSale;
    }
}
