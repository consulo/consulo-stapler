package org.kohsuke.stapler.idea;

import consulo.annotation.access.RequiredReadAction;
import consulo.language.Language;
import consulo.language.editor.inspection.LocalInspectionTool;
import consulo.language.editor.inspection.ProblemDescriptor;
import consulo.language.editor.inspection.ProblemsHolder;
import consulo.language.editor.inspection.scheme.InspectionManager;
import consulo.language.editor.rawHighlight.HighlightDisplayLevel;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiElementVisitor;
import consulo.language.util.ModuleUtilCore;
import consulo.localize.LocalizeValue;
import consulo.stapler.module.extension.StaplerModuleExtension;
import consulo.xml.language.XMLLanguage;
import consulo.xml.language.psi.XmlAttributeValue;
import consulo.xml.language.psi.XmlElementVisitor;
import consulo.xml.language.psi.XmlText;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import org.jetbrains.annotations.NonNls;

/**
 * {@link LocalInspectionTool} with enhancements to handle XML files.
 *
 * @author Kohsuke Kawaguchi
 */
public abstract class LocalXmlInspectionTool extends LocalInspectionTool {
    /**
     * Returns true if local inspection should kick in,
     * which is when the stapler facet is configured on the current module.
     */
    @RequiredReadAction
    protected static boolean shouldCheck(@Nonnull PsiElement psiElement) {
        return ModuleUtilCore.getExtension(psiElement, StaplerModuleExtension.class) != null;
    }

    @Nullable
    @Override
    public Language getLanguage() {
        return XMLLanguage.INSTANCE;
    }

    @Nonnull
    @Override
    public HighlightDisplayLevel getDefaultLevel() {
        return HighlightDisplayLevel.WARNING;
    }

    @Nonnull
    @Override
    public LocalizeValue getGroupDisplayName() {
        return LocalizeValue.localizeTODO("Jelly (Stapler)");
    }

    @NonNls
    @Nonnull
    public String getShortName() {
        return getClass().getSimpleName();
    }

    @Override
    public boolean isEnabledByDefault() {
        return true;
    }

    @Nonnull
    public PsiElementVisitor buildVisitor(@Nonnull final ProblemsHolder holder, final boolean isOnTheFly) {
        return new XmlElementVisitor() {
            @Override
            public void visitXmlText(XmlText text) {
                addDescriptors(checkXmlText(text, holder.getManager(), isOnTheFly));
            }

            @Override
            public void visitXmlAttributeValue(XmlAttributeValue value) {
                addDescriptors(checkXmlAttributeValue(value, holder.getManager(), isOnTheFly));
            }

            private void addDescriptors(final ProblemDescriptor[] descriptors) {
                for (ProblemDescriptor descriptor : descriptors) {
                    holder.registerProblem(descriptor);
                }
            }
        };
    }

    protected ProblemDescriptor[] checkXmlText(XmlText text, InspectionManager manager, boolean onTheFly) {
        return EMPTY_ARRAY;
    }

    protected ProblemDescriptor[] checkXmlAttributeValue(XmlAttributeValue text, InspectionManager manager, boolean onTheFly) {
        return EMPTY_ARRAY;
    }


    protected static final ProblemDescriptor[] EMPTY_ARRAY = new ProblemDescriptor[0];
}
