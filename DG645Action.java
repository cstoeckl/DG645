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
		
		System.out.println("prop name " +evt.getPropertyName() );
		System.out.println("source name " + evt.getSource());
		
		System.out.println("testing for txthold " + miGui.txtHold);
		if (evt.getPropertyName().equals("Trigger")) {		
			triggerProperty(evt);
		}

		if (evt.getPropertyName().equals("Burst")) {			
			burstProperty(evt);
		}

		if (evt.getPropertyName().equals("Delay")) {		
			delayProperty(evt);	
		}

		if (evt.getPropertyName().equals("Level")) {			
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
		if (DG645Control.dg645.isMoving == true)  {
			return;
		}

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
		if (e.getActionCommand().startsWith("Trigger")) {		
			triggerAction(e);	
		}

		if (e.getActionCommand().startsWith("Burst")) {			
			burstAction(e);
		}

		if (e.getActionCommand().startsWith("Delay")) {		
			delayAction(e);	
		}

		if (e.getActionCommand().startsWith("Level")) {			
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
		//trigger(String mode, String rate, String threshold, String advmode, String hold, String edge, String prescale, String phase)

		if(evt.getSource().equals(miGui.buttonInt))
			DG645Control.dg645.trigger("0", null, null, null, null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonExtR))
			DG645Control.dg645.trigger("1", null, null, null, null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonExtF))
			DG645Control.dg645.trigger("2", null, null, null, null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonSnglExtR))
			DG645Control.dg645.trigger("3", null, null, null, null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonSnglExtF))
			DG645Control.dg645.trigger("4", null, null, null, null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonSngl))
			DG645Control.dg645.trigger("5", null, null, null, null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonLine))
			DG645Control.dg645.trigger("6", null, null, null, null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonTrigOn))
			DG645Control.dg645.trigger(null, null, null, "1", null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonTrigOff))
			DG645Control.dg645.trigger(null, null, null, "0", null, null, null, null);
		else
			System.out.println("Error: Unknown trigger action source.");
	}

	public void triggerProperty(java.beans.PropertyChangeEvent evt)
	{     	
		//trigger(String mode, String rate, String threshold, String advmode, String hold, String edge, String prescale, String phase)

		if(evt.getSource().equals(miGui.txtTrigRate)){
			DG645Control.dg645.trigger(null, miGui.txtTrigRate.getValue().toString(), null, null, null, null, null, null);
		}
		else if(evt.getSource().equals(miGui.txtTrigThres)){
			DG645Control.dg645.trigger(null, null, miGui.txtTrigThres.getValue().toString(), null, null, null, null, null);
		}
		else if(evt.getSource().equals(miGui.txtHold)){
			DG645Control.dg645.trigger(null, null, null, null, miGui.txtHold.getValue().toString(), null, null, null);
		}                                  
		else if(evt.getSource().equals(miGui.txtTrigPres)){
			DG645Control.dg645.trigger(null, null, null, null, null, "0", miGui.txtTrigPres.getValue().toString().replace(",", ""), null);
		}
		else if(evt.getSource().equals(miGui.txtABpres)){
			DG645Control.dg645.trigger(null, null, null, null, null, "1", miGui.txtABpres.getValue().toString().replace(",", ""), null);
		}
		else if(evt.getSource().equals(miGui.txtCDpres)){	
			DG645Control.dg645.trigger(null, null, null, null, null, "2", miGui.txtCDpres.getValue().toString().replace(",", ""), null);
		}
		else if(evt.getSource().equals(miGui.txtEFpres)){
			DG645Control.dg645.trigger(null, null, null, null, null, "3", miGui.txtEFpres.getValue().toString().replace(",", ""), null);
		}
		else if(evt.getSource().equals(miGui.txtGHpres)){
			DG645Control.dg645.trigger(null, null, null, null, null, "4", miGui.txtGHpres.getValue().toString().replace(",", ""), null);
		}
		else if(evt.getSource().equals(miGui.txtABphase)){
			DG645Control.dg645.trigger(null, null, null, null, null, "1", null, miGui.txtABphase.getValue().toString().replace(",", ""));
		}
		else if(evt.getSource().equals(miGui.txtCDphase)){
			DG645Control.dg645.trigger(null, null, null, null, null, "2", null, miGui.txtCDphase.getValue().toString().replace(",", ""));
		}
		else if(evt.getSource().equals(miGui.txtEFphase)){
			DG645Control.dg645.trigger(null, null, null, null, null, "3", null, miGui.txtEFphase.getValue().toString().replace(",", ""));
		}
		else if(evt.getSource().equals(miGui.txtGHphase)){
			DG645Control.dg645.trigger(null, null, null, null, null, "4", null, miGui.txtGHphase.getValue().toString().replace(",", ""));
		}
		else
			System.out.println("Error: Unknown trigger property source.");
	}

	public void delayAction(java.awt.event.ActionEvent evt)
	{
		//delay(String delayChannel, String linkChannel, String value)
		if(evt.getSource().equals(miGui.cboxChannelA)){
			try{
				int index = miGui.cboxChannelA.getSelectedIndex();
				
				if(index < 1)
					DG645Control.dg645.delay("2", String.valueOf(index), null);
				else
					DG645Control.dg645.delay("2", String.valueOf(index+2), null);

				miGui.checkError();
				miGui.updateDelays("2");

			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxChannelB)){
			try{
				int index = miGui.cboxChannelB.getSelectedIndex();
				if(index == 0)
				DG645Control.dg645.delay("3", String.valueOf(index), null);
				else if(index < 2)
				DG645Control.dg645.delay("3", String.valueOf(index+1), null);
				else
				DG645Control.dg645.delay("3", String.valueOf(index+2), null);

				miGui.checkError();

				miGui.updateDelays("3");
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxChannelC)){
			try{
				int index = miGui.cboxChannelC.getSelectedIndex();
				if(index == 0)
				DG645Control.dg645.delay("4", String.valueOf(index), null);
				else if(index < 3)
				DG645Control.dg645.delay("4", String.valueOf(index+1), null);
				else
				DG645Control.dg645.delay("4", String.valueOf(index+2), null);

				miGui.checkError();    
				miGui.updateDelays("4");
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxChannelD)){
			try{
				int index = miGui.cboxChannelD.getSelectedIndex();
				if(index == 0)
				DG645Control.dg645.delay("5", String.valueOf(index), null);
				else if(index < 4)
				DG645Control.dg645.delay("5", String.valueOf(index+1), null);
				else
				DG645Control.dg645.delay("5", String.valueOf(index+2), null);

				miGui.checkError();
				miGui.updateDelays("5");
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxChannelE)){
			try{
				int index = miGui.cboxChannelE.getSelectedIndex();
				if(index == 0)
				DG645Control.dg645.delay("6", String.valueOf(index), null);
				else if(index < 5)
				DG645Control.dg645.delay("6", String.valueOf(index+1), null);
				else
				DG645Control.dg645.delay("6", String.valueOf(index+2), null);

				miGui.checkError();
				miGui.updateDelays("6");
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxChannelF)){
			try{
				int index = miGui.cboxChannelF.getSelectedIndex();
				if(index == 0)
				DG645Control.dg645.delay("7", String.valueOf(index), null);
				else if(index < 6)
				DG645Control.dg645.delay("7", String.valueOf(index+1), null);
				else
				DG645Control.dg645.delay("7", String.valueOf(index+2), null);

				miGui.checkError();
				miGui.updateDelays("7");
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxChannelG)){
			try{
				int index = miGui.cboxChannelG.getSelectedIndex();
				if(index == 0)
				DG645Control.dg645.delay("8", String.valueOf(index), null);
				else if(index < 7)
				DG645Control.dg645.delay("8", String.valueOf(index+1), null);
				else
				DG645Control.dg645.delay("8", String.valueOf(index+2), null);

				miGui.checkError();
				miGui.updateDelays("8");
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxChannelH)){
			try{
				int index = miGui.cboxChannelH.getSelectedIndex();
				if(index == 0)
				DG645Control.dg645.delay("9", String.valueOf(index), null);
				else if(index < 9)
				DG645Control.dg645.delay("9", String.valueOf(index+1), null);
				else
				DG645Control.dg645.delay("9", String.valueOf(index+2), null);

				miGui.checkError();
				miGui.updateDelays("9");
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.cboxPMA)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 2");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPMA.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);
				
				DG645Control.dg645.delay("2", null, replace);
				
				miGui.updateT1();
				miGui.checkError();
			}catch(Exception e){}
		} 
		else if(evt.getSource().equals(miGui.cboxPMB)){
			try{

				DG645Control.dg645.mConn.writeLine("DLAY? 3");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPMB.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);

				DG645Control.dg645.delay("3", null, replace);


				miGui.updateT1();
				miGui.checkError();  
			}catch(Exception e){}
		} 
		else if(evt.getSource().equals(miGui.cboxPMC)){
			try{

				DG645Control.dg645.mConn.writeLine("DLAY? 4");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPMC.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);

				DG645Control.dg645.delay("4", null, replace);


				miGui.updateT1();
				miGui.checkError();  
			}catch(Exception e){}
		} 
		else if(evt.getSource().equals(miGui.cboxPMD)){
			try{

				DG645Control.dg645.mConn.writeLine("DLAY? 5");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPMD.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);

				DG645Control.dg645.delay("5", null, replace);


				miGui.updateT1();
				miGui.checkError();
			}catch(Exception e){}
		} 
		else if(evt.getSource().equals(miGui.cboxPME)){
			try{

				DG645Control.dg645.mConn.writeLine("DLAY? 6");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPME.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);

				DG645Control.dg645.delay("6", null, replace);


				miGui.updateT1();
				miGui.checkError();
			}catch(Exception e){}
		} 
		else if(evt.getSource().equals(miGui.cboxPMF)){
			try{

				DG645Control.dg645.mConn.writeLine("DLAY? 7");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPMF.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);

				DG645Control.dg645.delay("7", null, replace);


				miGui.updateT1();
				miGui.checkError();  
			}catch(Exception e){}
		}  
		else if(evt.getSource().equals(miGui.cboxPMG)){
			try{

				DG645Control.dg645.mConn.writeLine("DLAY? 8");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPMG.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);

				DG645Control.dg645.delay("8", null, replace);


				miGui.updateT1();
				miGui.checkError();

			}catch(Exception e){}
		} 
		else if(evt.getSource().equals(miGui.cboxPMH)){
			try{

				DG645Control.dg645.mConn.writeLine("DLAY? 9");
				String current = DG645Control.dg645.mConn.readLine();
				String replace = current.substring(0, 2);

				int index = miGui.cboxPMH.getSelectedIndex();
				if(index == 0)
					replace = replace + "+";
				else
					replace = replace + "-";

				replace = replace + current.substring(3);


				DG645Control.dg645.delay("9", null, replace);


				miGui.updateT1();
				miGui.checkError();
			}catch(Exception e){}
		}
		else
			System.out.println("Error: Unknown delay action source.");
	}

	public void delayProperty(java.beans.PropertyChangeEvent evt)
	{
		//delay(String delayChannel, String linkChannel, String value)
		
		if(evt.getSource().equals(miGui.txtValA)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 2");
				DG645Control.dg645.delay("2", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValA.getValue());
				
				miGui.updateT1();
				miGui.checkError();
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.txtValB)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 3");
				
				DG645Control.dg645.delay("3", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValB.getValue());
				miGui.updateT1();        
				miGui.checkError();   
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.txtValC)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 4");
				
				DG645Control.dg645.delay("4", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValC.getValue());
				miGui.updateT1();      
				miGui.checkError();   
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.txtValD)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 5");
				
				DG645Control.dg645.delay("5", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValD.getValue());
				miGui.updateT1();     
				miGui.checkError();    
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.txtValE)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 6");
				
				DG645Control.dg645.delay("6", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValE.getValue());
				miGui.updateT1();     
				miGui.checkError();    
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.txtValF)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 7");
				DG645Control.dg645.delay("7", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValF.getValue());
				miGui.updateT1();  
				miGui.checkError();
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.txtValG)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 8");
				
				DG645Control.dg645.delay("8", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValG.getValue());
				miGui.updateT1();  
				miGui.checkError();  
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.txtValH)){
			try{
				DG645Control.dg645.mConn.writeLine("DLAY? 9");
				
				DG645Control.dg645.delay("9", null, DG645Control.dg645.mConn.readLine().substring(0, 3) + miGui.txtValH.getValue());
				miGui.updateT1();   
				miGui.checkError();   
			}catch(Exception e){}
		}
		else
			System.out.println("Error: Unknown delay property source.");
	}

	public void burstAction(java.awt.event.ActionEvent evt)
	{ 
		//burst(String burstStatus, String fireon, String cnt, String period, String delay)
		if(evt.getSource().equals(miGui.buttonOn))
			DG645Control.dg645.burst("1", null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonOff))
			DG645Control.dg645.burst("0", null, null, null, null);
		else if(evt.getSource().equals(miGui.buttonOutputFirst))
			DG645Control.dg645.burst(null, "1", null, null, null);
		else if(evt.getSource().equals(miGui.buttonOutputAll))
			DG645Control.dg645.burst(null, "0", null, null, null);
		else
			System.out.println("Error: Unknown burst action source.");
	}

	public void burstProperty(java.beans.PropertyChangeEvent evt)
	{
		//burst(String burstStatus, String fireon, String cnt, String period, String delay)
		if(evt.getSource().equals(miGui.txtCNT))
			DG645Control.dg645.burst(null, null, miGui.txtCNT.getValue().toString().replace(",", ""), null, null);
		else if(evt.getSource().equals(miGui.txtPeriod))
			DG645Control.dg645.burst(null, null, null, miGui.txtPeriod.getValue().toString(), null);
		else if(evt.getSource().equals(miGui.txtDelay)){
			try{
				DG645Control.dg645.burst(null, null, null, null, miGui.txtDelay.getValue().toString());

				DG645Control.dg645.mConn.writeLine("BURD?");

				String temp = DG645Control.dg645.mConn.readLine().substring(1);
				temp = ("0.000000000000" + temp).substring(temp.length());

				miGui.txtDelay.setText(temp);
			}catch(Exception e){}
		}
		else
			System.out.println("Error: Unknown burst property source.");
	}

	public void levelAction(java.awt.event.ActionEvent evt)
	{
		//level(channel, offset, amp, polarity)

		if(evt.getSource().equals(miGui.cboxLevel)){
			try{

				String index = String.valueOf(miGui.cboxLevel.getSelectedIndex());

				DG645Control.dg645.mConn.writeLine("LAMP? " + index);
				miGui.txtAmp.setValue(DG645Control.dg645.mConn.readLine().substring(1));
				DG645Control.dg645.mConn.writeLine("LOFF? " + index);
				miGui.txtOffset.setValue(DG645Control.dg645.mConn.readLine());//.substring(1));
				DG645Control.dg645.mConn.writeLine("LPOL? " + index);
				if(DG645Control.dg645.mConn.readLine().equals("0")){	
					miGui.levelPolarity.setSelected(miGui.buttonNeg.getModel(), true);
					DG645Control.dg645.level(String.valueOf(miGui.cboxLevel.getSelectedIndex()), miGui.txtOffset.getValue().toString(), miGui.txtAmp.getValue().toString(), "0");
				}
				else{
					miGui.levelPolarity.setSelected(miGui.buttonPos.getModel(), true);
					DG645Control.dg645.level(String.valueOf(miGui.cboxLevel.getSelectedIndex()), miGui.txtOffset.getValue().toString(), miGui.txtAmp.getValue().toString(), "1");
				}
			}catch(Exception e){}
		}
		else if(evt.getSource().equals(miGui.buttonPos)) 
			DG645Control.dg645.level(null, null, null, "1"); 
		else if(evt.getSource().equals(miGui.buttonNeg))
			DG645Control.dg645.level(null, null, null, "0"); 
		else
			System.out.println("Error: Unknown level action source.");
	}

	public void levelProperty(java.beans.PropertyChangeEvent evt)
	{
		//level(channel, offset, amp, polarity)
		if(evt.getSource().equals(miGui.txtOffset))
			DG645Control.dg645.level(null, miGui.txtOffset.getValue().toString(), null, null);
		else if(evt.getSource().equals(miGui.txtAmp))
			DG645Control.dg645.level(null, null, miGui.txtAmp.getValue().toString(), null);
		else
			System.out.println("Error: Unknown level property source.");
	}
}
