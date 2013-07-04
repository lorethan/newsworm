package org.lorethan.newsworm.core.io;

import java.util.Date;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.Extension;
import org.lorethan.newsworm.core.extension.ExtensionParser;
import org.lorethan.newsworm.core.extension.ExtensionParserFactory;
import org.lorethan.newsworm.core.feed.FeedType;
import org.lorethan.newsworm.core.feed.rss.Channel;

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
                // TODO: handle
            }
            else
            {
                resolveExtensions(childElement, channel);
            }
        }

    }

    protected void resolveExtensions(final Element element, final Channel channel)
    {
        final Namespace elementNamespace = element.getNamespace();

        final ExtensionParser<?> extensionParser = ExtensionParserFactory.getExtensionParser(elementNamespace);

        if (extensionParser != null)
        {
            Extension existingExtension = null;
            for (final Extension extension : channel.getExtensions())
            {
                if (extension.getNamespace().equals(elementNamespace))
                {
                    existingExtension = extension;
                    break;
                }
            }

            final Extension extension = extensionParser.parse(element, existingExtension);

            if (existingExtension == null)
            {
                channel.addExtension(extension);
            }
        }
        else
        {
            System.out.println("Not able to parse extension for namespace: " + elementNamespace.getURI());
        }
    }

}
