package by.epam.lab.implementations.confImpls;

import java.sql.*;
import java.util.*;

import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.model.beans.Conference;

import static by.epam.lab.utils.ConstantsJSP.*;

public class ConferenceImplDB implements ConferenceDAO {

    static {
        try {
            Class.forName(CLASS_FOR_NAME);
        } catch (ClassNotFoundException e) {
            throw new InitRuntimeException(e.getMessage());
        }
    }
    private static String DB_NAME;
    private static String USER;
    private static String PASSWORD;
    private static final int CONF_IND_ID = 1;
    private static final int CONF_NAME_ID = 2;
    private static final int CONF_FACULTY_ID = 3;
    private static final int CONF_DATE_ID = 4;

    public ConferenceImplDB(ResourceBundle rb) {
        DB_NAME = rb.getString(DB_NAME_PARAM);
        USER = rb.getString(DB_USER_PARAM);
        PASSWORD = rb.getString(DB_PASS_PARAM);
    }

    public Map<Integer, Conference> getConferences() {
        Map<Integer, Conference> confs = new HashMap<>();
        try (Connection cn = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD)) {
            try (Statement st = cn.createStatement()) {
                try (ResultSet rs = st.executeQuery(SELECT_CONF_TABLE)) {
                    while (rs.next()) {
                        confs.put(rs.getInt(CONF_IND_ID),
                                new Conference(rs.getString(CONF_NAME_ID),
                                        rs.getString(CONF_FACULTY_ID),
                                        rs.getString(CONF_DATE_ID)));
                    }
                }
            }
        } catch (SQLException e) {
            throw new InitRuntimeException(e.getMessage());
        }
        return confs;
    }

}
