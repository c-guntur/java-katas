package none.cvg.datetime;

import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayNameGenerator;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isUpperCase;

public class DateTimeKataDisplayNames extends DisplayNameGenerator.Standard {

    @Override
    public String generateDisplayNameForClass(Class<?> aClass) {

        String className = aClass.getSimpleName();
        if (className.startsWith("Test")) {
            return camelToText(className.substring(5));
        }
        if (className.startsWith("STest")) {
            return camelToText(className.substring(6));
        }
        return className;
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> aClass) {

        return super.generateDisplayNameForNestedClass(aClass);
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> aClass, Method method) {

        String methodName = method.getName();
        return camelToText(methodName);
    }


    private static String camelToText(String text) {

        StringBuilder builder = new StringBuilder();
        char lastChar = ' ';
        for (char c : text.toCharArray()) {
            char nc = c;

            if (isUpperCase(nc) && !isUpperCase(lastChar)) {
                if (lastChar != ' ' && isLetterOrDigit(lastChar)) {
                    builder.append(" ");
                }
                nc = Character.toLowerCase(c);
            } else if (isDigit(lastChar) && !isDigit(c)) {
                if (lastChar != ' ') {
                    builder.append(" ");
                }
                nc = Character.toLowerCase(c);
            }

            if (lastChar != ' ' || c != ' ') {
                builder.append(nc);
            }
            lastChar = c;
        }
        return builder.toString();
    }
}
