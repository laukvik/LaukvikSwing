package org.laukvik.swing.locale;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class LanguageComboBoxRenderer extends LanguagePanel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;
	
	public LanguageComboBoxRenderer() {
		super();
	}

	public Component getListCellRendererComponent( JList list, Object value, int index, boolean isSelected, boolean cellHasFocus ){
		if (value == null){
			
		} else if (value instanceof String ){
			setLocale2( new Locale( (String) value ) );
		} else if (value instanceof Locale){
			setLocale2( (Locale) value  );
		}

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