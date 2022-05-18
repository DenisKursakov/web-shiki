package by.epam.lab.implementations;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.ifaces.ActivityDao;
import by.epam.lab.ifaces.GenericDao;
import by.epam.lab.implementations.AbstractDao;
import by.epam.lab.beans.Event;

import java.sql.*;
import java.util.*;

import static by.epam.lab.utils.ConstantsJSP.*;
import static by.epam.lab.utils.ConstantsJSP.SELECT_EVENTS;

public class ActivityImplDB extends AbstractDao<Event> implements GenericDao<Event>, ActivityDao {
    private static final int EVENT_IND_ID = 1;
    private static final int EVENT_STAGE_ID = 2;
    private static final int EVENT_TIME_ID = 3;
    private Connection cn;

    public ActivityImplDB(Connection cn) {
        super(cn);
        this.cn = cn;
    }

    @Override
    protected String getSelectEntitiesRequest() {
        return SELECT_EVENTS;
    }

    @Override
    protected String getSelectByIdRequest() {
        return SELECT_EVENTS;
    }

    @Override
    protected List<Event> parseAfterSelect(ResultSet rs) throws DaoException {
        List<Event> events = new ArrayList<>();
        try {
            while (rs.next()) {
                events.add(new Event(rs.getInt(EVENT_IND_ID),
                        rs.getString(EVENT_STAGE_ID),
                        rs.getString(EVENT_TIME_ID)));
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Event> getEventsListById(long id) throws DaoException {
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery(getSelectByIdRequest() + id)) {
                return parseAfterSelect(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(Event entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean create(Event entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Event update(Event entity) {
        throw new UnsupportedOperationException();
    }


}
