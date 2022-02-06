package by.epam.lab.beans.resultsImp;

import by.epam.lab.beans.Result;
import by.epam.lab.beans.ResultsHandler;
import by.epam.lab.exceptions.ParseRuntimeException;
import by.epam.lab.exceptions.SourceException;
import by.epam.lab.factories.ResultFactory;
import by.epam.lab.interfaces.ResultDao;
import by.epam.lab.utils.Constants;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Iterator;

public class ResultImplXml implements ResultDao {
    private Iterator<Result> resultIterator;

    public ResultImplXml(String xmlName, ResultFactory resultFactory) throws SourceException {
        try {
            String fullXmlFileName = Constants.SOURCE_DIR + xmlName + Constants.XML;
            XMLReader reader = XMLReaderFactory.createXMLReader();
            ResultsHandler handler = new ResultsHandler(resultFactory);
            reader.setContentHandler(handler);
            reader.parse(fullXmlFileName);
            resultIterator = handler.getResultsList().iterator();
        } catch (IOException e) {
            throw new SourceException(e.getMessage());
        } catch (SAXException e) {
            throw new ParseRuntimeException(Constants.WRONG_DATA_IN_XML_FILE);
        }
    }

    @Override
    public Result nextResult() {
        return resultIterator.next();
    }

    @Override
    public boolean hasNextResult() {
        return resultIterator.hasNext();
    }

    @Override
    public void close() {
        resultIterator = null;
    }
}
