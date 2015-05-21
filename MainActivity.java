package com.arif.unitconverter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends Activity {
    private Spinner spUnit,spUnitFrom, spUnitTo;
    private String [] spinnerUnitsArray;
    private String [] temp_array;
    private String [] blankArray,weightArray;
    private EditText etFrom,etTo;



    ArrayAdapter <String> unitSpinnerAdapter,spUnitFromAdapter,spUnitToAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialization
        spUnit = (Spinner) findViewById(R.id.spUnit);
        spUnitTo =(Spinner) findViewById(R.id.spUnitTo);
        spUnitFrom = (Spinner) findViewById(R.id.spUnitFrom);
        etFrom = (EditText) findViewById(R.id.etFrom);
        etTo = (EditText) findViewById(R.id.etTo);


        //string array
        spinnerUnitsArray = getResources().getStringArray(R.array.convert_unit);
        temp_array = getResources().getStringArray(R.array.temp_array);
        blankArray = getResources().getStringArray(R.array.blank_array);
        weightArray = getResources().getStringArray(R.array.weight_array);



        unitSpinnerAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,spinnerUnitsArray);
        spUnit.setAdapter(unitSpinnerAdapter);

        // spUnit spninner item selected options

        spUnit.setOnItemSelectedListener(unit_listener);



        // initialize From unit spinner






    }
AdapterView.OnItemSelectedListener unit_listener = new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(spUnit.getSelectedItem().equals("Temprature"))
        {
            spUnitFromAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, temp_array);
            //spUnitFrom.setAdapter(spUnitFromAdapter);
            spUnitFrom.setAdapter(spUnitFromAdapter);
            spUnitToAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,temp_array);
            spUnitTo.setAdapter(spUnitToAdapter);

        }
        else if(spUnit.getSelectedItem().equals("Weight"))
        {
            spUnitFromAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, weightArray);
            //spUnitFrom.setAdapter(spUnitFromAdapter);
            spUnitFrom.setAdapter(spUnitFromAdapter);
            spUnitToAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,weightArray);
            spUnitTo.setAdapter(spUnitToAdapter);

        }
        else {
            spUnitFromAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, blankArray);
            //Toast.makeText(getApplicationContext(), "array not loaded correctly",Toast.LENGTH_LONG).show();
            spUnitFrom.setAdapter(spUnitFromAdapter);
            //spUnitFrom.clearFocus();
            spUnitToAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, blankArray);
//            //Toast.makeText(getApplicationContext(), "array not loaded correctly",Toast.LENGTH_LONG).show();
            spUnitTo.setAdapter(spUnitToAdapter);
//            //spUnitFrom.clearFocus();

        }

