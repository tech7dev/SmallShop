package cd.mercipro.smallshop.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import cd.mercipro.smallshop.R;
import cd.mercipro.smallshop.ViewModel.ProductViewModel;

public class SalesViewActivity extends AppCompatActivity {
    //private SaleViewModel saleViewModel;
    public static final int ADD_Sale_Request = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.salesView);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Dernières Ventes");

        //add new product
        FloatingActionButton btnAddProduct = findViewById(R.id.btnSaleProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SalesViewActivity.this, SaleProductActivity.class);
                startActivityForResult(intent,ADD_Sale_Request);
            }
        });

        //Init RecyclerView
        RecyclerView rcvProduct = findViewById(R.id.rcvSalesView);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setHasFixedSize(true);
    }
}
