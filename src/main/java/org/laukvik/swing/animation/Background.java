package org.laukvik.swing.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import javax.swing.SwingConstants;

public class Background implements AnimationEnabled {
	
	GradientPaint gp;
	Color from, target;
	int counter = 0;
	int turnaround = 50;
	int direction = SwingConstants.EAST;
	
	public Background(){
		from = new Color(83,30,74);
		target = new Color(153,55,120);
	}

	public boolean hasStopped() {
		return false;
	}

	public void paint(Graphics2D g, Dimension size) {
		gp = new GradientPaint(0, 0, from, 0, size.height, target, true);		
		g.setPaint( gp );
		g.fillRect(0,0, size.width, size.height );
	}

	public void play(int frames, Dimension size) {
		if (direction == SwingConstants.EAST){
			if (counter >= turnaround){
				direction = SwingConstants.WEST;
			} else {
				counter++;
			}
		} else {
			if (counter <= 0){
				direction = SwingConstants.EAST;
				counter = 0;
			} else {
				counter--;
			}			
		}
//		System.out.println( counter );
		from = new Color( counter, counter, counter );
	}

}