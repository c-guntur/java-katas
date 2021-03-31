package none.cvg.futures;

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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * This test covers some common executions in CompletableFuture
 */
@DisplayName("CompletableFuture executions to: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestKata3CompletableFutureExecutions {

    private static final Integer SEVEN = 7;
    private static final Integer NINE = 9;

    private List<Integer> aList;

    @BeforeEach
    public void setup() {

        aList = new ArrayList<>();
        aList.add(NINE);
    }

    @DisplayName("runAsync() with ForkJoinPool")
    @Test
    @Tag("TODO")
    @Order(1)
    public void runAsync() {

        // runAsync(?) performs an action via a Runnable but does not return anything.
        // The runAsync() is a static method that returns a CompletableFuture of type Void. As the
        // method name suggests, it relies on invoking the run method of a Runnable (typically set
        // as a lambda). Since the call is Async, it uses a ThreadPool.
        //
        // In this case, since no executor is provided, the task is completed using the
        // ForkJoinPool.commonPool().
        // TODO:
        //  Replace the null with a static CompletableFuture.runAsync(?) to set the value of the
        //  counter to 2.
        // HINT:
        //  • Use the static CompletableFuture.runAsync(?) method
        //  • For the Runnable, add an integer SEVEN to the aList
        CompletableFuture<Void> voidCompletableFuture =
                null;

        // Ensure completion
        voidCompletableFuture.join();

        assertNotNull(voidCompletableFuture,
                "The completedFuture should be null");

        assertEquals(2,
                aList.size(),
                "The list should have a size of 2");
    }

    @DisplayName("runAsync() with an executor")
    @Test
    @Tag("TODO")
    @Order(2)
    public void runAsyncWithExecutor() {

        // An executor pool of exactly one thread.
        final ExecutorService executor = Executors.newFixedThreadPool(1);

        // runAsync(?, ?) performs an action via a Runnable but does not return anything.
        // The runAsync() is a static method that returns a CompletableFuture of type Void. As the
        // method name suggests, it relies on invoking the run method of a Runnable (typically set
        // as a lambda). Since the call is Async, it uses a ThreadPool.
        //
        // In this case, since an executor is provided, the task is completed using the executor.
        // TODO:
        //  Replace the null with a static CompletableFuture.runAsync(?, ?) to set the value of the
        //  counter to 2.
        // HINT:
        //  • Use the static CompletableFuture.runAsync(?, ?) method
        //  • For the Runnable, add an integer SEVEN to the aList
        //  • For the second argument, supply the executor
        CompletableFuture<Void> voidCompletableFuture =
                null;

        // Ensure completion
        voidCompletableFuture.join();

        assertNotNull(voidCompletableFuture,
                "The completedFuture should be null");

        assertEquals(2,
                aList.size(),
                "The list should have a size of 2");
    }

    @DisplayName("supplyAsync() with ForkJoinPool")
    @Test
    @Tag("TODO")
    @Order(3)
    public void supplyAsync() throws ExecutionException, InterruptedException {

        // supplyAsync(?) performs an action via a Supplier<T> which returns A CompletableFuture<T>.
        // The supplyAsync() is a static method that returns a CompletableFuture of type T when
        // used with a Supplier<T>. As the method name suggests, it relies on invoking the get
        // method of a Supplier (typically set as a lambda). Since the call is Async, it uses a
        // ThreadPool.
        //
        // In this case, since no executor is provided, the task is completed using the
        // ForkJoinPool.commonPool().
        // TODO:
        //  Replace the null with a static CompletableFuture.supplyAsync(?) to create a Supplier
        //  that returns a String
        // HINT:
        //  • Use the static CompletableFuture.supplyAsync(?) method
        //  • For the Supplier, return the first element of aList
        CompletableFuture<Integer> suppliedCompletableFuture =
                null;

        suppliedCompletableFuture.join();

        assertNotNull(suppliedCompletableFuture,
                "The completedFuture should be null");

        assertEquals(NINE,
                suppliedCompletableFuture.get(),
                "The Integer values should be the same");
    }

    @DisplayName("supplyAsync() with an executor")
    @Test
    @Tag("TODO")
    @Order(4)
    public void supplyAsyncWithExecutor() throws ExecutionException, InterruptedException {

        // An executor pool of exactly one thread.
        final ExecutorService executor = Executors.newFixedThreadPool(1);

        // supplyAsync(?, ?) performs an action via a Supplier<T> which returns a
        // CompletableFuture<T>.
        // The supplyAsync() is a static method that returns a CompletableFuture of type T when
        // used with a Supplier<T>. As the method name suggests, it relies on invoking the get
        // method of a Supplier (typically set as a lambda). Since the call is Async, it uses a
        // ThreadPool.
        //
        // In this case, since an executor is provided, the task is completed using the executor.
        // TODO:
        //  Replace the null with a static CompletableFuture.supplyAsync(?) to create a Supplier
        //  that returns a String
        // HINT:
        //  • Use the static CompletableFuture.supplyAsync(?) method
        //  • For the Supplier, return the first element of aList
        CompletableFuture<Integer> suppliedCompletableFuture =
                null;

        suppliedCompletableFuture.join();

        assertNotNull(suppliedCompletableFuture,
                "The completedFuture should be null");

        assertEquals(NINE,
                suppliedCompletableFuture.get(),
                "The Integer values should be the same");
    }
}