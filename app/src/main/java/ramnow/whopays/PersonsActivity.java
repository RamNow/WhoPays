package ramnow.whopays;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.*;


public class PersonsActivity extends Activity {

    private List<String> _personen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        _personen = new ArrayList<String>();
        initButtonListener();
     }

    private void initButtonListener() {
        ImageButton btnAdd = (ImageButton) findViewById(R.id.btnAdd);

//        ListView listView = (ListView) findViewById(R.id.listViewPersons);
//        ArrayAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.person_list_view_item, _personen);
//        listView.setAdapter(listAdapter);


        btnAdd.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                _personen.add("P" +  _personen.size() + 1);
                Toast.makeText(getApplicationContext(), "Person hinzugefügt", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton btnDelete = (ImageButton) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Person entfernt", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.persons, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
