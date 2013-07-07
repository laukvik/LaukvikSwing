package org.laukvik.swing.designer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class PaperDesignable implements Designable {
	
	String name;
	Point location;
	Dimension size;
	String tooltip;
	boolean visible;
	
	public PaperDesignable( String name ){
	}

	public boolean contains(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Point getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public Dimension getSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isResizeable() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void setIndex(int index) {
		// TODO Auto-generated method stub

	}

	public void setLocation(Point point) {
		// TODO Auto-generated method stub

	}

	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	public void setSize(Dimension size) {
	}

	public void setTooltip(String name) {
	}

	public void setVisible(boolean visible) {
	}

}
