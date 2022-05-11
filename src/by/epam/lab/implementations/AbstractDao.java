package by.epam.lab.implementations;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.ifaces.GenericDao;
import by.epam.lab.beans.Entity;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T extends Entity> implements GenericDao<T> {

    public Optional<List<T>> getEntities() {
        return Optional.empty();
    }

    public Optional<List<T>> getEntitiesById(long id) throws DaoException {
        return Optional.empty();
    }

    public void saveRegistration(String name, int[] eventsId, int confId) throws DaoException {
        throw new UnsupportedOperationException();
    }
}
