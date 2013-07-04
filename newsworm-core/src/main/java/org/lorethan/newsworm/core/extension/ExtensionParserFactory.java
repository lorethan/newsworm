package org.lorethan.newsworm.core.extension;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.syndication.SyndicationExtensionParser;

public abstract class ExtensionParserFactory
{
    private static final List<ExtensionParser<?>> extensionParsers = new ArrayList<>();

    static
    {
        extensionParsers.add(new SyndicationExtensionParser());
    }

    private ExtensionParserFactory()
    {
    }

    public static ExtensionParser<?> getExtensionParser(final Namespace namespace)
    {
        for (final ExtensionParser<?> extensionParser : extensionParsers)
        {
            if (extensionParser.canParse(namespace))
            {
                return extensionParser;
            }
        }

        return null;
    }


}
