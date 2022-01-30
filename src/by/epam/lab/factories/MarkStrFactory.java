package by.epam.lab.factories;

import static by.epam.lab.Constants.*;

public class MarkStrFactory {
    public enum MarkType {
        INTEGER_TYPE {
            @Override
            String getMarkStr(int mark) {
                return String.valueOf(mark);
            }
        },
        DOUBLE_TYPE {
            @Override
            String getMarkStr(int mark) {
                return String.format(FORMAT_MARK, mark / TEN_FOR_GET_INT, mark % TEN_FOR_GET_INT);
            }
        },
        MIX_TYPE {
            @Override
            String getMarkStr(int mark) {
                String strMark = String.valueOf(mark / TEN_FOR_GET_INT);
                if (mark % TEN_FOR_GET_INT != 0) {
                    strMark = String.format(
                            FORMAT_MARK, mark / TEN_FOR_GET_INT, mark % TEN_FOR_GET_INT);
                }
                return strMark;
            }
        };

        abstract String getMarkStr(int mark);
    }

    public static String getMarkFromFactory(int mark, MarkType markType) {
        return markType.getMarkStr(mark);
    }

}
