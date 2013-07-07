package org.laukvik.swing.designer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class MoveForwardAction extends AbstractAction {
	
	private static final long serialVersionUID = 1L;

	public MoveForwardAction() {
		super();
		putValue( AbstractAction.NAME, "Move forward" );
	}
	
	public void actionPerformed( ActionEvent evt ) {
//		designerComponent.model.moveBack( )
	};

}
