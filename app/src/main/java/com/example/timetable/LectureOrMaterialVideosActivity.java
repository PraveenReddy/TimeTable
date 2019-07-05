package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LectureOrMaterialVideosActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_or_material_videos);

        findViewById(R.id.button_lectures).setOnClickListener(this);
        findViewById(R.id.button_materials).setOnClickListener(this);
        findViewById(R.id.button_backAdmin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_english:
                Intent englishIntent = new Intent(this, LectureOrMaterialVideosActivity.class);
                startActivity(englishIntent);
                break;
            case R.id.button_hindi:
                Intent hindiIntent = new Intent(this, LectureOrMaterialVideosActivity.class);
                startActivity(hindiIntent);
                break;
            case R.id.button_backAdmin:
                Intent backAdminIntent = new Intent(this, InitialScreen.class);
                startActivity(backAdminIntent);
                break;
        }
    }
}
