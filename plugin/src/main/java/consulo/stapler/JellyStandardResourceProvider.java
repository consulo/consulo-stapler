package consulo.stapler;

import consulo.annotation.component.ExtensionImpl;
import consulo.xml.javaee.ResourceRegistrar;
import consulo.xml.javaee.StandardResourceProvider;

/**
 * @author VISTALL
 * @since 25/05/2023
 */
@ExtensionImpl
public class JellyStandardResourceProvider implements StandardResourceProvider
{
	@Override
	public void registerResources(ResourceRegistrar register)
	{
		register.addStdResource("jelly:ant", "/standardSchemas/ant.xsd", getClass());
		register.addStdResource("jelly:antlr", "/standardSchemas/antlr.xsd", getClass());
		register.addStdResource("jelly:bean", "/standardSchemas/bean.xsd", getClass());
		register.addStdResource("jelly:beanshell", "/standardSchemas/beanshell.xsd", getClass());
		register.addStdResource("jelly:betwixt", "/standardSchemas/betwixt.xsd", getClass());
		register.addStdResource("jelly:bsf", "/standardSchemas/bsf.xsd", getClass());
		register.addStdResource("jelly:core", "/standardSchemas/core.xsd", getClass());
		register.addStdResource("jelly:define", "/standardSchemas/define.xsd", getClass());
		register.addStdResource("jelly:dynabean", "/standardSchemas/dynabean.xsd", getClass());
		register.addStdResource("jelly:email", "/standardSchemas/email.xsd", getClass());
		register.addStdResource("jelly:fmt", "/standardSchemas/fmt.xsd", getClass());
		register.addStdResource("jelly:html", "/standardSchemas/html.xsd", getClass());
		register.addStdResource("jelly:http", "/standardSchemas/http.xsd", getClass());
		register.addStdResource("jelly:interaction", "/standardSchemas/interaction.xsd", getClass());
		register.addStdResource("jelly:jaxme", "/standardSchemas/jaxme.xsd", getClass());
		register.addStdResource("jelly:jetty", "/standardSchemas/jetty.xsd", getClass());
		register.addStdResource("jelly:jface", "/standardSchemas/jface.xsd", getClass());
		register.addStdResource("jelly:jms", "/standardSchemas/jms.xsd", getClass());
		register.addStdResource("jelly:jmx", "/standardSchemas/jmx.xsd", getClass());
		register.addStdResource("jelly:jsl", "/standardSchemas/jsl.xsd", getClass());
		register.addStdResource("jelly:junit", "/standardSchemas/junit.xsd", getClass());
		register.addStdResource("jelly:log", "/standardSchemas/log.xsd", getClass());
		register.addStdResource("jelly:memory", "/standardSchemas/memory.xsd", getClass());
		register.addStdResource("jelly:ojb", "/standardSchemas/ojb.xsd", getClass());
		register.addStdResource("jelly:quartz", "/standardSchemas/quartz.xsd", getClass());
		register.addStdResource("jelly:regexp", "/standardSchemas/regexp.xsd", getClass());
		register.addStdResource("jelly:soap", "/standardSchemas/soap.xsd", getClass());
		register.addStdResource("jelly:sql", "/standardSchemas/sql.xsd", getClass());
		register.addStdResource("jelly:swing", "/standardSchemas/swing.xsd", getClass());
		register.addStdResource("jelly:swt", "/standardSchemas/swt.xsd", getClass());
		register.addStdResource("jelly:threads", "/standardSchemas/threads.xsd", getClass());
		register.addStdResource("jelly:util", "/standardSchemas/util.xsd", getClass());
		register.addStdResource("jelly:validate", "/standardSchemas/validate.xsd", getClass());
		register.addStdResource("jelly:velocity", "/standardSchemas/velocity.xsd", getClass());
		register.addStdResource("jelly:xml", "/standardSchemas/xml.xsd", getClass());
		register.addStdResource("jelly:xmlunit", "/standardSchemas/xmlunit.xsd", getClass());
		register.addStdResource("jelly:stapler", "/standardSchemas/stapler.xsd", getClass());

		register.addStdResource("/lib/form", "/standardSchemas/taglib/form.xsd", getClass());
		register.addStdResource("/lib/hudson", "/standardSchemas/taglib/hudson.xsd", getClass());
		register.addStdResource("/lib/layout", "/standardSchemas/taglib/layout.xsd", getClass());
		register.addStdResource("/lib/newFormList", "/standardSchemas/taglib/newFormList.xsd", getClass());
		register.addStdResource("/lib/project", "/standardSchemas/taglib/project.xsd", getClass());
		register.addStdResource("/lib/test", "/standardSchemas/taglib/test.xsd", getClass());
	}
}
