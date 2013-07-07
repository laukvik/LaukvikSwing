package org.laukvik.swing.locale;

import java.util.Locale;

import javax.swing.JComboBox;

public class LanguageComboBox extends JComboBox {

	private static final long serialVersionUID = 1L;

	public LanguageComboBox(){
		super( Locale.getISOLanguages() );		
	}

}