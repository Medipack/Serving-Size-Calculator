package com.example.medipack.servingsizecalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    private static final String TAG = "PotCalc";
    public static final int REQUEST_CODE = 1001;
    PotCollection potList;
    //listView : {views: pots.xml}

    Pot test = new Pot("test", 70);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        potList = new PotCollection();
        potList.addPot(test);
        populateListView();

        addPotLaunch(potList);
    }

    private void addPotLaunch(PotCollection potList) {
        Button addPotBtn = (Button) findViewById(R.id.addPot);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Add pot button clicked");
                // Launch add_a_Pot activity
                //Intent intent = new Intent(MainActivity.this,addAPot.class);
                Intent addPotIntent = addAPot.makeIntent(MainActivity.this);
                startActivityForResult(addPotIntent, REQUEST_CODE);
            }
        });
    }

    private void populateListView() {
        // Create list of items
        String[] potItems = potList.getPotDescriptions();

        //build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,                    // Context for the activity
                R.layout.list_pots,     //Layouts to use(create)
                potItems);              // Items to be displays

        //Configure the list view
        ListView list = (ListView) findViewById(R.id.potList);
        list.setAdapter(adapter);
    }

    /*public void additionalPot(View view){
        Intent intent = new Intent(this,addAPot.class);

    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String name = data.getStringExtra("nameAdded");
                    int weight = data.getIntExtra("weightAdded", 1);
                    Pot newPot = new Pot(name, weight);
                    potList.addPot(newPot);
                    populateListView();
                    Log.i(TAG, "Pot has been added");
                }else{
                    Log.i(TAG, "Activity cancelled");
                }
        }
    }
}
