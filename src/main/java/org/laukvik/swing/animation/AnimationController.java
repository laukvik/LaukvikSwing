package org.laukvik.swing.animation;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AnimationController extends JPanel{
	
	private static final long serialVersionUID = 1L;
	Timer timer;
	int delay = 1000/24;
	boolean isCompleted = true;
	Vector<AnimationEnabled> items, queue;
	int missingSteps = 0;
	
	public AnimationController(){
		super();
		setSize( 400, 400 );
		setVisible( true );
		setDoubleBuffered( true );
		
		items = new Vector<AnimationEnabled>();
		queue = new Vector<AnimationEnabled>();
		
		
		timer = new Timer();
		
		
		add( new Background() );
//		add( new PingPong() );
//		add( new PingPong() );
//		add( new PingPong() );
//		add( new PingPong() );
		add( new SimpleAnimable() );
		
		timer.schedule( new ScheduledTask(),  0, delay );
	}
	
	public synchronized void add( AnimationEnabled animable ){
		queue.add( animable );
	}
	

	public void setTaskCompleted( boolean isCompleted ){
		this.isCompleted = isCompleted;
	}
	
	public boolean isTaskCompleted(){
		return isCompleted;
	}
	
	private class ScheduledTask extends TimerTask {

		public void run() {
			/* Check whether the last task is running. This is to avoid a lot
			 * of tasks running if we get a hickup. Too many tasks can eventually
			 * use too much memory, cpu or other resources and then crash the
			 * system. */
			if (isTaskCompleted()){
				/* The previous task was completed. Set this task to incomplete */
				setTaskCompleted( false );

				/* Perform the scheduled tasks */
				performScheduledTask();
				
				/* Set status to completed after running */
				setTaskCompleted( true );
				
			} else {
				missingSteps++;
			}
		}

		private void performScheduledTask() {
			for (AnimationEnabled ae : queue){
				items.add( ae );
			}
			queue.removeAllElements();
			
			
			for (AnimationEnabled a : items){
				a.play( 1 + missingSteps, getSize() );
			}
			missingSteps = 0;
			repaint();

		}
	}
	
	protected void paintComponent(Graphics g){	
		if (!isTaskCompleted()){
			return;
		}
		super.paintComponent(g);
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		int imageW = getWidth();
		int imageH = getHeight();
		BufferedImage image = gc.createCompatibleImage(imageW, imageH, Transparency.TRANSLUCENT);
		Graphics2D g2 = (Graphics2D)image.getGraphics();
		
//		Graphics2D g2 = (Graphics2D) g;

		
		for (AnimationEnabled a : items){
			a.paint( g2, getSize() );
		}
		
		g.drawImage(image, 0, 0, null);
	}

	
	/**
	 * @param args
	 */
	public static void main( String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.add( new AnimationController() );
		frame.setSize( 400, 400 );
		frame.setVisible( true );
	}

}
