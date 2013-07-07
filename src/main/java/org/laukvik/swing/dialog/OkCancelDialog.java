package org.laukvik.swing.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.laukvik.swing.file.FileTable;

public class OkCancelDialog  extends BasicDialog{

	private static final long serialVersionUID = -8991646596959844929L;
	public JButton jButtonOk = new JButton("Ok");
	JButton jButtonCancel = new JButton("Cancel");
	

	public OkCancelDialog( JComponent component ){
		super();
		JPanel jPanelButtons = new JPanel();
		/* Create the navigation buttons */
		jPanelButtons.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

		jPanelButtons.setMaximumSize(new java.awt.Dimension(32767, 64));
		jPanelButtons.setMinimumSize(new java.awt.Dimension(263, 48));
		jPanelButtons.setPreferredSize(new java.awt.Dimension(10, 48));
		
		jButtonOk.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent evt) {
						acceptPressed();
					}
				} 
			);
		jButtonCancel.addActionListener( 
				new ActionListener(){
					public void actionPerformed(ActionEvent evt) {
						cancelPressed();
					}
				} 
			);

		jButtonOk.setEnabled(false);
		jPanelButtons.add( jButtonOk );
		jPanelButtons.add( jButtonCancel );

		if (component != null){
			setComponent( component );
		}
		
		add(jPanelButtons, BorderLayout.SOUTH);
	}
	
	public OkCancelDialog(){
		this( null );
	}
	
	public void setComponent( JComponent component ){
		add( component, BorderLayout.CENTER );
	}

	
	public void close(){
		dispose();
	}
	
	public void cancelPressed(){
		setReturnValue(null);
		close();
	};
	
	public void acceptPressed(){
		close();
	};

	public static void main( String [] args ){
//		OkCancelDialog dialog = new OkCancelDialog( new JScrollPane( new FileTable( new File("/Users/morten") ) ) );
		OkCancelDialog dialog = new OkCancelDialog( new JLabel("Morten var her") );
		dialog.setVisible( true );
	}

}
