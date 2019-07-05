package com.example.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.timetable.mtimes.Faculty;
import com.example.timetable.mtimes.Subject;

import java.util.ArrayList;

/**
 * Created by dharma on 9/10/2015.
 */
public class TimeTableDatabase extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "TIMETABLE";
    private static final String SUBJECT_NAME = "SUBJECT_NAME";
    private static final String SUBJECT_TABLE = "SUBJECT_TABLE";
    private static final String SUBJECT_CREATE_TABLE = "create table " + SUBJECT_TABLE + "(" + SUBJECT_NAME + " text)";
    private static final String FACULTY_NAME = "FACULTY_NAME";
    private static final String FACULTY_TABLE = "FACULTY_TABLE";
    private static final String FACULTY_CREATE_TABLE = "create table " + FACULTY_TABLE + "(" + FACULTY_NAME + " text)";
    private static final String FACULTY_ID = "FACULTY_ID";

    private static final String BRANCH = "BRANCH";
    private static final String SECTIONS = "SECTIONS";
    private static final String SESSION_ID = "SESSION_ID";
    private static final String SESSION_TABLE = "SESSION_TABLE";
    private static final String SESSION_CREATE_TABLE = "create table " + SESSION_TABLE + "(" + SESSION_ID + " text," + SUBJECT_NAME + " text," + FACULTY_ID + " long," + BRANCH + " text," + SECTIONS + " text," + FACULTY_NAME + " text)";

    public TimeTableDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SUBJECT_CREATE_TABLE);
        db.execSQL(FACULTY_CREATE_TABLE);
        db.execSQL(SESSION_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertSubject(String subject) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SUBJECT_NAME, subject);
        database.insert(SUBJECT_TABLE, null, contentValues);
    }

    public ArrayList<String> getSubjectsList() {
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + SUBJECT_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<String> list = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToNext()) {
                do {
                    list.add(cursor.getString(cursor.getColumnIndex(SUBJECT_NAME)));
                }
                while (cursor.moveToNext());
            }
        }
        return list;
    }

    public void insertFaculty(String faculty) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FACULTY_NAME, faculty);
        database.insert(FACULTY_TABLE, null, contentValues);
    }

    public ArrayList<Faculty> getFacultyList() {
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + FACULTY_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<Faculty> list = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToNext()) {
                do {
                    Faculty faculty = new Faculty();
                    faculty.setFacultyName(cursor.getString(cursor.getColumnIndex(FACULTY_NAME)));
                    faculty.setFacutlyId(cursor.getInt(0));
                    list.add(faculty);
                }
                while (cursor.moveToNext());
            }
        }

        Log.e("Fac", list + "");
        return list;
    }

    public void insertTimeTable(TimeTable timeTable) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FACULTY_NAME, timeTable.getFaculty().getFacultyName());
        contentValues.put(SUBJECT_NAME, timeTable.getSubject().getSubjectName());
        contentValues.put(SESSION_ID, timeTable.getId());
        contentValues.put(FACULTY_ID, timeTable.getFaculty().getFacutlyId());
        contentValues.put(SECTIONS, timeTable.getSection());
        contentValues.put(BRANCH, timeTable.getBranch());


        database.insert(SESSION_TABLE, null, contentValues);
    }

    public ArrayList<TimeTable> getSessions() {
        SQLiteDatabase database = getReadableDatabase();
        String query = "select * from " + SESSION_TABLE;
        Cursor cursor = database.rawQuery(query, null);
        ArrayList<TimeTable> list = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToNext()) {
                do {
                    TimeTable timeTable = new TimeTable();
                    timeTable.setId(cursor.getInt(0));
                    Subject subject=new Subject();
                    subject.setSubjectName(cursor.getString(cursor.getColumnIndex(SUBJECT_NAME)));
                    Faculty faculty=new Faculty();
                    faculty.setFacultyName(cursor.getString(cursor.getColumnIndex(FACULTY_NAME)));
                    faculty.setFacutlyId(cursor.getInt(cursor.getColumnIndex(FACULTY_ID)));
                    timeTable.setFaculty(faculty);
                    timeTable.setSubject(subject);
                    timeTable.setSemister_no(cursor.getInt(cursor.getColumnIndex(SESSION_ID)));
                    timeTable.setSection(cursor.getString(cursor.getColumnIndex(SECTIONS)));
                    timeTable.setBranch(cursor.getString(cursor.getColumnIndex(BRANCH)));
                    list.add(timeTable);
                }
                while (cursor.moveToNext());
            }
        }

        Log.e("Fac", list + "");
        return list;
    }

}
