package org.kohsuke.stapler.idea;

import consulo.annotation.component.ExtensionImpl;
import consulo.document.util.TextRange;
import consulo.language.Language;
import consulo.language.inject.MultiHostInjector;
import consulo.language.inject.MultiHostRegistrar;
import consulo.language.psi.PsiElement;
import consulo.language.psi.PsiLanguageInjectionHost;
import consulo.stapler.JellyFileType;
import consulo.xml.psi.xml.XmlAttribute;
import consulo.xml.psi.xml.XmlAttributeValue;

import jakarta.annotation.Nonnull;

/**
 * Injects CSS and JavaScript to suitable places
 *
 * @author Kohsuke Kawaguchi
 */
@ExtensionImpl
public class JellyXmlAttributeLanguageInjector implements MultiHostInjector
{
	@jakarta.annotation.Nonnull
	@Override
	public Class<? extends PsiElement> getElementClass()
	{
		return XmlAttributeValue.class;
	}

	@Override
	public void injectLanguages(@Nonnull final MultiHostRegistrar registrar, @Nonnull PsiElement context)
	{
		if(context.getContainingFile().getFileType() != JellyFileType.INSTANCE)
		{
			return; // not a jelly file
		}

		// inject CSS to @style
		if(context instanceof XmlAttributeValue)
		{
			final XmlAttributeValue value = (XmlAttributeValue) context;

			if(!(value.getParent() instanceof XmlAttribute))
			{
				return; // not an XML attribute, probably an XML PI
			}

			XmlAttribute a = (XmlAttribute) value.getParent();
			if(!a.getName().equals("style"))
			{
				return; // not a style attribute
			}

			Language language = Language.findLanguageByID("CSS");
			if(language == null)
			{
				return;
			}

			registrar.startInjecting(language);
			registrar.addPlace("dummy_selector {", "}", (PsiLanguageInjectionHost) value, TextRange.from(1, value.getTextLength() - 2));
			registrar.doneInjecting();
		}
	}
}
