package by.epam.lab.servlets;

import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.services.ConferenceService;
import by.epam.lab.services.RegisteredService;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession(true);
        final String accountValue = request.getParameter(ConstantsJSP.ACCOUNT_NAME_PARAM);
        final String confNameValue = request.getParameter(ConstantsJSP.CONF_NAME_PARAM);
        String registration = accountValue + ConstantsJSP.SUCCESSFUL_REG;
        int[] eventsId = Stream.of(request.getParameterValues(ConstantsJSP.ARRAY_EVENT_ID_NAME))
                .mapToInt(Integer::parseInt).toArray();
        try {
            new RegisteredService().saveRegistration(accountValue, eventsId,
                    Integer.parseInt(request.getParameter(ConstantsJSP.ID_CONF_NAME)));
        } catch (ServiceException e){
            registration = ConstantsJSP.WRONG_REG;
        }
        session.setAttribute(ConstantsJSP.CONF_NAME_PARAM, confNameValue);
        session.setAttribute(ConstantsJSP.ACCOUNT_NAME_PARAM, registration);

        response.sendRedirect(ConstantsJSP.REG_FILE);
    }
}
