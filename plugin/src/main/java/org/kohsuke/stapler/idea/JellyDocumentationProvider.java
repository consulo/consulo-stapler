package org.kohsuke.stapler.idea;

import com.intellij.xml.XmlAttributeDescriptor;
import com.intellij.xml.XmlElementDescriptor;
import consulo.annotation.component.ExtensionImpl;
import consulo.language.Language;
import consulo.language.editor.documentation.LanguageDocumentationProvider;
import consulo.language.psi.PsiElement;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.xml.lang.xml.XMLLanguage;
import consulo.xml.psi.xml.XmlAttribute;
import consulo.xml.psi.xml.XmlTag;
import jakarta.annotation.Nonnull;
import org.kohsuke.stapler.idea.descriptor.XmlAttributeDescriptorImpl;
import org.kohsuke.stapler.idea.descriptor.XmlElementDescriptorImpl;
import org.kohsuke.stapler.idea.dom.model.DocumentationTag;

/**
 * @author Kohsuke Kawaguchi
 */
@ExtensionImpl
public class JellyDocumentationProvider implements LanguageDocumentationProvider
{
	/**
	 * This method is called upon Ctrl+Q on usages.
	 *
	 * @param element This represents the declaration, not the usage.
	 * @param usage   This is where Ctrl+Q is invoked.
	 */
	public String generateDoc(PsiElement element, PsiElement usage)
	{
		PsiElement p = usage.getParent();
		if(p instanceof XmlTag)
		{
			XmlTag t = (XmlTag) p;
			XmlElementDescriptor d = t.getDescriptor();
			if(d instanceof XmlElementDescriptorImpl)
			{
				XmlElementDescriptorImpl dd = (XmlElementDescriptorImpl) d;
				DocumentationTag m = dd.getModel();
				if(m == null)
				{
					return null;
				}
				return m.generateHtmlDoc();
			}
		}
		else if(p instanceof XmlAttribute)
		{
			XmlAttribute a = (XmlAttribute) p;
			XmlAttributeDescriptor ad = a.getDescriptor();
			if(ad instanceof XmlAttributeDescriptorImpl)
			{
				XmlAttributeDescriptorImpl o = (XmlAttributeDescriptorImpl) ad;
				return o.getModel().generateHtmlDoc();
			}
		}
		else
		{
			// if the nearest namespaced tag is <st:documentation> or <st:attribute>,
			// render that document. This is just like what happens when you hit Ctrl+Q
			// inside javadoc.
			for(XmlTag tag = PsiTreeUtil.getParentOfType(usage, XmlTag.class);
					tag != null;
					tag = tag.getParentTag())
			{
				String ns = tag.getNamespace();
				if(ns.equals("jelly:stapler"))
				{
					String ln = tag.getLocalName();
					if(ln.equals("documentation") || ln.equals("attribute"))
					{
						// to be pedantic, it could be new AttributeTag as well,
						// but that doesn't make any difference in the end result.
						return new DocumentationTag(tag).generateHtmlDoc();
					}
				}
				if(!ns.equals(""))
				{
					break;
				}
			}
		}


		return null;
	}

	@Nonnull
	@Override
	public Language getLanguage()
	{
		return XMLLanguage.INSTANCE;
	}
}
