package by.epam.lab.servlets;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.factories.ActivityFactory;
import by.epam.lab.factories.ConferenceFactory;
import by.epam.lab.ifaces.ActivityDAO;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.model.beans.Conference;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;

import static by.epam.lab.utils.ConstantsJSP.*;

@WebServlet(
        urlPatterns = {"/prog"}

)

public class ProgController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResourceBundle rb = ResourceBundle.getBundle(FILE_VALUE_PARAM);
        ConferenceFactory.init(rb);
        ConferenceDAO conferenceDAO = ConferenceFactory.getClassFromFactory();
        ActivityFactory.init(rb);
        ActivityDAO activityDAO = ActivityFactory.getClassFromFactory();
        Map<Integer, Conference> confsMap = conferenceDAO.getConferences();
        int idConf = Integer.parseInt(request.getParameter(ID_CONF_NAME));
        request.setAttribute(CONFERENCES, confsMap.get(idConf));
        request.setAttribute(ID_CONF_NAME, idConf);
        try {
            request.setAttribute(EVENTS, activityDAO.getEventsById(idConf));
        } catch (DaoException e) {
            throw new ServletException(e.getMessage());
        }
        RequestDispatcher rd =
                getServletContext().getRequestDispatcher(PROG_FILE);
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}




