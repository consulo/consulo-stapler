package consulo.stapler.editor;

import com.intellij.java.language.JavaLanguage;
import com.intellij.java.language.psi.PsiAnonymousClass;
import com.intellij.java.language.psi.PsiClass;
import com.intellij.java.language.psi.PsiIdentifier;
import com.intellij.java.language.psi.util.PsiUtil;
import consulo.annotation.access.RequiredReadAction;
import consulo.annotation.component.ExtensionImpl;
import consulo.codeEditor.markup.GutterIconRenderer;
import consulo.language.Language;
import consulo.language.content.ProductionResourceContentFolderTypeProvider;
import consulo.language.editor.Pass;
import consulo.language.editor.gutter.LineMarkerInfo;
import consulo.language.editor.gutter.LineMarkerProvider;
import consulo.language.editor.ui.PopupNavigationUtil;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiFile;
import consulo.language.psi.PsiManager;
import consulo.module.Module;
import consulo.module.content.ModuleRootManager;
import consulo.platform.base.icon.PlatformIconGroup;
import consulo.stapler.module.extension.StaplerModuleExtension;
import consulo.ui.ex.RelativePoint;
import consulo.ui.ex.popup.JBPopup;
import consulo.util.lang.StringUtil;
import consulo.virtualFileSystem.VirtualFile;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author VISTALL
 * @since 25/05/2023
 */
@ExtensionImpl
public class JellyLineMarkerProvider implements LineMarkerProvider
{
	@Override
	@RequiredReadAction
	public boolean isAvailable(@Nonnull PsiFile file)
	{
		Module module = file.getModule();
		return module != null && module.getExtension(StaplerModuleExtension.class) != null;
	}

	@RequiredReadAction
	@Nullable
	@Override
	public LineMarkerInfo getLineMarkerInfo(@Nonnull PsiElement psiElement)
	{
		if(psiElement instanceof PsiIdentifier && psiElement.getParent() instanceof PsiClass psiClass)
		{
			if(psiClass instanceof PsiAnonymousClass || PsiUtil.isInnerClass(psiClass))
			{
				return null;
			}

			List<PsiFile> viewFiles = findViewFiles(psiClass);
			if(viewFiles.isEmpty())
			{
				return null;
			}

			return new LineMarkerInfo<>(psiElement, psiElement.getTextRange(), PlatformIconGroup.nodesWrite_access(), Pass.LINE_MARKERS, null, (mouseEvent, element) ->
			{
				PsiElement parent = element.getParent();
				if(!(parent instanceof PsiClass javaPsiClass))
				{
					return;
				}

				List<PsiFile> anotherFiles = findViewFiles(javaPsiClass);
				JBPopup popup = PopupNavigationUtil.getPsiElementPopup(anotherFiles.toArray(new PsiElement[0]), "Navigate to view");
				popup.show(new RelativePoint(mouseEvent));
			}, GutterIconRenderer.Alignment.RIGHT);
		}
		return null;
	}

	@RequiredReadAction
	private List<PsiFile> findViewFiles(PsiClass psiClass)
	{
		String qualifiedName = psiClass.getQualifiedName();
		if(StringUtil.isEmptyOrSpaces(qualifiedName))
		{
			return List.of();
		}

		Module module = psiClass.getModule();
		if(module == null)
		{
			return List.of();
		}

		VirtualFile[] files = ModuleRootManager.getInstance(module).getContentFolderFiles(it -> it == ProductionResourceContentFolderTypeProvider.getInstance());

		PsiManager psiManager = PsiManager.getInstance(psiClass.getProject());

		List<PsiFile> result = new ArrayList<>();
		for(VirtualFile file : files)
		{
			VirtualFile child = file.findFileByRelativePath(qualifiedName.replace(".", "/"));
			if(child == null || !child.isDirectory())
			{
				continue;
			}

			for(VirtualFile childFile : child.getChildren())
			{
				if("groovy".equals(childFile.getExtension()) || "jelly".equals(childFile.getExtension()))
				{
					PsiFile resultFile = psiManager.findFile(childFile);
					if(resultFile != null)
					{
						result.add(resultFile);
					}
				}
			}
		}
		return result;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return JavaLanguage.INSTANCE;
	}
}
