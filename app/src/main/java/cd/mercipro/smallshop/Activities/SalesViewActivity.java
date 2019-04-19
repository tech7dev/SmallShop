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
import android.view.View;
import android.widget.Toast;
//import java.util.Date;
import java.util.List;
import cd.mercipro.smallshop.Adapters.SaleAdapter;
import cd.mercipro.smallshop.Models.Sale;
import cd.mercipro.smallshop.R;
import cd.mercipro.smallshop.ViewModel.SaleViewModel;

public class SalesViewActivity extends AppCompatActivity {
    private SaleViewModel saleViewModel;
    public static final int ADD_Sale_Request = 1;
    public static final int Edit_Sale_Request = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sales_view);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrow_back_white);
        setTitle("Dernières Ventes");

        //add new product
        FloatingActionButton btnSaleProduct = findViewById(R.id.btnSaleProduct);
        btnSaleProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SalesViewActivity.this, SaleProductActivity.class);
                startActivityForResult(intent,ADD_Sale_Request);
            }
        });

        //Init RecyclerView
        RecyclerView rcvSalesView = findViewById(R.id.rcvSalesView);
        rcvSalesView.setLayoutManager(new LinearLayoutManager(this));
        rcvSalesView.setHasFixedSize(true);

        final SaleAdapter adapter = new SaleAdapter();
        rcvSalesView.setAdapter(adapter);

        //ce code observe toutes modifications sur les données et met à jour le RecyclerView
        saleViewModel = ViewModelProviders.of(this).get(SaleViewModel.class);
        saleViewModel.getAllsales().observe(this, new Observer<List<Sale>>(){
            @Override
            public  void onChanged(@Nullable List<Sale> sales){
                //update RecyclerView
                adapter.setSales(sales);
            }
        });

        //Swipe left or right to delete data item into recyclerview
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            //swipe item to delete
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                saleViewModel.delete(adapter.getSaleAt(viewHolder.getAdapterPosition()));
                Toast.makeText(SalesViewActivity.this,"Vente supprimé",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(rcvSalesView);

        //clickListener to edit item
        adapter.setOnItemClickListener(new SaleAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Sale sale) {
                Intent intent = new Intent(SalesViewActivity.this, SaleProductActivity.class);
                intent.putExtra(SaleProductActivity.EXTRA_ProductID, String.valueOf(sale.getProductID()));
                intent.putExtra(SaleProductActivity.EXTRA_PT, String.valueOf(sale.getPT()));
                intent.putExtra(SaleProductActivity.EXTRA_Quantity, String.valueOf(sale.getQuantity()));
                startActivityForResult(intent,Edit_Sale_Request);
            }
        });
    }

    //Nous récupérons les données venant de l'activity AddEditProductActivity afin de le sauvegarder dans la base des données
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //add product
        if (requestCode == ADD_Sale_Request && resultCode == RESULT_OK){
            int productID = Integer.parseInt(data.getStringExtra(SaleProductActivity.EXTRA_ProductID));
            double pt = Double.parseDouble(data.getStringExtra(SaleProductActivity.EXTRA_PT));
            //Date date = new Date();
            double quantity = Double.parseDouble(data.getStringExtra(SaleProductActivity.EXTRA_Quantity));

            Sale sale = new Sale(productID ,pt ,quantity ,"2019-4-19");
            saleViewModel.insert(sale);
            Toast.makeText(this,"La vente a été enregistré", Toast.LENGTH_SHORT).show();
        }
    }
}
