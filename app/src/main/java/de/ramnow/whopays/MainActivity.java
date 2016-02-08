package de.ramnow.whopays;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mAbrechnungenView;
    private LinearLayoutManager mLayoutManager;
    private AbrechnungenAdapter mAbrechnungenAdapter;

    public class AbrechnungenAdapter extends RecyclerView.Adapter<AbrechnungenAdapter.ViewHolder> {
        private ArrayList<String> mDataset;

        public void addData(String data) {
            mDataset.add(data);
            notifyItemInserted(mDataset.size() - 1);
        }

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            // each data item is just a string in this case
            public TextView mTextView;

            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
                v.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AbrechnungActivity.class)
                        .putExtra(Intent.EXTRA_TEXT, mTextView.getText());
                startActivity(intent);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public AbrechnungenAdapter(ArrayList<String> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public AbrechnungenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.abrechnung_list_item, parent, false);
            TextView tv = (TextView) v.findViewById(R.id.abrechnung_item_text);

            ViewHolder vh = new ViewHolder(tv);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.setText(mDataset.get(position));

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mAbrechnungenView = (RecyclerView) findViewById(R.id.abrechnungen_list);
        mAbrechnungenView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mAbrechnungenView.setLayoutManager(mLayoutManager);

        ArrayList<String> dummyData = new ArrayList<>();
        dummyData.add("OpenFlair 2014");
        dummyData.add("WG Abrechnung Oktober '14");
        mAbrechnungenAdapter = new AbrechnungenAdapter(dummyData);
        mAbrechnungenView.setAdapter(mAbrechnungenAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Add Code for \"Neue Abrechnung\"", Snackbar.LENGTH_SHORT)
//                        .setAction("Action", null).show();
                mAbrechnungenAdapter.addData("Item");
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
