package org.laukvik.swing.graph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

public class DefaultGraphRenderer implements GraphRenderer {

	Dimension size;
	Color foreground = Color.black;
	Color background = Color.white;
	/* The height of each graph */
	int graphHeight = 102;
	
	
	public DefaultGraphRenderer() {
		size = new Dimension(0,0);
	}
	
	public void setForeground(Color foreground) {
		this.foreground = foreground;
	}
	
	public Color getForeground() {
		return foreground;
	}
	
	public void setBackground(Color background) {
		this.background = background;
	}
	
	public Color getBackground() {
		return background;
	}

	public void paintGraph( GraphModel model, Graphics graphics ) {
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

//		if (size != null){
//			g.setColor( background );
//			g.clearRect( 0,0, size.width, size.height );	
//		}
//		
//		

		
		int maxX = 0;
		int maxY = model.graphCount() * graphHeight;
		
		/* Paint lines at zero */
		g.setColor( Color.blue );
		g.setStroke( new BasicStroke( 1 ) );


		
		
		g.setColor( foreground );
		g.setStroke( new BasicStroke( 2 ) );
		
		
		
		for (int graphIndex = 0; graphIndex<model.graphCount(); graphIndex++){
			int graphY = graphIndex * graphHeight;
			if (model.size( graphIndex ) > 1){
				Point lastPoint = model.get( 0, graphIndex );
				for(int index=1; index<model.size( graphIndex ); index++){
					int x1 = lastPoint.x;
					int y1 = lastPoint.y + graphY;
					Point nextPoint = model.get( index, graphIndex  );
					int x2 = nextPoint.x;
					int y2 = nextPoint.y + graphY;
					lastPoint = nextPoint;
					g.drawLine( x1, y1, x2, y2 );
					
					
					
					if (x1 > maxX){
						maxX = x1;
					}

				}
			}
		}
		

		size = new Dimension(maxX,maxY);
	}
	


	public Dimension getPreferredSize() {
		return size;
	}

}