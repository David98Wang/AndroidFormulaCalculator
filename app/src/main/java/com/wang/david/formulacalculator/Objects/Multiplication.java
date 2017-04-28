package com.wang.david.formulacalculator.Objects;

/**
 * Created by David on 2/20/2017.
 */

public class Multiplication extends FormulaMode {
    public Multiplication() {
        super("Multiplication", 2, new String[]{"varA", "varB"});

    }

    public Multiplication(String _title, int _numVariables, String[] _variableNames) {
        super(_title, _numVariables, _variableNames);

    }

    public double calculate() {
        double result = variableValues[0] * variableValues[1];
        return result;
    }
}
