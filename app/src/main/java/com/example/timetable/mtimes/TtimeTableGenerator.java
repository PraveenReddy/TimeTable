package com.example.timetable.mtimes;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ndindialap4 on 12/9/15.
 */
public class TtimeTableGenerator {


    private static final String TAG = TtimeTableGenerator.class.getName();

    public TtimeTableGenerator() {
    }

    public TimeTable generateTimetable(Samister samister, Context context) {

        DatabaseConnection connection = DatabaseConnection.getInstance(context, Constants.VERTION);
        int subjetcount = 0;
        List<Faculty> faculties = connection.getFaculties(samister);

        if (faculties == null || faculties.size() == 0) {
            Toast.makeText(context, "Please   Assined  faculty ", Toast.LENGTH_SHORT).show();
            return null;
        }
        for (int i = 0; i < faculties.size(); i++) {
            List<Subject> subjects = connection.getSubjects(samister, faculties.get(i));
            subjetcount += subjects.size();
            faculties.get(i).setSubjects(subjects);
        }
        if (subjetcount == 0) {
            Toast.makeText(context, "Please   Assined Subjects to faculty", Toast.LENGTH_SHORT).show();
            return null;
        }
        //
        //
        connection.deleteTimeTable(samister);
        //
        //
        //
        //
        TimeTable timeTable = new TimeTable();
        Periods[][] periodses = new Periods[Constants.MAX_DAYS][Constants.MAX_PERIODS];
        int dayCount = 0, periodCount = 0;

        while (!isAllPeriodsAdded(periodses)) {
            boolean isNotAdded = true;
            Set<Integer> facultyIds = new HashSet<>();
            boolean isNotCreateble = false;

            if (faculties.size() <= 0) {
                Log.d(TAG, " uuuuuuvvvvvvuuuu ");
                continue;
            }
            int facultyRandom = (int) (System.currentTimeMillis() % (faculties.size() <= 0 ? 1 : faculties.size()));
            int facultyid = faculties.get(facultyRandom).getFacutlyId();
            Set<Integer> subjectIds = new HashSet<>();
            boolean isSubjectNotAdded = true;


            if (faculties.get(facultyRandom).getSubjects().size() <= 0) {
                Log.d(TAG, " xxxxxxxxxxxxxxxxxxxxxxx  ");
                continue;
            }
            int subjectRandom = (int) (System.currentTimeMillis() % (faculties.get(facultyRandom).getSubjects().size() <= 0 ? 1 : faculties.get(facultyRandom).getSubjects().size()));
            int subjectId = faculties.get(facultyRandom).getSubjects().get(subjectRandom).getSubjectId();
            Log.d(TAG, facultyRandom + " : " + facultyid + " : " + subjectRandom + " : " + subjectId + ":" + dayCount + " : " + periodCount);
            if (subjectIds.add(subjectId)) {
                Log.d(TAG, "added  : " + facultyRandom + " : " + facultyid + " : " + subjectRandom + " : " + subjectId + ":" + dayCount + " : " + periodCount);

                if (!isCurrectPeroid(periodCount, dayCount, facultyid, connection)) {
                    Log.d(TAG, " is not existesd : " + facultyRandom + " : " + facultyid + " : " + subjectRandom + " : " + subjectId + ":" + dayCount + " : " + periodCount);


                    //we need to add the new period in to database
                    Periods period = new Periods();
                    period.setFaculty(faculties.get(facultyRandom));
                    period.setPeriodNo(periodCount);
                    period.setDay(dayCount);
                    period.setSubject(faculties.get(facultyRandom).getSubjects().get(subjectRandom));
                    Samister samister1 = new Samister();
                    samister1.setBranch(samister.getBranch());
                    samister1.setSection(samister.getSection());
                    samister1.setSem_no(samister.getSem_no());
                    samister1.setSubjectId(subjectId);
                    samister1.setFacultyId(facultyid);
                    int sem_id = connection.getSemId(samister1);
                    Log.d("uuuuuuuuuuuuu ", sem_id + " : ");
                    period.setSem_id(sem_id);
                    periodses[dayCount][periodCount] = period;
                    connection.insert(period);
                    periodCount++;
                    if (periodCount == Constants.MAX_PERIODS) {
                        periodCount = 0;

                        if (dayCount < Constants.MAX_DAYS) {
                            dayCount++;
                        }

                    }

                }
            }
            if (faculties.get(facultyRandom).getSubjects().size() == subjectIds.size()) {

                Log.d(TAG, "isSubjectNotAdded  : " + subjectIds);
                isSubjectNotAdded = false;
            }


            if (faculties.size() == facultyIds.size()) {
                Log.d(TAG, "  is Faculty  added : " + facultyIds);
                isNotAdded = false;
                isNotCreateble = true;
            }
            facultyIds.add(facultyid);

        }
        timeTable.setPeriodses(periodses);
        timeTable.setSamister(samister);
        Toast.makeText(context, "Time Table is Created.", Toast.LENGTH_LONG).show();
        return timeTable;
    }

    private boolean isCurrectPeroid(int periodCount, int dayCount, int facultyid, DatabaseConnection connection) {
        return connection.isExisted(periodCount, dayCount, facultyid);
    }


    private boolean isAllPeriodsAdded(Periods[][] periodses) {

        for (int i = 0; i < periodses.length; i++) {
            for (int j = 0; j < periodses[i].length; j++) {
                if (periodses[i][j] == null)
                    return false;
            }
        }

        return true;
    }

}
