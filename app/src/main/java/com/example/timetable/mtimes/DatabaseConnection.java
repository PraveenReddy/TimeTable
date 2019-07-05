package com.example.timetable.mtimes;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;



public class DatabaseConnection extends SQLiteOpenHelper {

    private static int ID = 0;
    private static final String DB_NAME = "timetabe";
    private static final String TAG = DatabaseConnection.class.getName();

    private static DatabaseConnection databaseConnection;


    private static String T_SUBJECT = "CREATE TABLE " + DB_Constants.SUBJECT_TABLE + " ( " + DB_Constants.SUBJECT_ID + " int AUTO INCREMENT,"
            + DB_Constants.SEM_NO + " int," + DB_Constants.SEM_SECTION + " varchar," + DB_Constants.SEM_BRANCH + " varchar," + DB_Constants.SUBJECT_NAME + " varchar,primary key(" + DB_Constants.SEM_NO + "," + DB_Constants.SEM_SECTION + " ," + DB_Constants.SEM_BRANCH + "," + DB_Constants.SUBJECT_NAME + "))";

    private static String T_REGISTER = "CREATE TABLE " + DB_Constants.REGISTER_TABLE + " ( " + DB_Constants.REG_ID + " int AUTO INCREMENT,"
            + DB_Constants.NAME + " varchar," + DB_Constants.PASSWORD + " varchar," + DB_Constants.AGE + " varchar," + DB_Constants.GENDER + " int," + DB_Constants.LOGIN_TYPE + " int,primary key(" + DB_Constants.LOGIN_TYPE + "," + DB_Constants.NAME + "))";

    private static String T_FACULTY = "CREATE TABLE " + DB_Constants.FACULTY_TABLE + " ( " + DB_Constants.FACULTY_ID + " int AUTO INCREMENT,"
            + DB_Constants.SEM_NO + " int," + DB_Constants.SEM_SECTION + " varchar," + DB_Constants.SEM_BRANCH + " varchar," + DB_Constants.FACULTY_NAME + " varchar PRIMARY KEY)";
    private static String T_SEMESTER = "CREATE TABLE " + DB_Constants.SEMESTER_TABLE + " ( " + DB_Constants.SEM_ID + " int AUTO INCREMENT,"
            + DB_Constants.SEM_FAC_ID + " int," + DB_Constants.SEM_SUB_ID + " int," + DB_Constants.SEM_NO + " int," + DB_Constants.SEM_SECTION + " varchar," + DB_Constants.SEM_BRANCH + " varchar,primary key(" + DB_Constants.SEM_SUB_ID + ","  + DB_Constants.SEM_FAC_ID +","+ DB_Constants.SEM_NO + "," + DB_Constants.SEM_SECTION + "," + DB_Constants.SEM_BRANCH + "))";

    private static String T_PERIOD = "CREATE TABLE " + DB_Constants.PERIOD_TABLE + " ( " + DB_Constants.PERIOD_ID + " int AUTO INCREMENT,"
            + DB_Constants.PERIOD_NO + " int," + DB_Constants.PERIOD_DAY + " int," + DB_Constants.P_SEM + " int)";


    public static DatabaseConnection getInstance(Context context, int version) {
        if (databaseConnection == null) {
            databaseConnection = new DatabaseConnection(context, version);
        }
        return databaseConnection;
    }

    public int getID() {
        if (ID > 30000)
            ID = 0;
        return ++ID;
    }


    private Subject readSubjectFromCusor(Cursor c) {
        Subject subject = new Subject();
        subject.setSubjectId(c.getInt(c.getColumnIndex(DB_Constants.SUBJECT_ID)));
        subject.setSubjectName(c.getString(c.getColumnIndex(DB_Constants.SUBJECT_NAME)));
        subject.setSection(c.getString(c.getColumnIndex(DB_Constants.SEM_SECTION)));
        subject.setSem_no(c.getInt(c.getColumnIndex(DB_Constants.SEM_NO)));
        subject.setBranch(c.getString(c.getColumnIndex(DB_Constants.SEM_BRANCH)));
        return subject;
    }

