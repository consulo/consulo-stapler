package org.mustbe.consulo.stapler.module.extension;

import org.consulo.module.extension.impl.ModuleExtensionImpl;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.roots.ModuleRootLayer;

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
