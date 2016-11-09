package consulo.stapler;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.intellij.icons.AllIcons;
import com.intellij.ide.highlighter.XmlLikeFileType;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.fileTypes.FileType;

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

	@NotNull
	@Override
	public String getName()
	{
		return "JELLY";
	}

	@NotNull
	@Override
	public String getDescription()
	{
		return "*.jelly xml file";
	}

	@NotNull
	@Override
	public String getDefaultExtension()
	{
		return "jelly";
	}

	@Nullable
	@Override
	public Icon getIcon()
	{
		return AllIcons.FileTypes.Xml;
	}
}
