package org.laukvik.swing.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class DefaultGridPainter implements GridPainter {

	BufferedImage cache = null;
	
	public DefaultGridPainter() {
		
	}
	
	public void paint(Graphics g, int width, int height, Color gridColor, Component c ) {

		Graphics2D g2d = (Graphics2D) g;
		cache = getOne( Color.white, gridColor, width, height );
		
		Rectangle2D tr = new Rectangle2D.Double(0, 0, width, height );
		TexturePaint tp = new TexturePaint( cache, tr );
		
        g2d.setPaint( tp );
        g2d.fillRect( 0, 0, c.getWidth(), c.getHeight() );
		
	}
	
	public final static BufferedImage getOneChecker( Color brite, Color dark ){
		BufferedImage checker = new BufferedImage( 20, 20, BufferedImage.TYPE_INT_RGB );
		Graphics g = checker.getGraphics();
		g.setColor( brite );
		g.fillRect( 0,0,20,20 );
		g.setColor( dark );
		g.fillRect( 0,0,10,10 );
		g.setColor( dark );
		g.fillRect( 10,10,20,20 );
		return checker;
	}

	public final static BufferedImage getOne( Color brite, Color dark, int width, int height ){
		BufferedImage checker = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
		Graphics g = checker.getGraphics();
		g.setColor( brite );
		g.fillRect( 0,0, width, height );
		g.setColor( dark );
		g.drawLine( 0, height-1, width, height-1  );
		g.fillRect( width-1,0, width-1, height );

		return checker;
	}

}
