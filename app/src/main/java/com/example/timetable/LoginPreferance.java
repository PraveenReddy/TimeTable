package com.example.timetable;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ndindialap4 on 13/9/15.
 */
public class LoginPreferance {


    private static LoginPreferance filterPreferences = null;

    private SharedPreferences preferences;
    private String FILE_NAME = "countries";

    private String LOGIN_TYPE = "login_type";


    public LoginPreferance(Context context) {
        preferences = context.getSharedPreferences(FILE_NAME, context.MODE_PRIVATE);
    }

    /* Returns the instance of filtersharedpreferences */
    public static LoginPreferance getInstance(Context context) {
        if (filterPreferences == null) {
            filterPreferences = new LoginPreferance(context);
        }
        return filterPreferences;
    }


    public void putLoginType(int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(LOGIN_TYPE, value);
        editor.commit();
    }

    public int getLoginType() {
        return preferences.getInt(LOGIN_TYPE, LoginType.ADMIN);
    }



}
