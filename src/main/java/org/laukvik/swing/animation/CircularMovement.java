package org.laukvik.swing.animation;

import java.awt.Dimension;
import java.awt.Point;

public class CircularMovement implements AnimationMovement {

	int x [];
	int y [];
	int steps;
	int r;
	
	public CircularMovement( int r  ){
		this(r,72);
	}
	
	public CircularMovement( int r, int steps ){
		this.steps = steps;
		this.r = r;
		int stepSize = 360 / steps;
		x = new int[ steps ];
		y = new int[ steps ];
		for (int n=0; n<steps; n++){
			y[n] = (int) (Math.cos( Math.toRadians( n*stepSize ) )*r) + 100;
			x[n] = (int) (Math.cos( Math.toRadians( n*stepSize+90 ) )*r) + 100;
		}
	}
	
	public Point getAnimationLocation( int frame, Point start, Dimension size ) {
		Point p  = new Point( start );
		int n = frame % x.length;
		p.x += x[ n ];
		p.y += y[ n ];
		return p;
	}

}