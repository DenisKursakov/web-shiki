package by.epam.lab.servlets;

import by.epam.lab.exceptions.InitException;
import by.epam.lab.implementations.confImpls.ConferenceImplDB;
import by.epam.lab.model.beans.Conference;
import by.epam.lab.model.beans.Event;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static by.epam.lab.utils.ConstantsJSP.*;

@WebServlet(
        urlPatterns = {"/prog"}
//        ,
//        initParams = {
//                @WebInitParam(name = , value = ),
//                @WebInitParam(name = , value = )
//        }
)

public class ProgController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle(FILE_VALUE_PARAM);
        ConferenceImplDB confImpl = new ConferenceImplDB(rb.getString(CSV_FILE_ACT_NAME),rb);
        Map<Integer, Conference> confsMap = confImpl.getConferences();
        request.setAttribute(CONFERENCES, confsMap.get(Integer.parseInt(request.getParameter(ID_CONF_NAME))));
        Map<Integer, List<Event>> eventsMap = confImpl.getEvents();
        request.setAttribute(EVENTS, eventsMap.get(Integer.parseInt(request.getParameter(ID_CONF_NAME))));
        RequestDispatcher rd =
                getServletContext().getRequestDispatcher(PROG_FILE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}




