package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.MainActivity1;


public class LoginActivity extends Activity implements View.OnClickListener {


    EditText email, password;
    private DatabaseConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.button_login).setOnClickListener(this);
        email = (EditText) findViewById(R.id.edittext_email);
        password = (EditText) findViewById(R.id.edittext_password);
        connection = DatabaseConnection.getInstance(this, Constants.VERTION);
    }

    @Override
    public void onClick(View v) {

        if (connection.isValidUser(LoginPreferance.getInstance(LoginActivity.this).getLoginType(), email.getText().toString(), password.getText().toString())) {

            if (LoginPreferance.getInstance(LoginActivity.this).getLoginType() == LoginType.STUDENT) {
//                        Intent intent = new Intent(this, StudentMainActivity.class);
                Intent intent = new Intent(this, ViewLectureVideosActivity.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);


            }
        } else {
            Toast.makeText(LoginActivity.this, "Invalid UserName or Password ", Toast.LENGTH_LONG).show();
        }


    }
}


