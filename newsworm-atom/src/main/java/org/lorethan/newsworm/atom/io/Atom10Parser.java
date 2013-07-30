package org.lorethan.newsworm.atom.io;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.atom.feed.Content;
import org.lorethan.newsworm.atom.feed.Feed;
import org.lorethan.newsworm.atom.feed.Person;
import org.lorethan.newsworm.core.feed.FeedType;
import org.lorethan.newsworm.core.io.AbstractFeedParser;
import org.lorethan.newsworm.core.io.DateParser;
import org.lorethan.newsworm.core.io.W3CDateParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Atom10Parser extends AbstractFeedParser<Feed>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Atom10Parser.class);

    private final static String NAMESPACE_URI = "http://www.w3.org/2005/Atom";

    private final static DateParser DATE_PARSER = new W3CDateParser();

    protected Atom10Parser()
    {
        super(FeedType.ATOM_1_0, Namespace.getNamespace(NAMESPACE_URI));
    }

    @Override
    public Feed parse(final Document document, final String encoding)
    {
        final Feed feed = new Feed(encoding);

        final Element feedElement = document.getRootElement();

        final List<Element> children = feedElement.getChildren();

        for (final Element child : children)
        {
            final boolean namespaceOk = getNamespace().equals(child.getNamespace());

            if ("id".equals(child.getName()) && namespaceOk)
            {
                final String id = parseId(child);
                feed.setId(id);
            }
            else if ("title".equals(child.getName()) && namespaceOk)
            {
                final Content title = parseContent(child);
                feed.setTitle(title);
            }
            else if ("subtitle".equals(child.getName()) && namespaceOk)
            {
                final Content subtitle = parseContent(child);
                feed.setSubtitle(subtitle);
            }
            else if ("updated".equals(child.getName()) && namespaceOk)
            {
                final String dateAsString = child.getTextTrim();
                feed.setUpdated(DATE_PARSER.parse(dateAsString));
            }
            else if ("rights".equals(child.getName()) && namespaceOk)
            {
                final String rights = child.getTextTrim();
                feed.setRights(rights);
            }
            else if ("author".equals(child.getName()) && namespaceOk)
            {
                final Person author = parsePerson(child);

                feed.addAuthor(author);
            }
            else if ("contributor".equals(child.getName()) && namespaceOk)
            {
                final Person contributor = parsePerson(child);

                feed.addContributor(contributor);
            }
            else
            {
                LOGGER.warn("Unhandled element in feed: {}", child.getQualifiedName());
            }
        }

        return feed;
    }

    private Content parseContent(final Element contentElement)
    {
        final Content content = new Content();

        final List<Attribute> attributes = contentElement.getAttributes();

        for (final Attribute attribute : attributes)
        {
            if ("type".equals(attribute.getName()))
            {
                final String type = attribute.getValue();

                content.setType(type);
            }
            else if ("src".equals(attribute.getName()))
            {
                final String src = attribute.getValue();

                content.setSrc(src);
            }
            else
            {
                LOGGER.warn("Unhandled attribute in {}: {}", contentElement.getName(), attribute.getQualifiedName());
            }

            final String value = contentElement.getTextNormalize();

            content.setValue(value);
        }

        return content;
    }

    private Person parsePerson(final Element personElement)
    {
        final Person person = new Person();

        final List<Element> children = personElement.getChildren();

        for (final Element child : children)
        {
            final boolean namespaceOk = getNamespace().equals(child.getNamespace());

            if ("name".equals(child.getName()) && namespaceOk)
            {
                final String name = child.getTextTrim();

                person.setName(name);
            }
            else if ("uri".equals(child.getName()) && namespaceOk)
            {
                final String uri = child.getTextTrim();

                person.setUri(uri);

            }
            else if ("email".equals(child.getName()) && namespaceOk)
            {
                final String email = child.getTextTrim();

                person.setEmail(email);

            }
            else
            {
                LOGGER.warn("Unhandled element in person: {}", child.getQualifiedName());
            }
        }

        return person;
    }

    private String parseId(final Element idElement)
    {
        return idElement.getTextTrim();
    }

    @Override
    public boolean canParse(final Document document)
    {
        final Element rootElement = document.getRootElement();

        return rootElement.getName().equals("feed") && getNamespace().equals(rootElement.getNamespace());
    }
}
