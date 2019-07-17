package none.cvg.variables;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import none.cvg.handles.HandlesKataDisplayNames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import sun.misc.Unsafe;

import static none.cvg.handles.ErrorMessages.TEST_FAILURE;
import static none.cvg.handles.ErrorMessages.UNSAFE_FAILURE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * DONE:
 *  This test aims at using VarHandles to compare and then set a value of a given variable.
 *  Each solved test shows how this can be achieved with the traditional reflection calls.
 *  Each unsolved test provides a few hints that will allow the kata-taker to manually solve
 *  the exercise to achieve the same goal with MethodHandles.
 */
@DisplayName("Compare and Set field values")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(HandlesKataDisplayNames.class)
public class TestSolutionCompareAndSet {

    private Integer currentValue = 2;

    private volatile Integer privateVolatile = 2;

    private Integer newValue = 7;

    @Test
    @Tag("PASSING")
    @Order(1)
    public void compareAndSetUsingAtomicReference() {

        AtomicReference<Integer> atomicReference = new AtomicReference<>(privateVolatile);


        boolean exchanged = atomicReference.compareAndSet(privateVolatile, newValue);

        assertTrue(exchanged,
                "The value should have been changed to 7, " +
                        "hence exchanged should be true"
                );

        assertEquals(newValue,
                atomicReference.get(),
                "The value of the privateVolatile should now be 7");

        exchanged = atomicReference.compareAndSet(privateVolatile, newValue);

        assertFalse(exchanged,
                "The value should not have changed again, " +
                        "hence exchanged should be false"
                );

        assertEquals(newValue,
                atomicReference.get(),
                "The value of the privateVolatile should still be 7");

    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void compareAndSetUsingAtomicReferenceFieldUpdater() {

        final AtomicReferenceFieldUpdater<TestSolutionCompareAndSet, Integer> valueUpdater =
                AtomicReferenceFieldUpdater.newUpdater(TestSolutionCompareAndSet.class,
                        Integer.class,
                        "privateVolatile");

        boolean exchanged = valueUpdater.compareAndSet(this, currentValue, newValue);

        assertTrue(exchanged,
                "The value should have been changed to 7, " +
                        "hence exchanged should be true"
                );

        assertEquals(newValue,
                valueUpdater.get(this),
                "The value of the privateVolatile should now be 7");

        exchanged = valueUpdater.compareAndSet(this, 2, 33);

        assertFalse(exchanged,
                "The value should not have changed since the expected value " +
                        "did not match, hence exchanged should be false"
                );

        assertEquals(newValue,
                valueUpdater.get(this),
                "The value of the privateVolatile should still be 7");
    }

    @Test
    @Tag("PASSING")
    @Order(3)
    public void compareAndSetUsingUnsafe() {

        try {

            Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafeInstance.setAccessible(true);
            final Unsafe unsafe = (Unsafe) theUnsafeInstance.get(null);

            final long offset;

            offset = unsafe.objectFieldOffset(
                    TestSolutionCompareAndSet.class.getDeclaredField("privateVolatile"));

            boolean exchanged = unsafe.compareAndSwapObject(this,
                    offset, currentValue, newValue);

            assertTrue(exchanged,
                    "The value should have been changed to 7, " +
                            "hence exchanged should be true"
                    );

            assertEquals(newValue,
                    unsafe.getObject(this, offset),
                    "The value of the privateVolatile should now be 7");

            exchanged = unsafe.compareAndSwapObject(this, offset, 2, 33);

            assertFalse(exchanged,
                    "The value should not have changed since the expected value " +
                            "did not match, hence exchanged should be false"
                    );

            assertEquals(newValue,
                    unsafe.getObject(this, offset),
                    "The value of the privateVolatile should still be 7");

        } catch (NoSuchFieldException | IllegalAccessException e) {

            fail(UNSAFE_FAILURE.getValue() + e.getMessage());

        }

    }

    @Test
    @Tag("PASSING")
    @Order(4)
    public void compareAndSetUsingVarHandles() {

        VarHandle varHandle = null;

        try {

            /*
             * DONE:
             *  Replace the "null"s with valid values to get a VarHandle.
             *  Check API: java.lang.invoke.MethodHandles.privateLookupIn(?, ?)
             *             HINT: params are Target class, Lookup type
             *  Check API: java.lang.invoke.MethodHandles.Lookup.findVarHandle(?, ?, ?)
             *             HINT: params are Declaring class, Variable name, Variable type
             */
            varHandle = MethodHandles
                    .privateLookupIn(TestSolutionCompareAndSet.class, MethodHandles.lookup())
                    .findVarHandle(TestSolutionCompareAndSet.class, "privateVolatile", Integer.class);

            /*
             * DONE:
             *  Replace the "false" to a compareAndSet call from 'currentValue' to 'newValue'.
             *  Check API: java.lang.invoke.VarHandle.compareAndSet(...)
             *  Three parameters are needed here:
             *      1. Instance of the class where the variable is being manipulated.
             *      2. The current value to compare
             *      3. The new value to set
             */
            boolean exchanged = varHandle.compareAndSet(this, currentValue, newValue);

            assertTrue(exchanged,
                    "The value should have been changed to 7, " +
                            "hence exchanged should be true"
                    );

            assertEquals(newValue,
                    varHandle.get(this),
                    "The value of the privateVolatile should now be 7");

            /*
             * TODO:
             *  Replace the "false" to a compareAndSet call from 2 to 33.
             *  Check API: java.lang.invoke.VarHandle.compareAndSet(...)
             *  Three parameters are needed here:
             *      1. Instance of the class where the variable is being manipulated.
             *      2. The current value to compare
             *      3. The new value to set
             */
            exchanged = varHandle.compareAndSet(this, 2, 33);

            assertFalse(exchanged,
                    "The value should not have changed since the expected value " +
                            "did not match, hence exchanged should be false"
                    );

            assertEquals(newValue,
                    varHandle.get(this),
                    "The value of the privateVolatile should still be 7");

        } catch (NoSuchFieldException | IllegalAccessException e) {

            fail(TEST_FAILURE.getValue() + e.getMessage());

        }

    }
}
