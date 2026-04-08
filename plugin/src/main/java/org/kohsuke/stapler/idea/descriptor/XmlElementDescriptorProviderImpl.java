package org.kohsuke.stapler.idea.descriptor;

import consulo.xml.descriptor.XmlElementDescriptor;
import consulo.xml.language.psi.XmlTag;
import consulo.xml.psi.impl.source.xml.XmlElementDescriptorProvider;

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
