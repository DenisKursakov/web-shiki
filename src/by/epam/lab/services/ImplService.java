package by.epam.lab.services;

import by.epam.lab.exceptions.*;
import by.epam.lab.implementations.activityImpls.ActivityImplDB;
import by.epam.lab.implementations.confImpls.ConferenceImplDB;
import by.epam.lab.beans.Conference;
import by.epam.lab.beans.Event;
import by.epam.lab.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplService {
    public List<Conference> getConfsList() throws ServiceException {
        try (Connection cn = ConnectionManager.getConnection()) {
            ConnectionManager.startTransactions();
            List<Conference> confsList =
                    new ConferenceImplDB(cn).getEntities().orElse(new ArrayList<>());
            ConnectionManager.commitConnection();
            ConnectionManager.endTransaction();
            return confsList;
        } catch (SQLException | ConnectionException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public List<Event> getEventsById(int id) throws ServiceException {
        try {
            try (Connection cn = ConnectionManager.getConnection()) {
                return new ActivityImplDB(cn).getEntitiesById(id).orElse(new ArrayList<>());
            }
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void saveRegistration(String accountValue, int[] eventsId, int parseInt) throws ServiceException {
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
