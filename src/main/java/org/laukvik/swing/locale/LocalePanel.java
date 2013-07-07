package org.laukvik.swing.locale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LocalePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Locale locale;
	private ImageIcon icon;

	public LocalePanel() {
		super();
		setPreferredSize( new Dimension(300,24) );
	}
	
	public void setLocale2( Locale locale ){
		this.locale = locale;
		try{
			this.icon = getIcon( locale );
		}catch(Exception e){
			this.icon = null;
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if (locale == null){
			
		} else {
			if (icon != null){
				g.drawImage( icon.getImage(), 4, 2, this );
			} else {
				Color orgCol = g.getColor();
				g.setColor( Color.WHITE );
				g.fillRect( 4,2, 20,20 );
				g.setColor( orgCol );
			}
			
			Font org = g.getFont();
			g.setFont( new Font(org.getName(), Font.BOLD, 12 ) );
			g.drawString( locale.getDisplayLanguage() + " " + locale.getDisplayVariant(), 30, 14 );
		}
	}
	
//	public ImageIcon getIcon( Locale locale  ) throws Exception{
//		return new javax.swing.ImageIcon( LocalePanel.class.getResource( locale.getCountry() + ".gif" ));
//	}
	
	public static ImageIcon getIcon( Locale locale ){
		return getIcon( locale.getCountry().toLowerCase() + ".gif" );
	}
	
	public static ImageIcon getIcon( String filename ){
		return  new javax.swing.ImageIcon( LocalePanel.class.getResource( "icons/" + filename ));
	}

}