package interfaces;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;

public interface IAppointment {

    Appointment getAppointment(int id);
    boolean editAppointment(int id, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates);
    void createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline,int group_id);
    Appointment[] getGroupAppointments(int id);
    Appointment[] getAllAppointments();
    boolean finalizeAppointment(int id);
    boolean addSelectionToAppointment(int id, String participant, TimeData date);

}
