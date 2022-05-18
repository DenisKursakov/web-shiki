package by.epam.lab.implementations;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.ifaces.GenericDao;
import by.epam.lab.beans.Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;


public abstract class AbstractDao <T extends Entity> implements GenericDao<T> {
    private Connection cn;

    public AbstractDao(Connection cn) {
        this.cn = cn;
    }

    public List<T> getEntities() {
        try (Statement st = cn.createStatement()) {
            try (ResultSet rs = st.executeQuery(getSelectEntitiesRequest())) {
                return parseAfterSelect(rs);
            }
        } catch (DaoException | SQLException e) {
            throw new InitRuntimeException(e.getMessage());
        }
    }

    public Optional<T> getEntityById(long id) throws DaoException {
        return Optional.empty();
    }



    protected abstract String getSelectEntitiesRequest ();
    protected abstract String getSelectByIdRequest ();
    protected abstract List<T> parseAfterSelect (ResultSet rs) throws DaoException;
}
