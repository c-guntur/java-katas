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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
@DisplayNameGeneration(DateTimeKataDisplayNames.class)
@DisplayName("Local and Zoned Date Time manipulations")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestKata3LocalAndZonedDateTimes {

    // Using a clock as a standard reference for testing any time
    // (even after any possible timezone changes)
    private Clock terminatorOriginalJudgementDay = null;

    @BeforeEach
    public void setup() {

        Instant instant = Instant.parse("1997-08-29T06:14:30Z");
        // *****************************************************
        // We make an assumption for GMT -4 as the standard
        // time for users of this test. This is because it is
        // widely believed that the Judgement Day was triggered
        // in New York City & Washington DC, both of which share
        // the GMT -4 (Eastern Daylight Time) timezone in August.
        // *****************************************************
        terminatorOriginalJudgementDay = Clock.fixed(instant, ZoneId.of("GMT-4"));
    }

    @Test
    @Tag("TODO")
    @Order(1)
    public void verifyLocalDateUsingIntegers() {

        // *****************************************************
        // Is March = month 2 ( remember old date API? ) or
        // did Java 8 Date Time FINALLY address that and make
        // month numbering consistent with ISO8601 standard?
        // *****************************************************

        // TODO: Replace the LocalDate.now() below to create a LocalDate of 2015-03-17.
        //  Fix LocalDate to a date of 2015-03-17. Try using integers for years, months and dates.
        //  Check : java.time.LocalDate.of(int, int, int)
        LocalDate stPatricksDay2015 = LocalDate.now();

        assertEquals("2015-03-17",
                stPatricksDay2015.toString(),
                "The stPatricksDay2015 toString() should match the expected [2015-03-17]");
    }

    @Test
    @Tag("TODO")
    @Order(2)
    public void verifyLocalDateUsingMonthEnums() {

        // *****************************************************
        // A tribute to 2001: A Space Odyssey.
        // HAL, the sentient computer,
        // was 'born' on January 12th 1999
        // *****************************************************

        // TODO: Replace the LocalDate.now() below to create a LocalDate of 1999-01-12.
        //  Fix LocalDate below to HAL's birthday. Use Month enums.
        //  No longer a confusion about whether January is 0 or 1.
        LocalDate halsBirthday = LocalDate.now();

        assertEquals(1999,
                halsBirthday.getYear(),
                "LocalDate year should match the expected");

        assertEquals(Month.JANUARY,
                halsBirthday.getMonth(),
                "LocalDate month should match the expected");
    }

    @Test
    @Tag("TODO")
    @Order(3)
    public void verifyLocalDateUsingClock() {

        // *****************************************************
        // NOTE: This test demonstrates the power of a Clock, by
        // allowing creation of a time that represents a
        // specific time as "now". For this test a Fixed Clock
        // instantiated in the setup() is used to generate a
        // "now" of the Original Terminator Judgement day.
        // *****************************************************

        // TODO: Replace the LocalDate.now() below to create a Date of the Judgement Day.
        //  Fix the date below to use a clock. Replace then now() with a now(Clock).
        //  The LocalDate should thus read 1997-08-29
        //  Check: java.time.LocalDate.now(java.time.Clock)
        //-----------------------------------------
        LocalDate theOriginalJudgementDDate = LocalDate.now();

        assertEquals(1997,
                theOriginalJudgementDDate.getYear(),
                "The Original Terminator Judgement Day was in 1997");

        assertEquals(Month.AUGUST,
                theOriginalJudgementDDate.getMonth(),
                "The Original Terminator Judgement Day was in August");

        assertEquals(8,
                theOriginalJudgementDDate.getMonthValue(),
                "The Original Terminator Judgement Day was in the 8th month");
    }

    @Test
    @Tag("TODO")
    @Order(4)
    public void verifyLocalTimeUsingIntegers() {

        // *****************************************************
        // Demonstrate setting just the time to 7:52
        // *****************************************************

        // TODO: Replace the LocalTime.now() below to display a LocalTime of 7:52 AM.
        //  Fix LocalTime to 07:52 AM, using LocalTime with integers for hours and minutes.
        //  Check: java.time.LocalTime.of(int, int)
        //-----------------------------------------
        LocalTime sevenFiftyTwoAm = LocalTime.now();

        assertEquals("07:52",
                sevenFiftyTwoAm.toString(),
                "The time should show 07:52");
    }

    @Test
    @Tag("TODO")
    @Order(5)
    public void verifyLocalTimeInUtcUsingClock() {

        // TODO: Replace the LocalTime.now() call below to display 2:14AM UTC
        //  Fix time below to refer to when the Original Judgement Day was triggered.
        //  This test does not include timezones, so all times will be UTC in this test.
        //  There is another test below that will show how this time can be displayed in
        //  Eastern Time (Washington DC / New York City).
        //  Check: java.time.LocalTime.now(java.time.Clock)
        LocalTime theOriginalJudgementDayTime = LocalTime.now();

        assertEquals(2,
                theOriginalJudgementDayTime.getHour(),
                "The hour should be at 2 AM");

        assertEquals(14,
                theOriginalJudgementDayTime.getMinute(),
                "The minute should be at 14");
    }

    @Test
    @Tag("TODO")
    @Order(6)
    public void verifyLocalDateTimeUsingIntegers() {

        // TODO: Replace the LocalDateTime.now() to produce the desired date and time.
        //  Fix LocalDateTime to a date of 2005-05-05 and a time on 05:05:05 AM.
        //  Check: java.time.LocalDateTime.of(int, int, int, int, int, int)
        LocalDateTime allDateTimeOhFives =
                LocalDateTime.now();

        assertTrue(allDateTimeOhFives.getMonthValue() == 5,
                "The month should be May (5th Month)");

        assertEquals(5,
                allDateTimeOhFives.getMinute(),
                "The minute should be 5");

        assertTrue(allDateTimeOhFives.getSecond() == 5,
                "The second should be 5");
    }

    @Test
    @Tag("TODO")
    @Order(7)
    public void verifyLocalDateTimeUsingClock() {

        // TODO: Replace the LocalDateTime.now() to produce the desired date and time.
        //  Fix LocalDateTime to the exact date-time of the Terminator (Original) Judgement Day.
        //  Check: java.time.LocalDateTime.now(java.time.Clock)
        LocalDateTime theOriginalJudgementDayDateTime =
                LocalDateTime.now();

        assertEquals(8,
                theOriginalJudgementDayDateTime.getMonthValue(),
                "The Original Terminator Judgement Day was in the 8th month (August)");

        assertEquals(2,
                theOriginalJudgementDayDateTime.getHour(),
                "The hour should be at 2 AM");

        assertEquals(14,
                theOriginalJudgementDayDateTime.getMinute(),
                "The minute should be at 14");
    }

    @Test
    @Tag("TODO")
    @Order(8)
    public void verifyZonedDateTimeUsingIntegers() {

        ZonedDateTime allDateTimeOhFives =
                ZonedDateTime.of(5,
                        5,
                        5,
                        5,
                        5,
                        5,
                        555,
                        ZoneId.ofOffset("", ZoneOffset.of("-0500")));

        ZoneId gmtPlusOneZoneId = ZoneId.ofOffset("", ZoneOffset.of("+0100"));

        // TODO: Replace ZonedDateTime.now() to get date time in GMT +1 offset.
        //  Given a date of 2005-05-05 and a time on 05:05:05 AM in GMT -5,
        //  fix it to display in GMT +1. Show the same Instant in a different zone.
        //  Check: java.time.ZonedDateTime.withZoneSameInstant(java.time.ZoneId)
        //-----------------------------------------
        ZonedDateTime gmtPlusOneDateTimeAtAllFivesInGmtMinusFive =
                ZonedDateTime.now();

        assertEquals(3600,
                gmtPlusOneDateTimeAtAllFivesInGmtMinusFive.getOffset().getTotalSeconds(),
                "The offset should be 3600 seconds (1h)");

        assertEquals(11,
                gmtPlusOneDateTimeAtAllFivesInGmtMinusFive.getHour(),
                "5 AM in GMT -5 should imply 11AM in GMT +1");
    }

    @Test
    @Tag("TODO")
    @Order(9)
    public void verifyZonedDateTimeUsingClock() {

        // *****************************************************
        // Demonstrate creating a new ZonedDateTime if GMT +1
        // from the clock now(). Convert a LocalDateTime into
        // an Instant, which can subsequently be used to
        // create the new ZonedDateTime
        // *****************************************************

        LocalDateTime theOriginalJudgementDayDateTime =
                LocalDateTime.now(terminatorOriginalJudgementDay);

        Instant tojdInstant = theOriginalJudgementDayDateTime
                .toInstant(ZoneOffset.of("-04:00"));

        // TODO: Replace ZonedDateTime.now() to get date time in GMT +1 offset.
        //  Given a timestamp of 1997-08-29T06:14:30Z,
        //  fix it to display in GMT +1. Show the same Instant in a different zone.
        //  Check: java.time.ZonedDateTime.ofInstant(java.time.Instant, java.time.ZoneId)
        ZonedDateTime gmtPlusOneHourTimeForTOJD =
                ZonedDateTime.now();

        assertEquals(8,
                gmtPlusOneHourTimeForTOJD.getMonthValue(),
                "The expected and actual month values should match");

        assertEquals(7,
                gmtPlusOneHourTimeForTOJD.getHour(),
                "The expected and actual hour values should match");

        assertEquals(Integer.valueOf(14),
                gmtPlusOneHourTimeForTOJD.getMinute(),
                "The expected and actual minute values should match");
    }

    @Test
    @Tag("TODO")
    @Order(10)
    @DisplayName("verify conversion of UTC date time to Indian Standard Time")
    public void verifyConversionOfUTCDateTimeToIndianStandardTime() {

        ZonedDateTime allDateTimeOhFives =
                ZonedDateTime.of(5,
                        5,
                        5,
                        5,
                        5,
                        5,
                        555,
                        ZoneId.ofOffset("", ZoneOffset.UTC));

        ZoneId gmtPlusOneZoneId = ZoneId.ofOffset("", ZoneOffset.of("+0530"));

        // TODO: Replace the ZonedDateTime.now() below to display the below UTC time in GMT +0530
        //  The ZonedDateTime created in GMT. Fix the calls so a ZonedDateTime
        //  can be created with the offset of GMT +0530. Use an ofInstant from a toInstant.
        //  Check: java.time.ZonedDateTime.ofInstant(java.time.Instant, java.time.ZoneId)
        //  Check: java.time.ZonedDateTime.toInstant()
        ZonedDateTime gmtPlusOneHourTimeForAllFives =
                ZonedDateTime.now();



        assertEquals(10,
                gmtPlusOneHourTimeForAllFives.getHour(),
                "The hour should be at 10 AM when Zone Offset is GMT +0530");

        assertEquals(35,
                gmtPlusOneHourTimeForAllFives.getMinute(),
                "The minute should be 35 when Zone Offset is GMT +0530");
    }

}
