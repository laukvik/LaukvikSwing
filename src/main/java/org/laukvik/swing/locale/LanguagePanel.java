package org.laukvik.swing.locale;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Locale;
import javax.swing.JPanel;

public class LanguagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Locale locale;

	public LanguagePanel() {
		super();
		setPreferredSize( new Dimension(300,24) );
	}
	
	public void setLocale2( Locale locale ){
		this.locale = locale;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (locale == null){
			
		} else {
			Font org = g.getFont();
			g.setFont( new Font(org.getName(), Font.BOLD, 12 ) );
			g.drawString( locale.getDisplayLanguage( getLocale() ) , 30, 14 );
		}
	}

}