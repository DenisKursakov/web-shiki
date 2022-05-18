package by.epam.lab.ifaces;

import by.epam.lab.beans.Event;
import by.epam.lab.exceptions.DaoException;

import java.util.List;


public interface ActivityDao {
    List<Event> getEventsListById(long id) throws DaoException;
}
