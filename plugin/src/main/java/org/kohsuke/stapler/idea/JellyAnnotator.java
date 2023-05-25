package org.kohsuke.stapler.idea;

import com.intellij.xml.XmlElementDescriptor;
import com.intellij.xml.XmlNSDescriptor;
import com.intellij.xml.impl.schema.AnyXmlElementDescriptor;
import consulo.language.editor.annotation.AnnotationHolder;
import consulo.language.editor.annotation.Annotator;
import consulo.language.editor.annotation.HighlightSeverity;
import consulo.language.psi.PsiElement;
import consulo.stapler.JellyFileType;
import consulo.xml.psi.xml.XmlTag;

/**
 * Additional Jelly-specific {@link Annotator}.
 *
 * <p>
 * Schemas generated from Jelly tag libraries use &lt;xs:any />,
 * so any nested element is deemed legal by default annotation.
 *
 * <p>
 * This {@link Annotator} ensures that such elements are marked
 * as errors.
 *
 * @author Kohsuke Kawaguchi
 */
public class JellyAnnotator implements Annotator {
    public void annotate(PsiElement psi, AnnotationHolder holder) {
        if (psi instanceof XmlTag) {
            XmlTag tag = (XmlTag) psi;

            if(tag.getContainingFile().getFileType() != JellyFileType.INSTANCE)
                return; // only do this in Jelly files

            // for elements
            XmlNSDescriptor ns = tag.getDescriptor().getNSDescriptor();
            XmlElementDescriptor e = ns.getElementDescriptor(tag);
            if(e instanceof AnyXmlElementDescriptor) {
                PsiElement startTagName = tag.getFirstChild().getNextSibling();
                holder.newAnnotation(HighlightSeverity.ERROR,"Undefined element").range(startTagName).create();
            }
        }
    }
}
