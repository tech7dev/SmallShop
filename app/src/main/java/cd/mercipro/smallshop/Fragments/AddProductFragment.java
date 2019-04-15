package cd.mercipro.smallshop.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cd.mercipro.smallshop.Activities.MainActivity;
import cd.mercipro.smallshop.Models.Product;
import cd.mercipro.smallshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {

    private EditText txtNom_Prod, txtPU_achat, txtPU_vente;
    private Button btnSaveProduct;
    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);
        txtNom_Prod = view.findViewById(R.id.txtNom_Prod);
        txtPU_achat = view.findViewById(R.id.txtNom_Prod);
        txtPU_vente = view.findViewById(R.id.txtPU_vente);
        btnSaveProduct = view.findViewById(R.id.btnSaveProduct);

        btnSaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = txtNom_Prod.getText().toString();
                double pu_achat = Double.parseDouble(txtPU_achat.getText().toString());
                double pu_vente = Double.parseDouble(txtPU_vente.getText().toString());

                Toast.makeText(getActivity(),"Produit "+txtNom_Prod.getText()+" est ajouté avec succès",Toast.LENGTH_SHORT).show();

                txtNom_Prod.setText("");
                txtPU_achat.setText("");
                txtPU_vente.setText("");
            }
        });

        return view;
    }

}
