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
import org.lorethan.newsworm.core.feed.rss.Channel;
import org.lorethan.newsworm.core.feed.rss.Cloud;
import org.lorethan.newsworm.core.feed.rss.Day;
import org.lorethan.newsworm.core.feed.rss.Enclosure;
import org.lorethan.newsworm.core.feed.rss.GUID;
import org.lorethan.newsworm.core.feed.rss.Hour;
import org.lorethan.newsworm.core.feed.rss.Image;
import org.lorethan.newsworm.core.feed.rss.Source;
import org.lorethan.newsworm.core.feed.rss.TextInput;

public class RSS20ParserTest
{
    private static final DateParser dateParser = new RFC822DateParser();

    private static final String CHANNEL_DESCRIPTION = "Newsworm description.";
    private static final String CHANNEL_GENERATOR = "http://newsworm.org";
    private static final String CHANNEL_LANGUAGE = "en";
    private static final Date CHANNEL_LAST_BUILD_DATE = dateParser.parse("Mon, 17 Sep 2012 11:35:12 +0000");
    private static final Date CHANNEL_PUB_DATE = dateParser.parse("Mon, 17 Sep 2012 11:35:00 +0000");
    private static final String CHANNEL_LINK = "http://newsworm.org/channel";
    private static final String CHANNEL_TITLE = "Newsworm title";
    private static final String CHANNEL_COPYRIGHT = "2013 Lorethan Inc. All rights reserved.";
    private static final String CHANNEL_DOCS = "http://www.w3schools.com/rss/default.asp";
    private static final String CHANNEL_MANAGING_EDITOR = "lorethan@newsworm.org";
    private static final String CHANNEL_RATING = "(PICS-1.1 \"http://www.classify.org/safesurf/\" 1 r (SS~~000 1))";
    private static final String CHANNEL_WEB_MASTER = "lorethan@newsworm.org";
    private static final int CHANNEL_TTL = 10;
    private static final Cloud CHANNEL_CLOUD = new Cloud();
    private static final TextInput CHANNEL_TEXT_INPUT = new TextInput();
    private static final Image CHANNEL_IMAGE = new Image();

    private static final String ITEM_TITLE = "Newsworm item title";
    private static final String ITEM_DESCRIPTION = "Newsworm item description";
    private static final String ITEM_LINK = "http://newsworm.org/item";
    private static final String ITEM_AUTHOR = "Newsworm item author";
    private static final String ITEM_COMMENTS = "http://newsworm.org/item#comments";
    private static final Date ITEM_PUB_DATE = dateParser.parse("Mon, 17 Sep 2012 11:35:12 +0000");
    private static final String ITEM_CATEGORY = "RSS 2.0";

    private static final Source ITEM_SOURCE = new Source();
    private static final Enclosure ITEM_ENCLOSURE = new Enclosure();
    private static final GUID ITEM_GUID = new GUID();

    private static Channel channel;

    @BeforeClass
    public static void initialize() throws IOException, JDOMException
    {
        final InputStream inputStream = RSS20ParserTest.class.getResourceAsStream("/fullRss20.xml");

        final XmlCharsetResolvingReader reader = new XmlCharsetResolvingReader(inputStream, null);

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(reader);

        final RSS20Parser parser = new RSS20Parser();

        channel = parser.parse(document, reader.getEncoding());

        CHANNEL_CLOUD.setDomain("www.w3schools.com");
        CHANNEL_CLOUD.setPath("/RPC");
        CHANNEL_CLOUD.setPort(80);
        CHANNEL_CLOUD.setProtocol("xml-rpc");
        CHANNEL_CLOUD.setRegisterProcedure("NotifyMe");

        CHANNEL_TEXT_INPUT.setDescription("Search Google");
        CHANNEL_TEXT_INPUT.setLink("http://www.goggle.com/search?");
        CHANNEL_TEXT_INPUT.setName("q");
        CHANNEL_TEXT_INPUT.setTitle("Search");

        CHANNEL_IMAGE.setDescription("W3Schools");
        CHANNEL_IMAGE.setLink("http://www.w3schools.com");
        CHANNEL_IMAGE.setTitle("W3Schools.com");
        CHANNEL_IMAGE.setUrl("http://www.w3schools.com/images/logo.gif");
        CHANNEL_IMAGE.setHeight(400);
        CHANNEL_IMAGE.setWidth(144);

        ITEM_SOURCE.setUrl("http://newsworm.org/itemSource");
        ITEM_SOURCE.setValue("Newsworm item source");

        ITEM_ENCLOSURE.setUrl("http://newsworm.org/image.png");
        ITEM_ENCLOSURE.setType("image/png");
        ITEM_ENCLOSURE.setLength(2048L);

        ITEM_GUID.setPermaLink(true);
        ITEM_GUID.setValue("http://newsworm.org/item");
    }

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
    public void checkChannelPubDateIsParsed()
    {
        assertThat(channel.getPubDate(), is(CHANNEL_PUB_DATE));
    }

