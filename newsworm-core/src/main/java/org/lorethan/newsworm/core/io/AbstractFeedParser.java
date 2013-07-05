package org.lorethan.newsworm.core.io;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.Extension;
import org.lorethan.newsworm.core.extension.ExtensionParser;
import org.lorethan.newsworm.core.extension.ExtensionParserFactory;
import org.lorethan.newsworm.core.extension.Extensionable;
import org.lorethan.newsworm.core.feed.AbstractGenericFeed;
import org.lorethan.newsworm.core.feed.FeedType;

public abstract class AbstractFeedParser<T extends AbstractGenericFeed> implements FeedParser<T>
{
    private final FeedType feedType;
    private final Namespace namespace;

    protected AbstractFeedParser(final FeedType feedType, final Namespace namespace)
    {
        this.feedType = feedType;
        this.namespace = namespace;
    }

    @Override
    public final FeedType getFeedType()
    {
        return feedType;
    }

    protected final Namespace getNamespace()
    {
        return namespace;
    }

    protected void resolveExtensions(final Element element, final Extensionable extensionable)
    {
        final Namespace elementNamespace = element.getNamespace();

        final ExtensionParser<?> extensionParser = ExtensionParserFactory.getExtensionParser(elementNamespace);

        if (extensionParser != null)
        {
            final Extension existingExtension = extensionable.getExtension(elementNamespace.getURI());

            final Extension extension = extensionParser.parse(element, existingExtension);

            if (existingExtension == null)
            {
                extensionable.addExtension(extension);
            }
        }
        else
        {
            System.out.println("Not able to parse extension for namespace: " + elementNamespace.getURI());
        }
    }
}
