package com.example.prac5.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.prac5.R;

public class SelectBreadDialogFragment extends DialogFragment {

    public interface ItemSelectedListener
    {
        void onItemClick(DialogFragment fragment, int position);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("Select Bread")
                .setItems(R.array.bread_choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // What we are saying is that, when the user clicks on
                        // an item, we will get the parent activity and
                        // call it's onItemClick event (if it implements the ItemSelectedListener)
                        //
                        Activity parentActivity = getActivity();
                        if (parentActivity instanceof ItemSelectedListener)
                            ((ItemSelectedListener)parentActivity).onItemClick(SelectBreadDialogFragment.this, i);
                    }
                })
                .create();
    }
}
