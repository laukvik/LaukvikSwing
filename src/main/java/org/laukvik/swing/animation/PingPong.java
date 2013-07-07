package org.laukvik.swing.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Random;

import javax.swing.SwingConstants;

public class PingPong implements AnimationEnabled {
	
	Point p;
	int horisontalDirection = SwingConstants.EAST;
	int verticalDirection = SwingConstants.SOUTH;
	int hspeed;
	int vspeed;
	int radius = 50;

	public PingPong(){
		Random r = new Random();
		this.p = new Point( 0, 0 );
		this.hspeed = r.nextInt( 10 );
		this.vspeed = r.nextInt( 10 );
	}

	public boolean hasStopped() {
		return false;
	}

	public void play(int steps, Dimension size ) {
		if (horisontalDirection == SwingConstants.EAST){
			p.x += hspeed;
			if (p.x > size.width-radius){
				horisontalDirection = SwingConstants.WEST;
			}			
		} else {
			p.x -= hspeed;
			if (p.x < 0){
				horisontalDirection = SwingConstants.EAST;
			}	
		}
		
		if (verticalDirection == SwingConstants.NORTH){
			p.y += vspeed;
			if (p.y > size.height-radius){
				verticalDirection = SwingConstants.SOUTH;
			}				
		} else {
			p.y -= vspeed;
			if (p.y < 0){
				verticalDirection = SwingConstants.NORTH;
			}	
		}
	}

	public void paint(Graphics2D g, Dimension size) {
		g.setColor( Color.yellow );
		g.fillOval( p.x, p.y, radius, radius );
	}

}
