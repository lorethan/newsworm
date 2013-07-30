package org.lorethan.newsworm.atom.feed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entry
{
    private String id;
    private Content title;
    private Content summary;
    private Content content;
    private Date updated;
    private Date published;
    private String rights;
    private Feed source;
    private List<Person> authors = new ArrayList<Person>();
    private List<Category> categories = new ArrayList<Category>();
    private List<Person> contributors = new ArrayList<Person>();
    private List<Link> links = new ArrayList<Link>();

    public String getId()
    {
        return id;
    }

    public void setId(final String id)
    {
        this.id = id;
    }

    public Content getTitle()
    {
        return title;
    }

    public void setTitle(final Content title)
    {
        this.title = title;
    }

    public Content getSummary()
    {
        return summary;
    }

    public void setSummary(final Content summary)
    {
        this.summary = summary;
    }

    public Content getContent()
    {
        return content;
    }

    public void setContent(final Content content)
    {
        this.content = content;
    }

    public Date getUpdated()
    {
        return updated;
    }

    public void setUpdated(final Date updated)
    {
        this.updated = updated;
    }

    public Date getPublished()
    {
        return published;
    }

    public void setPublished(final Date published)
    {
        this.published = published;
    }

    public String getRights()
    {
        return rights;
    }

    public void setRights(final String rights)
    {
        this.rights = rights;
    }

    public Feed getSource()
    {
        return source;
    }

    public void setSource(final Feed source)
    {
        this.source = source;
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

}
