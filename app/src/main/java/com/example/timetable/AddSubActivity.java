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
import com.example.timetable.mtimes.Samister;
import com.example.timetable.mtimes.Subject;


public class AddSubActivity extends Activity implements View.OnClickListener {

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
    private EditText mEtSubjectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        mSpinnerSemister = (Spinner) findViewById(R.id.spinner_semister);
        mSpinnerSections = (Spinner) findViewById(R.id.spinner_sections);
        mSpinnerBranch = (Spinner) findViewById(R.id.spinner_branch);
        mSemisterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mSemisters);
        mSemisterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerSemister.setAdapter(mSemisterAdapter);
        mEtSubjectName = (EditText) findViewById(R.id.edittext_subject);
        findViewById(R.id.button_add).setOnClickListener(this);
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
        if (v.getId() == R.id.button_add) {
            String sub = mEtSubjectName.getText().toString();
            if (!TextUtils.isEmpty(sub)) {
                DatabaseConnection connection = DatabaseConnection.getInstance(AddSubActivity.this, Constants.VERTION);
                Subject subject = new Subject();
                subject.setSubjectName(sub);
                subject.setSem_no(Integer.valueOf(mSession));
                subject.setBranch(mBranch);
                subject.setSection(mStringSection);
                connection.insert(subject);
                Toast.makeText(getApplicationContext(), "Subject Added Successfully.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(AddSubActivity.this, "Please Enter Faculty.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
