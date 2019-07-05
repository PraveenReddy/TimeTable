package com.example.timetable;

import com.example.timetable.mtimes.Faculty;
import com.example.timetable.mtimes.Subject;


public class TimeTable {

    private int id;
    private int day;
    private int period;
    private String section;
    private String branch;
    private int semister_no;
    private Subject subject;
    private Faculty faculty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getSemister_no() {
        return semister_no;
    }

    public void setSemister_no(int semister_no) {
        this.semister_no = semister_no;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeTable)) return false;

        TimeTable timeTable = (TimeTable) o;

        if (getId() != timeTable.getId()) return false;
        if (getDay() != timeTable.getDay()) return false;
        if (getPeriod() != timeTable.getPeriod()) return false;
        if (getSemister_no() != timeTable.getSemister_no()) return false;
        if (getSection() != null ? !getSection().equals(timeTable.getSection()) : timeTable.getSection() != null)
            return false;
        if (getBranch() != null ? !getBranch().equals(timeTable.getBranch()) : timeTable.getBranch() != null)
            return false;
        if (getSubject() != null ? !getSubject().equals(timeTable.getSubject()) : timeTable.getSubject() != null)
            return false;
        return !(getFaculty() != null ? !getFaculty().equals(timeTable.getFaculty()) : timeTable.getFaculty() != null);

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getDay();
        result = 31 * result + getPeriod();
        result = 31 * result + (getSection() != null ? getSection().hashCode() : 0);
        result = 31 * result + (getBranch() != null ? getBranch().hashCode() : 0);
        result = 31 * result + getSemister_no();
        result = 31 * result + (getSubject() != null ? getSubject().hashCode() : 0);
        result = 31 * result + (getFaculty() != null ? getFaculty().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "TimeTable{" +
                "id=" + id +
                ", day=" + day +
                ", period=" + period +
                ", section='" + section + '\'' +
                ", branch='" + branch + '\'' +
                ", semister_no=" + semister_no +
                ", subject=" + subject +
                ", faculty=" + faculty +
                '}';
    }
}
