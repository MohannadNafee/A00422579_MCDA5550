package com.example.mohan.bmiapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class calculate_BMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate__bmi);
    }
    public void calculate(View view) {
        EditText height = (EditText) findViewById(R.id.textHeight);
        String value = height.getText().toString();
        Double heightVal = Double.parseDouble(value);
        System.out.println("Here is the height "+heightVal);

        EditText weight = (EditText) findViewById(R.id.textWeight);
        String weightString = weight.getText().toString();
        Double weightVal = Double.parseDouble(weightString);
        System.out.println("Here is the height "+weightVal);

        Double calc = (weightVal / (heightVal * heightVal));
        EditText result = (EditText) findViewById(R.id.textResult);
        result.setText(calc.toString());
    }
}
