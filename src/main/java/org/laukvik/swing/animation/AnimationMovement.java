package org.laukvik.swing.animation;

import java.awt.Dimension;
import java.awt.Point;

public interface AnimationMovement {

	public Point getAnimationLocation( int frame, Point start, Dimension size );
	
}