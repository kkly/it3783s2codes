package com.example.prac5.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.prac5.R;

public class SelectSauceDialogFragment  extends DialogFragment {
    // Once again, this will be used to communicate to the
    // parent activity that the user has made the selection.
    //
    public interface ItemSelectedListener
    {
        void onItemClick(DialogFragment fragment, int position);
    }

    // This will store the user's selection.
    int checkedItem;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Retrieve the checkedItem integer value passed in to this dialog fragment
        // from the parent activity.
        //
        Bundle params = new Bundle();
        checkedItem = params.getInt("checkedItem");

        return new AlertDialog.Builder(getActivity())
                .setTitle("Select Sauce")
                .setSingleChoiceItems(R.array.sauce_choice, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // This method is triggered whenever the user selects one of the radio buttons.
                        // But selecting the radio buttons doesn't close the dialog immediately,
                        // so unlike the SelectBreadDialogFragment, we shouldn't be calling the
                        // onItemClick method of our parent fragment.
                        //
                        // Instead we just remember which item the user selected:
                        //
                        checkedItem = i;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Since the dialog doesn't auto close upon selection, we have to add
                        // the positive "OK" button for the user to confirm the selection.
                        //
                        // In this case, we will call the parent activity's onItemClick method
                        // when the user clicks on the "OK" button.
                        //
                        Activity parentActivity = getActivity();
                        if (parentActivity instanceof ItemSelectedListener)
                            ((ItemSelectedListener)parentActivity).onItemClick(SelectSauceDialogFragment.this, checkedItem);
                    }
                })
                .create();
    }
}
