package by.epam.lab.exceptions;

import by.epam.lab.Constants;
import by.epam.lab.beans.Causes;

public class WrongArgumentType extends NumberFormatException {
    private final String wrongArgument;
    private final String fieldName;
    private final String currentLine;

    public WrongArgumentType() {
        this(null, null, null);
    }

    public WrongArgumentType(String wrongArgument, String fieldName, String currentLine) {
        this.wrongArgument = wrongArgument;
        this.fieldName = fieldName;
        this.currentLine = currentLine;
    }

    public String getWrongArgument() {
        return wrongArgument;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getCurrentLine() {
        return currentLine;
    }

    @Override
    public String toString() {
        return currentLine + Constants.ARROW + Causes.WRONG_ARGUMENT_TYPE
                + Constants.SPACE + wrongArgument + Constants.IN + fieldName;
    }
}
