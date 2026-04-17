/**
 * @author VISTALL
 * @since 25/05/2023
 */
open module consulo.stapler
{
	requires consulo.application.api;
	requires consulo.base.icon.library;
	requires consulo.code.editor.api;
	requires consulo.disposer.api;
	requires consulo.document.api;
	requires consulo.language.api;
	requires consulo.language.editor.api;
	requires consulo.language.editor.ui.api;
	requires consulo.localize.api;
	requires consulo.module.api;
	requires consulo.module.content.api;
	requires consulo.project.api;
	requires consulo.ui.api;
	requires consulo.ui.ex.api;
	requires consulo.ui.ex.awt.api;
	requires consulo.util.dataholder;
	requires consulo.util.lang;
	requires consulo.virtual.file.system.api;

	requires consulo.java;
	requires com.intellij.properties;
	requires com.intellij.xml.api;
	requires com.intellij.xml;

	requires textile.j;
	requires commons.jexl;

	// TODO remove in future
	requires java.desktop;
}
