package by.epam.lab.servlets;

import by.epam.lab.exceptions.InitException;
import by.epam.lab.factories.ActivityFactory;
import by.epam.lab.factories.ConferenceFactory;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.implementations.confImpls.ConferenceImplDB;
import by.epam.lab.model.beans.Conference;

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
        urlPatterns = "/confs", loadOnStartup = 1,
        initParams = {
                @WebInitParam(name = FILE_NAME_PARAM, value = FILE_VALUE_PARAM),
        }
)

public class ConfsController extends HttpServlet {

    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        try {
            String propertiesName = sc.getInitParameter(FILE_NAME_PARAM);
            ResourceBundle rb = ResourceBundle.getBundle(propertiesName);
            ConferenceFactory.init(rb);
            ConferenceDAO conferenceDao = ConferenceFactory.getClassFromFactory();
            if (conferenceDao != null) {
                Map<Integer, Conference> conferences = conferenceDao.getConferences();
                if (conferences.isEmpty()) {
                    throw new InitException("No conferences is found...");
                }
                List<Map.Entry<Integer, Conference>> confsList = new ArrayList<>(conferences.entrySet());
                getServletContext().setAttribute("list", confsList);
                ActivityFactory.init(rb);
            }

        } catch (InitException e) {
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
