package by.epam.lab.beans;

import by.epam.lab.utils.Constants;

import java.sql.Date;

public class HalfResult extends Result {

    public static final int FACTOR = 2;

    public HalfResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    public HalfResult(String login, String test, String date, String mark) {
        super(login, test, toDate(date),
                (int) (Double.parseDouble(mark) * FACTOR));
    }

    public String getStringMark() {
        int mark = getMark();
        return (mark >> 1) + ((mark & 1) == 0 ? Constants.EMPTY_STRING :
                Constants.HALF_STRING_ELEMENT);
    }

}
