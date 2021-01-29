package interfaces;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;

/**
 * Interface that provides all method to interact with a GM.
 *
 * @author Team_19
 *
 */

public interface GMCmds {
   int createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline,int group_id);
   boolean selectDates(int id, String participant, TimeData[] dates);
   Appointment[] getGroupAppointments(int id);
}
