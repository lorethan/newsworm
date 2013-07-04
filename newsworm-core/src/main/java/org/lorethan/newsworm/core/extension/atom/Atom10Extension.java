package org.lorethan.newsworm.core.extension.atom;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.AbstractExtension;
import org.lorethan.newsworm.core.feed.atom.Link;

public class Atom10Extension extends AbstractExtension
{
    public static final String NAMESPACE_URI = "http://www.w3.org/2005/Atom";

    private final List<Link> links = new ArrayList<>();

    public Atom10Extension()
    {
        super(Namespace.getNamespace(NAMESPACE_URI));
    }

    public Iterable<Link> getLinks()
    {
        return links;
    }

    public void addLink(final Link link)
    {
        links.add(link);
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Atom10Extension))
        {
            return false;
        }

        final Atom10Extension that = (Atom10Extension) o;

        if (!links.equals(that.links))
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return links.hashCode();
    }
}
