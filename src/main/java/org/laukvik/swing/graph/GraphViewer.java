package org.laukvik.swing.graph;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class GraphViewer extends JFrame {


	private static final long serialVersionUID = 1L;
	GraphPanel panel;
	PointGraphModel model;
	
	public GraphViewer() {
		super();
		model = new PointGraphModel( 5 );
		
		for (int graphIndex=0; graphIndex<5; graphIndex++){
			for (int x=0; x<1500; x++){
				int y = (int) (Math.random()*100); 
				model.add( new Point( x*10, y ), graphIndex );
			}
		}

	
		panel = new GraphPanel( model );
		setLayout( new BorderLayout() );
		add( new JScrollPane(panel) );
		setSize( 400, 150 );
		setVisible( true );
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new GraphViewer();
	}

}
