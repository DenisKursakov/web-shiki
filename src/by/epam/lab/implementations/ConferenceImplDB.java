package by.epam.lab.implementations;

import java.sql.*;
import java.util.*;

import by.epam.lab.beans.Event;
import by.epam.lab.exceptions.DaoException;
import by.epam.lab.beans.Conference;

import static by.epam.lab.utils.ConstantsJSP.*;

public class ConferenceImplDB extends AbstractDao<Conference> implements ConferenceDao {
    private static final int CONF_IND_ID = 1;
    private static final int CONF_NAME_ID = 2;
    private static final int CONF_FACULTY_ID = 3;
    private static final int CONF_DATE_ID = 4;
    private Connection cn;
    private List<Conference> conferences;

    public ConferenceImplDB(Connection cn) {
        super(cn);
        this.cn = cn;
    }

    @Override
    protected String getSelectEntitiesRequest() {
        return SELECT_CONF_TABLE;
    }

    @Override
    protected String getSelectByIdRequest() {
        return SELECT_CONFS_ID;
    }

    @Override
    protected List<Conference> parseAfterSelect(ResultSet rs) throws DaoException {
        if(conferences == null) {
            conferences = new ArrayList<>();
        }
        try {
            while (rs.next()) {
                conferences.add(new Conference(rs.getInt(CONF_IND_ID),
                        rs.getString(CONF_NAME_ID),
                        rs.getString(CONF_FACULTY_ID),
                        rs.getString(CONF_DATE_ID)));
            }
            return conferences;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
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


    @Override
    public void initConfsEvents(Conference conference, List<Event> list) {
        conference.setEvents(list);
    }
}
