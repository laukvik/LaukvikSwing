package org.laukvik.swing.icon;

import java.net.URL;
import javax.swing.Icon;

public interface IconDroppedListener {

//	public void iconChanged( URL url );
	public void iconRemoved();
	public void iconChanged( Icon icon, URL url );
	
}