    public ContentValues getSubjectContentValues(Subject subject) {
        ContentValues values = new ContentValues();
        values.put(DB_Constants.SUBJECT_ID, getID());
        values.put(DB_Constants.SUBJECT_NAME, subject.getSubjectName());
        values.put(DB_Constants.SEM_BRANCH, subject.getBranch());
        values.put(DB_Constants.SEM_SECTION, subject.getSection());
        values.put(DB_Constants.SEM_NO, subject.getSem_no());
        return values;
    }

    private Faculty readFacultyFromCusor(Cursor c) {
        Faculty faculty = new Faculty();
        faculty.setFacutlyId(c.getInt(c.getColumnIndex(DB_Constants.FACULTY_ID)));
        faculty.setFacultyName(c.getString(c.getColumnIndex(DB_Constants.FACULTY_NAME)));
        faculty.setSection(c.getString(c.getColumnIndex(DB_Constants.SEM_SECTION)));
        faculty.setSem_no(c.getInt(c.getColumnIndex(DB_Constants.SEM_NO)));
        faculty.setBranch(c.getString(c.getColumnIndex(DB_Constants.SEM_BRANCH)));


        return faculty;
    }

    public ContentValues getFacultyContentValues(Faculty faculty) {
        ContentValues values = new ContentValues();
        values.put(DB_Constants.FACULTY_ID, getID());
        values.put(DB_Constants.FACULTY_NAME, faculty.getFacultyName());
        values.put(DB_Constants.SEM_BRANCH, faculty.getBranch());
        values.put(DB_Constants.SEM_SECTION, faculty.getSection());
        values.put(DB_Constants.SEM_NO, faculty.getSem_no());
        return values;
    }

    private Samister readSemisterFacultyFromCusor(Cursor c) {
        Samister samister = new Samister();
        samister.setSubjectId(c.getInt(c.getColumnIndex(DB_Constants.SEM_SUB_ID)));
        samister.setBranch(c.getString(c.getColumnIndex(DB_Constants.SEM_BRANCH)));
        samister.setFacultyId(c.getInt(c.getColumnIndex(DB_Constants.SEM_FAC_ID)));
        samister.setSection(c.getString(c.getColumnIndex(DB_Constants.SEM_SECTION)));
        samister.setSemisterId(c.getInt(c.getColumnIndex(DB_Constants.SEM_ID)));
        return samister;
    }

    public ContentValues getSemisterValues(Samister samister) {
        ContentValues values = new ContentValues();

        values.put(DB_Constants.SEM_ID, getID());
        values.put(DB_Constants.SEM_BRANCH, samister.getBranch());
        values.put(DB_Constants.SEM_FAC_ID, samister.getFacultyId());
        values.put(DB_Constants.SEM_NO, samister.getSem_no());
        values.put(DB_Constants.SEM_SUB_ID, samister.getSubjectId());
        values.put(DB_Constants.SEM_SECTION, samister.getSection());
        return values;
    }

    private Periods readPeriodFacultyFromCusor(Cursor c) {
        Periods periods = new Periods();
        periods.setPeriodNo(c.getInt(c.getColumnIndex(DB_Constants.PERIOD_NO)));
        periods.setDay(c.getInt(c.getColumnIndex(DB_Constants.PERIOD_DAY)));
        periods.setSem_id(c.getInt(c.getColumnIndex(DB_Constants.P_SEM)));
        return periods;
    }

    public ContentValues getPeriodValues(Periods period) {
        ContentValues values = new ContentValues();
        values.put(DB_Constants.PERIOD_ID, getID());
        values.put(DB_Constants.PERIOD_NO, period.getPeriodNo());
        values.put(DB_Constants.P_SEM, period.getSem_id());
        values.put(DB_Constants.PERIOD_DAY, period.getDay());
        return values;
    }

    public DatabaseConnection(Context context, int version) {
        this(context, DB_NAME, null, version, null);
    }


