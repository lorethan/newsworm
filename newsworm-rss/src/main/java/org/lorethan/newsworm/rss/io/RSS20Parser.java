package org.lorethan.newsworm.rss.io;

import java.util.Date;

import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.core.feed.FeedType;
import org.lorethan.newsworm.core.feed.rss.Channel;
import org.lorethan.newsworm.core.feed.rss.Cloud;
import org.lorethan.newsworm.core.feed.rss.Day;
import org.lorethan.newsworm.core.feed.rss.Enclosure;
import org.lorethan.newsworm.core.feed.rss.GUID;
import org.lorethan.newsworm.core.feed.rss.Hour;
import org.lorethan.newsworm.core.feed.rss.Image;
import org.lorethan.newsworm.core.feed.rss.Item;
import org.lorethan.newsworm.core.feed.rss.Source;
import org.lorethan.newsworm.core.feed.rss.TextInput;
import org.lorethan.newsworm.core.io.AbstractFeedParser;
import org.lorethan.newsworm.core.io.DateParser;
import org.lorethan.newsworm.core.io.RFC822DateParser;

public class RSS20Parser extends AbstractFeedParser<Channel>
{
    private final static String RSS_NAMESPACE_URI = "";
    private final DateParser dateParser = new RFC822DateParser();

    public RSS20Parser()
    {
        super(FeedType.RSS_2_0, Namespace.getNamespace(RSS_NAMESPACE_URI));
    }

    @Override
    public boolean canParse(final Document document)
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

