package by.epam.lab;

import by.epam.lab.beans.*;
import by.epam.lab.exceptions.*;

public class PurchaseFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            @Override
            Purchase getPurchase(String[] elements) {
                return new Purchase(elements[0],
                        Integer.parseInt(elements[1]),
                        Integer.parseInt(elements[2]));
            }
        },
        DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(String[] elements) {
                return new PriceDiscountPurchase(elements[0],
                        Integer.parseInt(elements[1]),
                        Integer.parseInt(elements[2]),
                        Integer.parseInt(elements[3]));
            }
        };

        abstract Purchase getPurchase(String[] elements) throws CsvLineException;
    }

    public static Purchase getPurchaseFromFactory(String currentLine) throws CsvLineException {
        String[] elements = currentLine.split(Constants.SEMICOLON);
        if (elements.length < Constants.MIN_IN_LINE_LENGTH
                || elements.length > Constants.MAX_IN_LINE_LENGTH) {
            throw new CsvLineException(currentLine, Causes.WRONG_NUMBER_ARGUMENT);
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
            throw new CsvLineException(currentLine, Causes.WRONG_INTEGER_ARGUMENT);
        } catch (NullPointerException e) {
            throw new CsvLineException(currentLine, Causes.EMPTY_NAME);
        } catch (NonPositiveArgumentException e){
            throw new CsvLineException(currentLine + e, Causes.WRONG_NUMBER_ARGUMENT);
        }

    }
}
