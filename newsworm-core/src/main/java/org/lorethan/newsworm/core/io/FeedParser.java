package org.lorethan.newsworm.core.io;

import org.jdom2.Document;
import org.lorethan.newsworm.core.AbstractGenericFeed;
import org.lorethan.newsworm.core.FeedType;

public interface FeedParser<T extends AbstractGenericFeed>
{
    FeedType getFeedType();

    T parse(Document document);

    boolean canParse(Document document);
}
