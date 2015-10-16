import java.awt.event.*;
import java.beans.*;

public class DG645Action implements Runnable, PropertyChangeListener, ActionListener 
{
	private static final long serialVersionUID = 1L;

	private DG645Gui gui;

	//private Thread waitThread;
	private int timeout = 5;

	public DG645Action(DG645Gui gui)
	{	
		this.gui = gui;	
		//waitThread = new Thread(this,"waitDone");
		//waitThread.start();
	}

	//	 separate function to be callable by main programm
	/*public void connect()
	{
		startWait("Connect",5);
		gui.settings.dg645.connect();
	}*/
	
/*	private synchronized void startWait(String Task, int time)
	{
		//gui.timerLabel.start(Task);
		timeout = time;
		notify();
	}*/
	
	public synchronized void run() 
	{
		while (true) {	

			//	        wait until a Task is send to microscope		
			try { wait(); } catch (InterruptedException e) {}

			//			waiting for microscope to finish it's task and update Gui
			gui.settings.dg645.waitDone(timeout);

			//gui.updateControls();	
		}
	}

	public void propertyChange(PropertyChangeEvent evt) {
		//check for all trigger sources
		//if(evt.getSource().getClass().getName().endsWith("Trigger")){
		triggerProperty(evt);
		//}

		//check for all burst sources
		//if(evt.getSource().getClass().getName().endsWith("Burst")){
		burstProperty(evt);
		//	}

		//check for all delay sources
		//	if(evt.getSource().getClass().getName().endsWith("Delay")){
		delayProperty(evt);
		//	}

		//check for all level sources
		//	if(evt.getSource().getClass().getName().endsWith("Level")){
		levelProperty(evt);
		//	}

	} 

	public void actionPerformed(ActionEvent e) {
		/*System.out.println("at dg645 action");

		System.out.println(e.getSource().getClass().getName());

		System.out.println("action command: " + e.getActionCommand());

		System.out.println("source: " + e.getSource());
		System.out.println("class: " + e.getClass());
		System.out.println("id" + e.getID());
		System.out.println("modifier" + e.getModifiers());

		System.out.println("when" + e.getWhen());
*/
		if(e.getSource().equals(gui.menuSettings))
		{
			//if(ac.equalsIgnoreCase("Settings"))
				gui.settings.setVisible(true);
		}
		
		if(e.getSource().equals(gui.menuSave))
		{
				gui.save.setVisible(true);
		}
		
		//if (e.getSource().getClass().getName().endsWith("Trigger")) {
		triggerAction(e);	
		//}

		//	if (e.getSource().getClass().getName().endsWith("Burst")) {			
		burstAction(e);
		//	}

		//	if (e.getSource().getClass().getName().endsWith("Delay")) {		
	//	delayAction(e);	
		//	}

		//	if (e.getSource().getClass().getName().endsWith("Level")) {			
		levelAction(e);
		//	}
	}

