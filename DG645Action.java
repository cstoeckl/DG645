import java.awt.*;
import java.awt.event.*;
import java.beans.*;

/*
 * Created on Feb 24, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author csto
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/*public class DG645Action implements Runnable, PropertyChangeListener, ActionListener 
{
	private static final long serialVersionUID = 1L;
	
	public DG645Gui miGui;
	private Thread waitThread;
	private int timeout;
	
	public DG645Action(DG645Gui gui)
	{	
		miGui = gui;	
		waitThread = new Thread(this,"waitDone");
		waitThread.start();
	}
	
//	 separate function to be callable by main programm
	public void connect()
	{
		startWait("Connect",5);
		DG645Control.dg645.connect();
		//DG645Control.dg645.testing();
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("edited") &&
			DG645Control.dg645.initialized == true) {
			miGui.lockControls();
			miGui.moveButton.setEnabled(true);
			miGui.moveButton.setBackground(Color.GREEN);
		}
		if (evt.getPropertyName().equals("stepping")) {
//			System.out.println("stepping");
			double P = miGui.Pos.value;
			DG645Control.dg645.step(P);
			miGui.updateControls();
		}
	} 
	
	private synchronized void startWait(String Task, int time)
	{
		miGui.timerLabel.start(Task);
		timeout = time;
		notify();
	}

	public void actionPerformed(ActionEvent e) {
				
		if (DG645Control.dg645.isMoving == true)  {
			return;
		}
		
		if (e.getActionCommand().startsWith("Exit")) {
			System.exit(0);
		}		
		if (e.getActionCommand().startsWith("Init")) {
			miGui.Pos.setValue(0.0); 
			startWait("Init",5);			
			DG645Control.dg645.init();	
		}		
		if (e.getActionCommand().startsWith("Stop")) {
			DG645Control.dg645.stop();
			miGui.updateControls();
		}		
		if (e.getActionCommand().startsWith("Move")) {
			miGui.moveButton.setBackground(Color.RED);
			double P = miGui.Pos.value;
			startWait("Move",5);	
			DG645Control.dg645.move(P);
		}	
		if (e.getActionCommand().startsWith("Settings")) {
			miGui.settingsDialog.setVisible(true);
		}	
	}

	public synchronized void run() 
	{
		while (true) {	
			
//	        wait until a Task is send to microscope		
			try { wait(); } catch (InterruptedException e) {}
					
//			waiting for microscope to finish it's task and update Gui
			DG645Control.dg645.waitDone(timeout);
			miGui.updateControls();	
		}
	}

}*/
