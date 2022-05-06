package by.epam.lab.implementations.activityImpls;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.ifaces.ActivityDAO;
import by.epam.lab.model.beans.Event;

import java.sql.*;
import java.util.*;

import static by.epam.lab.utils.ConstantsJSP.*;
import static by.epam.lab.utils.ConstantsJSP.SELECT_EVENTS;

public class ActivityImplDB implements ActivityDAO {
    private static final int EVENT_IND_ID = 1;
    private static final int EVENT_STAGE_ID = 2;
    private static final int EVENT_TIME_ID = 3;
    private Connection cn;

    public ActivityImplDB(Connection cn) {
        this.cn = cn;
    }


    @Override
    public List<Event> getEntityById(int id) throws DaoException {
        List<Event> events = new ArrayList<>();
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery(SELECT_EVENTS + id)) {
                while (rs.next()) {
                    events.add(new Event(rs.getInt(EVENT_IND_ID),
                            rs.getString(EVENT_STAGE_ID),
                            rs.getString(EVENT_TIME_ID)));
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return events;
    }

    @Override
    public void saveRegistration(String name, int[] eventsId, int confId) throws DaoException {
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery(String.format(SELECT_ID_STUD, name));
                 PreparedStatement studentsInsert = cn.prepareStatement(INSERT_INTO_STUD_TABLE);
                 PreparedStatement insertRegistered = cn.prepareStatement(INSERT_INTO_REG_TABLE)) {
                if (!rs.next()) {
                    studentsInsert.setString(1, name);
                    studentsInsert.addBatch();
                }
                studentsInsert.executeBatch();
                for (int i : eventsId) {
                    insertRegistered.setInt(1, i);
                    insertRegistered.setString(EVENT_STAGE_ID, name);
                    insertRegistered.addBatch();
                }
                insertRegistered.executeBatch();
            }

        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }


}
