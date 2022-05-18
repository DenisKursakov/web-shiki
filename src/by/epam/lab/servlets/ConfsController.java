package by.epam.lab.servlets;

import by.epam.lab.exceptions.InitException;
import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.beans.Conference;
import by.epam.lab.services.ConferenceService;
import by.epam.lab.utils.ConnectionManager;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static by.epam.lab.utils.ConstantsJSP.*;

@WebServlet(
        urlPatterns = "/confs", loadOnStartup = 1
)

public class ConfsController extends HttpServlet {

    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        try {
            List<Conference> conferences = new ConferenceService().getConfsList();
            if (conferences.isEmpty()) {
                throw new InitException("No conferences is found...");
            }
            getServletContext().setAttribute(ConstantsJSP.CONFS_LIST, conferences);
        } catch (ServiceException | InitException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
