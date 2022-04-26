package by.epam.lab.implementations;

import by.epam.lab.exceptions.InitException;
import by.epam.lab.exceptions.InitRuntimeException;
import static by.epam.lab.utils.ConstantsJSP.*;

import javax.servlet.ServletConfig;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBImpl implements NumberDAO {
    private final String dbName;
    private final String user;
    private final String password;


    static {
        try {
            Class.forName(CLASS_FOR_NAME);
        } catch (ClassNotFoundException e) {
            throw new InitRuntimeException(e.getMessage());
        }
    }

    public DBImpl(String param, ServletConfig sc) {
        String[] elements = param.split(DELIMITER);
        this.dbName = elements[DB_NAME_ID];
        this.user = elements[USER_ID];
        this.password = elements[PASSWORD_ID];
    }

    @Override
    public List<Double> getNumbers() throws InitException {
        List<Double> numbers = new ArrayList<>();
        try {

            try (Connection cn = DriverManager.getConnection(URL + dbName, user, password);
                 Statement st = cn.createStatement()) {
                try (ResultSet rs = st.executeQuery(GET_DB_NUMBER_REQUEST)) {
                    while (rs.next()) {
                        numbers.add(rs.getDouble(1));
                    }
                }
            }
        } catch ( SQLException e){
            throw new InitException(e.getMessage());
        }
        return numbers;
    }
}
