package org.laukvik.swing.file;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.laukvik.swing.table.RowColorModel;

public class FileTableCellRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = -1885308264356529403L;
	Font font;
	
	public FileTableCellRenderer(){
		super();
		font = new Font( getFont().getName(), Font.PLAIN, 11  );
		setFont( font );
	}

	public Component getTableCellRendererComponent(JTable table, Object value,	boolean isSelected, boolean hasFocus, int row, int column) {
		if (table instanceof RowColorModel){
			RowColorModel model = (RowColorModel) table;
			setBackground( model.getBackground( row, isSelected, table ) );
		}
		
		if (value instanceof Icon){
			setIcon( (Icon) value );
		} else {
			setIcon( null );
		}
		
		if (value instanceof String){
			setText( (String) value );
		} else 	if (value instanceof String){
			setText( (String) value );
		} else 	if (value instanceof Number){
			setText( ((Number) value).intValue() + "" );
		} else {
//			setText( value.toString() );
		}
		
		
		
		return this;
	}

}