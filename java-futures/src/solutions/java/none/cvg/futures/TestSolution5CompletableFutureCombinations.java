package none.cvg.futures;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * This test covers a few common combinations in CompletableFuture
 */
@DisplayName("CompletableFuture combinations to: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution5CompletableFutureCombinations {

    private static final Integer SEVEN = 7;
    private static final Integer NINE = 9;
    private static final Integer FORTY_NINE = 49;

    private List<Integer> aList;

    private ExecutorService executor;
    private ExecutorService delayedExecutor;

    @BeforeEach
    public void setup() {

        aList = new ArrayList<>();

        executor = Executors.newFixedThreadPool(1);

        // Create a delayed executor from the executor with a delay of 50ms.
        delayedExecutor = (ExecutorService) CompletableFuture.delayedExecutor(
                50, TimeUnit.MILLISECONDS, this.executor);
    }

    @AfterEach
    public void teardown() {

        aList = null;

        executor.shutdown();
        delayedExecutor.shutdown();
    }

    @DisplayName("complete ALL of the CFs")
    @Test
    @Tag("PASSING")
    @Order(1)
    public void allOf() {

        //NOTE: The CompletableFuture instances use a runAsync and add an Integer to the list.
        CompletableFuture<Void> addSeven =
                CompletableFuture.runAsync(() -> aList.add(SEVEN), executor);

        CompletableFuture<Void> addNineWithDelay =
                CompletableFuture.runAsync(() -> aList.add(NINE), delayedExecutor);

        // CompletableFuture allOf(...) returns a new CompletableFuture<Void> which is completed
        // when ALL provided CompletableFutures complete. Since the provided CFs can have different
        // return type generics, the allOf(...) returns a CompletableFuture of type Void.
        //
        // DONE: Create a CompletableFuture which ensures the completion of the above two
        //  CompletableFuture instances.
        // HINT:
        //  • Create a new CompletableFuture<Void> by invoking a static allOf(...)
        //  • Add both CFs.
        CompletableFuture<Void> voidCompletableFuture =
                CompletableFuture.allOf(addSeven, addNineWithDelay);

        voidCompletableFuture.join();

        assertTrue(
                aList.contains(SEVEN),
                "The addSeven completableFuture should have completed");

        assertTrue(
                aList.contains(NINE),
                "The addNineWithDelay completableFuture should have completed");
    }

    @DisplayName("run after BOTH CFs")
    @Test
    @Tag("PASSING")
    @Order(2)
    public void runAfterBoth() {

        //NOTE: The CompletableFuture instances use a runAsync and add an Integer to the list.
        CompletableFuture<Void> addSeven =
                CompletableFuture.runAsync(() -> aList.add(SEVEN), executor);

        CompletableFuture<Void> addNineWithDelay =
                CompletableFuture.runAsync(() -> aList.add(NINE), delayedExecutor);

        // CompletableFuture runAfterBoth(?, ?) returns a new CompletableFuture<Void> which runs a
        // Runnable when BOTH provided CompletableFutures complete. Since the provided CFs can have
        // different return type generics, the runAfterBoth(?, ?) returns a CompletableFuture of
        // type Void.
        //
        // NOTE: There are equivalent runAfterBothAsync(?, ?) and runAfterBothAsync(?, ?, ?) that
        //  run asynchronously without and with a specified executor.
        //
        // DONE: Create a CompletableFuture which ensures the completion of any of the above two
        //  CompletableFuture instances.
        // HINT:
        //  • Create a new CompletableFuture<Void> by invoking a runAfterBoth(?, ?) on addSeven.
        //  • For the first parameter use the addNineWithDelay
        //  • For the Runnable, add an integer FORTY_NINE to the aList
        CompletableFuture<Void> voidCompletableFuture =
                addSeven.runAfterBoth(addNineWithDelay, () -> aList.add(FORTY_NINE));

        voidCompletableFuture.join();

        assertTrue(
                aList.contains(FORTY_NINE),
                "The runAfterBoth should add FORTY_NINE to the list");
    }

    @DisplayName("consume values of BOTH CFs, when complete")
    @Test
    @Tag("PASSING")
    @Order(3)
    public void thenAcceptBoth() {

        //NOTE: The CompletableFuture instances use a supplyAsync and return an Integer Supplier.
        CompletableFuture<Integer> supplySeven =
                CompletableFuture.supplyAsync(() -> SEVEN);

        CompletableFuture<Integer> supplyNineWithDelay =
                CompletableFuture.supplyAsync(() -> NINE, delayedExecutor);

        // CompletableFuture thenAcceptBoth(?, ?) returns a new CompletableFuture<Void> which
        // accepts the results of both CFs into a BiConsumer when BOTH provided
        // CompletableFutures complete. Since the provided CFs can have
        // different return type generics, the thenAcceptBoth(?, ?) returns a CompletableFuture of
        // type Void.
        //
        // NOTE: There are equivalent thenAcceptBothAsync(?, ?) and thenAcceptBothAsync(?, ?, ?)
        //  that run asynchronously without and with a specified executor.
        //
        // DONE: Create a CompletableFuture which ensures the completion of both of the above
        //  CompletableFuture instances and add their results to the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> by invoking a thenAcceptBoth(?, ?) on supplySeven.
        //  • For the first parameter use the supplyNineWithDelay
        //  • For the Runnable, add an integer FORTY_NINE to the aList
        CompletableFuture<Void> voidCompletableFuture =
                supplySeven.thenAcceptBoth(supplyNineWithDelay,
                        (seven, nine) -> {
                            aList.add(seven);
                            aList.add(nine);
                        });

        voidCompletableFuture.join();

        assertTrue(
                aList.contains(SEVEN),
                "The thenAcceptBoth should add SEVEN to the list");

        assertTrue(
                aList.contains(NINE),
                "The thenAcceptBoth should add NINE to the list");
    }

    @DisplayName("Complete ANY of the CFs")
    @Test
    @Tag("PASSING")
    @Order(4)
    public void anyOf() {

        //NOTE: The CompletableFuture instances use a supplyAsync and return an Integer Supplier.
        CompletableFuture<Integer> supplySeven =
                CompletableFuture.supplyAsync(() -> SEVEN);

        CompletableFuture<Integer> supplyNineWithDelay =
                CompletableFuture.supplyAsync(() -> NINE, delayedExecutor);

        // CompletableFuture anyOf(...) returns a new CompletableFuture<Object> which is completed
        // when ANY provided CompletableFutures complete. Since the provided CFs can have different
        // return type generics, the anyOf(...) returns a CompletableFuture of type Object.
        //
        // DONE: Create a CompletableFuture which ensures the completion of any of the above two
        //  CompletableFuture instances.
        // HINT:
        //  • Create a new CompletableFuture<Object> by invoking a static anyOf(...)
        CompletableFuture<Object> objectCompletableFuture =
                CompletableFuture.anyOf(supplySeven, supplyNineWithDelay);

        Integer value = (Integer) objectCompletableFuture.join();

        assertEquals(
                SEVEN,
                value,
                "The addSeven completes ahead of addNineWithDelay");
    }

    @DisplayName("run after EITHER CF")
    @Test
    @Tag("PASSING")
    @Order(5)
    public void runAfterEither() {

        //NOTE: The CompletableFuture instances use a runAsync and add an Integer to the list.
        CompletableFuture<Void> addSeven =
                CompletableFuture.runAsync(() -> aList.add(SEVEN), executor);

        CompletableFuture<Void> addNineWithDelay =
                CompletableFuture.runAsync(() -> aList.add(NINE), delayedExecutor);

        // CompletableFuture runAfterEither(?, ?) returns a new CompletableFuture<Void> which runs a
        // Runnable when EITHER provided CompletableFutures complete. Since the Runnable does not
        // have a return type, the runAfterEither(?, ?) returns a CompletableFuture of type Void.
        //
        // NOTE: There are equivalent runAfterEitherAsync(?, ?) and runAfterEitherAsync(?, ?, ?)
        //  that run asynchronously without and with a specified executor.
        //
        // DONE: Create a CompletableFuture which ensures the completion of any of the above two
        //  CompletableFuture instances, and runs a task to add an integer to the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> by invoking a runAfterEither(?, ?) on addSeven.
        //  • For the first parameter use the addNineWithDelay
        //  • For the Runnable, add an integer FORTY_NINE to the aList
        CompletableFuture<Void> voidCompletableFuture =
                addSeven.runAfterEither(addNineWithDelay, () -> aList.add(FORTY_NINE));

        voidCompletableFuture.join();

        assertTrue(
                aList.contains(SEVEN),
                "The addSeven should add SEVEN to the list");
        assertFalse(
                aList.contains(NINE),
                "The addNineWithDelay should not have completed");
        assertTrue(
                aList.contains(FORTY_NINE),
                "The runAfterEither should add FORTY_NINE to the list");
    }

    @DisplayName("consume value of EITHER CF, when complete")
    @Test
    @Tag("PASSING")
    @Order(6)
    public void acceptEither() {

        //NOTE: The CompletableFuture instances use a supplyAsync and return an Integer Supplier.
        CompletableFuture<Integer> supplySeven =
                CompletableFuture.supplyAsync(() -> SEVEN);

        CompletableFuture<Integer> supplyNineWithDelay =
                CompletableFuture.supplyAsync(() -> NINE, delayedExecutor);

        // CompletableFuture acceptEither(?, ?) returns a new CompletableFuture<Void> which
        // accepts the results of either CFs into a Consumer when EITHER provided
        // CompletableFutures complete. Since the Consumer does not return anything,
        // the acceptEither(?, ?) returns a CompletableFuture of type Void.
        //
        // NOTE: There are equivalent acceptEitherAsync(?, ?) and acceptEitherAsync(?, ?, ?)
        //  that run asynchronously without and with a specified executor.
        //
        // DONE: Create a CompletableFuture which ensures the completion of either of the above
        //  CompletableFuture instances and add the result of the first-to-complete to the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> by invoking a thenAcceptBoth(?, ?) on supplySeven.
        //  • For the first parameter use the supplyNineWithDelay
        //  • For the Runnable, add an integer FORTY_NINE to the aList
        CompletableFuture<Void> voidCompletableFuture =
                supplySeven.acceptEither(supplyNineWithDelay,
                        (first) -> aList.add(first));

        voidCompletableFuture.join();

        assertTrue(
                aList.contains(SEVEN),
                "The acceptEither should add SEVEN to the list");

        assertFalse(
                aList.contains(NINE),
                "The supplyNineWithDelay should not have completed earlier");
    }

    @DisplayName("apply to value of EITHER CF, when complete")
    @Test
    @Tag("PASSING")
    @Order(7)
    public void applyToEither() {

        //NOTE: The CompletableFuture instances use a supplyAsync and return an Integer Supplier.
        CompletableFuture<Integer> supplySeven =
                CompletableFuture.supplyAsync(() -> SEVEN);

        CompletableFuture<Integer> supplyNineWithDelay =
                CompletableFuture.supplyAsync(() -> NINE, delayedExecutor);

        // CompletableFuture applyToEither(?, ?) returns a new CompletableFuture<U> which
        // applies the results of either CFs into a Function<T,U> when EITHER provided
        // CompletableFuture<T> complete. Since the Function returns a type U,
        // the applyToEither(?, ?) returns a CompletableFuture of type U.
        //
        // NOTE: There are equivalent acceptEitherAsync(?, ?) and acceptEitherAsync(?, ?, ?)
        //  that run asynchronously without and with a specified executor.
        //
        // DONE: Create a CompletableFuture which ensures the completion of either of the above
        //  CompletableFuture instances and add the result of the first-to-complete to the list.
        // HINT:
        //  • Create a new CompletableFuture<Integer> by invoking a applyToEither(?, ?) on supplySeven.
        //  • For the first parameter use the supplyNineWithDelay
        //  • For the Function, square the result
        CompletableFuture<Integer> squaredCompletableFuture =
                supplySeven.applyToEither(supplyNineWithDelay,
                        (first) -> first * first);

        Integer result = squaredCompletableFuture.join();

        assertEquals(
                FORTY_NINE,
                result,
                "The applyToEither should return FORTY_NINE");
    }
}