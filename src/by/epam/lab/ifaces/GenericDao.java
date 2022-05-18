package by.epam.lab.ifaces;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.beans.Entity;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T extends Entity> {
    List<T> getEntities();

    Optional<T> getEntityById(long id) throws DaoException;

    boolean delete(long id);

    boolean delete(T entity);

    boolean create(T entity);

    T update(T entity);
}
