package by.epam.lab.beans;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedList;
import java.util.List;
import static by.epam.lab.Constants.*;

public class ResultsHandler extends DefaultHandler {
    public enum ResultEnum {
        LOGIN, TEST, RESULTS, STUDENT, TESTS
    }
    private final List<Result> resultsList = new LinkedList<>();
    private String currentLogin;
    private ResultEnum resultEnum;

    public List<Result> getResultsList () {
        return resultsList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        resultEnum = ResultEnum.valueOf(localName.toUpperCase());
        if(resultEnum == ResultEnum.TEST) {
            resultsList.add(new Result(currentLogin, attributes.getValue(TEST_ID),
                    attributes.getValue(DATE_ID), attributes.getValue(MARK_ID)));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (resultEnum == ResultEnum.LOGIN) {
            String currentStr = new String(ch, start, length).trim();
            if (!currentStr.isEmpty()) {
                currentLogin = currentStr;
            }
        }
    }
}
