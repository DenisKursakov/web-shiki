import by.epam.lab.beans.Result;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.service.ResultLoader;
import by.epam.lab.utils.Util;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static by.epam.lab.utils.Constants.*;

public class RunnerLogic {
    private static Connection cn;

    public static void getPrintLogic(String fileName, ResultFactory resultFactory) {
        try {
            cn = DriverManager.getConnection(URL, USER, PASSWORD);
            try (Statement st = cn.createStatement()) {
                //delete all from tests and logins tables
                st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, LOGINS_TABLE_NAME));
                st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, TESTS_TABLE_NAME));
                st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, RESULTS_TABLE_NAME));

                //load the date in DB
                ResultLoader.loadResults(resultFactory.getDaoFromFactory(fileName));

                //print mean mark
                try (ResultSet rs = st.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_MEAN_MARK)) {
                    while (rs.next()) {
                        System.out.printf(FORMAT_FOR_AVG_MARK_TABLE,
                                rs.getString(NAME_ID_FOR_SET_LOG_TEST),
                                rs.getDouble(MEAN_MARK_ID) / TEN_FOR_GET_INT);
                    }
                    System.out.println(DIVIDING_LINE);
                } catch (SQLException e) {
                    System.err.println(e);
                }
                List<Result> resultList = new LinkedList<>();
                LocalDate localDateNow = LocalDate.now();

                //add elements in resultList
                try (ResultSet rs = st.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_DATE)) {
                    while (rs.next()) {
                        Result currentResult = resultFactory.getResultFromFactory(
                                rs.getString(LOGIN_ID_FOR_STATEMENT_SET),
                                rs.getString(TEST_ID_FOR_STATEMENT_SET),
                                rs.getDate(DATE_ID_FOR_STATEMENT_SET),
                                rs.getInt(MARK_ID_FOR_STATEMENT_SET)
                        );
                        LocalDate resultLocalDate = currentResult.getDate().toLocalDate();
                        if (resultLocalDate.getMonth().equals(localDateNow.getMonth()) &&
                                resultLocalDate.getYear() == localDateNow.getYear()) {
                            resultList.add(currentResult);
                        }
                    }
                    Util.printResultList(resultList);
                    System.out.println(DIVIDING_LINE);
                    System.out.println(PRINT_TESTS_LAST_DAY);

                    //print the tests which passed in the last day of current month
                    if (!resultList.isEmpty()) {
                        int dateMaxDay = resultList.get(resultList.size() - 1)
                                .getDate().toLocalDate().getDayOfMonth();
                        ListIterator<Result> listIterator = resultList.listIterator(resultList.size());
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
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
