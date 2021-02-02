package datatypes;

import java.time.LocalDateTime;

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

    public static TimeData now(){
        LocalDateTime date = LocalDateTime.now();
        return new TimeData(date.getYear(),date.getMonthValue(),date.getDayOfMonth(),date.getHour(),date.getMinute());
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
        String temp = year + ":" + month + ":" + day + ":" + hour + ":" + min;
        return temp;
    }
    
    public String toDisplayString() {
    	return (year != 0 ? year+" year(s) " : "")+(month != 0 ? month+" month(s) " : "")+
                (day != 0 ? day+" day(s) " : "")+(hour != 0 ? hour+" hour(s) " : "")+(min != 0 ? min+" min(s)": "");
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
