package org.kohsuke.stapler.idea;

import com.intellij.codeInsight.daemon.GroupNames;
import com.intellij.codeInspection.InspectionManager;
import com.intellij.codeInspection.LocalQuickFix;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.psi.xml.XmlText;
import org.jetbrains.annotations.Nls;
import javax.annotation.Nonnull;

/**
 * @author Kohsuke Kawaguchi
 */
public class I18nInspection extends LocalXmlInspectionTool {
    @Nls @Nonnull
    public String getGroupDisplayName() {
        return GroupNames.BUGS_GROUP_NAME;
    }

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
