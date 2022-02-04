package by.epam.lab.beans.resultsImp;

import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultsHandler;
import by.epam.lab.interfaces.ResultDao;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ResultImplXml implements ResultDao {
    private List<Result> resultList;
    private int currentId = 0;

    public ResultImplXml(String xmlName) {
        try {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ResultsHandler handler = new ResultsHandler();
            reader.setContentHandler(handler);
            try {
                reader.parse(xmlName);
                resultList = handler.getResultsList();
            } catch (FileNotFoundException e) {
                resultList = new LinkedList<>();
                System.err.println(e);
            }
        } catch (SAXException | IOException e) {
            System.err.println(e);
        }
    }

    @Override
    public Result nextResult() {
        Result result = resultList.get(currentId);
        if (hasNextResult()) {
            currentId++;
        }
        return result;
    }

    @Override
    public boolean hasNextResult() {
        return currentId < resultList.size() && resultList.get(currentId) != null;
    }

    @Override
    public void close() {

    }
}
