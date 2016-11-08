package com.store.data.generator.utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Random;

public class DateSelector
{
    /**
     * If range is larger than 10 years this might overflow
     */
    public DateTime getDate()
    {
        DateTime result = startRange.minusYears(5);

        while(result.isBefore(startRange) || result.isAfter(endRange)
                || result.getHourOfDay() < 9 || result.getHourOfDay() > 17
                || result.getDayOfWeek() == 6 || result.getDayOfWeek() == 7)
        {
            long millisDiff = endRange.getMillis() - startRange.getMillis();
            int secondsDiff = (int) (millisDiff/1000);

            result = startRange.plusSeconds(random.nextInt(secondsDiff));
        }

        return result;
    }

    public DateSelector(final DateTime startRange, final DateTime endRange, final Random random)
    {
        this.startRange = startRange;
        this.endRange = endRange;
        this.random = random;
    }

    private DateTime startRange;
    private DateTime endRange;
    private Random random;
}
