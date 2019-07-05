package com.example.timetable;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.timetable.mtimes.Constants;
import com.example.timetable.mtimes.DatabaseConnection;
import com.example.timetable.mtimes.Periods;
import com.example.timetable.mtimes.Samister;
import com.example.timetable.mtimes.Subject;

import java.util.ArrayList;
import java.util.List;


public class SessionDetails extends Activity {

    private DatabaseConnection mTimeTableDatabase;
    private TimeTableAdapter mTimeTableAdapter;
    private ArrayList<List<Periods>> mArrayListTimeTables;
    private static final String[] mDays = {"Tim", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private ArrayList<TimeTable> mTimeTables;
    private Periods[][] mArrayTimeTables = new Periods[Constants.MAX_DAYS + 1][Constants.MAX_PERIODS + 1];
    private Samister samister;
    private List<Periods> periods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_detail);
        mTimeTables = new ArrayList<>();
        mTimeTableDatabase = DatabaseConnection.getInstance(SessionDetails.this, Constants.VERTION);
        mArrayListTimeTables = new ArrayList<>();
        samister = (Samister) getIntent().getSerializableExtra(Constants.SEMISTER);
        ListView gridView = (ListView) findViewById(R.id.gridView);
        mTimeTableAdapter = new TimeTableAdapter(this, mArrayListTimeTables);
        gridView.setAdapter(mTimeTableAdapter);
        initData();
        // generateTimeTable();

    }


    private void initData() {
        loadDays();
    }


    private void loadDays() {
        for (int i = 0; i < mDays.length; i++) {
            Periods timeTable = new Periods();
            Subject subject = new Subject();
            subject.setSubjectName(mDays[i]);
            timeTable.setSubject(subject);
            mArrayTimeTables[0][i] = timeTable;
        }
        //periods=DatabaseConnection.getInstance(getApplication(),Constants.VERTION).getPeriods(samister);

        for (int i = 1; i < mArrayTimeTables.length; i++) {
            for (int j = 1; j < mArrayTimeTables[i].length; j++) {
                Periods periods = DatabaseConnection.getInstance(getApplication(), Constants.VERTION).getPeriods(samister, i - 1, j - 1);
                mArrayTimeTables[i][j] = periods;
            }

        }


        for (int i = 1; i < Constants.MAX_PERIODS + 1; i++) {
            Periods timeTable = new Periods();
            Subject subject = new Subject();
            subject.setSubjectName(" " + (i) + " ");
            timeTable.setSubject(subject);
            mArrayTimeTables[i][0] = timeTable;
        }

        for (int i = 0; i < mArrayTimeTables.length; i++) {
            List<Periods> sublist=new ArrayList<>();
            for (int j = 0; j < Constants.MAX_PERIODS; j++) {
                sublist.add(mArrayTimeTables[i][j]);
            }

            mArrayListTimeTables.add(sublist);

        }
        mTimeTableAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTimeTableAdapter.notifyDataSetChanged();
    }



}
