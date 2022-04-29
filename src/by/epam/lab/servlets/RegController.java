package by.epam.lab.servlets;

import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        urlPatterns = {"/reg"}
//        ,
//        initParams = {
//                @WebInitParam(name = , value = ),
//                @WebInitParam(name = , value = )
//        }
)

public class RegController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String accountValue = request.getParameter(ConstantsJSP.ACCOUNT_NAME_PARAM);
        final String facultyValue = request.getParameter(ConstantsJSP.FACULTY_NAME_PARAM);
        final String registration = accountValue + ConstantsJSP.SUCCESSFUL_REG;
        request.setAttribute(ConstantsJSP.FACULTY_NAME_PARAM, facultyValue);
        request.setAttribute(ConstantsJSP.ACCOUNT_NAME_PARAM, registration);
        RequestDispatcher rd =
                getServletContext().getRequestDispatcher("/reg.jsp");
        rd.forward(request, response);
    }
}
