package org.lorethan.newsworm.core.io.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DefaultW3CDateParser implements W3CDateParser
{
    private static final String[] STRICT_W3C_DATE_TIME_FORMATS = {
            "yyyy-MM-dd'T'HH:mm:ss.SSSz",
            "yyyy-MM-dd'T'HH:mm:ssz",
            "yyyy-MM-dd'T'HH:mmz"
    };

    @Override
    public Date parseW3CDate(final String date)
    {
        // fix time zone issues:
        String dateToUse = date;

        int timeIndex = dateToUse.indexOf("T");
        if (timeIndex > -1)
        {
            if (dateToUse.endsWith("Z")) {
                dateToUse = dateToUse.substring(0, dateToUse.length() - 1) + "+00:00";
            }

            int timeZoneIndex = dateToUse.indexOf("+", timeIndex);
            if (timeZoneIndex == -1)
            {
                timeZoneIndex = dateToUse.indexOf("-", timeIndex);
            }

            if (timeZoneIndex > -1)
            {
                final String pre = dateToUse.substring(0, timeZoneIndex);
                final String post = dateToUse.substring(timeZoneIndex);

                dateToUse = pre + "GMT" + post;
            }
        }
        else {
            dateToUse += "T00:00GMT";
        }

        for (final String format : STRICT_W3C_DATE_TIME_FORMATS)
        {
            final DateFormat dateFormat = new SimpleDateFormat(format, Locale.US);

            try
            {
                return dateFormat.parse(dateToUse);
            }
            catch (ParseException e)
            {
                // ignore
            }
        }

        throw new IllegalArgumentException("Invalid date: " + date);
    }
}
