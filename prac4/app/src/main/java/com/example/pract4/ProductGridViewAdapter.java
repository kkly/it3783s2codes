package com.example.pract4;

import android.app.Activity;
import android.content.Context;
import android.media.ImageReader;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProductGridViewAdapter extends ArrayAdapter {

    private Product[] products;

    public ProductGridViewAdapter(Context context, Product[] products){
        super(context, 0, products);
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View itemView = convertView;
       if(itemView == null)
           itemView = ((Activity)this.getContext()).getLayoutInflater().inflate(R.layout.gridview_product, null);

        ImageView imageView = itemView.findViewById(R.id.imageView);
        imageView.setImageResource(products[position].getImageResource());


        TextView textViewTitle = itemView.findViewById((R.id.textViewTitle));
        textViewTitle.setText(products[position].getTitle());

        TextView textViewOriginalPrice = itemView.findViewById(R.id.textViewOriginalPrice);
        String strikedThroughText =
                "<strike>$" +
                        products[position]
                                .getOriginalPrice()
                                .setScale(2)
                                .toString()
                        + "</strike>";

        textViewOriginalPrice.setText(Html.fromHtml(strikedThroughText));

        TextView textViewDiscountedPrice = itemView.findViewById(R.id.textViewDiscountedPrice);
        textViewDiscountedPrice.setText("$" +
                products[position]
                        .getDiscountedPrice()
                        .setScale(2)
                        .toString());



        return itemView;

//        return super.getView(position, convertView, parent);
    }
}
