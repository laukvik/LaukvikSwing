package org.laukvik.swing.dock;

import java.awt.BorderLayout;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

public class DockableInternalFrame extends JInternalFrame implements Dockable {

	private static final long serialVersionUID = 3531477926956110692L;
	Dockable dockable;

	public DockableInternalFrame( Dockable dockable ){
		super();
		this.dockable = dockable;
		setLayout( new BorderLayout() );
		add( dockable.getComponent(), BorderLayout.CENTER );
		setLocation( 50, 50 );
		setClosable( true );
		setMaximizable( true );
		setDefaultCloseOperation( DockableInternalFrame.EXIT_ON_CLOSE );
		setSize( 400, 500 );
		
		setFrameIcon( dockable.getIcon() );
		setTitle( dockable.getTitle() );
		setVisible( true );
	}

	public JComponent getComponent() {
		return dockable.getComponent();
	}

	public JToolBar getToolbar() {
		return dockable.getToolbar();
	}

	public Icon getIcon() {
		return dockable.getIcon();
	}

}
