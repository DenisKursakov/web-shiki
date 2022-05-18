package by.epam.lab.services;

import by.epam.lab.exceptions.ConnectionException;
import by.epam.lab.exceptions.ServiceException;
import by.epam.lab.utils.ConnectionManager;

import java.sql.*;

import static by.epam.lab.utils.ConstantsJSP.*;

public class RegisteredService {
    public void saveRegistration(String name, int[] eventsId, int confId) throws ServiceException {
        try {
            ConnectionManager cm = new ConnectionManager(RESOURCE_BUNDLE);
            try (Connection cn = cm.getConnection()) {
                cm.startTransactions();
                try (Statement st = cn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(String.format(SELECT_ID_STUD, name));
                         PreparedStatement studentsInsert = cn.prepareStatement(INSERT_INTO_STUD_TABLE);
                         PreparedStatement insertRegistered = cn.prepareStatement(INSERT_INTO_REG_TABLE)) {
                        if (!rs.next()) {
                            studentsInsert.setString(1, name);
                            studentsInsert.addBatch();
                        }
                        studentsInsert.executeBatch();
                        for (int i : eventsId) {
                            insertRegistered.setInt(1, i);
                            insertRegistered.setString(2, name);
                            insertRegistered.addBatch();
                        }
                        insertRegistered.executeBatch();
                    } catch (SQLException e) {
                        cm.rollbackTransaction();
                        throw new SQLException(e.getMessage());
                    }
                    cm.commitConnection();
                    cm.endTransaction();
                }
            }
        } catch (ConnectionException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
