package cd.mercipro.smallshop.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "product")
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int productID;

    @ColumnInfo(name = "productName")
    @NonNull
    public String productName;

    @NonNull
    public double pu_achat;

    @NonNull
    public double pu_vente;

    @Ignore
    public Product(){}

    public Product(@NonNull String productName, double pu_achat, double pu_vente) {
        this.productName = productName;
        this.pu_achat = pu_achat;
        this.pu_vente = pu_vente;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    @NonNull
    public String getProductName() {
        return productName;
    }

    public void setProductName(@NonNull String productName) {
        this.productName = productName;
    }

    public double getPu_achat() {
        return pu_achat;
    }

    public void setPu_achat(double pu_achat) {
        this.pu_achat = pu_achat;
    }

    public double getPu_vente() {
        return pu_vente;
    }

    public void setPu_vente(double pu_vente) {
        this.pu_vente = pu_vente;
    }
}
