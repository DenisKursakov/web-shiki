package by.epam.lab.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


import static by.epam.lab.utils.ConstantsJSP.*;

public class Conference extends Entity {
    private final String name;
    private final String faculty;
    private final Date date;

    private List<Event> list = new ArrayList<>();

    public Conference(long id, String name, String faculty, String date) {
        super(id);
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

    public List<Event> getList() {
        return list;
    }

    public void setList(List<Event> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return name + DELIMITER + faculty + DELIMITER + date;
    }
}
