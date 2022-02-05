package by.epam.lab.beans;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static by.epam.lab.utils.Constants.*;

public class Result implements Comparable<Result> {
    private String login;
    private String test;
    private Date date;
    private int mark;
    private final static SimpleDateFormat GET_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_SIMPLE);
    private final static SimpleDateFormat SET_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);


    public Result() {

    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }


    public Result(String login, String test, String date, String mark) {
        this(login, test, toDate(date), Integer.parseInt(mark));
    }

    static Date toDate(String dateStr) {
        Date date;
        try {
            date = new Date(SET_DATE_FORMAT.parse(dateStr).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(WRONG_DATE_FORMAT);
        }
        return date;
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
        return GET_DATE_FORMAT.format(date);
    }

    public String getStringMark() {
        return EMPTY_STRING + mark;
    }

    public int getMark() {
        return mark;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + SEMICOLON + login + SEMICOLON + test + SEMICOLON +
                getStringDate() + SEMICOLON + getStringMark();
    }

    @Override
    public int compareTo(Result o) {
        return date.compareTo(o.date);
    }
}
