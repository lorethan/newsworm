package org.lorethan.newsworm.atom.io;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static uk.co.it.modular.hamcrest.date.DateMatchers.sameInstant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.hamcrest.collection.IsIterableWithSize;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lorethan.newsworm.atom.feed.Category;
import org.lorethan.newsworm.atom.feed.Content;
import org.lorethan.newsworm.atom.feed.Entry;
import org.lorethan.newsworm.atom.feed.Feed;
import org.lorethan.newsworm.atom.feed.Generator;
import org.lorethan.newsworm.atom.feed.Image;
import org.lorethan.newsworm.atom.feed.Link;
import org.lorethan.newsworm.atom.feed.Person;
import org.lorethan.newsworm.core.io.XmlCharsetResolvingReader;
import org.lorethan.newsworm.core.io.datetime.DefaultW3CDateParser;

public class Atom10ParserTest
{
    private final static String FEED_ID = "tag:example.org,2003:3";
    private final static Content FEED_TITLE = new Content();
    private final static Content FEED_SUBTITLE = new Content();
    private final static String FEED_RIGHTS = "Copyright (c) 2003, Mark Pilgrim";
    private final static Generator FEED_GENERATOR = new Generator();
    private final static Person MARK_PILGRIM = new Person();
    private final static Person SAM_RUBY = new Person();
    private final static Person JOE_GREGORIO = new Person();
    private final static Link FEED_SELF_LINK = new Link();
    private final static Link FEED_ALTERNATE_LINK = new Link();
    private final static Image FEED_ICON = new Image();
    private final static Image FEED_LOGO = new Image();
    private static Long FEED_UPDATED;

    private final static String ENTRY_ID = "tag:example.org,2003:3.2397";
    private final static Content ENTRY_TITLE = new Content();
    private final static Content ENTRY_SUMMARY = new Content();
    private final static Content ENTRY_CONTENT = new Content();
    private final static Link ENTRY_ALTERNATE_LINK = new Link();
    private final static Link ENTRY_ENCLOSURE_LINK = new Link();
    private static Long ENTRY_UPDATED;
    private static Long ENTRY_PUBLISHED;

    private static final Category ATOM_CATEGORY = new Category();
    private static final Category SYNDICATION_CATEGORY = new Category();

    private static Feed feed;

    @BeforeClass
    public static void initialize() throws IOException, JDOMException
    {
        final Calendar cal = GregorianCalendar.getInstance(Locale.US);
        cal.set(Calendar.YEAR, 2005);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 29);
        cal.set(Calendar.SECOND, 29);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        FEED_UPDATED = cal.getTimeInMillis();

        FEED_TITLE.setType("text");
        FEED_TITLE.setSrc(null);
        FEED_TITLE.setValue("dive into mark");

        FEED_SUBTITLE.setType("html");
        FEED_SUBTITLE.setSrc(null);
        FEED_SUBTITLE.setValue("A <em>lot</em> of effort went into making this effortless");

        FEED_GENERATOR.setUri("http://www.example.com/");
        FEED_GENERATOR.setValue("Example Toolkit");
        FEED_GENERATOR.setVersion("1.0");

        MARK_PILGRIM.setEmail("f8dy@example.com");
        MARK_PILGRIM.setName("Mark Pilgrim");
        MARK_PILGRIM.setUri("http://example.org/");

        SAM_RUBY.setName("Sam Ruby");

        JOE_GREGORIO.setName("Joe Gregorio");

        FEED_SELF_LINK.setHref("http://example.org/feed.atom");
        FEED_SELF_LINK.setRel("self");
        FEED_SELF_LINK.setType("application/atom+xml");

        FEED_ALTERNATE_LINK.setHref("http://example.org/");
        FEED_ALTERNATE_LINK.setRel("alternate");
        FEED_ALTERNATE_LINK.setType("text/html");
        FEED_ALTERNATE_LINK.setHrefLang("en");

        FEED_ICON.setUri("http://example.org/icon.png");
        FEED_LOGO.setUri("http://example.org/logo.png");

        ENTRY_TITLE.setValue("Atom draft-07 snapshot");

        ENTRY_SUMMARY.setValue("<i>Atom draft-07 snapshot</i>");
        ENTRY_SUMMARY.setType("html");

        ENTRY_CONTENT.setType("xhtml");
        ENTRY_CONTENT.setValue("\r\n" +
                "            <div xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" +
                "                <p>\r\n" +
                "                    <i>[Update: The Atom draft is finished.]</i>\r\n" +
                "                </p>\r\n" +
                "            </div>\r\n" +
                "        ");

        ENTRY_ALTERNATE_LINK.setHref("http://example.org/2005/04/02/atom");
        ENTRY_ALTERNATE_LINK.setRel("alternate");
        ENTRY_ALTERNATE_LINK.setType("text/html");

        ENTRY_ENCLOSURE_LINK.setHref("http://example.org/audio/ph34r_my_podcast.mp3");
        ENTRY_ENCLOSURE_LINK.setRel("enclosure");
        ENTRY_ENCLOSURE_LINK.setType("audio/mpeg");
        ENTRY_ENCLOSURE_LINK.setLength(1337L);

        cal.set(Calendar.YEAR, 2005);
        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.DAY_OF_MONTH, 31);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 29);
        cal.set(Calendar.SECOND, 29);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));

        ENTRY_UPDATED = cal.getTimeInMillis();

