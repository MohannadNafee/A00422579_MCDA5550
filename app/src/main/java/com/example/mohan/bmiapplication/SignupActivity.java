package com.example.mohan.bmiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    EditText email_Text, password_Text, fullName_Text, date_Text, healthNo_Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email_Text = findViewById(R.id.emailText);
        password_Text = findViewById(R.id.passwordText);
        fullName_Text = findViewById(R.id.textFullName);
        date_Text = findViewById(R.id.dateText);
        healthNo_Text = findViewById(R.id.healthCardText);

        SetDatePick fromDate = new SetDatePick(date_Text, this);
    }


    public void registerButtonClicked(View view) {





        if (validateFields()) {
            InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
            if(helper.addUser(email_Text.getText().toString(),password_Text.getText().toString(),fullName_Text.getText().toString(),
                    healthNo_Text.getText().toString(),date_Text.getText().toString())) {
                Intent intent = new Intent(this, calculate_BMI.class);
                intent.putExtra("email", email_Text.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this,"User already exists! Try another!!!", Toast.LENGTH_LONG).show();
            }
        }


    }
    public void loginButtonClicked(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private Boolean validateFields() {
        Boolean isValid = true;
        if (email_Text.getText().toString().isEmpty()) {
            email_Text.setError("User is required!");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email_Text.getText().toString()).matches()){
            email_Text.setError("Invalid Email!");
            isValid = false;
        }
        if (password_Text.getText().toString().isEmpty()) {
            password_Text.setError("Password is required!");
            isValid = false;
        }
        if (fullName_Text.getText().toString().isEmpty()) {
            fullName_Text.setError("Name is required!");
            isValid = false;
        }
        if (healthNo_Text.getText().toString().isEmpty()) {
            healthNo_Text.setError("Health Card Number is required!");
            isValid = false;
        }
        if (date_Text.getText().toString().isEmpty()) {
            date_Text.setError("Date is required!");
            isValid = false;
        }

        return isValid;
    }

    // redirect to login page
    public void login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
