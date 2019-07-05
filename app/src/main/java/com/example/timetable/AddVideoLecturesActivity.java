package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddVideoLecturesActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video_lectures);

        findViewById(R.id.button_8th).setOnClickListener(this);
        findViewById(R.id.button_9th).setOnClickListener(this);
        findViewById(R.id.button_10th).setOnClickListener(this);
        findViewById(R.id.button_inter1).setOnClickListener(this);
        findViewById(R.id.button_inter2).setOnClickListener(this);
        findViewById(R.id.button_backAdmin).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_8th:
                Intent i1 = new Intent(this, SubjectVideosUploadActvity.class);
                startActivity(i1);
                break;
            case R.id.button_9th:
                Intent i2 = new Intent(this, SubjectVideosUploadActvity.class);
                startActivity(i2);
                break;
            case R.id.button_10th:
                Intent i3 = new Intent(this, SubjectVideosUploadActvity.class);
                startActivity(i3);
                break;
            case R.id.button_inter1:
                Intent i4 = new Intent(this, SubjectVideosUploadActvity.class);
                startActivity(i4);
                break;
            case R.id.button_inter2:
                Intent i5 = new Intent(this, SubjectVideosUploadActvity.class);
                startActivity(i5);
                break;
            case R.id.button_backAdmin:
                Intent i = new Intent(this, InitialScreen.class);
                startActivity(i);
                break;
        }
    }
}
