package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewLectureVideosActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_lecture_videos);

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
                Intent class8thIntent = new Intent(this, ViewSubjectVideosActivity.class);
                startActivity(class8thIntent);
                break;
            case R.id.button_9th:
                Intent class9thIntent = new Intent(this, ViewSubjectVideosActivity.class);
                startActivity(class9thIntent);
                break;
            case R.id.button_10th:
                Intent class10thIntent = new Intent(this, ViewSubjectVideosActivity.class);
                startActivity(class10thIntent);
                break;
            case R.id.button_inter1:
                Intent classInter1Intent = new Intent(this, ViewSubjectVideosActivity.class);
                startActivity(classInter1Intent);
                break;
            case R.id.button_inter2:
                Intent classInter2Intent = new Intent(this, ViewSubjectVideosActivity.class);
                startActivity(classInter2Intent);
                break;
            case R.id.button_backAdmin:
                Intent backAdminIntent = new Intent(this, InitialScreen.class);
                startActivity(backAdminIntent);
                break;
        }
    }
}
