package org.laukvik.swing.designer;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class LineDesignerLinkRenderer implements DesignerLinkRenderer {

	public void paintConnection( DesignerLink link, Graphics g ){
		Graphics2D g2 = (Graphics2D) g;
		Composite comp = g2.getComposite();
		g2.setComposite( AlphaComposite.getInstance( AlphaComposite.SRC_OVER, 0.4f ) );
		g2.setStroke( new BasicStroke( 1f ) );
		g2.setColor( Color.black );
		g2.drawLine( link.start.x, link.start.y, link.end.x, link.end.y  );
		g2.setComposite( comp );
	}

}
