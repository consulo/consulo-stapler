package consulo.stapler.module.extension;

import javax.annotation.Nonnull;

import consulo.module.extension.MutableModuleExtension;
import consulo.roots.ModuleRootLayer;

/**
 * @author VISTALL
 * @since 16.06.2015
 */
public class StaplerMutableModuleExtension extends StaplerModuleExtension implements MutableModuleExtension<StaplerModuleExtension>
{
	public StaplerMutableModuleExtension(@Nonnull String id, @Nonnull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}

	@Override
	public void setEnabled(boolean val)
	{
		myIsEnabled = val;
	}

	@Override
	public boolean isModified(@Nonnull StaplerModuleExtension originalExtension)
	{
		return myIsEnabled != originalExtension.isEnabled();
	}
}
