package org.laukvik.swing.designer;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import org.laukvik.swing.ruler.RulerScrollPane;

public class DesignerEditor extends JSplitPane {

	private static final long serialVersionUID = 1L;
	DesignerComponent designerComponent;
	
	public DesignerEditor() {
		super();
		setBorder( null );
		designerComponent = new DesignerComponent();
		setLeftComponent( new RulerScrollPane( designerComponent ) );
		setRightComponent( new JScrollPane( designerComponent.getLayersTable() ) );
		setResizeWeight( 1 );
		setDividerLocation( 0.8d );
	}
	
	public DesignerComponent getDesignerComponent() {
		return designerComponent;
	}

}