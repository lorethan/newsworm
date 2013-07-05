package org.lorethan.newsworm.core.feed.atom;

public class Link
{
    private String rel;
    private String type;
    private String href;

    public Link()
    {
    }

    public Link(final String rel, final String type, final String href)
    {
        this.rel = rel;
        this.type = type;
        this.href = href;
    }

    public String getRel()
    {
        return rel;
    }

    public void setRel(final String rel)
    {
        this.rel = rel;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    public String getHref()
    {
        return href;
    }

    public void setHref(final String href)
    {
        this.href = href;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Link))
        {
            return false;
        }

        final Link link = (Link) o;

        if (href != null ? !href.equals(link.href) : link.href != null)
        {
            return false;
        }
        if (rel != null ? !rel.equals(link.rel) : link.rel != null)
        {
            return false;
        }
        if (type != null ? !type.equals(link.type) : link.type != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = rel != null ? rel.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        return result;
    }
}
