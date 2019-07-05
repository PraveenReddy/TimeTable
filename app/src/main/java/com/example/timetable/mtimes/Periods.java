package com.example.timetable.mtimes;

/**
 * Created by ndindialap4 on 12/9/15.
 */
public class Periods {
    int periodNo;
    int day;
    int p_no;
    int sem_id;
    Subject subject;
    Faculty faculty;

    public int getSem_id() {
        return sem_id;
    }

    public void setSem_id(int sem_id) {
        this.sem_id = sem_id;
    }


    public int getPeriodNo() {
        return periodNo;
    }

    public void setPeriodNo(int periodNo) {
        this.periodNo = periodNo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getP_no() {
        return p_no;
    }

    public void setP_no(int p_no) {
        this.p_no = p_no;
    }

    public int getDay() {

        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getPeriod() {
        return subject == null ? "" : subject + (faculty == null ? "" : "/" + faculty);
    }

    @Override
    public String toString() {
        return
                "PeriodNo  : " + p_no +
                        " day : " + day +
                        " semistr : " + sem_id +
                        " subject : " + subject.getSubjectName() +
                        " faculty : " + faculty.getFacultyName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Periods)) return false;

        Periods periods = (Periods) o;

        if (getPeriodNo() != periods.getPeriodNo()) return false;
        if (getDay() != periods.getDay()) return false;
        if (getP_no() != periods.getP_no()) return false;
        if (getSem_id() != periods.getSem_id()) return false;
        if (getSubject() != null ? !getSubject().equals(periods.getSubject()) : periods.getSubject() != null)
            return false;
        return !(getFaculty() != null ? !getFaculty().equals(periods.getFaculty()) : periods.getFaculty() != null);

    }

    @Override
    public int hashCode() {
        int result = getPeriodNo();
        result = 31 * result + getDay();
        result = 31 * result + getP_no();
        result = 31 * result + getSem_id();
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + (getFaculty() != null ? getFaculty().hashCode() : 0);
        return result;
    }
}
