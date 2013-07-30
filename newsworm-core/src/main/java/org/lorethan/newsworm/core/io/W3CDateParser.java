package org.lorethan.newsworm.core.io;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class W3CDateParser implements DateParser
{
    private static final String[] W3C_DTFS = {
            "yyyy-MM-dd'T'HH:mm:ss.SSSz",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ssz",
            "yyyy-MM-dd'T'HH:mm:ss'Z'"
    };

    @Override
    public Date parse(final String dateAsString)
    {
        for (final String df : W3C_DTFS)
        {
            final DateFormat dateFormat = new SimpleDateFormat(df, Locale.US);

            try
            {
                return dateFormat.parse(dateAsString);
            }
            catch (ParseException e)
            {
                // ignore
            }
        }

        throw new IllegalArgumentException("Invalid date: " + dateAsString);
    }
}
