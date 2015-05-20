package com.arif.unitconverter;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {
    private Spinner spUnit,spUnitFrom, spUnitTo;
    private String [] spinnerUnitsArray;
    private String [] temp_array;
    private String [] blankArray;
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
public void convert(View view)
{
    //double input = Double.parseDouble(etFrom.getText().toString());
    float inputValue = Float.parseFloat(etFrom.getText().toString());
    if(spUnitFrom.getSelectedItem().equals("fahrenheit") && spUnitTo.getSelectedItem().equals("celcius"))
    {

        etTo.setText(String.valueOf(convertFahrenheitToCelsius(inputValue)));

    }
    else
    {
        Toast.makeText(getApplicationContext(),"Input correctly",Toast.LENGTH_LONG).show();
    }

    if(spUnitFrom.getSelectedItem().equals("celcius") && spUnitTo.getSelectedItem().equals("fahrenheit"))
    {

        etTo.setText(String.valueOf(convertCelsiusToFahrenheit(inputValue)));

    }
//    else
//    {
//        Toast.makeText(getApplicationContext(),"Input correctly",Toast.LENGTH_LONG).show();
//    }
}
    private float convertFahrenheitToCelsius(float far) {
        return ((far - 32) * 5 / 9);
    }

    private float convertCelsiusToFahrenheit(float celsius) {
        return ((celsius * 9) / 5) + 32;
    }
}
