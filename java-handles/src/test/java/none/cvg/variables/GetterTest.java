package none.cvg.variables;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;

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
import static none.cvg.handles.ErrorMessages.UNSAFE_FAILURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * TODO:
 *  This test aims at accessing public and private variables as well as a single and a
 *  multidimensional array on an existing object.
 *  Each solved test shows how this can be achieved with the traditional reflection calls.
 *  Each unsolved test provides a few hints that will allow the kata-taker to manually solve
 *  the exercise to achieve the same goal with MethodHandles/VarHandles.
 */
@DisplayName("Get field value")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(HandlesKataDisplayNames.class)
public class GetterTest {

    public Integer publicVariable = 1;

    private Integer privateVariable = 2;

    private int[] privatePrimitiveArrayVariable = {1, 2, 3};

    private int[][] privatePrimitive2DArrayVariable = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };

    /* -------------------------------------------------------------------------------------- */
    /*
     * BEGIN: PUBLIC VARIABLE USING REFLECTION AND VARIABLE HANDLES
     */
    @Test
    @Tag("PASSING")
    @Order(1)
    public void getPublicVariableFromConstructedClassViaReflection() {

        try {

            Class<?> clazz = GetterTest.class;

            Field publicVariableField = clazz.getDeclaredField("publicVariable");

            assertEquals(1,
                    publicVariableField.get(this),
                    "The value of the field should be 1");

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
                IllegalAccessException e) {

            fail(REFLECTION_FAILURE.getValue() + e.getMessage());

        }
    }

    @Test
    @Tag("TODO")
    @Order(2)
    public void getPublicVariableFromConstructedClassViaVarHandles() {

        try {

            /*
             * TODO:
             *  Replace the "null"s with valid values to get a VarHandle.
             *  Check API: java.lang.invoke.MethodHandles.lookup()
             *  Check API: java.lang.invoke.MethodHandles.Lookup.in(?)
             *             HINT: param is Target class
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVarHandle(?, ?, ?)
             *             HINT: params are Declaring class, Variable name, Variable type
             */
            VarHandle publicVariableVarHandle = MethodHandles
                    .lookup()
                    .in(null)
                    .findVarHandle(null, null, null);

            assertEquals(publicVariableVarHandle.coordinateTypes().size(),
                    1,
                    "There should only be one coordinateType");

            assertEquals(GetterTest.class,
                    publicVariableVarHandle.coordinateTypes().get(0),
                    "The only coordinate type is GetterTest");

            assertEquals(1,
                    publicVariableVarHandle.get(this),
                    "The value of the field should be 1");

        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {

            fail(TEST_FAILURE.getValue() + e.getMessage());

        }

    }
    /* -------------------------------------------------------------------------------------- */

    /* -------------------------------------------------------------------------------------- */
    /*
     * BEGIN: PRIVATE VARIABLE USING REFLECTION AND VARIABLE HANDLES
     */
    @Test
    @Tag("PASSING")
    @Order(3)
    public void getPrivateVariableFromConstructedClassViaReflection() {

        try {

            Class<?> clazz = GetterTest.class;

            Field privateVariableField = clazz.getDeclaredField("privateVariable");

            privateVariableField.setAccessible(true);

            assertEquals(2,
                    privateVariableField.get(this),
                    "The value of the field should be 2");

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
                IllegalAccessException e) {

            fail(REFLECTION_FAILURE.getValue() + e.getMessage());

        }
    }

    @Test
    @Tag("TODO")
    @Order(4)
    public void getPrivateVariableFromConstructedClassViaVarHandles() {

        try {

            /*
             * TODO:
             *  Replace the "null"s with valid values to get a VarHandle.
             *  Check API: java.lang.invoke.MethodHandles.privateLookupIn(?, ?)
             *             HINT: params are Target class and type of lookup
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVarHandle(?, ?, ?)
             *             HINT: params are Declaring class, Variable name, Variable type
             */
            VarHandle privateVariableVarHandle = MethodHandles
                    .privateLookupIn(null, MethodHandles.lookup())
                    .findVarHandle(null, null, null);

            assertEquals(1,
                    privateVariableVarHandle.coordinateTypes().size(),
                    "There should only be one coordinateType");

            assertEquals(GetterTest.class,
                    privateVariableVarHandle.coordinateTypes().get(0),
                    "The only coordinate type is AttributeGetterTest");

            assertTrue(privateVariableVarHandle.isAccessModeSupported(VarHandle.AccessMode.GET),
                    "Access mode for a GET should be true");

            assertEquals(2,
                    privateVariableVarHandle.get(this),
                    "The value of the field should be 2");

        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {

            fail(TEST_FAILURE.getValue() + e.getMessage());

        }
    }
    /* -------------------------------------------------------------------------------------- */


    /* -------------------------------------------------------------------------------------- */
    /*
     * BEGIN: PRIVATE 1-DIMENSIONAL ARRAY VARIABLE USING REFLECTION AND VARIABLE HANDLES
     */
    @Test
    @Tag("PASSING")
    @Order(5)
    public void getPrimitiveArrayFromConstructedClassViaReflection() {

        int[] reflectedArray = null;

        try {

            Class<?> clazz = GetterTest.class;

            Field privatePrimitiveArrayVariableField =
                    clazz.getDeclaredField("privatePrimitiveArrayVariable");

            privatePrimitiveArrayVariableField.setAccessible(true);

            Class<?> fieldClass = privatePrimitiveArrayVariableField.getType();

            if (fieldClass.isArray()) {
                reflectedArray = int[].class.cast(privatePrimitiveArrayVariableField.get(this));
            }

            assertEquals(3,
                    reflectedArray.length,
                    "The length of the array should be 3");

            assertEquals(1,
                    reflectedArray[0],
                    "The first element of the array should be 1");

            assertEquals(3,
                    reflectedArray[2],
                    "The third of the array should be 3");

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
                IllegalAccessException e) {

            fail(UNSAFE_FAILURE.getValue() + e.getMessage());

        }

    }

    @Test
    @Tag("TODO")
    @Order(6)
    public void getPrimitiveArrayFromConstructedClassViaVarHandles() {

        try {

            /*
             * TODO:
             *  Replace the "null"s with valid values to get a VarHandle.
             *  Check API: java.lang.invoke.MethodHandles.privateLookupIn(?, ?)
             *             HINT: params are Target class, Lookup type
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVarHandle(?, ?, ?)
             *             HINT: params are Declaring class, Variable name, Variable type
             */
            VarHandle privatePrimitiveArrayVariableVarHandle = MethodHandles
                    .privateLookupIn(null, null)
                    .findVarHandle(null, null, null);

            assertEquals(1,
                    privatePrimitiveArrayVariableVarHandle.coordinateTypes().size(),
                    "There should only be one coordinateType");

            assertEquals(GetterTest.class,
                    privatePrimitiveArrayVariableVarHandle.coordinateTypes().get(0),
                    "The only coordinate type is AttributeGetterTest");

            int[] varHandleTypeArray = int[].class.cast(
                    privatePrimitiveArrayVariableVarHandle.get(this));

            /*
             * TODO:
             *  Replace the "null"s with valid values to get a VarHandle.
             *  Check API: java.lang.invoke.MethodHandles.arrayElementVarHandle(?)
             */
            VarHandle arrayElementHandle = MethodHandles.arrayElementVarHandle(null);

            assertEquals(3,
                    varHandleTypeArray.length,
                    "The length of the array should be 3");

            assertEquals(1,
                    arrayElementHandle.get(privatePrimitiveArrayVariable, 0),
                    "The first element of the array should be 1");

            assertEquals(3,
                    arrayElementHandle.get(privatePrimitiveArrayVariable, 2),
                    "The third of the array should be 3");

        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {

            fail(TEST_FAILURE.getValue() + e.getMessage());

        }
    }
    /* -------------------------------------------------------------------------------------- */


    /* -------------------------------------------------------------------------------------- */
    /*
     * BEGIN: PRIVATE 2-DIMENSIONAL ARRAY VARIABLE USING REFLECTION AND VARIABLE HANDLES
     */
    @Test
    @Tag("PASSING")
    @Order(7)
    public void get2DimensionalPrimitiveArrayFromConstructedClassViaReflection() {

        int[][] reflectedArray = null;

        try {

            Class<?> clazz = GetterTest.class;

            Field privatePrimitive2DArrayVariableField =
                    clazz.getDeclaredField("privatePrimitive2DArrayVariable");

            privatePrimitive2DArrayVariableField.setAccessible(true);

            Class<?> fieldClass = privatePrimitive2DArrayVariableField.getType();

            if (fieldClass.isArray()) {
                reflectedArray = int[][].class.cast(privatePrimitive2DArrayVariableField.get(this));
            }

            assertEquals(3,
                    reflectedArray.length,
                    "The length of the array should be 3");

            assertEquals(1,
                    reflectedArray[0][0],
                    "The first of first element of the array should be 1");

            assertEquals(9,
                    reflectedArray[2][2],
                    "The third of third of the array should be 9");

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException |
                IllegalAccessException e) {

            fail(UNSAFE_FAILURE.getValue() + e.getMessage());

        }

    }

    @Test
    @Tag("TODO")
    @Order(8)
    public void get2DimensionalPrimitiveArrayFromConstructedClassViaVarHandles() {

        try {

            /*
             * TODO:
             *  Replace the "null"s with valid values to get a VarHandle.
             *  Check API: java.lang.invoke.MethodHandles.privateLookupIn(?, ?)
             *             HINT: params are Target class, Lookup type
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVarHandle(?, ?, ?)
             *             HINT: params are Declaring class, Variable name, Variable type
             */
            VarHandle privatePrimitive2DArrayVariableVarHandle = MethodHandles
                    .privateLookupIn(null, null)
                    .findVarHandle(null, null, null);

            assertEquals(1,
                    privatePrimitive2DArrayVariableVarHandle.coordinateTypes().size(),
                    "There should only be one coordinateType");

            assertEquals(GetterTest.class,
                    privatePrimitive2DArrayVariableVarHandle.coordinateTypes().get(0),
                    "The only coordinate type is AttributeGetterTest");

            int[][] varHandleTypeArray = int[][].class.cast(
                    privatePrimitive2DArrayVariableVarHandle.get(this));

            /*
             * TODO:
             *  Replace the "null"s with valid values to get a VarHandle.
             *  Check API: java.lang.invoke.MethodHandles.arrayElementVarHandle(?)
             */
            VarHandle arrayElementHandle = MethodHandles.arrayElementVarHandle(null);

            assertEquals(3,
                    varHandleTypeArray.length,
                    "The length of the array should be 3");

            assertEquals(1,
                    ((int[]) arrayElementHandle.get(privatePrimitive2DArrayVariable, 0))[0],
                    "The first element of the array should be 1");

            assertEquals(9,
                    ((int[]) arrayElementHandle.get(privatePrimitive2DArrayVariable, 2))[2],
                    "The last element of the array should be 9");

        } catch (NoSuchFieldException | IllegalAccessException | NullPointerException e) {

            fail(TEST_FAILURE.getValue() + e.getMessage());

        }
    }

}