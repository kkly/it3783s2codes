package com.example.prac5.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.prac5.R;

public class SelectToppingsDialogFragment extends DialogFragment {

    boolean[] checkedItems;

    public interface ItemsSelectedListener {
        void onItemsSelected(DialogFragment fragment,
                             boolean[] checkedItems);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        Bundle params = getArguments();
        checkedItems = params.getBooleanArray("checkedItems");

        return new AlertDialog.Builder(getActivity())
                .setTitle("Select Toppings")
                .setMultiChoiceItems(R.array.topping_choice,
                        checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkedItems[which] = isChecked;
                            }
                        })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Activity parentActivity = getActivity();
                        if (parentActivity instanceof ItemsSelectedListener)
                            ((ItemsSelectedListener)parentActivity).onItemsSelected(SelectToppingsDialogFragment.this, checkedItems);
                    }
                })
                .create();


    }
}
