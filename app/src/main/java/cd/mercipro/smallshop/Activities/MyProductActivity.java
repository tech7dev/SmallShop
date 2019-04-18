package cd.mercipro.smallshop.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    public static final int EDIT_PRODUCT_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_product);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Mes Produits");

        //add new product
        FloatingActionButton btnAddProduct = findViewById(R.id.btnAddProduct);
        btnAddProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MyProductActivity.this, AddEditProductActivity.class);
                startActivityForResult(intent,ADD_PRODUCT_REQUEST);
            }
        });

        //Init RecyclerView
        RecyclerView rcvProduct = findViewById(R.id.rcvProduct);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
        rcvProduct.setHasFixedSize(true);

        final ProductAdapter adapter = new ProductAdapter();
        rcvProduct.setAdapter(adapter);

        //ce code observe toutes modifications sur les données et met à jour le RecyclerView
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getAllproducts().observe(this, new Observer<List<Product>>(){
            @Override
            public  void onChanged(@Nullable List<Product> products){
                //update RecyclerView
                adapter.setProducts(products);
            }
        });

        //Swipe left or right to delete data item into recyclerview
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            //swipe item to delete
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                productViewModel.delete(adapter.getProductAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MyProductActivity.this,"Produit supprimé",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rcvProduct);

        //clickListener to edit item
        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(MyProductActivity.this, AddEditProductActivity.class);
                intent.putExtra(AddEditProductActivity.EXTRA_ProductID, String.valueOf(product.getProductID()));
                intent.putExtra(AddEditProductActivity.EXTRA_ProductName, product.getProductName());
                intent.putExtra(AddEditProductActivity.EXTRA_PA, String.valueOf(product.getPu_achat()));
                intent.putExtra(AddEditProductActivity.EXTRA_PV, String.valueOf(product.getPu_vente()));
                intent.putExtra(AddEditProductActivity.EXTRA_Quantity, String.valueOf(product.getQuantity()));
                startActivityForResult(intent,EDIT_PRODUCT_REQUEST);
            }
        });
    }

    //Nous récupérons les données venant de l'activity AddEditProductActivity afin de le sauvegarder dans la base des données
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //add product
        if (requestCode == ADD_PRODUCT_REQUEST && resultCode == RESULT_OK){
            String productName = data.getStringExtra(AddEditProductActivity.EXTRA_ProductName);
            double pa = Double.parseDouble(data.getStringExtra(AddEditProductActivity.EXTRA_PA));
            double pv = Double.parseDouble(data.getStringExtra(AddEditProductActivity.EXTRA_PV));
            double quantity = Double.parseDouble(data.getStringExtra(AddEditProductActivity.EXTRA_Quantity));

            Product product = new Product(productName,pa,pv,quantity);
            productViewModel.insert(product);
            Toast.makeText(this,"Le produit a été sauvé", Toast.LENGTH_SHORT).show();
        }

        //edit product
        else if (requestCode == EDIT_PRODUCT_REQUEST && resultCode == RESULT_OK){
            String id = data.getStringExtra(AddEditProductActivity.EXTRA_ProductID);
            if(id == "-1"){
                Toast.makeText(this,"Produit n'a pas été mis à jour",Toast.LENGTH_SHORT).show();
                return;
            }

            String productName = data.getStringExtra(AddEditProductActivity.EXTRA_ProductName);
            double pa = Double.parseDouble(data.getStringExtra(AddEditProductActivity.EXTRA_PA));
            double pv = Double.parseDouble(data.getStringExtra(AddEditProductActivity.EXTRA_PV));
            double quantity = Double.parseDouble(data.getStringExtra(AddEditProductActivity.EXTRA_Quantity));

            Product product = new Product(productName,pa,pv,quantity);
            product.setProductID(Integer.parseInt(id));
            productViewModel.update(product);
            Toast.makeText(this,"Produit a été mis à jour",Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(this,"Produit n'est pas enregistré", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_deleteall, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_allproducts:
                productViewModel.deleteAll();
                Toast.makeText(this,"Tous les produits ont été supprimés",Toast.LENGTH_SHORT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
