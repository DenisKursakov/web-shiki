package by.epam.lab.services;

import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.implementations.AbstractDao;
import by.epam.lab.implementations.confImpls.ConferenceImplDB;
import by.epam.lab.beans.Conference;
import by.epam.lab.utils.ConnectionManager;

import java.sql.*;
import java.util.*;

public class ConfsService {
    private static List<Conference> confsList;

    public static List<Conference> getConfsList() throws ServiceException {
        try (Connection cn = ConnectionManager.getConnection()) {
            ConnectionManager.startTransactions();
            AbstractDao<Conference> conferenceDAO = new ConferenceImplDB(cn);
            confsList = conferenceDAO.getEntities().orElse(new ArrayList<>());
            ConnectionManager.commitConnection();
            ConnectionManager.endTransaction();
        } catch (SQLException | ConnectionException e) {
            throw new ServiceException(e.getMessage());
        }
        return confsList;
    }
}
