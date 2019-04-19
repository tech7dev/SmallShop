package cd.mercipro.smallshop.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cd.mercipro.smallshop.R;

public class SaleProductActivity extends AppCompatActivity {
    public static final int ADD_Sale_Request = 1;
    private EditText txtProductname, txtPT, txtPU, txtQuantity;
    public static final String EXTRA_ProductID = "cd.mercipro.smallshop.EXTRA_ProductID";
    public static final String EXTRA_PT = "cd.mercipro.smallshop.EXTRA_PA"; //Prix total
    public static final String EXTRA_Quantity = "cd.mercipro.smallshop.EXTRA_Quantity"; //Quantity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale_product);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Vendre un Produit");

        //add new product
        FloatingActionButton btnSaveSaleProduct = findViewById(R.id.btnSaveSaleProduct);
        btnSaveSaleProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SaleProductActivity.this, SalesViewActivity.class);
                startActivityForResult(intent,ADD_Sale_Request);
            }
        });

    }
}
