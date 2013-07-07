package org.laukvik.swing.animation;

import java.awt.Dimension;
import java.awt.Point;

public class StoppedMovement implements AnimationMovement {

	public StoppedMovement() {
	}
	
	public Point getAnimationLocation(int frame, Point start, Dimension size) {
		return new Point( start );
	}

}
