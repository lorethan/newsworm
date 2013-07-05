package org.lorethan.newsworm.core.extension;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExtensionable implements Extensionable
{
    private final List<Extension> extensions = new ArrayList<>();

    @Override
    public Iterable<Extension> getExtensions()
    {
        return extensions;
    }

    @Override
    public void addExtension(final Extension extension)
    {
        extensions.add(extension);
    }
}
