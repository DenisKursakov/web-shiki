package by.epam.lab.utils;

public class Constants {
    //SQL-queries
    public static final String LOGINS_TABLE_NAME = "logins";
    public static final String TESTS_TABLE_NAME = "tests";
    public static final String RESULTS_TABLE_NAME = "results";
    public static final String DELETE_ALL_FROM_TABLE_FORMAT = "TRUNCATE TABLE %s;";
    public static final String INSERT_INTO_LOGINS_TABLE = "INSERT INTO logins (name) VALUES (?);";
    public static final String INSERT_INTO_TESTS_TABLE = "INSERT INTO tests (name) VALUES (?);";
    public static final String INSERT_INTO_RESULTS_TABLE =
            "INSERT INTO results (loginId, testId, dat, mark) VALUES (?,?,?,?)";
    public static final String SELECT_LOGIN_TABLE = "SELECT * FROM logins WHERE name = '%s';";
    public static final String SELECT_TESTS_TABLE = "SELECT * FROM tests WHERE name = '%s';";
    public static final String SELECT_RESULT_TABLE_AFTER_SORTED_BY_MEAN_MARK =
            "SELECT DISTINCT logins.name as " +
            "log_name, round(AVG(mark),2) AS mean_mark FROM results " +
            "INNER JOIN logins ON results.loginId = logins.idLogin " +
            "GROUP BY log_name " +
            "ORDER BY mean_mark DESC;";
    public static final String SELECT_RESULT_TABLE_AFTER_SORTED_BY_DATE =
            "SELECT logins.name, tests.name, dat, mark FROM results\n" +
                    "    INNER JOIN tests ON results.testId = tests.idTest\n" +
                    "    INNER JOIN logins ON results.loginId = logins.idLogin\n" +
                    "    order by dat asc";
    //other constants
    public static final String REQUIRED_MONTH_IS_NOT_FOUND = "Required month is not found";
    public static final String FILE_NAME_FOR_TASK1 = "src/results.csv";
    public static final String FILE_NAME_FOR_TASK2 = "src/results.xml";
    public static final String FILE_NAME_FOR_TASK3 = "src/results2.csv";
    public static final String SEMICOLON = ";";
    public static final String DIVIDING_LINE = "-----------------------------";
    public static final String FORMAT_MARK = "%d.%d";
    public static final String FORMAT_FOR_AVG_MARK_TABLE = "%s:%.2f\n";
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    public static final String PRINT_TESTS_LAST_DAY = "Tests which passed in the last day";
    public static final int TEST_ID = 0;
    public static final int DATE_ID = 1;
    public static final int MARK_ID = 2;
    public static final int LOGIN_ID_FOR_STATEMENT_SET = 1;
    public static final int TEST_ID_FOR_STATEMENT_SET = 2;
    public static final int DATE_ID_FOR_STATEMENT_SET = 3;
    public static final int MARK_ID_FOR_STATEMENT_SET = 4;
    public static final int NAME_ID_FOR_SET_LOG_TEST = 1;
    public static final int LOGIN_ID_IN_ARRAY = 0;
    public static final int TEST_ID_IN_ARRAY = 1;
    public static final int DATE_ID_IN_ARRAY = 2;
    public static final int MARK_ID_IN_ARRAY = 3;
    public static final int MEAN_MARK_ID = 2;
    public static final int TEN_FOR_GET_INT = 10;
    public static final String PASSWORD = "jse";
    public static final String USER = "jse";
    public static final String URL = "jdbc:mysql://localhost:3306/results";
}
