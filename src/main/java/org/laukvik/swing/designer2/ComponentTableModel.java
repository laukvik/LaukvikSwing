package org.laukvik.swing.designer2;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class ComponentTableModel implements TableModel {
	
	private DragDropJPanel component;
	
	public ComponentTableModel( DragDropJPanel component ){
		this.component = component;
	}

	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {
	}

	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int columnIndex) {
		return "Component";
	}

	public int getRowCount() {
		return component.getComponentCount();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return null;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}


	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	}

}