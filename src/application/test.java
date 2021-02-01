package application;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import dbAdapters.A_Adapter;

public class test {

    public static void main(String[] args){
        A_Adapter aa = A_Adapter.getInstance();
        Appointment[] appointments = aa.getGroupAppointments(0);
        for (int i=0;i<appointments.length;i++){
            System.out.println(appointments[i].toDisplayString());
        }
    }
}
