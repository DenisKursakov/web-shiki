package by.epam.lab.beans;

import by.epam.lab.factories.ResultFactory;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import static by.epam.lab.utils.Constants.*;

public class ResultsHandler extends DefaultHandler {
    public enum ResultEnum {
        LOGIN, TEST, RESULTS, STUDENT, TESTS
    }

    private final List<Result> resultsList = new ArrayList<>();
    private String currentLogin;
    private ResultEnum resultEnum;
    private ResultFactory resultFactory;

    public List<Result> getResultsList() {
        return resultsList;
    }

    public ResultsHandler(ResultFactory resultFactory) {
        super();
        this.resultFactory = resultFactory;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        resultEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (resultEnum == ResultEnum.TEST) {
            Result result = resultFactory.getResultFromFactory(
                    currentLogin,
                    attributes.getValue(TEST_ID),
                    attributes.getValue(DATE_ID),
                    attributes.getValue(MARK_ID));
            resultsList.add(result);
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
