package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Locale;

public enum Causes {
    DISCOUNT_MORE_OR_EQUAL_PRICE(" discount more or equal price "),
    EMPTY_NAME(" empty name "),
    WRONG_NUMBER_ARGUMENT(" wrong number argument "),
    WRONG_ARGUMENT_TYPE(" wrong argument type "),
    NON_POSITIVE_ARGUMENT(" non positive argument ");

    private final String strWrongInfo;

    Causes(String strWrongInfo) {
        this.strWrongInfo = strWrongInfo;
    }

    public String getStrWrongInfo() {
        return strWrongInfo;
    }

    @Override
    public String toString() {
        return strWrongInfo;
    }
}
