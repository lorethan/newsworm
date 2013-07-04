package org.lorethan.newsworm.core.extension.dc;

import java.util.Date;

import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.AbstractExtension;

public class DublinCoreExtension extends AbstractExtension
{
    public static final String NAMESPACE_URI = "http://purl.org/dc/elements/1.1/";

    private String contributor;
    private String coverage;
    private String creator;
    private Date date;
    private String description;
    private String format;
    private String identifier;
    private String language;
    private String publisher;
    private String relation;
    private String rights;
    private String source;
    private String subject;
    private String title;
    private String type;

    public DublinCoreExtension()
    {
        super(Namespace.getNamespace(NAMESPACE_URI));
    }

    public String getContributor()
    {
        return contributor;
    }

    public void setContributor(final String contributor)
    {
        this.contributor = contributor;
    }

    public String getCoverage()
    {
        return coverage;
    }

    public void setCoverage(final String coverage)
    {
        this.coverage = coverage;
    }

    public String getCreator()
    {
        return creator;
    }

    public void setCreator(final String creator)
    {
        this.creator = creator;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(final Date date)
    {
        this.date = date;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public String getFormat()
    {
        return format;
    }

    public void setFormat(final String format)
    {
        this.format = format;
    }

    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(final String identifier)
    {
        this.identifier = identifier;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(final String language)
    {
        this.language = language;
    }

    public String getPublisher()
    {
        return publisher;
    }

    public void setPublisher(final String publisher)
    {
        this.publisher = publisher;
    }

    public String getRelation()
    {
        return relation;
    }

    public void setRelation(final String relation)
    {
        this.relation = relation;
    }

    public String getRights()
    {
        return rights;
    }

    public void setRights(final String rights)
    {
        this.rights = rights;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(final String source)
    {
        this.source = source;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(final String subject)
    {
        this.subject = subject;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public String getType()
    {
        return type;
    }

    public void setType(final String type)
    {
        this.type = type;
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof DublinCoreExtension))
        {
            return false;
        }

        final DublinCoreExtension that = (DublinCoreExtension) o;

        if (contributor != null ? !contributor.equals(that.contributor) : that.contributor != null)
        {
            return false;
        }
        if (coverage != null ? !coverage.equals(that.coverage) : that.coverage != null)
        {
            return false;
        }
        if (creator != null ? !creator.equals(that.creator) : that.creator != null)
        {
            return false;
        }
        if (date != null ? !date.equals(that.date) : that.date != null)
        {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null)
        {
            return false;
        }
        if (format != null ? !format.equals(that.format) : that.format != null)
        {
            return false;
        }
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null)
        {
            return false;
        }
        if (language != null ? !language.equals(that.language) : that.language != null)
        {
            return false;
        }
        if (publisher != null ? !publisher.equals(that.publisher) : that.publisher != null)
        {
            return false;
        }
        if (relation != null ? !relation.equals(that.relation) : that.relation != null)
        {
            return false;
        }
        if (rights != null ? !rights.equals(that.rights) : that.rights != null)
        {
            return false;
        }
        if (source != null ? !source.equals(that.source) : that.source != null)
        {
            return false;
        }
        if (subject != null ? !subject.equals(that.subject) : that.subject != null)
        {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null)
        {
            return false;
        }
        if (type != null ? !type.equals(that.type) : that.type != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = contributor != null ? contributor.hashCode() : 0;
        result = 31 * result + (coverage != null ? coverage.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (format != null ? format.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (publisher != null ? publisher.hashCode() : 0);
        result = 31 * result + (relation != null ? relation.hashCode() : 0);
        result = 31 * result + (rights != null ? rights.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
