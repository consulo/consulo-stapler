package org.kohsuke.stapler.idea.dom.model;

import consulo.xml.psi.xml.XmlAttribute;
import consulo.xml.psi.xml.XmlTag;

import jakarta.annotation.Nonnull;

/**
 * {@link XmlTag} that wraps &lt;st:attribute>.
 *
 * @author Kohsuke Kawaguchi
 */
public class AttributeTag extends TagWithHtmlContent {
    public AttributeTag(@Nonnull XmlTag tag) {
        super(tag);
    }

    /**
     * Return the name attribute value, except that if it's not specified,
     * this method returns "" to avoid NPE.
     */
    @Nonnull
    public String getName() {
        XmlAttribute a = tag.getAttribute("name");
        if(a==null)     return "";
        return a.getValue();
    }

    public boolean isRequired() {
        XmlAttribute a = tag.getAttribute("use");
        return a != null && a.getValue().equals("required");
    }
}
