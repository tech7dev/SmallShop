package cd.mercipro.smallshop.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cd.mercipro.smallshop.R;

public class AddEditProductActivity extends AppCompatActivity {
    private EditText txtProductname, txtPU_achat, txtPU_vente, txtQuantity;
    public static final String EXTRA_ProductID = "cd.mercipro.smallshop.EXTRA_PRODUCTID";
    public static final String EXTRA_ProductName = "cd.mercipro.smallshop.EXTRA_PRODUCTNAME";
    public static final String EXTRA_PA = "cd.mercipro.smallshop.EXTRA_PA"; //Prix d'Achat
    public static final String EXTRA_PV = "cd.mercipro.smallshop.EXTRA_PV"; //Prix de Vente
    public static final String EXTRA_Quantity = "cd.mercipro.smallshop.EXTRA_Quantity"; //Quantity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_product);

        //Init view
        txtProductname = findViewById(R.id.txtProductname);
        txtPU_achat = findViewById(R.id.txtPU_achat);
        txtPU_vente = findViewById(R.id.txtPU_vente);
        txtQuantity = findViewById(R.id.txtQuantity);

        //retrieve title "Ajouter un produit" or "Edit un produit"
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ProductID)){
            setTitle("Editer un Produit");
            txtProductname.setText(intent.getStringExtra(EXTRA_ProductName));
            txtPU_achat.setText(intent.getStringExtra(EXTRA_PA));
            txtPU_vente.setText(intent.getStringExtra(EXTRA_PV));
            txtQuantity.setText(intent.getStringExtra(EXTRA_Quantity));
        }
        else
            setTitle("Ajouter un Produit");

        FloatingActionButton btnSaveProduct = findViewById(R.id.btnSaveProduct);
        btnSaveProduct.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                saveProduct();
            }
        });

    }

    private void saveProduct() {
        String productName = txtProductname.getText().toString();
        String pa = txtPU_achat.getText().toString();
        String pv = txtPU_vente.getText().toString();
        String qty = txtQuantity.getText().toString();

        if(productName.trim().isEmpty()){
            Toast.makeText(this,"Veuillez entrer le Nom du produit", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pa.trim().isEmpty()){
            Toast.makeText(this,"Veuillez entrer le Prix d'achat", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(pv.trim().isEmpty()){
            Toast.makeText(this,"Veuillez entrer le Prix de vente", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(qty.trim().isEmpty()){
            Toast.makeText(this,"Veuillez entrer Quantite", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_ProductName, productName);
        data.putExtra(EXTRA_PV, pv);
        data.putExtra(EXTRA_PA, pa);
        data.putExtra(EXTRA_Quantity, qty);

        //send id for edit
        String id = getIntent().getStringExtra(EXTRA_ProductID);
        if(id != "-1"){
            data.putExtra(EXTRA_ProductID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
