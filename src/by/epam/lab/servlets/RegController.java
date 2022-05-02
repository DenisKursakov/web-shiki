package by.epam.lab.servlets;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.factories.ActivityFactory;
import by.epam.lab.ifaces.ActivityDAO;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Stream;

@WebServlet(
        urlPatterns = {"/reg"}
)

public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String accountValue = request.getParameter(ConstantsJSP.ACCOUNT_NAME_PARAM);
        final String confNameValue = request.getParameter(ConstantsJSP.CONF_NAME_PARAM);
        String registration = accountValue + ConstantsJSP.SUCCESSFUL_REG;
        int[] eventsId = Stream.of(request.getParameterValues(ConstantsJSP.ARRAY_EVENT_ID_NAME))
                .mapToInt(Integer::parseInt).toArray();
        ActivityDAO activityDAO = ActivityFactory.getClassFromFactory();
        try {
            activityDAO.saveRegistration(accountValue, eventsId,
                    Integer.parseInt(request.getParameter(ConstantsJSP.ID_CONF_NAME)));
        } catch (DaoException e){
            registration = ConstantsJSP.WRONG_REG;
        }
        request.setAttribute(ConstantsJSP.CONF_NAME_PARAM, confNameValue);
        request.setAttribute(ConstantsJSP.ACCOUNT_NAME_PARAM, registration);

        RequestDispatcher rd =
                getServletContext().getRequestDispatcher(ConstantsJSP.REG_FILE);
        rd.forward(request, response);
    }
}
