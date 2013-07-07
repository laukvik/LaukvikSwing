package org.laukvik.swing.designer2;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Vector;

public class DragDropLayout implements LayoutManager{
	
	private Vector<Component> components = new Vector<Component>();
	
	public DragDropLayout() {
	}
	
	public void log( String msg ){
		System.out.println( this.getClass().getName() + ": " + msg );
	}

	public void addLayoutComponent( String name, Component comp ) {
		components.add( comp );
	}

	public void layoutContainer( Container parent ) {
		log( "layoutContainer" );
		for (Component c: components){
			if (c instanceof DragDropComponent){
				@SuppressWarnings("unused")
				DragDropComponent ddc = (DragDropComponent) c;
//				c.setLocation( 25, 25  );
			}	
		}
	}

	public Dimension minimumLayoutSize( Container parent ) {
		return parent.getPreferredSize();
	}

	public Dimension preferredLayoutSize( Container parent ) {
		return parent.getPreferredSize();
	}

	public void removeLayoutComponent( Component comp ) {
		components.remove( comp );
	}

}