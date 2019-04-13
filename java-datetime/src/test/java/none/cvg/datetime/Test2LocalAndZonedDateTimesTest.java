package none.cvg.datetime;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * Date containers: LocalDate, LocalTime, LocalDateTime and ZonedDateTime tests.
 * <p>
 * Note: We create a Clock instance in setup() used for some of the tests.
 *
 * @see Clock
 * @see LocalDate
 * @see LocalDateTime
 * @see ZonedDateTime
 * @see ZoneOffset
 * @see ZoneId
 * @see Month
 */
public class Test2LocalAndZonedDateTimesTest {

    private Clock terminatorOriginalJudgementDay = null;

    @BeforeEach
    public void setup() {

        Instant instant = Instant.parse("1997-08-29T07:14:30Z");
        // *****************************************************
        // We make an assumption for GMT - 5 as the standard
        // time for users of this test.
        // *****************************************************
        terminatorOriginalJudgementDay = Clock.fixed(instant, ZoneId.of("GMT-5"));
    }

    @Test
    public void simpleAssignmentOfLocalDate1() {

        // *****************************************************
        // Is March = month 2 ( remember old date API? ) or
        // did Java 8 Date Time FINALLY address that and make
        // month numbering consistent with ISO8601 standard?
        // *****************************************************

        // TODO: Fix LocalDate to a date of 2015-03-17. Try using integers for years, months and dates.
        // REPLACE the line below to fix stPatricksDay2015.
        //-----------------------------------------
        // LocalDate stPatricksDay2015 = LocalDate.???; // Fix to take year, month and date
        fail("Delete this fail() and FIX the assertion line below.");
        // assertEquals("LocalDate should match the expected", "2015-03-17", stPatricksDay2015.toString());
    }

    @Test
    public void simpleAssignmentOfLocalDate2() {

        // *****************************************************
        // A tribute to 2001: A Space Odyssey, HAL, the sentient
        // computer, was 'born' on January 12th 1999
        // *****************************************************

        // TODO: Fix date below to HAL's birthday. Use Month enums.
        // REPLACE the now() with HAL's birthday
        //-----------------------------------------
        // LocalDate halsBirthday = LocalDate.???; // Fix to take year, Month and date
        fail("Delete this fail() and FIX the assertion line below.");
        // assertEquals(1999, halsBirthday.getYear(),
        //         "LocalDate year should match the expected");
    }

    @Test
    public void recommendedDateBasedTestingForLocalDate() {

        // *****************************************************
        // This test demonstrates the power of a Clock, by
        // allowing creation of a time that represents a
        // specific time as "now". For this test a Fixed Clock
        // instantiated in the setup() is used to generate a
        // "now" of the Original Terminator Judgement day.
        // *****************************************************

        // TODO: Fix date below to refer to the exact date of the Terminator (Original) Judgement Day.
        // REPLACE the now() with the the right value.
        //-----------------------------------------
        // LocalDate tOJDDate = LocalDate.now(???); // FIXME
        fail("Delete this fail() and FIX the assertion line below.");
        // assertEquals(8, tOJDDate.getMonthValue(),
        //         "The Original Terminator Judgement Day was in the 8th month (August)");
    }

    @Test
    public void simpleAssignmentOfLocalTime() {

        // *****************************************************
        // Demonstrate setting just the time to 7:52
        // *****************************************************

        // TODO: Fix LocalTime to a time on 07:52 AM.
        // REPLACE the line below to set the right hour and minute.
        //-----------------------------------------
        // LocalTime sevenFiftyTwoAm = LocalTime.???; //FIXME
        fail("Delete this fail() and FIX the assertion line below.");
        // assertEquals("07:52", sevenFiftyTwoAm.toString(),
        //          "The time should show 07:52");
    }

