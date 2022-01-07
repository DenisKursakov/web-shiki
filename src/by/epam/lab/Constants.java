package by.epam.lab;

public class Constants {
    public static final String INSERT_INTO_FREQUENCIES_TABLE =
            "INSERT INTO frequencies (len, num) VALUES (%d,%d)";
    public static final String PASSWORD = "Ltybc1996";
    public static final String USER = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/coordinates";
    public static final String FOR_NAME_WAY = "com.mysql.cj.jdbc.Driver";
    public static final String SEMICOLON = ";";
    public static final String SELECT_COORDINATES_TABLE_AFTER_SORTING =
            "SELECT round(sqrt((x1 - x2) * (x1 - x2))) as len," +
                    " COUNT(*) as num \n" +
                    "from coordinates\n" +
                    "group by len  \n" +
                    "order by num DESC;";
    public static final String SELECT_FREQUENCIES_TABLE = "SELECT * from frequencies";
    public static final String TRUNCATE_FREQUENCIES_TABLE = "TRUNCATE TABLE frequencies";
    public static final String SELECT_FREQUENCIES_TABLE_AFTER_SORTING = "SELECT * FROM frequencies "
            + "WHERE len > num";
    public static final int LEN_ID_IN_COORDINATES_TABLE = 1;
    public static final int NUM_ID_IN_COORDINATE_TABLE = 2;
    public static final int LEN_ID_IN_FREQUENCIES_TABLE = 2;
    public static final int NUM_ID_IN_FREQUENCIES_TABLE = 3;
}
