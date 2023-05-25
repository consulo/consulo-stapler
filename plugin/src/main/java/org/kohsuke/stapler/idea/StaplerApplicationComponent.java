package org.kohsuke.stapler.idea;


import consulo.annotation.component.ExtensionImpl;
import consulo.language.psi.filter.AndFilter;
import consulo.language.psi.filter.ClassFilter;
import consulo.language.psi.meta.MetaDataContributor;
import consulo.language.psi.meta.MetaDataRegistrar;
import consulo.xml.psi.filters.position.NamespaceFilter;
import consulo.xml.psi.xml.XmlDocument;
import jakarta.inject.Singleton;
import org.kohsuke.stapler.idea.descriptor.XmlNSDescriptorImpl;

/**
 * @author Kohsuke Kawaguchi
 */
@ExtensionImpl
@Singleton
public class StaplerApplicationComponent implements MetaDataContributor
{
	public static final String DUMMY_SCHEMA_URL = "dummy-schema-url";

	@Override
	public void contributeMetaData(MetaDataRegistrar metaDataRegistrar)
	{
		// this is so that we can create an XmlFile whose getRootElement().getMetaData()
		// returns XmlNSDescriptorImpl. This is necessary to load schemas on the fly
		metaDataRegistrar.registerMetaData(
				new AndFilter(
						new ClassFilter(XmlDocument.class),
						new NamespaceFilter(DUMMY_SCHEMA_URL)),
				XmlNSDescriptorImpl::new
		);
	}
}
