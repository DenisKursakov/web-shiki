package by.epam.lab.ifaces;

import by.epam.lab.beans.Conference;
import by.epam.lab.beans.Event;

import java.util.List;

public interface ConferenceDao {
    void initConfsEvents(Conference conference, List<Event> list);
}
