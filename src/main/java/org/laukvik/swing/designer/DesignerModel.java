package org.laukvik.swing.designer;

import java.awt.Point;

public interface DesignerModel {

	public int size();
	public Point getLocation( int index );
	public void setLocation( Point location, int index );
	public void addDesignerListener( DesignerModelListener listener );
	public void removeDesignerListener( DesignerModelListener listener );
	public DesignerLink [] listLinks( int index );
	public String getTooltip( int index );
	public String getName( int index );
	public void setName( int index, String name );
	public Object getObject( int index );
	
	public void moveBack( int index );
	public void moveForward( int index );
	public boolean isResizable( int index );
	public boolean isVisible( int index );
	public void setVisible( boolean visible, int index );
	public void resize( int index, Point location, int width, int height );
	
}