package org.lorethan.newsworm.core.extension;

import org.jdom2.Element;
import org.jdom2.Namespace;

public interface ExtensionParser<T extends Extension>
{
    boolean canParse(Namespace namspace);

    T parse(Element element, Extension extension);
}
