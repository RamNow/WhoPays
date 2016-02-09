package de.ramnow.whopays;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonsFragment extends Fragment {

    private RecyclerView mPersonsView;
    private LinearLayoutManager mLayoutManager;
    private PersonsAdapter mPersonsAdapter;

    public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.ViewHolder> {
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
                Snackbar.make(view, mTextView.getText() + " geklickt", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public PersonsAdapter(ArrayList<String> myDataset) {
            mDataset = myDataset;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public PersonsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_item, parent, false);
            TextView tv = (TextView) v.findViewById(R.id.person_item_text);

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

    public PersonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fragment_persons = inflater.inflate(R.layout.fragment_persons, container, false);
        mPersonsView = (RecyclerView) fragment_persons.findViewById(R.id.persons_list);
        mPersonsView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mPersonsView.setLayoutManager(mLayoutManager);

        ArrayList<String> dummyData = new ArrayList<>();
        dummyData.add("Schobbe");
        dummyData.add("Ramelow");
        dummyData.add("Kappes");
        mPersonsAdapter = new PersonsAdapter(dummyData);
        mPersonsView.setAdapter(mPersonsAdapter);

        return fragment_persons;
    }
}
