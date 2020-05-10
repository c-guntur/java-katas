package none.cvg.lambdas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(LambdasKataDisplayNames.class)
@DisplayName("Lambdas - A deeper dive")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution2LambdasDeeperDive {

    @Test
    @Tag("PASSING")
    @Order(1)
    @DisplayName("use a BiFunction")
    public void useBiFunction() {

        final int integer1 = 3;
        final int integer2 = 4;

        // DONE:
        //  Replace the MyBiFuncton class with a BiFunction named add.
        //  Replace the max with a sum() function.
        //  -------------------
        //  Hint: BiFunction<Integer, Integer, Integer> adder = (x, y) -> //do something
        //  Check API: java.util.function.BiFunction
        //  Check API: java.util.function.BiFunction.apply
        BiFunction<Integer, Integer, Integer> adder = (x, y) -> Integer.sum(x, y);
        //  -------------------

        assertEquals(7, adder.apply(integer1, integer2),
                "The function should produce a result of 7");
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void partialFunctions() {
        // Partial application describes the conversion of a multi-argument function
        // into one that accepts fewer arguments, with values for the elided arguments
        // supplied in advance. It partially applies some arguments to a function,
        // returning a function with a signature that consists of the remaining arguments.
        BiFunction<Integer, Integer, Integer> add = (x, y) -> x + y;

        // TODO:
        //  Replace the below Function to create a partial function from the above BiFunction
        //  Create an addOne, which passes in a 1 and a int n to the add function and
        //  returns a partial application of 1, n.
        //  HINT: use the add.apply(?, ?)
        Function<Integer, Integer> addOne = n -> add.apply(1, n);

        // TODO:
        //  Replace the 6 with a call to addOne
        //  HINT: use the addOne.apply() to return a value of 7
        assertEquals(7, addOne.apply(6),
                "The function should return 7");
    }

    /**
     * @see Calculator - An interface with a single calculate() abstract method.
     */
    @Test
    @Tag("PASSING")
    @Order(3)
    public void customSingleAbstractFunctions() {
        // DONE:
        //  Replace the below Function to a lambda
        //  Replace the zero with a valid function call
        Calculator addition = (x, y) -> x + y;

        // DONE:
        //  Replace the below Function to a lambda
        //  Replace the zero with a valid function call
        Calculator subtraction = (x, y) -> x - y;

        // DONE:
        //  Replace the below Function to a lambda
        //  Replace the zero with a valid function call
        Calculator multiplication = (x, y) -> x * y;

        assertTrue(7 == addition.calculate(6, 1),
                "The addition should return a value of 7");

        assertTrue(7 == subtraction.calculate(10, 3),
                "The subtraction should return a value of 7");

        assertTrue(49 == multiplication.calculate(7, 7),
                "The multiplication should return a value of 49");
    }

    @Test
    @Tag("PASSING")
    @Order(4)
    public void sortNames() {
        List<Person> persons =
                Arrays.asList(Person.ALICE, Person.BOB, Person.CATHY, Person.DHRUV, Person.EMILY);
        List<Person> expectedList =
                Arrays.asList(Person.EMILY, Person.BOB, Person.DHRUV, Person.ALICE, Person.CATHY);

        // TODO:
        //  Replace the anonymous class with a lambda.
        //  Replace the postions of o2 and o1 to pass the test as well

        // -----------------  Solution 1 -----------------
//        Comparator nameSorter = (Comparator<Person>) (o1, o2) -> o1.getLastName().compareTo(o2.getLastName());
//        List<Person> actualList = new ArrayList<>();
//        actualList.addAll(persons);
//        Collections.sort(actualList, nameSorter);
        // -----------------------------------------------

        // -----------------  Solution 2 -----------------
        Comparator<Person> nameSorter = Comparator.comparing(Person::getLastName);
        List<Person> actualList = persons.stream().sorted(nameSorter).collect(Collectors.toList());
        // -----------------------------------------------

        assertEquals(expectedList, actualList, "The sorted lists should match");
    }
}
