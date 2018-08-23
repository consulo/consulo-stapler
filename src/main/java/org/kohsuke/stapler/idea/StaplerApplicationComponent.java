package org.kohsuke.stapler.idea;

import javax.inject.Singleton;

import org.kohsuke.stapler.idea.descriptor.XmlNSDescriptorImpl;
import com.intellij.psi.filters.AndFilter;
import com.intellij.psi.filters.ClassFilter;
import com.intellij.psi.filters.position.NamespaceFilter;
import com.intellij.psi.meta.MetaDataRegistrar;
import com.intellij.psi.xml.XmlDocument;

/**
 * @author Kohsuke Kawaguchi
 */
@Singleton
public class StaplerApplicationComponent
{
	public static final String DUMMY_SCHEMA_URL = "dummy-schema-url";

	public StaplerApplicationComponent()
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
