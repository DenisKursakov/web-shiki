package by.epam.lab;

import java.util.Scanner;

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

    public static Purchase getPurchaseFromFactory(Scanner scanner) {
        final String CURRENT_LINE = scanner.nextLine();
        final String ERROR_DISCOUNT_MORE = CURRENT_LINE + Constants.ARROW
                + "discount more or equal price";
        final String NON_POSITIVE = CURRENT_LINE + Constants.ARROW + "non positive value ";
        final String WRONG_NUMBER = CURRENT_LINE + Constants.ARROW + " wrong of arguments";
        final String WRONG_NAME = CURRENT_LINE + Constants.ARROW +  " wrong name";
        final String EMPTY_NAME = CURRENT_LINE + Constants.ARROW + " empty name";
        final String GENERAL_PURCHASE_REGEX = Constants.NAME_REGEX + Constants.SEMICOLON
                + Constants.NUMBER_REGEX + Constants.SEMICOLON + Constants.NUMBER_REGEX;
        final String DISCOUNT_PURCHASE_REGEX = GENERAL_PURCHASE_REGEX + Constants.SEMICOLON
                + Constants.NUMBER_REGEX;
        String[] elements = CURRENT_LINE.split(Constants.SEMICOLON);
        for (int i = Constants.ZERO; i < elements.length; i++) {
            if(elements[i].contains(Constants.POINT)){
                System.err.println(WRONG_NUMBER);
                break;
            }
            switch (i) {
                case Constants.ZERO:
                    if(elements.length <= Constants.TWO){
                        System.err.println(WRONG_NAME);
                    }
                    if (elements[i].matches(Constants.NON_WORDS_REGEX)) {
                        System.err.println(WRONG_NAME);
                        break;
                    } else if (elements[i].equals(Constants.EMPTY_STR)) {
                        System.err.println(EMPTY_NAME);
                    }
                    break;
                case Constants.ONE:
                    if (elements[i].contains(Constants.MINUS)
                            || elements[i].equals(Constants.ZERO_STRING)) {
                        System.err.println(NON_POSITIVE + elements[i] + Constants.IN_PRICE);
                        break;
                    } else if (elements[i].matches(Constants.NAME_REGEX)
                            || elements[i].equals(Constants.EMPTY_STR)) {
                        System.err.println(WRONG_NUMBER);
                    }
                    break;
                case Constants.TWO:
                    if (elements[i].contains(Constants.MINUS)
                            || elements[i].equals(Constants.ZERO_STRING)) {
                        System.err.println(NON_POSITIVE + elements[i] + Constants.IN_NUMBER);
                        break;
                    } else if (elements[i].matches(Constants.NAME_REGEX)
                            || elements[i].equals(Constants.EMPTY_STR)) {
                        System.err.println(WRONG_NUMBER);
                    }
                    break;
                case Constants.THREE:
                    if (elements[i].contains(Constants.MINUS)
                            || elements[i].equals(Constants.ZERO_STRING)) {
                        System.err.println(NON_POSITIVE + Constants.IN_DISCOUNT);
                        break;
                    } else if (elements[i].matches(Constants.NAME_REGEX)
                            || elements[i].equals(Constants.EMPTY_STR)) {
                        System.err.println(WRONG_NUMBER);
                    }
                    break;
            }
        }
        Purchase currentPurchase = null;
        //check the string by discount purchase regex
        if (CURRENT_LINE.matches(DISCOUNT_PURCHASE_REGEX)) {
            //check the string by discount > price
            if (Integer.parseInt(elements[Constants.ONE])
                    <= Integer.parseInt(elements[Constants.THREE])) {
                System.err.println(ERROR_DISCOUNT_MORE);
            } else {
                currentPurchase = PurchaseKind.DISCOUNT_PURCHASE.getPurchase(elements);
            }
        } else if (CURRENT_LINE.matches(GENERAL_PURCHASE_REGEX)) {       //check the string by general purchase regex
            currentPurchase = PurchaseKind.GENERAL_PURCHASE.getPurchase(elements);
        }
        return currentPurchase;
    }
}
