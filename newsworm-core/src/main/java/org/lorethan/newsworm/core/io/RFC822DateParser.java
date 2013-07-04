package org.lorethan.newsworm.core.io;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RFC822DateParser implements DateParser
{
    private static final String[] RFC822_FORMATS = {
            "EEE, dd MMM yy HH:mm:ss z", "EEE, dd MMM yy HH:mm z", "dd MMM yy HH:mm:ss z", "dd MMM yy HH:mm z",
    };

    public Date parse(final String dateAsString)
    {
        Date date = null;

        final String fixedDateAsString = fixUniversalTimeIssue(dateAsString);

        for (final String format : RFC822_FORMATS)
        {
            final DateFormat dateFormat = new SimpleDateFormat(format, Locale.US);
            dateFormat.setLenient(true);
            try
            {
                date = dateFormat.parse(fixedDateAsString);
                break;
            }
            catch (Exception e)
            {
                // ignore
            }
        }

        return date;
    }

    private String fixUniversalTimeIssue(final String dateAsString)
    {
        final String fixedDateAsString;
        if (dateAsString == null || dateAsString.isEmpty())
        {
            fixedDateAsString = dateAsString;
        }
        else
        {
            final int universalTimeIndex = dateAsString.indexOf(" UT");
            if (universalTimeIndex > -1)
            {
                final String preUniversalTime = dateAsString.substring(0, universalTimeIndex);
                final String postUniversalTime = dateAsString.substring(universalTimeIndex + 3);
                fixedDateAsString = preUniversalTime + " GMT" + postUniversalTime;
            }
            else
            {
                fixedDateAsString = dateAsString;
            }
        }

        return fixedDateAsString;
    }
}
