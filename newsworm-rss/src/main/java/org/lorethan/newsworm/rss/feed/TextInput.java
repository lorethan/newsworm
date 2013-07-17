package org.lorethan.newsworm.rss.feed;

public class TextInput
{
    private String description;
    private String title;
    private String link;
    private String name;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(final String link)
    {
        this.link = link;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof TextInput))
        {
            return false;
        }

        final TextInput textInput = (TextInput) o;

        if (description != null ? !description.equals(textInput.description) : textInput.description != null)
        {
            return false;
        }
        if (link != null ? !link.equals(textInput.link) : textInput.link != null)
        {
            return false;
        }
        if (name != null ? !name.equals(textInput.name) : textInput.name != null)
        {
            return false;
        }
        if (title != null ? !title.equals(textInput.title) : textInput.title != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
