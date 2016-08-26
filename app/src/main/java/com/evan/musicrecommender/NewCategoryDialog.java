package com.evan.musicrecommender;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.TextView;

/**
 * Created by evanh on 8/26/2016.
 */
public class NewCategoryDialog extends DialogFragment {

    public interface NewCategoryDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        inflater.inflate(R.layout.new_category_dialog_layout, null);

        builder.
    }
}
