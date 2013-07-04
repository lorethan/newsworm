package org.lorethan.newsworm.core.io;

import org.jdom2.Document;
import org.lorethan.newsworm.core.feed.AbstractGenericFeed;
import org.lorethan.newsworm.core.feed.FeedType;

public interface FeedParser<T extends AbstractGenericFeed>
{
    FeedType getFeedType();

    T parse(Document document, String encoding);

    boolean canParse(Document document, String encoding);
}
