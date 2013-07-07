package org.laukvik.swing.file;

import java.io.File;

import javax.swing.Icon;

import org.laukvik.swing.table.ColorTable;

public class FileTable extends ColorTable{
	
	private static final long serialVersionUID = 1L;

	public FileTable( File file ){
		super( new FileTableModel( file ) );
		getColumnModel().getColumn( 0 ).setMinWidth( 32 );
		getColumnModel().getColumn( 0 ).setMaxWidth( 32 );
		
		getColumnModel().getColumn( 2 ).setMinWidth( 100 );
		getColumnModel().getColumn( 2 ).setMaxWidth( 100 );
		
		setDefaultRenderer( Icon.class , new FileTableCellRenderer() );
		setDefaultRenderer( String.class , new FileTableCellRenderer() );
	}

}
