package org.laukvik.swing.ruler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JComponent;
import javax.swing.UIManager;


public class Ruler extends JComponent {


	private static final long serialVersionUID = 1L;
	public static final int INCH = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int SIZE = 35;
    
    public final static int SYSTEM_METRIC = 1;
    public final static int SYSTEM_INCH   = 2;
    public final static int SYSTEM_PIXELS = 0;

    public int orientation;
    public boolean isMetric;
    private int increment;
    private int units;
    private int system;
    private int marker = -1;
    
    public Ruler(int direction, int system) {
		orientation = direction;
		this.system = system;
		switch (system) {
			case SYSTEM_METRIC:
				units = (int) ((double) INCH / (double) 2.54); // dots per
				increment = units;// centimeter
				break;
			case SYSTEM_INCH:
				units = INCH;
				increment = units / 2;
				break;
			default:
				units = 100;
				increment = 10;
				break;
		}
	}
    
    public void setMarker(int marker) {
		this.marker = marker;
	}

    public int getIncrement() {
        return increment;
    }

    public void setPreferredHeight(int ph) {
        setPreferredSize(new Dimension(SIZE, ph));
    }

    public void setPreferredWidth(int pw) {
        setPreferredSize(new Dimension(pw, SIZE));
    }

    protected void paintComponent(Graphics g) {
        Rectangle drawHere = g.getClipBounds();

        // Fill clipping area with dirty brown/orange
        g.setColor( UIManager.getColor("Button.background") );
        g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);

        // Do the ruler labels in a small font that's black.
        g.setFont(new Font( UIManager.getFont("Label.font").getName(), Font.PLAIN, 10));
        g.setColor( UIManager.getColor("Button.foreground") );

        // Some vars we need.
        int end = 0;
        int start = 0;
        int tickLength = 0;
        String text = null;

        // Use clipping bounds to calculate first and last tick locations.
        if (orientation == HORIZONTAL) {
            start = (drawHere.x / increment) * increment;
            end = (((drawHere.x + drawHere.width) / increment) + 1)
                  * increment;
        } else {
            start = (drawHere.y / increment) * increment;
            end = (((drawHere.y + drawHere.height) / increment) + 1)
                  * increment;
        }

        // Make a special case of 0 to display the number
        // within the rule and draw a units label.
        if (start == 0) {
            if (system == SYSTEM_PIXELS){
           	 	text = "Pixels";
           } else {
        	   text = Integer.toString(0) + (isMetric ? " cm" : " in");
           }
            
            text = " ";
            tickLength = 10;
            if (orientation == HORIZONTAL) {
                g.drawLine(0, SIZE-1, 0, SIZE-tickLength-1);
                g.drawString(text, 2, 21);
            } else {
                g.drawLine(SIZE-1, 0, SIZE-tickLength-1, 0);
                g.drawString(text, 9, 10);
            }
            text = null;
            start = increment;
        }

        // ticks and labels
        for (int i = start; i < end; i += increment) {
            if (i % units == 0)  {
                tickLength = 10;
                if (system == SYSTEM_PIXELS){
                	 text = i + "";
                } else {
                	text = Integer.toString(i/units);
                }
                
               
            } else {
                tickLength = 7;
                text = null;
            }
            

            if (tickLength != 0) {
                if (orientation == HORIZONTAL) {
                    g.drawLine(i, SIZE-1, i, SIZE-tickLength-1);
                    if (text != null)
                        g.drawString(text, i-3, 21);
                } else {
                    g.drawLine(SIZE-1, i, SIZE-tickLength-1, i);
                    if (text != null)
                        g.drawString(text, 4, i+3);
                }
            }
        }
        
        paintMarkers( g );
    }
    
    public void paintMarkers( Graphics g ){
        g.setColor( getMarkerColor() );
        if (orientation == HORIZONTAL){
        	g.drawLine( marker, 0, marker, 50 );
        } else {
        	g.drawLine( 0, marker, getWidth(), marker );
        }
    }
    
    public final Color getMarkerColor(){
    	return UIManager.getColor( "List.selectionBackground" );
    }
    
}
