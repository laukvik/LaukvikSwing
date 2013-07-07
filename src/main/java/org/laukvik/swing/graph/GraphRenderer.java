package org.laukvik.swing.graph;

import java.awt.Dimension;
import java.awt.Graphics;

public interface GraphRenderer {

	public void paintGraph( GraphModel model, Graphics g );
	public Dimension getPreferredSize();
	
}