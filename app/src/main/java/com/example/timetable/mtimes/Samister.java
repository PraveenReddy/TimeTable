package com.example.timetable.mtimes;

import java.io.Serializable;

public class Samister  implements Serializable{
    int semisterId, facultyId, subjectId;
    String semisterName;
    String branch, section;
    int sem_no;


    public int getSemisterId() {
        return semisterId;
    }

    public void setSemisterId(int semisterId) {
        this.semisterId = semisterId;
    }

    public String getSemisterName() {
        return semisterName;
    }

    public void setSemisterName(String semisterName) {
        this.semisterName = semisterName;
    }

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

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Samister)) return false;

        Samister samister = (Samister) o;

        if (getSemisterId() != samister.getSemisterId()) return false;
        if (getSem_no() != samister.getSem_no()) return false;
        if (getFacultyId() != samister.getFacultyId()) return false;
        if (getSubjectId() != samister.getSubjectId()) return false;
        if (getSemisterName() != null ? !getSemisterName().equals(samister.getSemisterName()) : samister.getSemisterName() != null)
            return false;
        if (getBranch() != null ? !getBranch().equals(samister.getBranch()) : samister.getBranch() != null)
            return false;
        return !(getSection() != null ? !getSection().equals(samister.getSection()) : samister.getSection() != null);

    }

    @Override
    public int hashCode() {
        int result = getSemisterId();
        result = 31 * result + getSem_no();
        result = 31 * result + getFacultyId();
        result = 31 * result + getSubjectId();
        result = 31 * result + (getSemisterName() != null ? getSemisterName().hashCode() : 0);
        result = 31 * result + (getBranch() != null ? getBranch().hashCode() : 0);
        result = 31 * result + (getSection() != null ? getSection().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "Samister{" +
                "semisterId=" + semisterId +
                ", sem_no=" + sem_no +
                ", facultyId=" + facultyId +
                ", subjectId=" + subjectId +
                ", semisterName='" + semisterName + '\'' +
                ", branch='" + branch + '\'' +
                ", section='" + section + '\'' +
                '}';
    }
}
