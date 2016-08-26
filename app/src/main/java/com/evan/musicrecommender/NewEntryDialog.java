package com.evan.musicrecommender;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Evan on 8/7/2016.
 */
public class NewEntryDialog extends DialogFragment {

    public interface NewEntryListener {
        public void onEntryDialogPositiveClick(RecommendationEntry entry);
        public void onEntryDialogNegativeClick();
    }

    NewEntryListener mListener;

    String albumName;
    String artistName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.new_entry_dialog_layout);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_entry_dialog_layout, null);

        TextView artistField = (TextView)view.findViewById(R.id.artistField);
        TextView albumField = (TextView)view.findViewById(R.id.albumField);

        artistName = artistField.getText().toString();
        albumName = albumField.getText().toString();

        artistField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                artistName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        albumField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                albumName = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        builder.setView(view);

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Optional<RecommendationEntry> entry = getEntry();
                if(entry.isPresent())
                    mListener.onEntryDialogPositiveClick(entry.get());
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NewEntryDialog.this.dismiss();
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (NewEntryListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
            "must implement NewEntryListener");
        }
    }

    private Optional<RecommendationEntry> getEntry() {
        if(artistName.isEmpty())
            return Optional.empty();

        if(albumName.isEmpty())
            return Optional.of(new RecommendationEntry(artistName));

        return Optional.of(new RecommendationEntry(artistName, albumName));
    }
}
