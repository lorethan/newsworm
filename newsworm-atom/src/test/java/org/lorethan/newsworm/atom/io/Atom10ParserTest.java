package org.lorethan.newsworm.atom.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lorethan.newsworm.atom.feed.Content;
import org.lorethan.newsworm.atom.feed.Feed;
import org.lorethan.newsworm.core.io.DateParser;
import org.lorethan.newsworm.core.io.W3CDateParser;
import org.lorethan.newsworm.core.io.XmlCharsetResolvingReader;

public class Atom10ParserTest
{
    private static final DateParser dateParser = new W3CDateParser();

    private final static String FEED_ID = "tag:example.org,2003:3";
    private final static Content FEED_TITLE = new Content();
    private final static Content FEED_SUBTITLE = new Content();
    private final static Date FEED_UPDATED = dateParser.parse("2005-07-31T12:29:29Z");
    private final static String FEED_RIGHTS = "Copyright (c) 2003, Mark Pilgrim";
    private static Feed feed;

    @BeforeClass
    public static void initialize() throws IOException, JDOMException
    {
        FEED_TITLE.setType("text");
        FEED_TITLE.setSrc(null);
        FEED_TITLE.setValue("dive into mark");

        FEED_SUBTITLE.setType("html");
        FEED_SUBTITLE.setSrc(null);
        FEED_SUBTITLE.setValue("A <em>lot</em> of effort went into making this effortless");

        final InputStream inputStream = Atom10ParserTest.class.getResourceAsStream("/atomFeed.xml");

        final XmlCharsetResolvingReader reader = new XmlCharsetResolvingReader(inputStream, null);

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(reader);

        final Atom10Parser parser = new Atom10Parser();

        feed = parser.parse(document, reader.getEncoding());
    }

    @Test
    public void checkParsesId()
    {
        assertThat(feed.getId(), is(FEED_ID));
    }

    @Test
    public void checkParsesTitle()
    {
        assertThat(feed.getTitle(), is(FEED_TITLE));
    }

    @Test
    public void checkParsesSubtitle()
    {
        assertThat(feed.getSubtitle(), is(FEED_SUBTITLE));
    }

    @Test
    public void checkParsesUpdated()
    {
        assertThat(feed.getUpdated(), is(FEED_UPDATED));
    }

    @Test
    public void checkParsesRights()
    {
        assertThat(feed.getRights(), is(FEED_RIGHTS));
    }

}
