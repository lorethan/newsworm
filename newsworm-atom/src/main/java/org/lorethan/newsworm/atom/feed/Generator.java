package org.lorethan.newsworm.atom.feed;

public class Generator
{
    private String uri;
    private String version;
    private String value;

    public String getUri()
    {
        return uri;
    }

    public void setUri(final String uri)
    {
        this.uri = uri;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(final String version)
    {
        this.version = version;
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
        if (!(o instanceof Generator))
        {
            return false;
        }

        final Generator generator = (Generator) o;

        return !(uri != null ? !uri.equals(generator.uri) : generator.uri != null) && !(value != null ? !value.equals(generator.value) : generator.value != null) &&
                !(version != null ? !version.equals(generator.version) : generator.version != null);

    }

    @Override
    public int hashCode()
    {
        int result = uri != null ? uri.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
