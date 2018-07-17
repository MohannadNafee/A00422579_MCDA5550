package com.example.mohan.bmiapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        com.example.maruf.bmi_calculator.InClassDatabaseHelper helper = new com.example.maruf.bmi_calculator.InClassDatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
// run a query
        Cursor cursor = db.query(com.example.maruf.bmi_calculator.InClassDatabaseHelper.TABLE_NAME,new String[]
                        {"NAME","PASSWORD", "HEALTH_CARD_NUMB", "DATE"},
                null,null,null,null,null); //
        if (cursor.moveToFirst()){
            String name = cursor.getString(0);
            EditText results = (EditText) findViewById(R.id.nameText);
            results.setText(name);

            String password = cursor.getString(1);
            EditText passwordResults = (EditText) findViewById(R.id.passwordText);
            passwordResults.setText(password);

            String healthText = cursor.getString(2);
            EditText healthResults = (EditText) findViewById(R.id.healthCardText);
            healthResults.setText(healthText);

            String dateText = cursor.getString(3);
            EditText dateResults = (EditText) findViewById(R.id.dateText);
            dateResults.setText(dateText);
        }
        cursor.close(); // cleanup
        db.close(); // cleanup
    }



    public void onClickEnter(View view) {
        Intent intent = new Intent(this, calculate_BMI.class);
        startActivity(intent);
    }



}
