package by.epam.lab.utils;

public class ConstantsJSP {
    public static final String DELIMITER = ";";
    public static final String FILE_NAME_PARAM = "propertiesName";
    public static final String FILE_VALUE_PARAM =
            "confs";
    public static final String CONFERENCES = "conferences";
    public static final String ACCOUNT_NAME_PARAM = "account";
    public static final String FACULTY_NAME_PARAM = "faculty";
    public static final String EVENTS = "events";
    public static final String PROG_FILE = "/prog.jsp";
    public static final String INDEX_FILE = "/index.jsp";
    public static final String REG_FILE = "/reg.jsp";
    public static final String PASSWORD = "Ltybc1996";
    public static final String CLASS_FOR_NAME = "com.mysql.jdbc.Driver";
    public static final String USER = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/confs";
    public static final String SUCCESSFUL_REG = ", has been registered successful";
    public static final String WRONG_REG = "Unfortunately, your account has not been registered." +
            "Some problem with the data base source";
    public static final String SELECT_CONF_TABLE = "SELECT DISTINCT conferences.id, conferences.name, faculty.name, conferences.date FROM conferences\n" +
            "INNER JOIN faculty ON conferences.id_faculty = faculty.id;";
    public static final String SELECT_CONFS_ID = "SELECT conferences.id FROM conferences";
    public static final String SELECT_EVENTS = "SELECT DISTINCT events.id, eventsType.name, events.time FROM conferences\n" +
            "INNER JOIN events ON conferences.id = events.id_conf \n" +
            "INNER JOIN eventsType ON events.id_event_type = eventsType.id\n" +
            "where conferences.id = ";
    public static final String SELECT_EVENTS_PHYSIC_TABLE = "SELECT * FROM eventsPhysic";
    public static final String FACTORY_CONF_PARAM = "factory.conf";
    public static final String FACTORY_ACT_PARAM = "factory.activity";
    public static final String DB_NAME_PARAM  = "db.name";
    public static final String DB_USER_PARAM = "db.user";
    public static final String DB_PASS_PARAM = "db.password";
    public static final String ID_CONF_NAME = "idConf";
    public static final String CSV_FILE_ACT_NAME = "activity.csv.name";
}
