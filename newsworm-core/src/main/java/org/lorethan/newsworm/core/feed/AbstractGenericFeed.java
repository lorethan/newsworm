package org.lorethan.newsworm.core.feed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.lorethan.newsworm.core.extension.Extension;

public abstract class AbstractGenericFeed implements Serializable
{
    private final FeedType feedType;
    private final String encoding;
    private final List<Extension> extensions = new ArrayList<>();

    protected AbstractGenericFeed(final FeedType feedType, final String encoding)
    {
        if (feedType == null || encoding == null)
        {
            throw new IllegalArgumentException("feedType and encoding must be set!");
        }

        this.feedType = feedType;
        this.encoding = encoding;
    }

    public final FeedType getFeedType()
    {
        return feedType;
    }

    public final String getEncoding()
    {
        return encoding;
    }

    public Iterable<Extension> getExtensions()
    {
        return extensions;
    }

    public void addExtension(final Extension extension)
    {
        extensions.add(extension);
    }
}
