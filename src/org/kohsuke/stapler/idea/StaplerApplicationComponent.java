package org.kohsuke.stapler.idea;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.kohsuke.stapler.idea.descriptor.XmlNSDescriptorImpl;
import com.intellij.codeInspection.InspectionToolProvider;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.psi.filters.AndFilter;
import com.intellij.psi.filters.ClassFilter;
import com.intellij.psi.filters.position.NamespaceFilter;
import com.intellij.psi.meta.MetaDataRegistrar;
import com.intellij.psi.xml.XmlDocument;

/**
 * @author Kohsuke Kawaguchi
 */
public class StaplerApplicationComponent implements ApplicationComponent, InspectionToolProvider {
	public static final String DUMMY_SCHEMA_URL = "dummy-schema-url";

    @NonNls @NotNull
    public String getComponentName() {
        return getClass().getSimpleName();
    }

    public void initComponent() {

        // this is so that we can create an XmlFile whose getRootElement().getMetaData()
        // returns XmlNSDescriptorImpl. This is necessary to load schemas on the fly
        MetaDataRegistrar.getInstance().registerMetaData(
            new AndFilter(
                new ClassFilter(XmlDocument.class),
                new NamespaceFilter(DUMMY_SCHEMA_URL)),
            XmlNSDescriptorImpl.class
        );

    }

    public void disposeComponent() {
        // noop
    }

    public Class[] getInspectionClasses() {
        return new Class[]{JexlInspection.class};
    }

}
