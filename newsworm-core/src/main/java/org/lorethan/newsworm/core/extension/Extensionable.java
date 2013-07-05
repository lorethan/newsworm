package org.lorethan.newsworm.core.extension;

public interface Extensionable
{
    Iterable<Extension> getExtensions();

    void addExtension(Extension extension);

    Extension getExtension(String namespaceUri);
}
