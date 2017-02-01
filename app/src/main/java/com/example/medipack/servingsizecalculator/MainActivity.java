package com.example.medipack.servingsizecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

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
}
