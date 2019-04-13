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
import org.junit.jupiter.api.Test;

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
public class Test3PeriodsAndDurationsTest {
    private Clock terminatorOriginalJudgementDay = null;

    @BeforeEach
    public void setup() {

        Instant instant = Instant.parse("1997-08-29T07:14:30Z");
        // We make an assumption for GMT - 5 as the standard time for users of this test.
        terminatorOriginalJudgementDay = Clock.fixed(instant, ZoneId.of("GMT-5"));
    }

    @Test
    public void period() {

        // *****************************************************
        // Try using
        //                   either:
        // the fluent 'Period.ofXXX' and 'Period.with' methods
        // in tandem
        //                       or:
        // use the single 'of' method.
        // *****************************************************

        // Create a time period
        // TODO: Fix Period below to cover 20 years and 10 days.
        fail("Delete this fail() and FIX using Period static utilities below.");
        // Period twentyYearsAndTenDays = null;
        // assertEquals(20, twentyYearsAndTenDays.get(ChronoUnit.YEARS), "The Period should include twenty years");
        // assertEquals(10, twentyYearsAndTenDays.get(ChronoUnit.DAYS), "The Period should include ten days");


        // Add the Period to a LocalDateTime
        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

        fail("Delete this fail() and FIX LocalDateTime to use the created Period.");
        // TODO: Fix LocalDateTime below to add the 20 years and 10 days.
        //-----------------------------------------
        LocalDateTime calculatedTwentyYearsAndTenDaysLater = null; //tOJDateTime + Period.

        LocalDate twentyYearsAndTenDaysLaterDate = LocalDate.of(2017, 9, 8);
        assertEquals(twentyYearsAndTenDaysLaterDate.getYear(), calculatedTwentyYearsAndTenDaysLater.getYear(), "The year should match 2017.");
        assertEquals(twentyYearsAndTenDaysLaterDate.getMonth(), calculatedTwentyYearsAndTenDaysLater.getMonth(), "The month should match September (9th month).");
        assertEquals(twentyYearsAndTenDaysLaterDate.getDayOfMonth(), calculatedTwentyYearsAndTenDaysLater.getDayOfMonth(), "The date should match the 8th.");
    }

    @Test
    public void duration() {

        // *****************************************************
        // Try using
        //                   either:
        // the fluent methods on Duration
        //                       or:
        // use the single 'of' method.
        // *****************************************************

        // Create a time duration
        // TODO: Fix Period below to cover 3 hours and 6 seconds.
        fail("Delete this fail() and FIX using Duration static utilities below.");
        // Duration threeHoursAndSixSeconds = null;
        // assertEquals(3, threeHoursAndSixSeconds.toHours(), "The time duration should include three hours.");
        // assertEquals(10806, threeHoursAndSixSeconds.getSeconds(), "The time duration should include three hours and six seconds in seconds."); //Note: getSeconds gets full time.
        // NOTICE how the 'with'Seconds has a different meaning for Duration?


        // Add the Duration to a LocalDateTime
        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

//        fail("Delete this fail() and FIX LocalDateTime to use the created Duration.");
        // TODO: Fix LocalDateTime below to add the 3 hours and 6 seconds.
        //-----------------------------------------
        LocalDateTime calculatedThreeHoursAndSixSecondsLater = null; //tOJDateTime + Duration.

        //7:14:30 GMT = 2:14:30 in (GMT-5). Adding 3h 6s = 5:14:36 in (GMT-5)
        LocalDateTime threeHoursAndSixSecondsLaterDate = tOJDateTime.withHour(5).withSecond(36);
        assertEquals(threeHoursAndSixSecondsLaterDate.getHour(), calculatedThreeHoursAndSixSecondsLater.getHour(), "The hour should match 5.");
        assertEquals(threeHoursAndSixSecondsLaterDate.getMinute(), calculatedThreeHoursAndSixSecondsLater.getMinute(), "The minute should match 14.");
        assertEquals(threeHoursAndSixSecondsLaterDate.getSecond(), calculatedThreeHoursAndSixSecondsLater.getSecond(), "The second should match 36.");
    }
}
