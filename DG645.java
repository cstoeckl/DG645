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
	
	private DeviceConnection mConn=null;
	private Thread work;
	private  String host;
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
	
	
	public DG645(String server)
	{
		host = server;
	}
	
	public void connect() 
	{
		work = new Thread(this,"Connecting");
		isMoving = true;
		work.start();
		System.out.println("Here at connect");
	}
	
	public void testing()
	{
		if (!connected) return;
		work = new Thread(this, "Test");
		isMoving = true;
		work.start();
		
		System.out.println("====At test");
	}
	
	public void init() 
	{
		if (!connected) return;
		work = new Thread(this,"Init");
		isMoving = true;
		work.start(); 	
		

		System.out.println("Here at init");
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
	
	private double process(double x, double y){
		if (Math.abs(y) > limit) {
			return x; 
		}
		String tmp = "del " + valueFormat.format(y) + " ns";
//		System.out.println("process " + tmp);
		mConn.writeLine(tmp);
		mConn.writeLine("del?");
		tmp = mConn.readLine();
//		System.out.println(tmp);
		mConn.writeLine("rel?");
		tmp = mConn.readLine();
		System.out.println("reply: "+ tmp);
		relais = tmp;
		if (!tmp.equals("success")) {
			overLimit = false;
			return y;
		} else {
			initialized = false;
			overLimit = true;
			return x;		
		}
	}
	
	public void step(double P) {
		if (Pos != P) {
			Pos = process(Pos, P);	
		}
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
			mConn = new DeviceConnection(host,5025,System.out); 
			mConn.writeLine("*IDN?");
			tmp = mConn.readLine();
			System.out.println("reply: "+ tmp);
			
			//=======TESTING AREA
			/*mConn.writeLine("ADVT 1\n");			//advanced triggering, turn on/off, queries al lwork
			tmp = mConn.readLine();
			System.out.println("testing: "+ tmp);*/

	//		mConn.write("DISP 4\n");
			
			if (tmp == "1")
				mConn.writeLine("ADVT 0\n");
			else
				mConn.writeLine("ADVT 1\n");
			mConn.writeLine("ADVT?\n");
			
			tmp = mConn.readLine();
			System.out.println("testing: "+ tmp);
			
			mConn.writeLine("TSRC 0\n"); //set internal triggering
			mConn.writeLine("TRAT 4000\n"); //set trigger rate to 2kHz
			
			mConn.writeLine("DISP 0\n");
			System.out.print("here");
			/*mConn.write("DLAY 2,0,1e-6\n"); //supposed to change delay but machine does not register change?
			mConn.write("DLAY 3,2,2e-6\n)");
			
			mConn.write("DISP 11,2\n");*/
			
			//mConn.write("LERR?\n");
			System.out.println("last error: " + mConn.readLine());
			
			//========END TEST AREA
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
		/*} else if (work.getName().equals("Test")) {
			mConn.writeLine("ADVT?");
			tmp = mConn.readLine();
			System.out.println("testing: "+ tmp);
			
		//	mConn.writeLine("ADVT 1");
		//	tmp
			*/
			
		} else {
//			System.out.println("moving");
			step(z);
			isMoving = false;	
		}
		workDone();
	}
}

