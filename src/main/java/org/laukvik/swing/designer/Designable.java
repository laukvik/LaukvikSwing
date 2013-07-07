package org.laukvik.swing.designer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public interface Designable {

	public Point getLocation();
	public void setLocation( Point point );
	public void setSize( Dimension size );
	public Dimension getSize();
	public void setVisible( boolean visible );
	public boolean isVisible();
	public boolean isResizeable();
	public boolean contains( Point point );
	public void setIndex( int index );
	public int getIndex();
	public void paint( Graphics g );
	public void setName( String name );
	public String getName();
	public void setTooltip( String name );
	public String getTooltip();
	
}