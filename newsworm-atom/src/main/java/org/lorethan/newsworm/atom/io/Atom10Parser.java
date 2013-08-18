package org.lorethan.newsworm.atom.io;

import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.XMLOutputter;
import org.lorethan.newsworm.atom.feed.Category;
import org.lorethan.newsworm.atom.feed.Content;
import org.lorethan.newsworm.atom.feed.Entry;
import org.lorethan.newsworm.atom.feed.Feed;
import org.lorethan.newsworm.atom.feed.Generator;
import org.lorethan.newsworm.atom.feed.Image;
import org.lorethan.newsworm.atom.feed.Link;
import org.lorethan.newsworm.atom.feed.Person;
import org.lorethan.newsworm.core.feed.FeedType;
import org.lorethan.newsworm.core.io.AbstractFeedParser;
import org.lorethan.newsworm.core.io.datetime.W3CDateParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Atom10Parser extends AbstractFeedParser<Feed>
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Atom10Parser.class);

    private final static String NAMESPACE_URI = "http://www.w3.org/2005/Atom";

    private final W3CDateParser dateParser;

    public Atom10Parser(final W3CDateParser dateParser)
    {
        super(FeedType.ATOM_1_0, Namespace.getNamespace(NAMESPACE_URI));

        this.dateParser = dateParser;
    }

    @Override
    public Feed parse(final Document document, final String encoding)
    {

        final Element feedElement = document.getRootElement();

        return parseFeed(feedElement, encoding);
    }

    @SuppressWarnings("ConstantConditions")
    private Feed parseFeed(final Element feedElement, final String encoding)
    {
        final Feed feed = new Feed(encoding);

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
                feed.setUpdated(dateParser.parseW3CDate(dateAsString));
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
            else if ("generator".equals(child.getName()) && namespaceOk)
            {
                final Generator generator = parseGenerator(child);

                feed.setGenerator(generator);
            }
            else if ("link".equals(child.getName()) && namespaceOk)
            {
                final Link link = parseLink(child);

                feed.addLink(link);
            }
            else if ("category".equals(child.getName()) && namespaceOk)
            {
                final Category category = parseCategory(child);

                feed.addCategory(category);
            }
            else if ("logo".equals(child.getName()) && namespaceOk)
            {
                final Image logo = parseImage(child);

                feed.setLogo(logo);
            }
            else if ("icon".equals(child.getName()) && namespaceOk)
            {
                final Image icon = parseImage(child);

                feed.setIcon(icon);
            }
            else if ("entry".equals(child.getName()) && namespaceOk)
            {
                final Entry entry = parseEntry(child);

                feed.addEntry(entry);
            }
            else
            {
                LOGGER.warn("Unhandled element in feed: {}", child.getQualifiedName());
            }
        }

        return feed;
    }

    private Image parseImage(final Element imageElement)
    {
        final Image image = new Image();

        final String uri = imageElement.getTextTrim();

        image.setUri(uri);

        return image;
    }

    private Category parseCategory(final Element categoryElement)
    {
        final Category category = new Category();

        final List<Attribute> attributes = categoryElement.getAttributes();

        for (final Attribute attribute : attributes)
        {
            if ("term".equals(attribute.getName()))
            {
                final String term = attribute.getValue();

                category.setTerm(term);
            }
            else if ("scheme".equals(attribute.getName()))
            {
                final String scheme = attribute.getValue();

                category.setScheme(scheme);
            }
            else if ("label".equals(attribute.getName()))
            {
                final String label = attribute.getValue();

                category.setLabel(label);
            }
            else
            {
                LOGGER.warn("Unhandled attribute in {}: {}", categoryElement.getName(), attribute.getQualifiedName());
            }
        }

        return category;
    }

    @SuppressWarnings("ConstantConditions")
    private Link parseLink(final Element linkElement)
    {
        final Link link = new Link();

        final List<Attribute> attributes = linkElement.getAttributes();

        for (final Attribute attribute : attributes)
        {
            if ("href".equals(attribute.getName()))
            {
                final String href = attribute.getValue();

                link.setHref(href);
            }
            else if ("rel".equals(attribute.getName()))
            {
                final String rel = attribute.getValue();

                link.setRel(rel);
            }
            else if ("type".equals(attribute.getName()))
            {
                final String type = attribute.getValue();

                link.setType(type);
            }
            else if ("hreflang".equals(attribute.getName()))
            {
                final String hrefLang = attribute.getValue();

                link.setHrefLang(hrefLang);
            }
            else if ("length".equals(attribute.getName()))
            {
                try
                {
                    final long length = Long.parseLong(attribute.getValue());
                    link.setLength(length);
                }
                catch (NumberFormatException e)
                {
                    // ignore
                }
            }
            else
            {
                LOGGER.warn("Unhandled attribute in {}: {}", linkElement.getName(), attribute.getQualifiedName());
            }
        }

        return link;
    }

    @SuppressWarnings("ConstantConditions")
    private Entry parseEntry(final Element entryElement)
    {
        final Entry entry = new Entry();

        final List<Element> children = entryElement.getChildren();

        for (final Element child : children)
        {
            final boolean namespaceOk = getNamespace().equals(child.getNamespace());

            if ("id".equals(child.getName()) && namespaceOk)
            {
                final String id = child.getTextTrim();

                entry.setId(id);
            }
            else if ("title".equals(child.getName()) && namespaceOk)
            {
                final Content title = parseContent(child);
                entry.setTitle(title);
            }
            else if ("summary".equals(child.getName()) && namespaceOk)
            {
                final Content summary = parseContent(child);
                entry.setSummary(summary);
            }
            else if ("content".equals(child.getName()) && namespaceOk)
            {
                final Content content = parseContent(child);
                entry.setContent(content);
            }
            else if ("author".equals(child.getName()) && namespaceOk)
            {
                final Person author = parsePerson(child);

                entry.addAuthor(author);
            }
            else if ("contributor".equals(child.getName()) && namespaceOk)
            {
                final Person contributor = parsePerson(child);

                entry.addContributor(contributor);
            }
            else if ("link".equals(child.getName()) && namespaceOk)
            {
                final Link link = parseLink(child);

                entry.addLink(link);
            }
            else if ("category".equals(child.getName()) && namespaceOk)
            {
                final Category category = parseCategory(child);

                entry.addCategory(category);
            }
            else if ("updated".equals(child.getName()) && namespaceOk)
            {
                final String dateAsString = child.getTextTrim();
                entry.setUpdated(dateParser.parseW3CDate(dateAsString));
            }
            else if ("published".equals(child.getName()) && namespaceOk)
            {
                final String dateAsString = child.getTextTrim();
                entry.setPublished(dateParser.parseW3CDate(dateAsString));
            }
            else
            {
                LOGGER.warn("Unhandled element in {}: {}", entryElement.getQualifiedName(), child.getQualifiedName());
            }

        }

        return entry;
    }

    private Generator parseGenerator(final Element generatorElement)
    {
        final Generator generator = new Generator();

        final List<Attribute> attributes = generatorElement.getAttributes();

        for (final Attribute attribute : attributes)
        {
            if ("uri".equals(attribute.getName()))
            {
                final String uri = attribute.getValue();

                generator.setUri(uri);
            }
            else if ("version".equals(attribute.getName()))
            {
                final String version = attribute.getValue();

                generator.setVersion(version);
            }
            else
            {
                LOGGER.warn("Unhandled attribute in {}: {}", generatorElement.getName(), attribute.getQualifiedName());
            }
        }

        final String value = generatorElement.getTextNormalize();
        generator.setValue(value);

        return generator;
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
        }

        final String value;
        if ("xhtml".equalsIgnoreCase(content.getType()))
        {
            XMLOutputter output = new XMLOutputter();
            List<org.jdom2.Content> contentList = contentElement.getContent();

            for (final org.jdom2.Content domContent : contentList)
            {
                if (domContent instanceof Element)
                {
                    final Element elementContent = (Element) domContent;

                    if (elementContent.getNamespace().equals(getNamespace()))
                    {
                        elementContent.setNamespace(Namespace.NO_NAMESPACE);
                    }
                }
            }
            value = output.outputString(contentList);
        }
        else
        {
            value = contentElement.getTextNormalize();
        }
        content.setValue(value);

        return content;
    }

    @SuppressWarnings("ConstantConditions")
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
