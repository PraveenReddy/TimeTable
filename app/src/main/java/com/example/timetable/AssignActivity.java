package com.example.timetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.Faculty;
import com.example.timetable.mtimes.Samister;
import com.example.timetable.mtimes.Subject;

import java.util.ArrayList;
import java.util.List;


public class AssignActivity extends Activity implements View.OnClickListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aassign);
        mSpinnerSemister = (Spinner) findViewById(R.id.spinner_semister);
        mSpinnerSections = (Spinner) findViewById(R.id.spinner_sections);
        mSpinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        mSemisterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mSemisters);
        mSemisterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSemister.setAdapter(mSemisterAdapter);
        mTimeTableDatabase = DatabaseConnection.getInstance(AssignActivity.this, Constants.VERTION);
        mAdapterSections = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mSections);
        mSpinnerSections.setAdapter(mAdapterSections);
        mAdapterBrach = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mBranches);
        mSpinnerBranch.setAdapter(mAdapterBrach);
        mSpinnerSections.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mStringSection = mAdapterSections.getItem(position);
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSpinnerBranch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mBranch = mAdapterBrach.getItem(position);
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerSemister.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSession = mSemisterAdapter.getItem(position);
                update();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mSpinnerSubject = (Spinner) findViewById(R.id.spinner_subject);
        mSpinnerFaculty = (Spinner) findViewById(R.id.spinner_faculty);


        mArrayListFaculty = new ArrayList<>();
        mArrayListSubject = new ArrayList<>();


        Samister samister = getSamister();
        List<Faculty> faculties = mTimeTableDatabase.getFaculties(samister);
        mArrayListFaculty.addAll(faculties);
        mArrayListSubject.addAll(mTimeTableDatabase.getSubjects(samister));

        mAdapterSubject = new SubjectAdapter(this, mArrayListSubject);

        mAdapterFaculty = new FacultyAdapter(this, mArrayListFaculty);

        mSpinnerFaculty.setAdapter(mAdapterFaculty);
        mSpinnerSubject.setAdapter(mAdapterSubject);

        Log.e("Data ", mArrayListFaculty.size() + "");


        findViewById(R.id.button_submit).setOnClickListener(this);


        mSpinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mSubject = (Subject) mAdapterSubject.getItem(position);
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mSpinnerFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                mFaculty = (Faculty) mAdapterFaculty.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        update();

    }

    private void clear() {
        mSubject = null;
        mFaculty = null;
        mArrayListFaculty.clear();
        mArrayListSubject.clear();
    }

    private void update() {
        Log.d(mFaculty + "fffffffff  brrrrrrr : ", mArrayListFaculty + "");
        Log.d(mSubject + "fffffffff brrrrrrrrr : ", mArrayListSubject + "");
        updateSubject();
        updateFaculty();

        Log.d(mFaculty + "fffffffff : ", mArrayListFaculty + "");
        Log.d(mSubject + "fffffffff : ", mArrayListSubject + "");
    }

    @Override
    public void onClick(View v) {

        if (mSubject == null) {
            Toast.makeText(getApplicationContext(), "please  Add subjects.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mFaculty == null) {
            Toast.makeText(getApplicationContext(), "please  Add Faculty.", Toast.LENGTH_SHORT).show();
            return;
        }

        Samister timeTable = new Samister();
        timeTable.setSem_no(Integer.valueOf(mSession));
        timeTable.setSubjectId(mSubject.getSubjectId());
        timeTable.setFacultyId(mFaculty.getFacutlyId());
        timeTable.setBranch(mBranch);
        timeTable.setSection(mStringSection);
        mTimeTableDatabase.insert(timeTable);
        Toast.makeText(getApplicationContext(), mSubject.getSubjectName() + " Assined  Successfully.", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateSubject() {
        mArrayListSubject.clear();
        mArrayListSubject.addAll(mTimeTableDatabase.getSubjects(getSamister()));
        if (mArrayListFaculty.size() == 0) {
            mFaculty = null;
        }
        mAdapterSubject.notifyDataSetChanged();
    }


    private void updateFaculty() {
        mArrayListFaculty.clear();
        mArrayListFaculty.addAll(mTimeTableDatabase.getFaculties(getSamister()));
        if (mArrayListSubject.size() == 0) {
            mSubject = null;
        }
        mAdapterFaculty.notifyDataSetChanged();
    }


    public Samister getSamister() {


        Samister samister = new Samister();

        samister.setSection(mStringSection);
        samister.setBranch(mBranch);
        samister.setSem_no(Integer.valueOf(mSession));
        return samister;
    }
}
