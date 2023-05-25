package consulo.stapler.module.extension;

import consulo.annotation.component.ExtensionImpl;
import consulo.localize.LocalizeValue;
import consulo.module.content.layer.ModuleExtensionProvider;
import consulo.module.content.layer.ModuleRootLayer;
import consulo.module.extension.ModuleExtension;
import consulo.module.extension.MutableModuleExtension;
import consulo.stapler.icon.StaplerIconGroup;
import consulo.ui.image.Image;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;

/**
 * @author VISTALL
 * @since 17/05/2023
 */
@ExtensionImpl
public class StaplerModuleExtensionProvider implements ModuleExtensionProvider<StaplerModuleExtension>
{
	@Nonnull
	@Override
	public String getId()
	{
		return "stapler";
	}

	@Nullable
	@Override
	public String getParentId()
	{
		return "java";
	}

	@Nonnull
	@Override
	public LocalizeValue getName()
	{
		return LocalizeValue.localizeTODO("Stapler");
	}

	@Nonnull
	@Override
	public Image getIcon()
	{
		return StaplerIconGroup.stapler();
	}

	@Nonnull
	@Override
	public ModuleExtension<StaplerModuleExtension> createImmutableExtension(@Nonnull ModuleRootLayer moduleRootLayer)
	{
		return new StaplerModuleExtension(getId(), moduleRootLayer);
	}

	@Nonnull
	@Override
	public MutableModuleExtension<StaplerModuleExtension> createMutableExtension(@Nonnull ModuleRootLayer moduleRootLayer)
	{
		return new StaplerMutableModuleExtension(getId(), moduleRootLayer);
	}
}
