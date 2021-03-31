package none.cvg.futures;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * This test covers how CompletableFuture exceptions are handled
 */
@DisplayName("Handle exceptions in CompletableFuture with: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution2HandleExceptionsInCompletableFuture {

    private static final String REPLACE_THIS = "";

    @DisplayName("whenComplete()")
    @Test
    @Tag("PASSING")
    @Order(1)
    public void whenComplete() {

        String failureMessage = "This CompletableFuture has failed";

        // NOTE: See DummyException created in this class.
        CompletableFuture<String> failedFuture = CompletableFuture
                .failedFuture(new DummyException(failureMessage));


        // DONE: Replace the below try-catch block with a whenComplete instance method
        //  on CompletableFuture, that can work with either a success return or a failure exception
        //  and accepts either via a BiConsumer.
        // HINT:
        //  • Call the whenComplete(message, exception) instance method on failedFuture
        //  • Create a BiConsumer to handle either success message (String) or an Exception.
        //  • Since we know that this is a failedFuture, assert the exception is of the right type.
        //  • The exception is already casted, so can be used without the e.getCause()
        //  • Verify the exception's message matches the failureMessage.
        // NOTE: A whenComplete() method uses a BiConsumer, so it cannot return a value.

        failedFuture.whenComplete(
                (message, exception) -> {
                    assertTrue(exception instanceof DummyException);
                    assertEquals(failureMessage, exception.getMessage());
                }
        );
    }

    @DisplayName("handle()")
    @Test
    @Tag("PASSING")
    @Order(2)
    public void handle() {

        String failureMessage = "This CompletableFuture has failed";
        String handleOutput = "try to recover via handle()";

        // NOTE: See DummyException created in this class.
        CompletableFuture<String> failedFuture = CompletableFuture
                .failedFuture(new DummyException(failureMessage));

        // DONE: Replace the below try-catch block with a whenComplete instance method
        //  on CompletableFuture, that can work with either a success return or a failure exception
        //  and accepts either via a BiConsumer.
        // HINT:
        //  • Call the handle(message, exception) instance method on failedFuture
        //  • Create a BiFunction to handle either success message (String) or an Exception.
        //  • Since we know that this is a failedFuture, assert the exception is of the right type.
        //  • The exception is already casted, so can be used without the e.getCause()
        //  • Verify the exception's message matches the failureMessage.
        // NOTE: A handle() method uses a BiFunction, so it can return a value.

        CompletableFuture<String> handledValue = failedFuture.handle(
                (message, exception) -> {

                    assertTrue(exception instanceof DummyException);
                    assertEquals(failureMessage, exception.getMessage());
                    return handleOutput;
                }
        );

        assertEquals(
                handleOutput,
                handledValue.join(),
                "The handled value should match the handleOutput");
    }

    @DisplayName("exceptionally()")
    @Test
    @Tag("PASSING")
    @Order(3)
    public void exceptionally() {

        String failureMessage = "This CompletableFuture has failed";
        String exceptionallyOutput = "try to recover via exceptionally()";

        // NOTE: See DummyException created in this class.
        CompletableFuture<String> failedFuture = CompletableFuture
                .failedFuture(new DummyException(failureMessage));


        // DONE: Replace the below try-catch block with a whenComplete instance method
        //  on CompletableFuture, that can work with either a success return or a failure exception
        //  and accepts either via a BiConsumer.
        // HINT:
        //  • Call the exceptionally(exception) instance method on failedFuture
        //  • Create a Function to handle an Exception.
        //  • Since we know that this is a failedFuture, assert the exception is of the right type.
        //  • The exception is already casted, so can be used without the e.getCause()
        //  • Verify the exception's message matches the failureMessage.
        // NOTE: An exceptionally() method uses a Function, so it can return a value.

        CompletableFuture<String> exceptionallyValue = failedFuture.exceptionally(
                (exception) -> {

                    assertTrue(exception instanceof DummyException);
                    assertEquals(failureMessage, exception.getMessage());
                    return exceptionallyOutput;
                }
        );

        assertEquals(
                exceptionallyOutput,
                exceptionallyValue.join(),
                "The exceptionally value should match the exceptionallyOutput");
    }

    static class DummyException extends RuntimeException {
        public DummyException(String message) {
            super(message);
        }
    }
}