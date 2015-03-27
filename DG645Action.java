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
public class DG645Action implements Runnable, PropertyChangeListener, ActionListener 
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
	}

	public void propertyChange(PropertyChangeEvent evt) {
		/*if (evt.getPropertyName().equals("edited") &&
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
		}*/
		//END OLD

		//new

		//check for all trigger sources
		if(evt.getSource().getClass().getName().endsWith("Trigger")){
			triggerProperty(evt);
		}

		//check for all burst sources
		if(evt.getSource().getClass().getName().endsWith("Burst")){
			burstProperty(evt);
		}

		//check for all delay sources
		if(evt.getSource().getClass().getName().endsWith("Delay")){
			delayProperty(evt);
		}

		//check for all level sources
		if(evt.getSource().getClass().getName().endsWith("Level")){
			levelProperty(evt);
		}

	} 

	private synchronized void startWait(String Task, int time)
	{
		//miGui.timerLabel.start(Task);
		timeout = time;
		notify();
	}

	public void actionPerformed(ActionEvent e) {
		//OLD		
		/*if (DG645Control.dg645.isMoving == true)  {
			return;
		}*/

		/*if (e.getActionCommand().startsWith("Exit")) {
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
		}	*/
		//END OLD

		//new
		if (e.getSource().getClass().getName().endsWith("Trigger")) {		
			triggerAction(e);	
		}

		if (e.getSource().getClass().getName().endsWith("Burst")) {			
			burstAction(e);
		}

		if (e.getSource().getClass().getName().endsWith("Delay")) {		
			delayAction(e);	
		}

		if (e.getSource().getClass().getName().endsWith("Level")) {			
			levelAction(e);
		}
	}

	public synchronized void run() 
	{
		while (true) {	

			//	        wait until a Task is send to microscope		
			try { wait(); } catch (InterruptedException e) {}

			//			waiting for microscope to finish it's task and update Gui
			DG645Control.dg645.waitDone(timeout);

			//miGui.updateControls();	
		}
	}


	public void triggerAction(java.awt.event.ActionEvent evt)
	{
		try{
			//trigger(String mode, String rate, String threshold, String advmode, String hold, String edge, String prescale, String phase)

			if(evt.getSource().equals(miGui.panelTrigger.buttonInt))
				DG645Control.dg645.trigger("0", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonExtR))
				DG645Control.dg645.trigger("1", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonExtF))
				DG645Control.dg645.trigger("2", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonSnglExtR))
				DG645Control.dg645.trigger("3", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonSnglExtF))
				DG645Control.dg645.trigger("4", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonSngl))
				DG645Control.dg645.trigger("5", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonLine))
				DG645Control.dg645.trigger("6", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonTrigOn))
				DG645Control.dg645.trigger(null, null, null, "1", null, null, null, null);
			else if(evt.getSource().equals(miGui.panelTrigger.buttonTrigOff))
				DG645Control.dg645.trigger(null, null, null, "0", null, null, null, null);
			else
				System.out.println("Error: Unknown trigger action source.");
		}catch(Exception e) {}
		
	}

	public void triggerProperty(java.beans.PropertyChangeEvent evt)
	{     	
		//trigger(String mode, String rate, String threshold, String advmode, String hold, String edge, String prescale, String phase)

		try{
			if(evt.getSource().equals(miGui.panelTrigger.txtTrigRate)){
				DG645Control.dg645.trigger(null, miGui.panelTrigger.txtTrigRate.getValue().toString(), null, null, null, null, null, null);
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtTrigThres)){
				DG645Control.dg645.trigger(null, null, miGui.panelTrigger.txtTrigThres.getValue().toString(), null, null, null, null, null);
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtHold)){
				System.out.println("txthold " + miGui.panelTrigger.txtHold.getValue().toString());
				DG645Control.dg645.trigger(null, null, null, null, miGui.panelTrigger.txtHold.getValue().toString(), null, null, null);
			}                                  
			else if(evt.getSource().equals(miGui.panelTrigger.txtTrigPres)){
				DG645Control.dg645.trigger(null, null, null, null, null, "0", miGui.panelTrigger.txtTrigPres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtABpres)){
				DG645Control.dg645.trigger(null, null, null, null, null, "1", miGui.panelTrigger.txtABpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtCDpres)){	
				DG645Control.dg645.trigger(null, null, null, null, null, "2", miGui.panelTrigger.txtCDpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtEFpres)){
				DG645Control.dg645.trigger(null, null, null, null, null, "3", miGui.panelTrigger.txtEFpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtGHpres)){
				DG645Control.dg645.trigger(null, null, null, null, null, "4", miGui.panelTrigger.txtGHpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtABphase)){
				DG645Control.dg645.trigger(null, null, null, null, null, "1", null, miGui.panelTrigger.txtABphase.getValue().toString().replace(",", ""));
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtCDphase)){
				DG645Control.dg645.trigger(null, null, null, null, null, "2", null, miGui.panelTrigger.txtCDphase.getValue().toString().replace(",", ""));
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtEFphase)){
				DG645Control.dg645.trigger(null, null, null, null, null, "3", null, miGui.panelTrigger.txtEFphase.getValue().toString().replace(",", ""));
			}
			else if(evt.getSource().equals(miGui.panelTrigger.txtGHphase)){
				DG645Control.dg645.trigger(null, null, null, null, null, "4", null, miGui.panelTrigger.txtGHphase.getValue().toString().replace(",", ""));
			}
			else
				System.out.println("Error: Unknown trigger property source.");
		}catch(Exception e) {}
		
	}

	public void delayAction(java.awt.event.ActionEvent evt)
	{
		//delay(String delayChannel, String linkChannel, String value)
		
		try{
			String channel = "-1";
			int link = -1;
			
			if(evt.getSource().equals(miGui.panelDelay.panelA.cbox)){
					link = miGui.panelDelay.panelA.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelB.cbox)){
					 link = miGui.panelDelay.panelB.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelC.cbox)){
					link = miGui.panelDelay.panelC.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelD.cbox)){
				link = miGui.panelDelay.panelD.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelE.cbox)){
				link = miGui.panelDelay.panelE.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelF.cbox)){
				link = miGui.panelDelay.panelF.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelG.cbox)){
				link = miGui.panelDelay.panelG.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelH.cbox)){
				link = miGui.panelDelay.panelH.cbox.getSelectedIndex();
			}
			else
				System.out.println("Error: Unknown delay action source.");
			
			if(link == 0)
				DG645Control.dg645.delay(channel, String.valueOf(link), null);
			else if(link < Integer.parseInt(channel))
				DG645Control.dg645.delay(channel, String.valueOf(link+1), null);
			else
				DG645Control.dg645.delay(channel, String.valueOf(link+2), null);

			miGui.checkError();
			miGui.panelDelay.updateDelays(channel);
			
		}catch(Exception e){}
		
	}

	public void delayProperty(java.beans.PropertyChangeEvent evt)
	{
		System.out.println(evt.getSource());
		try{
			if(evt.getSource().equals(miGui.panelDelay.panelA.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 2");
					DG645Control.dg645.delay("2", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelA.value.getValue());
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelB.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 3");
					DG645Control.dg645.delay("3", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelB.value.getValue()); 
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelC.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 4");
					DG645Control.dg645.delay("4", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelC.value.getValue());
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelD.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 5");
					DG645Control.dg645.delay("5", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelD.value.getValue());
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelE.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 6");
					DG645Control.dg645.delay("6", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelE.value.getValue());
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelF.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 7");
					DG645Control.dg645.delay("7", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelF.value.getValue());
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelG.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 8");
					DG645Control.dg645.delay("8", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelG.value.getValue()); 
			}
			else if(evt.getSource().equals(miGui.panelDelay.panelH.value)){
					DG645Control.dg645.mConn.writeLine("DLAY? 9");
					//DG645Control.dg645.delay("9", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.panelDelay.panelH.value.getValue());
					System.out.println(DG645Control.dg645.mConn.readLine());
			}
			else
				System.out.println("Error: Unknown delay property source.");
			
			miGui.panelDelay.updateT1();   
			miGui.checkError();  
			
		}catch(Exception e){}
	}

	public void burstAction(java.awt.event.ActionEvent evt)
	{ 
		try{
			//burst(String burstStatus, String fireon, String cnt, String period, String delay)

			if(evt.getSource().equals(miGui.panelBurst.buttonOn))
				DG645Control.dg645.burst("1", null, null, null, null);
			else if(evt.getSource().equals(miGui.panelBurst.buttonOff))
				DG645Control.dg645.burst("0", null, null, null, null);
			else if(evt.getSource().equals(miGui.panelBurst.buttonOutputFirst))
				DG645Control.dg645.burst(null, "1", null, null, null);
			else if(evt.getSource().equals(miGui.panelBurst.buttonOutputAll))
				DG645Control.dg645.burst(null, "0", null, null, null);
			else
				System.out.println("Error: Unknown burst action source.");
		}catch(Exception e){}
	}

	public void burstProperty(java.beans.PropertyChangeEvent evt)
	{
		try{
			//burst(String burstStatus, String fireon, String cnt, String period, String delay)
			if(evt.getSource().equals(miGui.panelBurst.txtCNT))
				DG645Control.dg645.burst(null, null, miGui.panelBurst.txtCNT.getValue().toString().replace(",", ""), null, null);
			else if(evt.getSource().equals(miGui.panelBurst.txtPeriod))
				DG645Control.dg645.burst(null, null, null, miGui.panelBurst.txtPeriod.getValue().toString(), null);
			else if(evt.getSource().equals(miGui.panelBurst.txtDelay)){
				try{
					DG645Control.dg645.burst(null, null, null, null, miGui.panelBurst.txtDelay.getValue().toString());

					DG645Control.dg645.mConn.writeLine("BURD?");

					String temp = DG645Control.dg645.mConn.readLine().substring(1);
					temp = ("0.000000000000" + temp).substring(temp.length());

					miGui.panelBurst.txtDelay.setText(temp);
				}catch(Exception e){}
			}
			else
				System.out.println("Error: Unknown burst property source.");
		}catch(Exception e){}
		
	}

	public void levelAction(java.awt.event.ActionEvent evt)
	{
		//level(channel, offset, amp, polarity)

		if(evt.getSource().equals(miGui.panelLevel.cboxLevel)){
			try{

				String index = String.valueOf(miGui.panelLevel.cboxLevel.getSelectedIndex());

				DG645Control.dg645.mConn.writeLine("LAMP? " + index);
				miGui.panelLevel.txtAmp.setValue(DG645Control.dg645.mConn.readLine().substring(1));
				DG645Control.dg645.mConn.writeLine("LOFF? " + index);
				miGui.panelLevel.txtOffset.setValue(DG645Control.dg645.mConn.readLine());//.substring(1));
				DG645Control.dg645.mConn.writeLine("LPOL? " + index);
				if(DG645Control.dg645.mConn.readLine().equals("0")){	
					miGui.panelLevel.levelPolarity.setSelected(miGui.panelLevel.buttonNeg.getModel(), true);
					DG645Control.dg645.level(String.valueOf(miGui.panelLevel.cboxLevel.getSelectedIndex()), miGui.panelLevel.txtOffset.getValue().toString(), miGui.panelLevel.txtAmp.getValue().toString(), "0");
				}
				else{
					miGui.panelLevel.levelPolarity.setSelected(miGui.panelLevel.buttonPos.getModel(), true);
					DG645Control.dg645.level(String.valueOf(miGui.panelLevel.cboxLevel.getSelectedIndex()), miGui.panelLevel.txtOffset.getValue().toString(), miGui.panelLevel.txtAmp.getValue().toString(), "1");
				}
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.panelLevel.buttonPos)) 
			DG645Control.dg645.level(null, null, null, "1"); 
		else if(evt.getSource().equals(miGui.panelLevel.buttonNeg))
			DG645Control.dg645.level(null, null, null, "0"); 
		else
			System.out.println("Error: Unknown level action source.");
	}

	public void levelProperty(java.beans.PropertyChangeEvent evt)
	{
		//level(channel, offset, amp, polarity)
		if(evt.getSource().equals(miGui.panelLevel.txtOffset))
			DG645Control.dg645.level(null, miGui.panelLevel.txtOffset.getValue().toString(), null, null);
		else if(evt.getSource().equals(miGui.panelLevel.txtAmp))
			DG645Control.dg645.level(null, null, miGui.panelLevel.txtAmp.getValue().toString(), null);
		else
			System.out.println("Error: Unknown level property source.");
	}
}
