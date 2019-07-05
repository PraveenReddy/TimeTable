package com.example.timetable.mtimes;

import java.util.List;

public class Faculty {
    int facutlyId;
    String facultyName;
    List<Subject> subjects;

    String branch, section;

    int sem_no;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSem_no() {
        return sem_no;
    }

    public void setSem_no(int sem_no) {
        this.sem_no = sem_no;
    }

    public int getFacutlyId() {
        return facutlyId;
    }

    public void setFacutlyId(int facutlyId) {
        this.facutlyId = facutlyId;
    }

    public String getFacultyName() {
        return facultyName == null ? null : facultyName.split(Constants.SUP)[0];
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;

        Faculty faculty = (Faculty) o;

        if (getFacutlyId() != faculty.getFacutlyId()) return false;
        if (getFacultyName() != null ? !getFacultyName().equals(faculty.getFacultyName()) : faculty.getFacultyName() != null)
            return false;
        return !(getSubjects() != null ? !getSubjects().equals(faculty.getSubjects()) : faculty.getSubjects() != null);

    }

    @Override
    public int hashCode() {
        int result = getFacutlyId();
        result = 31 * result + (getFacultyName() != null ? getFacultyName().hashCode() : 0);
        result = 31 * result + (getSubjects() != null ? getSubjects().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return /*"Faculty{" +
                "facutlyId=" + facutlyId +
                ", facultyName='" +*/ facultyName == null ? "" : facultyName  /*+ '\'' +
                ", subjects=" + subjects +
                '}'*/;
    }
}
