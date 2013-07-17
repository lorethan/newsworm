package org.lorethan.newsworm.rss.feed;

public class Enclosure
{
    private String url;
    private String type;
    private Long length;

    public String getUrl()
    {
        return url;
    }

    public void setUrl(final String url)
    {
        this.url = url;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public Long getLength()
    {
        return length;
    }

    public void setLength(final Long length)
    {
        this.length = length;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Enclosure))
        {
            return false;
        }

        final Enclosure enclosure = (Enclosure) o;

        if (length != null ? !length.equals(enclosure.length) : enclosure.length != null)
        {
            return false;
        }
        if (type != null ? !type.equals(enclosure.type) : enclosure.type != null)
        {
            return false;
        }
        if (url != null ? !url.equals(enclosure.url) : enclosure.url != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        return result;
    }
}
