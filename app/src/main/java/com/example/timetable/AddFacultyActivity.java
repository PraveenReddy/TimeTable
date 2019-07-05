package com.example.timetable;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.Faculty;
import com.example.timetable.mtimes.Samister;


public class AddFacultyActivity extends Activity implements View.OnClickListener {

    private Spinner mSpinnerSemister, mSpinnerBranch;
    private String[] mSemisters = {1 + "", 2 + "", 3 + "", 4 + "", 5 + "", 6 + "", 7 + "", 8 + ""};
    private ArrayAdapter<String> mSemisterAdapter;
    private String mSession = "1", mBranch = "CSE";
    private Spinner mSpinnerSections;
    private String[] mSections = {"A", "B"};
    private String[] mBranches = {"CSE", "ECE", "MECH", "EIE", "ECE", "CIVIL", "IT"};
    private ArrayAdapter<String> mAdapterSections;
    private ArrayAdapter<String> mAdapterBrach;
    private String mStringSection = "A";
    private Samister samister;
    private EditText mEtFacultyName, mEtFacultyExp, mEtFacultyQual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        mSpinnerSemister = (Spinner) findViewById(R.id.spinner_semister);
        mSpinnerSections = (Spinner) findViewById(R.id.spinner_sections);
        mSpinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        mSemisterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mSemisters);
        mSemisterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSemister.setAdapter(mSemisterAdapter);
        mEtFacultyName = (EditText) findViewById(R.id.edittext_faculty);
        mEtFacultyExp = (EditText) findViewById(R.id.et_exp);
        mEtFacultyQual = (EditText) findViewById(R.id.et_qualification);
        findViewById(R.id.button_addfaculty).setOnClickListener(this);
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


        String facName = mEtFacultyName.getText().toString();
        String facQualification = mEtFacultyQual.getText().toString();
        String facExp = mEtFacultyExp.getText().toString();
         if (!TextUtils.isEmpty(facName)) {
             facName = facName + Constants.SUP + facQualification + Constants.SUP + facExp;
             DatabaseConnection connection = DatabaseConnection.getInstance(AddFacultyActivity.this, Constants.VERTION);
            Faculty faculty = new Faculty();
            faculty.setFacultyName(facName);
            faculty.setSem_no(Integer.valueOf(mSession));
            faculty.setBranch(mBranch);
            faculty.setSection(mStringSection);

            connection.insert(faculty);
            Toast.makeText(getApplicationContext(), "Faculty Added Successfully.", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            finish();
        } else {
            Toast.makeText(AddFacultyActivity.this, "Please Enter Faculty.", Toast.LENGTH_SHORT).show();
        }

    }


}
