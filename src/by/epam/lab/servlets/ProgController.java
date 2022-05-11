package by.epam.lab.servlets;

import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.services.ImplService;

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
            ImplService implService = new ImplService();
            request.setAttribute(CONFERENCES, implService.getConfsList().get(idConf - 1));
            request.setAttribute(ID_CONF_NAME, idConf);
            request.setAttribute(EVENTS, implService.getEventsById(idConf));
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




