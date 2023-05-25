package consulo.stapler;

import com.intellij.java.language.codeInsight.AnnotationUtil;
import com.intellij.java.language.psi.PsiClass;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.editor.ImplicitUsageProvider;
import consulo.language.psi.PsiElement;

/**
 * @author VISTALL
 * @since 25/05/2023
 */
@ExtensionImpl
public class JenkinsImplicitUsageProvider implements ImplicitUsageProvider
{
	@Override
	public boolean isImplicitUsage(PsiElement psiElement)
	{
		return psiElement instanceof PsiClass psiClass && AnnotationUtil.isAnnotated(psiClass, "hudson.Extension", 0);
	}

	@Override
	public boolean isImplicitRead(PsiElement psiElement)
	{
		return false;
	}

	@Override
	public boolean isImplicitWrite(PsiElement psiElement)
	{
		return false;
	}
}
