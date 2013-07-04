package org.lorethan.newsworm.core.io;

import org.jdom2.Namespace;
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
}
