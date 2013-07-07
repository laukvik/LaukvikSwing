package org.laukvik.swing.platform;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import org.laukvik.swing.platform.osx.OSXApplication;

public class CrossPlatformUtilities {

	public CrossPlatformUtilities() {
	}
	
	public static void applyCrossPlatform( JRootPane pane ){
//		pane.putClientProperty( "Window.style", "small" );
		pane.putClientProperty( "apple.awt.brushMetalLook", Boolean.TRUE );
	}
	
	public static File getHomeFolder(){
		File file = new File( System.getProperty("user.home") );
		if (!file.exists()){
			file.mkdir();
		}
		return file;
	}
	
	public static File getDownloadsFolder(){
		File lib =  new File( getHomeFolder(), "Downloads" );
		if (!lib.exists()){
			lib.mkdir();
		}
		return lib;
	}
	
	public static File getLibraryFolder(){
		File lib =  new File( getHomeFolder(), "Library" );
		if (!lib.exists()){
			lib.mkdir();
		}
		return lib;
	}	
	
	public static File getApplicationSupportFolder(){
		File file = new File( getLibraryFolder(), "Application Support" );
		if (!file.exists()){
			file.mkdir();
		}
		return file;
	}	
	
	/**
	 * Returns the home folder for the specific application $appName
	 * 
	 * @param appName
	 * @return
	 */
	public static File getApplicationFolder( String appName ){
//		if (isMacOSX()){
			File file= new File( getApplicationSupportFolder(), appName );	
			if (!file.exists()){
				file.mkdir();
			}
			return file;
//		} else {
//			
//			return getHomeFolder();
//		}
	}	

	/**
	 * Returns a cross-platform keystroke that enables the platform behave natively.
	 * This is usually a problem for Mac people who uses the Apple button like
	 * Windows people use the Control button.
	 * 
	 * @param keyevent the keyevent you want the keystroke for
	 * @return a cross-platfrom compatible keystroke
	 */
	public static KeyStroke getKeystroke(int keyevent) {
		return KeyStroke.getKeyStroke(keyevent, Toolkit.getDefaultToolkit()
				.getMenuShortcutKeyMask());
	}

	/**
	 * Returns an icon object with the given filename in the directory. The method
	 * will only look inside this directory for images
	 * <code>/org/laukvik/pdi/swing/icons/*.gif</code>
	 * 
	 * @param filename The name of the icon file
	 * @return an icon object
	 */
	@SuppressWarnings("unchecked")
	public static Icon getIcon2( Class klas, String filename) {
		return new javax.swing.ImageIcon( klas.getResource("icons/" + filename));
	}

	@SuppressWarnings("unchecked")
	public static ResourceBundle getLanguage( Class klass ) {
		Locale locale = Locale.getDefault();
		ResourceBundle messages = ResourceBundle.getBundle( klass.getPackage().getName()	+ ".messages", locale);
		return messages;
	}
	
	public static void setCrossPlatformProperties() {
		/* Set the system look and feel*/
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		if (System.getProperty("os.name").startsWith("Mac")) {
			System.setProperty("apple.laf.useScreenMenuBar", "true");
			// System.setProperty( "apple.awt.brushMetalLook", "true" );
			setMacLookAndFeel();
		}
	}

	public static void setMacLookAndFeel() {
		Color hiBg = new Color(61, 128, 223);
		Color hiFg = new Color(255, 255, 255);

		UIManager.put("Desktop.background", Color.GRAY);

		/* Set foreground and background colors of selected items */
		UIManager.put("Tree.selectionBackground", hiBg);
		UIManager.put("Tree.selectionForeground", hiFg);
		UIManager.put("Tree.selectionBorderColor", hiBg);

		/* Textfields */
		UIManager.put("TextField.selectionBackground", hiBg);
		UIManager.put("TextField.selectionForeground", hiFg);

		/* Tables */
		UIManager.put("Table.selectionBackground", hiBg);
		UIManager.put("Table.selectionForeground", hiFg);

		Font defaultFont = UIManager.getFont("Table.font");
		Font font = new Font(defaultFont.getName(), defaultFont.getStyle(), 11);
		UIManager.put("Table.font", font);
		UIManager.put("Tree.font", font);
		//		UIManager.put( "Label.font", font );
		//		UIManager.put( "Button.font", font );

		/* List Boxes */
		UIManager.put("List.selectionBackground", hiBg);
		UIManager.put("List.selectionForeground", hiFg);
	}
	
