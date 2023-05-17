package org.kohsuke.stapler.idea;

import javax.annotation.Nonnull;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlAttributeValue;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.ProcessingContext;
import consulo.psi.PsiPackage;

/**
 * Let IDEA know what some of the attribute values are referring to.
 *
 * <p>
 * This data drives Ctrl+Click. 
 *
 * @author Kohsuke Kawaguchi
 */
public class JellyTagLibReferenceProvider extends PsiReferenceProvider {
    /*
        The basic idea of ReferenceProvider is to create a reference speculatively,
        then the reference object will later try to find the target.
     */
    @Nonnull
    public PsiReference[] getReferencesByElement(@Nonnull PsiElement e, @Nonnull ProcessingContext processingContext) {
        /*
        This was the old way of marking references to tag files, but
        with the custom XmlNSDescriptor this is no longer necessary

        if (e instanceof XmlTag) {
            XmlTag t = (XmlTag)e;
            if(TagReference.isApplicable(t))
                return array(new TagReference(t));
        }
         */

        // is this <st:include page="..."> ?
        if (e instanceof XmlAttributeValue)
            return onAttributeValue((XmlAttributeValue) e);

        // this doesn't work, because XmlAttributeImpl doesn't call reference providers.
        // instead, XmlAttribute can only reference XmlAttributeDescriptor.getDeclaration()
//        if (e instanceof XmlAttribute) {
//            XmlAttribute a = (XmlAttribute) e;
//            PsiReference tagRef = a.getParent().getReference();
//            if(tagRef instanceof TagReference) {
//                return array(new TagAttributeReference((TagReference)tagRef,a));
//            }
//        }

        return PsiReference.EMPTY_ARRAY;
    }

    /**
     * Creates {@link PsiReference} for &lt;st:include page="..." /> attribute.
     */
    private PsiReference[] onAttributeValue(final XmlAttributeValue xav) {
        PsiElement _xa = xav.getParent();
        if (!(_xa instanceof XmlAttribute))
            return PsiReference.EMPTY_ARRAY;

        XmlAttribute a = (XmlAttribute) _xa;
        if(!a.getName().equals("page"))
            return PsiReference.EMPTY_ARRAY;

        XmlTag p = a.getParent();
        if(p==null)
            return PsiReference.EMPTY_ARRAY;

        if(!p.getLocalName().equals("include")
        || !p.getNamespace().equals("jelly:stapler"))
            return PsiReference.EMPTY_ARRAY;

        if(p.getAttribute("it")==null
        && p.getAttribute("from")==null) {
            // the page must be coming from the same object
            return array(new PsiReferenceBase<XmlAttributeValue>(xav,
                    TextRange.from(1,xav.getTextLength()-2)) {
                private final String page = xav.getValue();
                public PsiFile resolve() {
                    JavaPsiFacade javaFacade = JavaPsiFacade.getInstance(xav.getProject());
                    if(page.startsWith("/")) {
                        // absolute
                        String pkg = page.substring(1,page.lastIndexOf('/')).replace('/','.');
                        PsiJavaPackage jpkg = javaFacade.findPackage(pkg);
                        if(jpkg==null)  return null;
                        for (PsiDirectory dir : jpkg.getDirectories()) {
                            PsiFile f = dir.findFile(page.substring(page.lastIndexOf('/') + 1));
                            if(f!=null) return f;
                        }
                        return null;
                    } else {
                        // relative
                        PsiFile f = xav.getContainingFile();
                        PsiDirectory p = f.getParent();
                        if(p==null) return null;

                        // if the file is in the same place, that's the target.
                        PsiFile target = p.findFile(page);
                        if(target!=null) return target;

                        // maybe this Jelly file is a view of a Java class.
                        // In that case, go up the inheritance hierarchy until we find it
                        JavaDirectoryService jds = JavaDirectoryService.getInstance();
                        PsiPackage pkg = jds.getPackage(p);
                        if(pkg==null)   return null;

                        Module m = ModuleUtil.findModuleForFile(f.getVirtualFile(), f.getProject());
                        if(m==null)     return null;
                        PsiClass c = javaFacade.findClass(pkg.getQualifiedName(), GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(m));
                        if(c==null)     return null;
                        c = c.getSuperClass();
                        for( ; c!=null; c=c.getSuperClass()) {
                            pkg = javaFacade.findPackage(c.getQualifiedName());
                            if(pkg!=null) {
                                for (PsiDirectory dir : pkg.getDirectories()) {
                                    target = dir.findFile(page);
                                    if(target!=null) return target;
                                }
                            }
                        }
                        return null;
                    }
                }
            });
        }

        return PsiReference.EMPTY_ARRAY;
    }

    private PsiReference[] array(PsiReference ref) {
        return new PsiReference[] {ref};
    }
}
