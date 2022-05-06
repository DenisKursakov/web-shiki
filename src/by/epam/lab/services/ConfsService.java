package by.epam.lab.services;

import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.InitException;
import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.implementations.confImpls.ConferenceImplDB;
import by.epam.lab.model.beans.Conference;
import by.epam.lab.utils.ConnectionManager;

import java.rmi.ServerException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfsService {
    private static Map<Integer, Conference> confsMap = new HashMap<>();

    public static Map<Integer, Conference> getConfsMap() {
        return confsMap;
    }

    public static List<Map.Entry<Integer, Conference>> getConfsList() throws ServiceException {
        try (Connection cn = ConnectionManager.getConnection()) {
            ConnectionManager.startTransactions();
            ConferenceDAO conferenceDAO = new ConferenceImplDB(cn);
            confsMap = conferenceDAO.getEntity();
            ConnectionManager.commitConnection();
            ConnectionManager.endTransaction();
        } catch (SQLException | ConnectionException e) {
            throw new ServiceException(e.getMessage());
        }
        return new ArrayList<>(confsMap.entrySet());
    }
}
