package by.epam.lab.implementations.confImpls;

import java.sql.*;
import java.util.*;

import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.model.beans.Conference;

import static by.epam.lab.utils.ConstantsJSP.*;

public class ConferenceImplDB implements ConferenceDAO {
    private static final int CONF_IND_ID = 1;
    private static final int CONF_NAME_ID = 2;
    private static final int CONF_FACULTY_ID = 3;
    private static final int CONF_DATE_ID = 4;
    private Connection cn;

    public ConferenceImplDB(Connection cn) {
        this.cn = cn;
    }
    public void initMap () {

    }
    public Map<Integer, Conference> getEntity() {
        Map<Integer, Conference> confsMap = new HashMap<>();
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery(SELECT_CONF_TABLE)) {
                while (rs.next()) {
                    confsMap.put(rs.getInt(CONF_IND_ID),
                            new Conference(rs.getString(CONF_NAME_ID),
                                    rs.getString(CONF_FACULTY_ID),
                                    rs.getString(CONF_DATE_ID)));
                }
            }
        } catch (SQLException e) {
            throw new InitRuntimeException(e.getMessage());
        }
        return confsMap;
    }


}
