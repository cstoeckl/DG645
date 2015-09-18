import java.text.DecimalFormat;

import lib.*;
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
public class DG645 implements Runnable {

	public DeviceConnection mConn=null;
	private Thread work;
	public  String host;
	public int port;
	private String tmp;
	private double z;
	public String relais="000000000000 0.0ns";
	public boolean isMoving = false;
	public double Pos = 0.0;
	public double limit = 40.0;
	public boolean connected = false;
	public boolean initialized = false;
	public boolean overLimit = false;
	final static  DecimalFormat valueFormat = new DecimalFormat("0.000");

	//trigger variables
	private String mode;
	private String rate;
	private String threshold;
	private String advmode;
	private String hold;
	private String edge;
	private String prescale;
	private String phase;

	//burst variables
	private String burstStatus;
	private String fireon;
	private String cnt;
	private String period;
	private String delay;

	//delay variables
	private String delayChannel;
	private String linkChannel;
	private String delayVal;

	//level variables
	private String levelChannel;
	private String offset;
	private String amplitude;
	private String polarity;

	public DG645(String server, int port)
	{
		host = server;
		this.port = port;
	}

	public void trigger(String mode, String rate, String threshold, String advmode, String hold, String edge, String prescale, String phase)
	{
		if (!connected) return;

		System.out.println("at dg645 trigger");
		if(mode != null)
			this.mode = mode;
		if(rate != null)
			this.rate = rate;
		if(threshold != null)
			this.threshold = threshold;
		if(advmode != null)
			this.advmode = advmode;
		if(hold != null)
			this.hold = hold;
		if(edge != null)
			this.edge = edge;
		if(prescale != null)
			this.prescale = prescale;
		if(phase != null)
			this.phase = phase;

		System.out.println("mode " + this.mode);
		System.out.println("rate " + this.rate);
		System.out.println("threshold " + this.threshold);
		System.out.println("advmode " + this.advmode);
		System.out.println("hold " + this.hold);
		System.out.println("edge " + this.edge);
		System.out.println("prescale " + this.prescale);
		System.out.println("phase " + this.phase);
		work = new Thread(this,"Trigger");
		isMoving = true;
		work.start(); 	

		//work.run();

		System.out.println("At trigger in DG645 end");
	}

	public void delay(String delayChannel, String linkChannel, String value)
	{
		if (!connected) return;

		System.out.println("delayVal trsd " + this.delayVal);
		System.out.println("value " + value);
		if(delayChannel != null)
			this.delayChannel = delayChannel;
		if(linkChannel != null)
			this.linkChannel = linkChannel;
		if(value != null)
			this.delayVal = value.substring(3);

		System.out.println("delayChannel " + this.delayChannel);
		System.out.println("linkChannel " + this.linkChannel);
		System.out.println("delayVal trsd " + this.delayVal);

		work = new Thread(this,"Delay");
		isMoving = true;
		work.start(); 	
	}

	public void delay(String delayChannel, Object value)
	{
		mConn.writeLine("DLAY? " + delayChannel);
		String temp = mConn.readLine();

		System.out.println("temp " + temp);
		System.out.println("temp substring" + temp.substring(0, 3));
		delay(delayChannel, null, temp.substring(0, 3)+value);
	}

	public void burst(String burstStatus, String fireon, String cnt, String period, String delay)
	{
		if (!connected) return;

		if(burstStatus != null)
			this.burstStatus = burstStatus;
		if(fireon != null)
			this.fireon = fireon;
		if(cnt != null)
			this.cnt = cnt;
		if(period != null)
			this.period = period;
		if(delay != null)
			this.delay = delay;


		System.out.println("burstStatus " + this.burstStatus);
		System.out.println("fireon " + this.fireon);
		System.out.println("cnt " + this.cnt);
		System.out.println("period " + this.period);
		System.out.println("delay " + this.delay);

		work = new Thread(this,"Burst");
		isMoving = true;
		work.start(); 	
	}

	public void level(String channel, String offset, String amp, String polarity)
	{
		if (!connected) return;

		if(channel != null)
			this.levelChannel = channel;
		if(offset != null)
			this.offset = offset;
		if(amp != null)
			this.amplitude = amp;
		if(polarity != null)
			this.polarity = polarity;


		System.out.println("levelChannel " + this.levelChannel);
		System.out.println("offset " + this.offset);
		System.out.println("amplitude " + this.amplitude);
		System.out.println("polarity " + this.polarity);

		work = new Thread(this,"Level");
		isMoving = true;
		work.start(); 	
	}

