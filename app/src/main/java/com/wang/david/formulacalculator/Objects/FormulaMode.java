package com.wang.david.formulacalculator.Objects;

/**
 * Created by David on 2/20/2017.
 */

public abstract class FormulaMode {
    protected String title;
    protected int numVariables;
    protected String[] variableNames;
    protected int[] variableValues = {1, 1};

    public FormulaMode(String _title, int _numVariables, String[] _variableNames) {
        title = _title;
        numVariables = _numVariables;
        variableNames.equals(_variableNames);
    }

    public FormulaMode(String _title, String[] _variables) {
        title = _title;
        numVariables = _variables.length;
        variableNames.equals(_variables);
    }

    public FormulaMode() {

    }

    protected abstract double calculate();
}