    @Test
    public void recommendedDateBasedTestingForLocalTime() {

        // TODO: Fix time below to refer to the exact time of the Terminator (Original) Judgement Day.
        // REPLACE the now() with the the right value.
        //-----------------------------------------
        // LocalTime tOJDTime = LocalTime.now(???);
        fail("Delete this fail() and FIX the assertion lines below.");
        // assertEquals(2, tOJDTime.getHour(), "The hour should be at 2 AM");
        // assertEquals(14, tOJDTime.getMinute(), "The minute should be at 14");
    }

    @Test
    public void simpleAssignmentOfLocalDateTime() {

        // TODO: Fix LocalDateTime to a date of 2005-05-05 and a time on 05:05:05 AM.
        // REPLACE the line below to set the right date and time.
        //-----------------------------------------
        // LocalDateTime allDateTimeOhFives = LocalDateTime.???; //FIXME
        fail("Delete this fail() and FIX the assertion lines below.");
        // assertTrue(allDateTimeOhFives.getMonthValue() == 5, "The month should be May (5th Month)");
        // assertEquals(5, allDateTimeOhFives.getMinute(), "The minute should be 5");
        // assertTrue(allDateTimeOhFives.getSecond() == 5, "The second should be 5");
    }

    @Test
    public void recommendedDateBasedTestingForLocalDateTime() {

        // TODO: Fix time below to refer to the exact time of the Terminator (Original) Judgement Day.
        // REPLACE the now() with the the right value.
        //-----------------------------------------
        // LocalDateTime tOJDDateTime = LocalDateTime.now(???);
        fail("Delete this fail() and FIX the assertion lines below.");
        // assertEquals(8, tOJDDateTime.getMonthValue(),
        //         "The Original Terminator Judgement Day was in the 8th month (August)");
        // assertEquals(2, tOJDDateTime.getHour(), "The hour should be at 2 AM");
        // assertEquals(14, tOJDDateTime.getMinute(), "The minute should be at 14");
    }

    @Test
    public void zonedDateTime() {

        // Will not name this test as simple ...
        ZonedDateTime allDateTimeOhFives = ZonedDateTime.of(5, 5, 5, 5, 5, 5, 555, ZoneId.ofOffset("", ZoneOffset.of("-0500")));
        ZoneId gmtPlusOneZoneId = ZoneId.ofOffset("", ZoneOffset.of("+0100"));

        // TODO: Fix ZonedDateTime to a date of 2005-05-05 and a time on 05:05:05 AM in GMT -5 to display in GMT +1.
        // REPLACE the line below to show the same Instant in a different zone. Investigate the 'with' methods.
        //-----------------------------------------
        // ZonedDateTime gmtPlusOneDateTimeAtAllFivesInGmtMinusFive = allDateTimeOhFives.???;
        fail("Delete this fail() and FIX the assertion lines below.");
        // assertEquals(3600, gmtPlusOneDateTimeAtAllFivesInGmtMinusFive.getOffset().getTotalSeconds(), "The offset should be 1h in seconds");
        // assertEquals(11, gmtPlusOneDateTimeAtAllFivesInGmtMinusFive.getHour(), "5 AM in GMT-5 implies 11AM in GMT+1");
    }

    @Test
    public void recommendedDateBasedTestingForZonedDateTime() {

        LocalDateTime tOJDDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);
        ZonedDateTime gmtPlusOneHourTimeForTOJD = ZonedDateTime.ofInstant(tOJDDateTime.toInstant(ZoneOffset.of("+0000")), ZoneId.of("GMT+1"));
        // TODO: Fix the below asserts, so they pass.
        //-----------------------------------------
        fail("Delete this fail() and FIX the assertion lines below.");
        // assertEquals(0, gmtPlusOneHourTimeForTOJD.getMonthValue(), "The expected and actual values need to match");
        // assertEquals(0, gmtPlusOneHourTimeForTOJD.getHour(), "The expected and actual values need to match");
        // assertEquals(0, gmtPlusOneHourTimeForTOJD.getMinute(), "The expected and actual values need to match");
    }

}
