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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cd.mercipro.smallshop.Adapters.ProductAdapter;
import cd.mercipro.smallshop.Adapters.SelectProductAdapter;
import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.R;
import cd.mercipro.smallshop.ViewModel.ProductViewModel;

public class SaleProductActivity extends AppCompatActivity {
    private ProductViewModel productViewModel;
    private String productID;
    public static final int ADD_Sale_Request = 1;
    private EditText txtQuantitySale;
    private TextView txtProductNameSale, txtViewTotalPrice, txtViewPU, txtQuanityStock;
    private Button btnSelectProduct;
    public static final String EXTRA_ProductID = "cd.mercipro.smallshop.EXTRA_ProductID";
    public static final String EXTRA_PU = "cd.mercipro.smallshop.EXTRA_PU";
    public static final String EXTRA_PT = "cd.mercipro.smallshop.EXTRA_PT";
    public static final String EXTRA_ProductName = "cd.mercipro.smallshop.EXTRA_ProductName";
    public static final String EXTRA_QuantitySale = "cd.mercipro.smallshop.EXTRA_QuantitySale";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale_product);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Vendre un Produit");

        //init textview
        txtProductNameSale = findViewById(R.id.txtProductNameSale);
        txtViewPU = findViewById(R.id.txtViewPU);
        txtQuanityStock =  findViewById(R.id.txtQuanityStock);
        txtViewTotalPrice = findViewById(R.id.txtViewTotalPrice);

        //init editText
        txtQuantitySale = findViewById(R.id.txtQuantitySale);
        txtQuantitySale.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            //Lorsque l'utilisateur termine à editer la valeur de la quantité, nous calculons le prix total
            @Override
            public void afterTextChanged(Editable s) {
                if(String.valueOf(s)!= null){
                    try {
                        String q = txtQuantitySale.getText().toString().trim();
                        double qty = Double.parseDouble(String.valueOf(q));
                        double pu = Double.parseDouble(txtViewPU.getText().toString());
                        double pt = pu * qty;
                        txtViewTotalPrice.setText(String.valueOf(pt));
                        Log.d("Prix total",String.valueOf(pt));
                    }
                    catch(Exception e){}
                }
            }
        });

        //ajouter une nouvelle vente
        FloatingActionButton btnSaveSaleProduct = findViewById(R.id.btnSaveSaleProduct);
        btnSaveSaleProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveSaleProduct();
            }
        });

        //button Select Product via AlertDialog
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

                //clickListener to select product
                adapter.setOnItemClickListener(new SelectProductAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Product product) {
                        productID =  String.valueOf(product.getProductID());
                        txtProductNameSale.setText(String.valueOf(product.getProductName()));
                        txtViewPU.setText(String.valueOf(product.getPu_vente()));
                        txtQuanityStock.setText(String.valueOf(product.getQuantity()));
                    }
                });
                mBuilder.setView(alertView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });
    }

    private void saveSaleProduct() {
        String q = txtQuantitySale.getText().toString();
        if(q.trim().isEmpty()){
            Toast.makeText(this,"Veuillez entrer la quantité à vendre", Toast.LENGTH_SHORT).show();
            return;
        }
        if(productID!=null){
            Intent data = new Intent();
            data.putExtra(EXTRA_ProductID, productID);
            data.putExtra(EXTRA_ProductName, txtProductNameSale.getText().toString());
            data.putExtra(EXTRA_PU, txtViewPU.getText().toString().toString());
            data.putExtra(EXTRA_QuantitySale, txtQuantitySale.getText().toString());
            data.putExtra(EXTRA_PT, txtViewTotalPrice.getText().toString());

            setResult(RESULT_OK, data);
            finish();
        }

    }
}
