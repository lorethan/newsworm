package org.lorethan.newsworm.atom.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.lorethan.newsworm.core.feed.AbstractGenericFeed;
import org.lorethan.newsworm.core.feed.FeedType;

public class Feed extends AbstractGenericFeed
{
    private String id;
    private Date updated;
    private Content title;
    private Content subtitle;
    private String rights;
    private Generator generator;
    private Image icon;
    private Image logo;
    private List<Person> authors = new ArrayList<Person>();
    private List<Person> contributors = new ArrayList<Person>();
    private List<Category> categories = new ArrayList<Category>();
    private List<Link> links = new ArrayList<Link>();
    private List<Entry> entries = new ArrayList<Entry>();

    public Feed(final String encoding)
    {
        super(FeedType.ATOM_1_0, encoding);
    }

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(final Date updated)
    {
        this.updated = updated;
    }

    public Content getTitle()
    {
        return title;
    }

    public void setTitle(final Content title)
    {
        this.title = title;
    }

    public Content getSubtitle()
    {
        return subtitle;
    }

    public void setSubtitle(final Content subtitle)
    {
        this.subtitle = subtitle;
    }

    public String getRights()
    {
        return rights;
    }

    public void setRights(final String rights)
    {
        this.rights = rights;
    }

    public Generator getGenerator()
    {
        return generator;
    }

    public void setGenerator(final Generator generator)
    {
        this.generator = generator;
    }

    public Image getIcon()
    {
        return icon;
    }

    public void setIcon(final Image icon)
    {
        this.icon = icon;
    }

    public Image getLogo()
    {
        return logo;
    }

    public void setLogo(final Image logo)
    {
        this.logo = logo;
    }

    public Iterable<Person> getAuthors()
    {
        return authors;
    }

    public void addAuthor(final Person author)
    {
        this.authors.add(author);
    }

    public Iterable<Person> getContributors()
    {
        return contributors;
    }

    public void addContributor(final Person contributor)
    {
        this.contributors.add(contributor);
    }

    public Iterable<Category> getCategories()
    {
        return this.categories;
    }

    public void addCategory(final Category category)
    {
        this.categories.add(category);
    }

    public Iterable<Link> getLinks()
    {
        return links;
    }

    public void addLink(final Link link)
    {
        this.links.add(link);
    }

    public Iterable<Entry> getEntries()
    {
        return entries;
    }

    public void addEntry(final Entry entry)
    {
        this.entries.add(entry);
    }
}
