package org.laukvik.swing.designer;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class TransferableDesignerRow implements Transferable {

	public static DataFlavor NODE_FLAVOR = new DataFlavor( int[].class, "Node" );
	private DataFlavor flavors[] = { NODE_FLAVOR };
	private int [] rows;

	public TransferableDesignerRow( int [] rows ){
		this.rows = rows; 
	}

	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (flavor.getRepresentationClass() == int[].class);
	}

	public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (isDataFlavorSupported(flavor)) {
			return rows;
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}

}