	public void triggerAction(java.awt.event.ActionEvent evt)
	{
		try{
			//trigger(String mode, String rate, String threshold, String advmode, String hold, String edge, String prescale, String phase)

			if(evt.getSource().equals(gui.panelTrigger.buttonInt))
				gui.settings.dg645.trigger("0", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonExtR))
				gui.settings.dg645.trigger("1", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonExtF))
				gui.settings.dg645.trigger("2", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonSnglExtR))
				gui.settings.dg645.trigger("3", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonSnglExtF))
				gui.settings.dg645.trigger("4", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonSngl))
				gui.settings.dg645.trigger("5", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonLine))
				gui.settings.dg645.trigger("6", null, null, null, null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonTrigOn))
				gui.settings.dg645.trigger(null, null, null, "1", null, null, null, null);
			else if(evt.getSource().equals(gui.panelTrigger.buttonTrigOff))
				gui.settings.dg645.trigger(null, null, null, "0", null, null, null, null);
			else {}
			//System.out.println("Error: Unknown trigger action source.");
		}catch(Exception e) {}

	}

	public void triggerProperty(java.beans.PropertyChangeEvent evt)
	{     	
		//trigger(String mode, String rate, String threshold, String advmode, String hold, String edge, String prescale, String phase)
		try{
			if(evt.getSource().equals(gui.panelTrigger.txtTrigRate)){
				System.out.println("txtTrigrate " + gui.panelTrigger.txtTrigRate.getValue().toString() );
				gui.settings.dg645.trigger(null, gui.panelTrigger.txtTrigRate.getValue().toString(), null, null, null, null, null, null);
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtTrigThres)){
				String temp = gui.panelTrigger.txtTrigThres.getValue().toString();
				if(temp.startsWith("+"))
					temp = temp.substring(1, temp.length());
				gui.settings.dg645.trigger(null, null, temp, null, null, null, null, null);
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtHold)){
				System.out.println("txthold " + gui.panelTrigger.txtHold.getValue().toString());
				gui.settings.dg645.trigger(null, null, null, null, gui.panelTrigger.txtHold.getValue().toString(), null, null, null);
			}                                  
			else if(evt.getSource().equals(gui.panelTrigger.txtTrigPres)){
				gui.settings.dg645.trigger(null, null, null, null, null, "0", gui.panelTrigger.txtTrigPres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtABpres)){
				gui.settings.dg645.trigger(null, null, null, null, null, "1", gui.panelTrigger.txtABpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtCDpres)){	
				gui.settings.dg645.trigger(null, null, null, null, null, "2", gui.panelTrigger.txtCDpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtEFpres)){
				gui.settings.dg645.trigger(null, null, null, null, null, "3", gui.panelTrigger.txtEFpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtGHpres)){
				gui.settings.dg645.trigger(null, null, null, null, null, "4", gui.panelTrigger.txtGHpres.getValue().toString().replace(",", ""), null);
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtABphase)){
				gui.settings.dg645.trigger(null, null, null, null, null, "1", null, gui.panelTrigger.txtABphase.getValue().toString().replace(",", ""));
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtCDphase)){
				gui.settings.dg645.trigger(null, null, null, null, null, "2", null, gui.panelTrigger.txtCDphase.getValue().toString().replace(",", ""));
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtEFphase)){
				gui.settings.dg645.trigger(null, null, null, null, null, "3", null, gui.panelTrigger.txtEFphase.getValue().toString().replace(",", ""));
			}
			else if(evt.getSource().equals(gui.panelTrigger.txtGHphase)){
				gui.settings.dg645.trigger(null, null, null, null, null, "4", null, gui.panelTrigger.txtGHphase.getValue().toString().replace(",", ""));
			}
			else {}
			//System.out.println("Error: Unknown trigger property source.");
		}catch(Exception e) {}

	}

	public void delayAction(java.awt.event.ActionEvent evt)
	{
		//delay(String delayChannel, String linkChannel, String value)

		try{
			String channel = "-1";
			int link = -1;

			if(evt.getSource().equals(gui.panelDelay.panelA.cbox)){
				channel = "2";
				link = gui.panelDelay.panelA.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(gui.panelDelay.panelB.cbox)){
				channel = "3";
				link = gui.panelDelay.panelB.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(gui.panelDelay.panelC.cbox)){
				channel = "4";
				link = gui.panelDelay.panelC.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(gui.panelDelay.panelD.cbox)){
				channel = "5";
				link = gui.panelDelay.panelD.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(gui.panelDelay.panelE.cbox)){
				channel = "6";
				link = gui.panelDelay.panelE.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(gui.panelDelay.panelF.cbox)){
				channel = "7";
				link = gui.panelDelay.panelF.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(gui.panelDelay.panelG.cbox)){
				channel = "8";
				link = gui.panelDelay.panelG.cbox.getSelectedIndex();
			}
			else if(evt.getSource().equals(gui.panelDelay.panelH.cbox)){
				channel = "9";
				link = gui.panelDelay.panelH.cbox.getSelectedIndex();
			}
			else {}
			//System.out.println("Error: Unknown delay action source.");

			if(link != -1)
			{
				if(link == 0)
					gui.settings.dg645.delay(channel, String.valueOf(link), null);
				else if(link < Integer.parseInt(channel))
					gui.settings.dg645.delay(channel, String.valueOf(link+1), null);
				else
					gui.settings.dg645.delay(channel, String.valueOf(link+2), null);

				gui.checkError();
				gui.panelDelay.updateDelays(channel);	
			}

		}catch(Exception e){}

	}

	public void delayProperty(java.beans.PropertyChangeEvent evt)
	{
		try{
			if(evt.getSource().equals(gui.panelDelay.panelA.value)){
				gui.settings.dg645.delay("2", gui.panelDelay.panelA.value.getValue());
			}
			else if(evt.getSource().equals(gui.panelDelay.panelB.value)){
				gui.settings.dg645.delay("3", gui.panelDelay.panelB.value.getValue()); 
			}
			else if(evt.getSource().equals(gui.panelDelay.panelC.value)){
				gui.settings.dg645.delay("4", gui.panelDelay.panelC.value.getValue());
			}
			else if(evt.getSource().equals(gui.panelDelay.panelD.value)){
				gui.settings.dg645.delay("5", gui.panelDelay.panelD.value.getValue());
			}
			else if(evt.getSource().equals(gui.panelDelay.panelE.value)){
				gui.settings.dg645.delay("6", gui.panelDelay.panelE.value.getValue());
			}
			else if(evt.getSource().equals(gui.panelDelay.panelF.value)){
				gui.settings.dg645.delay("7", gui.panelDelay.panelF.value.getValue());
			}
			else if(evt.getSource().equals(gui.panelDelay.panelG.value)){
				gui.settings.dg645.delay("8", gui.panelDelay.panelG.value.getValue()); 
			}
			else if(evt.getSource().equals(gui.panelDelay.panelH.value)){
				gui.settings.dg645.delay("9", gui.panelDelay.panelH.value.getValue());
			}
			else {}
			//System.out.println("Error: Unknown delay property source.");

			gui.panelDelay.updateT1();   
			gui.checkError();  

		}catch(Exception e){}
	}

	public void burstAction(java.awt.event.ActionEvent evt)
	{ 
		try{
			//burst(String burstStatus, String fireon, String cnt, String period, String delay)

			if(evt.getSource().equals(gui.panelBurst.buttonOn))
				gui.settings.dg645.burst("1", null, null, null, null);
			else if(evt.getSource().equals(gui.panelBurst.buttonOff))
				gui.settings.dg645.burst("0", null, null, null, null);
			else if(evt.getSource().equals(gui.panelBurst.buttonOutputFirst))
				gui.settings.dg645.burst(null, "1", null, null, null);
			else if(evt.getSource().equals(gui.panelBurst.buttonOutputAll))
				gui.settings.dg645.burst(null, "0", null, null, null);
			else {}
			//System.out.println("Error: Unknown burst action source.");
		}catch(Exception e){}
	}

	public void burstProperty(java.beans.PropertyChangeEvent evt)
	{
		try{
			//burst(String burstStatus, String fireon, String cnt, String period, String delay)
			if(evt.getSource().equals(gui.panelBurst.txtCNT))
				gui.settings.dg645.burst(null, null, gui.panelBurst.txtCNT.getValue().toString().replace(",", ""), null, null);
			else if(evt.getSource().equals(gui.panelBurst.txtPeriod))
				gui.settings.dg645.burst(null, null, null, gui.panelBurst.txtPeriod.getValue().toString(), null);
			else if(evt.getSource().equals(gui.panelBurst.txtDelay)){
					gui.settings.dg645.burst(null, null, null, null, gui.panelBurst.txtDelay.getValue().toString());

					gui.settings.dg645.mConn.writeLine("BURD?");

					String temp = gui.settings.dg645.mConn.readLine().substring(1);
					temp = ("0.000000000000" + temp).substring(temp.length());

					gui.panelBurst.txtDelay.setText(temp);
			}
			else {}
			//System.out.println("Error: Unknown burst property source.");
		}catch(Exception e){}

	}

	public void levelAction(java.awt.event.ActionEvent evt)
	{
		//level(channel, offset, amp, polarity)
		try{
			if(evt.getSource().equals(gui.panelLevel.cboxLevel)){

				String index = String.valueOf(gui.panelLevel.cboxLevel.getSelectedIndex());

				gui.settings.dg645.mConn.writeLine("LAMP? " + index);
				gui.panelLevel.txtAmp.setValue(gui.settings.dg645.mConn.readLine().substring(1));
				gui.settings.dg645.mConn.writeLine("LOFF? " + index);
				gui.panelLevel.txtOffset.setValue(gui.settings.dg645.mConn.readLine());//.substring(1));
				gui.settings.dg645.mConn.writeLine("LPOL? " + index);
				if(gui.settings.dg645.mConn.readLine().equals("0")){	
					gui.panelLevel.levelPolarity.setSelected(gui.panelLevel.buttonNeg.getModel(), true);
					gui.settings.dg645.level(String.valueOf(gui.panelLevel.cboxLevel.getSelectedIndex()), gui.panelLevel.txtOffset.getValue().toString(), gui.panelLevel.txtAmp.getValue().toString(), "0");
				}
				else{
					gui.panelLevel.levelPolarity.setSelected(gui.panelLevel.buttonPos.getModel(), true);
					gui.settings.dg645.level(String.valueOf(gui.panelLevel.cboxLevel.getSelectedIndex()), gui.panelLevel.txtOffset.getValue().toString(), gui.panelLevel.txtAmp.getValue().toString(), "1");
				}

			}
			else if(evt.getSource().equals(gui.panelLevel.buttonPos)) 
				gui.settings.dg645.level(null, null, null, "1"); 
			else if(evt.getSource().equals(gui.panelLevel.buttonNeg))
				gui.settings.dg645.level(null, null, null, "0"); 
			else {}
			//System.out.println("Error: Unknown level action source.");
		}catch(Exception e){}
	}

	public void levelProperty(java.beans.PropertyChangeEvent evt)
	{
		//level(channel, offset, amp, polarity)
		try{
			if(evt.getSource().equals(gui.panelLevel.txtOffset))

				gui.settings.dg645.level(null, gui.panelLevel.txtOffset.getValue().toString(), null, null);
			else if(evt.getSource().equals(gui.panelLevel.txtAmp))
				gui.settings.dg645.level(null, null, gui.panelLevel.txtAmp.getValue().toString(), null);
			else {}
			//System.out.println("Error: Unknown level property source.");
		}catch(Exception e){}
	}
}