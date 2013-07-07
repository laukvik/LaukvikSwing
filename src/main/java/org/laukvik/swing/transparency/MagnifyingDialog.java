package org.laukvik.swing.transparency;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JDialog;

public class MagnifyingDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	MagnifyingGlass glass;

	public MagnifyingDialog( Component source ) {
		super();
		setLayout( new BorderLayout() );
		glass = new MagnifyingGlass( source );
		add( glass );
		setDefaultCloseOperation( JDialog.DISPOSE_ON_CLOSE );
		setSize( 250, 250 );
		setVisible( true );
		setAlwaysOnTop( true );
		
	}
	
	public void setMagnifyingComponent( Component source ){
		glass.setMagnifierComponent( source );
	}
	
}