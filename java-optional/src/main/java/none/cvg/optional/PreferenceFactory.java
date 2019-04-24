package none.cvg.optional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

public class PreferenceFactory {
    public static Set<String> PREFERENCE_NAMES = new HashSet<>();
    private static Map<String, Preference> PREFERNCE_MAP = new HashMap<>();

    static {
        PREFERENCE_NAMES.add("ABC");
        PREFERENCE_NAMES.add("DEF");
        PREFERENCE_NAMES.add("XYZ");
        PREFERENCE_NAMES.add("ZZZ");

        PREFERNCE_MAP.put("ABC", new Preference("ABC", "01_02_03"));
        PREFERNCE_MAP.put("DEF", new Preference("DEF", "04_05_06"));
        PREFERNCE_MAP.put("XYZ", new Preference("XYZ", "24_25_26"));
    }

    /**
     * Find a preference given the key. Return null if preference not found.
     *
     * @param name Key to look up a mapped preference.
     * @return a Preference instance mapped to the given name.
     */
    public static Preference findPreference(String name) {
        return PREFERNCE_MAP.get(name);
    }

    /**
     * Find a preference given the key. Return Optional.empty() if preference not found.
     *
     * @param name Key to look up a mapped preference.
     * @return an Optional wrapper over a Preference instance mapped to the given name.
     */
    public static Optional<Preference> findOptionalPreference(String name) {
        return Optional.ofNullable(PREFERNCE_MAP.get(name));
    }

    /**
     * Put a new entry into the preference map, simulate a failure when adding a key for OOO.
     *
     * @param name        A key for the preference mapping.
     * @param description A description attribute that makes up partof the Preference instance.
     * @return the created Preference - or - a null if the key is OOO
     */
    public static Preference createPreference(String name, String description) {
        if (!"OOO".equalsIgnoreCase(name)) {
            // Fake case of creation failure
            PREFERNCE_MAP.put(name, new Preference(name, description));
        }
        return PREFERNCE_MAP.get(name);
    }

    /**
     * Create a preference supplier, simulate a failure when adding a key for OOO.
     *
     * @param name        A key for the preference mapping.
     * @param description A description attribute that makes up partof the Preference instance.
     * @return the created Supplier of Preference - or - a null Supplier if the key is OOO
     */
    public static Supplier<Preference> getPreferenceSupplier(String name, String description) {
        if (!"OOO".equalsIgnoreCase(name)) {
            PREFERNCE_MAP.put(name, new Preference(name, description));
        }
        return () -> PREFERNCE_MAP.get(name);
    }

    /**
     * Create a preference supplier, simulate a failure when adding a key for OOO.
     *
     * @param name        A key for the preference mapping.
     * @param description A description attribute that makes up partof the Preference instance.
     * @return the created Supplier of an Optional wrapper over Preference - or - an Optional.empty() Supplier if the key is OOO
     */
    public static Supplier<Optional<Preference>> getPreferenceOptionalSupplier(String name, String description) {
        if (!"OOO".equalsIgnoreCase(name)) {
            PREFERNCE_MAP.put(name, new Preference(name, description));
        }
        return () -> Optional.ofNullable(PREFERNCE_MAP.get(name));
    }
}
