package by.epam.lab.implementations.confImpls;

import java.sql.*;
import java.util.*;

import by.epam.lab.beans.Conference;
import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.ifaces.GenericDao;
import by.epam.lab.implementations.AbstractDao;

import static by.epam.lab.utils.ConstantsJSP.*;

public class ConferenceImplDB extends AbstractDao<Conference> implements GenericDao<Conference> {
    private static final int CONF_IND_ID = 1;
    private static final int CONF_NAME_ID = 2;
    private static final int CONF_FACULTY_ID = 3;
    private static final int CONF_DATE_ID = 4;
    private Connection cn;

    public ConferenceImplDB(Connection cn) {
        this.cn = cn;
    }

    public Optional<List<Conference>> getEntities() {
        List<Conference> conferenceList = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery(SELECT_CONF_TABLE)) {
                while (rs.next()) {
                    conferenceList.add(new Conference(rs.getInt(CONF_IND_ID),
                            rs.getString(CONF_NAME_ID),
                            rs.getString(CONF_FACULTY_ID),
                            rs.getString(CONF_DATE_ID)));
                }
            }
        } catch (SQLException e) {
            throw new InitRuntimeException(e.getMessage());
        }
        return Optional.of(conferenceList);
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Conference entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Conference entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Conference update(Conference entity) {
        throw new UnsupportedOperationException();
    }


}
