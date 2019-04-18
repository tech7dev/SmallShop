package cd.mercipro.smallshop.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cd.mercipro.smallshop.Activities.MainActivity;
import cd.mercipro.smallshop.Activities.MyProductActivity;
import cd.mercipro.smallshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{


    private CardView buyProduct, stockAdd, stockView, expenseView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //defining Cards
        buyProduct = view.findViewById(R.id.buyProduct);
        stockAdd = view.findViewById(R.id.stockAdd);
        stockView = view.findViewById(R.id.stockView);
        expenseView = view.findViewById(R.id.expenseView);

        //register Click listener to the cards
        buyProduct.setOnClickListener(this);
        stockAdd.setOnClickListener(this);
        stockView.setOnClickListener(this);
        expenseView.setOnClickListener(this);

        return  view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buyProduct :
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new BuyProductFragment())
                        .addToBackStack(null).commit();
                break;
            case R.id.stockAdd :

                break;
            case R.id.stockView :
                Intent myProduct = new Intent(getActivity(), MyProductActivity.class);
                startActivity(myProduct);
                break;
            case R.id.expenseView :
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new ExpenseViewFragment())
                        .addToBackStack(null).commit();
                break;
            default:break;
        }
    }
}
