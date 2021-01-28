package dbAdapters;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import interfaces.IAppointment;

public class A_Adapter implements IAppointment {

    @Override
    public Appointment getAppointment(int id) {
        return null;//TODO
    }

    @Override
    public boolean editAppointment(int id, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates) {
        return false;//TODO
    }

    @Override
    public int createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline, int group_id) {
        return 0;//TODO
    }

    @Override
    public Appointment[] getGroupAppointments(int id) {
        return new Appointment[0];//TODO
    }

    @Override
    public boolean finalizeAppointment(int id) {
        return false;//TODO
    }

    @Override
    public boolean addSelectionToAppointment(int id, String participant, TimeData[] dates) {
        return false;//TODO
    }
}
