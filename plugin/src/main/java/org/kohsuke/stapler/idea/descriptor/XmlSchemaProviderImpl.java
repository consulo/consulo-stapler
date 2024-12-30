package org.kohsuke.stapler.idea.descriptor;

import com.intellij.xml.XmlNSDescriptor;
import com.intellij.xml.XmlSchemaProvider;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.psi.PsiDirectory;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiFileFactory;
import consulo.module.Module;
import consulo.util.dataholder.Key;
import consulo.xml.psi.xml.XmlFile;
import org.kohsuke.stapler.idea.StaplerApplicationComponent;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 *
 * <p>
 * This is not to be confused with W3C XML Schema. Rather,
 * it's a mechanism for locating a structure definition for a given URI
 * as {@link XmlFile}, such that its root metadata is {@link XmlNSDescriptor}. 
 *
 * @author Kohsuke Kawaguchi
 */
@ExtensionImpl
public class XmlSchemaProviderImpl extends XmlSchemaProvider {
    public XmlFile getSchema(@Nonnull String url, @Nullable Module module, @Nonnull PsiFile baseFile) {
        XmlNSDescriptorImpl d = XmlNSDescriptorImpl.get(url, module);
        if(d==null)     return null;

        XmlFile pseudoSchema = (XmlFile) PsiFileFactory.getInstance(module.getProject()).createFileFromText(
                url + ".xml", "<schema uri=\"" + url + "\" xmlns='" + StaplerApplicationComponent.DUMMY_SCHEMA_URL + "'>");
        pseudoSchema.putUserData(MODULE,d.getDir());
        return pseudoSchema;
    }

    /**
     * Before the {@link #getSchema(String, Module, PsiFile)}  method is called,
     * this method is called, presumably to see if this provider is applicable
     * to the file.
     *
     * <p>
     * In this case we are only interetsed in references from inside Jelly files,
     * so check that.
     */
    @Override
    public boolean isAvailable(@Nonnull XmlFile file) {
        return file.getName().endsWith(".jelly");
    }

    /*package*/ static final Key<PsiDirectory> MODULE = Key.create(PsiDirectory.class.getName());
}
