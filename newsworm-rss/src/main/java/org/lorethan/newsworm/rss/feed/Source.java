package org.lorethan.newsworm.rss.feed;

public class Source
{
    private String value;
    private String url;

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(final String url)
    {
        this.url = url;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Source))
        {
            return false;
        }

        final Source source = (Source) o;

        return !(url != null ? !url.equals(source.url) : source.url != null) && !(value != null ? !value.equals(source.value) : source.value != null);

    }

    @Override
    public int hashCode()
    {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
