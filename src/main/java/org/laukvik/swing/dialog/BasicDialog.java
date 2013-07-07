package org.laukvik.swing.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import org.laukvik.swing.platform.CrossPlatformUtilities;

public class BasicDialog extends javax.swing.JDialog{
	
	private static final long serialVersionUID = 648238808708247674L;
	private JLabel jLabelDescription;
	private JLabel jLabelName;
	private JPanel jPanelInformation;
	private JLabel jLabelImage = new JLabel();
	private Object returnValue;
	
	public BasicDialog(){
		initDialogComponents();
	}
	
	public void setReturnValue( Object value ){
		this.returnValue = value;
	}
	
	public Object getReturnValue(){
		return this.returnValue;
	}

	public void initDialogComponents(){
		setSize( 450, 350 );
		CrossPlatformUtilities.applyCrossPlatform( this.getRootPane() );
		setLocationRelativeTo( null );
		jPanelInformation = new javax.swing.JPanel();
		jLabelName = new javax.swing.JLabel();
		jLabelDescription = new javax.swing.JLabel();
		/* Create the information panel in the wizard. Title and error msg. */
		jLabelImage = new JLabel();
		JPanel jPanelTitleAndDescription = new JPanel();
        jPanelInformation.setLayout(new javax.swing.BoxLayout(jPanelInformation, javax.swing.BoxLayout.X_AXIS));
        jPanelInformation.setBackground(new java.awt.Color(255, 255, 255));
        jPanelInformation.setBorder(new javax.swing.border.EtchedBorder());
        jPanelTitleAndDescription.setBackground( new java.awt.Color(255, 255, 255) );
        jPanelTitleAndDescription.setLayout(new java.awt.GridLayout(2, 0));
        jPanelTitleAndDescription.setPreferredSize(new java.awt.Dimension(32000, 26));
        jPanelTitleAndDescription.add(jLabelName);
        jPanelTitleAndDescription.add(jLabelDescription);
        jPanelInformation.add(jPanelTitleAndDescription);
        jLabelName.setVerticalAlignment( javax.swing.SwingConstants.BOTTOM );
        jLabelDescription.setVerticalAlignment( javax.swing.SwingConstants.TOP );
        
        Font font = jLabelDescription.getFont();
//        Font larger = new Font( font.getName(), Font.BOLD, font.getSize() );
        Font smaller = new Font( font.getName(), font.getStyle(), font.getSize()-2 );        
//        jLabelName.setFont( larger );
        jLabelDescription.setFont( smaller );       
        jLabelImage.setPreferredSize(new java.awt.Dimension(75, 66));
        jPanelInformation.add(jLabelImage);
        add(jPanelInformation, BorderLayout.NORTH);
	}
	
	public void setIcon( Icon icon ){
		jLabelImage.setIcon( icon );
	}
	
	public void setTitle(String title) {
		jLabelName.setText("    " + title );
	}
	
	public void setDescription(String description) {
		jLabelDescription.setText("            " + description);
	}
	
	public void escapeButtonPressed(){
		setReturnValue(null);
		setVisible(false);
	}
	
	public void enterButtonPressed(){
		setVisible(false);
	}
	
	protected JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke enterStroke = KeyStroke.getKeyStroke("ENTER");
		Action actionListenerEnter = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed( ActionEvent evt ) {
				enterButtonPressed();
			}
		};
		
		KeyStroke escStroke = KeyStroke.getKeyStroke("ESCAPE");
		Action actionListenerEscape = new AbstractAction() {
			private static final long serialVersionUID = 1L;
			public void actionPerformed( ActionEvent evt ) {
				escapeButtonPressed();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(enterStroke, "ENTER");
		rootPane.getActionMap().put("ENTER", actionListenerEnter);
		
		inputMap.put(escStroke, "ESCAPE");
		rootPane.getActionMap().put("ESCAPE", actionListenerEscape);

		return rootPane;
	}
	
	public static void main( String [] args ){
		BasicDialog dialog = new BasicDialog();
		dialog.setVisible( true );
	}
	
}