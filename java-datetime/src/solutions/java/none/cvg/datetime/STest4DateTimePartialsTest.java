package none.cvg.datetime;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * DateTime partials: Month, MonthDay, Year, YearMonth and DayOfWeek tests.
 * <p>
 * Note: We create a Clock instance in setup() used for some of the tests.
 *
 * @see Clock
 * @see Month
 * @see MonthDay
 * @see Year
 * @see YearMonth
 * @see DayOfWeek
 */
public class STest4DateTimePartialsTest {
    private Clock terminatorOriginalJudgementDay = null;
    private LocalDateTime tOJDateTime;

    @BeforeEach
    public void setup() {

        Instant instant = Instant.parse("1997-08-29T07:14:30Z");
        // We make an assumption for GMT - 5 as the standard time for users of this test.
        terminatorOriginalJudgementDay = Clock.fixed(instant, ZoneId.of("GMT-5"));
        tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);
    }

    @Test
    public void month() {

        // TODO: Fix assertion.
//        fail("Delete this fail() and FIX assert below.");
        assertEquals(Month.AUGUST, tOJDateTime.getMonth(), "The Month enumeration should match August.");
    }

    @Test
    public void monthDay() {

        MonthDay monthDay = MonthDay.now(terminatorOriginalJudgementDay);
        // TODO: Fix assertions.
//        fail("Delete this fail() and FIX assertions below.");
        assertEquals(Month.AUGUST, monthDay.getMonth(), "The month enumeration should match August.");
        assertEquals(8, monthDay.getMonthValue(), "The month value should match 8.");
        assertEquals(29, monthDay.getDayOfMonth(), "The date value should match 29.");
    }

    @Test
    public void year() {

        Year year = Year.now(terminatorOriginalJudgementDay);
        // TODO: Fix assertion.
//        fail("Delete this fail() and FIX assertion below.");
        assertEquals(1997, year.getValue(), "The year value should match 1997.");
    }

    @Test
    public void yearMonth() {

        YearMonth yearMonth = YearMonth.now(terminatorOriginalJudgementDay);
        // TODO: Fix assertions.
//        fail("Delete this fail() and FIX assertions below.");
        assertEquals(Month.AUGUST, yearMonth.getMonth(), "The month enumeration should match August.");
        assertEquals(8, yearMonth.getMonthValue(), "The month value should match 8.");
        assertEquals(1997, yearMonth.getYear(), "The year value should match 1997.");
    }

    @Test
    public void dayOfWeek() {

        DayOfWeek dayOfWeek = tOJDateTime.getDayOfWeek();
        // TODO: Fix assertions.
//        fail("Delete this fail() and FIX assertion below.");
        assertEquals(DayOfWeek.FRIDAY, dayOfWeek, "The day of the week enumeration should match Friday.");
    }

}