    @Test
    public void checkChannelLinkIsParsed()
    {
        assertThat(channel.getLink(), is(CHANNEL_LINK));
    }

    @Test
    public void checkChannelCopyrightIsParsed()
    {
        assertThat(channel.getCopyright(), is(CHANNEL_COPYRIGHT));
    }

    @Test
    public void checkChannelDocsIsParsed()
    {
        assertThat(channel.getDocs(), is(CHANNEL_DOCS));
    }

    @Test
    public void checkChannelManagingEditorIsParsed()
    {
        assertThat(channel.getManagingEditor(), is(CHANNEL_MANAGING_EDITOR));
    }

    @Test
    public void checkChannelRatingIsParsed()
    {
        assertThat(channel.getRating(), is(CHANNEL_RATING));
    }

    @Test
    public void checkChannelWebMasterIsParsed()
    {
        assertThat(channel.getWebMaster(), is(CHANNEL_WEB_MASTER));
    }

    @Test
    public void checkChannelTtlIsParsed()
    {
        assertThat(channel.getTtl(), is(CHANNEL_TTL));
    }

    @Test
    public void checkChannelSkipDaysIsParsed()
    {
        assertThat(channel.getSkipDays(), IsIterableContainingInAnyOrder.<Day>containsInAnyOrder(Day.Saturday, Day.Sunday));
    }

    @Test
    public void checkChannelSkipHoursIsParsed()
    {
        assertThat(channel.getSkipHours(), IsIterableContainingInAnyOrder.<Hour>containsInAnyOrder(new Hour((byte) 0), new Hour((byte) 1)));
    }

    @Test
    public void checkChannelCloudIsParsed()
    {
        assertThat(channel.getCloud(), is(CHANNEL_CLOUD));
    }

    @Test
    public void checkChannelTextInputIsParsed()
    {
        assertThat(channel.getTextInput(), is(CHANNEL_TEXT_INPUT));
    }

    @Test
    public void checkChannelImageIsParsed()
    {
        assertThat(channel.getImage(), is(CHANNEL_IMAGE));
    }

    @Test
    public void checkItemTitleIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getTitle(), is(ITEM_TITLE));
    }

    @Test
    public void checkItemDescriptionIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getDescription(), is(ITEM_DESCRIPTION));
    }

    @Test
    public void checkItemLinkIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getLink(), is(ITEM_LINK));
    }

    @Test
    public void checkItemAuthorIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getAuthor(), is(ITEM_AUTHOR));
    }

    @Test
    public void checkItemCommentsIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getComments(), is(ITEM_COMMENTS));
    }

    @Test
    public void checkItemPubDateIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getPubDate(), is(ITEM_PUB_DATE));
    }

    @Test
    public void checkItemCategoriesIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getCategories(), IsIterableContainingInAnyOrder.<String>containsInAnyOrder(ITEM_CATEGORY));
    }

    @Test
    public void checkItemEnclosureIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getEnclosure(), is(ITEM_ENCLOSURE));
    }

    @Test
    public void checkItemGuidIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getGuid(), is(ITEM_GUID));
    }

    @Test
    public void checkItemSourceIsParsed()
    {
        assertThat(channel.getItems().iterator().next().getSource(), is(ITEM_SOURCE));
    }

}
