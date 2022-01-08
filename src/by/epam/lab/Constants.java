package by.epam.lab;

public class Constants {
    public static final String INSERT_INTO_FREQUENCIES_TABLE =
            "INSERT INTO frequencies (len, num) VALUES (?,?)";
    public static final String PASSWORD = "root";
    public static final String USER = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/segments";
    public static final String FOR_NAME_WAY = "com.mysql.cj.jdbc.Driver";
    public static final String SEMICOLON = ";";
    public static final String SELECT_COORDINATES_TABLE_AFTER_SORTING =
            "SELECT round(abs(x2-x1)) as len," +
                    " COUNT(*) as num \n" +
                    "from coordinates\n" +
                    "group by len  \n" +
                    "order by len ASC;";
    public static final String DELETE_ALL_FROM_FREQUENCIES_TABLE = "DELETE FROM frequencies";
    public static final String SELECT_FREQUENCIES_TABLE_AFTER_SORTING = "SELECT * FROM frequencies "
            + "WHERE len > num";
    public static final String LEN_IND = "len";
    public static final String NUM_IND = "num";
    public static final int LEN_ID_IN_FREQUENCIES_TABLE = 2;
    public static final int NUM_ID_IN_FREQUENCIES_TABLE = 3;
    public static final int LEN_ID_FOR_SET = 1;
    public static final int NUM_ID_FOR_SET = 2;
}
