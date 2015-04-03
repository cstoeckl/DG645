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
	private  String host;
	private int port;
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

		work = new Thread(this,"Trigger");
		isMoving = true;
		work.start(); 	

		//work.run();

		System.out.println("At trigger in DG645 end");
	}

	public void delay(String delayChannel, String linkChannel, String value)
	{
		if (!connected) return;

		if(delayChannel != null)
			this.delayChannel = delayChannel;
		if(linkChannel != null)
			this.linkChannel = linkChannel;
		if(value != null)
			this.delayVal = value;

		work = new Thread(this,"Delay");
		isMoving = true;
		work.start(); 	
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
		if (work.getName().startsWith("Connecting")) {
			mConn = new DeviceConnection(host,port,System.out); 
			mConn.writeLine("*IDN?");
			tmp = mConn.readLine();
			System.out.println("reply: "+ tmp);
			Pos = 0.0;
			connected = true;
			isMoving = false;

		} else if (work.getName().equals("Init")) {
			mConn.writeLine("del?");
			tmp = mConn.readLine();
			//			System.out.println("reply: "+ tmp);
			Pos = Double.parseDouble(tmp)/1e-9;
			mConn.writeLine("rel?");
			tmp = mConn.readLine();
			System.out.println("reply: "+ tmp);
			relais = tmp;
			initialized = true;
			isMoving = false;
		} else if(work.getName().equals("Trigger")) { 

			try{
				System.out.println("rate " + rate);
				if(mode != null)
					mConn.writeLine("TSRC " + mode);
				if(rate != null)
					mConn.writeLine("TRAT " + rate);
				if(threshold !=null)
					mConn.writeLine("TLVL," + threshold);
				if(advmode !=null)
					mConn.writeLine("ADVT " + advmode);
				if(hold !=null)
					mConn.writeLine("HOLD " + hold);
				if(edge != null && prescale !=null)
					mConn.writeLine("PRES " + edge + "," + prescale); 
				if(edge != null && phase !=null)
					mConn.writeLine("PHAS " + edge + "," + phase);
			}catch(Exception e){};

		} else if(work.getName().equals("Burst")) {
			try{
				if(burstStatus != null)
					mConn.writeLine("BURM " + burstStatus);
				if(fireon != null)
					mConn.writeLine("BURT " + fireon);
				if(cnt != null)
					mConn.writeLine("BURC " + cnt);
				if(period != null)
					mConn.writeLine("BURP " + period);
				if(delay != null)
					mConn.writeLine("BURD " + delay);
			}catch(Exception e){};
		} else if(work.getName().equals("Delay")) {	
			try{
				if(delayChannel != null && linkChannel!= null)
					mConn.writeLine("LINK " + delayChannel + "," + linkChannel);
				if(delayChannel != null && delayVal!= null)
					mConn.writeLine("DLAY " + delayChannel + "," + delayVal);
			}catch(Exception e){};
		} else if(work.getName().equals("Level")) {
			try{
				if( levelChannel!= null && offset!= null)
					mConn.writeLine("LOFF " + levelChannel + "," + offset);
				if( levelChannel!= null && amplitude!= null)
					mConn.writeLine("LAMP " + levelChannel + "," + amplitude);
				if(levelChannel != null && polarity!= null)
					mConn.writeLine("LPOL " + levelChannel + "," + polarity); 
			}catch(Exception e){};
		}
		else {
			//			System.out.println("moving");
			isMoving = false;	
		}
		workDone();
	}



}

