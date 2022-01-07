import by.epam.lab.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        try {
            Class.forName(Constants.FOR_NAME_WAY);
            Connection cn = null;
            Statement coordinatesStatement = null;
            Statement frequenciesStatement = null;
            ResultSet rs = null;
            try {
                cn = DriverManager.getConnection(Constants.URL, Constants.USER, Constants.PASSWORD);
                //create empty statement for working with the coordinates table
                coordinatesStatement = cn.createStatement();
                //create resultSet a with table after sorting
                ResultSet resultCoordinatesSet = coordinatesStatement.executeQuery(
                        Constants.SELECT_COORDINATES_TABLE_AFTER_SORTING);
                List<Integer[]> coordinatesList = new ArrayList<>();
                while (resultCoordinatesSet.next()) {
                    coordinatesList.add(new Integer[]{
                            resultCoordinatesSet.getInt(Constants.LEN_ID_IN_COORDINATES_TABLE),
                            resultCoordinatesSet.getInt(Constants.NUM_ID_IN_COORDINATE_TABLE)
                    });
                }

                //create empty statement for working with the frequencies table
                frequenciesStatement = cn.createStatement();
                //delete all data from the table frequencies
                frequenciesStatement.executeUpdate(Constants.TRUNCATE_FREQUENCIES_TABLE);
                //enter new data into the table frequencies using INSERT SQL query
                for (Integer[] element : coordinatesList) {
                    frequenciesStatement.executeLargeUpdate(String.format(
                            Constants.INSERT_INTO_FREQUENCIES_TABLE, element[0], element[1]));
                }

                //create a new result set with all data from frequencies table
                ResultSet resultFrequenciesSet = frequenciesStatement.executeQuery(
                        Constants.SELECT_FREQUENCIES_TABLE);
                //print num len data from frequencies table before sorting
                printLenNum(resultFrequenciesSet, Constants.LEN_ID_IN_FREQUENCIES_TABLE,
                        Constants.NUM_ID_IN_FREQUENCIES_TABLE);
                //create a new result set with all data from frequencies table after sorting
                ResultSet resultFrequenciesSetAfterSorting =
                        coordinatesStatement.executeQuery(
                                Constants.SELECT_FREQUENCIES_TABLE_AFTER_SORTING);
                //print num len data from frequencies table after sorting
                printLenNum(resultFrequenciesSetAfterSorting, Constants.LEN_ID_IN_FREQUENCIES_TABLE,
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
