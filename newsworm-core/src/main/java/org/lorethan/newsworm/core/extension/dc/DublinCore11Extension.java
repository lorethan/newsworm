package org.lorethan.newsworm.core.extension.dc;

import org.jdom2.Namespace;
import org.lorethan.newsworm.core.extension.AbstractExtension;

public class DublinCore11Extension extends AbstractExtension
{
    private static final String NAMESPACE_URI = "http://purl.org/dc/elements/1.1/";

    protected DublinCore11Extension()
    {
        super(Namespace.getNamespace(NAMESPACE_URI));
    }
}
