package com.example.medipack.servingsizecalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class addAPot extends AppCompatActivity {
    private static final String TAG = "addPot";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apot);
        OKBUtton();
        CancelButton();
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, addAPot.class);
    }

    private void OKBUtton() {
        Button addPotBtn = (Button) findViewById(R.id.OK);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Pot newPot = new Pot("test2", 80);

                Log.i(TAG, "Add pot confirmed");
                finish();
            }
        });
    }



    private void CancelButton() {
        Button addPotBtn = (Button) findViewById(R.id.Cancel);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Cancelled");
                finish();
            }
        });
    }
}
