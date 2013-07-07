package org.laukvik.swing.designer;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Shape;

public interface DesignerRenderer {

	public void paintDiagram( Object value, Point location, boolean selected, boolean dragging, DesignerComponent diagram, Graphics g );
	public Shape getShape( Point location, Object value  );
	
}