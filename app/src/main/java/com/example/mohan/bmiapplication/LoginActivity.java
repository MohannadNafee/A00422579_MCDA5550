package com.example.mohan.bmiapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       }

       // get input emamil and password from user
    public void signIn(View view) {
        EditText user = (EditText) findViewById(R.id.emailText);
        EditText password = (EditText) findViewById(R.id.passwordText);
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        if (validateFields()){
            if(helper.checkUser(user.getText().toString(), password.getText().toString())) {
                Intent intent = new Intent(this, calculate_BMI.class);
                intent.putExtra("email", user.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid Email or Password!",
                        Toast.LENGTH_LONG).show();
            }
        }


    }

    // Validation for login page
    public boolean validateFields() {
        Boolean isvalid = true;
        EditText emailText = findViewById(R.id.emailText);
        EditText passText = findViewById(R.id.passwordText);
        if (emailText.getText().toString().isEmpty()) {
            emailText.setError("Please insert email!");
            isvalid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailText.getText().toString()).matches()){
            emailText.setError("Invalid Email!");
            isvalid = false;
        }
        if (passText.getText().toString().isEmpty()) {
            passText.setError("Please insert email!");
            isvalid = false;
        }
        return isvalid;
    }

    // redirect to sign up page
    public void register(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

}

//mohannad.nafee@gmail.com
//