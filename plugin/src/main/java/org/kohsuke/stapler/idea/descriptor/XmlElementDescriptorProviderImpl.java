package org.kohsuke.stapler.idea.descriptor;

import com.intellij.xml.XmlElementDescriptor;
import consulo.xml.psi.impl.source.xml.XmlElementDescriptorProvider;
import consulo.xml.psi.xml.XmlTag;

/**
 * Contributes {@link XmlElementDescriptorProvider} for Jelly tags.
 *
 * @author Kohsuke Kawaguchi
 */
public class XmlElementDescriptorProviderImpl implements XmlElementDescriptorProvider
{
    public XmlElementDescriptor getDescriptor(XmlTag tag) {
        XmlNSDescriptorImpl ns = XmlNSDescriptorImpl.get(tag);
        if(ns!=null)    return ns.getElementDescriptor(tag);
        return null;
    }
}
