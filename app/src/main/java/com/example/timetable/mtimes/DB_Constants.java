package com.example.timetable.mtimes;

public interface DB_Constants {
    String SUBJECT_TABLE = "subject";
    String SUBJECT_ID = "_id";
    String SUBJECT_NAME = "name";


    String FACULTY_TABLE = "faculty";
    String FACULTY_ID = "_id";
    String FACULTY_NAME = "f_name";


    String SEMESTER_TABLE = "semister";
    String SEM_ID = "_id";
    String SEM_NO = "SEM_NO", SEM_SECTION = "SCETION", SEM_BRANCH = "BRANCH", SEM_SUB_ID = "SUB_ID", SEM_FAC_ID = "FAC_ID";

    String PERIOD_TABLE = "PERIODS", PERIOD_ID = "_id", PERIOD_NO = "P_NO", PERIOD_DAY = "P_DAY", P_SEM = "SEM_ID";


    String REGISTER_TABLE = "register";
    String REG_ID ="_id" ;
    String NAME ="name" ;
    String PASSWORD ="password" ;
    String AGE = "age";
    String GENDER = "gender";
    String LOGIN_TYPE = "log_type";
}
