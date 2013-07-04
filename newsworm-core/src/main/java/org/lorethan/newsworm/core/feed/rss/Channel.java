package org.lorethan.newsworm.core.feed.rss;

import java.util.Date;

import org.lorethan.newsworm.core.feed.AbstractGenericFeed;
import org.lorethan.newsworm.core.feed.FeedType;

public class Channel extends AbstractGenericFeed
{
    private String title;
    private String description;
    private String language;
    private String link;
    private String generator;
    private Date lastBuildDate;

    public Channel(final FeedType feedType, final String encoding)
    {
        super(feedType, encoding);
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

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(final String language)
    {
        this.language = language;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(final String link)
    {
        this.link = link;
    }

    public String getGenerator()
    {
        return generator;
    }

    public void setGenerator(final String generator)
    {
        this.generator = generator;
    }

    public Date getLastBuildDate()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate(final Date lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }
}