    public DatabaseConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, factory, version, null);
    }

    public DatabaseConnection(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version);
    }

    public void insert(Subject subject) {
        ContentValues contentValues = getSubjectContentValues(subject);
        Log.d(TAG, contentValues.toString());
        SQLiteDatabase db = getWritableDatabase();
        try {
            long res = db.insert(DB_Constants.SUBJECT_TABLE, null, contentValues);
            Log.d(TAG, res + " : ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void insert(Periods periods) {
        ContentValues contentValues = getPeriodValues(periods);
        SQLiteDatabase db = getWritableDatabase();
        try {
            long res = db.insert(DB_Constants.PERIOD_TABLE, null, contentValues);
            Log.d(TAG, periods + " res :  pere " + res + contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    public void insert(Faculty faculty) {
        ContentValues contentValues = getFacultyContentValues(faculty);
        Log.d(TAG, contentValues.toString());
        SQLiteDatabase db = getWritableDatabase();
        try {
            long res = db.insert(DB_Constants.FACULTY_TABLE, null, contentValues);
            Log.d(TAG, res + " :  faculty  ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }


    public void insert(Samister samister) {
        ContentValues contentValues = getSemisterValues(samister);
        SQLiteDatabase db = getWritableDatabase();
        Log.d(TAG, contentValues.toString());
        try {
            long res = db.insert(DB_Constants.SEMESTER_TABLE, null, contentValues);
            Log.d(TAG, res + " :  semister  " + contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(T_FACULTY);
        db.execSQL(T_PERIOD);
        db.execSQL(T_SEMESTER);
        db.execSQL(T_SUBJECT);
        db.execSQL(T_REGISTER);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(getDropQuery(DB_Constants.PERIOD_TABLE));
        db.execSQL(getDropQuery(DB_Constants.SEMESTER_TABLE));
        db.execSQL(getDropQuery(DB_Constants.SUBJECT_TABLE));
        db.execSQL(getDropQuery(DB_Constants.FACULTY_TABLE));
        db.execSQL(getDropQuery(DB_Constants.REGISTER_TABLE));
        onCreate(db);
    }

    public List<Subject> getSubjects(Samister samister, Faculty faculty) {
        String sql = "select  * from " + DB_Constants.SUBJECT_TABLE + "  where " + DB_Constants.SUBJECT_ID + " in( select " + DB_Constants.SEM_SUB_ID + " from " + DB_Constants.SEMESTER_TABLE + " where " + DB_Constants.SEM_FAC_ID + "=" + faculty.getFacutlyId() + " and " + DB_Constants.SEM_NO + "= " + samister.getSem_no() + "  and " + DB_Constants.SEM_BRANCH + "='" + samister.getBranch() + "' and " + DB_Constants.SEM_SECTION + "='" + samister.getSection() + "')";
        Log.d(TAG, sql);

        return getSubjectsList(sql);
    }

    public List<Subject> getSubjects(Samister samister) {
        String sql = "select  * from " + DB_Constants.SUBJECT_TABLE + "  where  " + DB_Constants.SEM_NO + " = " + samister.getSem_no() + "  and " + DB_Constants.SEM_BRANCH + " ='" + samister.getBranch() + "' and " + DB_Constants.SEM_SECTION + "='" + samister.getSection() + "'";
        //sql = "select  * from " + DB_Constants.SUBJECT_TABLE;
        return getSubjectsList(sql);
    }

    private List<Subject> getSubjectsList(String sql) {

        List<Subject> subjects = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        try {

            Cursor cur = db.rawQuery(sql, null);
            if (cur.moveToFirst()) {
                do {
                    subjects.add(readSubjectFromCusor(cur));
                } while (cur.moveToNext());
            }

        } catch (Exception e) {

        } finally {
            db.close();
        }

        Log.d(TAG, sql + " Subjects : " + subjects);

        return subjects;
    }

    private List<Faculty> getFacultyList(String sql) {

        List<Faculty> faculties = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cur = db.rawQuery(sql, null);
            if (cur.moveToFirst()) {
                do {
                    faculties.add(readFacultyFromCusor(cur));
                } while (cur.moveToNext());
            }

        } catch (Exception e) {

        } finally {
            db.close();
        }

        Log.d(TAG, sql + " facult : " + faculties);
        return faculties;
    }

    private List<Periods> getperiodsList(String sql) {

        List<Periods> subjects = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cur = db.rawQuery(sql, null);
            if (cur.moveToFirst()) {
                do {
                    subjects.add(readPeriodFacultyFromCusor(cur));
                } while (cur.moveToNext());
            }

        } catch (Exception e) {

        } finally {
            db.close();
        }

        return subjects;
    }

    private List<TimeTable> getTimeTableList(String sql) {

        List<TimeTable> subjects = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cur = db.rawQuery(sql, null);
            if (cur.moveToFirst()) {
                do {
                    subjects.add(readTimetableFromCusor(cur));
                } while (cur.moveToNext());
            }

        } catch (Exception e) {

        } finally {
            db.close();
        }

        return subjects;
    }


    private TimeTable readTimetableFromCusor(Cursor cur) {


        return null;
    }

    private List<Samister> getSamisterList(String sql) {

        List<Samister> subjects = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        try {
            Cursor cur = db.rawQuery(sql, null);
            if (cur.moveToFirst()) {
                do {
                    subjects.add(readSemisterFacultyFromCusor(cur));
                } while (cur.moveToNext());
            }

        } catch (Exception e) {

        } finally {
            db.close();
        }
        return subjects;
    }

    public List<Faculty> getFaculties(Samister samister) {
        String sql = "select  * from " + DB_Constants.FACULTY_TABLE + "  where " + DB_Constants.SEM_NO + " = " + samister.getSem_no() + "  and " + DB_Constants.SEM_BRANCH + "='" + samister.getBranch() + "' and " + DB_Constants.SEM_SECTION + "='" + samister.getSection() + "'";
        return getFacultyList(sql);
    }

    public List<TimeTable> getTimetables(Samister samister) {
        return null;
    }

    private String getDropQuery(String tableName) {
        return "DROP TABLE  " + tableName;
    }


    public boolean isExisted(int periodCount, int dayCount, int facultyid) {

        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "select count(*) cnt from " + DB_Constants.PERIOD_TABLE + " where " + DB_Constants.PERIOD_DAY + "=" + dayCount + " and " + DB_Constants.PERIOD_NO + "=" + periodCount + " and " + DB_Constants.P_SEM + " in( select " + DB_Constants.SEM_ID + " from " + DB_Constants.SEMESTER_TABLE + " where  " + DB_Constants.SEM_FAC_ID + "=" + facultyid + ")";
            Log.d(TAG, "   ggggggggggggggg  " + sql);
            Cursor cur = db.rawQuery(sql, null);
            if (cur.moveToFirst()) {
                Log.d(TAG, "true true");
                return cur.getInt(cur.getColumnIndex("cnt")) > 0 ? true : false;
            }

        } catch (Exception e) {

        } finally {
            db.close();
        }
        return false;
    }

    public int getSemId(Samister sam) {

        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "select " + DB_Constants.SEM_ID + " from " + DB_Constants.SEMESTER_TABLE + " where " + DB_Constants.SEM_FAC_ID + "=" + sam.getFacultyId() + " and " + DB_Constants.SEM_SUB_ID + "=" + sam.getSubjectId() + " and " + DB_Constants.SEM_NO + "=" + sam.getSem_no() + " and " + DB_Constants.SEM_BRANCH + "='" + sam.getBranch() + "' and " + DB_Constants.SEM_SECTION + " = '" + sam.getSection() + "'";
            Cursor cur = db.rawQuery(sql, null);

            if (cur.moveToFirst()) {
                return cur.getInt(cur.getColumnIndex(DB_Constants.SEM_ID));
            } else
                return 0;

        } catch (Exception e) {

            return 0;
        } finally {
            db.close();
        }

    }

    public List<TimeTable> getTimeTable(Samister samister) {
        String sql = "select * ";
        return getTimeTableList(sql);
    }

    public Periods getPeriods(Samister samister, int day, int period) {


        Periods periods = new Periods();
        periods.setP_no(period);
        periods.setDay(day);
        periods.setSem_id(samister.getSem_no());
        final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";

        String sql = "select " + DB_Constants.SEM_FAC_ID + "," + DB_Constants.SEM_SUB_ID + "  from " + DB_Constants.SEMESTER_TABLE + " a INNER JOIN  "
                + DB_Constants.PERIOD_TABLE + " b ON a." + DB_Constants.SEM_ID + "= b." + DB_Constants.P_SEM + "  where a." + DB_Constants.SEM_NO + " = " + samister.getSem_no() + " and a." + DB_Constants.SEM_BRANCH + " ='" + samister.getBranch() + "' and a." + DB_Constants.SEM_SECTION + " = '" + samister.getSection() +
                "' and b." + DB_Constants.PERIOD_NO + "=" + period + " and  b." + DB_Constants.PERIOD_DAY + "=" + day + "";

        int subjectId = 0;
        int facultyid = 0;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        try {
            if (cursor.moveToFirst()) {

                subjectId = cursor.getInt(cursor.getColumnIndex(DB_Constants.SEM_SUB_ID));
                facultyid = cursor.getInt(cursor.getColumnIndex(DB_Constants.SEM_FAC_ID));
                Log.d(TAG + " ll hhh if" + subjectId + " : " + facultyid, sql);
            } else {
                Log.d(TAG + " ll hhh else  " + subjectId + " : " + facultyid, sql);
            }
        } catch (Exception e) {
            Log.d(TAG + " ll exception  " + subjectId + " : " + facultyid, sql);

        } finally {
            cursor.close();
        }
        Log.d(TAG + " ll " + subjectId + " : " + facultyid, sql);

        List<Faculty> facultyList = getFacultyList("select * from " + DB_Constants.FACULTY_TABLE + "  where " + DB_Constants.FACULTY_ID + " = " + facultyid);
        List<Subject> subjectList = getSubjectsList("select * from " + DB_Constants.SUBJECT_TABLE + "  where " + DB_Constants.SUBJECT_ID + " = " + subjectId);

        if (facultyList.size() > 0) {
            periods.setFaculty(facultyList.get(0));
        }
        if (subjectList.size() > 0) {
            periods.setSubject(subjectList.get(0));
        }
        return periods;
    }

    public boolean isValidUser(int loginType, String username, String password) {

        SQLiteDatabase db = getReadableDatabase();


        String sql = "select count(*) cnt from " + DB_Constants.REGISTER_TABLE + " where  " + DB_Constants.NAME + " =? AND " + DB_Constants.PASSWORD + "=? AND " + DB_Constants.LOGIN_TYPE + "=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password, "" + loginType});
        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(cursor.getColumnIndex("cnt")) > 0 ? true : false;
            } else {
                return false;
            }
        } catch (Exception e) {

            return false;
        } finally {
            cursor.close();
        }

    }

    public void register(String name, String password, String age, int logntype, int gender) {

        ContentValues contentValues = getContentValues(name, password, age, logntype, gender);
        Log.d(TAG, contentValues.toString());
        SQLiteDatabase db = getWritableDatabase();
        try {
            long res = db.insert(DB_Constants.REGISTER_TABLE, null, contentValues);
            Log.d(TAG, res + " :  re  ");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

    }

    private ContentValues getContentValues(String name, String password, String age, int logntype, int gender) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DB_Constants.REG_ID, getID());
        contentValues.put(DB_Constants.NAME, name);
        contentValues.put(DB_Constants.PASSWORD, password);
        contentValues.put(DB_Constants.AGE, age);
        contentValues.put(DB_Constants.LOGIN_TYPE, logntype);
        contentValues.put(DB_Constants.GENDER, gender);

        return contentValues;
    }

    public void deleteTimeTable(Samister sam) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "select " + DB_Constants.SEM_ID + " from " + DB_Constants.SEMESTER_TABLE + " where " + DB_Constants.SEM_NO + "=" + sam.getSem_no() + " and " + DB_Constants.SEM_BRANCH + "='" + sam.getBranch() + "' and " + DB_Constants.SEM_SECTION + " = '" + sam.getSection() + "'";
            db.delete(DB_Constants.PERIOD_TABLE, DB_Constants.P_SEM + " in ( " + sql + ") ", null);

        } catch (Exception e) {


        } finally {
            db.close();
        }


    }
}
