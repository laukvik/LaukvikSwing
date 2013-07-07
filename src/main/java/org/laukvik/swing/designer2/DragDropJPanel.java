package org.laukvik.swing.designer2;

import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import javax.swing.JPanel;

public class DragDropJPanel extends JPanel implements DropTargetListener {

	private static final long serialVersionUID = 4978462816429378967L;

	public DragDropJPanel() {
		setLayout( null );
		
		new DropTarget( this, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null );
	}
	
	public Component add( Component comp, int x, int y ){
		Component result = super.add( new DragDropComponent(comp) );
		result.setLocation( x , y );
		return result;
	}

	public Component add( Component comp ) {
		return add( comp, 0 , 0 );
	}
	
	public void dragExit(DropTargetEvent dte) {
	}
	
	public void log( String msg ){
//		System.out.println( this.getClass().getName() + ": " + msg );
	}

	public void dragEnter(DropTargetDragEvent dtde) {
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
	}

	public void dragOver(DropTargetDragEvent dtde) {
	}
	
//	public void paint(Graphics g) {
//		super.paint(g);	
//		int grid = 10;
//		Color brightGrid = new Color(224, 224, 224);
//		Color darkGrid = new Color(208, 208, 208);
//		/* Selection Symbols */
//		int square = 3;
//		int squareSize = square * 2;
//		Rectangle r = getBounds();
//		if (grid > 0) {
//			/* Make a grid with bright colors */
//			g.setColor( brightGrid );
//
//			for (int i = grid; i < r.height; i += grid)
//				g.drawLine(0, i, r.width, i);
//			for (int i = grid; i < r.width; i += grid)
//				g.drawLine(i, 0, i, r.height);
//
//			/* Make a grid with slightly darker colors */
//			g.setColor( darkGrid );
//
//			for (int i = grid * 10; i < r.height; i += grid * 10)
//				g.drawLine(0, i, r.width, i);
//			for (int i = grid * 10; i < r.width; i += grid * 10)
//				g.drawLine(i, 0, i, r.height);
//		}
//
//	}

	public void drop( DropTargetDropEvent dtde ) {
//		log( "drop: start" );
		
		if (dtde.isDataFlavorSupported( TransferablePoint.POINT_FLAVOR )){
//			log( "drop: supported" );
			dtde.acceptDrop( dtde.getDropAction() );
			Transferable t = dtde.getTransferable();
			
			try {
				/* Get the starting point */
				Point p = (Point) t.getTransferData( TransferablePoint.POINT_FLAVOR );
				log( "origin: " + p.getLocation().x + " x " + p.getLocation().y );

				/* Get the component that was being dragged */
				Component component = getComponentAt( p );
				log( "component: " + component.getX() + " x " + component.getY() );
				
				/* Calculate the point inside the component the mouse was in when clicked */
				int deltaX = p.x - component.getX();
				int deltaY = p.y - component.getY();
				log( "delta: " + deltaX + " x " + deltaY );
				
				
				log( "mouse: " + dtde.getLocation().x + " x " + dtde.getLocation().y );
				
				/* Set new component location */
				component.setLocation( dtde.getLocation().x - deltaX, dtde.getLocation().y - deltaY );
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			/* Stop drag n drop stuff */
			dtde.dropComplete(true);
		}

	}

}