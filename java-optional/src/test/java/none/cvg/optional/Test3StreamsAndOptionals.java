package none.cvg.optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * TODO:
 *  These tests show how Optionals can be used in stream APIs
 *  The first test demonstrates a simple usage of Optional using isPresent() and get()
 *  while the second test shows the newer API flatMap() that returns Optionals if they exist
 *  and discards empty values.
 *
 *  stream(), isPresent(), get()
 */
@DisplayName("Optional - using Optionals in collections with Stream API")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test3StreamsAndOptionals {

    private Set<NameValuePair> expectedSetOfNameValuePairs;
    private Set<String> namesSet = new HashSet<>();
    private Map<String, NameValuePair> nameNameValuePairMap = new HashMap<>();

    private static class NameValuePair implements Comparable {
        private String name;

        private String value;

        public NameValuePair() {
        }

        public NameValuePair(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public NameValuePair name(final String name) {
            this.name = name;
            return this;
        }

        public NameValuePair value(final String value) {
            this.value = value;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(Object o) {
            NameValuePair that = (NameValuePair) o;
            return this.getName().compareTo(that.getName());
        }

    }

    @BeforeEach
    public void setUp() {
        expectedSetOfNameValuePairs = new TreeSet<>();
        expectedSetOfNameValuePairs.add(new NameValuePair("ABC", "01_02_03"));
        expectedSetOfNameValuePairs.add(new NameValuePair("DEF", "04_05_06"));
        expectedSetOfNameValuePairs.add(new NameValuePair("XYZ", "24_25_26"));

        namesSet = new HashSet<>();
        namesSet.add("ABC");
        namesSet.add("DEF");
        namesSet.add("XYZ");
        namesSet.add("ZZZ");

        nameNameValuePairMap.put("ABC", new NameValuePair("ABC", "01_02_03"));
        nameNameValuePairMap.put("DEF", new NameValuePair("DEF", "04_05_06"));
        nameNameValuePairMap.put("XYZ", new NameValuePair("XYZ", "24_25_26"));
    }

    public Optional<NameValuePair> findOptionalNameValuePair(String name) {
        return Optional.ofNullable(nameNameValuePairMap.get(name));
    }

    @Test
    @DisplayName("Java 8 Streaming with Optional")
    @Tag("TODO")
    @Order(1)
    public void java8Example() {

        /*
         * TODO:
         *  Replace the code below to use stream() API:
         *  1. stream() the namesSet
         *  2.   map() to get an Optional<NameValuePair> using this::findOptionalNameValuePair(?)
         *  3.   filter() to include non empty Optionals, use Optional::isPresent as predicate
         *  4.   map() to get the actual NameValuePair object, use Optional::get
         *  5.   collect() to a Set, use Collectors.toSet()
         */
        Set<NameValuePair> actualNameValuePairs = new TreeSet<>();
        for (String name : namesSet) {
            NameValuePair nameValuePair = nameNameValuePairMap.get(name);
            if (nameValuePair != null) {
                actualNameValuePairs.add(nameValuePair);
            }
        }

        assertEquals(
                expectedSetOfNameValuePairs,
                actualNameValuePairs,
                "The two collections should be the same");
    }

    @Test
    @DisplayName("Java 9 and above Streaming with Optional")
    @Tag("TODO")
    @Order(2)
    public void java9Example() {
        /*
         * TODO:
         *  Replace the code below to use stream() API:
         *  1. stream() the namesSet
         *  2.   map() to get an Optional<NameValuePair> using this::findOptionalNameValuePair(?)
         *  3.   flatMap() to include non empty Optionals, use Optional::stream flattener
         *  4.   collect() to a Set, use Collectors.toSet()
         */
        Set<NameValuePair> actualNameValuePairs = new TreeSet<>();
        for (String name : namesSet) {
            NameValuePair nameValuePair = nameNameValuePairMap.get(name);
            if (nameValuePair != null) {
                actualNameValuePairs.add(nameValuePair);
            }
        }

        assertEquals(
                expectedSetOfNameValuePairs,
                actualNameValuePairs,
                "The two collections should be the same");
    }

}
