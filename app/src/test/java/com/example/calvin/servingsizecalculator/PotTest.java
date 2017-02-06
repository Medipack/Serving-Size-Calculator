package com.example.calvin.servingsizecalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Calvin on 2017-02-06.
 */
public class PotTest {

    private String potName = "testing Pot";
    private int potWeight = 300;
    private Pot testPot = new Pot(potName, potWeight);

    @Test
    public void testGetWeight() throws Exception {
        assertEquals(potWeight, testPot.getWeightInG());
    }

    @Test
    public void testSetWeightInG() throws Exception {
        String potName = "testing Pot";
        int potWeight = 300;
        Pot testPot = new Pot(potName, potWeight);
        testPot.setWeightInG(500);
        assertEquals(500, testPot.getWeightInG());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testExceptSetWeightInG() throws Exception {
        int potWeight = -100;
        Pot testPot = new Pot(potName, potWeight);
        testPot.setWeightInG(potWeight);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(potName, testPot.getName());
    }

    @Test
    public void testSetName() throws Exception {
        String newName = "kettle";
        testPot.setName(newName);
        assertEquals(newName, testPot.getName());
    }

    @Test (expected = IllegalArgumentException.class)
    public void testEmptySetName() throws Exception {
        String newName = "";
        testPot.setName(newName);
    }
}