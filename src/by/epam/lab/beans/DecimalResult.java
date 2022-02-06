package by.epam.lab.beans;

import by.epam.lab.utils.Constants;

import java.sql.Date;

public class DecimalResult extends Result {
    public final static int FACTOR = 10;

    public DecimalResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    public DecimalResult(String login, String test, String date, String mark) {
        this(login, test, toDate(date), (int) (Double.parseDouble(mark) * FACTOR));
    }

    public String getStringMark() {
        int mark = getMark();
        return String.format(Constants.FORMAT_MARK, mark / FACTOR, mark % FACTOR);
    }
}
