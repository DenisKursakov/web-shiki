package by.epam.lab.servlets;

import by.epam.lab.exceptions.InitException;
import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.factories.NumberFactory;

import static by.epam.lab.utils.ConstantsJSP.*;

import java.io.IOException;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
        urlPatterns = {"/start"},
        initParams = {
//init param for the minimal size of a collection or an array of real numbers
                @WebInitParam(name = MIN_SIZE, value = MIN_SIZE_VALUE),
//init param for the source of real numbers
                @WebInitParam(name = FACTORY_NUMBER, value = DB_IMPL + DELIMITER + DB_PARAM)
        }
)

public class StartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init(ServletConfig sc) throws ServletException {
        super.init(sc);
        try {
            //read the value of the init parameter min.size
            final int MIN_SIZE = Integer.parseInt(sc.getInitParameter(MIN_SIZE_NAME));
            //read the value of the init parameter factory.number and get numbers
            List<Double> numbers =
                    NumberFactory.getResultsFromFactory(sc);
            if (numbers.size() < MIN_SIZE) {
                throw new InitException(MIN_SIZE_ERR);
            }
            //store data in the app scope
            getServletContext().setAttribute(NUMBERS_VALUE, numbers);
            getServletContext().setAttribute(MAX_VALUE_NAME, numbers.size());
        } catch (InitRuntimeException | InitException e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute(NUMBERS_VALUE, getServletContext()
                .getAttribute(NUMBERS_VALUE));
        int statsNumber =
                Integer.parseInt(request.getParameter(NUMBER_NAME));
        request.setAttribute(NUMBER_NAME, statsNumber);
        RequestDispatcher rd =
                getServletContext().getRequestDispatcher(START_PAGE_URL);
        rd.forward(request, response);

    }
}

