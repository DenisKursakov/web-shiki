package by.gsu.epamlab;

import java.util.Locale;

public enum WeekDay {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return getName();
    }
}
