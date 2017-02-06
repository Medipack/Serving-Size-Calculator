package com.example.calvin.servingsizecalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PotCalcApp";
    private static final int REQUEST_CODE_ADDPOT = 1001;
    private static final int REQUEST_CODE_EDITPOT = 1002;
    private static final int REQUEST_CODE_CALC = 1003;
    private PotCollection potList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        potList = new PotCollection();
        populateListView();
        //Make list items clickable
        itemsClickable(potList);
        addPotLaunch();
    }

    private void itemsClickable(final PotCollection potList) {
        ListView list = (ListView) findViewById(R.id.potList);
        //set list items clickable
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "Item clicked");
                //Extract the information
                Pot listedPot = potList.getPot(position);
                String name = listedPot.getName();
                int weight = listedPot.getWeightInG();
                Intent potCalcIntent = calculateServing.makeIntent(MainActivity.this, name, weight);
                potCalcIntent.putExtra("potIndex", position);
                //Launches pot calculation activity
                startActivityForResult(potCalcIntent, REQUEST_CODE_CALC);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "Item Long Clicked");
                //Extract the information
                Pot listedPot = potList.getPot(position);
                String name = listedPot.getName();
                int weight = listedPot.getWeightInG();
                String weightString = String.valueOf(weight);
                //Create intent
                Intent potEditIntent = addAPot.makeIntent(MainActivity.this);
                potEditIntent.putExtra("potName", name);
                potEditIntent.putExtra("potWeight", weightString);
                potEditIntent.putExtra("potIndex", position);
                //Launches pot editing activity
                startActivityForResult(potEditIntent, REQUEST_CODE_EDITPOT);
                return true;
            }
        });
    }

    private void addPotLaunch() {
        Button addPotBtn = (Button) findViewById(R.id.addPot);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Add Pot button clicked");
                // Launch add_a_Pot activity
                Intent addPotIntent = addAPot.makeIntent(MainActivity.this);
                startActivityForResult(addPotIntent, REQUEST_CODE_ADDPOT);
            }
        });
    }

    private void populateListView() {
        // Create list of items
        String[] potItems = potList.getPotDescriptions();
        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                    // Context for the activity
                R.layout.list_pots,     //Layouts to use(create)
                potItems);              // Items to be displays
        //Configure the list view
        ListView list = (ListView) findViewById(R.id.potList);
        list.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_ADDPOT:
                if (resultCode == Activity.RESULT_OK) {
                    String name = data.getStringExtra("nameAdded");
                    int weight = data.getIntExtra("weightAdded", 0);
                    Pot newPot = new Pot(name, weight);
                    potList.addPot(newPot);
                    populateListView();
                    Log.i(TAG, "Pot has been added");
                }else{
                    Log.i(TAG, "Activity cancelled");
                }
                break;
            case REQUEST_CODE_EDITPOT:
                if (resultCode == Activity.RESULT_OK) {
                    String name = data.getStringExtra("nameAdded");
                    int weight = data.getIntExtra("weightAdded", 0);
                    Pot newPot = new Pot(name, weight);
                    int index = data.getIntExtra("potIndex", 0);
                    potList.changePot(newPot, index);
                    populateListView();
                    Log.i(TAG, "Pot has been edited");
                }else{
                    Log.i(TAG, "Activity cancelled");
                }
                break;
            case REQUEST_CODE_CALC:
                if (resultCode == calculateServing.RESULT_DELETE){
                    int index = data.getIntExtra("potIndex", 0);
                    potList.removePot(index);
                    populateListView();
                    Log.i(TAG, "Pot has been removed");
                }
        }
    }
}
