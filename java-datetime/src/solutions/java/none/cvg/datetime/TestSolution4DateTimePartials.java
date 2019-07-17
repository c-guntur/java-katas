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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
@DisplayNameGeneration(DateTimeKataDisplayNames.class)
@DisplayName("Date and Time partials such as credit card expiration")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSolution4DateTimePartials {

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
    public void verifyMonth() {

        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

        // TODO: Replace the "null" below get a Month instance.
        //  Check: java.time.LocalDateTime.getMonth()
        Month tOJMonth = tOJDateTime.getMonth();

        assertEquals(Month.AUGUST,
                tOJMonth,
                "The Month enumeration should match August.");
    }

    @Test
    @Tag("PASSING")
    @Order(2)
    public void verifyMonthDay() {

        // Real world: Birthday or anniversary

        // TODO: Replace the MonthDay.now() below get a MonthDay anniversary for the Judegement Day.
        //  Check: java.time.MonthDay.now(java.time.Clock)
        MonthDay anniversaryofJudgementDay = MonthDay.now(terminatorOriginalJudgementDay);

        assertEquals(Month.AUGUST,
                anniversaryofJudgementDay.getMonth(),
                "The month enumeration should match August.");

        assertEquals(8,
                anniversaryofJudgementDay.getMonthValue(),
                "The month value should match 8.");

        assertEquals(29,
                anniversaryofJudgementDay.getDayOfMonth(),
                "The date value should match 29.");
    }

    @Test
    @Tag("PASSING")
    @Order(3)
    public void verifyYear() {

        // Real world: Age or Year of occurrence.

        // TODO: Replace the Year.now() below get a Year for the Judegement Day.
        //  Check: java.time.Year.now(java.time.Clock)
        Year yearOfJudgementDay = Year.now(terminatorOriginalJudgementDay);

        assertEquals(1997,
                yearOfJudgementDay.getValue(),
                "The year value should match 1997.");
    }

    @Test
    @Tag("PASSING")
    @Order(4)
    public void verifyYearMonth() {

        // Real world: CreditCard or Medicine expiration.

        // TODO: Replace the YearMonth.now() below get a YearMonth for the Judegement Day.
        //  Check: java.time.YearMonth.now(java.time.Clock)
        YearMonth yearMonthOfJudgementDay = YearMonth.now(terminatorOriginalJudgementDay);

        assertEquals(Month.AUGUST,
                yearMonthOfJudgementDay.getMonth(),
                "The month enumeration should match August.");

        assertEquals(8,
                yearMonthOfJudgementDay.getMonthValue(),
                "The month value should match 8.");

        assertEquals(1997,
                yearMonthOfJudgementDay.getYear(),
                "The year value should match 1997.");
    }

    @Test
    @Tag("PASSING")
    @Order(5)
    public void verifyDayOfWeek() {

        LocalDateTime tOJDateTime = LocalDateTime.now(terminatorOriginalJudgementDay);

        // TODO: Replace the null below to get the Day Of Week from the tOJDateTime.
        //  Check: java.time.LocalDateTime.getDayOfWeek()
        DayOfWeek dayOfWeek = tOJDateTime.getDayOfWeek();

        assertEquals(DayOfWeek.FRIDAY,
                dayOfWeek,
                "The day of the week enumeration should match Friday.");
    }

}
