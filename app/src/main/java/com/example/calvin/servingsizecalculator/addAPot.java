package com.example.calvin.servingsizecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addAPot extends AppCompatActivity {
    private static final String TAG = "PotCalcApp";
    private static final int MINIMUM_CHAR = 1;
    private static final int MINIMUM_WEIGHT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_apot);
        EditText nameField = (EditText) findViewById(R.id.nameField);
        EditText weightField = (EditText) findViewById(R.id.weightField);
        Intent intent = getIntent();
        //Fill in and record extra information
        setInfo(nameField, weightField, intent);
        int potIndex = intent.getIntExtra("potIndex", 0);
        //Button functions
        OKButton(nameField, weightField, potIndex);
        CancelButton();
    }

    private void setInfo(EditText nameField, EditText weightField, Intent intent) {
        String potName = intent.getStringExtra("potName");
        nameField.setText(potName);
        String potWeight = intent.getStringExtra("potWeight");
        weightField.setText(potWeight);
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, addAPot.class);
    }

    private void OKButton(final EditText nameField, final EditText weightField, final int potIndex) {
        Button addPotBtn = (Button) findViewById(R.id.OK);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extract data from UI
                String potName = nameField.getText().toString();
                String potWeight = weightField.getText().toString();
                //Check to see if all fields have been filled
                //If the name field is empty then toast an error message that it should be one character long
                //If the weight field is empty than toast that it should be more than or equal to 0
                if(isEmpty(potName) && isEmpty(potWeight)) {
                    String message = "Please enter all your information";
                    makeToastMsg(message);
                }else if(isEmpty(potName)){
                    if(potName.length()<MINIMUM_CHAR){
                        String message =  "Name of pot must be %d character long";
                        message = String.format(message, MINIMUM_CHAR);
                        makeToastMsg(message);
                    }
                }else if(isEmpty(potWeight)){
                    String message = "Weight of pot must be more than or equal to %d";
                    message = String.format(message, MINIMUM_WEIGHT);
                    makeToastMsg(message);
                }else{
                    int weight = Integer.parseInt(potWeight);
                    passData(potName, weight, potIndex);
                    Log.i(TAG, "Add pot confirmed");
                    finish();
                }
            }
        });
    }

    private boolean isEmpty(String potName) {
        return TextUtils.isEmpty(potName);
    }

    private void makeToastMsg(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void passData(String potName, int weight, int potIndex) {
        //Pass data back
        Intent intent = new Intent();
        intent.putExtra("nameAdded", potName);
        intent.putExtra("weightAdded", weight);
        intent.putExtra("potIndex", potIndex);
        setResult(Activity.RESULT_OK, intent);
    }

    private void CancelButton() {
        Button addPotBtn = (Button) findViewById(R.id.Cancel);
        addPotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Activity Cancelled");
                finish();
            }
        });
    }
}

