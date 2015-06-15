package org.mustbe.consulo.stapler.module.extension;

import javax.swing.JComponent;

import org.consulo.module.extension.MutableModuleExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mustbe.consulo.RequiredDispatchThread;
import com.intellij.openapi.roots.ModuleRootLayer;

/**
 * @author VISTALL
 * @since 16.06.2015
 */
public class StaplerMutableModuleExtension extends StaplerModuleExtension implements MutableModuleExtension<StaplerModuleExtension>
{
	public StaplerMutableModuleExtension(@NotNull String id, @NotNull ModuleRootLayer moduleRootLayer)
	{
		super(id, moduleRootLayer);
	}

	@RequiredDispatchThread
	@Nullable
	@Override
	public JComponent createConfigurablePanel(@NotNull Runnable updateOnCheck)
	{
		return null;
	}

	@Override
	public void setEnabled(boolean val)
	{
		myIsEnabled = val;
	}

	@Override
	public boolean isModified(@NotNull StaplerModuleExtension originalExtension)
	{
		return myIsEnabled != originalExtension.isEnabled();
	}
}
