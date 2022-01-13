package by.epam.lab.beans;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.Constants.*;

public class ResultsHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }

    private final List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String currentLogin;

    public List<Result> getResults() {
        return results;
    }

    @Override
    public void setDocumentLocator(Locator locator) {

    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts)
            throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            Result currentResult = new Result();
            currentResult.setLogin(currentLogin);
            currentResult.setTest(atts.getValue(TEST_ID));
            currentResult.setDate(Date.valueOf(atts.getValue(DATE_ID)));
            currentResult.setMark(Integer.parseInt(
                    new StringBuilder(atts.getValue(MARK_ID)).
                            delete(ID_START_POINT_DELETE,ID_FINISH_POINT_DELETE).toString()));
//            currentResult.setMark(
//                    (int) (Double.parseDouble(atts.getValue(MARK_ID)) * TEN_FOR_GET_INT));

//            currentResult.setMark(Integer.parseInt(atts.getValue(MARK_ID).
//                    replaceAll("\\.","")));
            results.add(currentResult);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String currentStr = new String(ch, start, length).trim();
        if (currentEnum == ResultEnum.LOGIN) {
            if(!currentStr.isEmpty()) {
                currentLogin = currentStr;
            }
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {

    }

    @Override
    public void skippedEntity(String name) throws SAXException {

    }
}
