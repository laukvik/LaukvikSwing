package org.laukvik.swing.locale;

import java.awt.Dimension;
import java.util.Locale;
import javax.swing.JComboBox;

public class LocaleComboBox extends JComboBox {

	private static final long serialVersionUID = 1L;
	
	public LocaleComboBox() {
		this( Locale.getAvailableLocales() );
	}

	public LocaleComboBox( Locale... locale ) {
		super( locale );
		setRenderer( new LocaleComboBoxRenderer() );
		setPreferredSize( new Dimension(300, 24 ) );
	}
	
	public void setSelectedItem( Object item ) {
		Locale newItem = (Locale) item;
		for (int x=0; x<getItemCount(); x++){
			Locale l = (Locale) getItemAt( x );
			if (newItem.toString().equalsIgnoreCase( l.toString() )){
				super.setSelectedItem( l );
			}
		}
	}
	
	public void setSelectedLocale( Locale locale  ){
		setSelectedItem( locale );
	}

	public Locale getSelectedLocale() {
		return (Locale) getSelectedItem();
	}

}