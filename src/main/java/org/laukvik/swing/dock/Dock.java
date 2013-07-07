package org.laukvik.swing.dock;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Dock extends JFrame {

	private static final long serialVersionUID = 3101142659675921142L;
	private JDesktopPane desk;
	public JSplitPane split;
	
	public Dock(){
		super();
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout( new BorderLayout() );
		desk = new JDesktopPane();
		
		split = new JSplitPane();
		split.setBorder( null );
		split.setRightComponent( desk );
		split.setDividerLocation( 250 );
		split.setResizeWeight( 0 );
		split.setEnabled( false );
		split.setDividerSize( 0 );
		
		add( split, BorderLayout.CENTER );
		

		setMaximizedBounds( GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds() );
		setExtendedState( JFrame.MAXIMIZED_BOTH);
		
//		setUndecorated( true );
	}
	
	public void setExplorer( JComponent component ){
		split.setLeftComponent( component );
	}
	
	public void setFullscreen( int index ){
		DockableInternalFrame frame = (DockableInternalFrame) desk.getComponent( index );
		split.setRightComponent( frame.getComponent() );
	}
	
	public void addDockable( Dockable dockable ){
		DockableInternalFrame frame = new DockableInternalFrame( dockable );
//		frame.add
		desk.add( frame );
	}
	
	public void removeDockable( Dockable dockable ){
		for (Component c : desk.getComponents()){
			if (c instanceof DockableInternalFrame){
				DockableInternalFrame frame = (DockableInternalFrame) c;
				if (frame.getComponent() == dockable){
					desk.remove(frame.getComponent());
				}
			}
		}
	}

}