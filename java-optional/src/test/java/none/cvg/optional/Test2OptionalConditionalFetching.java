package none.cvg.optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * TODO:
 *  This test aims at understanding the basic features of Optional
 *  Each unsolved test provides a few hints that will allow the kata-taker to manually solve
 *  the exercise.
 *
 *  orElse(), orElseGet(), orElseThrow(), orElseThrow(?)
 */
@DisplayName("Optional - conditional fetch non-null value or alternative")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test2OptionalConditionalFetching {

    @BeforeEach
    public void setUp() {
    }

    @Test
    @DisplayName("return an Optional of either a non-null value or from a Supplier")
    @Tag("TODO")
    @Order(1)
    public void orReturnOptional() {

        Optional<String> defaultOptional = Optional.of("supplied");
        Optional<String> anOptional = Optional.empty();

        /*
         * TODO:
         *  Replace the empty optional to either return the anOptional, if it has a value
         *  or return the defaultOptional (use a Supplier)
         *  Check API: java.util.Optional.or(?)
         */
        Optional<String> nonNullOptional = Optional.empty();

        assertTrue(nonNullOptional instanceof Optional,
                "The nonNullOptional should be an instance of Optional");

        assertFalse(nonNullOptional.isEmpty(),
                "The nonNullOptional should not be empty");
    }

    @Test
    @DisplayName("return either a value from non-empty Optional or a default value")
    @Tag("TODO")
    @Order(2)
    public void orElseReturnValue() {

        Integer anInteger = null;
        Optional<Integer> nullableInteger = Optional.ofNullable(anInteger);

        /*
         * TODO:
         *  Replace the below to use an orElse(?) - instead of - the or(?)
         *  and the need to use get()
         *  Check API: java.util.Optional.orElse(?)
         */
        Integer nonNullInteger = nullableInteger.or(() -> Optional.of(11)).get();

        assertTrue(nonNullInteger instanceof Integer,
                "The nonNullInteger should be an instance of Integer");

        assertEquals(nonNullInteger,
                10,
                "The nonNullInteger should not be empty");
    }

    @Test
    @DisplayName("return a value either from non-empty Optional or from a Supplier")
    @Tag("TODO")
    @Order(3)
    public void orElseGetSupplierValue() {

        String defaultOptional = "supplied";
        Optional<String> anOptional = Optional.empty();

        /*
         * TODO:
         *  Replace the below to use an orElseGet(?) - instead of - the or(?)
         *  and the need to use get()
         *  Check API: java.util.Optional.ofNullable(?)
         */
        String nonNullString = null;

        assertTrue(nonNullString instanceof String,
                "The nonNullString should be an instance of String");

        assertEquals(nonNullString,
                "supplied",
                "The nonNullString should have a value of \"supplied\"");
    }

    @Test
    @DisplayName("return a value either from non-empty Optional or throw Exception")
    @Tag("TODO")
    @Order(4)
    public void orElseThrowReplacesOptionalGet() {

        /*
         * NOTE: Refer to the bug: https://bugs.openjdk.java.net/browse/JDK-8140281
         *
         * This is a deliberate API addition to "replace" or "deprecate" the get()
         * get() brings about an ambiguity, when used without an ifPresent().
         *
         * Bug for get() deprecation: https://bugs.openjdk.java.net/browse/JDK-8160606
         */

        Optional<String> anOptional = Optional.empty();

        /*
         * TODO:
         *  Replace the below to use an orElseThrow() - instead of - the get()
         *  Check API: java.util.Optional.orElseThrow()
         */
        assertThrows(NoSuchElementException.class, () -> {
            String nonNullString = null;
        });

    }

    @Test
    @DisplayName("return a value either from non-empty Optional or throw Custom Exception")
    @Tag("TODO")
    @Order(5)
    public void orElseThrowCustomException() {

        Optional<String> anOptional = Optional.empty();

        String exceptionMessage = "Empty value";

        Supplier<RuntimeException> exceptionSupplier = () -> new RuntimeException(exceptionMessage);

        /*
         * TODO:
         *  Replace the below to use an orElseThrow() - instead of - the get()
         *  Check API: java.util.Optional.orElseThrow(?)
         */
        Exception caughtException = assertThrows(
                RuntimeException.class,
                () -> {
                    String nonNullString = null;

                });

        assertEquals("Empty value",
                caughtException.getMessage(),
                "The custom exception should have benn thrown");

    }

    @Test
    @DisplayName("perform action with value if non-empty else perform empty action")
    @Tag("TODO")
    @Order(6)
    public void ifPresentConsumeOrElseOtherAction() {
        AtomicInteger nonEmptyValueCounter = new AtomicInteger(0);

        Consumer<Integer> nonEmptyValueAction = x -> nonEmptyValueCounter.getAndAdd(x);
        Runnable alternateAction = nonEmptyValueCounter::getAndDecrement;

        Optional<Integer> nonEmptyIntegerOptional = Optional.of(10);

        /*
         * TODO:
         *  Add an ifPresentOrElse call to run either the nonEmptyValueAction or alternateAction
         *  (depending on whether the optional has a value or not)
         *  Check API: java.util.Optional.ifPresentOrElse(?, ?)
         */
//        nonEmptyIntegerOptional.???;

        assertEquals(10, nonEmptyValueCounter.get(), "");

        Optional<Integer> emptyIntegerOptional = Optional.ofNullable(null);

        /*
         * TODO:
         *  Add an ifPresentOrElse call to run either the nonEmptyValueAction or alternateAction
         *  (depending on whether the optional has a value or not)
         *  Check API: java.util.Optional.ifPresentOrElse(?, ?)
         */
//        emptyIntegerOptional.???

        assertEquals(9, nonEmptyValueCounter.get(), "");
    }

}
