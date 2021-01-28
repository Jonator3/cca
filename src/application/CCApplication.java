package application;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import interfaces.GMCmds;
import interfaces.ITimer;

public class CCApplication implements ITimer, GMCmds {

/**
 *
 * @author Team_19
 * github: www.github.com/Jonator3/cca
 *
 */

    @Override
    public void runTimer() {
        // TODO
        return;
    }

    @Override
    public Appointment[] getGroupAppointments(int id) {
        return new Appointment[0]; // TODO
    }

    @Override
    public boolean selectDates(int id, String participant, PossibleDate[] dates) {
        return false; //TODO
    }

    @Override
    public int createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline, int group_id) {
        return 0;//TODO
    }
}
