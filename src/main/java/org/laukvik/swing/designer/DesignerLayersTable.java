package org.laukvik.swing.designer;

import java.awt.dnd.DnDConstants;

import javax.swing.JTable;

public class DesignerLayersTable extends JTable{

	private static final long serialVersionUID = 1L;

	public DesignerLayersTable() {
		super();
//		setPreferredSize( new Dimension( 150, 500 ) );
//		setRowHeight( 32 );
		/* Add drag n drop to layers */
//		setDropMode( DropMode.INSERT_ROWS );
		setDragEnabled( true ); 


	
		LayersTableDragSource dsTable = new LayersTableDragSource( this, DnDConstants.ACTION_COPY_OR_MOVE );
//		TableDropTarget dtTable = new TableDropTarget(table,main);
		
		
	}
	
}