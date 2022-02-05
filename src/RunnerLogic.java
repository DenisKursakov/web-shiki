import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.LoadRuntimeException;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.service.DBManager;
import by.epam.lab.service.ResultLoader;
import by.epam.lab.utils.Constants;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static by.epam.lab.utils.Constants.*;

public class RunnerLogic {
    public static void getPrintLogic(String fileName, ResultFactory resultFactory) {
        try {
            loadResult(resultFactory, fileName);
            printAverageMarks(resultFactory);
            getCurrentAndPrintLastDay(resultFactory);
        } finally {
            DBManager.closeConnection();
        }
    }

    private static void loadResult(ResultFactory resultFactory, String fileName) {
        try (ResultDao reader = resultFactory.getDaoFromFactory(fileName)) {
            ResultLoader.loadResults(reader);
        } catch (SourceException e) {
            System.err.println(OPEN_ERROR);
        } catch (ConnectionException e) {
            throw new LoadRuntimeException(ERROR_DB_LOAD);
        } catch (IOException e) {
            System.err.println(ERROR_IO);
        }
    }

    private static void printAverageMarks(ResultFactory resultFactory) {
        try (Statement st = DBManager.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_MEAN_MARK)) {
            while (rs.next()) {
                String login = rs.getString(NAME_ID_FOR_SET_LOG_TEST);
                double mark = rs.getDouble(MEAN_MARK_ID);
                mark = resultFactory.getScaledMark(mark);
                System.out.printf(FORMAT_FOR_AVG_MARK_TABLE, login, mark);
            }
            System.out.println(DIVIDING_LINE);
        } catch (SQLException e) {
            System.err.println(Constants.ERROR_AVERAGE_MARKS + e.getMessage());
        }
    }

    private static void getCurrentAndPrintLastDay(ResultFactory factory) {
        try {
            List<Result> currentMonthResults = getCurrentMonthResults(factory);
            printLastDayResults(currentMonthResults);
        } catch (SQLException e) {
            System.err.println(Constants.ERROR_AVERAGE_MARKS + " " + e.getMessage());
        }
    }

    private static List<Result> getCurrentMonthResults(ResultFactory factory) throws SQLException {
        try (Statement statement = DBManager.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_DATE)) {
            List<Result> currentMonthResults = new LinkedList<>();
            while (rs.next()) {
                Result result = factory.getResultFromFactory(
                        rs.getString(LOGIN_ID_FOR_STATEMENT_SET),
                        rs.getString(TEST_ID_FOR_STATEMENT_SET),
                        rs.getDate(DATE_ID_FOR_STATEMENT_SET),
                        rs.getInt(MARK_ID_FOR_STATEMENT_SET));
                System.out.println(result);
                currentMonthResults.add(result);
            }
            System.out.println(DIVIDING_LINE);
            return currentMonthResults;
        }
    }

    private static void printLastDayResults(List<Result> results) {
        if (!results.isEmpty()) {
            int dateMaxDay = results.get(results.size() - 1)
                    .getDate().toLocalDate().getDayOfMonth();
            ListIterator<Result> listIterator = results.listIterator(results.size());
            while (listIterator.hasPrevious()) {
                Result currentResult = listIterator.previous();
                if (currentResult.getDate().toLocalDate().getDayOfMonth() == dateMaxDay) {
                    System.out.println(currentResult);
                }
            }
        } else {
            System.out.println(REQUIRED_MONTH_IS_NOT_FOUND);
        }
    }
}
