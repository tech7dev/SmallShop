package cd.mercipro.smallshop.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cd.mercipro.smallshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StockAddFragment extends Fragment {


    public StockAddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stock_add, container, false);
    }

}
