package org.lorethan.newsworm.rss.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lorethan.newsworm.core.extension.AbstractExtensionable;

public class Item extends AbstractExtensionable
{
    private GUID guid;
    private String title;
    private String description;
    private String link;
    private Source source;
    private Date pubDate;
    private String author;
    private String comments;
    private Enclosure enclosure;
    private final List<String> categories = new ArrayList<>();

    public GUID getGuid()
    {
        return guid;
    }

    public void setGuid(final GUID guid)
    {
        this.guid = guid;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(final String link)
    {
        this.link = link;
    }

    public Source getSource()
    {
        return source;
    }

    public void setSource(final Source source)
    {
        this.source = source;
    }

    public Date getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(final Date pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(final String author)
    {
        this.author = author;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(final String comments)
    {
        this.comments = comments;
    }

    public Enclosure getEnclosure()
    {
        return enclosure;
    }

    public void setEnclosure(final Enclosure enclosure)
    {
        this.enclosure = enclosure;
    }

    public Iterable<String> getCategories()
    {
        return categories;
    }

    public void addCategory(final String category)
    {
        categories.add(category);
    }
}
