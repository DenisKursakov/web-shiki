package by.epam.lab.implementations.confImpls;

import java.sql.*;
import java.util.*;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.exceptions.InitException;
import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.model.beans.Conference;
import by.epam.lab.model.beans.Event;
import by.epam.lab.utils.ConstantsJSP;

import javax.servlet.ServletConfig;

import static by.epam.lab.utils.ConstantsJSP.*;

public class ConferenceImplDB implements ConferenceDAO {

    static {
        try {
            Class.forName(CLASS_FOR_NAME);
        } catch (ClassNotFoundException e) {
            throw new InitRuntimeException(e.getMessage());
        }
    }
    private static Map<Integer, Conference> confs = getInitialConferences();
    private static Map<Integer, List<Event>> events = getInitialEvents();

    public ConferenceImplDB(String param, ResourceBundle rb) {
    }

    private static Map<Integer, Conference> getInitialConferences() {
        confs = new HashMap<>();
        try (Connection cn = DriverManager.getConnection(URL, USER, PASSWORD);) {
            try (Statement st = cn.createStatement()) {
                try (ResultSet rs = st.executeQuery(SELECT_CONF_TABLE)) {
                    while (rs.next()) {
                        confs.put(rs.getInt(1),
                                new Conference(rs.getString(2),
                                        rs.getString(3),
                                        rs.getString(4)));
                    }
                }
            }
        } catch (SQLException e) {
            throw new InitRuntimeException(e.getMessage());
        }
        return confs;
    }

    private static Map<Integer, List<Event>> getInitialEvents() {
        events = new HashMap<>();
        try (Connection cn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (Statement st = cn.createStatement()) {
                for (Map.Entry<Integer, Conference> entryConfs : confs.entrySet()) {
                    int idConf = entryConfs.getKey();
                    try(ResultSet rs = st.executeQuery(SELECT_EVENTS + idConf)) {
                        List<Event> eventsList = new ArrayList<>();
                        while (rs.next()) {
                            eventsList.add(new Event(rs.getInt(1), rs.getString(2),
                                    rs.getString(3)));
                        }
                        events.put(idConf, eventsList);
                    }
                }
            }
        } catch (SQLException e) {
            throw new InitRuntimeException(e.getMessage());
        }

        return events;
    }

    public Map<Integer, Conference> getConferences() {
        return confs;
    }

    public Map<Integer, List<Event>> getEvents() {
        return events;
    }

    public List<Event> getEventsById(int id) throws DaoException {
        if (id == 3) {
            throw new DaoException("Error simulation...");
        }
        return events.get(id);
    }
}
