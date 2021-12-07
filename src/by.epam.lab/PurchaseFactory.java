package by.epam.lab;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;

public class PurchaseFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            @Override
            Purchase getPurchase(String[] elements) {
                return new Purchase(elements[0],
                        new Byn(Integer.parseInt(elements[1])),
                        Integer.parseInt(elements[2]));
            }
        },
        DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(String[] elements) {
                return new PriceDiscountPurchase(elements[0],
                        new Byn(Integer.parseInt(elements[1])),
                        Integer.parseInt(elements[2]),
                        new Byn(Integer.parseInt(elements[3])));
            }
        };

        abstract Purchase getPurchase(String[] elements);
    }

    public static Purchase getPurchaseFromFactory(String currentLine) throws CsvLineException {
        String[] elements = currentLine.split(Constants.SEMICOLON);
        if (elements.length < Constants.MIN_IN_LINE_LENGTH
                || elements.length > Constants.MAX_IN_LINE_LENGTH) {
            throw new CsvLineException(currentLine, Causes.WRONG_ARGUMENT);
        }
        for (int i = 0; i < elements.length; i++) {
            switch (i) {
                case Constants.IN_LINE_NAME:
                    if (elements[i].isEmpty()) {
                        throw new CsvLineException(currentLine, Causes.EMPTY_NAME);
                    }
                    break;
                case Constants.IN_LINE_PRICE:
                    checkNonPositiveEx(currentLine, elements[i], Constants.PRICE);
                    break;
                case Constants.IN_LINE_NUMBER:
                    checkNonPositiveEx(currentLine, elements[i], Constants.NUMBER);
                    break;
                case Constants.IN_LINE_DISCOUNT:
                    checkNonPositiveEx(currentLine, elements[i], Constants.DISCOUNT);
                    break;
            }

        }
        //check the string by discount purchase regex
        try {
            if (elements.length == Constants.MAX_IN_LINE_LENGTH &&
                    Integer.parseInt(elements[Constants.IN_LINE_PRICE]) <=
                            Integer.parseInt(elements[Constants.IN_LINE_DISCOUNT])) {
                throw new CsvLineException(currentLine, Causes.DISCOUNT_MORE_OR_EQUAL_PRICE);
            }
            return elements.length == 4 ? PurchaseKind.DISCOUNT_PURCHASE.getPurchase(elements) :
                    PurchaseKind.GENERAL_PURCHASE.getPurchase(elements);
        } catch (NumberFormatException e) {
            throw new CsvLineException(currentLine, Causes.WRONG_ARGUMENT);
        } catch (NullPointerException e) {
            throw new CsvLineException(currentLine, Causes.NULL_POINTER);
        }

    }

    private static void checkNonPositiveEx(String currentLine, String element, String lineNumber) {
        if (element.contains(Constants.MINUS)
                || element.equals(Constants.ZERO_STRING)) {
            throw new NonPositiveArgumentException(
                    new NumFields(lineNumber, element, currentLine));

        }
    }

    private static void checkWrongArgumentEx(String currentLine, String element)
            throws CsvLineException {
        if (!element.matches(Constants.NUMBER_REGEX) || element.isEmpty()) {
            throw new CsvLineException(currentLine, Causes.WRONG_ARGUMENT);
        }
    }
}
