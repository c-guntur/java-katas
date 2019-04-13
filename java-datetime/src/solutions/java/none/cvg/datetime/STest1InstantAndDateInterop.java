package none.cvg.datetime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static none.cvg.datetime.LenientAssert.assertAlmostEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Instant and Date interoperability.
 *
 * @see Instant
 * @see Date
 * @see LenientAssert
 */
@DisplayNameGeneration(DateTimeKataDisplayNames.class)
@DisplayName("Instant And Date Interoperability")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class STest1InstantAndDateInterop {

    private Date classicDate;
    private SimpleDateFormat classicDateFormatter = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeEach
    public void setup() {
        classicDate = new Date();
    }

    @Test
    @Tag("PASSING")
    @Order(1)
    public void verifyInstantAndDateHaveSameEpochMilliseconds() {

        // TODO: Replace with an Instant generated from a Date
        //  Use a Date API that converts Date instances into Instant instances.
        //  Check: java.util.Date.toInstant()
        Instant instant = classicDate.toInstant();

        // TODO: Fix the "null" below to get milliseconds from epoch from the Instant
        //  Use an Instant API which converts it into milliseconds
        //  Check: java.time.Instant.toEpochMilli()
        assertEquals(Long.valueOf(classicDate.getTime()),
                instant.toEpochMilli(),
                "Date and Instant milliseconds should be equal");
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void verifyInstantAndDateHaveAlmostSameEpochSeconds() {

        Instant instant = classicDate.toInstant();

        // NOTE: There is no simpler API method to get epoch seconds.
        long classicDateInSeconds = classicDate.getTime() / 1000;

        // TODO: Replace the "null" below to get seconds from epoch from the Instant
        //  Assert that the seconds from epoch from both Date and Instant are equal.
        //  Check: java.time.Instant.getEpochSecond()
        // NOTE: We use a custom assertion here since the millis to second arithmetic may cause
        //       rounding issues. We add a tolerance of 1 second.
        assertAlmostEquals(classicDateInSeconds,
                instant.getEpochSecond(),
                1L,
                "Date and Instant seconds should almost match");
    }

    @Test
    @Tag("PASSING")
    @Order(3)
    public void verifyInstantHasNanoseconds() {

        Instant instant = Instant.now();

        // TODO: Replace the "null" below to get nanos from the Instant
        //  Assert that instant has nano seconds.
        //  Check: java.time.Instant.getNano()
        assertTrue(Integer.valueOf(instant.getNano()) > -1,
                "Instant should have nanoseconds");
    }

    @Test
    @Tag("PASSING")
    @Order(4)
    public void verifyInstantDefaultFormatting() {

        classicDateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        Instant instant = classicDate.toInstant();

        // TODO: Replace the "null" below to retrieve an instant as a string
        //  Assert that instant default toString matches the ISO8601 full date format.
        assertEquals(classicDateFormatter.format(classicDate),
                instant.toString(),
                "Instant toString() should match ISO8601 format");
    }

    @Test
    @Tag("PASSING")
    @Order(5)
    public void verifyInstantDateInteroperability() {

        // *****************************************************
        // Converting Date to an Instant.
        // *****************************************************
        Instant instant = classicDate.toInstant();
        assertEquals(classicDate.getTime(), instant.toEpochMilli());


        // *****************************************************
        // Converting an Instant to a Date.
        // *****************************************************
        // TODO: Replace the "null" below to convert an Instant into a Date
        //  Check: java.util.Date.from()
        Date anotherDate = Date.from(instant);
        assertEquals(classicDate, anotherDate);

        // *****************************************************
        // Think about why all conversions and inter-ops are
        // built into Date and not the newer java.time API.
        // *****************************************************
    }

}
