package org.lorethan.newsworm.core.io;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.lorethan.newsworm.core.feed.AbstractGenericFeed;

public abstract class FeedParserFactory
{
    private static final List<FeedParser<? extends AbstractGenericFeed>> feedParsers = new ArrayList<>();

    static
    {
        feedParsers.add(new RSS20Parser());
    }

    private FeedParserFactory()
    {
    }

    public static FeedParser<? extends AbstractGenericFeed> getFeedParser(final Document document)
    {
        for (final FeedParser<? extends AbstractGenericFeed> feedParser : feedParsers)
        {
            if (feedParser.canParse(document))
            {
                return feedParser;
            }
        }

        return null;
    }
}
