package org.lorethan.newsworm.core.feed.rss;

public class Hour
{
    private final int value;

    public Hour(final int value)
    {
        if (value < 0 || value > 23)
        {
            throw new IllegalArgumentException("Illegal value. Must be 0 <= value <= 23");
        }
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Hour))
        {
            return false;
        }

        final Hour hour = (Hour) o;

        return value == hour.value;

    }

    @Override
    public int hashCode()
    {
        return value;
    }
}
