package com.example.pract4;

import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle params = this.getArguments();
        String selectedProductId = params.getString("productId");

        ProductDataManager productDataManager =
                new ProductDataManager();
        Product product =
                productDataManager.getProductById(selectedProductId);

        if(product != null) {
            ImageView imageViewDetails = view.findViewById(R.id.imageViewDetail);
            imageViewDetails.setImageResource(product.getImageResource());
            TextView textViewTitle = view.findViewById(R.id.textViewTitleDetail);
            textViewTitle.setText(product.getTitle());

            TextView textViewCategory = view.findViewById(R.id.textViewCategory);
            textViewCategory.setText(product.getCategory());

            TextView textViewOriginalPrice = view.findViewById(R.id.textViewOriginalPriceDetail);
            String strikedThroughText = "<strike>$" + product.getOriginalPrice().setScale(2).toString() + "</strike>";
            textViewOriginalPrice.setText(Html.fromHtml(strikedThroughText));

            TextView textViewDiscountedPrice = view.findViewById(R.id.textViewDiscountedPriceDetail);
            textViewDiscountedPrice.setText("$" + product.getDiscountedPrice().setScale(2).toString());

            TextView textViewDescription = view.findViewById(R.id.textViewDescription);
            textViewDescription.setText(product.getDescription());
        }

        return view;

    }

}
