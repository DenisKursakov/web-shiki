package by.epam.lab.implementations.activityImpls;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.factories.ConferenceFactory;
import by.epam.lab.ifaces.ActivityDAO;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.model.beans.Conference;
import by.epam.lab.model.beans.Event;

import java.util.*;

public class ActivityMemoryImpl implements ActivityDAO {
    private static Map<Integer, List<Event>> events = getInitialEvents();

    public ActivityMemoryImpl ( ResourceBundle rb) {

    }

    private static Map<Integer, List<Event>> getInitialEvents() {
        int id = 1;
        events = new HashMap<>();
        events.put(1,
                Arrays.asList(
                        new Event(id++, "Arrival", "8:00"),
                        new Event(id++, "Opening", "10:00"),
                        new Event(id++, "Plenary session", "10:15"),
                        new Event(id++, "Dinner", "13:00"),
                        new Event(id++, "Sections", "14:00"),
                        new Event(id++, "Discussion", "17:00"),
                        new Event(id++, "Party", "18:00")
                )
        );
        events.put(2,
                Arrays.asList(
                        new Event(id++, "Arrival", "9:30"),
                        new Event(id++, "Opening", "10:00"),
                        new Event(id++, "Plenary session", "11:00"),
                        new Event(id++, "Discussion", "13:00"),
                        new Event(id++, "Closing", "14:00")
                )
        );
        events.put(3,
                Arrays.asList(
                        new Event(id++, "Opening", "14:00"),
                        new Event(id++, "Sections", "14:30"),
                        new Event(id++, "Party", "18:00")
                )
        );
        events.put(4,
                Arrays.asList(
                )
        );
        return events;
    }

    public List<Event> getEventsById(int id) throws DaoException {
        if(id == 3) {
            throw new DaoException("Error simulation...");
        }
        return events.get(id);
    }

    @Override
    public void saveRegistration(String name, int[] eventsId, int confId) throws DaoException {
        System.out.println(name);
        List<Event> list = events.get(confId);
        list.stream()
                .filter(e -> Arrays
                        .stream(eventsId)
                        .anyMatch(i -> e.getId() == i))
                .forEach(System.out::println);

    }

}
