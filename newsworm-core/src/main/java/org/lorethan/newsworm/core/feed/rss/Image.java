package org.lorethan.newsworm.core.feed.rss;

public class Image
{
    private String link;
    private String title;
    private String url;
    private String description;
    private Integer height;
    private Integer width;

    public String getLink()
    {
        return link;
    }

    public void setLink(final String link)
    {
        this.link = link;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(final String url)
    {
        this.url = url;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public Integer getHeight()
    {
        return height;
    }

    public void setHeight(final Integer height)
    {
        this.height = height;
    }

    public Integer getWidth()
    {
        return width;
    }

    public void setWidth(final Integer width)
    {
        this.width = width;
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

        if (description != null ? !description.equals(image.description) : image.description != null)
        {
            return false;
        }
        if (height != null ? !height.equals(image.height) : image.height != null)
        {
            return false;
        }
        if (link != null ? !link.equals(image.link) : image.link != null)
        {
            return false;
        }
        if (title != null ? !title.equals(image.title) : image.title != null)
        {
            return false;
        }
        if (url != null ? !url.equals(image.url) : image.url != null)
        {
            return false;
        }
        if (width != null ? !width.equals(image.width) : image.width != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (width != null ? width.hashCode() : 0);
        return result;
    }
}
