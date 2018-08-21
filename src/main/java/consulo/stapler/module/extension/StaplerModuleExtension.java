package consulo.stapler.module.extension;

import javax.annotation.Nonnull;

import consulo.module.extension.impl.ModuleExtensionImpl;
import consulo.roots.ModuleRootLayer;

/**
 * @author VISTALL
 * @since 16.06.2015
 */
public class StaplerModuleExtension extends ModuleExtensionImpl<StaplerModuleExtension>
{
	public StaplerModuleExtension(@Nonnull String id, @Nonnull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}
}
