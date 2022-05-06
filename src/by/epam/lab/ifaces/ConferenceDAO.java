package by.epam.lab.ifaces;

import by.epam.lab.model.beans.Conference;

import java.util.Map;

public interface ConferenceDAO {
    Map<Integer, Conference> getEntity();
}
