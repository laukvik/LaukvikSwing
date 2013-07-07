package org.laukvik.swing.designer2;

import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragGestureEvent;
import java.io.IOException;

public class TransferablePoint implements Transferable  {

	public static DataFlavor POINT_FLAVOR = new DataFlavor( Point.class, "Point" );
	private DataFlavor flavors[] = { POINT_FLAVOR };
	private Point point;

	public TransferablePoint( Component component, DragGestureEvent dge ){
		this.point = component.getLocation();
		this.point = new Point( component.getLocation().x + dge.getDragOrigin().x, component.getLocation().y + dge.getDragOrigin().y );
		log( "TransferableComponent: " + point.x + " x " + point.y );
	}

	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (flavor.getRepresentationClass() == Point.class);
	}

	public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (isDataFlavorSupported(flavor)) {
			return point;
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}
	
	public void log( String msg ){
//		System.out.println( this.getClass().getName() + ": " + msg );
	}

}