package org.lorethan.newsworm.core;

import java.io.Serializable;
import java.nio.charset.Charset;

public abstract class AbstractGenericFeed implements Serializable
{
    private final FeedType feedType;
    private final Charset charset;

    protected AbstractGenericFeed(final FeedType feedType, final Charset charset)
    {
        if (feedType == null || charset == null)
        {
            throw new IllegalArgumentException("feedType and charset must be set!");
        }

        this.feedType = feedType;
        this.charset = charset;
    }

    public final FeedType getFeedType()
    {
        return feedType;
    }

    public final Charset getCharset()
    {
        return charset;
    }
}
