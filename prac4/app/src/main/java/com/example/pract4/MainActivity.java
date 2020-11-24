package com.example.pract4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
            implements MasterFragment.ProductSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showMasterFragment();
    }

    protected void showMasterFragment(){

        MasterFragment newFragmentToShow = new MasterFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, newFragmentToShow)
                .commit();

    }

    protected void showDetailFragmentOverMasterFragment(String selectedProductId)
    {
        DetailFragment newFragmentToShow = new DetailFragment();
        Bundle params = new Bundle();
        params.putString("productId", selectedProductId);
        newFragmentToShow.setArguments(params);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, newFragmentToShow)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onProductSelected(Product product) {
        showDetailFragmentOverMasterFragment(product.getId());
    }
}