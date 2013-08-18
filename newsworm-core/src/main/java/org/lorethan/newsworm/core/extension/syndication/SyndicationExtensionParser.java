package org.lorethan.newsworm.core.extension.syndication;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.Extension;
import org.lorethan.newsworm.core.extension.ExtensionParser;
import org.lorethan.newsworm.core.io.datetime.W3CDateParser;

public class SyndicationExtensionParser implements ExtensionParser<SyndicationExtension>
{
    private static final Namespace NAMESPACE = Namespace.getNamespace(SyndicationExtension.NAMESPACE_URI);

    private final W3CDateParser dateParser;

    public SyndicationExtensionParser(final W3CDateParser dateParser)
    {
        this.dateParser = dateParser;
    }

    @Override
    public boolean canParse(final Namespace namspace)
    {
        return NAMESPACE.equals(namspace);
    }

    @Override
    public SyndicationExtension parse(final Element element, final Extension extension)
    {
        if (!NAMESPACE.equals(element.getNamespace()))
        {
            throw new IllegalArgumentException("Element does not have expected namespace: " + NAMESPACE.getURI());
        }

        final SyndicationExtension extensionToUpdate;

        if (extension == null)
        {
            extensionToUpdate = new SyndicationExtension();
        }
        else
        {
            extensionToUpdate = (SyndicationExtension) extension;
        }

        if ("updatePeriod".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setUpdatePeriod(SyndicationExtension.UpdatePeriod.fromString(element.getValue()));
        }
        else if ("updateFrequency".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setUpdateFrequency(Integer.parseInt(element.getValue()));
        }
        else if ("updateBase".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setUpdateBase(dateParser.parseW3CDate(element.getValue()));
        }
        else
        {
            System.err.println("Unknown element: " + element.getName());
        }

        return extensionToUpdate;
    }
}
