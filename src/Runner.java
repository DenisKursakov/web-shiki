import by.epam.lab.Constants;
import by.epam.lab.beans.LenNum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.Constants.*;

public class Runner {
    public static void main(String[] args) {
        try (Connection cn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = cn.createStatement();
             PreparedStatement ps =
                     cn.prepareStatement(INSERT_INTO_FREQUENCIES_TABLE)) {
            try (ResultSet rs = st.executeQuery(SELECT_COORDINATES_TABLE_AFTER_SORTING)) {
                //create list with results from coordinates table after sorting
                List<LenNum> lenNumList = new ArrayList<>();
                while (rs.next()) {
                    LenNum lenNum = new LenNum(rs.getInt(LEN_ID_FOR_SET),
                            rs.getInt(NUM_ID_FOR_SET));
                    lenNumList.add(lenNum);
                    System.out.println(lenNum);
                }
                //delete all data from the table frequencies
                st.executeUpdate(DELETE_ALL_FROM_FREQUENCIES_TABLE);
                //enter new data into the table frequencies using INSERT SQL query
                for (LenNum numLen : lenNumList) {
                    ps.setInt(LEN_ID_FOR_SET, numLen.getSegmentsLength());
                    ps.setInt(NUM_ID_FOR_SET, numLen.getNumberOfSegments());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
            //create a new result set with all data from frequencies table after sorting
            try (ResultSet rs = st.executeQuery(SELECT_FREQUENCIES_TABLE_AFTER_SORTING)) {
                //print num len data from frequencies table after sorting
                while (rs.next()) {
                    System.out.println(rs.getInt(LEN_ID_IN_FREQUENCIES_TABLE) +
                            SEMICOLON + rs.getInt(NUM_ID_IN_FREQUENCIES_TABLE));
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
