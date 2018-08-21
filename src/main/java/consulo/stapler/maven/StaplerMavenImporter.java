package consulo.stapler.maven;

import java.util.List;
import java.util.Map;

import org.jetbrains.idea.maven.importing.MavenModifiableModelsProvider;
import org.jetbrains.idea.maven.importing.MavenRootModelAdapter;
import org.jetbrains.idea.maven.project.MavenProject;
import org.jetbrains.idea.maven.project.MavenProjectChanges;
import org.jetbrains.idea.maven.project.MavenProjectsProcessorTask;
import org.jetbrains.idea.maven.project.MavenProjectsTree;
import com.intellij.openapi.module.Module;
import consulo.maven.importing.MavenImporterFromBuildPlugin;
import consulo.stapler.module.extension.StaplerModuleExtension;

/**
 * @author VISTALL
 * @since 16.06.2015
 */
public class StaplerMavenImporter extends MavenImporterFromBuildPlugin
{
	public StaplerMavenImporter()
	{
		super("org.kohsuke.stapler", "maven-stapler-plugin");
	}

	@Override
	public void preProcess(Module module,
			MavenProject mavenProject,
			MavenProjectChanges mavenProjectChanges,
			MavenModifiableModelsProvider mavenModifiableModelsProvider)
	{

	}

	@Override
	public void process(MavenModifiableModelsProvider mavenModifiableModelsProvider,
			Module module,
			MavenRootModelAdapter mavenRootModelAdapter,
			MavenProjectsTree mavenProjectsTree,
			MavenProject mavenProject,
			MavenProjectChanges mavenProjectChanges,
			Map<MavenProject, String> mavenProjectStringMap,
			List<MavenProjectsProcessorTask> mavenProjectsProcessorTasks)
	{
		enableModuleExtension(module, mavenModifiableModelsProvider, StaplerModuleExtension.class);
	}
}
