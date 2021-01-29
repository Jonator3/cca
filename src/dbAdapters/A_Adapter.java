package dbAdapters;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import interfaces.IAppointment;

import java.sql.*;

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
        String sql = "SELECT * FROM Appointments WHERE id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement query = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                query.setInt(1,id);
                ResultSet res = query.executeQuery();
                con.commit();

                Appointment a = new Appointment(res.getString("name"),res.getString("description"),res.getString("location"),res.getString("duration"),res.getString("planned_participants"),res.getString("dates"),res.getString("deadline"),res.getBoolean("isFinal"),res.getInt("id"),res.getInt("group_id"));

                return a;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean editAppointment(int id, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates) {
        String sql = "UPDATE Appointments SET description = ?, location = ?, duration = ?, planned_participants = ?, dates = ? WHERE id = ? AND isFinal = FALSE";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement update = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                update.setString(1,description);
                update.setString(2,location);
                update.setString(3,duration.toString());
                String pp = "";
                for (int i=0;i<planned_participants.length;i++){
                    if (i!=0){
                        pp += ",";
                    }
                    pp += planned_participants[i];
                }
                update.setString(4,pp);
                String pd = "";
                for (int i=0;i<dates.length;i++){
                    if (i!=0){
                        pd += ",";
                    }
                    pd += dates[i].toString();
                }
                update.setString(5,pd);
                int res = update.executeUpdate();
                con.commit();

                if (res == 0){
                    return false;
                }
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Appointment[] getEditableAppointments() {
        String sql = "SELECT * FROM Appointments WHERE isFinal = FALSE";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement query = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                ResultSet res = query.executeQuery();
                con.commit();

                Appointment[] appointments = new Appointment[res.getFetchSize()];
                for (int i=0;i<appointments.length;i++){
                    appointments[i] = new Appointment(res.getString("name"),res.getString("description"),res.getString("location"),res.getString("duration"),res.getString("planned_participants"),res.getString("dates"),res.getString("deadline"),res.getBoolean("isFinal"),res.getInt("id"),res.getInt("group_id"));
                    res.next();
                }

                return appointments;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline, int group_id) {
        String sql = "INSERT INTO Appointments (name, description, location, duration, planned_participants, dates, deadline, isFinal, group_id)OUTPUT Inserted.id VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement insert = con.prepareStatement(sql)){
                con.setAutoCommit(false);
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
                ResultSet res = insert.executeQuery();
                con.commit();
                return res.getInt("id");
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Appointment[] getGroupAppointments(int id) {
        String sql = "SELECT * FROM Appointments WHERE group_id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement query = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                query.setInt(1,id);
                ResultSet res = query.executeQuery();
                con.commit();

                Appointment[] appointments = new Appointment[res.getFetchSize()];
                for (int i=0;i<appointments.length;i++){
                    appointments[i] = new Appointment(res.getString("name"),res.getString("description"),res.getString("location"),res.getString("duration"),res.getString("planned_participants"),res.getString("dates"),res.getString("deadline"),res.getBoolean("isFinal"),res.getInt("id"),res.getInt("group_id"));
                    res.next();
                }

                return appointments;
            }catch (SQLException e){
                e.printStackTrace();
                return null;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean finalizeAppointment(int id) {
        String sql = "UPDATE Appointments SET isFinal = TRUE WHERE id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement update = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                update.setInt(1,id);
                update.executeUpdate();
                con.commit();
            }catch (SQLException e){
                e.printStackTrace();
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean addSelectionToAppointment(int id, String participant, TimeData[] dates) {
        Appointment a = getAppointment(id);
        return false;//TODO
    }
}
