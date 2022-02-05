package by.epam.lab.factories;

import by.epam.lab.beans.HalfResult;
import by.epam.lab.beans.Result;

import java.sql.Date;

public class HalfFactoryResult extends ResultFactory {

    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new HalfResult(login, test, date, mark);
    }

    public Result getResultFromFactory(String login, String test, String date, String mark) {
        return new HalfResult(login, test, date, mark);
    }


    public double getScaledMark(double mark) {
        return mark / HalfResult.FACTOR;
    }
}
