package none.cvg.datetime;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * DateTime ranges: Period, Duration tests.
 * <p>
 * Note: We create a Clock instance in setup() used for some of the tests.
 *
 * @see Clock
 * @see Period
 * @see Duration
 * @see ChronoUnit
 */
@DisplayNameGeneration(DateTimeKataDisplayNames.class)
@DisplayName("Periods (days, months years) and Durations (hours minutes, seconds")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution3PeriodsAndDurations {

    private Clock terminatorOriginalJudgementDay = null;

    @BeforeEach
    public void setup() {

        Instant instant = Instant.parse("1997-08-29T07:14:30Z");
        // We make an assumption for GMT - 5 as the standard time for users of this test.
        terminatorOriginalJudgementDay = Clock.fixed(instant, ZoneId.of("GMT-5"));
    }

    @Test
    @Tag("PASSING")
    @Order(1)
    public void verifyPeriodCreatedUsingIntegers() {

        // Create a Period instance
        // TODO: Replace the Period.ZERO to a time period of 20 years and 10 days.
        //  Check : java.time.Period.of(int, int, int)
        Period twentyYearsAndTenDays = Period.of(20, 0, 10);

        assertEquals(20,
                twentyYearsAndTenDays.get(ChronoUnit.YEARS),
                "The Period should include twenty years");

        assertEquals(10,
                twentyYearsAndTenDays.get(ChronoUnit.DAYS),
                "The Period should include ten days");


        // Add the Period to a LocalDateTime
        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

        // TODO: Call a method on tOJDateTime to add the newly created Period
        //  Check : java.time.LocalDateTime.plus(java.time.temporal.TemporalAmount)
        LocalDateTime calculatedTwentyYearsAndTenDaysLater =
                tOJDateTime.plus(twentyYearsAndTenDays);

        assertEquals(2017,
                calculatedTwentyYearsAndTenDaysLater.getYear(),
                "The year after 20 years and 10 days should be 2017");

        assertEquals(9,
                calculatedTwentyYearsAndTenDaysLater.getMonthValue(),
                "The month value after 20 years and 10 days should be 9");

        assertEquals(8,
                calculatedTwentyYearsAndTenDaysLater.getDayOfMonth(),
                "The date after 20 years and 10 days should be 8");
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void verifyPeriodCreatedUsingFluentMethods() {

        // Create a Period instance
        // TODO: Replace the Period.ZERO to a time period of 20 years and 10 days.
        //  Check : java.time.Period.ofYears(int).withDays(int)
        Period twentyYearsAndTenDays = Period.ofYears(20).withDays(10);

        assertEquals(20,
                twentyYearsAndTenDays.get(ChronoUnit.YEARS),
                "The Period should include twenty years");

        assertEquals(10,
                twentyYearsAndTenDays.get(ChronoUnit.DAYS),
                "The Period should include ten days");


        // Add the Period to a LocalDateTime
        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

        // TODO: Call a method on tOJDateTime to add the newly created Period
        //  Check : java.time.LocalDateTime.plus(java.time.temporal.TemporalAmount)
        LocalDateTime calculatedTwentyYearsAndTenDaysLater =
                tOJDateTime.plus(twentyYearsAndTenDays);

        //1997-08-29 after 20 years and 10 days => 2017-09=08
        assertEquals(2017,
                calculatedTwentyYearsAndTenDaysLater.getYear(),
                "The year after 20 years and 10 days should be 2017");

        assertEquals(9,
                calculatedTwentyYearsAndTenDaysLater.getMonthValue(),
                "The month value after 20 years and 10 days should be 9");

        assertEquals(8,
                calculatedTwentyYearsAndTenDaysLater.getDayOfMonth(),
                "The date after 20 years and 10 days should be 8");
    }

    @Test
    @Tag("PASSING")
    @Order(3)
    public void verifyDurationCreatedUsingIntegersAndChronoUnits() {

        // Create a Duration instance
        // TODO: Replace the Duration.ZERO to a duration of 3 hours and 6 seconds.
        //  Check: java.time.Duration.of(int, ChronoUnit)
        Duration threeHoursAndSixSeconds = Duration.of(10806, ChronoUnit.SECONDS);

        assertEquals(3,
                threeHoursAndSixSeconds.toHours(),
                "The time duration should include three hours.");

        assertEquals(10806,
                threeHoursAndSixSeconds.getSeconds(),
                "The time duration should include three hours and six seconds in seconds.");


        // Add the Duration to a LocalDateTime
        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

        // TODO: Call a method on tOJDateTime to add the newly created Duration
        //  Check : java.time.LocalDateTime.plus(java.time.temporal.TemporalAmount)
        LocalDateTime calculatedThreeHoursAndSixSecondsLater =
                tOJDateTime.plus(threeHoursAndSixSeconds);

        //7:14:30 GMT = 2:14:30 in (GMT-5). Adding 3h 6s = 5:14:36 in (GMT-5)
        assertEquals(5,
                calculatedThreeHoursAndSixSecondsLater.getHour(),
                "The hour after 3 hours and six seconds should be 5.");

        assertEquals(14,
                calculatedThreeHoursAndSixSecondsLater.getMinute(),
                "The minute after 3 hours and six seconds should be 14.");

        assertEquals(36,
                calculatedThreeHoursAndSixSecondsLater.getSecond(),
                "The second after 3 hours and six seconds should be 36.");
    }

    @Test
    @Tag("PASSING")
    @Order(4)
    public void verifyDurationCreatedUsingFluentMethods() {

        // Create a Duration instance
        // TODO: Replace the Duration.ZERO to a duration of 3 hours and 6 seconds.
        //  NOTE: Check the difference between plusSeconds and withSeconds
        //  Check: java.time.Duration.ofHours(int).plusSeconds(int))
        Duration threeHoursAndSixSeconds = Duration.ofHours(3).plusSeconds(6);

        assertEquals(3,
                threeHoursAndSixSeconds.toHours(),
                "The time duration should include three hours.");

        assertEquals(10806,
                threeHoursAndSixSeconds.getSeconds(),
                "The time duration should include three hours and six seconds in seconds.");
        // NOTE: getSeconds gets full time.


        // Add the Duration to a LocalDateTime
        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

        // TODO: Call a method on tOJDateTime to add the newly created Duration
        //  Check : java.time.LocalDateTime.plus(java.time.temporal.TemporalAmount)
        LocalDateTime calculatedThreeHoursAndSixSecondsLater =
                tOJDateTime.plus(threeHoursAndSixSeconds);

        //7:14:30 GMT = 2:14:30 in (GMT-5). Adding 3h 6s = 5:14:36 in (GMT-5)
        assertEquals(5,
                calculatedThreeHoursAndSixSecondsLater.getHour(),
                "The hour after 3 hours and six seconds should be 5.");

        assertEquals(14,
                calculatedThreeHoursAndSixSecondsLater.getMinute(),
                "The minute after 3 hours and six seconds should be 14.");

        assertEquals(36,
                calculatedThreeHoursAndSixSecondsLater.getSecond(),
                "The second after 3 hours and six seconds should be 36.");
    }
}
