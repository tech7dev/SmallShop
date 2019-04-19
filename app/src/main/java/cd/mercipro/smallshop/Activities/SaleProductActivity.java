package cd.mercipro.smallshop.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import cd.mercipro.smallshop.Adapters.ProductAdapter;
import cd.mercipro.smallshop.Adapters.SelectProductAdapter;
import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.R;
import cd.mercipro.smallshop.ViewModel.ProductViewModel;

public class SaleProductActivity extends AppCompatActivity {
    private ProductViewModel productViewModel;
    public static final int ADD_Sale_Request = 1;
    private EditText txtProductname, txtPT, txtPU, txtQuantity;
    private Button btnSelectProduct;
    public static final String EXTRA_ProductID = "cd.mercipro.smallshop.EXTRA_ProductID";
    public static final String EXTRA_PT = "cd.mercipro.smallshop.EXTRA_PT"; //Prix total
    public static final String EXTRA_Quantity = "cd.mercipro.smallshop.EXTRA_Quantity"; //Quantity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale_product);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Vendre un Produit");

        //ajouter une nouvelle vente
        FloatingActionButton btnSaveSaleProduct = findViewById(R.id.btnSaveSaleProduct);
        btnSaveSaleProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SaleProductActivity.this, SalesViewActivity.class);
                startActivityForResult(intent,ADD_Sale_Request);
            }
        });

        //boutton Select Product via AlertDialog
        btnSelectProduct = findViewById(R.id.btnSelectProduct);
        btnSelectProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(SaleProductActivity.this);
                View alertView = getLayoutInflater().inflate(R.layout.select_product,null);

                //Init RecyclerView
                RecyclerView rcvSelectInListProduct = alertView.findViewById(R.id.rcvSelectInListProduct);
                rcvSelectInListProduct.setLayoutManager(new LinearLayoutManager(SaleProductActivity.this));
                rcvSelectInListProduct.setHasFixedSize(true);

                final SelectProductAdapter adapter = new SelectProductAdapter();
                rcvSelectInListProduct.setAdapter(adapter);

                //ce code observe toutes modifications sur les données et met à jour le RecyclerView
                productViewModel = ViewModelProviders.of(SaleProductActivity.this).get(ProductViewModel.class);
                productViewModel.getAllproducts().observe(SaleProductActivity.this, new Observer<List<Product>>(){
                    @Override
                    public  void onChanged(@Nullable List<Product> products){
                        //update RecyclerView
                        adapter.setProducts(products);
                    }
                });

                //clickListener to edit item
                adapter.setOnItemClickListener(new SelectProductAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Product product) {
                        int productID =  Integer.valueOf(product.getProductID());
                        Toast.makeText(SaleProductActivity.this,"Id du Produit"+productID,Toast.LENGTH_SHORT);
                    }
                });
                mBuilder.setView(alertView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });

    }
}
