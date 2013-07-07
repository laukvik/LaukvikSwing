package org.laukvik.swing.file;

import java.io.File;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class FileTableModel implements TableModel {

	private File [] files;
	JFileChooser chooser;
	
	public FileTableModel( File [] files ){
		this.files = files;
		chooser = new JFileChooser();
	}
	
	public FileTableModel( File folder ){
		this.files  = folder.listFiles();
		chooser = new JFileChooser();
	}
	
	public Icon getIcon( File file ){
		return chooser.getIcon( file );
	}
	
	public File getRow( int rowIndex ){
		return files[ rowIndex ];
	}

	public int getRowCount() {
		return files.length;
	}

	public int getColumnCount() {
		return 3;
	}

	public String getColumnName(int columnIndex) {		
		switch (columnIndex){
			case 0: return "Icon";
			case 1: return "File";
			case 2: return "Size";
			
		}
		return null;
	}

	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == 0){
			return Icon.class;
		}
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		File file = getRow( rowIndex );
		switch (columnIndex){
			case 0: return getIcon( file );
			case 1: return file.getAbsolutePath();
			case 2: return file.length();
		}
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {	
	}
	
}