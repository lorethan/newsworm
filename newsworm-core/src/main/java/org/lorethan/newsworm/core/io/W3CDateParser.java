package org.lorethan.newsworm.core.io;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class W3CDateParser implements DateParser
{
    private static final String W3CDTF = "yyyy-MM-dd'T'HH:mm:ssZ";

    @Override
    public Date parse(final String dateAsString)
    {
        final DateFormat dateFormat = new SimpleDateFormat(W3CDTF, Locale.US);

        try
        {
            return dateFormat.parse(dateAsString);
        }
        catch (ParseException e)
        {
            throw new IllegalArgumentException("Invalid date: " + dateAsString, e);
        }
    }
}
