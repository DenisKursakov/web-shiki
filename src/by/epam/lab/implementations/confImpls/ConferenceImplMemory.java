package by.epam.lab.implementations.confImpls;

import java.util.*;

import by.epam.lab.ifaces.ConferenceDAO;
import by.epam.lab.model.beans.Conference;


public class ConferenceImplMemory implements ConferenceDAO {

	private static Map<Integer, Conference> confs = getInitialConferences();

	public ConferenceImplMemory(ResourceBundle rb) {
	}
	private static Map<Integer, Conference> getInitialConferences() {
		confs = new HashMap<>();
		confs.put(1,  new Conference("IT Planet", "Physics and IT", "2022-11-25"));
		confs.put(2,  new Conference("Math readings", "Math and PT", "2022-12-10"));
		confs.put(3,  new Conference("Live Issues of Physics", "Physics and IT","2022-12-15"));
		confs.put(4,  new Conference("Java day", "Physics and IT", "2022-12-23"));
		return confs;
	}

	public Map<Integer, Conference> getConferences(){
		return confs;
	}

}
