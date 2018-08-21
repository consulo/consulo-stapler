package org.kohsuke.stapler.idea;

import org.kohsuke.stapler.idea.descriptor.XmlNSDescriptorImpl;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.psi.filters.AndFilter;
import com.intellij.psi.filters.ClassFilter;
import com.intellij.psi.filters.position.NamespaceFilter;
import com.intellij.psi.meta.MetaDataRegistrar;
import com.intellij.psi.xml.XmlDocument;

/**
 * @author Kohsuke Kawaguchi
 */
public class StaplerApplicationComponent implements ApplicationComponent
{
	public static final String DUMMY_SCHEMA_URL = "dummy-schema-url";

	@Override
	public void initComponent()
	{
		// this is so that we can create an XmlFile whose getRootElement().getMetaData()
		// returns XmlNSDescriptorImpl. This is necessary to load schemas on the fly
		MetaDataRegistrar.getInstance().registerMetaData(
				new AndFilter(
						new ClassFilter(XmlDocument.class),
						new NamespaceFilter(DUMMY_SCHEMA_URL)),
				XmlNSDescriptorImpl.class
		);

	}
}
