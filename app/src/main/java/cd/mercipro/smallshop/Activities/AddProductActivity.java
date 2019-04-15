package cd.mercipro.smallshop.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cd.mercipro.smallshop.R;

public class AddProductActivity extends AppCompatActivity {
    private EditText txtNom_Prod, txtPU_achat, txtPU_vente;
    public static final String EXTRA_PRODUCTNAME = "cd.mercipro.smallshop.EXTRA_PRODUCTNAME";
    public static final String EXTRA_PA = "cd.mercipro.smallshop.EXTRA_PA";
    public static final String EXTRA_PV = "cd.mercipro.smallshop.EXTRA_PV";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //Set Title and Icon on ActionBar
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white);
        setTitle("Ajouter un Produit");

        txtNom_Prod = findViewById(R.id.txtNom_Prod);
        txtPU_achat = findViewById(R.id.txtPU_achat);
        txtPU_vente = findViewById(R.id.txtPU_vente);

    }

    private void saveProduct() {
        String productName = txtNom_Prod.getText().toString();
        String pa = txtPU_achat.getText().toString();
        String pv = txtPU_vente.getText().toString();

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

        Intent data = new Intent();
        data.putExtra(EXTRA_PRODUCTNAME, productName);
        data.putExtra(EXTRA_PA, pa);
        data.putExtra(EXTRA_PV, pv);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_product:
                saveProduct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
