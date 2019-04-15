package cd.mercipro.smallshop.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import cd.mercipro.smallshop.Adapters.ProductAdapter;
import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.R;
import cd.mercipro.smallshop.ViewModel.ProductViewModel;

public class MyProductActivity extends AppCompatActivity {
    private ProductViewModel productViewModel;
    public static final int ADD_PRODUCT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_product);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Mes Produits");

        FloatingActionButton btnAddProduct = findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MyProductActivity.this,AddProductActivity.class);
                startActivityForResult(intent,ADD_PRODUCT_REQUEST);
            }
        });

        //Initialisation du RecyclerView
        RecyclerView rcvProduct = findViewById(R.id.rcvProduct);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setHasFixedSize(true);

        final ProductAdapter adapter = new ProductAdapter();
        rcvProduct.setAdapter(adapter);

        //ce code observe toutes modifications sur les donnees et met à jour le RecyclerView
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getAllproducts().observe(this, new Observer<List<Product>>(){
            @Override
            public  void onChanged(@Nullable List<Product> products){
                //update RecyclerView
                adapter.setProducts(products);
            }
        });
    }

    //Nous recuperons les données venant de l'activity AddProductActivity afin de le sauvegarder dans la base des données
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK){
            String productName = data.getStringExtra(AddProductActivity.EXTRA_PRODUCTNAME);
            double pa = data.getDoubleExtra(AddProductActivity.EXTRA_PA,0.0);
            double pv = data.getDoubleExtra(AddProductActivity.EXTRA_PV,0.0);

            Product product = new Product(productName,pa,pv);
            productViewModel.insert(product);
            Toast.makeText(this,"Le produit a été sauvé", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Produit n'est pas enregistré", Toast.LENGTH_SHORT).show();
        }
    }
}