//        <published>2003-12-13T08:29:29-04:00</published>
        cal.set(Calendar.YEAR, 2003);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 13);
        cal.set(Calendar.HOUR_OF_DAY, 8);
        cal.set(Calendar.MINUTE, 29);
        cal.set(Calendar.SECOND, 29);
        cal.set(Calendar.MILLISECOND, 0);
        cal.setTimeZone(TimeZone.getTimeZone("GMT-4"));

        ENTRY_PUBLISHED = cal.getTimeInMillis();

        ATOM_CATEGORY.setTerm("atom");
        ATOM_CATEGORY.setScheme("http://myscheme");
        ATOM_CATEGORY.setLabel("Atom");

        SYNDICATION_CATEGORY.setTerm("syndication");
        SYNDICATION_CATEGORY.setScheme("http://myscheme");
        SYNDICATION_CATEGORY.setLabel("Syndication");

        final InputStream inputStream = Atom10ParserTest.class.getResourceAsStream("/atomFeed.xml");

        final XmlCharsetResolvingReader reader = new XmlCharsetResolvingReader(inputStream, null);

        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(reader);

        final Atom10Parser parser = new Atom10Parser(new DefaultW3CDateParser());

        feed = parser.parse(document, reader.getEncoding());
    }

    @Test
    public void checkParsesFeedId()
    {
        assertThat(feed.getId(), is(FEED_ID));
    }

    @Test
    public void checkParsesFeedTitle()
    {
        assertThat(feed.getTitle(), is(FEED_TITLE));
    }

    @Test
    public void checkParsesFeedSubtitle()
    {
        assertThat(feed.getSubtitle(), is(FEED_SUBTITLE));
    }

    @Test
    public void checkParsesFeedUpdated()
    {
        assertThat(feed.getUpdated(), sameInstant(FEED_UPDATED));
    }

    @Test
    public void checkParsesFeedRights()
    {
        assertThat(feed.getRights(), is(FEED_RIGHTS));
    }

    @Test
    public void checkParsesFeedGenerator()
    {
        assertThat(feed.getGenerator(), is(FEED_GENERATOR));
    }

    @Test
    public void checkParsesFeedAuthors()
    {
        assertThat(feed.getAuthors(), IsIterableContainingInAnyOrder.<Person>containsInAnyOrder(MARK_PILGRIM));
    }

    @Test
    public void checkParsesFeedContributors()
    {
        assertThat(feed.getContributors(), IsIterableContainingInAnyOrder.<Person>containsInAnyOrder(SAM_RUBY));
    }

    @Test
    public void checkParsesFeedLinks()
    {
        assertThat(feed.getLinks(), IsIterableContainingInOrder.<Link>contains(FEED_ALTERNATE_LINK, FEED_SELF_LINK));
    }

    @Test
    public void checkParsesFeedCategories()
    {
        assertThat(feed.getCategories(), IsIterableContainingInOrder.<Category>contains(ATOM_CATEGORY, SYNDICATION_CATEGORY));
    }

    @Test
    public void checkParsesFeedIcon()
    {
        assertThat(feed.getIcon(), is(FEED_ICON));
    }

    @Test
    public void checkParsesFeedLogo()
    {
        assertThat(feed.getLogo(), is(FEED_LOGO));
    }

    @Test
    public void checkParsesAllFeedEntries()
    {
        assertThat(feed.getEntries(), IsIterableWithSize.<Entry>iterableWithSize(1));
    }

    @Test
    public void checkParsesEntryId()
    {
        assertThat(feed.getEntries().iterator().next().getId(), is(ENTRY_ID));
    }

    @Test
    public void checkParsesEntryTitle()
    {
        assertThat(feed.getEntries().iterator().next().getTitle(), is(ENTRY_TITLE));
    }

    @Test
    public void checkParsesEntrySummary()
    {
        assertThat(feed.getEntries().iterator().next().getSummary(), is(ENTRY_SUMMARY));
    }

    @Test
    public void checkParsesEntryContent()
    {
        assertThat(feed.getEntries().iterator().next().getContent(), is(ENTRY_CONTENT));
    }

    @Test
    public void checkParsesEntryAuthors()
    {
        assertThat(feed.getEntries().iterator().next().getAuthors(), IsIterableContainingInOrder.<Person>contains(MARK_PILGRIM));
    }

    @Test
    public void checkParsesEntryContributors()
    {
        assertThat(feed.getEntries().iterator().next().getContributors(), IsIterableContainingInOrder.<Person>contains(SAM_RUBY, JOE_GREGORIO));
    }

    @Test
    public void checkParsesEntryLinks()
    {
        assertThat(feed.getEntries().iterator().next().getLinks(), IsIterableContainingInOrder.<Link>contains(ENTRY_ALTERNATE_LINK, ENTRY_ENCLOSURE_LINK));
    }

    @Test
    public void checkParsesEntryCategories()
    {
        assertThat(feed.getEntries().iterator().next().getCategories(), IsIterableContainingInOrder.<Category>contains(ATOM_CATEGORY, SYNDICATION_CATEGORY));
    }

    @Test
    public void checkParsesEntryUpdated()
    {
        assertThat(feed.getEntries().iterator().next().getUpdated(), sameInstant(ENTRY_UPDATED));
    }

    @Test
    public void checkParsesEntryCategoriesPublished()
    {
        assertThat(feed.getEntries().iterator().next().getPublished(), sameInstant(ENTRY_PUBLISHED));
    }

}
