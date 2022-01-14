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

    private final List<by.epam.lab.beans.Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String currentLogin;

    public List<by.epam.lab.beans.Result> getResults() {
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
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            by.epam.lab.beans.Result currentResult = new by.epam.lab.beans.Result();
            currentResult.setLogin(currentLogin);
            currentResult.setTest(attributes.getValue(TEST_ID));
            currentResult.setDate(Date.valueOf(attributes.getValue(DATE_ID)));
            currentResult.setMark(
                    (int) (Double.parseDouble(attributes.getValue(MARK_ID)) * TEN_FOR_GET_INT));
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
            if (!currentStr.isEmpty()) {
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
