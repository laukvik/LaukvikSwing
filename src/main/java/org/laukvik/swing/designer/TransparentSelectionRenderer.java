package org.laukvik.swing.designer;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TransparentSelectionRenderer implements DesignerSelectionRenderer {

	public void paintSelection( Rectangle selectionArea, Graphics g) {
		/* Paint the selected area */
		g.setColor( Color.black );
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite( AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f ) );
//		g2.fill( selectionArea );
		int rounded = 20;
		g2.fillRoundRect( selectionArea.x, selectionArea.y, selectionArea.width, selectionArea.height, rounded, rounded );
		g2.setComposite( AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f ) );
		g2.setColor( Color.BLACK );
		g2.drawRoundRect( selectionArea.x, selectionArea.y, selectionArea.width, selectionArea.height, rounded, rounded );
//		int x = (selectionArea.x + selectionArea.width) / 2;
//		int y = (selectionArea.y + selectionArea.height) / 2;
	}

}