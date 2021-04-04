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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * This test covers CompletableFuture chaining
 */
@DisplayName("CompletableFuture executions chained with: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution4CompletableFutureChaining {

    private static final Integer SEVEN = 7;
    private static final Integer NINE = 9;

    private List<Integer> aList;
    private ExecutorService executor;

    private CompletableFuture<Integer> chainedFuture;

    @BeforeEach
    public void setup() {

        aList = new ArrayList<>();
        aList.add(NINE);

        chainedFuture = CompletableFuture.supplyAsync(() -> aList.get(0));

        executor = Executors.newFixedThreadPool(1);
    }

    @AfterEach
    public void teardown() {

        aList = null;

        chainedFuture = null;

        executor.shutdown();
    }

    @DisplayName("1a. thenRun")
    @Test
    @Tag("PASSING")
    @Order(1)
    public void thenRun() {

        // A thenRun(?) adds a new Stage that returns a CompletableFuture<Void> after
        // executing the run() method of a Runnable. Since the run() method has a void return, the
        // stage returns a CompletableFuture<Void>.
        //
        // This is a synchronous task.
        // DONE: Create a chaining for the above CompletableFuture to add an integer SEVEN to
        //  the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> to invoke a thenRun(?) on chainedFuture.
        //  • For the Runnable, add an integer SEVEN to the aList
        CompletableFuture<Void> voidCompletableFuture =
                chainedFuture.thenRun(() -> aList.add(SEVEN));

        voidCompletableFuture.join();

        assertNotNull(
                voidCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                2,
                aList.size(),
                "The list should now have two elements");
    }

    @DisplayName("1b. thenRunAsync with ForkJoinPool")
    @Test
    @Tag("PASSING")
    @Order(2)
    public void thenRunAsync() {

        // A thenRunAsync(?) adds a new Stage that returns a CompletableFuture<Void> after
        // executing the run() method of a Runnable. Since the run() method has a void return, the
        // stage returns a CompletableFuture<Void>.
        //
        // In this case, since no executor is provided, the asynchronous task is completed using the
        // ForkJoinPool.commonPool().
        // DONE: Create a chaining for the above CompletableFuture to add an integer SEVEN to
        //  the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> to invoke a thenRunAsync(?) on chainedFuture.
        //  • For the Runnable, add an integer SEVEN to the aList
        CompletableFuture<Void> voidCompletableFuture =
                chainedFuture.thenRunAsync(() -> aList.add(SEVEN));

        voidCompletableFuture.join();

        assertNotNull(
                voidCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                2,
                aList.size(),
                "The list should now have two elements");
    }

    @DisplayName("1c. thenRunAsync with an executor")
    @Test
    @Tag("PASSING")
    @Order(3)
    public void thenRunAsyncWithExecutor() {

        // A thenRunAsync(?, ?) adds a new Stage that returns a CompletableFuture<Void> after
        // executing the run() method of a Runnable. Since the run() method has a void return, the
        // stage returns a CompletableFuture<Void>.
        //
        // In this case, since an executor is provided, the asynchronous task is completed using the
        // provided executor.
        // DONE: Create a chaining for the above CompletableFuture to add an integer SEVEN to
        //  the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> to invoke a thenRunAsync(?, ?) on chainedFuture.
        //  • For the Runnable, add an integer SEVEN to the aList
        //  • For the second argument, supply the executor
        CompletableFuture<Void> voidCompletableFuture =
                chainedFuture.thenRunAsync(() -> aList.add(SEVEN), executor);

        voidCompletableFuture.join();

        assertNotNull(
                voidCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                2,
                aList.size(),
                "The list should now have two elements");
    }

    @DisplayName("2a. thenAccept")
    @Test
    @Tag("PASSING")
    @Order(4)
    public void thenAccept() {

        // A thenAccept(?) adds a new Stage that returns a CompletableFuture<Void> after
        // executing the accept() method of a Consumer. Since the accept() method has a void
        // return, the stage returns a CompletableFuture<Void>. The Consumer accepts the value of
        // the previous stage and can use the value.
        //
        // This is a synchronous task.
        // DONE: Create a chaining for the above CompletableFuture to add an integer with the
        //  previous stage's value minus two, to the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> to invoke a thenAccept(?) on chainedFuture.
        //  • For the Runnable, add a previous stage output minus 2 via a Consumer.
        CompletableFuture<Void> voidCompletableFuture =
                chainedFuture.thenAccept(val -> aList.add(val - 2));

        voidCompletableFuture.join();

        assertNotNull(
                voidCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                aList.get(1),
                "The second item on list should be 7");
    }

    @DisplayName("2b. thenAcceptAsync with ForkJoinPool")
    @Test
    @Tag("PASSING")
    @Order(5)
    public void thenAcceptAsync() {

        // A thenAcceptAsync(?) adds a new Stage that returns a CompletableFuture<Void> after
        // executing the accept() method of a Consumer. Since the accept() method has a void
        // return, the stage returns a CompletableFuture<Void>. The Consumer accepts the value of
        // the previous stage and can use the value.
        //
        // In this case, since no executor is provided, the asynchronous task is completed using the
        // ForkJoinPool.commonPool().
        // DONE: Create a chaining for the above CompletableFuture to add an integer SEVEN to
        //  the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> to invoke a thenAcceptAsync(?) on chainedFuture.
        //  • For the Runnable, add a previous stage output minus 2 via a Consumer.
        CompletableFuture<Void> voidCompletableFuture =
                chainedFuture.thenAcceptAsync(val -> aList.add(val - 2));

        voidCompletableFuture.join();

        assertNotNull(
                voidCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                aList.get(1),
                "The second item on list should be 7");
    }

    @DisplayName("2c. thenAcceptAsync with an executor")
    @Test
    @Tag("PASSING")
    @Order(6)
    public void thenAcceptAsyncWithExecutor() {

        // A thenAcceptAsync(?, ?) adds a new Stage that returns a CompletableFuture<Void> after
        // executing the accept() method of a Consumer. Since the accept() method has a void
        // return, the stage returns a CompletableFuture<Void>. The Consumer accepts the value of
        // the previous stage and can use the value.
        //
        // In this case, since an executor is provided, the asynchronous task is completed using the
        // provided executor.
        // DONE: Create a chaining for the above CompletableFuture to add an integer with the
        //  previous stage's value minus two, to the list.
        // HINT:
        //  • Create a new CompletableFuture<Void> to invoke a thenAcceptAsync(?, ?) on chainedFuture.
        //  • For the Runnable, add a previous stage output minus 2 via a Consumer.
        //  • For the second argument, supply the executor
        CompletableFuture<Void> voidCompletableFuture =
                chainedFuture.thenAcceptAsync(val -> aList.add(val - 2), executor);

        voidCompletableFuture.join();

        assertNotNull(
                voidCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                aList.get(1),
                "The second item on list should be 7");
    }

    @DisplayName("3a. thenApply")
    @Test
    @Tag("PASSING")
    @Order(7)
    public void thenApply() {

        // A thenApply(?) adds a new Stage that returns a CompletableFuture<R> after
        // executing the apply() method of a Function. Since the apply() method has a return type R,
        // the stage returns a CompletableFuture<R>. The Function can use the value of the previous
        // stage with Type T and returns a CompletableFuture<R>, same as the Function's return type.
        //
        // This is a synchronous task.
        // DONE: Create a chaining for the above CompletableFuture to add an integer with the
        //  previous stage's value minus two, to the list.
        // HINT:
        //  • Create a new CompletableFuture<Integer> to invoke a thenApply(?) on chainedFuture.
        //  • For the Function, return a previous stage output minus 2 via a Function.
        CompletableFuture<Integer> minusTwoCompletableFuture =
                chainedFuture.thenApply(val -> val - 2);

        Integer output = minusTwoCompletableFuture.join();

        assertNotNull(
                minusTwoCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                output,
                "The output should be 7");
    }

    @DisplayName("3b. thenApplyAsync with ForkJoinPool")
    @Test
    @Tag("PASSING")
    @Order(8)
    public void thenApplyAsync() {

        // A thenApply(?) adds a new Stage that returns a CompletableFuture<R> after
        // executing the apply() method of a Function. Since the apply() method has a return type R,
        // the stage returns a CompletableFuture<R>. The Function can use the value of the previous
        // stage with Type T and returns a CompletableFuture<R>, same as the Function's return type.
        //
        // In this case, since no executor is provided, the asynchronous task is completed using the
        // ForkJoinPool.commonPool().
        // DONE: Create a chaining for the above CompletableFuture to add an integer with the
        //  previous stage's value minus two, to the list.
        // HINT:
        //  • Create a new CompletableFuture<Integer> to invoke a thenApplyAsync(?) on chainedFuture.
        //  • For the Function, return a previous stage output minus 2 via a Function.
        CompletableFuture<Integer> minusTwoCompletableFuture =
                chainedFuture.thenApplyAsync(val -> val - 2);

        Integer output = minusTwoCompletableFuture.join();

        assertNotNull(
                minusTwoCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                output,
                "The output should be 7");
    }

    @DisplayName("3c. thenApplyAsync with an executor")
    @Test
    @Tag("PASSING")
    @Order(9)
    public void thenApplyAsyncWithExecutor() {

        // A thenApply(?) adds a new Stage that returns a CompletableFuture<R> after
        // executing the apply() method of a Function. Since the apply() method has a return type R,
        // the stage returns a CompletableFuture<R>. The Function can use the value of the previous
        // stage with Type T and returns a CompletableFuture<R>, same as the Function's return type.
        //
        // In this case, since no executor is provided, the asynchronous task is completed using the
        // provided executor.
        // DONE: Create a chaining for the above CompletableFuture to add an integer with the
        //  previous stage's value minus two, to the list.
        // HINT:
        //  • Create a new CompletableFuture<Integer> to invoke a thenApplyAsync(?,?) on chainedFuture.
        //  • For the Function, return a previous stage output minus 2 via a Function.
        //  • For the second argument, supply the executor
        CompletableFuture<Integer> minusTwoCompletableFuture =
                chainedFuture.thenApplyAsync(val -> val - 2, executor);

        Integer output = minusTwoCompletableFuture.join();

        assertNotNull(
                minusTwoCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                output,
                "The output should be 7");
    }

    @DisplayName("4a. thenCompose")
    @Test
    @Tag("PASSING")
    @Order(10)
    public void thenCompose() {

        // A thenCompose(?) adds a new Stage that returns a CompletableFuture<R> after
        // executing the apply() method of a Function. Since the apply() method has a return type R,
        // the stage returns a CompletableFuture<R>. The Function can use the value of the previous
        // stage with Type T and returns a CompletableFuture<R>, same as the Function's return type.
        // The difference between thenApply and thenCompose:
        // • the thenApply(?) corresponds to a map-like operation, working on a synchronous
        //   mapping function.
        // • the thenCompose(?) corresponds to a flatMap-like operation, working on an asynchronous
        //   mapping function via another CompletionStage.
        //
        // DONE: Create a chaining for the above CompletableFuture compose a check on the integer
        //  to check if it is boolean.
        // HINT:
        //  • Create a new CompletableFuture<Boolean> to invoke a thenCompose(?)
        //    on chainedFuture.
        //  • For the CompletionStage, return a previous stage output with
        //    Function<Integer, Boolean>.
        //      • For the internal function, use (i -> i % 2 == 0) to return a Boolean
        CompletableFuture<Boolean> isEvenCompletableFuture =
                chainedFuture.thenCompose(integer ->
                        CompletableFuture.supplyAsync(() -> integer % 2 == 0));

        Boolean isEven = isEvenCompletableFuture.join();

        assertNotNull(
                isEvenCompletableFuture,
                "The chained completableFuture should not be null");

        assertFalse(
                isEven,
                "The output should be false");
    }

    @DisplayName("4b. thenComposeAsync with ForkJoinPool")
    @Test
    @Tag("PASSING")
    @Order(11)
    public void thenComposeAsync() {

        // A thenCompose(?) adds a new Stage that returns a CompletableFuture<R> after
        // executing the apply() method of a Function. Since the apply() method has a return type R,
        // the stage returns a CompletableFuture<R>. The Function can use the value of the previous
        // stage with Type T and returns a CompletableFuture<R>, same as the Function's return type.
        // The difference between thenApply and thenCompose:
        // • the thenApply(?) corresponds to a map-like operation, working on a synchronous
        //   mapping function.
        // • the thenCompose(?) corresponds to a flatMap-like operation, working on an asynchronous
        //   mapping function via another CompletionStage.
        //
        // DONE: Create a chaining for the above CompletableFuture compose a check on the integer
        //  to check if it is boolean.
        // HINT:
        //  • Create a new CompletableFuture<Boolean> to invoke a thenComposeAsync(?)
        //    on chainedFuture.
        //  • For the CompletionStage, return a previous stage output with
        //    Function<Integer, Boolean>.
        //      • For the internal function, use (i -> i % 2 == 0) to return a Boolean
        CompletableFuture<Boolean> isEvenCompletableFuture =
                chainedFuture.thenComposeAsync(integer ->
                        CompletableFuture.supplyAsync(() -> integer % 2 == 0));

        Boolean isEven = isEvenCompletableFuture.join();

        assertNotNull(
                isEvenCompletableFuture,
                "The chained completableFuture should not be null");

        assertFalse(
                isEven,
                "The output should be false");
    }

    @DisplayName("4c. thenComposeAsync with an executor")
    @Test
    @Tag("PASSING")
    @Order(12)
    public void thenComposeAsyncWithExecutor() {

        // A thenCompose(?) adds a new Stage that returns a CompletableFuture<R> after
        // executing the apply() method of a Function. Since the apply() method has a return type R,
        // the stage returns a CompletableFuture<R>. The Function can use the value of the previous
        // stage with Type T and returns a CompletableFuture<R>, same as the Function's return type.
        // The difference between thenApply and thenCompose:
        // • the thenApply(?) corresponds to a map-like operation, working on a synchronous
        //   mapping function.
        // • the thenCompose(?) corresponds to a flatMap-like operation, working on an asynchronous
        //   mapping function via another CompletionStage.
        //
        // DONE: Create a chaining for the above CompletableFuture compose a check on the integer
        //  to check if it is boolean.
        // HINT:
        //  • Create a new CompletableFuture<Boolean> to invoke a thenComposeAsync(?)
        //    on chainedFuture.
        //  • For the CompletionStage, return a previous stage output with
        //    Function<Integer, Boolean>.
        //      • For the internal function, use (i -> i % 2 == 0) to return a Boolean
        CompletableFuture<Boolean> isEvenCompletableFuture =
                chainedFuture.thenComposeAsync(integer ->
                        CompletableFuture.supplyAsync(() -> integer % 2 == 0), executor);

        Boolean isEven = isEvenCompletableFuture.join();

        assertNotNull(
                isEvenCompletableFuture,
                "The chained completableFuture should not be null");

        assertFalse(
                isEven,
                "The output should be false");
    }

    @DisplayName("5a. thenCombine")
    @Test
    @Tag("PASSING")
    @Order(13)
    public void thenCombine() {

        CompletableFuture<Integer> minusTwoCompletableFuture =
                chainedFuture.thenApply(val -> val - 2);

        CompletableFuture<Boolean> isOddCompletableFuture =
                chainedFuture.thenApply(val -> val % 2 != 0);

        // A thenCombine(?) adds a new Stage that returns a CompletableFuture<V> after
        // executing the apply() method of a BiFunction. The BiFunction uses the outputs
        // (of type T and U) of two CompletableFuture instances and produces a flattened
        // CompletableFuture of type U.
        //
        // DONE: Create a chaining for two CompletableFuture instances, using a thenCombine. Use
        //  the outcome of the boolean to determine adding the integer to the list.
        // HINT:
        //  • Create a new CompletableFuture<Boolean> to invoke a thenCombine(?, ?)
        //    on minusTwoCompletableFuture.
        //  • The first argument is the isOddCompletableFuture.
        //  • The second argument is a BiFunction that uses the boolean and integer values.
        //  • If the isOdd returns a "true", add the integer value to the list.

        CompletableFuture<Integer> combinedCompletableFuture =
                minusTwoCompletableFuture.thenCombine(
                        isOddCompletableFuture, (value, isOdd) -> {
                            if(isOdd) {
                                aList.add(value);
                            }
                            return value;
                        }
                );

        Integer combinedValue = combinedCompletableFuture.join();

        assertNotNull(
                combinedCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                combinedValue,
                "The value should be 7");

        assertEquals(
                SEVEN,
                aList.get(1),
                "The value should be 7");
    }

    @DisplayName("5a. thenCombineAsync with ForkJoinPool")
    @Test
    @Tag("PASSING")
    @Order(14)
    public void thenCombineAsync() {

        CompletableFuture<Integer> minusTwoCompletableFuture =
                chainedFuture.thenApply(val -> val - 2);

        CompletableFuture<Boolean> isOddCompletableFuture =
                chainedFuture.thenApply(val -> val % 2 != 0);

        // A thenCombineAsync(?) adds a new Stage that returns a CompletableFuture<V> after
        // executing the apply() method of a BiFunction. The BiFunction uses the outputs
        // (of type T and U) of two CompletableFuture instances and produces a flattened
        // CompletableFuture of type U.
        //
        // DONE: Create a chaining for two CompletableFuture instances, using a thenCombineAsync.
        //  Use the outcome of the boolean to determine adding the integer to the list.
        // HINT:
        //  • Create a new CompletableFuture<Boolean> to invoke a thenCombine(?, ?)
        //    on minusTwoCompletableFuture.
        //  • The first argument is the isOddCompletableFuture.
        //  • The second argument is a BiFunction that uses the boolean and integer values.
        //  • If the isOdd returns a "true", add the integer value to the list.

        CompletableFuture<Integer> combinedCompletableFuture =
                minusTwoCompletableFuture.thenCombineAsync(
                        isOddCompletableFuture, (value, isOdd) -> {
                            if(isOdd) {
                                aList.add(value);
                            }
                            return value;
                        }
                );

        Integer combinedValue = combinedCompletableFuture.join();

        assertNotNull(
                combinedCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                combinedValue,
                "The value should be 7");

        assertEquals(
                SEVEN,
                aList.get(1),
                "The value should be 7");
    }

    @DisplayName("5a. thenCombineAsync with an executor")
    @Test
    @Tag("PASSING")
    @Order(15)
    public void thenCombineAsyncWithExecutor() {

        CompletableFuture<Integer> minusTwoCompletableFuture =
                chainedFuture.thenApply(val -> val - 2);

        CompletableFuture<Boolean> isOddCompletableFuture =
                chainedFuture.thenApply(val -> val % 2 != 0);

        // A thenCombine(?, ?, ?) adds a new Stage that returns a CompletableFuture<V> after
        // executing the apply() method of a BiFunction. The BiFunction uses the outputs
        // (of type T and U) of two CompletableFuture instances and produces a flattened
        // CompletableFuture of type U.
        //
        // DONE: Create a chaining for two CompletableFuture instances, using a thenCombine. Use
        //  the outcome of the boolean to determine adding the integer to the list.
        // HINT:
        //  • Create a new CompletableFuture<Boolean> to invoke a thenCombine(?, ?)
        //    on minusTwoCompletableFuture.
        //  • The first argument is the isOddCompletableFuture.
        //  • The second argument is a BiFunction that uses the boolean and integer values.
        //  • If the isOdd returns a "true", add the integer value to the list.

        CompletableFuture<Integer> combinedCompletableFuture =
                minusTwoCompletableFuture.thenCombineAsync(
                        isOddCompletableFuture, (value, isOdd) -> {
                            if(isOdd) {
                                aList.add(value);
                            }
                            return value;
                        },
                        executor
                );

        Integer combinedValue = combinedCompletableFuture.join();

        assertNotNull(
                combinedCompletableFuture,
                "The chained completableFuture should not be null");

        assertEquals(
                SEVEN,
                combinedValue,
                "The value should be 7");

        assertEquals(
                SEVEN,
                aList.get(1),
                "The value should be 7");
    }
}