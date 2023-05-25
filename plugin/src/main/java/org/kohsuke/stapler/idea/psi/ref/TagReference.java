package org.kohsuke.stapler.idea.psi.ref;

import com.intellij.java.language.psi.JavaPsiFacade;
import com.intellij.java.language.psi.PsiJavaPackage;
import consulo.document.util.TextRange;
import consulo.language.psi.PsiDirectory;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiReferenceBase;
import consulo.language.psi.scope.GlobalSearchScope;
import consulo.language.util.ModuleUtilCore;
import consulo.module.Module;
import consulo.xml.psi.xml.XmlFile;
import consulo.xml.psi.xml.XmlTag;

/**
 * {@link PsiReference} to a definition of a Jelly tag.
 *
 * @author Kohsuke Kawaguchi
 */
public final class TagReference extends PsiReferenceBase<XmlTag>
{
    public TagReference(XmlTag ref) {
        super(ref,calcTagNameRange(ref));
    }

    /**
     * Returns true if the given XML tag is a reference to a Jelly tag.
     */
    public static boolean isApplicable(XmlTag ref) {
        return ref.getNamespace().startsWith("/");
    }

    /**
     * Calculate the text range withtin {@link XmlTag} that represents
     * the tag name.
     */
    private static TextRange calcTagNameRange(XmlTag t) {
        // reference is only for the element name.
        // text range is relative to this element
        TextRange tr = t.getFirstChild().getNextSibling().getTextRange();
        return tr.shiftRight(-t.getTextRange().getStartOffset());

    }

    public XmlFile resolve() {
        String localName = myElement.getLocalName();
        String nsUri = myElement.getNamespace();
        if(nsUri.length()==0)   return null;

        Module m = ModuleUtilCore.findModuleForPsiElement(myElement);
        if(m==null) return null; // just trying to be defensive

        JavaPsiFacade javaPsi = JavaPsiFacade.getInstance(myElement.getProject());

        String pkgName = nsUri.substring(1).replace('/', '.');
        // this invocation below successfully finds packages that includes
        // invalid characters like 'a-b-c'
		PsiJavaPackage pkg = javaPsi.findPackage(pkgName);
        if(pkg==null)   return null;

        PsiDirectory[] dirs = pkg.getDirectories(GlobalSearchScope.moduleWithDependenciesAndLibrariesScope(m, false));

        for (PsiDirectory dir : dirs) {
            PsiFile tagFile = dir.findFile(localName + ".jelly");
            if (tagFile instanceof XmlFile)
                return (XmlFile) tagFile;
        }

//        // TODO: this is just a test
//        PsiManager psiManager = PsiManager.getInstance(myElement.getProject());
//        VirtualFile module = m.getModuleFile().getParent();
//        VirtualFile child = module.findChild(localName + ".txt");
//        if(child!=null)
//            return psiManager.findFile(child);

        return null;
    }

    public Object[] getVariants() {
//        // not sure how to use this
//        // -> this is used apparently as a quick completion.
//        // try typing "<a" then hit Ctrl+SPACE.
//        return new String[]{"abc","def","ghi"};
        return new Object[0];
    }
}
