package org.kohsuke.stapler.idea;

import consulo.language.editor.inspection.LocalQuickFix;
import consulo.language.editor.inspection.ProblemDescriptor;
import consulo.language.editor.inspection.ProblemHighlightType;
import consulo.language.editor.inspection.scheme.InspectionManager;
import consulo.xml.psi.xml.XmlText;
import org.jetbrains.annotations.Nls;

import jakarta.annotation.Nonnull;

/**
 * @author Kohsuke Kawaguchi
 */
public class I18nInspection extends LocalXmlInspectionTool {
    @Nls
    @Nonnull
    public String getDisplayName() {
        return "Tests something";
    }

    protected ProblemDescriptor[] checkXmlText(XmlText text, InspectionManager manager, boolean onTheFly) {
        if(text.getText().equals("foo")) {
            return new ProblemDescriptor[] {
                manager.createProblemDescriptor(text,"Can't be foo", LocalQuickFix.EMPTY_ARRAY,
                ProblemHighlightType.GENERIC_ERROR_OR_WARNING )
            };
        }
        return EMPTY_ARRAY;
    }
}
