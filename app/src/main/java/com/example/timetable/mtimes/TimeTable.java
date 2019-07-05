package com.example.timetable.mtimes;

import java.util.Arrays;

/**
 * Created by ndindialap4 on 12/9/15.
 */
public class TimeTable {
    int tableId;
    Samister samister;
    Periods[][] periodses = new Periods[Constants.MAX_DAYS][Constants.MAX_PERIODS];

    public TimeTable() {
        periodses = new Periods[Constants.MAX_DAYS][Constants.MAX_PERIODS];
    }
    
    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Samister getSamister() {
        return samister;
    }

    public void setSamister(Samister samister) {
        this.samister = samister;
    }

    public Periods[][] getPeriodses() {
        return periodses;
    }

    public void setPeriodses(Periods[][] periodses) {
        this.periodses = periodses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeTable)) return false;

        TimeTable timeTable = (TimeTable) o;

        if (getTableId() != timeTable.getTableId()) return false;
        if (getSamister() != null ? !getSamister().equals(timeTable.getSamister()) : timeTable.getSamister() != null)
            return false;
        return Arrays.deepEquals(getPeriodses(), timeTable.getPeriodses());

    }


    @Override
    public int hashCode() {
        int result = getTableId();
        result = 31 * result + (getSamister() != null ? getSamister().hashCode() : 0);
        result = 31 * result + (getPeriodses() != null ? Arrays.deepHashCode(getPeriodses()) : 0);
        return result;
    }
}
