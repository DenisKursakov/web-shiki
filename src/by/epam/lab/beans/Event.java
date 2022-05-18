package by.epam.lab.beans;

import static by.epam.lab.utils.ConstantsJSP.*;

public class Event extends Entity{
    private final String stage;
    private final String time;

    public Event(long id, String stage, String time) {
        super(id);
        this.stage = stage;
        this.time = time;
    }

    public long getId() {
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
