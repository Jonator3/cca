package application;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import dbAdapters.A_Adapter;
import interfaces.GMCmds;
import interfaces.IAppointment;
import interfaces.ITimer;

public class CCApplication implements ITimer, GMCmds {

    /**
    *
    * @author Team_19
    * github: www.github.com/Jonator3/cca
    *
    */

    private static CCApplication instance;
    private static IAppointment a_adapter = A_Adapter.getInstance();

    public static CCApplication getInstance() {
        if (instance == null) {
            instance = new CCApplication();
        }

        return instance;
    }

    @Override
    public void runTimer() {
        // entry Point of CCA_ASAD
        Appointment[] appointments = a_adapter.getAllAppointments();

        for(int i=0;i<appointments.length;i++){
            Appointment a = appointments[i];
            if (!a.isFinal()) {
                PossibleDate best = null;
                PossibleDate[] dates = a.getDates();
                for (int n = 0; n < dates.length; n++) {
                    PossibleDate pd = dates[n];
                    if (best == null) {
                        best = pd;
                    } else if (best.getPossible_participants().length < pd.getPossible_participants().length) {
                        best = pd;
                    }
                }
                if (a.getDeadline().isBefore(TimeData.now()) | best.getPossible_participants().length == a.getPlanned_participants().length) {
                    PossibleDate[] pd = new PossibleDate[1];
                    pd[0] = best;
                    a_adapter.editAppointment(a.getId(), a.getDescription(), a.getLocation(), a.getDuration(), best.getPossible_participants(), pd);
                    a.setPlanned_participants(best.getPossible_participants());
                    a.setDates(pd);
                    a.setFinal(true);
                    a_adapter.finalizeAppointment(a.getId());
                }
            }// put an else here to delete old entrys
        }
    }

    @Override
    public Appointment[] getGroupAppointments(int id) {
        return a_adapter.getGroupAppointments(id);
    }

    @Override
    public boolean selectDate(int id, String participant, TimeData date) {
        return a_adapter.addSelectionToAppointment(id, participant, date);
    }

    @Override
    public void createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline, int group_id) {
        a_adapter.createAppointment(name,description,location,duration,planned_participants,dates,deadline,group_id);
    }
}
