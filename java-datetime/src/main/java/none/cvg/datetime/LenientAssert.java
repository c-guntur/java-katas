package none.cvg.datetime;

import static org.junit.jupiter.api.Assertions.fail;

public class LenientAssert {

    public static final String FIX_WITH_RIGHT_CALL = "Replace this string with a valid call";

    public static void assertAlmostEquals(long first, String second, long tolerance) {

        assertAlmostEquals(first, second, tolerance, null);
    }

    public static void assertAlmostEquals(long first, long second, long tolerance) {

        assertAlmostEquals(first, second, tolerance, null);
    }

    public static void assertAlmostEquals(long first, long second, long tolerance, String message) {

        if (!(Math.abs(first - second) <= tolerance)) {
            fail(format(message, first, second));
        }
    }

    public static void assertAlmostEquals(long first, String second, long tolerance, String message) {

            fail(format(message, first, second));
    }

    private static String format(String message, Object expected, Object actual) {

        String formatted = "";
        if (message != null && !message.equals("")) {
            formatted = message + " ";
        }
        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);
        if (expectedString.equals(actualString)) {
            return formatted + "expected: "
                    + formatClassAndValue(expected, expectedString)
                    + " but was: " + formatClassAndValue(actual, actualString);
        } else {
            return formatted + "expected:<" + expectedString + "> but was:<"
                    + actualString + ">";
        }
    }

    private static String formatClassAndValue(Object value, String valueString) {

        String className = value == null ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }
}
