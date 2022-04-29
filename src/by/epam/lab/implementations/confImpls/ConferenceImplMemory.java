package by.epam.lab.implementations.confImpls;

import java.util.*;

import by.epam.lab.exceptions.DaoException;
import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.model.beans.Conference;
import by.epam.lab.model.beans.Event;

import javax.servlet.ServletConfig;

public class ConferenceImplMemory implements ConferenceDAO {

	private static Map<Integer, Conference> confs = getInitialConferences();
	private static Map<Integer, List<Event>> events = getInitialEvents();

	public ConferenceImplMemory(String param, ResourceBundle rb) {
	}
	private static Map<Integer, Conference> getInitialConferences() {
		confs = new HashMap<>();
		confs.put(1,  new Conference("IT Planet", "Physics and IT", "25.11"));
		confs.put(2,  new Conference("Math readings", "Math and PT", "10.12"));
		confs.put(3,  new Conference("Live Issues of Physics", "Physics and IT", "15.12"));
		confs.put(4,  new Conference("Java day", "Physics and IT", "23.12"));
		return confs;
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
	
	public Map<Integer, Conference> getConferences(){
		return confs;
	}

	public List<Event> getEventsById(int id) throws DaoException {
		if(id == 3) {
			throw new DaoException("Error simulation...");
		}
		return events.get(id);
	}
}
