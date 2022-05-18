package by.epam.lab.services;

import by.epam.lab.beans.Event;
import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.DaoException;
import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.implementations.ActivityImplDB;
import by.epam.lab.utils.ConnectionManager;
import by.epam.lab.utils.ConstantsJSP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityService {
    public List<Event> getEventsListById(long id) throws ServiceException {
        try {
            ConnectionManager cm = new ConnectionManager(ConstantsJSP.RESOURCE_BUNDLE);
            try (Connection cn = cm.getConnection()) {
                cm.startTransactions();
                List<Event> events = new ArrayList<>();
                try {
                    events = new ActivityImplDB(cn).getEventsListById(id);
                } catch (DaoException e) {
                    cm.rollbackTransaction();
                    throw new SQLException(e.getMessage());
                }
                cm.commitConnection();
                cm.endTransaction();
                return events;
            }
        } catch (ConnectionException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
