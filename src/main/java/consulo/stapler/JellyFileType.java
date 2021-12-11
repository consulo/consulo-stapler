package consulo.stapler;

import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.XmlLikeFileType;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.fileTypes.FileType;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 16.06.2015
 */
public class JellyFileType extends XmlLikeFileType
{
	public static final FileType INSTANCE = new JellyFileType();

	public JellyFileType()
	{
		super(XMLLanguage.INSTANCE);
	}

	@Nonnull
	@Override
	public String getId()
	{
		return "JELLY";
	}

	@Nonnull
	@Override
	public LocalizeValue getDescription()
	{
		return LocalizeValue.localizeTODO("*.jelly xml file");
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return "jelly";
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return AllIcons.FileTypes.Xml;
	}
}
