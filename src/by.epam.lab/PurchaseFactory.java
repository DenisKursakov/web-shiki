package by.epam.lab;

public class PurchaseFactory {
    private enum PurchaseKind {
        GENERAL_PURCHASE {
            @Override
            Purchase getPurchase(String[] elements) {
                return new Purchase(elements);
            }
        },
        DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(String[] elements) {
                return new PriceDiscountPurchase(elements);
            }
        };

        abstract Purchase getPurchase(String[] elements);
    }

    public static Purchase getPurchaseFromFactory(String currentLine) {
        final String ERROR_DISCOUNT_MORE = currentLine + Constants.ARROW
                + "discount more or equal price";
        final String NON_POSITIVE = currentLine + Constants.ARROW + "non positive value ";
        final String WRONG_NUMBER = currentLine + Constants.ARROW + " wrong of arguments";
        final String WRONG_NAME = currentLine + Constants.ARROW + " wrong name";
        final String EMPTY_NAME = currentLine + Constants.ARROW + " empty name";
        final String GENERAL_PURCHASE_REGEX = Constants.NAME_REGEX + Constants.SEMICOLON
                + Constants.NUMBER_REGEX + Constants.SEMICOLON + Constants.NUMBER_REGEX;
        final String DISCOUNT_PURCHASE_REGEX = GENERAL_PURCHASE_REGEX + Constants.SEMICOLON
                + Constants.NUMBER_REGEX;
        String[] elements = currentLine.split(Constants.SEMICOLON);
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].contains(Constants.POINT)) {
                System.err.println(WRONG_NUMBER);
                break;
            }
            switch (i) {
                case Constants.IN_LINE_NAME:
                    if (elements.length <= Constants.IN_LINE_NUMBER) {
                        System.err.println(WRONG_NAME);
                    }
                    if (elements[i].matches(Constants.NON_WORDS_REGEX)) {
                        System.err.println(WRONG_NAME);
                        break;
                    } else if (elements[i].isEmpty()) {
                        System.err.println(EMPTY_NAME);
                    }
                    break;
                case Constants.IN_LINE_PRICE:
                    if (elements[i].contains(Constants.MINUS)
                            || elements[i].equals(Constants.ZERO_STRING)) {
                        System.err.println(NON_POSITIVE + elements[i] + Constants.IN_PRICE);
                        break;
                    } else if (elements[i].matches(Constants.NAME_REGEX)
                            || elements[i].isEmpty()) {
                        System.err.println(WRONG_NUMBER);
                    }
                    break;
                case Constants.IN_LINE_NUMBER:
                    if (elements[i].contains(Constants.MINUS)
                            || elements[i].equals(Constants.ZERO_STRING)) {
                        System.err.println(NON_POSITIVE + elements[i] + Constants.IN_NUMBER);
                        break;
                    } else if (elements[i].matches(Constants.NAME_REGEX)
                            || elements[i].isEmpty()) {
                        System.err.println(WRONG_NUMBER);
                    }
                    break;
                case Constants.IN_LINE_DISCOUNT:
                    if (elements[i].contains(Constants.MINUS)
                            || elements[i].equals(Constants.ZERO_STRING)) {
                        System.err.println(NON_POSITIVE + Constants.IN_DISCOUNT);
                        break;
                    } else if (elements[i].matches(Constants.NAME_REGEX)
                            || elements[i].isEmpty()) {
                        System.err.println(WRONG_NUMBER);
                    }
                    break;
            }
        }
        Purchase currentPurchase = null;
        //check the string by discount purchase regex
        if (currentLine.matches(DISCOUNT_PURCHASE_REGEX)) {
            //check the string by discount > price
            if (Integer.parseInt(elements[Constants.IN_LINE_PRICE])
                    <= Integer.parseInt(elements[Constants.IN_LINE_DISCOUNT])) {
                System.err.println(ERROR_DISCOUNT_MORE);
            } else {
                currentPurchase = PurchaseKind.DISCOUNT_PURCHASE.getPurchase(elements);
            }
        } else if (currentLine.matches(GENERAL_PURCHASE_REGEX)) {
            currentPurchase = PurchaseKind.GENERAL_PURCHASE.getPurchase(elements);
        }
        return currentPurchase;
    }
}
