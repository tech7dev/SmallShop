package cd.mercipro.smallshop.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cd.mercipro.smallshop.R;

public class MyProductActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private EditText txtNom_Prod, txtPU_achat, txtPU_vente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myproduct);

        //init view
        databaseReference = FirebaseDatabase.getInstance().getReference();
        txtNom_Prod = findViewById(R.id.txtNom_Prod);
        txtPU_achat = findViewById(R.id.txtPU_achat);
        txtPU_vente = findViewById(R.id.txtPU_vente);
    }
}
