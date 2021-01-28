package datatypes;

import org.junit.Test;

public class TimeData {
    private int year;
    private int month;
    private int day;

    private int hour;
    private int min;

    public TimeData(int year, int month, int day, int hour, int min){
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        String temp = day + "." + month + "." + year + " " + hour + ":";
        if(min < 10){
            temp += "0";
        }
        return temp + min;
    }

    public boolean isBefore(TimeData other){
        return false; // TODO
    }
}
