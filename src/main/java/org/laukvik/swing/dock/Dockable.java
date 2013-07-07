package org.laukvik.swing.dock;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JToolBar;

public interface Dockable {

	public JComponent getComponent();
	public JToolBar getToolbar();
	public String getTitle();
	public Icon getIcon();
	
}