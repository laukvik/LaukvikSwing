package org.laukvik.swing.designer;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.laukvik.swing.platform.CrossPlatformUtilities;
import org.laukvik.swing.ruler.RulerScrollPane;

public class DesignerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	DesignerComponent designer;

	public DesignerFrame() {
		super();
		designer = new DesignerComponent();
		setTitle( "Designer" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout( new BorderLayout() );
		JSplitPane split = new JSplitPane();
		split.setLeftComponent( new RulerScrollPane(designer) );
		split.setRightComponent( new JScrollPane(designer.getLayersTable()) );
		split.setResizeWeight( 1 );
		add( split, BorderLayout.CENTER );
		pack();
		setExtendedState(getExtendedState() | MAXIMIZED_BOTH);
		setVisible( true );
		split.setDividerLocation( getWidth()-200 );
	}
	
	public DesignerComponent getDesigner() {
		return designer;
	}
	
	public DesignerModel getModel(){
		return designer.getModel();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CrossPlatformUtilities.setCrossPlatformProperties();
		new DesignerFrame();
	}

}