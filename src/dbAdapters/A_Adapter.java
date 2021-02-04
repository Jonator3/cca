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
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    public static void setInstance(A_Adapter instance_) {
        instance = instance_;
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

                res.first();
                Appointment a = new Appointment(res.getString("name"),res.getString("description"),res.getString("location"),res.getString("duration"),res.getString("planned_participants"),res.getString("dates"),res.getString("deadline"),res.getBoolean("isFinal"),res.getInt("id"),res.getInt("groupid"));

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
                update.setInt(6,id);
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

    private boolean contains(String[] array, String key){
        for (int n=0;n<array.length;n++){
            if (array[n].equals(key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Appointment[] getAllAppointments() {
        String sql = "SELECT * FROM Appointments";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement query = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                ResultSet res = query.executeQuery();
                con.commit();
                res.last();
                int rows = res.getRow();

                res.first();
                Appointment[] appointments = new Appointment[rows];
                for (int i=0;i<appointments.length;i++){
                    appointments[i] = new Appointment(res.getString("name"),res.getString("description"),res.getString("location"),res.getString("duration"),res.getString("planned_participants"),res.getString("dates"),res.getString("deadline"),res.getBoolean("isFinal"),res.getInt("id"),res.getInt("groupid"));
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
    public void createAppointment(String name, String description, String location, TimeData duration, String[] planned_participants, PossibleDate[] dates, TimeData deadline, int group_id) {
        String sql = "INSERT INTO Appointments (name, description, location, duration, planned_participants, dates, deadline, isFinal, groupid) VALUES (?,?,?,?,?,?,?,false,?)";

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
                int id = insert.executeUpdate();
                con.commit();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Appointment[] getGroupAppointments(int id) {
        String sql = "SELECT * FROM Appointments WHERE groupid = ?";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement query = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                query.setInt(1,id);
                ResultSet res = query.executeQuery();
                con.commit();
                res.last();
                int rows = res.getRow();

                res.first();
                Appointment[] appointments = new Appointment[rows];
                for (int i=0;i<appointments.length;i++){
                    appointments[i] = new Appointment(res.getString("name"),res.getString("description"),res.getString("location"),res.getString("duration"),res.getString("planned_participants"),res.getString("dates"),res.getString("deadline"),res.getBoolean("isFinal"),res.getInt("id"),res.getInt("groupid"));
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
    public boolean addSelectionToAppointment(int id, String participant, TimeData date) {
        Appointment a = getAppointment(id);
        PossibleDate[] dates = a.getDates();
        String[] planned_participants = a.getPlanned_participants();

        if (!contains(planned_participants,participant)){
            String[] new_participants = new String[planned_participants.length+1];
            new_participants[0] = participant;
            for (int n=0;n<planned_participants.length;n++){
                new_participants[n+1] = planned_participants[n];
            }
            planned_participants = new_participants;
        }

        boolean date_exists = false;
        for (int i=0;i<dates.length;i++){
            PossibleDate pd = dates[i];
            if (pd.getDate().equals(date)){
                date_exists = true;
                String[] participants = new String[pd.getPossible_participants().length+1];
                for (int n=0;n<pd.getPossible_participants().length;n++){
                    participants[n+1] = pd.getPossible_participants()[n];
                }
                participants[0] = participant;
                pd.setPossible_participants(participants);
            }
        }
        if (!date_exists){
            if (a.isFinal()){
                return false;
            }
            PossibleDate[] new_dates = new PossibleDate[dates.length+1];
            for (int n=0;n<dates.length;n++){
                new_dates[n+1] = dates[n];
            }
            new_dates[0] = new PossibleDate(date, new String[]{participant});
            dates = new_dates;
        }
        String sql = "UPDATE Appointments SET planned_participants = ?, dates = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection("jdbc:" + config.getTYPE() + "://" + config.getSERVER() + ":" + config.getPORT() + "/" + config.getDATABASE(), config.getUSER(), config.getPASSWORD())) {
            try (PreparedStatement update = con.prepareStatement(sql)){
                con.setAutoCommit(false);
                String pp = "";
                for (int i=0;i<planned_participants.length;i++){
                    if (i!=0){
                        pp += ",";
                    }
                    pp += planned_participants[i];
                }
                String pd = "";
                for (int i=0;i<dates.length;i++){
                    if (i!=0){
                        pd += ",";
                    }
                    pd += dates[i].toString();
                }
                update.setString(2,pd);
                update.setInt(3,id);
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
}
