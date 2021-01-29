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

    public TimeData(String s){
        String[] data = s.split(":");
        this.year = Integer.parseInt(data[0]);
        this.month = Integer.parseInt(data[1]);
        this.day = Integer.parseInt(data[2]);
        this.hour = Integer.parseInt(data[3]);
        this.min = Integer.parseInt(data[4]);
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
        String temp = day + ":" + month + ":" + year + ":" + hour + ":" + min;
        return temp + min;
    }

    public boolean isBefore(TimeData other){
        if (this.year < other.year){
            return true;
        }else if (this.year > other.year){
            return false;
        }
        if (this.month < other.month){
            return true;
        }else if(this.month > other.month){
            return false;
        }
        if (this.day < other.day){
            return true;
        }else if (this.day > other.day){
            return false;
        }
        if (this.hour < other.hour){
            return true;
        }else if (this.hour > other.hour){
            return false;
        }
        return (this.min < other.min);
    }
}
