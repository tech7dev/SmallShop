package cd.mercipro.smallshop.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "Product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int productID;

    //@ColumnInfo(name = "productName")
    @NonNull
    public String productName;

    public double pu_achat;

    @NonNull
    public double pu_vente;

    @NonNull
    public double quantity;

    @Ignore
    public Product() {
    }

    public Product(@NonNull String productName, double pu_achat, double pu_vente, double quantity) {
        this.productName = productName;
        this.pu_achat = pu_achat;
        this.pu_vente = pu_vente;
        this.quantity = quantity;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPu_achat() {
        return pu_achat;
    }

    public double getPu_vente() {
        return pu_vente;
    }

    public double getQuantity() {
        return quantity;
    }

}
