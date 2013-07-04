package org.lorethan.newsworm.core.extension.dc;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.Extension;
import org.lorethan.newsworm.core.extension.ExtensionParser;
import org.lorethan.newsworm.core.io.DateParser;
import org.lorethan.newsworm.core.io.W3CDateParser;

public class DublinCoreExtensionParser implements ExtensionParser<DublinCoreExtension>
{
    private static final Namespace NAMESPACE = Namespace.getNamespace(DublinCoreExtension.NAMESPACE_URI);

    private final DateParser dateParser = new W3CDateParser();

    @Override
    public boolean canParse(final Namespace namspace)
    {
        return NAMESPACE.equals(namspace);
    }

    @Override
    public DublinCoreExtension parse(final Element element, final Extension extension)
    {
        if (!NAMESPACE.equals(element.getNamespace()))
        {
            throw new IllegalArgumentException("Element does not have expected namespace: " + NAMESPACE.getURI());
        }

        final DublinCoreExtension extensionToUpdate;

        if (extension == null)
        {
            extensionToUpdate = new DublinCoreExtension();
        }
        else
        {
            extensionToUpdate = (DublinCoreExtension) extension;
        }

        if ("contributor".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setContributor(element.getValue());
        }
        else if ("coverage".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setCoverage(element.getValue());
        }
        else if ("creator".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setCreator(element.getValue());
        }
        else if ("date".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setDate(dateParser.parse(element.getValue()));
        }
        else if ("description".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setDescription(element.getValue());
        }
        else if ("format".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setFormat(element.getValue());
        }
        else if ("identifier".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setIdentifier(element.getValue());
        }
        else if ("language".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setLanguage(element.getValue());
        }
        else if ("publisher".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setPublisher(element.getValue());
        }
        else if ("relation".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setRelation(element.getValue());
        }
        else if ("rights".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setRights(element.getValue());
        }
        else if ("source".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setSource(element.getValue());
        }
        else if ("subject".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setSubject(element.getValue());
        }
        else if ("title".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setTitle(element.getValue());
        }
        else if ("type".equalsIgnoreCase(element.getName()))
        {
            extensionToUpdate.setType(element.getValue());
        }
        else
        {
            System.err.println("Unknown element: " + element.getName());
        }

        return extensionToUpdate;
    }
}
