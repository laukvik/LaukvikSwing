package org.laukvik.swing.graph;

import java.awt.Point;
import java.util.Vector;

public class GraphPoints {
	
	Vector<Point> points;

	public GraphPoints(){
		this.points = new Vector<Point>();
	}
	
	public void add( Point point ){
		this.points.add( point );
	}
	
	public void remove( Point point ){
		this.points.remove( point );
	}
	
	public void removeAll(){
		this.points.removeAllElements();
	}
	
	public int size(){
		return this.points.size();
	}
	
	public Point get( int index ){
		return this.points.get( index );
	}
	
}