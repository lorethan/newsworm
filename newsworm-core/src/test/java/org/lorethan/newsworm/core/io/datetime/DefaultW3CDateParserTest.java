package org.lorethan.newsworm.core.io.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import uk.co.it.modular.hamcrest.date.DateMatchers;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class DefaultW3CDateParserTest
{
    private final DefaultW3CDateParser parser = new DefaultW3CDateParser();

    @Test
    public void checkCanParseWithMillisWithTimeZone()
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);

        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 1);
        cal.set(Calendar.MILLISECOND, 123);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+1"));

        final Date date = parser.parseW3CDate("2013-07-30T12:15:01.123+01:00");

        MatcherAssert.assertThat(date, DateMatchers.sameInstant(cal.getTimeInMillis()));
    }

    @Test
    public void checkCanParseWithMillisWithoutTimeZone()
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);

        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 1);
        cal.set(Calendar.MILLISECOND, 123);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        final Date date = parser.parseW3CDate("2013-07-30T12:15:01.123Z");

        MatcherAssert.assertThat(date, DateMatchers.sameInstant(cal.getTimeInMillis()));
    }

    @Test
    public void checkCanParseWithSecsWithTimeZone()
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);

        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 1);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+1"));

        final Date date = parser.parseW3CDate("2013-07-30T12:15:01+01:00");

        MatcherAssert.assertThat(date, DateMatchers.sameInstant(cal.getTimeInMillis()));
    }

    @Test
    public void checkCanParseWithSecsWithoutTimeZone()
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);

        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 1);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        final Date date = parser.parseW3CDate("2013-07-30T12:15:01Z");

        MatcherAssert.assertThat(date, DateMatchers.sameInstant(cal.getTimeInMillis()));
    }

    @Test
    public void checkCanParseWithMinutesWithTimeZone()
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);

        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT+1"));

        final Date date = parser.parseW3CDate("2013-07-30T12:15+01:00");

        MatcherAssert.assertThat(date, DateMatchers.sameInstant(cal.getTimeInMillis()));
    }

    @Test
    public void checkCanParseWithMinutesWithoutTimeZone()
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);

        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 15);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        final Date date = parser.parseW3CDate("2013-07-30T12:15Z");

        MatcherAssert.assertThat(date, DateMatchers.sameInstant(cal.getTimeInMillis()));
    }

    @Test
    public void checkCanParseWithoutTime()
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);

        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 30);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        final Date date = parser.parseW3CDate("2013-07-30");

        MatcherAssert.assertThat(date, DateMatchers.sameInstant(cal.getTimeInMillis()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkFailsWithoutTimeZone()
    {
        parser.parseW3CDate("2013-07-30T12:15");
    }
}
