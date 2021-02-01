package application;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import dbAdapters.A_Adapter;

public class test {

    public static void main(String[] args){
        A_Adapter aa = A_Adapter.getInstance();
        String[] pp = new String[1];
        pp[0] = "dummy";
        PossibleDate[] pd = new PossibleDate[1];
        pd[0] = new PossibleDate(new TimeData(2021,2,11,12,0),pp);
        aa.createAppointment("test2","just a second test","nowhere",new TimeData(0,0,0,1,45),pp,pd,new TimeData(2021,2,8,8,0),0);
        Appointment a = aa.getAppointment(1);
        System.out.println(a.toDisplayString());
    }
}
