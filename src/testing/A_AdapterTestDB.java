package testing;

import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import dbAdapters.A_Adapter;
import dbAdapters.Configuration;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class A_AdapterTestDB extends TestCase {

    private Appointment testA;

    /**
     * Preparing classes with static methods
     */
    @Before
    public void setUp() {

        // Appointment object to be tested
        testA = new Appointment("test","test234","somewhere","0:0:0:1:0","dummy,dummy2","2021:2:11:12:0;dummy","2021:2:7:0:0",false,1,0);

        // SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS Appointments";
        String sqlCreateTableAppointments = "CREATE TABLE Appointments( name TEXT NOT NULL , description TEXT NOT NULL , location TEXT NOT NULL , duration TEXT NOT NULL , planned_participants LONGTEXT NOT NULL , dates LONGTEXT NOT NULL , deadline TEXT NOT NULL , isFinal BOOLEAN NOT NULL , id INT NOT NULL AUTO_INCREMENT , groupid INT NOT NULL , PRIMARY KEY (id))";
        String sqlInsertAppointment = "INSERT INTO Appointments (name, description, location, duration, planned_participants, dates, deadline, isFinal, groupid) VALUES (?,?,?,?,?,?,?,false,?)";

        // Perform database updates
        try (Connection connection = DriverManager
                .getConnection(
                        "jdbc:" + Configuration.getTYPE() + "://" + Configuration.getSERVER() + ":"
                                + Configuration.getPORT() + "/" + Configuration.getDATABASE(),
                        Configuration.getUSER(), Configuration.getPASSWORD())) {

            try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
                psClean.executeUpdate();
            }
            try (PreparedStatement psCreateBooking = connection.prepareStatement(sqlCreateTableAppointments)) {
                psCreateBooking.executeUpdate();
            }
            try (PreparedStatement psInsertAppointment = connection.prepareStatement(sqlInsertAppointment)) {
                psInsertAppointment.setString(1,testA.getName());
                psInsertAppointment.setString(2,testA.getDescription());
                psInsertAppointment.setString(3,testA.getLocation());
                psInsertAppointment.setString(4,testA.getDuration().toString());
                String pp = "";
                for (int i=0;i<testA.getPlanned_participants().length;i++){
                    if (i!=0){
                        pp += ",";
                    }
                    pp += testA.getPlanned_participants()[i];
                }
                psInsertAppointment.setString(5,pp);
                String pd = "";
                for (int i=0;i<testA.getDates().length;i++){
                    if (i!=0){
                        pd += ",";
                    }
                    pd += testA.getDates()[i].toString();
                }
                psInsertAppointment.setString(6,pd);
                psInsertAppointment.setString(7,testA.getDeadline().toString());
                psInsertAppointment.setInt(8,testA.getGroup_id());
                psInsertAppointment.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetGroupAppointments() {

        // Select a time where the offer should be available


        Appointment[] appointments = A_Adapter.getInstance().getGroupAppointments(0);

        // Verify return values
        assertTrue(appointments.length == 1);
        assertTrue(appointments[0].getId() == testA.getId());
        assertTrue(appointments[0].getName().equals(testA.getName()));
        assertTrue(appointments[0].getDescription().equals(testA.getDescription()));
        assertTrue(appointments[0].getLocation().equals(testA.getLocation()));
        assertTrue(appointments[0].getDuration().equals(testA.getDuration()));
        assertTrue(appointments[0].getDeadline().equals(testA.getDeadline()));
        assertTrue(appointments[0].getGroup_id() == testA.getGroup_id());

    }

    @Test
    public void testGetAllAppointments() {

        Appointment[] appointments = A_Adapter.getInstance().getAllAppointments();

        // Verify return values
        assertTrue(appointments.length == 1);
        assertTrue(appointments[0].getId() == testA.getId());
        assertTrue(appointments[0].getName().equals(testA.getName()));
        assertTrue(appointments[0].getDescription().equals(testA.getDescription()));
        assertTrue(appointments[0].getLocation().equals(testA.getLocation()));
        assertTrue(appointments[0].getDuration().equals(testA.getDuration()));
        assertTrue(appointments[0].getDeadline().equals(testA.getDeadline()));
        assertTrue(appointments[0].getGroup_id() == testA.getGroup_id());

    }

    @Test
    public void testGetAppointment() {

        Appointment appointment = A_Adapter.getInstance().getAppointment(1);

        // Verify return values
        assertTrue(appointment != null);
        assertTrue(appointment.getId() == testA.getId());
        assertTrue(appointment.getName().equals(testA.getName()));
        assertTrue(appointment.getDescription().equals(testA.getDescription()));
        assertTrue(appointment.getLocation().equals(testA.getLocation()));
        assertTrue(appointment.getDuration().equals(testA.getDuration()));
        assertTrue(appointment.getDeadline().equals(testA.getDeadline()));
        assertTrue(appointment.getGroup_id() == testA.getGroup_id());

    }



    /**
     * Rest database
     */

    @After
    public void tearDown() {

        // SQL statements
        String sqlCleanDB = "DROP TABLE IF EXISTS Appointments";
        String sqlCreateTableAppointments = "CREATE TABLE Appointments( name TEXT NOT NULL , description TEXT NOT NULL , location TEXT NOT NULL , duration TEXT NOT NULL , planned_participants LONGTEXT NOT NULL , dates LONGTEXT NOT NULL , deadline TEXT NOT NULL , isFinal BOOLEAN NOT NULL , id INT NOT NULL AUTO_INCREMENT , groupid INT NOT NULL , PRIMARY KEY (id))";

        // Perform database updates
        try (Connection connection = DriverManager
                .getConnection(
                        "jdbc:" + Configuration.getTYPE() + "://" + Configuration.getSERVER() + ":"
                                + Configuration.getPORT() + "/" + Configuration.getDATABASE(),
                        Configuration.getUSER(), Configuration.getPASSWORD())) {

            try (PreparedStatement psClean = connection.prepareStatement(sqlCleanDB)) {
                psClean.executeUpdate();
            }
            try (PreparedStatement psCreateBooking = connection.prepareStatement(sqlCreateTableAppointments)) {
                psCreateBooking.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
