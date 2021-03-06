package org.laukvik.swing.table;

import java.awt.Component;
import java.awt.Font;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class RowColorTableCellRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = -1885308264356529403L;

	public RowColorTableCellRenderer(){
		super();
		setOpaque( true );
		setFont( new Font( getFont().getName(), Font.PLAIN, 11 ) );
	}

	public Component getTableCellRendererComponent(JTable table, Object value,	boolean isSelected, boolean hasFocus, int row, int column) {
		if (table instanceof RowColorModel){

			RowColorModel model = (RowColorModel) table;
			setBackground( model.getBackground( row, isSelected, table ) );
			setForeground( model.getForeground( row, isSelected, table ) );
		

		}
		
		if (value instanceof Icon){
			setIcon( (Icon) value );
		} else {
			setIcon( null );
		}
		
//		if (value instanceof String){
//			setText( (String) value );
//		} else 	if (value instanceof String){
//			setText( (String) value );
//		} else 	if (value instanceof Number){
//			setText( ((Number) value).intValue() + "" );
//		} else {
////			setText( value.toString() );
//		}
		
		setText( value == null ? "" : value.toString() );

		return this;
	}

}