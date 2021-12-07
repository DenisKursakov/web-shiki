package by.epam.lab.beans;

import by.epam.lab.Constants;

import java.util.Locale;

public enum Causes {
    DISCOUNT_MORE_OR_EQUAL_PRICE,
    EMPTY_NAME,
    WRONG_NUMBER_ARGUMENT,
    WRONG_INTEGER_ARGUMENT;

    @Override
    public String toString() {
        return name().replaceAll(Constants.UNDERSCORE, Constants.SPACES).toLowerCase(Locale.ROOT) ;
    }
}
