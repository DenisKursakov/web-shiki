
import by.epam.lab.beans.Result;
import by.epam.lab.enums.LoginNames;
import by.epam.lab.factories.MarkStrFactory;
import by.epam.lab.enums.TestNames;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static by.epam.lab.Constants.*;

public class RunnerForCsvFile {
    public static void main(String[] args) {

        try (Connection cn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement psForLogInsert =
                     cn.prepareStatement(INSERT_INTO_LOGINS_TABLE);
             PreparedStatement psForTestInsert =
                     cn.prepareStatement(INSERT_INTO_TESTS_TABLE)) {
            //delete all from logins table
            st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, LOGINS_TABLE_NAME));
            //add data constants to logins table
            for (LoginNames logName : LoginNames.values()) {
                psForLogInsert.setString(NAME_ID_FOR_SET_LOG_TEST, logName.toString());
                psForLogInsert.addBatch();
            }
            psForLogInsert.executeBatch();
            //delete all from logins table
            st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, TESTS_TABLE_NAME));
            //add data constants to logins table
            for (TestNames testName : TestNames.values()) {
                psForTestInsert.setString(NAME_ID_FOR_SET_LOG_TEST, testName.toString());
                psForTestInsert.addBatch();
            }
            psForTestInsert.executeBatch();
            st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, RESULTS_TABLE_NAME));
            try (Scanner sc = new Scanner(new FileReader(FILE_NAME_FOR_TASK1));
                 PreparedStatement psForResultInsert =
                         cn.prepareStatement(INSERT_INTO_RESULTS_TABLE)) {
                //get strings from scanner, create Result element
                while (sc.hasNextLine()) {
                    String[] elements = sc.nextLine().split(SEMICOLON);
                    psForResultInsert.setInt(LOGIN_ID_FOR_STATEMENT_SET, LoginNames.valueOf
                            (elements[LOGIN_ID_IN_ARRAY].toUpperCase()).ordinal() + 1);
                    psForResultInsert.setInt(TEST_ID_FOR_STATEMENT_SET, TestNames.valueOf(
                            elements[TEST_ID_IN_ARRAY].toUpperCase()).ordinal() + 1);
                    psForResultInsert.setString(DATE_ID_FOR_STATEMENT_SET,
                            elements[DATE_ID_IN_ARRAY]);
                    psForResultInsert.setString(MARK_ID_FOR_STATEMENT_SET,
                            elements[MARK_ID_IN_ARRAY]);
                    psForResultInsert.addBatch();
                }
                psForResultInsert.executeBatch();
            } catch (FileNotFoundException e) {
                System.err.println(e);
            }
            try (ResultSet rs = st.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_MEAN_MARK)) {
                while (rs.next()) {
                    System.out.printf(FORMAT_FOR_AVG_MARK_TABLE,
                            rs.getString(NAME_ID_FOR_SET_LOG_TEST),
                            rs.getDouble(MEAN_MARK_ID));
                }
                System.out.println(DIVIDING_LINE);
            } catch (SQLException e) {
                System.err.println(e);
            }
            List<Result> resultList = new LinkedList<>();
            LocalDate localDateNow = LocalDate.now();
            try (ResultSet rs = st.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_DATE)) {
                while (rs.next()) {
                    Result currentResult = new Result(
                            rs.getString(LOGIN_ID_FOR_STATEMENT_SET),
                            rs.getString(TEST_ID_FOR_STATEMENT_SET),
                            rs.getString(DATE_ID_FOR_STATEMENT_SET),
                            rs.getInt(MARK_ID_FOR_STATEMENT_SET),
                            MarkStrFactory.MarkType.INTEGER_TYPE
                    );
                    LocalDate resultLocalDate = currentResult.getDate().toLocalDate();
                    if (resultLocalDate.getMonth().equals(localDateNow.getMonth()) &&
                            resultLocalDate.getYear() == localDateNow.getYear()) {
                        resultList.add(currentResult);
                    }
                }
            }
            printList(resultList);
            System.out.println(DIVIDING_LINE);
            System.out.println(PRINT_TESTS_LAST_DAY);
            //print the tests which passed in the last day of current month
            if (!resultList.isEmpty()) {
                int dateWithMaxDay = resultList.get(resultList.size() - 1)
                        .getDate().toLocalDate().getDayOfMonth();
                for (Result result : resultList) {
                    if (result.getDate().toLocalDate().getDayOfMonth() == dateWithMaxDay) {
                        System.out.println(result);
                    }
                }
            } else {
                System.out.println(REQUIRED_MONTH_IS_NOT_FOUND);
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    private static void printList(List<Result> currentList) {
        for (Result result : currentList) {
            System.out.println(result);
        }
    }
}
