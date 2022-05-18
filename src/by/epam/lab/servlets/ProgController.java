package by.epam.lab.servlets;

import by.epam.lab.beans.Conference;
import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.services.ActivityService;
import by.epam.lab.services.ConferenceService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.lab.utils.ConstantsJSP.*;

@WebServlet(
        urlPatterns = {"/prog"}

)

public class ProgController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idConf = Integer.parseInt(request.getParameter(ID_CONF_NAME));
        try {
            Conference conf = new ConferenceService().getConfsList().get(idConf - 1);
            conf.setEvents(new ActivityService().getEventsListById(idConf));
            request.setAttribute(CONFERENCES, conf);
            request.setAttribute(ID_CONF_NAME, idConf);
        } catch (ServiceException e) {
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




