package org.lorethan.newsworm.core.extension.atom;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.Extension;
import org.lorethan.newsworm.core.extension.ExtensionParser;
import org.lorethan.newsworm.core.feed.atom.Link;

public class Atom10ExtensionParser implements ExtensionParser<Atom10Extension>
{
    private static final Namespace NAMESPACE = Namespace.getNamespace(Atom10Extension.NAMESPACE_URI);

    @Override
    public boolean canParse(final Namespace namspace)
    {
        return NAMESPACE.equals(namspace);
    }

    @Override
    public Atom10Extension parse(final Element element, final Extension extension)
    {
        if (!NAMESPACE.equals(element.getNamespace()))
        {
            throw new IllegalArgumentException("Element does not have expected namespace: " + NAMESPACE.getURI());
        }

        final Atom10Extension extensionToUpdate;

        if (extension == null)
        {
            extensionToUpdate = new Atom10Extension();
        }
        else
        {
            extensionToUpdate = (Atom10Extension) extension;
        }

        if ("link".equalsIgnoreCase(element.getName()))
        {
            final Link link = new Link();

            final Attribute rel = element.getAttribute("rel");
            final Attribute type = element.getAttribute("type");
            final Attribute href = element.getAttribute("href");

            link.setRel((rel != null) ? rel.getValue() : "");
            link.setType((type != null) ? type.getValue() : "");
            link.setHref((href != null) ? href.getValue() : "");

            extensionToUpdate.addLink(link);
        }
        else
        {
            System.err.println("Unknown element: " + element.getName());
        }

        return extensionToUpdate;
    }
}
