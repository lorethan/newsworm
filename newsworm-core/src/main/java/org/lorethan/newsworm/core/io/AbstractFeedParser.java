package org.lorethan.newsworm.core.io;

import org.jdom2.Namespace;
import org.lorethan.newsworm.core.FeedType;

public abstract class AbstractFeedParser implements FeedParser
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
