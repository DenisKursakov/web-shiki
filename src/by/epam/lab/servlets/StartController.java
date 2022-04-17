package by.epam.lab.servlets;



import by.epam.lab.utils.ConstantsJSP;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/start")
public class StartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final String HEADER_NAME = "referer";

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        String referer = request.getHeader(HEADER_NAME);
        if (referer == null) {
            response.sendRedirect(request.getContextPath());
            return;
        }

        //get statsNumber from the request parameter utils.ConstantsJSP.NUMBER_NAME;
        //set attribute utils.ConstantsJSP.NUMBER_NAME;
        String quantity = request.getParameter(ConstantsJSP.NUMBER_NAME);
        request.setAttribute(ConstantsJSP.NUMBER_NAME, quantity);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.START_PAGE_URL);
        rd.forward(request, response);
    }

}