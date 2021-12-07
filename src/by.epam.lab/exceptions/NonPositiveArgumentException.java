package by.epam.lab.exceptions;

import by.epam.lab.Constants;
import by.epam.lab.beans.NumFields;

public class NonPositiveArgumentException extends IllegalArgumentException {
    private final String wrongField;
    private final String wrongElement;

    public NonPositiveArgumentException(){
        this(null,null);
    }
    public NonPositiveArgumentException(String wrongField, String wrongElement) {
        this.wrongField = wrongField;
        this.wrongElement = wrongElement;
    }

    public String getWrongField() {
        return wrongField;
    }

    public String getWrongElement() {
        return wrongElement;
    }

    @Override
    public String toString() {
        return Constants.ARROW + Constants.NON_POSITIVE +
                wrongElement + Constants.IN + wrongField;
    }
}
