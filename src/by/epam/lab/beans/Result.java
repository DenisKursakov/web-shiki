package by.epam.lab.beans;

import by.epam.lab.beans.markTypes.Mark;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static by.epam.lab.utils.Constants.*;

public class Result implements Comparable<Result> {
    private String login;
    private String test;
    private Date date;
    private Mark mark;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);


    public Result() {

    }

    public Result(String login, String test, Date date, Mark mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public Result(String login, String test, Date date, int mark) {
        this(login, test, date, new Mark(mark));
    }

    public Result(String[] elements) {
        this(elements[LOGIN_ID_IN_ARRAY], elements[TEST_ID_IN_ARRAY],
                Date.valueOf(elements[DATE_ID_IN_ARRAY]), new Mark(elements[MARK_ID_IN_ARRAY]));
    }

    public Result(String login, String test, String date, String mark) {
        this(login, test, Date.valueOf(date), new Mark(mark));
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

    public void setMark(Mark mark) {
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
        return String.valueOf(date);
    }


    public Mark getMark() {
        return mark;
    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + SEMICOLON + login + SEMICOLON + test + SEMICOLON +
                OUTPUT_DATE_FORMAT.format(date) + SEMICOLON + mark;
    }

    @Override
    public int compareTo(Result o) {
        return date.compareTo(o.date);
    }
}
