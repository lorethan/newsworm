package org.lorethan.newsworm.core.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lorethan.newsworm.core.extension.atom.Atom10Extension;
import org.lorethan.newsworm.core.extension.syndication.SyndicationExtension;
import org.lorethan.newsworm.core.feed.atom.Link;
import org.lorethan.newsworm.core.feed.rss.Channel;

public class RSS20WithExtensionsParserTest
{
    private static final DateParser dateParser = new RFC822DateParser();

    private static final String CHANNEL_DESCRIPTION = "NRKs sandkasse for teknologi, duppeditter, mye medier og alt annet som er viktig her i livet.";
    private static final String CHANNEL_GENERATOR = "http://wordpress.org/?v=3.2.1";
    private static final String CHANNEL_LANGUAGE = "en";
    private static final Date CHANNEL_LAST_BUILD_DATE = dateParser.parse("Mon, 17 Sep 2012 11:35:12 +0000");
    private static final String CHANNEL_LINK = "http://nrkbeta.no";
    private static final String CHANNEL_TITLE = "NRKbeta";
    private static final Link ATOM_CHANNEL_SELF_LINK = new Link("self", "application/rss+xml", "http://feeds.feedburner.com/nrkbeta");
    private static final Link ATOM_CHANNEL_HUB_LINK = new Link("hub", "", "http://pubsubhubbub.appspot.com/");
    private static final SyndicationExtension EXPECTED_SYNDICATION_EXTENSION = new SyndicationExtension();

    private static Channel channel;

    static
    {
        EXPECTED_SYNDICATION_EXTENSION.setUpdatePeriod(SyndicationExtension.UpdatePeriod.HOURLY);
        EXPECTED_SYNDICATION_EXTENSION.setUpdateFrequency(1);
    }

    @BeforeClass
    public static void initialize() throws IOException, JDOMException
    {
        final InputStream inputStream = RSS20WithExtensionsParserTest.class.getResourceAsStream("/nrkbeta.xml");

        final XmlCharsetResolvingReader reader = new XmlCharsetResolvingReader(inputStream, "text/xml; charset=UTF-8");

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(reader);

        final RSS20Parser parser = new RSS20Parser();

        channel = parser.parse(document, reader.getEncoding());
    }

//    assertThat(channel.getExtensions(), IsIterableContainingInAnyOrder.<Extension>containsInAnyOrder(syndicationExtension, atomExtension));

    @Test
    public void checkChannelTitleIsParsed()
    {
        assertThat(channel.getTitle(), is(CHANNEL_TITLE));
    }

    @Test
    public void checkChannelDescriptionIsParsed()
    {
        assertThat(channel.getDescription(), is(CHANNEL_DESCRIPTION));
    }

    @Test
    public void checkChannelGeneratorIsParsed()
    {
        assertThat(channel.getGenerator(), is(CHANNEL_GENERATOR));
    }

    @Test
    public void checkChannelLanguageIsParsed()
    {
        assertThat(channel.getLanguage(), is(CHANNEL_LANGUAGE));
    }

    @Test
    public void checkChannelLastBuildDateIsParsed()
    {
        assertThat(channel.getLastBuildDate(), is(CHANNEL_LAST_BUILD_DATE));
    }

    @Test
    public void checkChannelLinkIsParsed()
    {
        assertThat(channel.getLink(), is(CHANNEL_LINK));
    }

    @Test
    public void checkChannelAtomExtensionLinksIsParsed()
    {
        final Atom10Extension atomExtension = (Atom10Extension) channel.getExtension(Atom10Extension.NAMESPACE_URI);

        assertThat(atomExtension.getLinks(), IsIterableContainingInAnyOrder.<Link>containsInAnyOrder(ATOM_CHANNEL_SELF_LINK, ATOM_CHANNEL_HUB_LINK));
    }

    @Test
    public void checkChannelSyndicationExtensionIsParsed()
    {
        final SyndicationExtension extension = (SyndicationExtension) channel.getExtension(SyndicationExtension.NAMESPACE_URI);

        assertThat(extension, is(EXPECTED_SYNDICATION_EXTENSION));
    }
}
