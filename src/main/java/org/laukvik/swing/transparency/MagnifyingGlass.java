package org.laukvik.swing.transparency;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class MagnifyingGlass extends JComponent implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	
	private Robot robot;
	private Component source = null;
	private Component magnifier = null;
	private Point point = new Point(-1, -1); // flag to say don't draw until we get a MouseMotionEvent
	private Dimension magnifierSize = new Dimension(200,200);
	private double zoom = 2;

	/**
	 * Creates a new Magnifier object which will look for changes in the 
	 * specified source component for mouse/graphics changes.
	 * 
	 * @param source Component to listen to
	 */
	public MagnifyingGlass( Component source  ){
		this.source = source;
		this.magnifier = this;
		try {
			robot = new Robot();
			source.addMouseMotionListener( this );
		} catch (AWTException awte) {
			System.err.println( "Can't get a Robot" );
			awte.printStackTrace();
		}
	}

	public void setSize( Dimension d ) {
		super.setSize(d);
		magnifierSize = d;
	}
	
	public void repaintGlass(){
		if (magnifier == null || magnifier.getGraphics() == null ){

		} else {
			paintGlass( magnifier.getGraphics() );
		}
	}
	
	public void paintGlass( Graphics g ){
		if ((robot == null) || (point.x == -1)) {
			g.setColor( UIManager.getColor("Panel.background") );
			g.fillRect(0, 0, magnifierSize.width, magnifierSize.height);
			return;
		}
		/* Get rectangle to screen grab */
		Rectangle grabRect = computeGrabRect();
		/* Grab it */
		BufferedImage grabImg = robot.createScreenCapture(grabRect);
		/* Resize it */
		Image scaleImg = grabImg.getScaledInstance( magnifierSize.width, magnifierSize.height, Image.SCALE_FAST);
		g.drawImage(scaleImg, 0, 0, null);
	}

	private Rectangle computeGrabRect() {
		// width, height are size of this comp / zoom
		int grabWidth = (int) ((double) magnifierSize.width / zoom);
		int grabHeight = (int) ((double) magnifierSize.height / zoom);
		// upper left corner is current point
		return new Rectangle(point.x-grabWidth/2, point.y-grabHeight/2, grabWidth, grabHeight);
	}

	public Dimension getPreferredSize() {
		return magnifierSize;
	}

	public Dimension getMinimumSize() {
		return magnifierSize;
	}

	public Dimension getMaximumSize() {
		return magnifierSize;
	}

	// MouseMotionListener implementations
	public void mouseMoved(MouseEvent e) {
		Point offsetPoint = source.getLocationOnScreen();
		e.translatePoint(offsetPoint.x, offsetPoint.y);
		point = e.getPoint();
		repaintGlass();
	}

	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	/**
	 * Sets the zoom factor
	 * 
	 * @param zoom
	 */
	public void setZoom( double zoom ) {
		this.zoom = zoom;
	}

	/**
	 * Sets the component to draw the magnifier graphics onto
	 * 
	 * @param magnifier
	 */
	public void setMagnifierComponent( Component magnifier ) {
		this.magnifier = magnifier;
	}
	
	public Component getMagnifierComponent(){
		return this.magnifier;
	}

}