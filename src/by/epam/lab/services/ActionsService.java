package by.epam.lab.services;

import by.epam.lab.exceptions.*;
import by.epam.lab.implementations.activityImpls.ActivityImplDB;
import by.epam.lab.model.beans.Event;
import by.epam.lab.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionsService implements ServiceDAO {

    public static List<Event> getEventsById(int id) throws ServiceException {
        List<Event> list = new ArrayList<>();
        try {
            try (Connection cn = ConnectionManager.getConnection()) {
                list = new ActivityImplDB(cn).getEntityById(id);
            }
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
        return list;
    }

    public static void saveRegistration(String accountValue, int[] eventsId, int parseInt) throws ServiceException {
        try (Connection cn = ConnectionManager.getConnection()) {
            ConnectionManager.startTransactions();
            try {
                new ActivityImplDB(cn).saveRegistration(accountValue, eventsId, parseInt);
            } catch (DaoException e) {
                ConnectionManager.rollbackTransaction();
                throw new SQLException(e.getMessage());
            }
            ConnectionManager.commitConnection();
            ConnectionManager.endTransaction();
        } catch (ConnectionException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
