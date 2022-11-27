package none.cvg.futures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * This test covers the basic features of creating CompletableFuture instances and fetching
 * results.
 */
@DisplayName("Simple CompletableFuture operations to: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution1SimpleCompletableFutureOperations {

    private static final String REPLACE_THIS = "";

    @BeforeEach
    public void setUp() {
    }

    @DisplayName("create a completed CompletableFuture")
    @Test
    @Tag("PASSING")
    @Order(1)
    public void createCompletedCompletableFuture() {

        String successMessage = "This is a completed future";

        // completedFuture() returns a new CompletableFuture that is already completed with the
        // given value.
        // DONE:
        //  Create a completedFuture. Replace the null with a completedFuture.
        // HINT:
        //  • Use the static CompletableFuture.completedFuture() method with
        //    the successMessage.
        CompletableFuture<String> completedFuture =
                CompletableFuture.completedFuture(successMessage);

        assertNotNull(completedFuture,
                "The completedFuture should not be null");

        assertTrue(completedFuture instanceof CompletableFuture,
                "The completedFuture should be an instance of CompletableFuture");
    }

    @DisplayName("create a failed CompletableFuture")
    @Test
    @Tag("PASSING")
    @Order(2)
    public void failedCompletableFuture() {

        String exceptionMessage = "This CompletableFuture has failed";

        // failedFuture() returns a new CompletableFuture that is already completed
        // exceptionally with the given exception.
        // DONE:
        //  Create a failedFuture. Replace the null with a failedFuture.
        // HINT:
        //  • Use the static CompletableFuture.failedFuture() method with
        //    a new RuntimeException(exceptionMessage).
        CompletableFuture<String> failedFuture =
                CompletableFuture.failedFuture(new RuntimeException(exceptionMessage));

        assertNotNull(failedFuture,
                "The completedFuture should not be null");

        assertTrue(failedFuture instanceof CompletableFuture,
                "The completedFuture should be an instance of CompletableFuture");
    }

    @DisplayName("check if a future is completed")
    @Test
    @Tag("PASSING")
    @Order(3)
    public void checkCompletableFuture() {

        String successMessage = "This is a completed future";

        CompletableFuture<String> completedFuture = CompletableFuture
                .completedFuture(successMessage);

        // DONE:
        //  Check if the completedFuture is done. Replace the 'false' with a check.
        // HINT:
        //  • Use the instance method isDone() on CompletableFuture.
        assertTrue(completedFuture.isDone(),
                "The completedFuture should be done");
    }

    @DisplayName("get the value of a CompletableFuture using get()")
    @Test
    @Tag("PASSING")
    @Order(4)
    public void valueOfCompletableFutureUsingGet() {

        String successMessage = "This is a completed future";

        CompletableFuture<String> completedFuture = CompletableFuture
                .completedFuture(successMessage);

        try {
            // DONE:
            //  Get the value of the completedFuture. Replace the REPLACE_THIS_STRING with a
            //  valid call.
            // HINT:
            //  • Use the instance method get() on CompletableFuture.
            //  • The get() method throws checked exceptions, which need to be caught and handled.
            assertEquals(successMessage,
                    completedFuture.get(),
                    "The completedFuture value should return the content of successMessage");
            // DONE:
            //  Replace the catch-block with right Exceptions thrown by the get().
        } catch (InterruptedException | ExecutionException e) {
            fail("Failed to get the value of the CompletedFuture");
        }
    }

    @DisplayName("get the value of a CompletableFuture using join()")
    @Test
    @Tag("PASSING")
    @Order(5)
    public void valueOfCompletableFutureUsingJoin() {

        String successMessage = "This is a completed future";

        CompletableFuture<String> completedFuture = CompletableFuture
                .completedFuture(successMessage);

        // DONE:
        //  Get the value of the completedFuture. Replace the REPLACE_THIS_STRING with a
        //  valid call.
        // HINT:
        //  • Use the instance method join() on CompletableFuture.
        //  • The join() method throws unchecked exceptions, which need not be caught.
        // The join method throws the following RuntimeExceptions:
        // • java.util.concurrent.CancellationException
        // • java.util.concurrent.CompletionException
        assertEquals(successMessage,
                completedFuture.join(),
                "The completedFuture value should return the content of successMessage");
    }
}