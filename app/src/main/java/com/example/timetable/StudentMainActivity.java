package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.Faculty;
import com.example.timetable.mtimes.Samister;
import com.example.timetable.mtimes.Subject;

import java.util.ArrayList;


public class StudentMainActivity extends Activity implements View.OnClickListener {

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
        setContentView(R.layout.student_main);
        findViewById(R.id.button_viewTimeTable).setOnClickListener(this);
        findViewById(R.id.button_backStudent).setOnClickListener(this);
        mSpinnerSemister = (Spinner) findViewById(R.id.spinner_semister);
        mSpinnerSections = (Spinner) findViewById(R.id.spinner_sections);
        mSpinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        mSemisterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mSemisters);
        mSemisterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSemister.setAdapter(mSemisterAdapter);
        mTimeTableDatabase = DatabaseConnection.getInstance(StudentMainActivity.this, Constants.VERTION);
        mAdapterSections = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mSections);
        mSpinnerSections.setAdapter(mAdapterSections);
        mAdapterBrach = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mBranches);
        mSpinnerBranch.setAdapter(mAdapterBrach);
        mSpinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStringSection = mAdapterSections.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mBranch = mAdapterBrach.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerSemister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSession = mSemisterAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_viewTimeTable)
        {
            Intent intent2 = new Intent(this, SessionDetails.class);
            intent2.putExtra(Constants.SEMISTER, getSamister());
            startActivityForResult(intent2, REQUEST_TIMETABLE);

        }
        else {
            Intent i = new Intent(this, InitialScreen.class);
            startActivity(i);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public Samister getSamister() {
        Samister samister = new Samister();

        samister.setSection(mStringSection);
        samister.setBranch(mBranch);
        samister.setSem_no(Integer.valueOf(mSession));
        return samister;
    }
    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
    }
}
