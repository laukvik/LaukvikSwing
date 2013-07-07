package org.laukvik.swing.graph;

import java.awt.Point;

public interface GraphModel {

	public int size( int graphIndex );
	
	public Point get( int index, int graphIndex );
	
	public int graphCount();
	
}