import by.epam.lab.Constants;
import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultsHandler;
import by.epam.lab.enums.LoginNames;
import by.epam.lab.factories.MarkStrFactory;
import by.epam.lab.enums.TestNames;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import static by.epam.lab.Constants.*;

public class RunnerForXmlFile {
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
            //get list result using SAX-parser
            try (PreparedStatement psForResultInsert =
                         cn.prepareStatement(INSERT_INTO_RESULTS_TABLE)) {
                XMLReader reader = XMLReaderFactory.createXMLReader();
                ResultsHandler handler = new ResultsHandler();
                reader.setContentHandler(handler);
                try {
                    reader.parse(Constants.FILE_NAME_FOR_TASK2);
                } catch (FileNotFoundException e) {
                    System.err.println(e);
                }
                //delete all from result table
                st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, RESULTS_TABLE_NAME));
                //insert new data to result table
                for (Result result : handler.getResultsList()) {
                    psForResultInsert.setInt(LOGIN_ID_FOR_STATEMENT_SET,
                            result.getLogin().ordinal() + 1);
                    psForResultInsert.setInt(TEST_ID_FOR_STATEMENT_SET,
                            result.getTest().ordinal() + 1);
                    psForResultInsert.setString(DATE_ID_FOR_STATEMENT_SET,
                            result.getStringDate());
                    psForResultInsert.setInt(MARK_ID_FOR_STATEMENT_SET,
                            result.getMark());
                    psForResultInsert.addBatch();
                }
                psForResultInsert.executeBatch();
            }

            //print mean marks
            try (ResultSet rs = st.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_MEAN_MARK)) {
                while (rs.next()) {
                    System.out.printf(FORMAT_FOR_AVG_MARK_TABLE,
                            rs.getString(NAME_ID_FOR_SET_LOG_TEST),
                            rs.getDouble(MEAN_MARK_ID) / TEN_FOR_GET_INT);
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
            // print collections after sorting
            System.out.println(DIVIDING_LINE);
            List<Result> resultList = new LinkedList<>();
            try (ResultSet rs = st.executeQuery(SELECT_RESULT_TABLE_AFTER_SORTED_BY_DATE)) {
                while (rs.next()) {
                    Result currentResult = new Result(
                            rs.getString(LOGIN_ID_FOR_STATEMENT_SET),
                            rs.getString(TEST_ID_FOR_STATEMENT_SET),
                            rs.getString(DATE_ID_FOR_STATEMENT_SET),
                            rs.getInt(MARK_ID_FOR_STATEMENT_SET),
                            MarkStrFactory.MarkType.DOUBLE_TYPE
                    );
                    resultList.add(currentResult);
                }
            }
            printList(resultList);
            System.out.println(DIVIDING_LINE);
            //print the tests which passed in the last day of current month
            LocalDate localDateNow = LocalDate.now();
            LocalDate DateWithMaxDay = LocalDate.MIN;
            for (int i = resultList.size() - 1; i >= 0; i--) {
                Result result = resultList.get(i);
                LocalDate resultLocalDate = result.getDate().toLocalDate();
                if (resultLocalDate.getMonth().equals(localDateNow.getMonth()) &&
                        resultLocalDate.getYear() == localDateNow.getYear() &&
                        DateWithMaxDay.getDayOfMonth() <= resultLocalDate.getDayOfMonth()) {
                    DateWithMaxDay = resultLocalDate;
                    System.out.println(result);
                }
            }
            if(DateWithMaxDay.equals(LocalDate.MIN)){
                System.out.println(REQUIRED_MONTH_IS_NOT_FOUND);
            }
        } catch (SQLException | SAXException |
                IOException e) {
            System.err.println(e);
        }

    }

    private static void printList(List<Result> currentList) {
        for (Result result : currentList) {
            System.out.println(result);
        }
    }
}

