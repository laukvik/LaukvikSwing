package org.laukvik.swing.recent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import org.laukvik.swing.platform.CrossPlatformUtilities;

public class RecentFiles {
	
	private File file;
	private Properties prop = new Properties();
	private int limit = 10;
	private Vector <String> items = new Vector <String>();
	
	@SuppressWarnings("unchecked")
	public RecentFiles( Class className ){
		File appHome = CrossPlatformUtilities.getApplicationFolder( className.getSimpleName() );
		File file = new File( appHome, "preferences.properties" );
		init( file );
	}
	
	public RecentFiles( File file ){
		init( file );
	}
	
	public void init( File file ){
		this.file = file;
	    // Read properties file.
        try {
			this.prop.load(new FileInputStream( file ) );
			int index = 0;
			for (Object item : prop.values() ){
				if (index < limit){
					items.add( item.toString() );
				}
			}
		} catch (FileNotFoundException e) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		try {
			// Clear properties
			prop.clear();
			// Set properties from vector
			for (int x=0; x<items.size(); x++){
				prop.put( x+1+"", items.get(x) );
			}
			prop.store(new FileOutputStream( file ), null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void add( String filename ){
		if (items.contains( filename )){
			// Dont add if already exist
		} else {
			items.add( filename );
			save();
		}
	}
	
	public void removeAll(){
		items = new Vector <String>();
	}
	
	public Vector <String> getItems(){
		return items;
	}
	
}