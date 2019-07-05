package com.example.timetable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;

/**
 * Created by ndindialap4 on 13/9/15.
 */
public class Register extends Activity {

    private DatabaseConnection connection;
    EditText mName, mPassword;
    RadioGroup mGroupLogin, mGroupGender;
    Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        connection = DatabaseConnection.getInstance(this, Constants.VERTION);

        mName = (EditText) findViewById(R.id.et_name);
        mPassword = (EditText) findViewById(R.id.et_password);
        mGroupGender = (RadioGroup) findViewById(R.id.rg_gender);
        mGroupLogin = (RadioGroup) findViewById(R.id.rg_usertype);
        mRegister = (Button) findViewById(R.id.bt_register);
        mRegister.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             register();
                                         }
                                     }

        );


    }

    private void register() {
        if (mName.getText().toString().trim().isEmpty() || mName.getText().toString().trim().length() <= 3) {
            Toast.makeText(Register.this, "Invalid UserName", Toast.LENGTH_LONG).show();
            return;
        }
        if (mPassword.getText().toString().trim().isEmpty() || mPassword.getText().toString().trim().length() <= 3) {
            Toast.makeText(Register.this, "Invalid Password", Toast.LENGTH_LONG).show();
            return;
        }

        connection.register(mName.getText().toString(), mPassword.getText().toString(), "666", mGroupLogin.getCheckedRadioButtonId() == R.id.rb_admin ? LoginType.ADMIN : LoginType.STUDENT, mGroupGender.getCheckedRadioButtonId() == R.id.rb_male ? 1 : 2);

        Toast.makeText(Register.this, "Successfully Registerd", Toast.LENGTH_LONG).show();

        finish();


    }
}
