package org.laukvik.swing.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GraphPanel extends JComponent {

	private static final long serialVersionUID = 1L;
	GraphModel model;
	GraphRenderer renderer;
	
	public GraphPanel(GraphModel model) {
		super();
		this.model = model;
		this.renderer = new DefaultGraphRenderer();
		setBackground( Color.black );
		setForeground( Color.white );
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		renderer.paintGraph( model, g );
		Dimension size = renderer.getPreferredSize();
		setPreferredSize( size );
	}
	
}