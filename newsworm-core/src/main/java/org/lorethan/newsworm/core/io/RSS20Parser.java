package org.lorethan.newsworm.core.io;

import java.util.Date;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.core.feed.FeedType;
import org.lorethan.newsworm.core.feed.rss.Channel;
import org.lorethan.newsworm.core.feed.rss.Enclosure;
import org.lorethan.newsworm.core.feed.rss.GUID;
import org.lorethan.newsworm.core.feed.rss.Item;

public class RSS20Parser extends AbstractFeedParser<Channel>
{
    private final static String RSS_NAMESPACE_URI = "";
    private final DateParser dateParser = new RFC822DateParser();

    public RSS20Parser()
    {
        super(FeedType.RSS_2_0, Namespace.getNamespace(RSS_NAMESPACE_URI));
    }

    @Override
    public Channel parse(final Document document, final String encoding)
    {
        final Channel channel = new Channel(getFeedType(), encoding);

        final Element rootElement = document.getRootElement();

        //final List<Namespace> additionalNamespaces = rootElement.getAdditionalNamespaces();

        final Element channelElement = rootElement.getChild("channel", getNamespace());

        parseChannel(channelElement, channel);

        return channel;
    }

    @Override
    public boolean canParse(final Document document, final String encoding)
    {
        final Element rssRoot = document.getRootElement();
        boolean ok = rssRoot.getName().equals("rss");

        if (ok)
        {
            ok = false;
            final Attribute version = rssRoot.getAttribute("version");
            if (version != null)
            {
                ok = version.getValue().startsWith("2.0");
            }
        }

        return ok;
    }

    protected void parseChannel(final Element channelElement, final Channel channel)
    {
        for (final Element childElement : channelElement.getChildren())
        {
            final boolean namespaceOk = getNamespace().equals(childElement.getNamespace());

            if (namespaceOk && "title".equalsIgnoreCase(childElement.getName()))
            {
                final String title = childElement.getTextTrim();
                channel.setTitle(title);
            }
            else if (namespaceOk && "description".equalsIgnoreCase(childElement.getName()))
            {
                final String description = childElement.getTextTrim();
                channel.setDescription(description);
            }
            else if (namespaceOk && "language".equalsIgnoreCase(childElement.getName()))
            {
                final String language = childElement.getTextTrim();
                channel.setLanguage(language);
            }
            else if (namespaceOk && "link".equalsIgnoreCase(childElement.getName()))
            {
                final String link = childElement.getTextTrim();
                channel.setLink(link);
            }
            else if (namespaceOk && "generator".equalsIgnoreCase(childElement.getName()))
            {
                final String generator = childElement.getTextTrim();
                channel.setGenerator(generator);
            }
            else if (namespaceOk && "lastBuildDate".equalsIgnoreCase(childElement.getName()))
            {
                final String lastBuildDateAsString = childElement.getTextTrim();
                final Date lastBuildDate = dateParser.parse(lastBuildDateAsString);
                channel.setLastBuildDate(lastBuildDate);
            }
            else if (namespaceOk && "item".equalsIgnoreCase(childElement.getName()))
            {
                final Item item = parseItem(childElement);

                channel.addItem(item);
            }
            else
            {
                resolveExtensions(childElement, channel);
            }
        }

    }

    private Item parseItem(final Element itemElement)
    {
        final Item item = new Item();

        for (final Element childElement : itemElement.getChildren())
        {
            final boolean namespaceOk = getNamespace().equals(childElement.getNamespace());

            if (namespaceOk && "title".equalsIgnoreCase(childElement.getName()))
            {
                final String title = childElement.getTextTrim();
                item.setTitle(title);
            }
            else if (namespaceOk && "description".equalsIgnoreCase(childElement.getName()))
            {
                final String description = childElement.getTextTrim();
                item.setDescription(description);
            }
            else if (namespaceOk && "link".equalsIgnoreCase(childElement.getName()))
            {
                final String link = childElement.getTextTrim();
                item.setLink(link);
            }
            else if (namespaceOk && "category".equalsIgnoreCase(childElement.getName()))
            {
                final String category = childElement.getTextTrim();
                item.addCategory(category);
            }
            else if (namespaceOk && "source".equalsIgnoreCase(childElement.getName()))
            {
                final String source = childElement.getTextTrim();
                item.setSource(source);
            }
            else if (namespaceOk && "comments".equalsIgnoreCase(childElement.getName()))
            {
                final String comments = childElement.getTextTrim();
                item.setComments(comments);
            }
            else if (namespaceOk && "author".equalsIgnoreCase(childElement.getName()))
            {
                final String author = childElement.getTextTrim();
                item.setAuthor(author);
            }
            else if (namespaceOk && "pubDate".equalsIgnoreCase(childElement.getName()))
            {
                final String pubDateAsString = childElement.getTextTrim();
                final Date pubDate = dateParser.parse(pubDateAsString);
                item.setPubDate(pubDate);
            }
            else if (namespaceOk && "guid".equalsIgnoreCase(childElement.getName()))
            {
                final GUID guid = new GUID();

                guid.setValue(childElement.getTextTrim());
                final Attribute isPermaLinkAttribute = childElement.getAttribute("isPermaLink");
                if (isPermaLinkAttribute != null)
                {
                    try
                    {
                        guid.setPermaLink(isPermaLinkAttribute.getBooleanValue());
                    }
                    catch (DataConversionException e)
                    {
                        // we silently ignore this, effectively using default value true
                    }
                }

                item.setGuid(guid);
            }
            else if (namespaceOk && "enclosure".equalsIgnoreCase(childElement.getName()))
            {
                final Enclosure enclosure = parseEnclosure(childElement);

                item.setEnclosure(enclosure);
            }
            else
            {
                resolveExtensions(childElement, item);
            }
        }

        return item;
    }

    private Enclosure parseEnclosure(final Element enclosureElement)
    {
        final Enclosure enclosure = new Enclosure();

        final Attribute urlAttribute = enclosureElement.getAttribute("url");
        if (urlAttribute != null)
        {
            enclosure.setUrl(urlAttribute.getValue());
        }

        final Attribute typeAttribute = enclosureElement.getAttribute("type");
        if (typeAttribute != null)
        {
            enclosure.setType(typeAttribute.getValue());
        }

        final Attribute lengthAttribute = enclosureElement.getAttribute("length");
        if (lengthAttribute != null)
        {
            try
            {
                enclosure.setLength(lengthAttribute.getLongValue());
            }
            catch (DataConversionException e)
            {
                // silently ignore, effectively leaving length unset (null)
            }
        }

        return enclosure;
    }

}
