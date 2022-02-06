package by.epam.lab.service;

import by.epam.lab.beans.Result;
import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.interfaces.ResultDao;

import java.sql.*;

import static by.epam.lab.utils.Constants.*;

public class ResultLoader {

    public static void loadResults(ResultDao reader) throws ConnectionException {
        Connection cn = DBManager.getConnection();

        try (PreparedStatement psForResultInsert = cn.prepareStatement(INSERT_INTO_RESULTS_TABLE);
             PreparedStatement psForInsertTests = cn.prepareStatement(INSERT_INTO_TESTS_TABLE);
             PreparedStatement psForInsertLogs = cn.prepareStatement(INSERT_INTO_LOGINS_TABLE);
             PreparedStatement psForSelectTests = cn.prepareStatement(SELECT_TESTS_TABLE);
             PreparedStatement psForSelectLogs = cn.prepareStatement(SELECT_LOGIN_TABLE);
             Statement st = cn.createStatement()) {
            st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, LOGINS_TABLE_NAME));
            st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, TESTS_TABLE_NAME));
            st.executeUpdate(String.format(DELETE_ALL_FROM_TABLE_FORMAT, RESULTS_TABLE_NAME));
            while (reader.hasNextResult()) {
                Result result = reader.nextResult();
                String login = result.getLogin();
                String test = result.getTest();
                int loginId = getId(login, psForSelectLogs, psForInsertLogs);
                int testId = getId(test, psForSelectTests, psForInsertTests);
                psForResultInsert.setInt(LOGIN_ID_FOR_STATEMENT_SET, loginId);
                psForResultInsert.setInt(TEST_ID_FOR_STATEMENT_SET, testId);
                psForResultInsert.setDate(DATE_ID_FOR_STATEMENT_SET, result.getDate());
                psForResultInsert.setInt(MARK_ID_FOR_STATEMENT_SET, result.getMark());
                psForResultInsert.addBatch();
            }
            psForResultInsert.executeBatch();
        } catch (SQLException e) {
            throw new ConnectionException(LOAD_ERROR);
        }
    }

    private static int getId(String name, PreparedStatement selectInquiry,
                             PreparedStatement insertInquiry) throws SQLException {
        selectInquiry.setString(NAME_ID_FOR_SET_LOG_TEST, name);
        int requiredId;
        try (ResultSet rs = selectInquiry.executeQuery()) {
            if (rs.next()) {
                requiredId = rs.getInt(NAME_ID_FOR_SET_LOG_TEST);
            } else {
                insertInquiry.setString(NAME_ID_FOR_SET_LOG_TEST, name);
                insertInquiry.executeUpdate();
                try (ResultSet rs2 = selectInquiry.executeQuery()) {
                    rs2.next();
                    requiredId = rs2.getInt(NAME_ID_FOR_SET_LOG_TEST);
                }
            }
        }
        return requiredId;
    }
}
