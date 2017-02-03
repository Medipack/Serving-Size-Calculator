package com.example.medipack.servingsizecalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PotCalc";
    PotCollection potList;
    ListView list;
    //listView : {views: pots.xml}
    //Test
    Pot test = new Pot("test", 70);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creates the list
        potList = new PotCollection();
        potList.addPot(test);
        //Populate the list
        populateListView(list);

        //Make items clickable
        itemsClickabale();

        //Add Pot button
        addPotLaunch();
    }

    //Make items clickable
    private void itemsClickabale() {
        ListView list = (ListView) findViewById(R.id.potList);
        //set list items clickable
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "Item clicked");
                //Launches pot calculation activity
                Intent potCalcIntent = calculateServing.makeIntent(MainActivity.this);
                startActivity(potCalcIntent);
            }
        });
    }

    //Add Pot button
    private void addPotLaunch() {
        Button addPotBtn = (Button) findViewById(R.id.addPot);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log the button press
                Log.i(TAG, "Add pot button clicked");
                // Launches addAPot activity
                Intent addPotIntent = addAPot.makeIntent(MainActivity.this);
                startActivity(addPotIntent);
            }
        });
    }

    //Populate a list
    private void populateListView(ListView list) {
        // Create list of items
        String[] potItems = potList.getPotDescriptions();

        //build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                    // Context for the activity
                R.layout.list_pots,     //Layouts to use(create)
                potItems);              // Items to be displays

        //Configure the list view
        list = (ListView) findViewById(R.id.potList);
        list.setAdapter(adapter);
    }

}
