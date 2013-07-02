package org.lorethan.newsworm.core.extension;

import org.jdom2.Element;

public interface ExtensionParser<T extends Extension>
{
    boolean canParse(Element element);

    T parse(Element element);
}
