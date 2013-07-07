package org.laukvik.swing.designer2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class DragDropComponent extends JPanel implements DragGestureListener, MouseListener, MouseMotionListener{

	private static final long serialVersionUID = 1L;
	private Cursor normalCursor = new Cursor( Cursor.DEFAULT_CURSOR );
	private Border normalBorder = BorderFactory.createLineBorder( Color.WHITE, 2 );
	private Border highlightedBorder = BorderFactory.createEtchedBorder( EtchedBorder.LOWERED);
	private Component component;
	JLabel label;
	
	public DragDropComponent( Component component ) {
		super();
		this.component = component;
		label = new JLabel( "Name" );
		label.setOpaque( true );
		label.setBackground( new Color( 100, 100, 200 ) );
		label.setForeground( Color.white );
		label.setCursor( Cursor.getPredefinedCursor( Cursor.MOVE_CURSOR ) );
		setSize( component.getSize() );
		setBorder( normalBorder  );
		setLayout( new BorderLayout() );
		add( label, BorderLayout.NORTH );
		add( component, BorderLayout.CENTER );
		DragSource dragSource = DragSource.getDefaultDragSource();
		dragSource.createDefaultDragGestureRecognizer( this, DnDConstants.ACTION_COPY_OR_MOVE, this );
		setCursor( normalCursor );
		addMouseListener( this );
		addMouseMotionListener( this );
	}
	
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		component.setSize( getSize() );
	}

	public void dragGestureRecognized(DragGestureEvent dge) {
		if (hasResizeDirection()){
		} else {
			Transferable t = new TransferablePoint( this, dge );
			dge.startDrag( null, t );
		}
	}

	
	public void log( String msg ){
//		System.out.println( this.getClass().getName() + ": " + msg );
	}
	

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {	
		setBorder( highlightedBorder );
	}

	public void mouseExited(MouseEvent e) {
		setBorder( normalBorder );
	}

	public void mousePressed(MouseEvent e) {
		log("mousePressed");
		if (hasResizeDirection()){
			setResizeInProgress( true );
		} else {
			setResizeInProgress( false );
		}
	}

	public void mouseReleased(MouseEvent e) {
		log("mouseReleased");
		setResizeInProgress( false );
	}

	public void mouseDragged(MouseEvent e) {	
		
		if (isResizeInProgress()){
			if (resizeDirection == Cursor.SE_RESIZE_CURSOR){
				setBounds( this.getBounds().x, this.getBounds().y, e.getX(), e.getY() );
			} else if (resizeDirection == Cursor.E_RESIZE_CURSOR){
				setBounds( this.getBounds().x, this.getBounds().y, e.getX(), this.getBounds().height );
			} else if (resizeDirection == Cursor.S_RESIZE_CURSOR){
				setBounds( this.getBounds().x, this.getBounds().y, this.getBounds().width, e.getY() );
			} else if (resizeDirection == Cursor.N_RESIZE_CURSOR){
				setBounds( this.getBounds().x, this.getBounds().y + e.getY(), this.getBounds().width, this.getBounds().height-e.getY() );
			} else if (resizeDirection == Cursor.W_RESIZE_CURSOR){
				setBounds( this.getBounds().x+e.getX(), this.getBounds().y, this.getBounds().width-e.getX(), this.getBounds().height );
			} else if (resizeDirection == Cursor.NE_RESIZE_CURSOR){
				setBounds( this.getBounds().x, this.getBounds().y + e.getY(), e.getX(), this.getBounds().height-e.getY() );
			} else if (resizeDirection == Cursor.SW_RESIZE_CURSOR){ 
				setBounds(this.getBounds().x+e.getX(), this.getBounds().y, this.getBounds().width-e.getX(), e.getY() );
			} else if (resizeDirection == Cursor.NW_RESIZE_CURSOR){
				setBounds( this.getBounds().x+e.getX(), this.getBounds().y + e.getY(), this.getBounds().width-e.getX(), this.getBounds().height-e.getY() );
			}
		} else {
			/* Do nada */
		}
	}
	
	/**
	 * Sets the specified cursor and only if it has changed 
	 * 
	 * @param cursor
	 */
	public void changeCursor( Cursor cursor ){
		if (cursor == selectedCursor){
		} else {
			setCursor( cursor );
		}
	}
	
	private Cursor selectedCursor = null;
	private int resizeDirection = -1;
	private boolean resizeInProgress = false;
	

	public boolean hasResizeDirection() {
		return resizeDirection != -1;
	}
	
	public void setResizeDirection( int resizeDirection ) {
		this.resizeDirection = resizeDirection;
	}
	
	public void setResizeInProgress( boolean resizeInProgress ) {
		log("setResizeInProgress: " + resizeInProgress );
		this.resizeInProgress = resizeInProgress;
	}
	
	public boolean isResizeInProgress() {
		return resizeInProgress;
	}
	
	public final static int RESIZE_AREA_SIZE = 5;

	public void mouseMoved(MouseEvent e) {
//		log("mouseMoved: " + e.getX() + "x" + e.getY() );
		if (e.getX() < RESIZE_AREA_SIZE){
			/* West */
			if (e.getY() < RESIZE_AREA_SIZE){
				/* North-West */
				changeCursor( new Cursor( Cursor.NW_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.NW_RESIZE_CURSOR );
			} else if (e.getY() > this.getHeight()-RESIZE_AREA_SIZE){
				/* South-West*/
				changeCursor( new Cursor( Cursor.SW_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.SW_RESIZE_CURSOR );
			} else if ( e.getY() > ((this.getHeight()/2)-RESIZE_AREA_SIZE) &&  e.getY() < ((this.getHeight()/2)+RESIZE_AREA_SIZE)  ){
				/* West */
				changeCursor( new Cursor( Cursor.W_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.W_RESIZE_CURSOR );
			} else {
				changeCursor( normalCursor );
				setResizeDirection( -1 );
			}
		} else if (e.getX() > this.getWidth()-RESIZE_AREA_SIZE){
			/* East */
			
			if (e.getY() < RESIZE_AREA_SIZE){
				/* North-East */
				changeCursor( new Cursor( Cursor.NE_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.NE_RESIZE_CURSOR );
			} else if (e.getY() > this.getHeight()-RESIZE_AREA_SIZE){
				/* South-East */
				changeCursor( new Cursor( Cursor.SE_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.SE_RESIZE_CURSOR );
			} else if ( e.getY() > ((this.getHeight()/2)-RESIZE_AREA_SIZE) &&  e.getY() < ((this.getHeight()/2)+RESIZE_AREA_SIZE)  ){
				/* East */
				changeCursor( new Cursor( Cursor.E_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.E_RESIZE_CURSOR );
			} else {
				changeCursor( normalCursor );
				setResizeDirection( -1 );
			}
			
		} else if (e.getX() > ((this.getWidth()/2)-RESIZE_AREA_SIZE) &&  e.getX() < ((this.getWidth()/2)+RESIZE_AREA_SIZE) ){
			/* Center */
			
			if (e.getY() < RESIZE_AREA_SIZE){
				/* North */
				changeCursor( new Cursor( Cursor.N_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.N_RESIZE_CURSOR );
			} else if (e.getY() > this.getHeight()-RESIZE_AREA_SIZE){
				/* South  */
				changeCursor( new Cursor( Cursor.S_RESIZE_CURSOR ) );
				setResizeDirection( Cursor.S_RESIZE_CURSOR );
			} else {
				changeCursor( normalCursor );
				setResizeDirection( -1 );
			}
			
		} else {
			/* Nothing */
			changeCursor( normalCursor );
			setResizeDirection( -1 );
		}
	}

}