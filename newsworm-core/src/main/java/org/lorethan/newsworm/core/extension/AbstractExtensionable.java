package org.lorethan.newsworm.core.extension;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractExtensionable implements Extensionable
{
    private final Map<String, Extension> extensionMap = new HashMap<>();

    @Override
    public Iterable<Extension> getExtensions()
    {
        return extensionMap.values();
    }

    @Override
    public void addExtension(final Extension extension)
    {
        extensionMap.put(extension.getNamespace().getURI(), extension);
    }

    @Override
    public Extension getExtension(final String namespaceUri)
    {
        return extensionMap.get(namespaceUri);
    }
}
