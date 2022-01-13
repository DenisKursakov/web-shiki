import by.epam.lab.Constants;
import by.epam.lab.beans.ResultsHandler;
import by.epam.lab.beans.Result;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class Runner {
    public static void main(String[] args) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ResultsHandler handler = new ResultsHandler();
            reader.setContentHandler(handler);
            reader.parse(Constants.WAY_RESULTS_XML);
            for (Result result: handler.getResults()) {
                System.out.println(result);
            }
        }catch (SAXException | IOException e){
            e.printStackTrace();
        }
    }
}
