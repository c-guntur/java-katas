package none.cvg.constructors;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import none.cvg.handles.DemoClass;
import none.cvg.handles.HandlesKataDisplayNames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import sun.misc.Unsafe;

import static none.cvg.handles.ErrorMessages.REFLECTION_FAILURE;
import static none.cvg.handles.ErrorMessages.TEST_FAILURE;
import static none.cvg.handles.ErrorMessages.UNSAFE_FAILURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * DONE:
 *  This test aims at using MethodHandles to invoke a default constructor on a class in order to
 *  create a new instance.
 *  Each solved test shows how this can be achieved with the traditional reflection/unsafe calls.
 *  Each unsolved test provides a few hints that will allow the kata-taker to manually solve
 *  the exercise to achieve the same goal with MethodHandles.
 */
@DisplayNameGeneration(HandlesKataDisplayNames.class)
@DisplayName("Invoke DemoClass()")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SDefaultConstructorInvocationTest {

    @Test
    @Tag("PASSING")
    @Order(1)
    public void reflectionNoParamConstructor() {

        String expectedOutput = "[No param DemoClass constructor]" +
                " - Default constructor via Reflection";

        try {

            Class<DemoClass> demoClassClass =
                    (Class<DemoClass>) Class.forName("none.cvg.handles.DemoClass");

            DemoClass demoClass =
                    demoClassClass.getDeclaredConstructor().newInstance();

            assertEquals(expectedOutput,
                    demoClass.printStuff("Default constructor via Reflection"),
                    "Reflection invocation failed");

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException | ClassNotFoundException e) {

            fail(REFLECTION_FAILURE.getValue() + e.getMessage());
        }
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void unsafeNoParamConstructor() {

        String expectedOutput = "[I am Unsafe.] - Default constructor via Unsafe";

        try {

            Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeInstance.setAccessible(true);
            final Unsafe unsafe = (Unsafe) theUnsafeInstance.get(null);

            // Allocate instance does not go through initialization process, no constructor called.
            DemoClass demoClass = (DemoClass)
                    unsafe.allocateInstance(DemoClass.class);

            // Get a handle to the "name" field of DemoClass
            Field nameFieldOfDemoClass = DemoClass.class.getDeclaredField("name");

            // Determine the memory offset location of the field in any instance of DemoClass.
            final long offset = unsafe.objectFieldOffset(nameFieldOfDemoClass);

            // Get the field for the DemoClass instance created above & set its value.
            unsafe.getAndSetObject(demoClass, offset, "I am Unsafe.");

            assertEquals(expectedOutput,
                    demoClass.printStuff("Default constructor via Unsafe"),
                    "Unsafe invocation failed");

        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {

            fail(UNSAFE_FAILURE.getValue() + e.getMessage());
        }
    }

    @Test
    @Tag("PASSING")
    @Order(3)
    public void methodHandleNoParamConstructor() {

        String expectedOutput = "[No param DemoClass constructor]" +
                " - Default constructor via Method Handles";

        /*
         * DONE:
         *  Replace the "null" to get a public Lookup instance.
         *  Get a public lookup from java.lang.invoke.MethodHandles
         *  Non-private constructors are looked up via "public lookups"
         *  Check API: java.lang.invoke.MethodHandles.publicLookup()
         */
        MethodHandles.Lookup publicMethodHandlesLookup = MethodHandles.publicLookup();

        /*
         * DONE
         *  Create a methodType instance that matches the default constructor
         *  Constructors should have a void return type
         *  Default constructors have no parameters
         *  Check API: java.lang.invoke.MethodType.methodType(?)
         */
        MethodType methodType = MethodType.methodType(void.class);

        try {

            /*
             * DONE:
             *  Replace the "null"s to get a MethodHandle to a constructor, for the DemoClass.
             *  "Find" a constructor of the class via the Lookup instance,
             *  based on the methodType described above
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findConstructor(?, ?)
             */
            MethodHandle demoClassConstructor =
                    publicMethodHandlesLookup.findConstructor(DemoClass.class, methodType);
            // Hint: Class and MethodType

            /*
             * DONE:
             *  Replace the null with a constructor handle invocation. Use casting.
             *  Create an instance of the DemoClass by invoking the method handle
             *  The MethodHandle has two methods invoke() and invokeExact()
             *  The invoke() is good for conversion/substitution of param types
             *  The invokeExact() is great if there is no ambiguity
             *  Check API: java.lang.invoke.MethodHandle.invokeExact()
             */
            DemoClass demoClass =
                    (DemoClass) demoClassConstructor.invokeExact();

            assertEquals(expectedOutput,
                    demoClass.printStuff(
                            "Default constructor via Method Handles"),
                    "Should match: " + expectedOutput);

        } catch (NoSuchMethodException | IllegalAccessException e) {

            fail("Failed to execute a constructor invocation via Method Handles: "
                    + e.getMessage());

        } catch (Throwable t) {

            // invokeExact() throws a Throwable (hence catching Throwable separately).
            fail(TEST_FAILURE.getValue() + t.getMessage());
        }
    }
}