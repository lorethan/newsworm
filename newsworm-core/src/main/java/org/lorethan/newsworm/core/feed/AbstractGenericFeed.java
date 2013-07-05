package org.lorethan.newsworm.core.feed;

import java.io.Serializable;

import org.lorethan.newsworm.core.extension.AbstractExtensionable;

public abstract class AbstractGenericFeed extends AbstractExtensionable implements Serializable
{
    private final FeedType feedType;
    private final String encoding;

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
}
