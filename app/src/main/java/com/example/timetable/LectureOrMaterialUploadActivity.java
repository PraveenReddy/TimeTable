package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LectureOrMaterialUploadActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_or_material);

        findViewById(R.id.button_lectures).setOnClickListener(this);
        findViewById(R.id.button_materials).setOnClickListener(this);
        findViewById(R.id.button_backAdmin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_lectures:
                Intent lecturesIntent = new Intent(this, LectureOrMaterialUploadActivity.class);
                startActivity(lecturesIntent);
                break;
            case R.id.button_materials:
                Intent materialsIntent = new Intent(this, LectureOrMaterialUploadActivity.class);
                startActivity(materialsIntent);
                break;
            case R.id.button_backAdmin:
                Intent backAdminIntent = new Intent(this, InitialScreen.class);
                startActivity(backAdminIntent);
                break;
        }
    }
}
