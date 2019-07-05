package com.example.timetable;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.Samister;
import com.example.timetable.mtimes.Subject;


public class AddSubject extends Activity implements View.OnClickListener {

    private EditText mEditTextSubject;
    private Samister samister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        samister = (Samister) getIntent().getSerializableExtra(Constants.SEMISTER);
        mEditTextSubject = (EditText) findViewById(R.id.edittext_subject);
        findViewById(R.id.button_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_add) {
            String sub = mEditTextSubject.getText().toString();
            if (!TextUtils.isEmpty(sub)) {
                DatabaseConnection connection = DatabaseConnection.getInstance(AddSubject.this, Constants.VERTION);
                Subject subject = new Subject();
                subject.setSubjectName(sub);
                subject.setSem_no(samister.getSem_no());
                subject.setBranch(samister.getBranch());
                subject.setSection(samister.getSection());
                connection.insert(subject);
                Toast.makeText(getApplicationContext(), "Subject Added Successfully.", Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(AddSubject.this, "Please Enter Faculty.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
