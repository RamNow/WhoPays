package de.ramnow.whopays;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PayoffsAdapter extends RecyclerView.Adapter<PayoffsAdapter.ViewHolder> {
    private MainActivity mainActivity;
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
            Intent intent = new Intent(view.getContext(), PayoffsActivity.class)
                    .putExtra(Intent.EXTRA_TEXT, mTextView.getText());
            mainActivity.startActivity(intent);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PayoffsAdapter(MainActivity mainActivity, ArrayList<String> myDataset) {
        this.mainActivity = mainActivity;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.payoff_list_item, parent, false);
        TextView tv = (TextView) v.findViewById(R.id.payoff_item_text);

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
