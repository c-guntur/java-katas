package none.cvg.datetime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The tests in this class aim to show `java.time.Clock` usage.
 *
 * @see Clock
 */
@DisplayNameGeneration(DateTimeKataDisplayNames.class)
@DisplayName("Clock API")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution2Clocks {

    @Test
    @Tag("PASSING")
    @Order(1)
    public void useSystemClock() {

        // Get current ZoneOffset
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZoneOffset offset = offsetDateTime.getOffset();

        // DONE: Create a system clock
        //  Given a date of 2005-05-05 and a time on 05:05:05 AM in GMT -5,
        //  fix it to display in GMT +1. Show the same Instant in a different zone.
        //  Check: java.time.Clock.withZoneSameInstant(java.time.ZoneId)
        //-----------------------------------------
        Clock systemClock = Clock.systemDefaultZone();

        LocalDateTime localDateTimeWithoutClock = LocalDateTime.now();

        // DONE: Use a system clock instead of the LocalDateTime.of(...)
        //  Check: LocalDateTime.now(java.time.Clock)
        //-----------------------------------------
        LocalDateTime localDateTimeWithClock = LocalDateTime.now(systemClock);

        long secondsWithoutClock = localDateTimeWithoutClock.toEpochSecond(offset);
        long secondsWithClock = localDateTimeWithClock.toEpochSecond(offset);

        // Since the times are created sequentially, it may be possible that a second may
        // have elapsed/ticked since the first date was generated. Hence we check for the
        // difference being a second or less.
        assertTrue(Math.abs(secondsWithClock - secondsWithoutClock) <= 1);
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void useFixedClock() {

        // Get current ZoneOffset
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZoneOffset offset = offsetDateTime.getOffset();

        Instant utcHALBirthday = Instant.parse("1999-01-12T00:00:00.001-08:00");

        // DONE: Replace SystemClock with a FixedClock to always generate the same date and time
        //  Check: Clock.fixed(java.time.Instant, java.time.ZoneId)
        //  Check: ZoneId.of(String) which can accept "UTC" as a value
        Clock utcClockForHAL = Clock.fixed(utcHALBirthday, ZoneId.of("UTC"));

        // DONE: Replace the SystemClock with the FixedClock instance
        LocalDate birthDateForHAL = LocalDate.now(utcClockForHAL);

        // DONE: Replace the SystemClock with the FixedClock instance
        LocalDateTime birthDateAndTimeForHAL = LocalDateTime.now(utcClockForHAL);

        assertEquals(1999,
                birthDateForHAL.getYear(),
                "HAL birth year should be 1999");

        assertEquals(8,
                birthDateAndTimeForHAL.getLong(ChronoField.HOUR_OF_DAY),
                "HAL was born 8 AM UTC");

        assertEquals(1,
                birthDateAndTimeForHAL.getLong(ChronoField.MILLI_OF_SECOND),
                "HAL was born 1 millisecond past midnight, Pacific Time");
    }

    @Test
    @Tag("PASSING")
    @Order(3)
    public void useOffsetClock() {

        // Get current ZoneOffset
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZoneOffset offset = offsetDateTime.getOffset();

        // Create a Duration of 1 hour. More on this in another Kata example.
        Duration oneHour = Duration.ofHours(1L);

        Instant utcHALBirthday = Instant.parse("1999-01-12T00:00:00.001-08:00");
        Clock utcClock = Clock.fixed(utcHALBirthday, ZoneId.of("UTC"));

        // DONE: Replace SystemClock with an OffsetClock, offset by a Duration of 1 HOUR.
        //  Check: Clock.offset(java.time.Clock, java.time.Duration)
        Clock plusOneHourClock = Clock.offset(utcClock, oneHour);

        // DONE: Use the utcClock instance
        LocalTime utcTime = LocalTime.now(utcClock);

        // DONE: Use the plusOneDayClock instance
        LocalTime plusOneHourTime = LocalTime.now(plusOneHourClock);

        assertEquals(1,
                plusOneHourTime.getHour() - utcTime.getHour(),
                "There should be an hour difference in offset");
    }

    @Test
    @Tag("PASSING")
    @Order(4)
    public void useTickClock() {

        // *****************************************************
        // Think of a tick as being similar to how hands on an
        // analog watch move. If a watch does not have a seconds
        // hand, the only time there is a movement is when 60
        // seconds complete (which may then move the minute and
        // possibly the hour hand).
        // *****************************************************

        // Get current ZoneOffset
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        ZoneOffset offset = offsetDateTime.getOffset();

        // Create a Duration of 30 seconds. More on this in another Kata example.
        Duration thirtySeconds = Duration.ofSeconds(30L);

        // No tick
        Instant utcInstant1 = Instant.parse("1999-01-12T00:00:00.000-08:00");
        Clock utcClock1 = Clock.fixed(utcInstant1, ZoneId.of("UTC"));
        // DONE: Replace the FixedClock with a TickClock.
        //  Check: Clock.tick(java.time.Clock, java.time.Duration)
        Clock plusThirtySecondTickClock1 = Clock.tick(utcClock1, thirtySeconds);


        // No tick
        Instant utcInstant2 = Instant.parse("1999-01-12T00:00:10.000-08:00");
        Clock utcClock2 = Clock.fixed(utcInstant2, ZoneId.of("UTC"));
        // DONE: Replace the FixedClock with a TickClock.
        //  Check: Clock.tick(java.time.Clock, java.time.Duration)
        Clock plusThirtySecondTickClock2 = Clock.tick(utcClock2, thirtySeconds);


        // No tick
        Instant utcInstant3 = Instant.parse("1999-01-12T00:00:20.000-08:00");
        Clock utcClock3 = Clock.fixed(utcInstant3, ZoneId.of("UTC"));
        // DONE: Replace the FixedClock with a TickClock.
        //  Check: Clock.tick(java.time.Clock, java.time.Duration)
        Clock plusThirtySecondTickClock3 = Clock.tick(utcClock3, thirtySeconds);


        // Should have a tick
        Instant utcInstant4 = Instant.parse("1999-01-12T00:00:30.000-08:00");
        Clock utcClock4 = Clock.fixed(utcInstant4, ZoneId.of("UTC"));
        // DONE: Replace the FixedClock with a TickClock.
        //  Check: Clock.tick(java.time.Clock, java.time.Duration)
        Clock plusThirtySecondTickClock4 = Clock.tick(utcClock4, thirtySeconds);


        // No tick
        assertTrue(utcClock1.instant().equals(plusThirtySecondTickClock1.instant()),
                "The instant is less than the tick time of 30 seconds");

        // No tick
        assertTrue(utcClock1.instant().equals(plusThirtySecondTickClock2.instant()),
                "The instant is less than the tick time of 30 seconds");

        // No tick
        assertTrue(utcClock1.instant().equals(plusThirtySecondTickClock3.instant()),
                "The instant is less than the tick time of 30 seconds");

        // Should have a tick
        assertFalse(utcClock1.instant().equals(plusThirtySecondTickClock4.instant()),
                "The instant is not less than the tick time of 30 seconds");

    }

}
