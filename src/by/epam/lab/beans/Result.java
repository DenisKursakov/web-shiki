package by.epam.lab.beans;

import by.epam.lab.enums.LoginNames;
import by.epam.lab.factories.MarkStrFactory;
import by.epam.lab.enums.TestNames;

import java.sql.Date;
import java.text.SimpleDateFormat;

import static by.epam.lab.utils.Constants.*;

public class Result implements Comparable<Result> {
    private LoginNames login;
    private TestNames test;
    private Date date;
    private int mark;
    private String markStr;
    private final static SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);


    public Result() {

    }

    public Result(LoginNames login, TestNames test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
        this.markStr = String.valueOf(mark);
    }

    public Result(String login, String test, String date, int mark,
                  MarkStrFactory.MarkType markType) {
        this(
                LoginNames.valueOf(login.toUpperCase()),
                TestNames.valueOf(test.toUpperCase()),
                Date.valueOf(date),
                mark
        );
        markStr = MarkStrFactory.getMarkFromFactory(mark, markType);

    }

    public Result(String login, String test, String date, String mark) {
        this(
                LoginNames.valueOf(login.toUpperCase()),
                TestNames.valueOf(test.toUpperCase()),
                Date.valueOf(date),
                (int) (Double.parseDouble(mark) * TEN_FOR_GET_INT)
        );
    }

    public void setLogin(LoginNames login) {
        this.login = login;
    }

    public void setTest(TestNames test) {
        this.test = test;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public LoginNames getLogin() {
        return login;
    }

    public TestNames getTest() {
        return test;
    }

    public Date getDate() {
        return date;
    }

    public String getStringDate() {
        return String.valueOf(date);
    }


    public int getMark() {
        return mark;
    }


    @Override
    public String toString() {
        return login + SEMICOLON + test + SEMICOLON + OUTPUT_DATE_FORMAT.format(date)
                + SEMICOLON + markStr;
    }

    @Override
    public int compareTo(Result o) {
        return date.compareTo(o.date);
    }
}
