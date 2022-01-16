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
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            results.add(new Result(currentLogin, attributes.getValue(TEST_ID),
                    attributes.getValue(DATE_ID), attributes.getValue(MARK_ID)));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (currentEnum == ResultEnum.LOGIN) {
            String currentStr = new String(ch, start, length).trim();
            if (!currentStr.isEmpty()) {
                currentLogin = currentStr;
            }
        }
    }
}
