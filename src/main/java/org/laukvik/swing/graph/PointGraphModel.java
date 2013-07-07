package org.laukvik.swing.graph;

import java.awt.Point;

public class PointGraphModel implements GraphModel {
	
	GraphPoints [] points;
	
	public PointGraphModel( int graphCount ) {
		this.points =  new GraphPoints[ graphCount ];
		for(int n=0; n<graphCount; n++){
			this.points[ n ] = new GraphPoints();
		}
	}
	
	public PointGraphModel() {
		this( 1 );
	}
	
	public Point add( Point point, int graphIndex ){
		this.points[ graphIndex ].add( point );
		return point;
	}
	
	public Point remove( Point point, int graphIndex ){
		this.points[ graphIndex ].remove( point );
		return point;
	}
	
	public void removeAll( int graphIndex ){
		this.points[ graphIndex ].removeAll();
	}

	public int size( int graphIndex ) {
		return points[ graphIndex ].size();
	}

	public Point get( int index, int graphIndex ) {
		return points[ graphIndex ].get( index );
	}

	public int graphCount() {
		return points.length;
	}

}