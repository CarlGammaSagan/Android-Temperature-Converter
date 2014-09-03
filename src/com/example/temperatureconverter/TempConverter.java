/*
 * 
 * Copyright © 2014 SaganSpirit8@gmail.com
 *
 * This file is part of the Temperature Converter Android App.
 *
 * Temperature Converter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Temperature Converter is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Temperature Converter.  If not, see <http://www.gnu.org/licenses/>.
 *
 */


package com.example.temperatureconverter;

import java.text.DecimalFormat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TempConverter extends Activity
{
    String testString;
    // int switch to indicate which conversion formula to use. 
    // 0 is Fah to Cel. 1 is Cel to Fah.
    int convDirection = 0; 

    double input;
    EditText et;
    TextView tv;
    RadioGroup rg;
    
    DecimalFormat df = new DecimalFormat("#.##"); //used to format output temperature
    
    Toast toast;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	//initial setup of UI layout
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);
    
        //Google's Admob
        AdView av = (AdView) findViewById(R.id.adView);        
        AdRequest adRequest = new AdRequest.Builder().build();
        // Start loading the ad in the background.
        av.loadAd(adRequest);
        
        //connect UI elements in Java file to XML file
        rg = (RadioGroup) findViewById(R.id.rgroup);
        et = (EditText) findViewById(R.id.input);
        tv = (TextView) findViewById(R.id.output);
        rg.check(R.id.radio1); //the first button is checked by default
    }
   
    public void onRadioButtonClicked(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        
        et.setText("0");
        tv.setText("");
        
        switch(view.getId())
        {
            case R.id.radio1:
                if (checked)
                    convDirection = 0; //Fahrenheit to Celsius
                break;
            case R.id.radio2:
                if (checked)
                    convDirection = 1; //Celsius to Fahrenheit
                break;
        }
    }
    
    public void onConvertButtonClicked(View view)
    {
	
	testString = et.getText().toString();
	if (testString.isEmpty()) //set input temperature to 0 if user did not input anything. otherwise, app will crash.
	{
	    et.setText("0");
	    testString = "0";
	}
        input = Double.parseDouble(testString);
        
        switch(convDirection)
        {
            case 0: 
                tv.setText(df.format(fToC(input))); 
                break;
            case 1:
                tv.setText(df.format(cToF(input)));
                break;
        }
    }
    
    private double fToC (double input)
    {
        return (5.0 / 9 * ( input - 32));
    }
    
    private double cToF (double input)
    {
        return (9.0 / 5 * input + 32);
    }
}