package by.epam.lab.beans;

import java.util.Date;

import static by.epam.lab.Constants.*;

public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result() {
        this(null, null, null, 0);
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getLogin() {
        return login;
    }

    public String getTest() {
        return test;
    }

    public Date getDate() {
        return date;
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return login + SEMICOLON + test + SEMICOLON + date + SEMICOLON
                + String.format(FORMAT_MARK, mark / TEN_FOR_GET_INT, mark % TEN_FOR_GET_INT);
    }
}
