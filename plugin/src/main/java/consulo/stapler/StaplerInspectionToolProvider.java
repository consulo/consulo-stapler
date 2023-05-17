package consulo.stapler;

import org.kohsuke.stapler.idea.JexlInspection;
import com.intellij.codeInspection.InspectionToolProvider;

/**
 * @author VISTALL
 * @since 2018-08-21
 */
public class StaplerInspectionToolProvider implements InspectionToolProvider
{
	@Override
	public Class[] getInspectionClasses()
	{
		return new Class[]{JexlInspection.class};
	}
}
