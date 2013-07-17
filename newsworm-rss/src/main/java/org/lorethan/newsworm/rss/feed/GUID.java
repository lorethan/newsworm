package org.lorethan.newsworm.rss.feed;

public class GUID
{
    private boolean isPermaLink = true;
    private String value;

    public boolean isPermaLink()
    {
        return isPermaLink;
    }

    public void setPermaLink(final boolean permaLink)
    {
        isPermaLink = permaLink;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof GUID))
        {
            return false;
        }

        final GUID guid = (GUID) o;

        if (isPermaLink != guid.isPermaLink)
        {
            return false;
        }
        if (value != null ? !value.equals(guid.value) : guid.value != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (isPermaLink ? 1 : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
