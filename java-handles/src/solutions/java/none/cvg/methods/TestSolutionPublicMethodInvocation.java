package none.cvg.methods;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import none.cvg.handles.DemoClass;
import none.cvg.handles.HandlesKataDisplayNames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static none.cvg.handles.ErrorMessages.REFLECTION_FAILURE;
import static none.cvg.handles.ErrorMessages.TEST_FAILURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * DONE:
 *  This test aims at using MethodHandles to invoke a public method on a class.
 *  Each solved test shows how this can be achieved with the traditional reflection calls.
 *  Each unsolved test provides a few hints that will allow the kata-taker to manually solve
 *  the exercise to achieve the same goal with MethodHandles.
 */
@DisplayNameGeneration(HandlesKataDisplayNames.class)
@DisplayName("Invoke DemoClass.publicMethod(String)")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolutionPublicMethodInvocation {

    @Test
    @Tag("PASSING")
    @Order(1)
    public void reflectionPublicMethod() {

        String expectedOutput = "[DemoClass] - Public method - via reflection";

        try {

            // Find the method on the class via a getMethod.
            Method publicMethod =
                    DemoClass.class.getMethod("publicMethod",
                            String.class);

            DemoClass demoClass = new DemoClass();

            assertEquals(expectedOutput,
                    publicMethod.invoke(demoClass, "via reflection"),
                    "Reflection invocation failed");

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {

            fail(REFLECTION_FAILURE.getValue() + e.getMessage());
        }
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void methodHandlePublicMethod() {

        String expectedOutput = "[DemoClass] - Public method - via Method Handles";

        /*
         * DONE:
         *  Get a public lookup from java.lang.invoke.MethodHandles
         *  Public parts of a class are looked up via "public lookups"
         *  Check API: java.lang.invoke.MethodHandles.publicLookup()
         */
        MethodHandles.Lookup publicMethodHandlesLookup = MethodHandles.publicLookup();

        /*
         * DONE:
         *  Replace the "null"s with valid values to extract a method signature for publicMethod.
         *  Create a methodType instance that matches the signature of what we wish to invoke
         *  HINT: The publicMethod() returns a String and accepts a String parameter
         *  Check API: java.lang.invoke.MethodType.methodType(?, ?)
         */
        MethodType methodType = MethodType.methodType(String.class, String.class);

        try {

            /*
             * DONE:
             *  Replace the "null"s with appropriate values to get a handle to publicMethod.
             *  "Find" a method of the class via the Lookup instance,
             *  based on the methodType described above and the method name.
             *  Public methods can be searched via the findVirtual() method.
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVirtual(?, ?, ?)
             */
            MethodHandle publicMethodHandle =
                    publicMethodHandlesLookup.findVirtual(DemoClass.class,
                            "publicMethod", methodType); // Class, Method name, MethodType

            DemoClass demoClass = new DemoClass();

            assertEquals(expectedOutput,
                    publicMethodHandle.invoke(demoClass,
                            "via Method Handles"),
                    "Method handles invocation failed");

        } catch (NoSuchMethodException | IllegalAccessException e) {

            fail("Failed to execute a public method invocation via Method Handles: "
                    + e.getMessage());
        } catch (Throwable t) {

            // invoke throws a Throwable (hence catching Throwable separately).
            fail(TEST_FAILURE.getValue() + t.getMessage());
        }
    }
}