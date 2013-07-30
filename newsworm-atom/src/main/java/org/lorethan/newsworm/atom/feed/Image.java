package org.lorethan.newsworm.atom.feed;

public class Image
{
    private String uri;

    public String getUri()
    {
        return uri;
    }

    public void setUri(final String uri)
    {
        this.uri = uri;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Image))
        {
            return false;
        }

        final Image image = (Image) o;

        return uri.equals(image.uri);

    }

    @Override
    public int hashCode()
    {
        return uri.hashCode();
    }
}
