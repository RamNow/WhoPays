package de.ramnow.whopays;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PayoffsAdapter mPayoffsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        RecyclerView payoffsRecyclerView = (RecyclerView) findViewById(R.id.payoffs_list);
        payoffsRecyclerView.setHasFixedSize(true);
        payoffsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> dummyData = new ArrayList<>();
        dummyData.add("OpenFlair 2014");
        dummyData.add("WG Abrechnung Oktober '14");
        mPayoffsAdapter = new PayoffsAdapter(this, dummyData);
        payoffsRecyclerView.setAdapter(mPayoffsAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Add Code for \"Neue Abrechnung\"", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
                mPayoffsAdapter.addData("Item");
            }
        });
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

        // no inspection Simplifiable If-Statement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
