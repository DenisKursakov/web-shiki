package by.epam.lab.implementations;

import by.epam.lab.exceptions.InitRuntimeException;
import by.epam.lab.utils.ConstantsJSP;

import java.sql.*;

public class DBImpl implements NumberDAO {

    @Override
    public double[] getNumbers() {
        double[] numbers = new double[ConstantsJSP.ARRAY_LENGTH];
        try {
            Class.forName(ConstantsJSP.CLASS_FOR_NAME);
            try (Connection cn = DriverManager.getConnection(ConstantsJSP.URL, ConstantsJSP.USER, ConstantsJSP.PASSWORD);
                 Statement st = cn.createStatement()) {
                try (ResultSet rs = st.executeQuery(ConstantsJSP.GET_DB_NUMBER_REQUEST)) {
                    int i = 0;
                    while (rs.next()) {
                        numbers[i] = rs.getDouble(1);
                        i++;
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e){
            throw new InitRuntimeException(e.getMessage());
        }
        return numbers;
    }
}
