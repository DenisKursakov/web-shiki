package by.epam.lab.services;

import by.epam.lab.exceptions.*;
import by.epam.lab.implementations.ActivityImplDB;
import by.epam.lab.implementations.ConferenceImplDB;
import by.epam.lab.beans.Conference;
import by.epam.lab.utils.ConnectionManager;
import by.epam.lab.utils.ConstantsJSP;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConferenceService {
    public List<Conference> getConfsList() throws ServiceException {
        try {
            ConnectionManager cm = new ConnectionManager(ConstantsJSP.RESOURCE_BUNDLE);
            try (Connection cn = cm.getConnection()) {
                cm.startTransactions();
                ConferenceImplDB conferenceImplDB = new ConferenceImplDB(cn);
                ActivityImplDB activityImplDB = new ActivityImplDB(cn);
                List<Conference> confsList = conferenceImplDB.getEntities();
                for (Conference confs : confsList) {
                    conferenceImplDB.initConfsEvents(confs,
                            activityImplDB.getEventsListById(confs.getId()));
                }
                cm.commitConnection();
                cm.endTransaction();
                return confsList;
            }
        } catch (SQLException | ConnectionException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
