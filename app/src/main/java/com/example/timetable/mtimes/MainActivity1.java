package com.example.timetable.mtimes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timetable.AddFaculty;
import com.example.timetable.AddSubject;
import com.example.timetable.FacultyAdapter;
import com.example.timetable.LoginPreferance;
import com.example.timetable.LoginType;
import com.example.timetable.R;
import com.example.timetable.SessionDetails;
import com.example.timetable.SubjectAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity1 extends Activity implements View.OnClickListener {

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
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_viewTimeTable).setOnClickListener(this);
        mSpinnerSemister = (Spinner) findViewById(R.id.spinner_semister);
        mSpinnerSections = (Spinner) findViewById(R.id.spinner_sections);
        mSpinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        mSemisterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mSemisters);
        mSemisterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSemister.setAdapter(mSemisterAdapter);
        mTimeTableDatabase = DatabaseConnection.getInstance(MainActivity1.this, Constants.VERTION);
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

        if (LoginPreferance.getInstance(MainActivity1.this).getLoginType() == LoginType.STUDENT) {
            findViewById(R.id.ll_faculty).setVisibility(View.GONE);
            findViewById(R.id.ll_subject).setVisibility(View.GONE);

            findViewById(R.id.button_faculty).setVisibility(View.GONE);
            findViewById(R.id.button_subject).setVisibility(View.GONE);
            findViewById(R.id.button_submit).setVisibility(View.GONE);
            findViewById(R.id.button_generateTable).setVisibility(View.GONE);
        } else {

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

            findViewById(R.id.button_faculty).setOnClickListener(this);
            findViewById(R.id.button_subject).setOnClickListener(this);
            findViewById(R.id.button_submit).setOnClickListener(this);
            findViewById(R.id.button_generateTable).setOnClickListener(this);


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
        }
        update();

    }

    private void update() {
        updateSubject();
        updateFaculty();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_faculty:
                Intent intent = new Intent(this, AddFaculty.class);
                intent.putExtra(Constants.SEMISTER, getSamister());
                startActivityForResult(intent, REQUEST_FACULTY);
                break;
            case R.id.button_subject:
                Intent intent1 = new Intent(this, AddSubject.class);
                intent1.putExtra(Constants.SEMISTER, getSamister());
                startActivityForResult(intent1, REQUEST_SUBJECT);
                break;
            case R.id.button_submit:
                Samister timeTable = new Samister();
                timeTable.setSem_no(Integer.valueOf(mSession));
                timeTable.setSubjectId(mSubject.getSubjectId());
                timeTable.setFacultyId(mFaculty.getFacutlyId());
                timeTable.setBranch(mBranch);
                timeTable.setSection(mStringSection);
                mTimeTableDatabase.insert(timeTable);
                Toast.makeText(getApplicationContext(), "Session Created Successfully.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_viewTimeTable:
                Intent intent2 = new Intent(this, SessionDetails.class);
                intent2.putExtra(Constants.SEMISTER, getSamister());
                startActivityForResult(intent2, REQUEST_TIMETABLE);
                break;
            case R.id.button_generateTable:
                TtimeTableGenerator tableGenerator = new TtimeTableGenerator();
                tableGenerator.generateTimetable(getSamister(), MainActivity1.this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_FACULTY:
                    updateFaculty();
                    break;
                case REQUEST_SUBJECT:
                    updateSubject();
                    break;
            }
        }
    }

    private void updateSubject() {
        mArrayListSubject.clear();
        mArrayListSubject.addAll(mTimeTableDatabase.getSubjects(getSamister()));
        mAdapterSubject.notifyDataSetChanged();
    }

    private void updateFaculty() {
        mArrayListFaculty.clear();
        mArrayListFaculty.addAll(mTimeTableDatabase.getFaculties(getSamister()));
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