    @Override
    public Channel parse(final Document document, final String encoding)
    {
        final Channel channel = new Channel(getFeedType(), encoding);

        final Element rootElement = document.getRootElement();

        final Element channelElement = rootElement.getChild("channel", getNamespace());

        parseChannel(channelElement, channel);

        return channel;
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
            else if (namespaceOk && "cloud".equalsIgnoreCase(childElement.getName()))
            {
                final Cloud cloud = parseCloud(childElement);
                channel.setCloud(cloud);
            }
            else if (namespaceOk && "copyright".equalsIgnoreCase(childElement.getName()))
            {
                final String copyright = childElement.getTextTrim();
                channel.setCopyright(copyright);
            }
            else if (namespaceOk && "docs".equalsIgnoreCase(childElement.getName()))
            {
                final String docs = childElement.getTextTrim();
                channel.setDocs(docs);
            }
            else if (namespaceOk && "managingEditor".equalsIgnoreCase(childElement.getName()))
            {
                final String managingEditor = childElement.getTextTrim();
                channel.setManagingEditor(managingEditor);
            }
            else if (namespaceOk && "rating".equalsIgnoreCase(childElement.getName()))
            {
                final String rating = childElement.getTextTrim();
                channel.setRating(rating);
            }
            else if (namespaceOk && "textInput".equalsIgnoreCase(childElement.getName()))
            {
                final TextInput textInput = parseTextInput(childElement);
                channel.setTextInput(textInput);
            }
            else if (namespaceOk && "webMaster".equalsIgnoreCase(childElement.getName()))
            {
                final String webMaster = childElement.getTextTrim();
                channel.setWebMaster(webMaster);
            }
            else if (namespaceOk && "pubDate".equalsIgnoreCase(childElement.getName()))
            {
                final String pubDateAsString = childElement.getTextTrim();
                final Date pubDate = dateParser.parse(pubDateAsString);
                channel.setPubDate(pubDate);
            }
            else if (namespaceOk && "lastBuildDate".equalsIgnoreCase(childElement.getName()))
            {
                final String lastBuildDateAsString = childElement.getTextTrim();
                final Date lastBuildDate = dateParser.parse(lastBuildDateAsString);
                channel.setLastBuildDate(lastBuildDate);
            }
            else if (namespaceOk && "image".equalsIgnoreCase(childElement.getName()))
            {
                final Image image = parseImage(childElement);
                channel.setImage(image);
            }
            else if (namespaceOk && "ttl".equalsIgnoreCase(childElement.getName()))
            {
                final String ttlAsString = childElement.getTextTrim();
                final int ttl = Integer.parseInt(ttlAsString);
                channel.setTtl(ttl);
            }
            else if (namespaceOk && "category".equalsIgnoreCase(childElement.getName()))
            {
                final String category = childElement.getTextTrim();
                channel.addCategory(category);
            }
            else if (namespaceOk && "skipDays".equalsIgnoreCase(childElement.getName()))
            {
                parseSkipDays(childElement, channel);
            }
            else if (namespaceOk && "skipHours".equalsIgnoreCase(childElement.getName()))
            {
                parseSkipHours(childElement, channel);
            }
            else if (namespaceOk && "skipHours".equalsIgnoreCase(childElement.getName()))
            {
                final String category = childElement.getTextTrim();
                channel.addCategory(category);
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

    private Cloud parseCloud(final Element cloudElement)
    {
        final Cloud cloud = new Cloud();

        for (final Attribute attribute : cloudElement.getAttributes())
        {
            if ("domain".equalsIgnoreCase(attribute.getName()))
            {
                cloud.setDomain(attribute.getValue());
            }
            else if ("port".equalsIgnoreCase(attribute.getName()))
            {
                try
                {
                    cloud.setPort(attribute.getIntValue());
                }
                catch (DataConversionException e)
                {
                    // ignore, effectively setting port to null
                }
            }
            else if ("path".equalsIgnoreCase(attribute.getName()))
            {
                cloud.setPath(attribute.getValue());
            }
            else if ("registerProcedure".equalsIgnoreCase(attribute.getName()))
            {
                cloud.setRegisterProcedure(attribute.getValue());
            }
            else if ("protocol".equalsIgnoreCase(attribute.getName()))
            {
                cloud.setProtocol(attribute.getValue());
            }
            else
            {
                // log unknown attribute
            }
        }

        return cloud;
    }

    private TextInput parseTextInput(final Element textInputElement)
    {
        final TextInput textInput = new TextInput();
        for (final Element childElement : textInputElement.getChildren())
        {
            final boolean namespaceOk = getNamespace().equals(childElement.getNamespace());

            if (namespaceOk && "link".equalsIgnoreCase(childElement.getName()))
            {
                final String link = childElement.getTextTrim();
                textInput.setLink(link);
            }
            else if (namespaceOk && "title".equalsIgnoreCase(childElement.getName()))
            {
                final String title = childElement.getTextTrim();
                textInput.setTitle(title);
            }
            else if (namespaceOk && "name".equalsIgnoreCase(childElement.getName()))
            {
                final String name = childElement.getTextTrim();
                textInput.setName(name);
            }
            else if (namespaceOk && "description".equalsIgnoreCase(childElement.getName()))
            {
                final String description = childElement.getTextTrim();
                textInput.setDescription(description);
            }
            else
            {
                // log unexpected element
            }
        }

        return textInput;
    }

    private Image parseImage(final Element imageElement)
    {
        final Image image = new Image();
        for (final Element childElement : imageElement.getChildren())
        {
            final boolean namespaceOk = getNamespace().equals(childElement.getNamespace());

            if (namespaceOk && "link".equalsIgnoreCase(childElement.getName()))
            {
                final String link = childElement.getTextTrim();
                image.setLink(link);
            }
            else if (namespaceOk && "title".equalsIgnoreCase(childElement.getName()))
            {
                final String title = childElement.getTextTrim();
                image.setTitle(title);
            }
            else if (namespaceOk && "url".equalsIgnoreCase(childElement.getName()))
            {
                final String url = childElement.getTextTrim();
                image.setUrl(url);
            }
            else if (namespaceOk && "description".equalsIgnoreCase(childElement.getName()))
            {
                final String description = childElement.getTextTrim();
                image.setDescription(description);
            }
            else if (namespaceOk && "height".equalsIgnoreCase(childElement.getName()))
            {
                final String heightAsString = childElement.getTextTrim();
                final int height = Integer.parseInt(heightAsString);
                image.setHeight(height);
            }
            else if (namespaceOk && "width".equalsIgnoreCase(childElement.getName()))
            {
                final String widthAsString = childElement.getTextTrim();
                final int width = Integer.parseInt(widthAsString);
                image.setWidth(width);
            }
        }

        return image;
    }

    private void parseSkipHours(final Element skipHoursElement, final Channel channel)
    {
        for (final Element childElement : skipHoursElement.getChildren())
        {
            final boolean namespaceOk = getNamespace().equals(childElement.getNamespace());

            if (namespaceOk && "skipHour".equalsIgnoreCase(childElement.getName()))
            {
                final String hourAsString = childElement.getTextTrim();

                channel.addSkipHour(new Hour(Byte.parseByte(hourAsString)));
            }
        }
    }

    private void parseSkipDays(final Element skipDaysElement, final Channel channel)
    {
        for (final Element childElement : skipDaysElement.getChildren())
        {
            final boolean namespaceOk = getNamespace().equals(childElement.getNamespace());

            if (namespaceOk && "skipDay".equalsIgnoreCase(childElement.getName()))
            {
                final String dayAsString = childElement.getTextTrim();

                channel.addSkipDay(Day.valueOf(dayAsString));
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
                final Source source = new Source();
                source.setValue(childElement.getTextTrim());
                final Attribute urlAttribute = childElement.getAttribute("url");
                if (urlAttribute != null)
                {
                    source.setUrl(urlAttribute.getValue());
                }
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
