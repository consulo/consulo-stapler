package consulo.stapler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.XmlLikeFileType;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.fileTypes.FileType;
import consulo.ui.image.Image;

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
	public String getDescription()
	{
		return "*.jelly xml file";
	}

	@Nonnull
	@Override
	public String getDefaultExtension()
	{
		return "jelly";
	}

	@Nullable
	@Override
	public Image getIcon()
	{
		return AllIcons.FileTypes.Xml;
	}
}
