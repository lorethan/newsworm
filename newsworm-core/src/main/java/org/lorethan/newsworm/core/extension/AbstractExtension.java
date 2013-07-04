package org.lorethan.newsworm.core.extension;

import org.jdom2.Namespace;

public abstract class AbstractExtension implements Extension
{
    private final Namespace namespace;

    protected AbstractExtension(final Namespace namespace)
    {
        this.namespace = namespace;
    }

    public Namespace getNamespace()
    {
        return namespace;
    }
}