//        if(spUnitFrom.getSelectedItem().equals("celcius")|
//                spUnitFrom.getSelectedItem().equals("fahrenheit")|
//                spUnitFrom.getSelectedItem().equals("kelvin"))
//        {
//            spUnitToAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,temp_array);
//            spUnitTo.setAdapter(spUnitToAdapter);
//
//
//
//        }
//        else {
//            spUnitToAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, blankArray);
//            //Toast.makeText(getApplicationContext(), "array not loaded correctly",Toast.LENGTH_LONG).show();
//            spUnitTo.setAdapter(spUnitToAdapter);
//            //spUnitFrom.clearFocus();
//        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
};
public void clear(View view)
{

    etFrom.setText("");
    etTo.setText("");


}
public void convert(View view) {
    //double input = Double.parseDouble(etFrom.getText().toString());


    DecimalFormat df = new DecimalFormat("##.##");
    String input = etFrom.getText().toString();
    if (input != null) {
        double inputValue;
        try {
            inputValue = new Double(etFrom.getText().toString());
        }
        catch (NumberFormatException e)
        {
            inputValue=0;
        }
        //double inputValue = Double.parseDouble(input);


        //String input = Float.toString(inputValue);
        //float inputValue = Float.parseFloat(etFrom.getText().toString());
        // Temperature convert conditions starts  here
        // F > C
        if (spUnitFrom.getSelectedItem().equals("fahrenheit") && spUnitTo.getSelectedItem().equals("celsius")) {
            double ftoc = Double.parseDouble(String.valueOf(convertFahrenheitToCelsius(inputValue)));
            etTo.setText(df.format(ftoc));

        }
//    else
//    {
//        Toast.makeText(getApplicationContext(),"Input correctly",Toast.LENGTH_LONG).show();
//    }

        // C > F
        if (spUnitFrom.getSelectedItem().equals("celsius") && spUnitTo.getSelectedItem().equals("fahrenheit")) {
            double ctof = Double.parseDouble(String.valueOf(convertCelsiusToFahrenheit(inputValue)));
            etTo.setText(df.format(ctof));

        }
        // C > K
        if (spUnitFrom.getSelectedItem().equals("celsius") && spUnitTo.getSelectedItem().equals("kelvin")) {
            double ctok = Double.parseDouble(String.valueOf(convertCelsiusToKelvin(inputValue)));
            etTo.setText(df.format(ctok));


        }
        // K> C
        if (spUnitFrom.getSelectedItem().equals("kelvin") && spUnitTo.getSelectedItem().equals("celsius")) {
            double value = Double.parseDouble(String.valueOf(convertKelvinToCelsius(inputValue)));
            etTo.setText(df.format(value));

        }


        // F > K
        if (spUnitFrom.getSelectedItem().equals("fahrenheit") && spUnitTo.getSelectedItem().equals("kelvin")) {
            double conv = Double.parseDouble(String.valueOf(convertFarenhietToKelvin(inputValue)));
            etTo.setText(df.format(conv));

        }


        // K > F

        if (spUnitFrom.getSelectedItem().equals("kelvin") && spUnitTo.getSelectedItem().equals("fahrenheit")) {
            double ktof = Double.parseDouble(String.valueOf(convertKelvinToFarenhiet(inputValue)));
            etTo.setText(df.format(ktof));


        }
// Temperature spinner selection ends here

        // Weight connvert conditions starts here


        if (spUnitFrom.getSelectedItem().equals("gram") && spUnitTo.getSelectedItem().equals("kilogram")) {
            double ktof = Double.parseDouble(String.valueOf(gramToKg(inputValue)));
            etTo.setText(df.format(ktof));


        }
        if (spUnitFrom.getSelectedItem().equals("gram") && spUnitTo.getSelectedItem().equals("ounce")) {
            double ktof = Double.parseDouble(String.valueOf(gramToOunce(inputValue)));
            etTo.setText(df.format(ktof));


        }
        if (spUnitFrom.getSelectedItem().equals("gram") && spUnitTo.getSelectedItem().equals("pounds")) {
            double ktof = Double.parseDouble(String.valueOf(gramToPound(inputValue)));
            etTo.setText(df.format(ktof));


        }
// Same input in both Spinner

        if (spUnitFrom.getSelectedItem() == spUnitTo.getSelectedItem()) {
            etTo.setText(String.valueOf(etFrom.getText().toString()));
        }


    }
    if (input.matches("")){
        Toast.makeText(getApplication(),"Input should not be null", Toast.LENGTH_LONG).show();
    }
}

    // Temperature conversion methods starts here
    private double convertFahrenheitToCelsius(double far) {
        return ((far - 32) * 5 / 9);
    }
    private double convertCelsiusToFahrenheit(double celsius) {
        return ((celsius * 9) / 5) + 32;
    }
    private double convertCelsiusToKelvin(double celsius) {
        return (celsius +273.15);
    }
    private double convertKelvinToCelsius(double kelvin) {
        return (kelvin -273.15);
    }
    private double convertFarenhietToKelvin(double fahr) {
        return ((fahr +459.67)*5 / 9);
    }
    private double convertKelvinToFarenhiet(double kelvin) {
        return (kelvin*(9/5) -459.67);
    }
    private double gramToKg(double gram) {
        return (gram / 1000);
    }
    private double gramToOunce(double gram) {
        return (gram * 0.03528);
    }

    private double gramToPound(double gram) {
        return (gram * 0.0022);
    }

    // Temperature conversion methods ends here



    public void swap(View view)
    {
        int spFromspinnerIndex = spUnitFrom.getSelectedItemPosition();
        spUnitFrom.setSelection(spUnitTo.getSelectedItemPosition());
        spUnitTo.setSelection(spFromspinnerIndex);


    }
}