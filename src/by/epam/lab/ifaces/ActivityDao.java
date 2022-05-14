package by.epam.lab.ifaces;

import by.epam.lab.exceptions.DaoException;

public interface ActivityDao {
    void saveRegistration(String name, int[] eventsId, int confId) throws DaoException;
}
