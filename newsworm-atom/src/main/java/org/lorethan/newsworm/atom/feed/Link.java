package org.lorethan.newsworm.atom.feed;

public class Link
{
    private String rel;
    private String type;
    private String href;
    private String hrefLang;
    private Long length;

    public Link()
    {
    }

    public Link(final String rel, final String type, final String href, final String hrefLang, final Long length)
    {
        this.rel = rel;
        this.type = type;
        this.href = href;
        this.hrefLang = hrefLang;
        this.length = length;
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

    public String getHrefLang()
    {
        return hrefLang;
    }

    public void setHrefLang(final String hrefLang)
    {
        this.hrefLang = hrefLang;
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
        if (!(o instanceof Link))
        {
            return false;
        }

        final Link link = (Link) o;

        return !(length != null ? !length.equals(link.length) : link.length != null) && !(hrefLang != null ? !hrefLang.equals(link.hrefLang) : link.hrefLang != null) &&
                !(href != null ? !href.equals(link.href) : link.href != null) &&
                !(rel != null ? !rel.equals(link.rel) : link.rel != null) && !(type != null ? !type.equals(link.type) : link.type != null);

    }

    @Override
    public int hashCode()
    {
        int result = rel != null ? rel.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (href != null ? href.hashCode() : 0);
        result = 31 * result + (hrefLang != null ? hrefLang.hashCode() : 0);
        result = 31 * result + (length != null ? length.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Link{");
        sb.append("rel='").append(rel).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", href='").append(href).append('\'');
        sb.append(", hrefLang='").append(hrefLang).append('\'');
        sb.append(", length=").append(length);
        sb.append('}');
        return sb.toString();
    }
}
