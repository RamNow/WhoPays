package de.ramnow.whopays;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Date;

import de.ramnow.whopays.data.WhoPaysContract;
import de.ramnow.whopays.data.WhoPaysDbHelper;

public class MainActivity extends AppCompatActivity {

    private PayoffsAdapter mPayoffsAdapter;
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        RecyclerView payoffsRecyclerView = (RecyclerView) findViewById(R.id.payoffs_list);
        payoffsRecyclerView.setHasFixedSize(true);
        payoffsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        WhoPaysDbHelper dbHelper = new WhoPaysDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllPayoffs();
        mPayoffsAdapter = new PayoffsAdapter(this, cursor);
        payoffsRecyclerView.setAdapter(mPayoffsAdapter);

        // Sample Data
        /*addNewPayoff("OpenFlair 2014");
        addNewPayoff("WG Abrechnung Oktober '14");*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addNewPayoff("Item" + new Date().toString());
                mPayoffsAdapter.swapCursor(getAllPayoffs());
            }
        });
    }

    /**
     * Query the mDb and get all payoffs from the abrechnung table
     *
     * @return Cursor containing the list of payoffs
     */
    private Cursor getAllPayoffs() {
        return mDb.query(
                WhoPaysContract.AbrechnungEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WhoPaysContract.AbrechnungEntry.COLUMN_NAME
        );
    }

    private long addNewPayoff(String name){

        ContentValues cv = new ContentValues();
        cv.put(WhoPaysContract.AbrechnungEntry.COLUMN_NAME, name);

        return mDb.insert(WhoPaysContract.AbrechnungEntry.TABLE_NAME, null, cv);
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
