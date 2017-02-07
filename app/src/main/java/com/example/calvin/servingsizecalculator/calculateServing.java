package com.example.calvin.servingsizecalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calculateServing extends AppCompatActivity {
    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_WEIGHT = "EXTRA WEIGHT";
    private static final String TAG = "PotCalcApp";
    public static final int RESULT_DELETE = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_serving);

        final Intent intent = getIntent();
        //Pot Name
        String name = intent.getStringExtra(EXTRA_NAME);
        TextView listedName = (TextView) findViewById(R.id.potName);
        listedName.setText(name);

        //Pot Weight
        final int emptyPot = intent.getIntExtra(EXTRA_WEIGHT, 0);
        TextView listedWeight = (TextView) findViewById(R.id.listedPotWeight);
        listedWeight.setText(""+emptyPot);

        //Weight with food
        weightWithFood(emptyPot);
        //# of servings
        numberServings();
        //Delete function
        deleteButton(intent);
        //Returns to previous activity
        returnButton();
    }

    private void deleteButton(final Intent intent) {
        Button deleteBtn = (Button) findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = intent.getIntExtra("potIndex", 0);
                Intent intentDel = new Intent();
                intentDel.putExtra("potIndex", position);
                setResult(RESULT_DELETE, intent);
                finish();
            }
        });
    }

    private void weightWithFood(final int emptyPot) {
        final EditText totalWeight = (EditText) findViewById(R.id.totalWeightField);
        totalWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String total = totalWeight.getText().toString();
                    int totalInt;
                    //Check for null value
                    if (TextUtils.isEmpty(total)) {
                        totalInt = 0;
                    } else {
                        totalInt = Integer.parseInt(total);
                    }
                    //Calculate weight of the food
                    int food = totalInt - emptyPot;
                    if (food < 0) {
                        food = 0;
                    }
                    //Changes text accordingly
                    TextView foodWeight = (TextView) findViewById(R.id.calcFoodWeight);
                    foodWeight.setText("" + food);
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Number is too long", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void numberServings() {
        final EditText numServings = (EditText) findViewById(R.id.servingNumField);
        numServings.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                try {
                    String servingWeight = numServings.getText().toString();
                    int servingInt;

                    //Check for null value
                    if (TextUtils.isEmpty(servingWeight)) {
                        servingInt = 0;
                    } else {
                        servingInt = Integer.parseInt(servingWeight);
                    }
                    //Calculate weight of the food
                    TextView foodWeight = (TextView) findViewById(R.id.calcFoodWeight);
                    String calcWeight = foodWeight.getText().toString();
                    int calcWeightInt = Integer.parseInt(calcWeight);
                    if (servingInt <= 0) {
                        servingInt = 1;
                    }
                    int indivServ = calcWeightInt / servingInt;
                    //Changes text accordingly
                    TextView servSize = (TextView) findViewById(R.id.calcServingWeight);
                    servSize.setText("" + indivServ);
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Number is too long", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void returnButton() {
        Button returnBtn = (Button) findViewById(R.id.goBack);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Returning");
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context, String name, int weight){
        Intent intent = new Intent(context, calculateServing.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_WEIGHT, weight);
        return intent;
    }
}
