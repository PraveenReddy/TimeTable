package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SubjectVideosUploadActvity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_videos_upload_actvity);

        findViewById(R.id.button_maths).setOnClickListener(this);
        findViewById(R.id.button_physics).setOnClickListener(this);
        findViewById(R.id.button_chemistry).setOnClickListener(this);
        findViewById(R.id.button_english).setOnClickListener(this);
        findViewById(R.id.button_hindi).setOnClickListener(this);
        findViewById(R.id.button_backAdmin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_maths:
                Intent mathsIntent = new Intent(this, LectureOrMaterialUploadActivity.class);
                startActivity(mathsIntent);
                break;
            case R.id.button_physics:
                Intent physicsIntent = new Intent(this, LectureOrMaterialUploadActivity.class);
                startActivity(physicsIntent);
                break;
            case R.id.button_chemistry:
                Intent chemistryIntent = new Intent(this, LectureOrMaterialUploadActivity.class);
                startActivity(chemistryIntent);
                break;
            case R.id.button_english:
                Intent englishIntent = new Intent(this, LectureOrMaterialUploadActivity.class);
                startActivity(englishIntent);
                break;
            case R.id.button_hindi:
                Intent hindiIntent = new Intent(this, LectureOrMaterialUploadActivity.class);
                startActivity(hindiIntent);
                break;
            case R.id.button_backAdmin:
                Intent backAdminIntent = new Intent(this, InitialScreen.class);
                startActivity(backAdminIntent);
                break;
        }
    }
}
