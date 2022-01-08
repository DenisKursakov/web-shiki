import by.epam.lab.Constants;
import by.epam.lab.beans.LenNum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try (Connection cn = DriverManager.getConnection(
                Constants.URL, Constants.USER, Constants.PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps =
                     cn.prepareStatement(Constants.INSERT_INTO_FREQUENCIES_TABLE)) {
            try (ResultSet rs = st.executeQuery(Constants.SELECT_COORDINATES_TABLE_AFTER_SORTING)) {
                Class.forName(Constants.FOR_NAME_WAY);
                //create list with results from coordinates table after sorting
                List<LenNum> lenNumList = new ArrayList<>();
                while (rs.next()) {
                    LenNum lenNum = new LenNum(rs.getInt(Constants.LEN_IND),
                            rs.getInt(Constants.NUM_IND));
                    lenNumList.add(lenNum);
                    System.out.println(lenNum);
                }
                //delete all data from the table frequencies
                st.executeUpdate(Constants.DELETE_ALL_FROM_FREQUENCIES_TABLE);
                //enter new data into the table frequencies using INSERT SQL query
                for (LenNum numLen : lenNumList) {
                    ps.setInt(Constants.LEN_ID_FOR_SET,
                            numLen.getSegmentsLength());
                    ps.setInt(Constants.NUM_ID_FOR_SET,
                            numLen.getNumberOfSegments());
                    ps.addBatch();
                }
            }
            ps.executeBatch();
            //create a new result set with all data from frequencies table after sorting
            try (ResultSet rs = st.executeQuery(Constants.SELECT_FREQUENCIES_TABLE_AFTER_SORTING)) {
                //print num len data from frequencies table after sorting
                while (rs.next()) {
                    System.out.println(rs.getInt(Constants.LEN_ID_IN_FREQUENCIES_TABLE) +
                            Constants.SEMICOLON + rs.getInt(Constants.NUM_ID_IN_FREQUENCIES_TABLE));
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }
}
