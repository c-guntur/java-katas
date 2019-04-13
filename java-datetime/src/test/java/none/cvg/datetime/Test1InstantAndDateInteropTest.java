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

import static none.cvg.datetime.LenientAssert.FIX_WITH_RIGHT_CALL;
import static none.cvg.datetime.LenientAssert.assertAlmostEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
public class Test1InstantAndDateInteropTest {

    private Date classicDate;
    private Instant java8Instant;
    private SimpleDateFormat classicDateFormatter = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ss.SSS'Z'");

    @BeforeEach
    public void setup() {

        classicDate = new Date();
        java8Instant = null; //FIXME
        //-----------------------------------------
        // java8Instant = Instant.???;
    }

    @Test
    @Tag("TODO")
    @Order(1)
    public void convertInstantToDate() {

        // *****************************************************
        // If the below conversion is not performed, the setup
        // assigns a value to the Instant some time after the
        // Date assignment, thus not being always equal.
        // In some tests below, we use an 'assertAlmostEquals()'
        // to circumvent this.
        // *****************************************************
        // TODO: Replace with Date to instant conversion
        fail("Delete this fail() and FIX the line below.");
        // java8Instant = classicDate.???;
    }

    @Test
    @Tag("TODO")
    @Order(2)
    public void convertInstantToMilliseconds() {

        // *****************************************************
        // Notice we use a tolerance when checking milliseconds.
        // We assigned the date and instant one after the other,
        // that could render a few millisecond difference. We
        // thus need to allow for a few milliseconds off.
        // *****************************************************

        // TODO: Assert that the Instant milliseconds form epoch almost equals the date milliseconds.
        // REPLACE the FIX_WITH_RIGHT_CALL below with a method call on Instant.
        //-----------------------------------------
//        assertAlmostEquals("Date and Instant milliseconds should almost match", classicDate.getTime(), FIX_WITH_RIGHT_CALL, 10);
    }

    @Test
    @Tag("TODO")
    @Order(3)
    public void convertInstantToSeconds() {

        // *****************************************************
        // Notice we use a tolerance when checking seconds.
        // We assigned the date and instant one after the other,
        // that could render a few milliseconds difference. We
        // thus need to allow for at least a second off.
        // *****************************************************

        long classicDateInSeconds = classicDate.getTime() / 1000;
        // TODO: Assert that the Instant seconds form epoch almost equals the date seconds from epoch calculation.
        // REPLACE the FIX_WITH_RIGHT_CALL below with a method call on Instant.
//        assertAlmostEqual("Date and Instant seconds should almost match", classicDateInSeconds, FIX_WITH_RIGHT_CALL, 1);
    }

    @Test
    @Tag("TODO")
    @Order(4)
    public void convertInstantHasNanoseconds() {

        // TODO: Assert that instant has nano seconds > 0
        fail("Delete this fail() and FIX the assertion below.");
        // assertTrue("Instant should have nanoseconds", java8Instant.??? > 0);
    }

    @Test
    @Tag("TODO")
    @Order(5)
    public void verifyInstantDefaultFormatting() {

        classicDateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        // TODO: Assert that instant default toString matches the ISO8601 full date format.
        // REPLACE the FIX_WITH_RIGHT_CALL below with a method call on Instant.
        assertEquals(classicDateFormatter.format(classicDate), FIX_WITH_RIGHT_CALL,
                "Instant toString() should match ISO8601 format");
    }

    @Test
    @Tag("TODO")
    @Order(6)
    public void verifyInstantDateInteroperability() {

        // *****************************************************
        // Converting Date to an Instant.
        // *****************************************************

        // TODO: Verify interoperability between classic and java8 styles
        // REPLACE with conversion from Date to Instant below
        //-----------------------------------------
        Instant anotherInstant = null;
        fail("Delete this fail() and FIX the assertion below.");
        //-----------------------------------------
        //assertEquals("The milliseconds from classic date and the anotherInstant should match", classicDate.getTime(), anotherInstant.toEpochMilli());


        // *****************************************************
        // Converting an Instant to a Date.
        // *****************************************************

        // REPLACE with conversion from Instant to Date below
        //-----------------------------------------
        Date anotherDate = null;
        fail("Delete this fail() and FIX the assertion below.");
        //-----------------------------------------
        //assertEquals("Classic date should match the converted Date", classicDate, anotherDate);

        // *****************************************************
        // Think about why all conversions and inter-ops are
        // built into Date and not the java.time API.
        // *****************************************************
    }

}
