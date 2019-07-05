package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import com.example.timetable.mtimes.Samister;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.Faculty;
import com.example.timetable.mtimes.Subject;






public class MainActivity extends Activity implements View.OnClickListener {

    private Spinner mSpinnerSemister, mSpinnerBranch, mSpinnerFaculty, mSpinnerSubject;
    private String[] mSemisters = {1 + "", 2 + "", 3 + "", 4 + "", 5 + "", 6 + "", 7 + "", 8 + ""};
    private ArrayAdapter<String> mSemisterAdapter;
    private ArrayList<Subject> mArrayListSubject;
    private ArrayList<Faculty> mArrayListFaculty;
    private static final int REQUEST_SUBJECT = 51;
    private static final int REQUEST_FACULTY = 52, REQUEST_TIMETABLE = 53;

    private DatabaseConnection mTimeTableDatabase;
    private SubjectAdapter mAdapterSubject;
    private FacultyAdapter mAdapterFaculty;
    private String mSession = "1", mBranch = "CSE";
    private Subject mSubject;

    private Faculty mFaculty;
    private Spinner mSpinnerSections;
    private String[] mSections = {"A", "B"};
    private String[] mBranches = {"CSE", "ECE", "MECH", "EIE", "ECE", "CIVIL", "IT"};
    private ArrayAdapter<String> mAdapterSections;
    private ArrayAdapter<String> mAdapterBrach;

    private String mStringSection = "A";
    private Samister samister;

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        findViewById(R.id.button_viewTimeTable).setOnClickListener(this);
        findViewById(R.id.button_faculty).setOnClickListener(this);
        findViewById(R.id.button_subject).setOnClickListener(this);
        findViewById(R.id.button_submit).setOnClickListener(this);
        findViewById(R.id.button_generateTable).setOnClickListener(this);
        findViewById(R.id.button_lecture_classes).setOnClickListener(this);
        findViewById(R.id.button_lecture_materials).setOnClickListener(this);
        findViewById(R.id.button_backAdmin).setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_faculty:
                Intent facultyIntent = new Intent(this, AddFacultyActivity.class);
                startActivityForResult(facultyIntent, REQUEST_FACULTY);
                break;
            case R.id.button_subject:
                Intent addSubjectIntent = new Intent(this, AddSubActivity.class);
                startActivityForResult(addSubjectIntent, REQUEST_SUBJECT);
                break;
            case R.id.button_submit:
                Intent assignIntent = new Intent(this, AssignActivity.class);
                startActivityForResult(assignIntent, REQUEST_TIMETABLE);
                break;

            case R.id.button_viewTimeTable:
                Intent viewTimeIntent = new Intent(this, StudentMainActivity.class);
                startActivityForResult(viewTimeIntent, REQUEST_TIMETABLE);
                break;
            case R.id.button_generateTable:
                Intent generateTableIntent = new Intent(this, GenerateActivity.class);
                startActivityForResult(generateTableIntent, REQUEST_TIMETABLE);
                break;
            case R.id.button_lecture_classes:
                Intent classesIntent = new Intent(this, AddVideoLecturesActivity.class);
                startActivity(classesIntent);
                break;
            case R.id.button_lecture_materials:
                Intent materialsIntent = new Intent(this, AddVideoLecturesActivity.class);
                startActivity(materialsIntent);
                break;
            case R.id.button_backAdmin:
                Intent backAdminIntent = new Intent(this, InitialScreen.class);
                startActivity(backAdminIntent);
                break;
        }
    }
}
