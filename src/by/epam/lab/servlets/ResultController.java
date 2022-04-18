package by.epam.lab.servlets;

import by.epam.lab.enums.Operation;
import by.epam.lab.utils.ConstantsJSP;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result")
public class ResultController extends HttpServlet {

    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        //get strOperation and strNumbers from the corresponding request parameters;
        String strOperation = request.getParameter(ConstantsJSP.OPERATION_NAME);
        String[] strNumbers = request.getParameterValues(ConstantsJSP.STAT_NAME);
        //convert strNumbers to double numbers;
        double[] numbers = Arrays.stream(strNumbers).mapToDouble(Double::parseDouble).toArray();
        //convert strOperation to the operation - an item of the enum Operation;
        Operation operation = Operation.valueOf(strOperation.toUpperCase());
        double result = operation.getResult(numbers);
        //set attributes for the next page;
        request.setAttribute(ConstantsJSP.STAT_NAME, numbers);
        request.setAttribute(ConstantsJSP.OPERATION_NAME, operation.toString());
        request.setAttribute(ConstantsJSP.RESULT_NAME, result);
        //forward (or redirect?) the request to the next page;
        RequestDispatcher rq = getServletContext().getRequestDispatcher(ConstantsJSP.RESULT_PAGE_URL);
        rq.forward(request, response);
    }
}