package org.lorethan.newsworm.core.extension;

public interface Extensionable
{
    Iterable<Extension> getExtensions();

    void addExtension(final Extension extension);
}
