package cd.mercipro.smallshop.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import cd.mercipro.smallshop.R;

public class SaleProductActivity extends AppCompatActivity {

    public static final int ADD_Sale_Request = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale_product);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Vendre un Produit");

        //add new product
//        FloatingActionButton btnAddProduct = findViewById(R.id.btnSaveSaleProduct);
//        btnAddProduct.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                Intent intent = new Intent(SaleProductActivity.this, SalesViewActivity.class);
//                startActivityForResult(intent,ADD_Sale_Request);
//            }
//        });

    }
}
