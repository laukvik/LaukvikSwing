package org.laukvik.swing.grid;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public interface GridPainter {

	public void paint( Graphics g, int width, int height, Color color, Component component );
	
}