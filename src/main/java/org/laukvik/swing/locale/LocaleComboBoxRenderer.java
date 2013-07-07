package org.laukvik.swing.locale;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class LocaleComboBoxRenderer extends LocalePanel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public LocaleComboBoxRenderer() {
		super();
	}

	public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ){
		if (value != null){
			Locale l = (Locale) value;
			setLocale2( l );
		}
		setLocale( list.getLocale() );
		if (isSelected) {
			setBackground( list.getSelectionBackground() );
			setForeground( list.getSelectionForeground() );
		} else {
			setBackground( list.getBackground() );
			setForeground( list.getForeground() );
		}
		return this;
	}


}