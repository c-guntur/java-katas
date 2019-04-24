package none.cvg.optional;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test shows the same functionality using different versions of Java.
 * Code readability and fluency increases with each version of Java.
 * The scenario is to fetch all preferences into a TreeSet.
 * TreeSet is used only to pretty-print the output, since other Set
 * implementations (which do no guarantee any order) result in erratic
 * assertion failure messages that are not easy to read.
 */
public class OptionalStreamExample {

    Set<Preference> expected;

    @BeforeEach
    public void setUp() {
        expected = new TreeSet<>();
        expected.add(new Preference("ABC", "01_02_03"));
        expected.add(new Preference("DEF", "04_05_06"));
        expected.add(new Preference("XYZ", "24_25_26"));
    }

    @Test
    public void preJava8Example() {
        Set<Preference> preferences = new TreeSet<>();
        for (String preferenceName : PreferenceFactory.PREFERENCE_NAMES) {
            Preference aPreference = PreferenceFactory.findPreference(preferenceName);
            if (aPreference != null) {
                preferences.add(aPreference);
            }
        }
        assertEquals(expected, preferences, "The two collections should be the same");
    }

    @Test
    public void java8Example() {
        Set<Preference> preferences =
                PreferenceFactory.PREFERENCE_NAMES.stream()
                        .map(PreferenceFactory::findOptionalPreference)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toSet());
        preferences = new TreeSet<>(preferences);
        assertEquals(expected, preferences, "The two collections should be the same");
    }

    @Test
    public void java9Example() {
        Set<Preference> preferences =
                PreferenceFactory.PREFERENCE_NAMES.stream()
                        .map(PreferenceFactory::findOptionalPreference)
                        .flatMap(Optional::stream)
                        .collect(Collectors.toSet());
        preferences = new TreeSet<>(preferences);
        assertEquals(expected, preferences, "The two collections should be the same");
    }
}
