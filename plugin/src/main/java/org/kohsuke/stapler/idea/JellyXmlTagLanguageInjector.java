package org.kohsuke.stapler.idea;

import consulo.annotation.component.ExtensionImpl;
import consulo.document.util.TextRange;
import consulo.language.Language;
import consulo.language.inject.MultiHostInjector;
import consulo.language.inject.MultiHostRegistrar;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiLanguageInjectionHost;
import consulo.stapler.JellyFileType;
import consulo.xml.psi.XmlElementVisitor;
import consulo.xml.psi.xml.XmlTag;
import consulo.xml.psi.xml.XmlText;

import jakarta.annotation.Nonnull;

/**
 * Injects CSS and JavaScript to suitable places
 *
 * @author Kohsuke Kawaguchi
 */
@ExtensionImpl
public class JellyXmlTagLanguageInjector implements MultiHostInjector
{
	@jakarta.annotation.Nonnull
	@Override
	public Class<? extends PsiElement> getElementClass()
	{
		return XmlTag.class;
	}

	@Override
	public void injectLanguages(@Nonnull final MultiHostRegistrar registrar, @Nonnull PsiElement context)
	{
		if(context.getContainingFile().getFileType() != JellyFileType.INSTANCE)
		{
			return; // not a jelly file
		}

		// inject JavaScript to <script>
		if(context instanceof XmlTag)
		{
			/*
                IntelliJ reports an assertion error if the we didn't call any addPlace
                between startInjecting/doneInjection, so we need to call them lazily.
             */
			final boolean[] started = new boolean[1];

			XmlTag t = (XmlTag) context;
			if(!t.getName().equals("script"))
			{
				return; // not a script element
			}

			final Language language = Language.findLanguageByID("JavaScript");
			if(language == null)
			{
				return;
			}

			t.acceptChildren(new XmlElementVisitor()
			{
				@Override
				public void visitXmlText(XmlText text)
				{
					int len = text.getTextLength();
					if(len == 0)
					{
						return;
					}

					if(!started[0])
					{
						started[0] = true;
						registrar.startInjecting(language);
					}
					registrar.addPlace(null, null, (PsiLanguageInjectionHost) text, TextRange.from(0, len));
				}
			});
			if(started[0])
			{
				registrar.doneInjecting();
			}
		}
	}
}
