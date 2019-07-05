package com.example.timetable;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.Faculty;
import com.example.timetable.mtimes.Samister;


public class AddFaculty extends Activity implements View.OnClickListener {

    private EditText mEditTextSubject;
    Samister samister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        samister = (Samister) getIntent().getSerializableExtra(Constants.SEMISTER);
        mEditTextSubject = (EditText) findViewById(R.id.edittext_faculty);
        findViewById(R.id.button_addfaculty).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_addfaculty) {
            String facName = mEditTextSubject.getText().toString();
            if (!TextUtils.isEmpty(facName)) {
                DatabaseConnection connection = DatabaseConnection.getInstance(AddFaculty.this, Constants.VERTION);
                Faculty faculty = new Faculty();
                faculty.setFacultyName(facName);
                faculty.setSem_no(samister.getSem_no());
                faculty.setBranch(samister.getBranch());
                faculty.setSection(samister.getSection());
                connection.insert(faculty);
                Toast.makeText(getApplicationContext(), "Faculty Added Successfully.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(AddFaculty.this, "Please Enter Faculty.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
