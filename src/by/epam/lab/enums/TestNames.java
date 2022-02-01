package by.epam.lab.enums;

public enum TestNames {
    OOP,
    XML,
    JDBC,
    COLLECTIONS;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
