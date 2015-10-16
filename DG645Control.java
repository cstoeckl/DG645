import javax.swing.*;

public class DG645Control  {
	

	//public static DG645 dg645;
	//public static DG645Gui dg645Gui;
//	public static String ip = "172.20.34.210";
//	public static String ip = "172.21.37.92";
	
//	public static int port = 5025;
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Couldn't use the cross-platform look and feel: " + e);
		}
		
		connect();
	/*	WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		try{
			
			dg645 = new DG645(ip, 5025);
			dg645.connect();
			
			dg645Gui = new DG645Gui();
			
			dg645Gui.addWindowListener(l);
			dg645Gui.pack();
			dg645Gui.setVisible(true);
			
		
			// automatically connect to DG645
			dg645Gui.action.connect();
			
		}catch(Exception e){
			
		}*/
	}
	
	public static void connect(){
		

		
		try{
			DG645Settings settings = new DG645Settings();
			settings.setVisible(true);
			
		}catch(Exception e){
			System.out.println("Error at connection"); // to IP: " + ip);
			e.printStackTrace();
		}
	}
	
	/*public static void connect2(){
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		System.out.println("Connect 2 line 1");
	//	dg645 = new DG645(ip, 5025);
		dg645 = new DG645();
		System.out.println("Connect 2 line 2");
		dg645.connect();

		System.out.println("Connect 2 line 3");
		dg645Gui = new DG645Gui();

		dg645Gui.addWindowListener(l);
	}*/
	/*
	public static void connect(){
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		try{
			
			dg645 = new DG645(ip, 5025);
			dg645.connect();
			
			dg645Gui = new DG645Gui();
			dg645Gui.settingsDialog.setVisible(true);

			dg645Gui.addWindowListener(l);
			
			
		}catch(Exception e){
			System.out.println("Error at connection to IP: " + ip);
			e.printStackTrace();
		}
	}*/
	
	/*public static void showGui()
	{

		// automatically connect to DG645
		dg645Gui.action.connect();
		
		dg645Gui.pack();
		dg645Gui.setVisible(true);
		
	}
	*/
	/*public static void restartGui()
	{
		dg645Gui.setVisible(false);
		
		dg645Gui.initRun = true;
		dg645Gui.initComponents();
		dg645Gui.initRun = false;
		
		//dg645Gui.setVisible(true);
		
		
		showGui();
		
	/*	WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		dg645Gui = new DG645Gui();
		dg645Gui.addWindowListener(l);
		dg645Gui.pack();
		dg645Gui.setVisible(true);
		

	}*/
	
	/*public static void restartGui()
	{
		dg645Gui.setVisible(false);
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		try{
			
			//dg645 = new DG645(ip, 5025);
			dg645 = new DG645();
			dg645.connect();
			
			dg645Gui = new DG645Gui();
			dg645Gui.initRun = true;
			dg645Gui.initComponents();
			
			dg645Gui.addWindowListener(l);
			dg645Gui.pack();
			dg645Gui.setVisible(true);
			
			// automatically connect to DG645
			dg645Gui.action.connect();
			
		}catch(Exception e){
			
		}
	}*/
}