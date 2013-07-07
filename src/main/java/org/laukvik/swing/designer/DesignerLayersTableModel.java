package org.laukvik.swing.designer;

import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DesignerLayersTableModel implements TableModel {

	DesignerModel model;
	Vector<TableModelListener> listeners;
	
	public DesignerLayersTableModel( DesignerModel model ) {
		this.model = model;
		listeners = new Vector<TableModelListener>();
	}
	
	public void addTableModelListener(TableModelListener listener) {
		listeners.add( listener );
	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove( listener );
	}
	
	public void fireTableChanged( int rowIndex ){
		for (TableModelListener l : listeners){
			l.tableChanged( new TableModelEvent( this , rowIndex ) );
			
		}
	}

	public Class<?> getColumnClass(int columnIndex ) {
		switch(columnIndex){
			case 0 : return Boolean.class;
			case 1 : return String.class;
		}
		return null;
	}

	public int getColumnCount() {
		return 2;
	}


	public String getColumnName(int columnIndex) {
		switch(columnIndex){
			case 0 : return "Visible";
			case 1 : return "Name";
		}
		return null;
	}

	public int getRowCount() {
		return model.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0 : return model.isVisible( rowIndex );
			case 1 : return model.getName( rowIndex );
		}
		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0 : model.setVisible( (Boolean) value, rowIndex ); break;
			case 1 : model.setName( rowIndex, (String) value ); break;
		}
		fireTableChanged( rowIndex );
	}

}
