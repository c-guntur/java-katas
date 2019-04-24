package none.cvg.optional;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionalOrExample {

    public static String FAKE_VALUE = "fakeValue";
    public static String TOP_RATED = "Top rated preference";

    @Test
    public void preJava8Example() {

        //Sunny day scenario, creation succeeds in the factory.
        Preference preferenceAAA = PreferenceFactory.findPreference("AAA");
        if (preferenceAAA == null) {
            preferenceAAA = PreferenceFactory.createPreference("AAA", TOP_RATED);
        }
        if (preferenceAAA == null) {
            preferenceAAA = new Preference("AAA", FAKE_VALUE);
        }

        assertEquals(TOP_RATED, preferenceAAA.getDescription(), "AAA should map to a saved preference");

        //Rainy day scenario, creation fails in the factory.
        Preference preferenceOOO = PreferenceFactory.findPreference("OOO");
        if (preferenceOOO == null) {
            preferenceOOO = PreferenceFactory.createPreference("OOO", TOP_RATED);
        }
        if (preferenceOOO == null) {
            preferenceOOO = new Preference("OOO", FAKE_VALUE);
        }

        assertEquals(FAKE_VALUE, preferenceOOO.getDescription(), "OOO should default to a fake preference");
    }

    @Test
    public void java8Example() {
        //Sunny day scenario, creation succeeds in the factory.
        Preference preferenceAAA =
                PreferenceFactory
                        .findOptionalPreference("AAA")
                        .orElseGet(PreferenceFactory.getPreferenceSupplier("AAA", TOP_RATED));
        if (preferenceAAA == null) {
            preferenceAAA = new Preference("AAA", FAKE_VALUE);
        }

        assertEquals(TOP_RATED, preferenceAAA.getDescription(), "AAA should map to a saved preference");

        //Rainy day scenario, creation fails in the factory.
        Preference preferenceOOO =
                PreferenceFactory
                        .findOptionalPreference("OOO")
                        .orElseGet(PreferenceFactory.getPreferenceSupplier("OOO", TOP_RATED));
        if (preferenceOOO == null) {
            preferenceOOO = new Preference("OOO", FAKE_VALUE);
        }

        assertEquals(FAKE_VALUE, preferenceOOO.getDescription(), "OOO should default to a fake preference");
    }

    @Test
    public void java9Example() {
        System.out.println("In Java 9 --------------------------------- >>>>");

        //Sunny day scenario, creation succeeds in the factory.
        Optional<Preference> optionalPreferenceAAA =
                PreferenceFactory
                        .findOptionalPreference("AAA")
                        .or(PreferenceFactory.getPreferenceOptionalSupplier("AAA", TOP_RATED));
        Preference preferenceAAA = optionalPreferenceAAA.orElse(new Preference("AAA", FAKE_VALUE));

        assertEquals(TOP_RATED, preferenceAAA.getDescription(), "AAA should map to a saved preference");

        //Rainy day scenario, creation fails in the factory.
        Optional<Preference> optionalPreferenceOOO =
                PreferenceFactory
                        .findOptionalPreference("OOO")
                        .or(PreferenceFactory.getPreferenceOptionalSupplier("OOO", TOP_RATED));
        Preference preferenceOOO = optionalPreferenceOOO.orElse(new Preference("OOO", FAKE_VALUE));

        assertEquals(FAKE_VALUE, preferenceOOO.getDescription(), "OOO should default to a fake preference");
    }
}
