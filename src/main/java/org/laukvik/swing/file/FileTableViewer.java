package org.laukvik.swing.file;

import java.io.File;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class FileTableViewer extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public FileTableViewer( File file ){
		super();
		getRootPane().putClientProperty( "Window.style", "small" );
		getRootPane().putClientProperty( "apple.awt.brushMetalLook", Boolean.TRUE );
		
		add( new JScrollPane( new FileTable( file ) ) );
		setSize( 640, 480 );
		setVisible( true );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new FileTableViewer( new File("/Users/morten") );
	}

}
