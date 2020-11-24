package com.example.pract4;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MasterFragment extends Fragment {

    public interface ProductSelectedListener
    {
        void onProductSelected(Product product);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        ProductDataManager productDataManager = new ProductDataManager();
        Product[] products = productDataManager.getProducts();
        GridView gridViewProducts =
                view.findViewById(R.id.gridViewProducts);

        ProductGridViewAdapter productAdapter = new
                ProductGridViewAdapter(this.getContext(), products);

        gridViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product selectedProduct = products[position];

                Activity parentActivity = getActivity();
                if (parentActivity instanceof ProductSelectedListener)
                {
                    ((ProductSelectedListener)parentActivity)
                            .onProductSelected(selectedProduct);
                }

            }
        });

        gridViewProducts.setAdapter(productAdapter);


        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
