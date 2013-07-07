package org.laukvik.swing.ruler;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class RulerScrollPane extends JScrollPane implements MouseMotionListener {

	private static final long serialVersionUID = 1L;
	private Ruler h,v;

	public RulerScrollPane( JComponent component ) {
		super(component);
		setBackground( UIManager.getColor("Button.background") );
		setViewportBorder( BorderFactory.createEtchedBorder() );
		setBorder( null );
		h = new Ruler( Ruler.HORIZONTAL, Ruler.SYSTEM_PIXELS );
		h.setPreferredSize( new Dimension(5000,30) );
		
		v = new Ruler( Ruler.VERTICAL, Ruler.SYSTEM_PIXELS  );
		v.setPreferredSize( new Dimension(30,5000) );
		setColumnHeaderView( h );
		setRowHeaderView( v );
		setViewportView( component );
		setVisible(true);
		setEnabled(true);
	}
	
	public void setHorisontalMarker( int position ){
		h.setMarker( position );
	}
	
	public void setVerticalMarker( int position ){
		v.setMarker( position );
	}
	
	public void setMarkers( Point point ){
		setHorisontalMarker( point.x );
		setVerticalMarker( point.y );
	}
	
	public void markersChanged(){
		v.repaint();
		h.repaint();
	}

	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
		setMarkers( e.getPoint() );
		markersChanged();
	}
	
}