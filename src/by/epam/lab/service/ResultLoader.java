package by.epam.lab.service;

import by.epam.lab.beans.Result;
import by.epam.lab.interfaces.ResultDao;

import java.sql.*;

import static by.epam.lab.utils.Constants.*;

public class ResultLoader {
    private static Connection cn;
    private static Statement st;

    public static void loadResults(ResultDao reader) throws SQLException {
        cn = DriverManager.getConnection(URL, USER, PASSWORD);
        st = cn.createStatement();
        PreparedStatement psForResultInsert = cn.prepareStatement(INSERT_INTO_RESULTS_TABLE);
        while (reader.hasNextResult()) {
            Result result = reader.nextResult();
            String login = result.getLogin();
            String test = result.getTest();
            int loginId = getId(login, SELECT_LOGIN_TABLE, INSERT_INTO_LOGINS_TABLE);
            int testId = getId(test, SELECT_TESTS_TABLE, INSERT_INTO_TESTS_TABLE);
            psForResultInsert.setInt(LOGIN_ID_FOR_STATEMENT_SET, loginId);
            psForResultInsert.setInt(TEST_ID_FOR_STATEMENT_SET, testId);
            psForResultInsert.setDate(DATE_ID_FOR_STATEMENT_SET, result.getDate());
            psForResultInsert.setInt(MARK_ID_FOR_STATEMENT_SET, result.getMark().getMarkValue());
            psForResultInsert.addBatch();
            psForResultInsert.executeBatch();
        }
    }

    private static int getId(String element, String selectInquiry, String insertInquiry) {
        int requiredId = 0;

        try (PreparedStatement psForInsertLogTests = cn.prepareStatement(insertInquiry)) {
            boolean elementWasCreated = false;
            try (ResultSet rs = st.executeQuery(String.format(selectInquiry, element))) {
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        elementWasCreated = true;
                        requiredId = rs.getInt(1);
                    }
                }
                if (!elementWasCreated) {
                    psForInsertLogTests.setString(NAME_ID_FOR_SET_LOG_TEST, element);
                    psForInsertLogTests.addBatch();
                    psForInsertLogTests.executeBatch();
                }
            }
            try (ResultSet rs = st.executeQuery(String.format(selectInquiry, element))) {
                if (!elementWasCreated) {
                    while (rs.next()) {
                        requiredId = rs.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return requiredId;
    }
}
