package org.laukvik.swing.designer2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.IOException;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Demo extends JFrame {

	private static final long serialVersionUID = 1L;
	private DragDropJPanel panel = new DragDropJPanel();
	private LayersPanel layers = new LayersPanel();

	public Demo() {
		JLabel morten = new JLabel("Morten");
		morten.setSize( new Dimension(200,50) );
		
		JLabel janne = new JLabel("Janne");
		janne.setSize( new Dimension(70,130) );
		
		JEditorPane google = new JEditorPane();
		try {
			google.setPage("http://www.google.com");
		} catch (IOException e) {
			e.printStackTrace();
		}
		google.setSize( new Dimension(50,300) );
		
		panel.add( morten, 200, 30 );
		panel.add( janne, 130,70 );
		panel.add( google, 40, 20 );
		
		
		add( layers, BorderLayout.SOUTH );
		
		setLayout( new BorderLayout() );
		add( panel  );
		setSize( 400, 400 );
		setVisible( true );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Demo();
	}

}
