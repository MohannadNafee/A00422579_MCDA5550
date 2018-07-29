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

            InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
            helper.insert_BMI(email, heightVal, weightVal, calc);
        }
        catch (Exception exception){

        }

    }
    public void showBmiHistory(View view) {

        Intent intent = new Intent(this, BmiHistory.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
