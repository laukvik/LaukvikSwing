package org.laukvik.swing.designer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.laukvik.swing.grid.DefaultGridPainter;
import org.laukvik.swing.grid.GridPainter;

public class DesignerComponent extends JPanel implements MouseMotionListener, MouseListener, DesignerModelListener, ActionListener{

	private static final long serialVersionUID = 1L;
	protected DesignerModel designerModel;
	protected DesignerRenderer designerRenderer;
	protected DesignerLinkRenderer designerLinkRenderer;
	protected ListSelectionModel selectionModel;
	protected DesignerSelectionRenderer designerSelectionRenderer;
	protected GridPainter gridPainter;
	protected Color gridColor;
	protected Dimension gridSize;	
	protected boolean snapToGrid;
	protected JPopupMenu popupMenu, contextMenu;
	protected Vector<DesignerMouseListener> mouseListeners;
	protected int selectedMouseOverIndex = -1;
	protected int resizeSensitivity = 10;
	protected DesignerLayersTable layersTable;
	protected DesignerLayersTableModel layersModel;
	
	public DesignerComponent(){
		super();
		setAutoscrolls( true );
		setSize( new Dimension(400,400) );
		setPreferredSize( new Dimension(400,400) );
		
		selectionModel = new DefaultListSelectionModel();
		designerSelectionRenderer = new TransparentSelectionRenderer();
		designerLinkRenderer = new LineDesignerLinkRenderer();
		gridPainter = new DefaultGridPainter();
		mouseListeners = new Vector<DesignerMouseListener>();
		snapToGrid = true;
		gridSize = new Dimension( 20, 20 );
		gridColor = new Color( 240, 240, 240 );
		gridColor = new Color( 200, 200, 255 );
		setBackground( Color.white );
		
		
		layersTable = new DesignerLayersTable();

		selectionModel = layersTable.getSelectionModel();
		selectionModel.addListSelectionListener( new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent arg0) {
				repaint();
			}} );
		
		
		contextMenu = new JPopupMenu();
		JMenuItem moveForward = new JMenuItem( new MoveForwardAction() );
		moveForward.addActionListener( this );
		JMenuItem moveBack = new JMenuItem( new MoveBackwardAction() );
		moveBack.addActionListener( this );
		
		contextMenu.add( moveForward );
		contextMenu.add( moveBack );
		
		popupMenu = new JPopupMenu();
		popupMenu.setLabel( "Items" );
		
		addMouseMotionListener( this );
		addMouseListener( this );
		
		designerModel = new DefaultDesignerModel();
	}
	
	public void setSnapToGrid(boolean snapToGrid) {
		this.snapToGrid = snapToGrid;
	}
	
	public boolean isSnapToGrid() {
		return snapToGrid;
	}
	
	public void addMouseListener( DesignerMouseListener l ){
		mouseListeners.add( l );
	}
	
	public void removeMouseListener( DesignerMouseListener l ){
		mouseListeners.remove( l );
	}
	
	public void fireMouseOver( int index ){
		for (DesignerMouseListener l : mouseListeners){
			l.mouseOver( index );
		}
	}
	
	public void fireMouseOut( int index ){
		for (DesignerMouseListener l : mouseListeners){
			l.mouseOut( index );
		}
	}
	
	public void fireMouseClicked( int index ){
		for (DesignerMouseListener l : mouseListeners){
			l.mouseClicked( index );
		}
	}
	
	public void fireMouseDoubleClicked( int index ){
		for (DesignerMouseListener l : mouseListeners){
			l.mouseDoubleClicked( index );
		}
	}
	
	public void setModel(DesignerModel model) {
		if (this.designerModel != null){
			this.designerModel.removeDesignerListener( this );	
		
		}
		
		
		
		layersModel = new DesignerLayersTableModel( model );		
		layersTable.setModel( layersModel );
		
		layersModel.addTableModelListener( new TableModelListener(){
			public void tableChanged(TableModelEvent e) {
				repaint();
			}} );
		
		layersTable.getColumnModel().getColumn( 0 ).setMinWidth( 24 );
		layersTable.getColumnModel().getColumn( 0 ).setMaxWidth( 24 );
		
		this.designerModel = model;
		model.addDesignerListener( this );
	}
	public DesignerModel getModel() {
		return designerModel;
	}
	
	public void setDesignerRenderer(DesignerRenderer designerRenderer) {
		this.designerRenderer = designerRenderer;
	}
	
	public DesignerRenderer getDesignerRenderer() {
		return designerRenderer;
	}
	
	/**
	 * Returns the required size
	 * 
	 * @return
	 */
	protected Dimension calculateSize(){
		int rightMost = 0;
		int bottomMost = 0;
		for (int x=0; x<designerModel.size(); x++){
			if (designerModel.isVisible(x)){
				Shape s = designerRenderer.getShape( designerModel.getLocation(x),  designerModel.getObject( x ) );
				Rectangle r = s.getBounds();
				if (r.x+r.width > rightMost ){
					rightMost = r.x + r.width;
				}
				if (r.y+r.height > bottomMost ){
					bottomMost = r.y + r.height;
				}
			} else {
				/* Dont count invisible items */
			}

		}

		return new Dimension( rightMost+1, bottomMost+1 );
	}

	public void paintComponent( Graphics g ){
		super.paintComponent(g);
		
//		Graphics2D g2 = (Graphics2D)g;
//		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		/* Paint the grid */
		gridPainter.paint(g, gridSize.width, gridSize.height, gridColor, this );

		
		/* Paint the links */
		for(int x=0; x<designerModel.size(); x++){
			if (designerModel.isVisible( x )){
				DesignerLink [] links = designerModel.listLinks( x );
				for (DesignerLink l : links){
					designerLinkRenderer.paintConnection( l, g );	
				}		
			} else {
				/**/
			}

		}
		
		/* Draw non-dragged items below */
		for(int x=0; x<designerModel.size(); x++){
			boolean selected = (selectionModel.isSelectedIndex( x ));
			boolean dragging = !selectionMode && selected;
			if (designerModel.isVisible( x ) && !dragging){ 
				designerRenderer.paintDiagram( designerModel.getObject(x), designerModel.getLocation( x ), selected, dragging, this, g );
			}
		}
		
		/* Draw dragged items topmost */
		for(int x=0; x<designerModel.size(); x++){
			boolean selected = (selectionModel.isSelectedIndex( x ));
			boolean dragging = !selectionMode && selected;
			if (designerModel.isVisible( x ) && dragging){ 
				designerRenderer.paintDiagram( designerModel.getObject(x), designerModel.getLocation( x ), selected, dragging, this, g );
			}
		}
		
		
		if (selectionArea == null){
			/* No area is selected */
		} else {
			/* Paint the selected area */
			designerSelectionRenderer.paintSelection( selectionArea, g );
		}
	}
	
	/**
	 * Returns a rectangle which always has a positive width and height
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static Rectangle getRectangle( Point start, Point end ){
		Rectangle r = new Rectangle(0,0,0,0);
		if (end.x > start.x){
			/* East */
			if (end.y > start.y){
				/* South  */
				r = new Rectangle( start, new Dimension( end.x-start.x, end.y-start.y ) );
			} else {
				/* North */
				r = new Rectangle( start.x, end.y, end.x-start.x, start.y-end.y );
			}
		} else {
			/* West */
			if (end.y > start.y){
				/* South */
				r = new Rectangle( end.x, start.y, start.x-end.x, end.y-start.y );
			} else {
				/* North */
				r = new Rectangle( end.x, end.y, start.x-end.x, start.y-end.y );
			}
		}
		return r;
	}
	
	
	/**
	 * Returns the index of the object topmost at current location. -1 is returned
	 * if nothing found 
	 * 
	 * @param location
	 * @return
	 */
	private int getTopmost( Point location ){
		int index = -1;
		for(int x=0; x<designerModel.size(); x++){
			Object value = designerModel.getObject( x );
			Shape s = designerRenderer.getShape( designerModel.getLocation( x ), value );
			if (s.contains( location )){
				index  = x;
			}
		}
		return index;
	}
	
	/**
	 * Returns a collection of all indexes at the specified location
	 * 
	 * @param location
	 * @return
	 */
	public Vector<Integer> listIndexes( Point location ){
		Vector<Integer> indexes = new Vector<Integer>();
		for(int x=0; x<designerModel.size(); x++){
			Object value = designerModel.getObject( x );
			Shape s = designerRenderer.getShape( designerModel.getLocation( x ), value );
			if (s.contains( location )){
				indexes.add( x );
			}
		}
		return indexes;
	}

	public Vector<Integer> listIndexes( Rectangle area ){
		Vector<Integer> indexes = new Vector<Integer>();
		for(int x=0; x<designerModel.size(); x++){
			Object value = designerModel.getObject( x );
			Shape s = designerRenderer.getShape( designerModel.getLocation( x ), value );
			if (s.intersects( area )){
				indexes.add( x );
			}
		}
		return indexes;
	}
	
	
	
	
	
	
	
	
	
	/** 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * MOUSE STUFF 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 ****/
	
	boolean selectionMode = true;
	Point start, end;
	Rectangle selectionArea;


	public void mouseDragged(MouseEvent e) {
		int dx = start.x - e.getX();
		int dy = start.y - e.getY();
//		System.out.println( "mouseDragged: " +  e.getX() + "x" + e.getY() + " " + dx + "x" + dy );
		if (selectionMode){
			/* Select mode */
			end = e.getPoint();
			selectionArea = getRectangle( start, end );
			selectionModel.clearSelection();
			Vector<Integer> indexes = listIndexes( selectionArea );
			for (int i : indexes){
				selectionModel.addSelectionInterval( i, i );
			}
			repaint();
		} else {
			/* Drag mode */
			if (selectionModel.isSelectionEmpty()){
				/* Nothing is already selection */
				Vector<Integer> indexes = listIndexes( e.getPoint() );
				if (indexes.size() == 0){
					/* Selecting on an empty area */
					selectionModel.clearSelection();
					repaint();
				} else {
					/* Making a selection on a empty area */

				}
			} else {
				/* Something is already selected */

				for (int x=0; x<designerModel.size(); x++){
					if (selectionModel.isSelectedIndex( x )){
						Point existingPoint = designerModel.getLocation( x );
						Point newPoint = new Point( existingPoint );
						newPoint.x -= dx;
						newPoint.y -= dy;
//						if (isSnapToGrid()){
//							model.setLocation( findClosestGridPoint( newPoint ), x );
//						} else {
							designerModel.setLocation( newPoint, x );
//						}
						
					}
				}
				start = e.getPoint();
				repaint();
			}
		}
	}
	
	public Point findClosestGridPoint( Point p ){
		// 20x20
		
		Float halfx = (p.x + 0f)  / (0f + gridSize.width);
		Float halfy = (p.y + 0f)  / (0f + gridSize.height);
		
//		System.out.println( p.x  + "  " + (halfx > 0.5) + " " + Math.round( halfx ));
		return new Point( Math.round( halfx )*gridSize.width, Math.round( halfy ) *gridSize.height );
	}

	public void mouseMoved(MouseEvent e) {
//		System.out.println( e.getX() + "x" + e.getY() );
		int index = getTopmost( e.getPoint() );
		if (index == -1){
			setToolTipText( null );
			setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR ) );

		} else {
			Shape s = designerRenderer.getShape( designerModel.getLocation( index ), designerModel.getObject( index ) );
			Rectangle r = s.getBounds();
			Point p = e.getPoint();
			int x = p.x;
			int y = p.y;
			
			setToolTipText( designerModel.getTooltip( index ) );
			
			if (designerModel.isResizable( index )){
				if (x <= r.x+resizeSensitivity && y <= r.y+resizeSensitivity){
					setCursor( Cursor.getPredefinedCursor( Cursor.NW_RESIZE_CURSOR ) );
					
				} else if (x >= r.x+r.width-resizeSensitivity && y <= r.y+resizeSensitivity){
					setCursor( Cursor.getPredefinedCursor( Cursor.NE_RESIZE_CURSOR ) );
					
				} else if (x <= r.x+resizeSensitivity && y >= r.y+r.height-resizeSensitivity){
					setCursor( Cursor.getPredefinedCursor( Cursor.SW_RESIZE_CURSOR ) );
					
				} else if (x >= r.x+r.width-resizeSensitivity && y >= r.y+r.height-resizeSensitivity){
					setCursor( Cursor.getPredefinedCursor( Cursor.SE_RESIZE_CURSOR ) );
					
				} else {
					setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
				}
			} else {
				setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
			}
			
		}
		
		if (index == -1 && selectedMouseOverIndex == -1){
			/* Nothing is already mouse over and nothing is mouse over*/
		} else if (index > -1 && selectedMouseOverIndex != index){
			/* Found a new index and the old one isnt the same */
			fireMouseOut( selectedMouseOverIndex );
			fireMouseOver( index );
		} else if (index > -1 && selectedMouseOverIndex == -1){
			/* Found new index but didnt already have one */
			fireMouseOver( selectedMouseOverIndex );
		} else if (index == -1 && selectedMouseOverIndex > -1){
			fireMouseOut( selectedMouseOverIndex );
		}

		
		selectedMouseOverIndex = index;
	}

	
	/**
	 * 
	 */
	public void mouseClicked(MouseEvent e) {
//		System.out.println( " mouseClicked: ");
		start = null;
		end = null;
		selectionArea = null;
		
		int index = getTopmost( e.getPoint() );
		if (index > -1){
			if (e.getClickCount() == 2){
				fireMouseDoubleClicked( index );
			} else {
				fireMouseClicked( index );
			}
			
		} else {
			selectionModel.clearSelection();
		}	
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
			boolean isContextMenu = true;
			if (isContextMenu){
				contextMenu.show(e.getComponent(), e.getX(), e.getY());
			} else {
				popupMenu.removeAll();
				
				for (int x=0; x<designerModel.size();  x++){
					popupMenu.add( new JCheckBoxMenuItem( designerModel.getName( x ), selectionModel.isSelectedIndex( x ) ) );	
				}
				
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
			

        } else {
        	
        }
		
		boolean shiftPressed = ((e.getModifiersEx() & (InputEvent.SHIFT_DOWN_MASK)) == InputEvent.SHIFT_DOWN_MASK);
//		System.out.println( "mousePressed: shift: " + shiftPressed );
		int topmost = getTopmost( e.getPoint() );
		start = e.getPoint();
		if (topmost == -1){
			/* Didn't click on anything */
			selectionMode = true;
			selectionModel.clearSelection();
			start = e.getPoint();
		} else {

			if (shiftPressed){
				/* Add to selection */
				if (selectionModel.isSelectedIndex( topmost )){
					/* It was already selected - deselect it */
					selectionModel.removeSelectionInterval( topmost, topmost );
				} else {
					/* */
					selectionModel.addSelectionInterval( topmost, topmost );
				}
				
			} else {
				if (!selectionModel.isSelectedIndex( topmost )){
					selectionModel.clearSelection();
					selectionModel.addSelectionInterval( topmost, topmost );
				}
			}

			/* Clicked on one or more things */
			selectionMode = false;
//			repaint();
		}

	}

	public void mouseReleased( MouseEvent e ) {
		/* Snap to grid */
		if (!selectionMode){
			int dx = start.x - e.getX();
			int dy = start.y - e.getY();
			for (int x=0; x<designerModel.size(); x++){
				if (selectionModel.isSelectedIndex( x )){
					Point existingPoint = designerModel.getLocation( x );
					Point newPoint = new Point( existingPoint );
					newPoint.x -= dx;
					newPoint.y -= dy;
					if (isSnapToGrid()){
						designerModel.setLocation( findClosestGridPoint( newPoint ), x );
					}
					
				}
			}
		}

		
		
		start = null;
		end = null;
		selectionArea = null;
		selectionMode = true;
		repaint();
	}

	/**
	 * Fires when
	 * 
	 */
	public void designerChanged( DesignerModelEvent evt ){
		setPreferredSize( calculateSize() );
		setSize( calculateSize() );
	}

	/**
	 * 
	 */
	public void actionPerformed( ActionEvent evt) {
		System.out.println( evt.getActionCommand() );
		
	}

	public JTable getLayersTable() {
		return layersTable;
	}
	
	
	public static void main( String [] args ){
		
	}

}