package de.ramnow.whopays;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import de.ramnow.whopays.data.WhoPaysContract;

public class PayoffsAdapter extends RecyclerView.Adapter<PayoffsAdapter.ViewHolder> {
    private MainActivity mainActivity;

    // Holds on to the cursor to display the payoffs
    private Cursor mCursor;

    PayoffsAdapter(MainActivity mainActivity, Cursor cursor) {

        this.mainActivity = mainActivity;
        this.mCursor = cursor;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.payoff_list_item, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.payoff_item_text);

        return new ViewHolder(tv);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Move the mCursor to the position of the item to be displayed
        if (!mCursor.moveToPosition(position))
            return; // bail if returned null

        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(WhoPaysContract.AbrechnungEntry.COLUMN_NAME));
        holder.mTextView.setText(name);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    void swapCursor(Cursor newCursor) {
        if (this.mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;
        if (newCursor != null) {
            this.notifyDataSetChanged();
        }
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView mTextView;

        ViewHolder(TextView v) {
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
}
