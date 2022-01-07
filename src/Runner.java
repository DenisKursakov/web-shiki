import by.epam.lab.Constants;
import by.epam.lab.beans.NumLen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try {
            Class.forName(Constants.FOR_NAME_WAY);
            Connection cn = null;
            Statement coordinatesStatement = null;
            PreparedStatement frequenciesStatement = null;
            ResultSet rs = null;
            try {
                cn = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
                //create empty statement for working with the coordinates table
                coordinatesStatement = cn.createStatement();
                //create resultSet a with table after sorting
                rs = coordinatesStatement.executeQuery(
                        Constants.SELECT_COORDINATES_TABLE_AFTER_SORTING);
                //create list with results from coordinates table after sorting
                List<NumLen> coordinatesList = new ArrayList<>();
                while (rs.next()) {
                    coordinatesList.add(new NumLen(
                            rs.getInt(Constants.LEN_ID_IN_COORDINATES_TABLE),
                            rs.getInt(Constants.NUM_ID_IN_COORDINATE_TABLE)));
                }

                //create prepare statement for working with the frequencies table
                frequenciesStatement = cn.prepareStatement(Constants.INSERT_INTO_FREQUENCIES_TABLE);
                //delete all data from the table frequencies
                frequenciesStatement.executeUpdate(Constants.TRUNCATE_FREQUENCIES_TABLE);
                //enter new data into the table frequencies using INSERT SQL query
                for (NumLen numLen : coordinatesList) {
                    frequenciesStatement.setInt(Constants.LEN_ID_IN_COORDINATES_TABLE,
                            numLen.getSegmentsLength());
                    frequenciesStatement.setInt(Constants.NUM_ID_IN_COORDINATE_TABLE,
                            numLen.getNumberOfSegments());
                    frequenciesStatement.executeUpdate();
                }

                //create a new result set with all data from frequencies table
                rs = frequenciesStatement.executeQuery(
                        Constants.SELECT_FREQUENCIES_TABLE);

                //print num len data from frequencies table before sorting
                printLenNum(rs, Constants.LEN_ID_IN_FREQUENCIES_TABLE,
                        Constants.NUM_ID_IN_FREQUENCIES_TABLE);

                //create a new result set with all data from frequencies table after sorting
                rs = frequenciesStatement.executeQuery(
                                Constants.SELECT_FREQUENCIES_TABLE_AFTER_SORTING);

                //print num len data from frequencies table after sorting
                printLenNum(rs, Constants.LEN_ID_IN_FREQUENCIES_TABLE,
                        Constants.NUM_ID_IN_FREQUENCIES_TABLE);
            } finally {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
                if (frequenciesStatement != null) {
                    frequenciesStatement.close();
                }

                if (coordinatesStatement != null) {
                    coordinatesStatement.close();
                }
                if (cn != null) {
                    cn.close();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printLenNum(ResultSet resultSet, int lenId, int numId) {
        try {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(lenId) +
                        Constants.SEMICOLON +
                        resultSet.getInt(numId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
