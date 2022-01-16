package by.epam.lab.beans;

import java.text.SimpleDateFormat;
import java.sql.Date;

import static by.epam.lab.Constants.*;

public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);


    public Result() {
        this(null, null, null, 0);
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), (int) (Double.parseDouble(mark) * TEN_FOR_GET_INT));
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

    public String getStringDate() {
        return OUTPUT_DATE_FORMAT.format(date);
    }

    public String getStringMark() {
        return String.format(FORMAT_MARK, mark / TEN_FOR_GET_INT, mark % TEN_FOR_GET_INT);
    }

    public int getMark() {
        return mark;
    }

    @Override
    public String toString() {
        return login + SEMICOLON + test + SEMICOLON + getStringDate()
                + SEMICOLON + getStringMark();
    }
}