	public static void openFile( File file ) throws Exception{
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Mac OS")) {
			Runtime r = Runtime.getRuntime();
			r.exec("open " + file.getAbsolutePath() );
		} else if (osName.startsWith("Windows")) {
			Runtime.getRuntime().exec( "rundll32 url.dll,FileProtocolHandler " + file.getAbsolutePath() );
		} else {
			// Popular browsers are firefox, opera, konqueror, epiphany, mozilla, netscape
			Runtime r = Runtime.getRuntime();
			r.exec("open " + file.getAbsolutePath() );
		}
	}

	/**
	 * Opens the URL in the native browser
	 * 
	 * @param url
	 * @throws Exception
	 */
	public static void openBrowser(URL url) throws Exception {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Mac OS")) {
			Runtime r = Runtime.getRuntime();
			r.exec("open " + url.toExternalForm().replaceAll(" ", "%20"));
		} else if (osName.startsWith("Windows")) {
			Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler " + url);
		} else {
			// Popular browsers are firefox, opera, konqueror, epiphany, mozilla, netscape
			Runtime r = Runtime.getRuntime();
			r.exec("firefox " + url.toExternalForm());
		}
	}

	public static void openBrowser(File file) throws Exception {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Mac OS")) {
			Runtime r = Runtime.getRuntime();
			r.exec("open " + file.getAbsolutePath());
		} else if (osName.startsWith("Windows")) {
			Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler "
							+ file.getAbsolutePath());
		} else {
			// Popular browsers are firefox, opera, konqueror, epiphany, mozilla, netscape
			Runtime r = Runtime.getRuntime();
			r.exec("firefox " + file.getAbsolutePath());
		}
	}

	public static boolean isMacOSX() {
		return (System.getProperty("os.name").toLowerCase()
				.startsWith("mac os x"));
	}

	@SuppressWarnings("unchecked")
	public static void macOSXRegistration(OSXApplication app) {
		if (isMacOSX()) {
			try {
				Class osxAdapter = ClassLoader.getSystemClassLoader()
						.loadClass("org.laukvik.swing.osx.OSXAdapter");

				Class[] defArgs = { OSXApplication.class };
				Method registerMethod = osxAdapter.getDeclaredMethod(
						"registerMacOSXApplication", defArgs);
				if (registerMethod != null) {
					Object[] args = { app };
					registerMethod.invoke(osxAdapter, args);
				}
				// This is slightly gross.  to reflectively access methods with boolean args, 
				// use "boolean.class", then pass a Boolean object in as the arg, which apparently
				// gets converted for you by the reflection system.
				defArgs[0] = boolean.class;
				Method prefsEnableMethod = osxAdapter.getDeclaredMethod(
						"enablePrefs", defArgs);
				if (prefsEnableMethod != null) {
					Object args[] = { Boolean.TRUE };
					prefsEnableMethod.invoke(osxAdapter, args);
				}
			} catch (NoClassDefFoundError e) {
				// This will be thrown first if the OSXAdapter is loaded on a system without the EAWT
				// because OSXAdapter extends ApplicationAdapter in its def
				System.err
						.println("This version of Mac OS X does not support the Apple EAWT.  Application Menu handling has been disabled ("
								+ e + ")");
			} catch (ClassNotFoundException e) {
				// This shouldn't be reached; if there's a problem with the OSXAdapter we should get the 
				// above NoClassDefFoundError first.
				System.err
						.println("This version of Mac OS X does not support the Apple EAWT.  Application Menu handling has been disabled ("
								+ e + ")");
			} catch (Exception e) {
				System.err.println("Exception while loading the OSXAdapter:");
				e.printStackTrace();
			}
		}
	}

}