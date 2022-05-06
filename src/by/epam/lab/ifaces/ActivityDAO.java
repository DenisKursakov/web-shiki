package by.epam.lab.ifaces;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.model.beans.Event;

import java.util.List;


public interface ActivityDAO {
    List<Event> getEntityById(int id) throws DaoException;
    void saveRegistration (String name, int[] eventsId, int confId) throws DaoException;
}
