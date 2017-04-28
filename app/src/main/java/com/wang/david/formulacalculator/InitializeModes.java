package com.wang.david.formulacalculator;


/**
 * Created by David on 2/22/2017.
 *
 * @author David Wang
 */

public class InitializeModes {
    public static boolean initializeAll() {
        boolean initializeSuccess = true;
        MainActivity.numberOfModes = 4;

        //List of functions starts
        initializeSuccess &= initializeMultiplication();


        //List of functions ends
        return initializeSuccess;
    }

    public static boolean initializeMultiplication() {
        return true;
        //TODO finish this function
    }
}