	public void connect() 
	{
		work = new Thread(this,"Connecting");
		isMoving = true;
		work.start();
	}

	public void init() 
	{
		if (!connected) return;
		work = new Thread(this,"Init");
		isMoving = true;
		work.start(); 	
	}

	public void stop() 
	{
		initialized = false;
	}

	public void move(double P) 
	{
		if (!initialized) return;
		z = P;
		work = new Thread(this,"Moving");
		isMoving = true;
		work.start(); 		


		System.out.println("Here at move");
	}

	public synchronized void waitDone(int timeout) {
		try {
			wait(timeout*1000);
		} catch (InterruptedException e) {}
	}

	private synchronized void workDone() {
		notifyAll();
	}

	public void run() 
	{		
		try{
			if (work.getName().startsWith("Connecting")) {
				mConn = new DeviceConnection(host,port,System.out); 
				DG645Control.dg645.mConn.writeLine("*CLS");	//Clears ESR, INSR, and LERR
				mConn.writeLine("*IDN?");
				tmp = mConn.readLine();
				System.out.println("reply: "+ tmp);
				connected = true;
				isMoving = false;
			} else if(work.getName().equals("Trigger")) { 
				System.out.println("rate " + rate);
				if(mode != null)
				{
					mConn.writeLine("TSRC " + mode);
					mode = null;
				}
				if(rate != null)
				{	
					mConn.writeLine("TRAT " + rate);
					rate = null;
				}
				if(threshold !=null)
				{	
					mConn.writeLine("TLVL," + threshold);
					threshold = null;
				}
				if(advmode !=null)
				{	
					mConn.writeLine("ADVT " + advmode);
					advmode = null;
				}
				if(hold !=null)
				{	
					mConn.writeLine("HOLD " + hold);
					hold = null;
				}
				if(edge != null && prescale !=null)
				{	
					mConn.writeLine("PRES " + edge + "," + prescale); 
					edge = null;
					prescale = null;
				}
				if(edge != null && phase !=null)
				{	
					mConn.writeLine("PHAS " + edge + "," + phase);
					edge = null;
					prescale = null;
				}

			} else if(work.getName().equals("Burst")) {
				if(burstStatus != null)
				{	
					mConn.writeLine("BURM " + burstStatus);
					burstStatus = null;
				}
				if(fireon != null)
				{	
					mConn.writeLine("BURT " + fireon);
					fireon = null;
				}
				if(cnt != null)
				{	
					mConn.writeLine("BURC " + cnt);
					cnt = null;
				}
				if(period != null)
				{	
					mConn.writeLine("BURP " + period);
					period = null;
				}
				if(delay != null)
				{	
					mConn.writeLine("BURD " + delay);
					delay = null;
				}
			} else if(work.getName().equals("Delay")) {	
				if(delayChannel != null && linkChannel != null)
				{	
					mConn.writeLine("LINK " + delayChannel + "," + linkChannel);
					delayChannel = null;
					linkChannel = null;
				}
				if(delayChannel != null && delayVal != null)
				{	
					mConn.writeLine("DLAY? " + delayChannel);
					mConn.writeLine("DLAY? 2");
					System.out.println("delayChannel " + delayChannel + "read: " + mConn.readLine());
					//if(linkChannel == null)
						linkChannel = mConn.readLine().substring(0, 1);
					
						System.out.println("link " + linkChannel);

						System.out.println("DLAY " + delayChannel + "," + linkChannel + "," + delayVal);
					mConn.writeLine("DLAY " + delayChannel + "," + linkChannel + "," + delayVal);
					

					delayChannel = null;
					delayVal = null;
				}
			} else if(work.getName().equals("Level")) {

				if( levelChannel != null && offset != null)
				{	
					mConn.writeLine("LOFF " + levelChannel + "," + offset);
					levelChannel = null;
					offset = null;
				}
				if( levelChannel != null && amplitude != null)
				{	
					mConn.writeLine("LAMP " + levelChannel + "," + amplitude);
					levelChannel = null;
					amplitude = null;
				}
				if(levelChannel != null && polarity != null)
				{	
					mConn.writeLine("LPOL " + levelChannel + "," + polarity); 
					levelChannel = null;
					polarity = null;
				}
			}
			else {
				//System.out.println("moving");
				isMoving = false;	
			}
		}catch(Exception e){};

		workDone();
	}



}

