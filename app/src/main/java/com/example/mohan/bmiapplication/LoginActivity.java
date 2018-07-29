package com.example.mohan.bmiapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void signIn(View view) {
        EditText user = (EditText) findViewById(R.id.emailText);
        EditText password = (EditText) findViewById(R.id.passwordText);
        InClassDatabaseHelper helper = new InClassDatabaseHelper(this);
        if(helper.checkUser(user.getText().toString(), password.getText().toString())) {
            Intent intent = new Intent(this, calculate_BMI.class);
            intent.putExtra("email", user.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Invalid Email or Password!",
                    Toast.LENGTH_LONG).show();
        }

    }
    public void register(View view) {
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
