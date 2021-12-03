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

    public static Purchase getPurchaseFromFactory(Scanner scanner, StringBuilder errorInfo) {
        final String MINUS = "-";
        final String POINT = ".";
        final String ZERO = "0";
        final String EMPTY_STR = "";
        final String DELIMITER = ";";
        final String ARROW = "\t-> ";
        final String TABULATION = "\n";
        final String ERROR_DISCOUNT_MORE = ARROW + "discount more or equal price";
        final String NON_POSITIVE = ARROW + "non positive value ";
        final String IN_PRICE = " in price";
        final String IN_NUMBER = " in number of units";
        final String IN_DISCOUNT = " in discount";
        final String WRONG_NUMBER = ARROW + " wrong of arguments";
        final String WRONG_NAME = ARROW + " wrong name";
        final String EMPTY_NAME = ARROW + " empty name";
        final String NUMBER_REGEX = "[1-9]\\d*";
        final String NAME_REGEX = "[A-Za-z].*";
        final String NON_WORDS_REGEX = "\\W";
        final String GENERAL_PURCHASE_REGEX = NAME_REGEX + DELIMITER + NUMBER_REGEX + DELIMITER
                + NUMBER_REGEX;
        final String DISCOUNT_PURCHASE_REGEX = GENERAL_PURCHASE_REGEX + DELIMITER + NUMBER_REGEX;
        final String CURRENT_LINE = scanner.nextLine();
        String[] elements = CURRENT_LINE.split(DELIMITER);
        //check the string by discount purchase regex
        if (CURRENT_LINE.matches(DISCOUNT_PURCHASE_REGEX)) {
            //check the string by discount > price
            if (Integer.parseInt(elements[1]) <= Integer.parseInt(elements[3])) {
                errorInfo.append(CURRENT_LINE).append(ERROR_DISCOUNT_MORE).append("\n");
                return null;
            }
            return PurchaseKind.DISCOUNT_PURCHASE.getPurchase(elements);
        }
        //check the string by general purchase regex
        if (CURRENT_LINE.matches(GENERAL_PURCHASE_REGEX)) {
            return PurchaseKind.GENERAL_PURCHASE.getPurchase(elements);
        }
        //check the string array by wrong elements (element = "")

        for (int i = 0; i < elements.length; i++) {
            if(elements[i].contains(POINT)){
                errorInfo.append(CURRENT_LINE).append(WRONG_NUMBER).append(TABULATION);
                break;
            }
            switch (i) {
                case 0:
                    if(elements.length <= 2){
                        errorInfo.append(CURRENT_LINE).append(WRONG_NUMBER).append(TABULATION);
                    }
                    if (elements[i].matches(NON_WORDS_REGEX)) {
                        errorInfo.append(CURRENT_LINE).append(WRONG_NAME).append(TABULATION);
                        break;
                    } else if (elements[i].equals(EMPTY_STR)) {
                        errorInfo.append(CURRENT_LINE).append(EMPTY_NAME).append(TABULATION);
                    }
                    break;
                case 1:
                    if (elements[i].contains(MINUS) || elements[i].equals(ZERO)) {
                        errorInfo.append(CURRENT_LINE).append(NON_POSITIVE)
                                .append(elements[i]).append(IN_PRICE).append(TABULATION);
                        break;
                    } else if (elements[i].matches(NAME_REGEX) || elements[i].equals(EMPTY_STR)) {
                        errorInfo.append(CURRENT_LINE).append(WRONG_NUMBER).append(TABULATION);
                    }
                    break;
                case 2:
                    if (elements[i].contains(MINUS) || elements[i].equals(ZERO)) {
                        errorInfo.append(CURRENT_LINE).append(NON_POSITIVE)
                                .append(elements[i]).append(IN_NUMBER).append(TABULATION);
                        break;
                    } else if (elements[i].matches(NAME_REGEX) || elements[i].equals(EMPTY_STR)) {
                        errorInfo.append(CURRENT_LINE).append(WRONG_NUMBER).append(TABULATION);
                    }
                    break;
                case 3:
                    if (elements[i].contains(MINUS) || elements[i].equals(ZERO)) {
                        errorInfo.append(CURRENT_LINE).append(NON_POSITIVE)
                                .append(elements[i]).append(IN_DISCOUNT).append(TABULATION);
                        break;
                    } else if (elements[i].matches(NAME_REGEX) || elements[i].equals(EMPTY_STR)) {
                        errorInfo.append(CURRENT_LINE).append(WRONG_NUMBER).append(TABULATION);
                    }
                    break;
            }
        }
        return null;
    }
}
