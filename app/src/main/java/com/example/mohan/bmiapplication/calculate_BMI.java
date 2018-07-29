package com.example.mohan.bmiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;

public class calculate_BMI extends AppCompatActivity {
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate__bmi);
        email = getIntent().getExtras().getString("email");
    }
    public void calculate(View view) {
        try {
            EditText height = (EditText) findViewById(R.id.textHeight);
            EditText weight = (EditText) findViewById(R.id.textWeight);
            EditText result = (EditText) findViewById(R.id.textResult);

            if (validateFields()) {
                String weightString = weight.getText().toString();
                Double weightVal = Double.parseDouble(weightString);
                System.out.println("Here is the height "+weightVal);

                String value = height.getText().toString();
                Double heightVal = Double.parseDouble(value);
                System.out.println("Here is the height "+heightVal);
                Double calc = (weightVal / (heightVal * heightVal));

                result.setText(calc.toString());

                InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
                helper.insert_BMI(email, heightVal, weightVal, calc);
            }
        }
        catch (Exception exception){

        }

    }
    public boolean validateFields() {
        Boolean isvalid = true;
        EditText height = (EditText) findViewById(R.id.textHeight);
        EditText weight = (EditText) findViewById(R.id.textWeight);
        if (height.getText().toString().isEmpty()) {
            height.setError("Please insert height!");
            isvalid = false;
        }
        if (weight.getText().toString().isEmpty()) {
            weight.setError("Please insert email!");
            isvalid = false;
        }
        String weightString = weight.getText().toString();
        Double weightVal = Double.parseDouble(weightString);
        String value = height.getText().toString();
        Double heightVal = Double.parseDouble(value);
        if (weightVal < 50) {
            weight.setError("Weight should be above 50");
            isvalid = false;
        }
        if (heightVal < 1 & heightVal > 2.5) {
            height.setError("Height should be Between 1 and 2.5 m");
            isvalid = false;
        }
        return isvalid;
    }
    public void showBmiHistory(View view) {

        Intent intent = new Intent(this, BmiHistory.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
