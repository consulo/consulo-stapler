package consulo.stapler.module.extension;

import consulo.module.content.layer.ModuleRootLayer;
import consulo.module.content.layer.extension.ModuleExtensionBase;

import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 16.06.2015
 */
public class StaplerModuleExtension extends ModuleExtensionBase<StaplerModuleExtension>
{
	public StaplerModuleExtension(@Nonnull String id, @Nonnull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}
}
