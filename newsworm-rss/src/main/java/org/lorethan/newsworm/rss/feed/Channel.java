package org.lorethan.newsworm.rss.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lorethan.newsworm.core.feed.AbstractGenericFeed;
import org.lorethan.newsworm.core.feed.FeedType;

public class Channel extends AbstractGenericFeed
{
    private String title;
    private String description;
    private String link;
    private String language;
    private Date lastBuildDate;
    private Date pubDate;
    private String copyright;
    private String docs;
    private String generator;
    private Cloud cloud;
    private String managingEditor;
    private String rating;
    private Set<Day> skipDays = new HashSet<Day>();
    private Set<Hour> skipHours = new HashSet<Hour>();
    private TextInput textInput;
    private Integer ttl;
    private String webMaster;
    private Image image;
    private Set<String> categories = new HashSet<String>();
    private final List<Item> items = new ArrayList<Item>();

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

    public Date getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(final Date pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getCopyright()
    {
        return copyright;
    }

    public void setCopyright(final String copyright)
    {
        this.copyright = copyright;
    }

    public String getDocs()
    {
        return docs;
    }

    public void setDocs(final String docs)
    {
        this.docs = docs;
    }

    public Cloud getCloud()
    {
        return cloud;
    }

    public void setCloud(final Cloud cloud)
    {
        this.cloud = cloud;
    }

    public String getManagingEditor()
    {
        return managingEditor;
    }

    public void setManagingEditor(final String managingEditor)
    {
        this.managingEditor = managingEditor;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(final String rating)
    {
        this.rating = rating;
    }

    public Iterable<Day> getSkipDays()
    {
        return skipDays;
    }

    public void addSkipDay(final Day skipDay)
    {
        skipDays.add(skipDay);
    }

    public Iterable<Hour> getSkipHours()
    {
        return skipHours;
    }

    public void addSkipHour(final Hour skipHour)
    {
        skipHours.add(skipHour);
    }

    public TextInput getTextInput()
    {
        return textInput;
    }

    public void setTextInput(final TextInput textInput)
    {
        this.textInput = textInput;
    }

    public Integer getTtl()
    {
        return ttl;
    }

    public void setTtl(final Integer ttl)
    {
        this.ttl = ttl;
    }

    public String getWebMaster()
    {
        return webMaster;
    }

    public void setWebMaster(final String webMaster)
    {
        this.webMaster = webMaster;
    }

    public Date getLastBuildDate()
    {
        return lastBuildDate;
    }

    public void setLastBuildDate(final Date lastBuildDate)
    {
        this.lastBuildDate = lastBuildDate;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(final Image image)
    {
        this.image = image;
    }

    public Iterable<String> getCategories()
    {
        return categories;
    }

    public void addCategory(final String category)
    {
        categories.add(category);
    }

    public Iterable<Item> getItems()
    {
        return items;
    }

    public void addItem(final Item item)
    {
        items.add(item);
    }
}
