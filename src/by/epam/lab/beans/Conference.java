package by.epam.lab.beans;

import java.sql.Date;


import static by.epam.lab.utils.ConstantsJSP.*;

public class Conference {
    private final String name;
    private final String faculty;
    private final Date date;

    public Conference(String name, String faculty, String date) {
        this.name = name;
        this.faculty = faculty;
        this.date = Date.valueOf(date);
    }

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name + DELIMITER + faculty + DELIMITER + date;
    }
}
