package com.example.calvin.servingsizecalculator;

/**
 * Store information about a single pot
 */

public class Pot {

    private String potName;
    private int potWeight;

    // Set member data based on parameters.
    public Pot(String name, int weightInG) {
        potName = name;
        potWeight = weightInG;
    }

    // Return the weight
    public int getWeightInG() {
        return potWeight;
    }

    // Set the weight. Throws IllegalArgumentException if weight is less than 0.
    public void setWeightInG(int weightInG) {
        if(weightInG<0){
            throw new IllegalArgumentException("Weight is less than 0");
        }else {
            potWeight = weightInG;
        }
    }

    // Return the name.
    public String getName() {
        return potName;
    }

    // Set the name. Throws IllegalArgumentException if name is an empty string (length 0),
    // or if name is a null-reference.
    public void setName(String name) {
        if(name == ""){
            throw new IllegalArgumentException("Empty field");
        }else{
            potName = name;
        }
    }
}
