package dbAdapters;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import interfaces.IAppointment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class A_Adapter implements IAppointment {

    private static Configuration config = new Configuration();
    private static A_Adapter instance;

    public static A_Adapter getInstance(){
        if (instance == null){
            instance = new A_Adapter();
        }
        return instance;
    }

    @Override
    public Appointment getAppointment(int id) {
        String query = ""; // TODO

        try (Connection con = DriverManager.getConnection("jdbc:" + Configuration.getTYPE() + "://" + Configuration.getSERVER() + ":" + Configuration.getPORT() + "/" + Configuration.getDATABASE(), Configuration.getUSER(), Configuration.getPASSWORD())) {
            // TODO
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;//TODO
    }

    @Override
    public boolean editAppointment(int id, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates) {
        return false;//TODO
    }

    @Override
    public Integer createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline, int group_id) {
        String query = "INSERT INTO Appointments (name, description, location, duration, planned_participants, dates, deadline, isFinal, group_id)OUTPUT Inserted.id VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection("jdbc:" + Configuration.getTYPE() + "://" + Configuration.getSERVER() + ":" + Configuration.getPORT() + "/" + Configuration.getDATABASE(), Configuration.getUSER(), Configuration.getPASSWORD())) {
            try (PreparedStatement insert = con.prepareStatement(query)){
                insert.setString(1,name);
                insert.setString(2,description);
                insert.setString(3,location);
                insert.setString(4,duration.toString());
                String pp = "";
                for (int i=0;i<planned_participants.length;i++){
                    if (i!=0){
                        pp += ",";
                    }
                    pp += planned_participants[i];
                }
                insert.setString(5,pp);
                String pd = "";
                for (int i=0;i<dates.length;i++){
                    if (i!=0){
                        pd += ",";
                    }
                    pd += dates[i].toString();
                }
                insert.setString(6,pd);
                insert.setString(7,deadline.toString());
                insert.setInt(8,group_id);
                int rep = insert.executeUpdate();
                System.out.println(rep);
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

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
