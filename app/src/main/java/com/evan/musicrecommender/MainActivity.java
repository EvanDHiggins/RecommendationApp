package com.evan.musicrecommender;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements NewEntryDialog.NewEntryListener, View.OnClickListener {

    public static final Random random = new Random();

    EntryDataSource entryDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        entryDataSource = new EntryDataSource(this);
        entryDataSource.open();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageDrawable(getResources().getDrawable(R.drawable.fab_add_item, null));
        final Context c = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup layout = (ViewGroup) findViewById(R.id.content_main);
                layout.addView(new CategoryCard(c, null));
//                DialogFragment dialog = new NewEntryDialog();
//                dialog.show(getFragmentManager(), "blah");
            }
        });

//        Button recommendButton = (Button) findViewById(R.id.recommendButton);
//        recommendButton.setOnClickListener(this);

    }

    private void displayCategoryCreatorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create Category");

        EditText
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onEntryDialogPositiveClick(RecommendationEntry entry) {
        Toast.makeText(getApplicationContext(), entry.getArtist().get(), Toast.LENGTH_SHORT).show();
        entryDataSource.createEntry(entry);
    }

    @Override
    public void onEntryDialogNegativeClick() {

    }

    @Override
    public void onClick(View v) {
        Optional<RecommendationEntry> randomEntry = entryDataSource.getRandomEntry();
        if(randomEntry.isPresent()) {
            RecommendationEntry entry = randomEntry.get();
            StringBuilder toastBuilder = new StringBuilder();
            toastBuilder.append("Artist: " + entry.getArtist().get() + "\n");
            if(entry.hasAlbum()) {
                toastBuilder.append("Album: " + entry.getAlbum().get());
            }
            Toast.makeText(getApplicationContext(), toastBuilder.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
