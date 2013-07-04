package org.lorethan.newsworm.core.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;
import org.lorethan.newsworm.core.extension.Extension;
import org.lorethan.newsworm.core.extension.atom.Atom10Extension;
import org.lorethan.newsworm.core.extension.syndication.SyndicationExtension;
import org.lorethan.newsworm.core.feed.atom.Link;
import org.lorethan.newsworm.core.feed.rss.Channel;

public class RSS20ParserTest
{
    private static final DateParser dateParser = new RFC822DateParser();

    private static final String CHANNEL_DESCRIPTION = "NRKs sandkasse for teknologi, duppeditter, mye medier og alt annet som er viktig her i livet.";
    private static final String CHANNEL_GENERATOR = "http://wordpress.org/?v=3.2.1";
    private static final String CHANNEL_LANGUAGE = "en";
    private static final Date CHANNEL_LAST_BUILD_DATE = dateParser.parse("Mon, 17 Sep 2012 11:35:12 +0000");
    private static final String CHANNEL_LINK = "http://nrkbeta.no";
    private static final String CHANNEL_TITLE = "NRKbeta";

    private static final SyndicationExtension syndicationExtension = new SyndicationExtension();
    private static final Atom10Extension atomExtension = new Atom10Extension();

    static
    {
        syndicationExtension.setUpdatePeriod(SyndicationExtension.UpdatePeriod.HOURLY);
        syndicationExtension.setUpdateFrequency(1);

        final Link selfLink = new Link();
        selfLink.setRel("self");
        selfLink.setHref("http://feeds.feedburner.com/nrkbeta");
        selfLink.setType("application/rss+xml");

        final Link hubLink = new Link();
        hubLink.setRel("hub");
        hubLink.setHref("http://pubsubhubbub.appspot.com/");
        hubLink.setType("");

        atomExtension.addLink(selfLink);
        atomExtension.addLink(hubLink);
    }

    @Test
    public void testParseNrkBeta() throws IOException, JDOMException
    {
        final InputStream inputStream = RSS20ParserTest.class.getResourceAsStream("/nrkbeta.xml");

        final XmlCharsetResolvingReader reader = new XmlCharsetResolvingReader(inputStream, "text/xml; charset=UTF-8");

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(reader);

        final RSS20Parser parser = new RSS20Parser();

        final boolean canParse = parser.canParse(document, reader.getEncoding());

        assertThat(canParse, notNullValue());

        final Channel channel = parser.parse(document, reader.getEncoding());

        assertThat(channel.getDescription(), is(CHANNEL_DESCRIPTION));
        assertThat(channel.getGenerator(), is(CHANNEL_GENERATOR));
        assertThat(channel.getLanguage(), is(CHANNEL_LANGUAGE));
        assertThat(channel.getLastBuildDate(), is(CHANNEL_LAST_BUILD_DATE));
        assertThat(channel.getLink(), is(CHANNEL_LINK));
        assertThat(channel.getTitle(), is(CHANNEL_TITLE));

        assertThat(channel.getExtensions(), IsIterableContainingInAnyOrder.<Extension>containsInAnyOrder(syndicationExtension, atomExtension));
    }
}
