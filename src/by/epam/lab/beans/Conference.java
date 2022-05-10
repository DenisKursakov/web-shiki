package by.epam.lab.beans;

import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.DaoException;
import by.epam.lab.implementations.AbstractDao;
import by.epam.lab.implementations.activityImpls.ActivityImplDB;
import by.epam.lab.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import static by.epam.lab.utils.ConstantsJSP.*;

public class Conference extends Entity {
    private final String name;
    private final String faculty;
    private final Date date;

    private final List<Event> eventList = new ArrayList<>();

    public Conference(long id, String name, String faculty, String date) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.date = Date.valueOf(date);
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public Date getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public List<Event> getEventList() throws DaoException {
        try (Connection cn = ConnectionManager.getConnection()) {
            ConnectionManager.startTransactions();
            AbstractDao<Event> eventsDao = new ActivityImplDB(cn);
            ConnectionManager.commitConnection();
            ConnectionManager.endTransaction();
            return eventsDao.getEntitiesById(id).orElse(new ArrayList<>());
        } catch (SQLException | ConnectionException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return name + DELIMITER + faculty + DELIMITER + date;
    }
}
