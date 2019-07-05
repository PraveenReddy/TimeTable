package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by ndindialap4 on 13/9/15.
 */
public class InitialScreen extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);
        findViewById(R.id.bt_admin_login).setOnClickListener(this);
        findViewById(R.id.bt_student_login).setOnClickListener(this);
        findViewById(R.id.bt_register).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_admin_login:
                Intent adminLoginIntent = new Intent(this, LoginActivity.class);
                LoginPreferance.getInstance(InitialScreen.this).putLoginType(LoginType.ADMIN);
                startActivity(adminLoginIntent);
                break;
            case R.id.bt_student_login:
                Intent studentLoginIntent = new Intent(this, LoginActivity.class);
                LoginPreferance.getInstance(InitialScreen.this).putLoginType(LoginType.STUDENT);
                startActivity(studentLoginIntent);
                break;
            case R.id.bt_register:
                Intent registerIntent = new Intent(this, Register.class);
                startActivity(registerIntent);
                break;

        }

    }
    public void exitAppMethod(){

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //then you can call this method wherever you want. like

    @Override
    public void onBackPressed() {
        exitAppMethod();
    }
}
