package org.laukvik.swing.locale;

import java.util.Comparator;
import java.util.Locale;


public class LanguageSorter implements Comparator<Object> {
	
	Locale locale;
	
	public LanguageSorter( Locale locale ){
		this.locale = locale;
	}

	/**
	 * Compares its two arguments for order. Returns a negative integer, zero, or a positive integer 
	 * as the first argument is less than, equal to, or greater than the second.
	 * 
	 */
	public int compare(Object o1, Object o2) {
		Locale l1 = (Locale) o1;
		Locale l2 = (Locale) o2;
//		System.out.println( l1.getDisplayLanguage( locale ) + "  =  " + l2.getDisplayLanguage( locale ) );
		int v = l1.getDisplayLanguage( locale ).compareTo( l2.getDisplayLanguage( locale ) );
		return v;
//		if (l1.getLanguage().equalsIgnoreCase("no")){
//			System.out.println( "NO: " + v + " " + l1.getLanguage() + " " + l1.getDisplayVariant( locale ) );
//		} else {
//			System.out.println( "Ikke norsk: " + l1.getLanguage() + " " + l1.get );
//		}
//		
//		if (v == 0){
//			return l1.getDisplayVariant( locale ).compareTo( l2.getDisplayVariant( locale ) );
//		} else {
//			return v;
//		}
	}
	

}
