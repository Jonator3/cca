package servlets;

import application.CCApplication;
import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;
import dbAdapters.A_Adapter;
import interfaces.GMCmds;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class MemberGUI extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static GMCmds CCA = new CCApplication();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        //TODO
        String action = request.getParameter("action");
        if("viewCalendar".equals(action)) {
            if(request.getParameter("groupid") == null) {
                try {
                    request.setAttribute("pagetitle", "Group Calender");
                    request.getRequestDispatcher("/templates/calendarAccess.ftl").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            } else {
                doPost(request, response);
            }

        }else if("addAppointment".equals(action)) {
                try {
                      request.setAttribute("pagetitle", "Add Appointment");
                      request.getRequestDispatcher("/templates/addAppointmentGUI.ftl").forward(request, response);
                  } catch (ServletException | IOException e) {
                      e.printStackTrace();
                  }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //TODO
        String action = request.getParameter("action");
        if("viewCalendar".equals(action)) {
            request.setAttribute("pagetitle", "Group Calender");
            if(request.getParameter("groupid") == null) {
                request.setAttribute("errormessage", "Attribute error: Please don't forget the groupid!");
                try {
                    request.getRequestDispatcher("/templates/error.ftl").forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
            int groupid = Integer.parseInt(request.getParameter("groupid"));

            Appointment[] appointments = new Appointment[] {
            new Appointment("Joeys verrÃ¼ckte Fete", "Freibier fÃ¼r alle!", "online",
                    new TimeData(0, 0, 1, 0, 0), new String[]{},
                    new PossibleDate[]{
                            new PossibleDate(new TimeData(2021,2,28,14,30),new String[]{"Finn"})},
                    new TimeData(2021,2,21,14,0),false,46,1)};
            //request.setAttribute("appointments", appointments);

            request.setAttribute("appointments", CCA.getGroupAppointments(groupid));
            try {
                request.getRequestDispatcher("/templates/calendar.ftl").forward(request, response);
            } catch(ServletException | IOException e) {
                e.printStackTrace();
            }
        } else if("selectDate".equals(action)) {
            request.setAttribute("pagetitle", "Select Date");
            int AId = Integer.parseInt(request.getParameter("AId"));
            String SelectedDate = request.getParameter("SelectedDate");
            TimeData Selected_Date = new TimeData(SelectedDate);
            String MyName = request.getParameter("MyName");
            boolean success = CCA.selectDate(AId, MyName, Selected_Date);
            if(success){
                request.setAttribute("success", "it worked!");
            } else {
                request.setAttribute("success", "it didn't worked!");
            }
            try {
                request.getRequestDispatcher("/templates/dateSelectedSuccess.ftl").forward(request, response);
            }   catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else if("suggestDate".equals(action)) {
            request.setAttribute("pagetitle", "Select Date");
            int AId = Integer.parseInt(request.getParameter("AId"));
            //TODO...
        }else if("addAppointmentGUISubmit".equals(action)) {
        	request.setAttribute("pagetitle", "add Appointment pagetitle");
        	String name = request.getParameter("Name");
        	String descr = request.getParameter("descr");
        	String loc = request.getParameter("loc");
        	String date = request.getParameter("date");
        	String plannedParticipants = request.getParameter("plannedParticipants");
        	String posDates = request.getParameter("posDatesDate");
        	String posDatesTime = request.getParameter("posDatesTime");
        	String deadline = request.getParameter("deadline");
        	String group_id = request.getParameter("group_id");
        	TimeData dateDT = new TimeData(0, 0, 0, Integer.parseInt(date.split(":")[0]), Integer.parseInt(date.split(":")[1]));
        	TimeData posDatesDT = new TimeData(Integer.parseInt(posDates.split("-")[0]), Integer.parseInt(posDates.split("-")[1]), Integer.parseInt(posDates.split("-")[2]), Integer.parseInt(posDates.split(":")[0]), Integer.parseInt(posDates.split(":")[1]));
        	TimeData deadlineDT = new TimeData(Integer.parseInt(deadline.split("-")[0]), Integer.parseInt(deadline.split("-")[1]), Integer.parseInt(deadline.split("-")[2]), 0, 0);
        	int groupid = Integer.parseInt(group_id);
        	System.out.println("date: "+date);
        	System.out.println("posDates: "+posDates);
        	System.out.println("posDatesTime: "+posDatesTime);
        	System.out.println("deadline: "+deadline);
        	CCA.createAppointment(name, descr, loc, dateDT, new String[] {plannedParticipants}, new PossibleDate[] {new PossibleDate(posDatesDT, new String[] {plannedParticipants})}, deadlineDT,groupid);
        	
        	try {
                request.getRequestDispatcher("/templates/index.ftl").forward(request, response);
            }   catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
