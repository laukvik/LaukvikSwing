package org.laukvik.swing.designer;

import java.awt.Point;
import java.util.Vector;

public class DefaultDesignerModel implements DesignerModel {
	
	Vector<Designable> items;
	Vector<DesignerModelListener> listeners;

	public DefaultDesignerModel(){
		items = new Vector<Designable>();
		listeners = new Vector<DesignerModelListener>();
	}
	
	public void addDesignerListener(DesignerModelListener listener) {
		listeners.add( listener );
	}
	
	public void removeDesignerListener(DesignerModelListener listener) {
		listeners.remove( listener );
	}

	public Point getLocation(int index) {
		return items.get( index ).getLocation();
	}

	public String getName(int index) {
		return items.get( index ).getName();
	}

	public Object getObject(int index) {
		return items.get(index);
	}

	public String getTooltip(int index) {
		return items.get( index ).getTooltip();
	}

	public boolean isResizable(int index) {
		return items.get( index ).isResizeable();
	}

	public boolean isVisible(int index) {
		return items.get( index ).isVisible();
	}

	public DesignerLink[] listLinks(int index) {
		return new DesignerLink[]{};
	}

	public void moveBack(int index) {
	}

	public void moveForward(int index) {
	}

	public void resize(int index, Point location, int width, int height) {
	}

	public void setLocation(Point location, int index) {

	}

	public int size() {
		return items.size();
	}

	public void setName(int index, String name) {
		items.get( index ).setName( name );
	}

	public void setVisible(boolean visible, int index) {
		items.get( index ).setVisible( visible );
	}

}