package by.epam.lab.beans;

public class NumFields {
    private String fieldName;
    private String wrongValue;
    private String currentLine;

    public NumFields() {

    }

    public NumFields(String fieldName, String wrongValue, String currentLine) {
        this.fieldName = fieldName;
        this.wrongValue = wrongValue;
        this.currentLine = currentLine;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getWrongValue() {
        return wrongValue;
    }

    public void setWrongValue(String wrongValue) {
        this.wrongValue = wrongValue;
    }

    public String getCurrentLine() {
        return currentLine;
    }

    public void setCurrentLine(String currentLine) {
        this.currentLine = currentLine;
    }

    @Override
    public String toString() {
        return fieldName + ";" + wrongValue;
    }
}
