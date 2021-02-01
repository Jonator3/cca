package servlets;

import application.CCApplication;
import datatypes.Appointment;
import datatypes.PossibleDate;
import datatypes.TimeData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MemberGUI extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
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
            Appointment[] appointments = new Appointment[] {
            new Appointment("Joeys verrückte Fete", "Freibier für alle!", "online",
                    new TimeData(0, 0, 1, 0, 0), new String[]{},
                    new PossibleDate[]{
                            new PossibleDate(new TimeData(2021,2,28,14,30),new String[]{"Finn"})},
                    new TimeData(2021,2,21,14,0),false,46,1)};
            request.setAttribute("appointments", appointments);

            try {
                request.getRequestDispatcher("/templates/calendar.ftl").forward(request, response);
            } catch(ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        //TODO
        String action = request.getParameter("action");
        if("selectDate".equals(action)) {
            request.setAttribute("pagetitle", "select Date");
            int AId = Integer.parseInt(request.getParameter("AId"));
            String SelectedDate = request.getParameter("SelectedDate");
            TimeData Selected_Date = new TimeData(SelectedDate);
            String MyName = request.getParameter("MyName");
            boolean success = (new CCApplication()).selectDate(AId, MyName, Selected_Date);
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
        }
    }
}
