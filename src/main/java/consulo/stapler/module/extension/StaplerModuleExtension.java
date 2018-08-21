package consulo.stapler.module.extension;

import org.jetbrains.annotations.NotNull;
import consulo.extension.impl.ModuleExtensionImpl;
import consulo.roots.ModuleRootLayer;

/**
 * @author VISTALL
 * @since 16.06.2015
 */
public class StaplerModuleExtension extends ModuleExtensionImpl<StaplerModuleExtension>
{
	public StaplerModuleExtension(@NotNull String id, @NotNull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}
}
