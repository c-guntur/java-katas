package none.cvg.lambdas;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.lang.reflect.Method;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isUpperCase;

public class LambdasKataDisplayNames extends DisplayNameGenerator.Standard {
    @Override
    public String generateDisplayNameForClass(Class<?> aClass) {
        String className = aClass.getSimpleName();
        if (className.startsWith("TestKata")) {
            return camelToText(className.substring(9));
        }
        if (className.startsWith("TestSolution")) {
            return camelToText(className.substring(13));
        }
        return super.generateDisplayNameForClass(aClass);
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
