package none.cvg.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * DateTime in Streams.
 *
 * @see TemporalAdjuster
 * @see TemporalAdjusters
 * @see DayOfWeek
 * @see Stream
 * @see IntStream
 * @see Collectors
 */
public class Test5StreamsInDateTimeTest {

    @Test
    public void temporalAdjuster() {

        LocalDate tOJDate = LocalDate.of(1997, 8, 29);

        // TODO: Fix assertions below.
        //-----------------------------------------
        fail("Delete this fail() and FIX the assertion below.");
        assertEquals(tOJDate.minusDays(28), tOJDate.with(TemporalAdjusters.firstDayOfMonth()), "firstDayOfTheMonth");
        assertEquals(tOJDate.plusDays(2), tOJDate.with(TemporalAdjusters.lastDayOfMonth()), "lastDayOfMonth ");
        assertEquals(tOJDate.plusDays(3), tOJDate.with(TemporalAdjusters.firstDayOfNextMonth()), "firstDayOfNextMonth ");
        assertEquals(tOJDate.plusDays(5), tOJDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)), "nextDayOfWeek ");

        // The first day of the next year after the Original Judgement day.
        LocalDate nextYearDate = LocalDate.of(1998, 1, 1);
        // TODO: Fix assertions below.
        //-----------------------------------------
        fail("Delete this fail() and FIX the assertion below.");
        assertEquals(nextYearDate, tOJDate.with(TemporalAdjusters.firstDayOfNextYear()), "firstDayOfNextYear ");
        assertEquals(nextYearDate.minusDays(365), tOJDate.with(TemporalAdjusters.firstDayOfYear()), "firstDayOfCurrentYear from nextYearDate ");
    }

    @Test
    public void streamTemporalAdjustment() {

        TemporalAdjuster temporalAdjuster = temporal -> {
            LocalDate localDate = LocalDate.from(temporal);
            localDate = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
            return temporal.with(localDate);
        };

        Stream<LocalDate> someDates = Stream.of(
                LocalDate.of(1997, 8, 29),
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2015, 1, 4));

        // TODO: Fix the list population below.
        // REPLACE the new ArrayList creation with a stream action on 'someDates'.
        //-----------------------------------------
        List<LocalDate> collectSundays = someDates.map(each -> each.with(temporalAdjuster)).collect(Collectors.toList());

        fail("Delete this fail() and FIX the assertion line below.");
        assertEquals(collectSundays.get(0).toString(), "Sunday on or after 1997-08-29 is 1997-08-31", "1997-08-31");
        assertEquals(collectSundays.get(1).toString(), "Sunday on or after 2015-01-01 is 2015-01-04", "2015-01-04");
        assertEquals(collectSundays.get(2).toString(), "Sunday on or after 1997-01-04 is 2015-01-04", "2015-01-04");
    }

    @Test
    public void twoBusinessDateShippingCalculator() {

        // TODO: Fix the TemporalAdjuster below.
        // Fix the logic. Use the assertions below.
        //-----------------------------------------
        TemporalAdjuster tPlusTwoBusinessDates = temporal -> {
            LocalDate localDate = LocalDate.from(temporal);
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            if (dayOfWeek.getValue() >= 5) {
                localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
            } else {
                localDate = localDate.plusDays(2);
                if (localDate.getDayOfWeek().getValue() > 5) {
                    localDate = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
                }
            }
            localDate = localDate.plusDays(2);
            return temporal.with(localDate);
        };

        LocalDate localDate = LocalDate.of(2015, 11, 18);

        List<LocalDate> sampleShippingDates = new ArrayList<>();

        IntStream.iterate(0, i -> i + 1)
                .limit(10)
                .forEach(i -> sampleShippingDates.add(localDate.plusDays(i)));

        Map<LocalDate, LocalDate> shippingDateMap = sampleShippingDates.stream()
                .collect(Collectors.toMap(each -> each, each -> each.with(tPlusTwoBusinessDates)));

        assertTrue(shippingDateMap
                        .values()
                        .stream()
                        .noneMatch(localDate1 -> localDate1.getDayOfWeek().getValue() > 5),
                "No weekend shipping");

        assertEquals(DayOfWeek.WEDNESDAY,
                shippingDateMap
                        .get(localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)))
                        .getDayOfWeek(),
                "Items purchased on Monday should ship on the following Wednesday");

        assertEquals(DayOfWeek.THURSDAY,
                shippingDateMap
                        .get(localDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY)))
                        .getDayOfWeek(),
                "Items purchased on Tuesday should ship on the following Thursday");

        assertEquals(DayOfWeek.FRIDAY,
                shippingDateMap
                        .get(localDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)))
                        .getDayOfWeek(),
                "Items purchased on Wednesday should ship on the following Friday");

        assertEquals(DayOfWeek.MONDAY,
                shippingDateMap
                        .get(localDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)))
                        .getDayOfWeek(),
                "Items purchased on Thursday should ship on the following Monday");

        assertEquals(DayOfWeek.TUESDAY,
                shippingDateMap
                        .get(localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)))
                        .getDayOfWeek(),
                "Items purchased on Friday should ship on the following Tuesday");

        assertEquals(DayOfWeek.TUESDAY,
                shippingDateMap
                        .get(localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY)))
                        .getDayOfWeek(),
                "Items purchased on Saturday should ship on the following Tuesday");

        assertEquals(DayOfWeek.TUESDAY,
                shippingDateMap
                        .get(localDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)))
                        .getDayOfWeek(),
                "Items purchased on Sunday should ship on the following Tuesday");

    }

}
