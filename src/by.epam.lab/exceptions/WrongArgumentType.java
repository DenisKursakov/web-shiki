package by.epam.lab.exceptions;

import by.epam.lab.Constants;
import by.epam.lab.beans.Causes;

public class WrongArgumentType extends NumberFormatException {
    private final String wrongArgument;
    private final String fieldName;

    public WrongArgumentType() {
        this(null, null);
    }

    public WrongArgumentType(String wrongArgument, String fieldName) {
        this.wrongArgument = wrongArgument;
        this.fieldName = fieldName;
    }

    public String getWrongArgument() {
        return wrongArgument;
    }

    public String getFieldName() {
        return fieldName;
    }

    @Override
    public String toString() {
        return wrongArgument + Constants.IN + fieldName;
    }
}
