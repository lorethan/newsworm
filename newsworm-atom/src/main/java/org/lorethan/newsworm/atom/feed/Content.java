package org.lorethan.newsworm.atom.feed;

public class Content
{
    private String type;
    private String value;
    private String src;

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(final String value)
    {
        this.value = value;
    }

    public String getSrc()
    {
        return src;
    }

    public void setSrc(final String src)
    {
        this.src = src;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Content))
        {
            return false;
        }

        final Content content = (Content) o;

        return !(src != null ? !src.equals(content.src) : content.src != null) && !(type != null ? !type.equals(content.type) : content.type != null) &&
                !(value != null ? !value.equals(content.value) : content.value != null);

    }

    @Override
    public int hashCode()
    {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (src != null ? src.hashCode() : 0);
        return result;
    }
}
