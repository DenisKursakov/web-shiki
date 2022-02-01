package by.epam.lab.enums;

public enum LoginNames {
    COOL,
    CLEVER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
