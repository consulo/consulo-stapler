/**
 * @author VISTALL
 * @since 25/05/2023
 */
open module consulo.stapler
{
	requires consulo.ide.api;

	requires consulo.java;
	requires com.intellij.properties;
	requires com.intellij.xml;

	requires textile.j;
	requires commons.jexl;

	// TODO remove in future
	requires java.desktop;
}