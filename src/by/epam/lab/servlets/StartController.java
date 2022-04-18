package by.epam.lab.servlets;

import by.epam.lab.utils.ConstantsJSP;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/start")
public class StartController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        //get statsNumber from the request parameter utils.ConstantsJSP.NUMBER_NAME;
        int quantity = (int) Math.round(
                Double.parseDouble(request.getParameter(ConstantsJSP.NUMBER_NAME)));
        //set attribute utils.ConstantsJSP.NUMBER_NAME;
        request.setAttribute(ConstantsJSP.NUMBER_NAME, quantity);
        RequestDispatcher rd = getServletContext().getRequestDispatcher(ConstantsJSP.START_PAGE_URL);
        rd.forward(request, response);
    }

}