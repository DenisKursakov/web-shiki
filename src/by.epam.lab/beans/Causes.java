package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Locale;

public enum Causes {
    DISCOUNT_MORE_OR_EQUAL_PRICE(Constants.DISCOUNT_MORE_OR_EQUAL_PRICE),
    EMPTY_NAME(Constants.EMPTY_NAME),
    WRONG_NUMBER_ARGUMENT(Constants.WRONG_NUMBER_ARGUMENT),
    WRONG_ARGUMENT_TYPE(Constants.WRONG_ARGUMENT_TYPE),
    NON_POSITIVE_ARGUMENT(Constants.NON_POSITIVE_ARGUMENT);

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
