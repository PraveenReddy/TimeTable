package com.example.timetable.mtimes;

/**
 * Created by ndindialap4 on 12/9/15.
 */
public class Subject {
    int subjectId;
    String subjectName;

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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subject)) return false;

        Subject subject = (Subject) o;

        if (getSubjectId() != subject.getSubjectId()) return false;
        return !(getSubjectName() != null ? !getSubjectName().equals(subject.getSubjectName()) : subject.getSubjectName() != null);

    }


    @Override
    public int hashCode() {
        int result = getSubjectId();
        result = 31 * result + (getSubjectName() != null ? getSubjectName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return subjectName;
    }
}
