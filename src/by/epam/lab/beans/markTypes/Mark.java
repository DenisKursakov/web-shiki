package by.epam.lab.beans.markTypes;

import static by.epam.lab.utils.Constants.*;

public class Mark {
    private final int markValue;

    public Mark(int markValue) {
        this.markValue = markValue;
    }

    public Mark(String strMark) {
        this((int) (Double.parseDouble(strMark) * TEN_FOR_GET_INT));
    }

    protected String getStrMark(int markValue) {
        return String.valueOf(markValue / TEN_FOR_GET_INT);
    }

    public int getMarkValue() {
        return markValue;
    }


    @Override
    public String toString() {
        return getStrMark(markValue);
    }
}
