package de.ramnow.whopays;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExpensesFragment extends Fragment {

    private String CURRENCY;

    public ExpensesFragment() {
        // Required empty public constructor
       /* FragmentActivity activity = getActivity();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        CURRENCY = prefs.getString(getString(R.string.pref_currency_key), getString(R.string.pref_currency_default));*/
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expenses, container, false);
    }
}
