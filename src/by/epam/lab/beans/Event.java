package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsJSP.*;

public class Event {
    private final int id;
    private final String stage;
    private final String time;

    public Event(int id, String stage, String time) {
        this.id = id;
        this.stage = stage;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getStage() {
        return stage;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return id + DELIMITER + stage + DELIMITER + time;
    }
}
