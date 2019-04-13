package none.cvg.handles;

import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayNameGenerator;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.isUpperCase;

public class HandlesKataDisplayNames extends DisplayNameGenerator.Standard {
    @Override
    public String generateDisplayNameForClass(Class<?> aClass) {
        return super.generateDisplayNameForClass(aClass);
    }

    @Override
    public String generateDisplayNameForNestedClass(Class<?> aClass) {
        return super.generateDisplayNameForNestedClass(aClass);
    }

    @Override
    public String generateDisplayNameForMethod(Class<?> aClass, Method method) {
        String methodName = method.getName();
        if (methodName.startsWith("reflection")) {
            return "using Reflection";
        }
        if (methodName.startsWith("unsafe")) {
            return "using Unsafe";
        }
        if (methodName.startsWith("methodHandle")) {
            return "using Method Handles";
        }
        if (methodName.startsWith("compareAndSet")) {
            return camelToText(methodName.substring(13));
        }
        if (methodName.startsWith("get")) {
            return camelToText(methodName.substring(3));
        }
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
