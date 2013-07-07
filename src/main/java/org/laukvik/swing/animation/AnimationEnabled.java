package org.laukvik.swing.animation;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface AnimationEnabled {

	public void play( int frames, Dimension size );
	public boolean hasStopped();
	public void paint( Graphics2D g, Dimension size );
	
}