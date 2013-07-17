package org.lorethan.newsworm.core.extension;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.atom.Atom10ExtensionParser;
import org.lorethan.newsworm.core.extension.dc.DublinCoreExtensionParser;
import org.lorethan.newsworm.core.extension.syndication.SyndicationExtensionParser;

public abstract class ExtensionParserFactory
{
    private static final List<ExtensionParser<? extends Extension>> extensionParsers = new ArrayList<>();

    static
    {
        extensionParsers.add(new SyndicationExtensionParser());
        extensionParsers.add(new DublinCoreExtensionParser());
        extensionParsers.add(new Atom10ExtensionParser());
    }

    private ExtensionParserFactory()
    {
    }

    public static ExtensionParser<? extends Extension> getExtensionParser(final Namespace namespace)
    {
        for (final ExtensionParser<? extends Extension> extensionParser : extensionParsers)
        {
            if (extensionParser.canParse(namespace))
            {
                return extensionParser;
            }
        }

        return null;
    }
}
