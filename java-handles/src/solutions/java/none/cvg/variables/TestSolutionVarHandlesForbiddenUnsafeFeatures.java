package none.cvg.variables;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import none.cvg.handles.HandlesKataDisplayNames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static none.cvg.handles.ErrorMessages.REFLECTION_FAILURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * DONE:
 *  This test aims at differentiating VarHandles from the traditional Reflection/Unsafe usage.
 *  Each solved test shows how 'bad' things can be done with the traditional reflection calls.
 *  Each unsolved test proves that there is no support for the same in Method/VarHandles.
 *  The "ability" to modify finals and static finals using Unsafe is highlighted here. VarHandles
 *  do not support this behavior for very obvious reasons.
 */
@DisplayName("Modify FINAL fields")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(HandlesKataDisplayNames.class)
public class TestSolutionVarHandlesForbiddenUnsafeFeatures {

    @Test
    @Tag("PASSING")
    @Order(1)
    public void modifyPrivateFinalUsingReflection() {

        final ClassWithPrivateFinalField instance = new ClassWithPrivateFinalField(10);

        try {

            final Field privateFinalField = ClassWithPrivateFinalField.class.getDeclaredField(
                    "PRIVATE_FINAL_FIELD");

            // Needed for non-public fields
            privateFinalField.setAccessible(true);

            // VERY VERY BAD !!!
            // Will work (tested until Java 11), since the final modifier is ignored.
            privateFinalField.setInt(instance, 20);

            assertEquals(20,
                    instance.getPrivateFinalField(),
                    "The private final field should have a value of 20");

        } catch (NoSuchFieldException | IllegalAccessException e) {

            fail(REFLECTION_FAILURE.getValue() + e.getMessage());

        }
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void cannotModifyPrivateFinalUsingVarHandles() throws Throwable {

        /*
         * NOTE:
         * A call to alter the value of a final field should ideally fail, per the JEP / spec.
         * Final fields cannot be modified using VarHandles.
         */
        assertThrows(Exception.class, () -> {

            final ClassWithPrivateFinalField instance = new ClassWithPrivateFinalField(10);

            /*
             * DONE:
             *  Replace the "null"s with valid values to get a VarHandle to PRIVATE_FINAL_FIELD.
             *  Note that the final field is in an inner class.
             *  Check API: java.lang.invoke.MethodHandles.privateLookupIn(?, ?)
             *             HINT: params are Target class, Lookup type
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVarHandle(?, ?, ?)
             *             HINT: params are Declaring class, Variable name, Variable type
             *  Remember PRIVATE_FINAL_FIELD in of type int.class
             */
            VarHandle privateFinalField = MethodHandles
                    .privateLookupIn(TestSolutionVarHandlesForbiddenUnsafeFeatures.class,
                            MethodHandles.lookup())
                    .findVarHandle(ClassWithPrivateFinalField.class,
                            "PRIVATE_FINAL_FIELD", int.class);

            /*
             * NOT ALLOWED:
             * Should throw the expected exception
             * Can throw either:
             *   java.lang.UnsupportedOperationException
             *     or
             *   java.lang.IllegalAccessException,
             * depending on call order.
             */
            privateFinalField.set(instance, 20);
        });

    }

    @Test
    @Tag("PASSING")
    @Order(3)
    public void modifyConstantViaReflection() {

        final ClassWithPrivateFinalField instance = new ClassWithPrivateFinalField(10);

        try {

            assertEquals(10,
                    ClassWithPrivateFinalField.getConstant(),
                    "The constant should have a value of 10");

            Field publicStaticFinalConstant = ClassWithPrivateFinalField.class.getDeclaredField(
                    "CONSTANT");

            // Needed for non-public fields
            publicStaticFinalConstant.setAccessible(true);


            // Access the soecial "modifiers" field of Field.class
            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            // VERY VERY BAD !!!
            // Alter the modifiers to remove the "final" modifier.
            modifiersField.setInt(publicStaticFinalConstant,
                    publicStaticFinalConstant.getModifiers() & ~Modifier.FINAL);


            // Will work, since the final modifier is removed.
            publicStaticFinalConstant.set(null, -20);

            assertEquals(-20,
                    ClassWithPrivateFinalField.getConstant(),
                    "The constant should have a value of -20");

        } catch (NoSuchFieldException | IllegalAccessException e) {

            fail(REFLECTION_FAILURE.getValue() + e.getMessage());

        }
    }

    @Test
    @Tag("PASSING")
    @Order(4)
    public void cannotModifyConstantUsingVarHandles() throws Throwable {

        /*
         * NOTE:
         * A call to alter the value of a constant should ideally fail, per the JEP / spec.
         * Constants (static finals) cannot be modified using VarHandles.
         */
        assertThrows(java.lang.UnsupportedOperationException.class, () -> {

            ClassWithPrivateFinalField instance = new ClassWithPrivateFinalField(10);

            /*
             * DONE:
             *  Replace the "null"s with valid values to get a VarHandle to PRIVATE_FINAL_FIELD.
             *  Note that the final field is in an inner class.
             *  Check API: java.lang.invoke.MethodHandles.privateLookupIn(?, ?)
             *             HINT: params are Target class, Lookup type
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVarHandle(?, ?, ?)
             *             HINT: params are Declaring class, Variable name, Variable type
             *  Remember CONSTANT in of type Integer.class
             */
            VarHandle publicStaticFinalConstant = MethodHandles
                    .privateLookupIn(TestSolutionVarHandlesForbiddenUnsafeFeatures.class,
                            MethodHandles.lookup())
                    .findStaticVarHandle(ClassWithPrivateFinalField.class,
                            "CONSTANT",
                            Integer.class);

            /*
             * NOT ALLOWED:
             * Should throw the expected exception.
             */
            publicStaticFinalConstant.set(instance, -20);
        });

    }

    public static class ClassWithPrivateFinalField {

        public static final Integer CONSTANT = 10;

        private final int PRIVATE_FINAL_FIELD;

        ClassWithPrivateFinalField(final int field) {
            PRIVATE_FINAL_FIELD = field;
        }

        public static int getConstant() {
            return CONSTANT;
        }

        int getPrivateFinalField() {
            return PRIVATE_FINAL_FIELD;
        }
    }

}