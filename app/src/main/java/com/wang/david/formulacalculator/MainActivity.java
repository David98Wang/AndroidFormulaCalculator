package com.wang.david.formulacalculator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.wang.david.formulacalculator.Objects.FormulaMode;
import com.wang.david.formulacalculator.R;

/**
 * <h1>Formula Calculator</h1>
 * Calculates formulas based on inputs
 *
 * @author David Wang
 * @version 0.1
 * @since 2017-2-21
 */
public class MainActivity extends AppCompatActivity {
    public static int numberOfModes;
    //Constants
    private final String UTF_8 = "UTF-8";
    public FormulaMode[] formulaModes;
    //Private variables
    private EditText edittext1, edittext2;
    private TextView result;
    private Button ButtonSetting;
    private Integer mode = -1;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    private void addDrawerItems() {
        String[] modeArray;
        modeArray = getResources().getStringArray(R.array.list_mode_entries);

        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, modeArray);
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView) findViewById(R.id.navList);

        InitializeModes.initializeAll();


        addDrawerItems();

        parseSetting();
        //ButtonSetting.setText(String.valueOf(mode));
        addResultListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeTitle();
    }
    public void parseSetting() {
        try {
            SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
            String modeString = SP.getString("mode", "mul");
            switch (modeString) {
                case "1":
                    mode = 1;
                    break;
                case "2":
                    mode = 2;
                    break;
                case "3":
                    mode = 3;
                    break;
                case "4":
                    mode = 4;
                    break;
                default:
                    mode = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double add(double a, double b) {
        return a + b;
    }

    private double sub(double a, double b) {
        return a - b;
    }

    private double mul(double a, double b) {
        return a * b;
    }

    private double dev(double a, double b) {
        if (b != 0) return a / b;
        else return 0;//TODO: change divide by 0 case
    }
    public void changeTitle() {
        TextView title = (TextView) findViewById(R.id.textViewTitle);
        switch(mode){
            case 1:
                title.setText("MULTIPLY");
                break;
            case 2:
                title.setText("DIVIDE");
                break;
            case 3:
                title.setText("ADD");
                break;
            case 4:
                title.setText("SUBTRACT");
                break;
            default:
                title.setText("Unexpected Case");
        }
    }
    public void addTitileListener(int curMode) {
        //TextView titleTV = (TextView) findViewById(R.id.textViewTitle);
        changeTitle();
    }
    public void addResultListener() {

        changeTitle();
        //title.setText(Integer.toString(mode));
        edittext1 = (EditText) findViewById(R.id.edittext1);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        result = (TextView) findViewById(R.id.result);
        ButtonSetting = (Button) findViewById(R.id.ButtonSetting);
        ButtonSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this,
                        SettingsActivity.class);
                startActivity(myIntent);
            }
        });
        // add a keylistener to keep track user input
        edittext1.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                changeTitle();
                // if keydown and "enter" is pressed
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    double a = 0., b = 0.;
                    if (!edittext1.getText().toString().isEmpty())
                        a = Double.parseDouble(edittext1.getText().toString());
                    if (!edittext2.getText().toString().isEmpty())
                        b = Double.parseDouble(edittext2.getText().toString());
                    //result.setText(String.valueOf(a * b));

                    if (!edittext2.getText().toString().matches("")) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edittext1.getWindowToken(), 0);
                    }
                    double resultVal = 0;
                    parseSetting();
                    switch (mode) {
                        case 1:
                            resultVal = mul(a, b);
                            break;
                        case 2:
                            resultVal = dev(a, b);
                            break;
                        case 3:
                            resultVal = add(a, b);
                            break;
                        case 4:
                            resultVal = sub(a, b);
                    }
                    result.setText(String.valueOf(resultVal));
                    return true;
                }
                return false;
            }
        });
        edittext2.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                changeTitle();
                if ((event.getAction() == KeyEvent.ACTION_DOWN)
                        && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    double a = 0., b = 0.;
                    if (!edittext1.getText().toString().isEmpty())
                        a = Double.parseDouble(edittext1.getText().toString());
                    if (!edittext2.getText().toString().isEmpty())
                        b = Double.parseDouble(edittext2.getText().toString());
                    double resultVal = 0;
                    parseSetting();
                    switch (mode) {
                        case 1:
                            resultVal = mul(a, b);
                            break;
                        case 2:
                            resultVal = dev(a, b);
                            break;
                        case 3:
                            resultVal = add(a, b);
                            break;
                        case 4:
                            resultVal = sub(a, b);
                    }
                    result.setText(String.valueOf(resultVal));
                    return true;
                }
                return false;
            }
        });

    }
}
