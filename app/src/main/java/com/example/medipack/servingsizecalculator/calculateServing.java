package com.example.medipack.servingsizecalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class calculateServing extends AppCompatActivity {
    private static final String TAG = "calcServ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_serving);
        returnButton();
    }

    private void returnButton() {
        Button addPotBtn = (Button) findViewById(R.id.goBack);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Cancelled");
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, calculateServing.class);
    }
}
