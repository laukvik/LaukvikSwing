package org.laukvik.swing.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;

public class SimpleAnimable implements AnimationEnabled {

	AnimationMovement movement;
	Point location;
	Point pathLocation;
	int frameIndex = -1;
	
	public SimpleAnimable() {
		location = new Point( 50,50 );
		pathLocation = new Point( location );
		this.movement = new CircularMovement( 50 );
	}

	public boolean hasStopped() {
		return false;
	}

	public void paint(Graphics2D g, Dimension size) {
		g.setColor( Color.red );
		g.fillOval( pathLocation.x, pathLocation.y, 40, 40 );
	}

	public void play(int frames, Dimension size) {
		frameIndex += frames;
		pathLocation = movement.getAnimationLocation(frameIndex, location , size );
	}

}