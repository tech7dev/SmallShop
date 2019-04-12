package cd.mercipro.smallshop.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;

@Entity(tableName = "stock",
        foreignKeys = @ForeignKey(entity = Product.class,
            parentColumns = "productID",
            childColumns = "productID",
            onDelete = ForeignKey.CASCADE),
            indices = {@Index("productID")})

public class Stock {

    @PrimaryKey(autoGenerate = true)
    public int stockID;

    @NonNull
    public double quantity;

    @NonNull
    public Date dateEntry;

    @NonNull
    public int productID;

    public Stock(int stockID, double quantity, @NonNull Date dateEntry) {
        this.stockID = stockID;
        this.quantity = quantity;
        this.dateEntry = dateEntry;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @NonNull
    public Date getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(@NonNull Date dateEntry) {
        this.dateEntry = dateEntry;
    }
}
