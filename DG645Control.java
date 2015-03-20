import java.awt.event.*;
import javax.swing.*;
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
public class DG645Control  {
	

	public static DG645 dg645;
	public static DG645Gui dg645Gui;
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Couldn't use the cross-platform look and feel: " + e);
		}
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};
		
		dg645 = new DG645("172.20.34.210", 5025);
		dg645.connect();
		
		dg645Gui = new DG645Gui();
		dg645Gui.addWindowListener(l);
		dg645Gui.pack();
	//	dg645Gui.setSize(350, 230);
		dg645Gui.setVisible(true);
		
		// automatically connect to DG645-Box
		dg645Gui.action.connect();
		
		//DG645Gui dggui = new DG645Gui();
		
	
	}
}
