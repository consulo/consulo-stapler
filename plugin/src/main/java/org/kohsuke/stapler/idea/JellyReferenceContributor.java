package org.kohsuke.stapler.idea;

import consulo.language.pattern.StandardPatterns;
import consulo.language.psi.PsiReferenceContributor;
import consulo.language.psi.PsiReferenceRegistrar;
import consulo.xml.psi.xml.XmlAttributeValue;

/**
 * @author Kohsuke Kawaguchi
 */
public abstract class JellyReferenceContributor extends PsiReferenceContributor
{
    @Override
    public void registerReferenceProviders(PsiReferenceRegistrar registrar) {
        JellyTagLibReferenceProvider p = new JellyTagLibReferenceProvider();
        registrar.registerReferenceProvider(StandardPatterns.instanceOf(XmlAttributeValue.class), p);
    }
}
