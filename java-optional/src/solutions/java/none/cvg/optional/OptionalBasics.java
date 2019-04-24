package none.cvg.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/*
 * TODO:
 *  This test aims at understanding the basic features of Optional
 *  Each unsolved test provides a few hints that will allow the kata-taker to manually solve
 *  the exercise.
 */
@DisplayName("java.util.Optional Basics")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OptionalBasics {

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("create an empty Optional")
    @Tag("PASSING")
    @Order(1)
    public void emptyOptional() {

        /*
         * TODO:
         *  Replace the "null" to create an empty Optional.
         *  Check API: java.util.Optional.empty()
         */
        Optional<String> optionalEmptyString = Optional.empty();
        assertTrue(optionalEmptyString instanceof Optional,
                "The optionalEmptyString should be an instance of Optional");

        assertTrue(optionalEmptyString.isEmpty(),
                "The optionalEmptyString should be empty");
    }

    @Test
    @DisplayName("create an Optional from a variable")
    @Tag("PASSING")
    @Order(2)
    public void createOptionalFromValue() {

        Integer anInteger = 10;

        /*
         * TODO:
         *  Replace the "null" to create an Optional for anInteger.
         *  Check API: java.util.Optional.of()
         */
        Optional<Integer> optionalForInteger = Optional.of(anInteger);
        assertTrue(optionalForInteger instanceof Optional,
                "The optionalEmptyString should be an instance of Optional");

        assertFalse(optionalForInteger.isEmpty(),
                "The optionalForInteger should not be empty");
    }

    @Test
    @DisplayName("create a nullable Optional from a variable")
    @Tag("PASSING")
    @Order(3)
    public void createNullableOptionalFromValue() {

        Integer anInteger = null;

        /*
         * TODO:
         *  Replace the "null" to create a nullable Optional for anInteger.
         *  Check API: java.util.Optional.ofNullable()
         */
        Optional<Integer> optionalNullableInteger = Optional.ofNullable(anInteger);
        assertTrue(optionalNullableInteger instanceof Optional,
                "The optionalNullableInteger should be an instance of Optional");

        assertTrue(optionalNullableInteger.isEmpty(),
                "The optionalNullableInteger should be empty");
    }

    @Test
    @DisplayName("check a non-null Optional has a value")
    @Tag("PASSING")
    @Order(4)
    public void checkOptionalForNonNullValueIsPresent() {

        Integer anInteger = 10;

        Optional<Integer> optionalInteger = Optional.ofNullable(anInteger);

        /*
         * TODO:
         *  Replace the "false" to check that the Optional has a non-null value.
         *  Check API: java.util.Optional.isPresent()
         */
        assertTrue(optionalInteger.isPresent(),
                "The optionalNullableInteger should be present");


        anInteger = null;

        optionalInteger = Optional.ofNullable(anInteger);

        /*
         * TODO:
         *  Replace the "false" to check that the Optional has a non-null value.
         *  Check API: java.util.Optional.isPresent()
         */
        assertFalse(optionalInteger.isPresent(),
                "The optionalNullableInteger should not be present");

    }

    @Test
    @DisplayName("fetch a value from a non-null Optional")
    @Tag("PASSING")
    @Order(5)
    public void getValueFromOptionalForNonNullValue() {

        Integer anInteger = 10;

        Optional<Integer> optionalInteger = Optional.ofNullable(anInteger);

        /*
         * TODO:
         *  Replace the "false" to check that the Optional has a non-null value.
         *  Check API: java.util.Optional.get()
         */
        assertEquals(10,
                optionalInteger.get(),
                "The optionalNullableInteger should be present");


        anInteger = null;

        Optional<Integer> anotherOptionalInteger = Optional.ofNullable(anInteger);

        /*
         * TODO:
         *  Replace the "false" to check that the Optional has a non-null value.
         *  Check API: java.util.Optional.isPresent()
         */

        assertThrows(NoSuchElementException.class, () -> {


            assertNotEquals(10,
                    anotherOptionalInteger.get(), //replace this line with a 10
                    "This call should throw a NoSuchElementException");
        });

    }





}
