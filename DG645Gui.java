import java.awt.*;
import javax.swing.*;

public class DG645Gui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DG645Action action;
	public DG645Settings settings;
	public DG645Save save;
	
	//TimerLabel timerLabel;

	//for uniform style
	Font deFont = new java.awt.Font("Tahoma", 0, 14);
	int textHeight = 30;

	private int intemp;

	public void checkError()
	{
		if(!settings.initRun)
		{	
			settings.dg645.mConn.writeLine("LERR?");
			intemp = Integer.parseInt(settings.dg645.mConn.readLine());

			switch(intemp){
			case 0: break; //No error

			//Execution Errors
			case 10: System.out.print("Error 10: Illegal Value - A parameter was out of range.\n"); break;
			case 11: System.out.print("Error 11: Illegal Mode - The action is illegal in the current mode. This might happen, for instance, if a single shot is requested when the trigger source is not single shot\n"); break;
			case 12: System.out.print("Error 12: Illegal Delay - The requested delay is out of range.\n"); break;
			case 13: System.out.print("Error 13: Illegal Link - The requested delay linkage is illegal.\n"); break;
			case 14: System.out.print("Error 14: Recall Failed - The recall of instrument settings from nonvolatile storage failed. The instrument settings were invalid\n"); break;
			case 15: System.out.print("Error 15: Not Allowed - The requested action is not allowed becaues the instrument is locked by another interface.\n"); break;
			case 16: System.out.print("Error 16: Failed Self Test - The DG645 self test failed.\n");  break;
			case 17: System.out.print("Error 17: Failed Auto Calibration - The DG645 auto calibration failed\n"); break;

			//Parsing Errors
			case 118: break;//System.out.print("Error 118: Invalid Floating Point Number - The parser expected a floating point number, but was unable to parse it.\nNote: If sure of your number, disregard this error.\n"); break;
			default: System.out.println("Unknown error:" + intemp + "\nPlease refer to DG645 user manual.\n");
				
			}
		}

	}

	/**
	 * Creates new form DG645Gui
	 */
	/*public DG645Gui() {
		super("DG645Control");
		
		settings = new DG645Settings(this);
		
		save = new DG645Save(this);
		action=new DG645Action(this);
		
	/*	initComponents();
		initRun = false;
	}
*/
	public DG645Gui(DG645Settings settings){
		super("DG645Control");
		
		this.settings = settings;
		
		save = new DG645Save(this);
		action = new DG645Action(this);
	}
	
	public void showGui()
	{
		// automatically connect to DG645
		//action.connect();
		settings.dg645.connect();
		
		pack();
		setVisible(true);
	}
	
	public void initComponents()
	{
		panelMain = new JPanel();
		tabPanel = new JTabbedPane();
		//frame
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("DG645 Settings");
		setPreferredSize(new java.awt.Dimension(1150, 600));
		//main panel
		panelMain.setPreferredSize(new java.awt.Dimension(1200, 500));
		//tabbed panel
		tabPanel.setFont(new java.awt.Font("Tahoma", 0, 16)); 
		tabPanel.setPreferredSize(new java.awt.Dimension(1200, 500));
		//menu bar
		menu();
		//add revelant tabs to tabbed panel
		panelTrigger = new DG645Trigger(this);
		panelBurst = new DG645Burst(this);
		panelDelay = new DG645Delay(this);
		panelLevel = new DG645Level(this);
		
		tabPanel.addTab("Trigger", panelTrigger);
		tabPanel.addTab("Burst", panelBurst);
		tabPanel.addTab("Delay", panelDelay);
		tabPanel.addTab("Level", panelLevel);
		//main panel layout
		GroupLayout panelMainLayout = new GroupLayout(panelMain);
		panelMain.setLayout(panelMainLayout);
		panelMainLayout.setHorizontalGroup(
				panelMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(tabPanel, GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
				);
		panelMainLayout.setVerticalGroup(
				panelMainLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelMainLayout.createSequentialGroup()
						.addComponent(tabPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
				);

		//frame layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(panelMain, GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(panelMain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				);

		pack();
	}

	private void menu()
	{
		//initializing necessary variables
		menuBar = new JMenuBar();
		menuFile = new JMenu();
		menuSave = new JMenuItem();
		menuExit = new JMenuItem();
		menuEdit = new JMenu();
		menuSettings = new JMenuItem();
		menuView = new JMenu();
		menuTools = new JMenu();

		menuBar.setFont(new java.awt.Font("Segoe UI", 0, 16)); 
		menuBar.setPreferredSize(new java.awt.Dimension(1200, 30));

		//file
		menuFile.setText("File");

		menuSave.setText("Save");
		menuSave.addActionListener(action);
		menuFile.add(menuSave);

		menuExit.setText("Exit");
		menuFile.add(menuExit);

		menuBar.add(menuFile);

		//edit
		menuEdit.setText("Edit");

		menuSettings.setText("Settings");
		menuSettings.addActionListener(action);
		menuEdit.add(menuSettings);
		
		menuBar.add(menuEdit);

		//view
		menuView.setText("View");
		menuBar.add(menuView);

		//tools
		menuTools.setText("Tools");
		menuBar.add(menuTools);

		setJMenuBar(menuBar);
	}

	// Variables declaration         

	private JPanel panelMain;

	//private JPanel panelSave;
	//private JPanel panelSettings;
	private JMenuBar menuBar;
	private JMenu menuEdit;
	private JMenuItem menuExit;
	private JMenu menuFile;
	public JMenuItem menuSave;
//	private JPopupMenu menuSavePopup;
	private JMenu menuTools;
	private JMenu menuView;

	public JMenuItem menuSettings;
	private JTabbedPane tabPanel;

	public DG645Trigger panelTrigger;
	public DG645Burst panelBurst;
	public DG645Delay panelDelay;
	public DG645Level panelLevel;
}
