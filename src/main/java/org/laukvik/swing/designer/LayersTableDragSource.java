package org.laukvik.swing.designer;

import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;

import javax.swing.JTable;

public class LayersTableDragSource implements DragSourceListener, DragGestureListener{

	DragSource source;
	DragGestureRecognizer recognizer;
	JTable table;
	
	public LayersTableDragSource( JTable table, int actions ) {
		this.table = table;
		this.source = new DragSource();
		this.recognizer = this.source.createDefaultDragGestureRecognizer(table,	actions, this);
	}
	
	public void dragGestureRecognized(DragGestureEvent evt) {
		int [] selects = table.getSelectedRows();
		System.out.println( "Dragging: " + selects.length );
		try{
			TransferableDesignerRow transferable = new TransferableDesignerRow( selects );
			source.startDrag( evt, DragSource.DefaultMoveNoDrop, transferable, this );
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public void dragDropEnd(DragSourceDropEvent evt) {
		System.out.println( "dragDropEnd: " );
		evt.getDropSuccess();
	}

	public void dragEnter(DragSourceDragEvent evt) {
		System.out.println( "dragEnter: " );
	}

	public void dragExit(DragSourceEvent evt) {
		System.out.println( "dragExit: " );
	}
	
	public void dragOver(DragSourceDragEvent evt) {
		System.out.println( "dragOver: " );
	}

	public void dropActionChanged(DragSourceDragEvent evt) {
		System.out.println( "dropActionChanged: " );
	}
}