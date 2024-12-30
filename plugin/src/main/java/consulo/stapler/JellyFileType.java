package consulo.stapler;

import consulo.application.AllIcons;
import consulo.localize.LocalizeValue;
import consulo.ui.image.Image;
import consulo.virtualFileSystem.fileType.FileType;
import consulo.xml.ide.highlighter.XmlLikeFileType;
import consulo.xml.lang.xml.XMLLanguage;

import jakarta.annotation.Nonnull;

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
