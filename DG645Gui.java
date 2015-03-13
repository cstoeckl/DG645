import java.awt.*;

import javax.swing.JPanel;
import javax.swing.UIManager;

import lib.DeviceConnection;
import lib.TimerLabel;


public class DG645Gui extends javax.swing.JFrame {

	DG645Action action;
	
	//TimerLabel timerLabel;
	
	Font deFont = new java.awt.Font("Tahoma", 0, 14);
	
	int textHeight = 30;
	
	private DeviceConnection mConn=new DeviceConnection("172.20.34.210",5025,System.out);
	private String temp;
	private int intemp;
	private boolean initRun = true;


	
	public void checkError()
	{
		if(!initRun)
		{	
			mConn.writeLine("LERR?");
			intemp = Integer.parseInt(mConn.readLine());

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

	public void updateDelays(String channel)
	{
		mConn.writeLine("DLAY? " + channel);
		temp = mConn.readLine();

		//int link;
		int pm;
		String val;

		if(temp.substring(2,3).equals("+"))
			pm = 0;
		else
			pm = 1;

		val = temp.substring(3);
		val = ("000.000000000000" + val).substring(val.length());

		intemp = Integer.parseInt(channel);

		switch(intemp){
		case 0:
		case 1:
			break;
		case 2:
			//cboxChannelA.setSelectedIndex();
			cboxPMA.setSelectedIndex(pm);
			txtValA.setText(val);
			break;
		case 3:
			cboxPMB.setSelectedIndex(pm);
			txtValB.setText(val);
			break;
		case 4:
			cboxPMC.setSelectedIndex(pm);
			txtValC.setText(val);
			break;
		case 5:
			cboxPMD.setSelectedIndex(pm);
			txtValD.setText(val);
			break;
		case 6:
			cboxPME.setSelectedIndex(pm);
			txtValE.setText(val);
			break;
		case 7:
			cboxPMF.setSelectedIndex(pm);
			txtValF.setText(val);
			break;
		case 8:
			cboxPMG.setSelectedIndex(pm);
			txtValG.setText(val);
			break;
		case 9:
			cboxPMH.setSelectedIndex(pm);
			txtValH.setText(val);
			break;
		default: System.out.println("Invalid Channel");
		}

		updateT1();
		checkError();
	}

	public void updateT1()
	{
		mConn.writeLine("DLAY? 1");
		temp = mConn.readLine().substring(2);
		labelT1val.setText(temp);
		//labelT1val.repaint();
	}

	
	/**
	 * Creates new form DG645Gui
	 */
	public DG645Gui() {

		action=new DG645Action(this);
		
		mConn.writeLine("*CLS");

		mConn.writeLine("*IDN?"); 
		System.out.println("reply: "+ mConn.readLine());

		initComponents();
		initRun = false;

	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents()
	{
		panelMain = new javax.swing.JPanel();
		jTabMenus = new javax.swing.JTabbedPane();
		
		//frame
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("DG645 Settings");
		setPreferredSize(new java.awt.Dimension(1150, 600));
		
		//main panel
		panelMain.setPreferredSize(new java.awt.Dimension(1200, 500));
		
		//tabbed panel
		jTabMenus.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
		jTabMenus.setPreferredSize(new java.awt.Dimension(1200, 500));
		
		//menu bar
		menu();
		
		//add revelant tabs to tabbed panel
		jTabMenus.addTab("Trigger", triggerPanel());
		jTabMenus.addTab("Burst", burstPanel());
		jTabMenus.addTab("Delay", delayPanel());
		jTabMenus.addTab("Level", levelPanel());
		
		//main panel layout
		javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
		panelMain.setLayout(panelMainLayout);
		panelMainLayout.setHorizontalGroup(
				panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jTabMenus, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
		);
		panelMainLayout.setVerticalGroup(
				panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelMainLayout.createSequentialGroup()
						.addComponent(jTabMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
		);
		
		
		
		
		//frame layout
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(panelMain, javax.swing.GroupLayout.DEFAULT_SIZE, 1150, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		);

		pack();
	}
	
	private void menu()
	{
		//initializing necessary variabels
		menuBar = new javax.swing.JMenuBar();
		menuFile = new javax.swing.JMenu();
		menuSave = new javax.swing.JMenuItem();
		menuExit = new javax.swing.JMenuItem();
		menuEdit = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		menuView = new javax.swing.JMenu();
		menuTools = new javax.swing.JMenu();
		
		menuBar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
		menuBar.setPreferredSize(new java.awt.Dimension(1200, 30));

		//file
		menuFile.setText("File");

		menuSave.setText("Save");
		menuFile.add(menuSave);

		menuExit.setText("Exit");
		menuFile.add(menuExit);

		menuBar.add(menuFile);

		//edit
		menuEdit.setText("Edit");

		jMenuItem1.setText("Settings");
		menuEdit.add(jMenuItem1);

		menuBar.add(menuEdit);

		//view
		menuView.setText("View");
		menuBar.add(menuView);

		//tools
		menuTools.setText("Tools");
		menuBar.add(menuTools);

		setJMenuBar(menuBar);
	}
	
	private javax.swing.JPanel triggerPanel()
	{
		//initializing necessary variables
		panelTrigger = new javax.swing.JPanel();
		
		triggerModes = new javax.swing.ButtonGroup();
		triggerAdv = new javax.swing.ButtonGroup();
		
		txtTrigRate = new javax.swing.JFormattedTextField();
		txtTrigThres = new javax.swing.JFormattedTextField();
		buttonTrigOn = new javax.swing.JRadioButton();
		buttonTrigOff = new javax.swing.JRadioButton();
		txtHold = new javax.swing.JFormattedTextField();
		labelTrigRate = new javax.swing.JLabel();
		labelTrigThres = new javax.swing.JLabel();
		labelAdvTrig = new javax.swing.JLabel();
		labelHold = new javax.swing.JLabel();
		panelPresConfig = new javax.swing.JPanel();
		labelPresConfig = new javax.swing.JLabel();
		labelT2 = new javax.swing.JLabel();
		labelA1 = new javax.swing.JLabel();
		labelC1 = new javax.swing.JLabel();
		labelE1 = new javax.swing.JLabel();
		labelG1 = new javax.swing.JLabel();
		labelB1 = new javax.swing.JLabel();
		labelD1 = new javax.swing.JLabel();
		labelF1 = new javax.swing.JLabel();
		labelH1 = new javax.swing.JLabel();
		labelTrigPS = new javax.swing.JLabel();
		txtTrigPres = new javax.swing.JFormattedTextField();
		labelEdge = new javax.swing.JLabel();
		labelEdge2 = new javax.swing.JLabel();
		labelABpres = new javax.swing.JLabel();
		labelCDpres = new javax.swing.JLabel();
		labelEFpres = new javax.swing.JLabel();
		labelGHpres = new javax.swing.JLabel();
		labelABphase = new javax.swing.JLabel();
		labelCDphase = new javax.swing.JLabel();
		labelEFphase = new javax.swing.JLabel();
		labelGHphase = new javax.swing.JLabel();
		txtABpres = new javax.swing.JFormattedTextField();
		txtCDpres = new javax.swing.JFormattedTextField();
		txtEFpres = new javax.swing.JFormattedTextField();
		txtGHpres = new javax.swing.JFormattedTextField();
		txtABphase = new javax.swing.JFormattedTextField();
		txtCDphase = new javax.swing.JFormattedTextField();
		txtEFphase = new javax.swing.JFormattedTextField();
		txtGHphase = new javax.swing.JFormattedTextField();
		panelModes = new javax.swing.JPanel();
		buttonLine = new javax.swing.JRadioButton();
		buttonSnglExtF = new javax.swing.JRadioButton();
		buttonSngl = new javax.swing.JRadioButton();
		labelMode = new javax.swing.JLabel();
		labelInt = new javax.swing.JLabel();
		labelExt = new javax.swing.JLabel();
		labelExt2 = new javax.swing.JLabel();
		labelSnglExt = new javax.swing.JLabel();
		buttonSnglExtR = new javax.swing.JRadioButton();
		labelSnglExt2 = new javax.swing.JLabel();
		buttonInt = new javax.swing.JRadioButton();
		labelLine = new javax.swing.JLabel();
		labelSngl = new javax.swing.JLabel();
		buttonExtF = new javax.swing.JRadioButton();
		buttonExtR = new javax.swing.JRadioButton();
		jSeparator1 = new javax.swing.JSeparator();
		//end variable initialization
		
		
		
		panelTrigger.setPreferredSize(new java.awt.Dimension(1200, 500));

		mConn.writeLine("TRAT?");
		temp = mConn.readLine().substring(1);
		temp = ("0000.000000" + temp).substring(temp.length());
		try {
			txtTrigRate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####.######")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtTrigRate.setText(temp);
		txtTrigRate.setToolTipText("Internal Triggering");
		txtTrigRate.setAutoscrolls(false);
		txtTrigRate.setFont(deFont); // NOI18N
		txtTrigRate.setPreferredSize(new java.awt.Dimension(120, 30));
		txtTrigRate.addPropertyChangeListener(action);

		mConn.writeLine("TLVL?");
		temp = mConn.readLine(); //.substring(1);
		//temp = ("0.00" + temp).substring(temp.length());
		try {
			txtTrigThres.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*#.##")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtTrigThres.setText(temp);
		txtTrigThres.setToolTipText("External Triggering; Range -3.50 to +3.50");
		txtTrigThres.setFont(deFont); // NOI18N
		txtTrigThres.setPreferredSize(new java.awt.Dimension(60, 30));
		txtTrigThres.setRequestFocusEnabled(false);
		txtTrigThres.addPropertyChangeListener(action);

		triggerAdv.add(buttonTrigOn);
		buttonTrigOn.setFont(deFont); // NOI18N
		buttonTrigOn.setText("On");
		buttonTrigOn.setPreferredSize(new java.awt.Dimension(50, 30));
		buttonTrigOn.addActionListener(action);

		triggerAdv.add(buttonTrigOff);
		buttonTrigOff.setFont(deFont); // NOI18N
		buttonTrigOff.setText("Off");
		buttonTrigOff.setPreferredSize(new java.awt.Dimension(50, 30));
		buttonTrigOff.addActionListener(action);
		mConn.writeLine("ADVT?");
		switch(Integer.parseInt(mConn.readLine())) {
		case 0: triggerAdv.setSelected(buttonTrigOff.getModel(), true);
		break;
		case 1: triggerAdv.setSelected(buttonTrigOn.getModel(), true);
		break;
		default: System.out.println("Invalid ADVT");
		}

		mConn.writeLine("HOLD?");
		temp = mConn.readLine().substring(1);
		temp = ("0.000000000000" + temp).substring(temp.length());
		try {
			txtHold.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtHold.setText(temp);
		txtHold.setFont(deFont); // NOI18N
		txtHold.setPreferredSize(new java.awt.Dimension(160, 30));
		txtHold.addPropertyChangeListener(action);
		

		labelTrigRate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelTrigRate.setText("Trig Rate");
		labelTrigRate.setPreferredSize(new java.awt.Dimension(90, 30));

		labelTrigThres.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelTrigThres.setText("Trig Thres");
		labelTrigThres.setPreferredSize(new java.awt.Dimension(90, 30));

		labelAdvTrig.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelAdvTrig.setText("Adv. Trig");
		labelAdvTrig.setPreferredSize(new java.awt.Dimension(90, 30));

		labelHold.setFont(deFont); // NOI18N
		labelHold.setText("Hold");
		labelHold.setPreferredSize(new java.awt.Dimension(70, 30));

		labelPresConfig.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelPresConfig.setText("Prescaler Configuration");
		labelPresConfig.setPreferredSize(new java.awt.Dimension(200, 30));

		labelT2.setFont(deFont); // NOI18N
		labelT2.setText("T0");
		labelT2.setPreferredSize(new java.awt.Dimension(30, 30));

		labelA1.setFont(deFont); // NOI18N
		labelA1.setText("A");
		labelA1.setPreferredSize(new java.awt.Dimension(20, 30));
		labelA1.setRequestFocusEnabled(false);

		labelC1.setFont(deFont); // NOI18N
		labelC1.setText("C");
		labelC1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelE1.setFont(deFont); // NOI18N
		labelE1.setText("E");
		labelE1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelG1.setFont(deFont); // NOI18N
		labelG1.setText("G");
		labelG1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelB1.setFont(deFont); // NOI18N
		labelB1.setText("B");
		labelB1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelD1.setFont(deFont); // NOI18N
		labelD1.setText("D");
		labelD1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelF1.setFont(deFont); // NOI18N
		labelF1.setText("F");
		labelF1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelH1.setFont(deFont); // NOI18N
		labelH1.setText("H");
		labelH1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelTrigPS.setFont(deFont); // NOI18N
		labelTrigPS.setText("TRG Prescale");
		labelTrigPS.setPreferredSize(new java.awt.Dimension(100, 30));

		mConn.writeLine("PRES? 0");
		temp = mConn.readLine();
		temp = ("0000000000" + temp).substring(temp.length());
		try {
			txtTrigPres.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#,###,###,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtTrigPres.setText(temp);
		txtTrigPres.setToolTipText("max value 1073741823");
		txtTrigPres.setFont(deFont); // NOI18N
		txtTrigPres.setPreferredSize(new java.awt.Dimension(150, 30));
		txtTrigPres.addPropertyChangeListener(action);
		

		labelEdge.setFont(deFont); // NOI18N
		labelEdge.setText("Edge");
		labelEdge.setPreferredSize(new java.awt.Dimension(50, 30));

		labelEdge2.setFont(deFont); // NOI18N
		labelEdge2.setText("Edge");
		labelEdge2.setPreferredSize(new java.awt.Dimension(50, 30));

		labelABpres.setFont(deFont); // NOI18N
		labelABpres.setText("AB Prescale");
		labelABpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelCDpres.setFont(deFont); // NOI18N
		labelCDpres.setText("CD Prescale");
		labelCDpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelEFpres.setFont(deFont); // NOI18N
		labelEFpres.setText("EF Prescale");
		labelEFpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelGHpres.setFont(deFont); // NOI18N
		labelGHpres.setText("GH Prescale");
		labelGHpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelABphase.setFont(deFont); // NOI18N
		labelABphase.setText("AB Phase");
		labelABphase.setPreferredSize(new java.awt.Dimension(85, 30));

		labelCDphase.setFont(deFont); // NOI18N
		labelCDphase.setText("CD Phase");
		labelCDphase.setPreferredSize(new java.awt.Dimension(85, 30));

		labelEFphase.setFont(deFont); // NOI18N
		labelEFphase.setText("EF Phase");
		labelEFphase.setPreferredSize(new java.awt.Dimension(85, 30));

		labelGHphase.setFont(deFont); // NOI18N
		labelGHphase.setText("GH Phase");
		labelGHphase.setPreferredSize(new java.awt.Dimension(85, 30));

		mConn.writeLine("PRES? 1");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtABpres.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtABpres.setText(temp);
		txtABpres.setToolTipText("max value 65535");
		txtABpres.setFont(deFont); // NOI18N
		txtABpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtABpres.addPropertyChangeListener(action);
		

		mConn.writeLine("PRES? 2");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtCDpres.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtCDpres.setText(temp);
		txtCDpres.setToolTipText("max value 65535");
		txtCDpres.setFont(deFont); // NOI18N
		txtCDpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtCDpres.addPropertyChangeListener(action);

		mConn.writeLine("PRES? 3");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtEFpres.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtEFpres.setText(temp);
		txtEFpres.setToolTipText("max value 65535");
		txtEFpres.setFont(deFont); // NOI18N
		txtEFpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtEFpres.addPropertyChangeListener(action);

		mConn.writeLine("PRES? 4");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtGHpres.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtGHpres.setText(temp);
		txtGHpres.setToolTipText("max value 65535");
		txtGHpres.setFont(deFont); // NOI18N
		txtGHpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtGHpres.addPropertyChangeListener(action);

		mConn.writeLine("PHAS? 1");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtABphase.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtABphase.setText(temp);
		txtABphase.setToolTipText("max value 65534");
		txtABphase.setFont(deFont); // NOI18N
		txtABphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtABphase.addPropertyChangeListener(action);

		mConn.writeLine("PHAS? 2");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtCDphase.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtCDphase.setText(temp);
		txtCDphase.setToolTipText("max value 65534");
		txtCDphase.setFont(deFont); // NOI18N
		txtCDphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtCDphase.addPropertyChangeListener(action);

		mConn.writeLine("PHAS? 3");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtEFphase.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtEFphase.setText(temp);
		txtEFphase.setToolTipText("max value 65534");
		txtEFphase.setFont(deFont); // NOI18N
		txtEFphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtEFphase.addPropertyChangeListener(action);

		mConn.writeLine("PHAS? 4");
		temp = mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtGHphase.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##,###")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtGHphase.setText(temp);
		txtGHphase.setToolTipText("max value 65534");
		txtGHphase.setFont(deFont); // NOI18N
		txtGHphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtGHphase.addPropertyChangeListener(action);

		javax.swing.GroupLayout panelPresConfigLayout = new javax.swing.GroupLayout(panelPresConfig);
		panelPresConfig.setLayout(panelPresConfigLayout);
		panelPresConfigLayout.setHorizontalGroup(
				panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelPresConfigLayout.createSequentialGroup()
						.addComponent(labelPresConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(panelPresConfigLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(panelPresConfigLayout.createSequentialGroup()
												.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(labelEdge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(panelPresConfigLayout.createSequentialGroup()
																.addGap(10, 10, 10)
																.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																		.addGroup(panelPresConfigLayout.createSequentialGroup()
																				.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(labelA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(labelC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(labelE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(labelG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																								.addComponent(labelCDpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelABpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelEFpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelGHpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(txtEFpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(txtGHpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
																										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPresConfigLayout.createSequentialGroup()
																												.addGap(132, 132, 132)
																												.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																														.addComponent(txtABpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																														.addComponent(txtCDpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
																														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
																														.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																.addComponent(labelEdge2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGroup(panelPresConfigLayout.createSequentialGroup()
																																		.addGap(10, 10, 10)
																																		.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																				.addGroup(panelPresConfigLayout.createSequentialGroup()
																																						.addComponent(labelD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																						.addComponent(labelCDphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																																						.addGroup(panelPresConfigLayout.createSequentialGroup()
																																								.addComponent(labelF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																								.addComponent(labelEFphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																																								.addGroup(panelPresConfigLayout.createSequentialGroup()
																																										.addComponent(labelH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(labelGHphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																																										.addGroup(panelPresConfigLayout.createSequentialGroup()
																																												.addComponent(labelB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																												.addComponent(labelABphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
																																												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																												.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																																														.addComponent(txtCDphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																														.addComponent(txtABphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																														.addComponent(txtEFphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																														.addComponent(txtGHphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																																														.addContainerGap(25, Short.MAX_VALUE))
																																														.addGroup(panelPresConfigLayout.createSequentialGroup()
																																																.addComponent(labelT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																.addComponent(labelTrigPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																																.addComponent(txtTrigPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																																																.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		panelPresConfigLayout.setVerticalGroup(
				panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelPresConfigLayout.createSequentialGroup()
						.addComponent(labelPresConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTrigPS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTrigPres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(labelEdge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(labelEdge2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(labelA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelABpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtABpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelABphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(txtABphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(panelPresConfigLayout.createSequentialGroup()
																.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(labelC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(labelCDpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtCDpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(labelE1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(labelEFpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(txtEFpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addGroup(panelPresConfigLayout.createSequentialGroup()
																						.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																								.addComponent(labelD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelCDphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addComponent(txtCDphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																										.addComponent(labelF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(labelEFphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addComponent(txtEFphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
																										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(panelPresConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																												.addComponent(labelG1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(labelGHpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(txtGHpres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(labelH1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(labelGHphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(txtGHphase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		triggerModes.add(buttonLine);
		buttonLine.setFont(deFont); // NOI18N
		buttonLine.setText("LINE");
		buttonLine.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonLine.addActionListener(action);
		mConn.writeLine("TSRC?");
		switch(Integer.parseInt(mConn.readLine())) {
		case 0: triggerModes.setSelected(buttonInt.getModel(), true);
		break;
		case 1: triggerModes.setSelected(buttonExtR.getModel(), true);
		break;
		case 2: triggerModes.setSelected(buttonExtF.getModel(), true);
		break;
		case 3: triggerModes.setSelected(buttonSnglExtR.getModel(), true);
		break;
		case 4: triggerModes.setSelected(buttonSnglExtF.getModel(), true);
		break;
		case 5: triggerModes.setSelected(buttonSngl.getModel(), true);
		break;
		case 6: triggerModes.setSelected(buttonLine.getModel(), true);
		break;
		default: System.out.println("Invalid ISRC");
		}

		triggerModes.add(buttonSnglExtF);
		buttonSnglExtF.setFont(deFont); // NOI18N
		buttonSnglExtF.setText("SNGL EXT ↓");
		buttonSnglExtF.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonSnglExtF.addActionListener(action);

		triggerModes.add(buttonSngl);
		buttonSngl.setFont(deFont); // NOI18N
		buttonSngl.setText("SNGL");
		buttonSngl.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonSngl.addActionListener(action);

		labelMode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelMode.setText("Select Mode");
		labelMode.setPreferredSize(new java.awt.Dimension(100, 30));

		labelInt.setFont(deFont); // NOI18N
		labelInt.setText("Internal triggering at rates from 100 uHz to MHz");
		labelInt.setPreferredSize(new java.awt.Dimension(300, 30));
		labelInt.setRequestFocusEnabled(false);

		labelExt.setFont(deFont); // NOI18N
		labelExt.setText("External triggering on rising edges");
		labelExt.setPreferredSize(new java.awt.Dimension(300, 30));

		labelExt2.setFont(deFont); // NOI18N
		labelExt2.setText("External triggering on falling edges");
		labelExt2.setPreferredSize(new java.awt.Dimension(300, 30));

		labelSnglExt.setFont(deFont); // NOI18N
		labelSnglExt.setText("Externally triggered single shot on a rising edge");
		labelSnglExt.setPreferredSize(new java.awt.Dimension(300, 30));

		triggerModes.add(buttonSnglExtR);
		buttonSnglExtR.setFont(deFont); // NOI18N
		buttonSnglExtR.setText("SNGL EXT ↑");
		buttonSnglExtR.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonSnglExtR.addActionListener(action);

		labelSnglExt2.setFont(deFont); // NOI18N
		labelSnglExt2.setText("Externally triggered single shot on a falling edge");
		labelSnglExt2.setPreferredSize(new java.awt.Dimension(300, 30));

		triggerModes.add(buttonInt);
		buttonInt.setFont(deFont); // NOI18N
		buttonInt.setText("INT");
		buttonInt.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonInt.addActionListener(action);

		labelLine.setFont(deFont); // NOI18N
		labelLine.setText("Trigger at the power line frequency");
		labelLine.setPreferredSize(new java.awt.Dimension(300, 30));

		labelSngl.setFont(deFont); // NOI18N
		labelSngl.setText("Single shot triggering");
		labelSngl.setPreferredSize(new java.awt.Dimension(300, 30));

		triggerModes.add(buttonExtF);
		buttonExtF.setFont(deFont); // NOI18N
		buttonExtF.setText("EXT ↓");
		buttonExtF.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonExtF.addActionListener(action);

		triggerModes.add(buttonExtR);
		buttonExtR.setFont(deFont); // NOI18N
		buttonExtR.setText("EXT ↑");
		buttonExtR.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonExtR.addActionListener(action);

		javax.swing.GroupLayout panelModesLayout = new javax.swing.GroupLayout(panelModes);
		panelModes.setLayout(panelModesLayout);
		panelModesLayout.setHorizontalGroup(
				panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelModesLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(labelMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(panelModesLayout.createSequentialGroup()
										.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(buttonInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonExtR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonExtF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonSnglExtR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonSnglExtF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonSngl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
														.addComponent(labelSngl, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelSnglExt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelSnglExt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelExt2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelExt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelInt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelLine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
														.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelModesLayout.setVerticalGroup(
				panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelModesLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(labelMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(buttonInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(buttonExtR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(labelExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(buttonExtF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelExt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(buttonSnglExtR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelSnglExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(buttonSnglExtF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(labelSnglExt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(buttonSngl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(labelSngl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(buttonLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(labelLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

		javax.swing.GroupLayout panelTriggerLayout = new javax.swing.GroupLayout(panelTrigger);
		panelTrigger.setLayout(panelTriggerLayout);
		panelTriggerLayout.setHorizontalGroup(
				panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelTriggerLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(panelModes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(panelTriggerLayout.createSequentialGroup()
										.addGap(10, 10, 10)
										.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(labelTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(txtTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(txtTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(panelTriggerLayout.createSequentialGroup()
																		.addComponent(labelAdvTrig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(buttonTrigOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(buttonTrigOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(panelTriggerLayout.createSequentialGroup()
																						.addComponent(labelHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(txtHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
																						.addComponent(panelPresConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addContainerGap(98, Short.MAX_VALUE))
		);
		panelTriggerLayout.setVerticalGroup(
				panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelTriggerLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jSeparator1)
								.addGroup(panelTriggerLayout.createSequentialGroup()
										.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(panelTriggerLayout.createSequentialGroup()
														.addComponent(buttonTrigOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(buttonTrigOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(labelHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(txtHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(panelPresConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addComponent(labelAdvTrig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGroup(panelTriggerLayout.createSequentialGroup()
																		.addComponent(panelModes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(labelTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(txtTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(labelTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(txtTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
																						.addGap(0, 0, Short.MAX_VALUE)))
																						.addContainerGap())
		);
		
		return panelTrigger;
	}
	
	private javax.swing.JPanel burstPanel()
	{
		//initializing necessary variables
		panelBurst = new javax.swing.JPanel();
		
		burstOnOff = new javax.swing.ButtonGroup();
		burstOutput = new javax.swing.ButtonGroup();
		
		buttonOn = new javax.swing.JRadioButton();
		buttonOff = new javax.swing.JRadioButton();
		buttonOutputAll = new javax.swing.JRadioButton();
		buttonOutputFirst = new javax.swing.JRadioButton();
		txtCNT = new javax.swing.JFormattedTextField();
		txtPeriod = new javax.swing.JFormattedTextField();
		txtDelay = new javax.swing.JFormattedTextField();
		labelCNT = new javax.swing.JLabel();
		labelPeriod = new javax.swing.JLabel();
		labelDelay = new javax.swing.JLabel();
		//end variable initialization
		
		panelBurst.setPreferredSize(new java.awt.Dimension(500, 500));

		burstOnOff.add(buttonOn);
		buttonOn.setFont(deFont); // NOI18N
		buttonOn.setText("On");
		buttonOn.setPreferredSize(new java.awt.Dimension(60, 30));
		buttonOn.addActionListener(action);

		burstOnOff.add(buttonOff);
		buttonOff.setFont(deFont); // NOI18N
		buttonOff.setText("Off");
		buttonOff.setPreferredSize(new java.awt.Dimension(60, 30));
		buttonOff.addActionListener(action);
		mConn.writeLine("BURM?");
		switch(Integer.parseInt(mConn.readLine())) {
		case 0: burstOnOff.setSelected(buttonOff.getModel(), true);
		break;
		case 1: burstOnOff.setSelected(buttonOn.getModel(), true);
		break;
		default: System.out.println("Invalid BURM");
		}

		burstOutput.add(buttonOutputAll);
		buttonOutputAll.setFont(deFont); // NOI18N
		buttonOutputAll.setText("T0 fire on all delay cycles of burst");
		buttonOutputAll.setPreferredSize(new java.awt.Dimension(300, 30));
		buttonOutputAll.addActionListener(action);

		burstOutput.add(buttonOutputFirst);
		buttonOutputFirst.setFont(deFont); // NOI18N
		buttonOutputFirst.setText("T0 fire on first delay cycle of burst");
		buttonOutputFirst.setPreferredSize(new java.awt.Dimension(300, 30));
		buttonOutputFirst.addActionListener(action);
		mConn.writeLine("BURT?");
		switch(Integer.parseInt(mConn.readLine())) {
		case 0: burstOutput.setSelected(buttonOutputAll.getModel(), true);
		break;
		case 1: burstOutput.setSelected(buttonOutputFirst.getModel(), true);
		break;
		default: System.out.println("Invalid BURT");
		}

		mConn.writeLine("BURC?");
		temp = mConn.readLine();
		try {
			txtCNT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##########")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtCNT.setText(temp);
		txtCNT.setToolTipText("range 1 to 2^32 - 1");
		txtCNT.setFont(deFont); // NOI18N
		txtCNT.setPreferredSize(new java.awt.Dimension(50, 30));
		txtCNT.addPropertyChangeListener(action);

		mConn.writeLine("BURP?");
		//temp = mConn.readLine();
		temp = mConn.readLine().substring(1);
		temp = ("0.00000000" + temp).substring(temp.length());
		try {
			txtPeriod.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.########")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtPeriod.setText(temp);
		txtPeriod.setToolTipText("10ns resolution");
		txtPeriod.setFont(deFont); // NOI18N
		txtPeriod.setPreferredSize(new java.awt.Dimension(50, 30));
		txtPeriod.addPropertyChangeListener(action);

		mConn.writeLine("BURD?");
		//temp = mConn.readLine();
		temp = mConn.readLine().substring(1);
		temp = ("0.000000000000" + temp).substring(temp.length());
		try {
			txtDelay.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtDelay.setText(temp);
		txtDelay.setToolTipText("5ps resolution");
		txtDelay.setFont(deFont); // NOI18N
		txtDelay.setPreferredSize(new java.awt.Dimension(100, 30));
		txtDelay.addPropertyChangeListener(action);

		labelCNT.setFont(deFont); // NOI18N
		labelCNT.setText("CNT");
		labelCNT.setPreferredSize(new java.awt.Dimension(50, 30));

		labelPeriod.setFont(deFont); // NOI18N
		labelPeriod.setText("Period");
		labelPeriod.setPreferredSize(new java.awt.Dimension(50, 30));

		labelDelay.setFont(deFont); // NOI18N
		labelDelay.setText("Delay");
		labelDelay.setPreferredSize(new java.awt.Dimension(50, 30));

		javax.swing.GroupLayout panelBurstLayout = new javax.swing.GroupLayout(panelBurst);
		panelBurst.setLayout(panelBurstLayout);
		panelBurstLayout.setHorizontalGroup(
				panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelBurstLayout.createSequentialGroup()
						.addGap(64, 64, 64)
						.addGroup(panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(buttonOutputAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonOutputFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(panelBurstLayout.createSequentialGroup()
										.addGap(6, 6, 6)
										.addGroup(panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(labelCNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																.addComponent(txtCNT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(txtPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addComponent(txtDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
																.addContainerGap(781, Short.MAX_VALUE))
		);
		panelBurstLayout.setVerticalGroup(
				panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelBurstLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(buttonOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(buttonOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(buttonOutputAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(buttonOutputFirst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addGroup(panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtCNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelCNT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(txtPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(labelPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(txtDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		return panelBurst;
	}
	
	private javax.swing.JPanel delayPanel()
	{
		//initializing necessary variables
		panelDelay = new javax.swing.JPanel();
		
		labelChannel = new javax.swing.JLabel();
		labelDelay2 = new javax.swing.JLabel();
		jPanelT0 = new javax.swing.JPanel();
		labelEqual = new javax.swing.JLabel();
		labelT0 = new javax.swing.JLabel();
		labelChannel0 = new javax.swing.JLabel();
		labelT0val = new javax.swing.JLabel();
		panelT1 = new javax.swing.JPanel();
		labelChannel1 = new javax.swing.JLabel();
		labelEqual1 = new javax.swing.JLabel();
		labelT1 = new javax.swing.JLabel();
		labelT1val = new javax.swing.JLabel();
		panelA = new javax.swing.JPanel();
		cboxChannelA = new javax.swing.JComboBox();
		cboxPMA = new javax.swing.JComboBox();
		labelEqualA = new javax.swing.JLabel();
		labelA = new javax.swing.JLabel();
		txtValA = new javax.swing.JFormattedTextField();
		panelB = new javax.swing.JPanel();
		cboxPMB = new javax.swing.JComboBox();
		labelB = new javax.swing.JLabel();
		labelEqualB = new javax.swing.JLabel();
		txtValB = new javax.swing.JFormattedTextField();
		cboxChannelB = new javax.swing.JComboBox();
		panelC = new javax.swing.JPanel();
		cboxChannelC = new javax.swing.JComboBox();
		cboxPMC = new javax.swing.JComboBox();
		labelC = new javax.swing.JLabel();
		labelEqualC = new javax.swing.JLabel();
		txtValC = new javax.swing.JFormattedTextField();
		panelD = new javax.swing.JPanel();
		cboxChannelD = new javax.swing.JComboBox();
		cboxPMD = new javax.swing.JComboBox();
		txtValD = new javax.swing.JFormattedTextField();
		labelD = new javax.swing.JLabel();
		labelEqualD = new javax.swing.JLabel();
		panelE = new javax.swing.JPanel();
		cboxChannelE = new javax.swing.JComboBox();
		cboxPME = new javax.swing.JComboBox();
		txtValE = new javax.swing.JFormattedTextField();
		labelE = new javax.swing.JLabel();
		labelEqualE = new javax.swing.JLabel();
		panelF = new javax.swing.JPanel();
		cboxChannelF = new javax.swing.JComboBox();
		cboxPMF = new javax.swing.JComboBox();
		labelF = new javax.swing.JLabel();
		labelEqualF = new javax.swing.JLabel();
		txtValF = new javax.swing.JFormattedTextField();
		panelG = new javax.swing.JPanel();
		cboxChannelG = new javax.swing.JComboBox();
		cboxPMG = new javax.swing.JComboBox();
		labelG = new javax.swing.JLabel();
		labelEqualG = new javax.swing.JLabel();
		txtValG = new javax.swing.JFormattedTextField();
		panelH = new javax.swing.JPanel();
		cboxChannelH = new javax.swing.JComboBox();
		cboxPMH = new javax.swing.JComboBox();
		txtValH = new javax.swing.JFormattedTextField();
		labelH = new javax.swing.JLabel();
		labelEqualH = new javax.swing.JLabel();
		//end variable initialization
		
		panelDelay.setPreferredSize(new java.awt.Dimension(500, 500));

		labelChannel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelChannel.setText("Channel");
		labelChannel.setPreferredSize(new java.awt.Dimension(100, 30));

		labelDelay2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelDelay2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelDelay2.setText("Delay");
		labelDelay2.setPreferredSize(new java.awt.Dimension(100, 30));

		jPanelT0.setPreferredSize(new java.awt.Dimension(500, 30));
		jPanelT0.setRequestFocusEnabled(false);

		labelEqual.setFont(deFont); // NOI18N
		labelEqual.setText("=");
		labelEqual.setPreferredSize(new java.awt.Dimension(15, 30));

		labelT0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelT0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelT0.setText("T0");
		labelT0.setPreferredSize(new java.awt.Dimension(30, 30));

		labelChannel0.setFont(deFont); // NOI18N
		labelChannel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelChannel0.setText("0");
		labelChannel0.setPreferredSize(new java.awt.Dimension(55, 30));

		mConn.writeLine("DLAY? 0");  
		temp = mConn.readLine().substring(2);
		labelT0val.setFont(deFont); // NOI18N
		labelT0val.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelT0val.setText(temp);
		labelT0val.setPreferredSize(new java.awt.Dimension(200, 30));

		javax.swing.GroupLayout jPanelT0Layout = new javax.swing.GroupLayout(jPanelT0);
		jPanelT0.setLayout(jPanelT0Layout);
		jPanelT0Layout.setHorizontalGroup(
				jPanelT0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelT0Layout.createSequentialGroup()
						.addContainerGap(105, Short.MAX_VALUE)
						.addComponent(labelT0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelChannel0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(labelT0val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(64, 64, 64))
		);
		jPanelT0Layout.setVerticalGroup(
				jPanelT0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanelT0Layout.createSequentialGroup()
						.addGroup(jPanelT0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelEqual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelChannel0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT0val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(0, 0, Short.MAX_VALUE))
		);

		panelT1.setPreferredSize(new java.awt.Dimension(500, 30));

		labelChannel1.setFont(deFont); // NOI18N
		labelChannel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelChannel1.setText("0");
		labelChannel1.setPreferredSize(new java.awt.Dimension(55, 30));

		labelEqual1.setFont(deFont); // NOI18N
		labelEqual1.setText("=");
		labelEqual1.setPreferredSize(new java.awt.Dimension(15, 30));

		labelT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelT1.setText("T1");
		labelT1.setPreferredSize(new java.awt.Dimension(30, 30));

		mConn.writeLine("DLAY? 1");  
		temp = mConn.readLine().substring(2);
		labelT1val.setFont(deFont); // NOI18N
		labelT1val.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelT1val.setText(temp);
		labelT1val.setPreferredSize(new java.awt.Dimension(200, 30));

		javax.swing.GroupLayout panelT1Layout = new javax.swing.GroupLayout(panelT1);
		panelT1.setLayout(panelT1Layout);
		panelT1Layout.setHorizontalGroup(
				panelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelT1Layout.createSequentialGroup()
						.addContainerGap(105, Short.MAX_VALUE)
						.addComponent(labelT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqual1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelChannel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(labelT1val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(64, 64, 64))
		);
		panelT1Layout.setVerticalGroup(
				panelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelT1Layout.createSequentialGroup()
						.addGroup(panelT1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelChannel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelEqual1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT1val, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		panelA.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 2");
		temp = mConn.readLine().substring(0,1);

		if(temp.equals("0"))
			intemp = 0;
		else
			intemp = Integer.parseInt(temp) - 2;
		//cboxChannelA.setSelectedIndex(Integer.parseInt(temp));
		cboxChannelA.setFont(deFont); // NOI18N
		cboxChannelA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "B", "C", "D", "E", "F", "G", "H" }));
		cboxChannelA.setSelectedIndex(intemp);
		cboxChannelA.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelA.addActionListener(action);
		
		mConn.writeLine("DLAY? 2");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMA.setFont(deFont); // NOI18N
		cboxPMA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMA.setSelectedIndex(intemp);
		cboxPMA.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMA.addActionListener(action);

		labelEqualA.setFont(deFont); // NOI18N
		labelEqualA.setText("=");
		labelEqualA.setPreferredSize(new java.awt.Dimension(15, 30));

		labelA.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelA.setText("A");
		labelA.setPreferredSize(new java.awt.Dimension(30, 30));

		mConn.writeLine("DLAY? 2");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValA.setText(temp);
		txtValA.setFont(deFont); // NOI18N
		txtValA.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValA.addPropertyChangeListener(action);

		javax.swing.GroupLayout panelALayout = new javax.swing.GroupLayout(panelA);
		panelA.setLayout(panelALayout);
		panelALayout.setHorizontalGroup(
				panelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelALayout.createSequentialGroup()
						.addGap(0, 107, Short.MAX_VALUE)
						.addComponent(labelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelALayout.setVerticalGroup(
				panelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelALayout.createSequentialGroup()
						.addGroup(panelALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cboxPMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxChannelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelEqualA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtValA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		panelB.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 3");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMB.setFont(deFont); // NOI18N
		cboxPMB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMB.setSelectedIndex(intemp);
		cboxPMB.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMB.addActionListener(action);

		labelB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelB.setText("B");
		labelB.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualB.setFont(deFont); // NOI18N
		labelEqualB.setText("=");
		labelEqualB.setPreferredSize(new java.awt.Dimension(15, 30));

		mConn.writeLine("DLAY? 3");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValB.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValB.setText(temp);
		txtValB.setFont(deFont); // NOI18N
		txtValB.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValB.addPropertyChangeListener(action);

		mConn.writeLine("DLAY? 3");
		temp = mConn.readLine().substring(0,1);
		intemp = Integer.parseInt(temp);

		if(intemp == 0)
		{
			//do nothing
		}
		else if(intemp == 2 )
			intemp = intemp - 1;
		else
			intemp = intemp - 2;
		cboxChannelB.setFont(deFont); // NOI18N
		cboxChannelB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "C", "D", "E", "F", "G", "H" }));
		cboxChannelB.setSelectedIndex(intemp);
		cboxChannelB.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelB.addActionListener(action);

		javax.swing.GroupLayout panelBLayout = new javax.swing.GroupLayout(panelB);
		panelB.setLayout(panelBLayout);
		panelBLayout.setHorizontalGroup(
				panelBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelBLayout.createSequentialGroup()
						.addContainerGap(107, Short.MAX_VALUE)
						.addComponent(labelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelBLayout.setVerticalGroup(
				panelBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelBLayout.createSequentialGroup()
						.addGroup(panelBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtValB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxPMB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxChannelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelEqualB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(0, 0, Short.MAX_VALUE))
		);

		panelC.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 4");
		temp = mConn.readLine().substring(0,1);
		intemp = Integer.parseInt(temp);

		if(intemp == 0)
		{
			//do nothing
		}
		else if(intemp < 4)
			intemp = intemp - 1;
		else
			intemp = intemp - 2;
		cboxChannelC.setFont(deFont); // NOI18N
		cboxChannelC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "D", "E", "F", "G", "H" }));
		cboxChannelC.setSelectedIndex(intemp);
		cboxChannelC.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelC.addActionListener(action);

		mConn.writeLine("DLAY? 4");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMC.setFont(deFont); // NOI18N
		cboxPMC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMC.setSelectedIndex(intemp);
		cboxPMC.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMC.addActionListener(action);

		labelC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelC.setText("C");
		labelC.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualC.setFont(deFont); // NOI18N
		labelEqualC.setText("=");
		labelEqualC.setPreferredSize(new java.awt.Dimension(15, 30));

		mConn.writeLine("DLAY? 4");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValC.setText(temp);
		txtValC.setFont(deFont); // NOI18N
		txtValC.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValC.addPropertyChangeListener(action);
		

		javax.swing.GroupLayout panelCLayout = new javax.swing.GroupLayout(panelC);
		panelC.setLayout(panelCLayout);
		panelCLayout.setHorizontalGroup(
				panelCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelCLayout.createSequentialGroup()
						.addContainerGap(107, Short.MAX_VALUE)
						.addComponent(labelC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelCLayout.setVerticalGroup(
				panelCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(labelC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(labelEqualC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(cboxChannelC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(panelCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cboxPMC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtValC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);

		panelD.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 5");
		temp = mConn.readLine().substring(0,1);
		intemp = Integer.parseInt(temp);

		if(intemp == 0)
		{
			//do nothing
		}
		else if(intemp < 5)
			intemp = intemp - 1;
		else
			intemp = intemp - 2;
		cboxChannelD.setFont(deFont); // NOI18N
		cboxChannelD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "E", "F", "G", "H" }));
		cboxChannelD.setSelectedIndex(intemp);
		cboxChannelD.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelD.addActionListener(action);
		

		mConn.writeLine("DLAY? 5");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMD.setFont(deFont); // NOI18N
		cboxPMD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMD.setSelectedIndex(intemp);
		cboxPMD.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMD.addActionListener(action);
		
		mConn.writeLine("DLAY? 5");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValD.setText(temp);
		txtValD.setFont(deFont); // NOI18N
		txtValD.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValD.addPropertyChangeListener(action);
		
		labelD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelD.setText("D");
		labelD.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualD.setFont(deFont); // NOI18N
		labelEqualD.setText("=");
		labelEqualD.setPreferredSize(new java.awt.Dimension(15, 30));

		javax.swing.GroupLayout panelDLayout = new javax.swing.GroupLayout(panelD);
		panelD.setLayout(panelDLayout);
		panelDLayout.setHorizontalGroup(
				panelDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelDLayout.createSequentialGroup()
						.addContainerGap(107, Short.MAX_VALUE)
						.addComponent(labelD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPMD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelDLayout.setVerticalGroup(
				panelDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelDLayout.createSequentialGroup()
						.addGroup(panelDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cboxChannelD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtValD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelEqualD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxPMD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(0, 0, Short.MAX_VALUE))
		);

		panelE.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 6");
		temp = mConn.readLine().substring(0,1);
		intemp = Integer.parseInt(temp);

		if(intemp == 0)
		{
			//do nothing
		}
		else if(intemp < 6)
			intemp = intemp - 1;
		else
			intemp = intemp - 2;
		cboxChannelE.setFont(deFont); // NOI18N
		cboxChannelE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "F", "G", "H" }));
		cboxChannelE.setSelectedIndex(intemp);
		cboxChannelE.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelE.addActionListener(action);
		

		mConn.writeLine("DLAY? 6");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPME.setFont(deFont); // NOI18N
		cboxPME.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPME.setSelectedIndex(intemp);
		cboxPME.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPME.addActionListener(action);
		

		mConn.writeLine("DLAY? 6");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValE.setText(temp);
		txtValE.setFont(deFont); // NOI18N
		txtValE.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValE.addPropertyChangeListener(action);
		

		labelE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelE.setText("E");
		labelE.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualE.setFont(deFont); // NOI18N
		labelEqualE.setText("=");
		labelEqualE.setPreferredSize(new java.awt.Dimension(15, 30));

		javax.swing.GroupLayout panelELayout = new javax.swing.GroupLayout(panelE);
		panelE.setLayout(panelELayout);
		panelELayout.setHorizontalGroup(
				panelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelELayout.createSequentialGroup()
						.addContainerGap(107, Short.MAX_VALUE)
						.addComponent(labelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelELayout.setVerticalGroup(
				panelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelELayout.createSequentialGroup()
						.addGroup(panelELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtValE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxPME, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxChannelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelEqualE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(26, 26, 26))
		);

		panelF.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 7");
		temp = mConn.readLine().substring(0,1);

		intemp = Integer.parseInt(temp);

		if(intemp == 0)
		{
			//do nothing
		}
		else if(intemp < 7)
			intemp = intemp - 1;
		else
			intemp = intemp - 2;
		cboxChannelF.setFont(deFont); // NOI18N
		cboxChannelF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "G", "H" }));
		cboxChannelF.setSelectedIndex(intemp);
		cboxChannelF.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelF.addActionListener(action);
		

		mConn.writeLine("DLAY? 7");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMF.setFont(deFont); // NOI18N
		cboxPMF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMF.setSelectedIndex(intemp);
		cboxPMF.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMF.addActionListener(action);
		

		labelF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelF.setText("F");
		labelF.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualF.setFont(deFont); // NOI18N
		labelEqualF.setText("=");
		labelEqualF.setPreferredSize(new java.awt.Dimension(15, 30));

		mConn.writeLine("DLAY? 7");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValF.setText(temp);
		txtValF.setFont(deFont); // NOI18N
		txtValF.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValF.addPropertyChangeListener(action);
		

		javax.swing.GroupLayout panelFLayout = new javax.swing.GroupLayout(panelF);
		panelF.setLayout(panelFLayout);
		panelFLayout.setHorizontalGroup(
				panelFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelFLayout.createSequentialGroup()
						.addContainerGap(107, Short.MAX_VALUE)
						.addComponent(labelF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelFLayout.setVerticalGroup(
				panelFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(labelF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(labelEqualF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(panelFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cboxChannelF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxPMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtValF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);

		panelG.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 8");
		temp = mConn.readLine().substring(0,1);

		intemp = Integer.parseInt(temp);

		if(intemp == 0)
		{
			//do nothing
		}
		else if(intemp < 8)
			intemp = intemp - 1;
		else
			intemp = intemp - 2;
		cboxChannelG.setFont(deFont); // NOI18N
		cboxChannelG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "F", "H" }));
		cboxChannelG.setSelectedIndex(intemp);
		cboxChannelG.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelG.addActionListener(action);
		

		mConn.writeLine("DLAY? 8");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMG.setFont(deFont); // NOI18N
		cboxPMG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMG.setSelectedIndex(intemp);
		cboxPMG.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMG.addActionListener(action);
		

		labelG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelG.setText("G");
		labelG.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualG.setFont(deFont); // NOI18N
		labelEqualG.setText("=");
		labelEqualG.setPreferredSize(new java.awt.Dimension(15, 30));

		mConn.writeLine("DLAY? 8");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValG.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValG.setText(temp);
		txtValG.setFont(deFont); // NOI18N
		txtValG.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValG.addPropertyChangeListener(action);

		javax.swing.GroupLayout panelGLayout = new javax.swing.GroupLayout(panelG);
		panelG.setLayout(panelGLayout);
		panelGLayout.setHorizontalGroup(
				panelGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelGLayout.createSequentialGroup()
						.addContainerGap(107, Short.MAX_VALUE)
						.addComponent(labelG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelGLayout.setVerticalGroup(
				panelGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(labelG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(labelEqualG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(panelGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cboxChannelG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxPMG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtValG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);

		panelH.setPreferredSize(new java.awt.Dimension(500, 30));

		mConn.writeLine("DLAY? 9");
		temp = mConn.readLine().substring(0,1);

		intemp = Integer.parseInt(temp);

		if(intemp == 0)
		{
			//do nothing
		}
		else if(intemp < 9)
			intemp = intemp - 1;
		else
			intemp = intemp - 2;
		cboxChannelH.setFont(deFont); // NOI18N
		cboxChannelH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "F", "G" }));
		cboxChannelH.setSelectedIndex(intemp);
		cboxChannelH.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelH.addActionListener(action);
		
		mConn.writeLine("DLAY? 9");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMH.setFont(deFont); // NOI18N
		cboxPMH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMH.setSelectedIndex(intemp);
		cboxPMH.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMH.addActionListener(action);
		
		mConn.writeLine("DLAY? 9");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValH.setText(temp);
		txtValH.setFont(deFont); // NOI18N
		txtValH.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValH.addPropertyChangeListener(action);
		

		labelH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelH.setText("H");
		labelH.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualH.setFont(deFont); // NOI18N
		labelEqualH.setText("=");
		labelEqualH.setPreferredSize(new java.awt.Dimension(15, 30));

		javax.swing.GroupLayout panelHLayout = new javax.swing.GroupLayout(panelH);
		panelH.setLayout(panelHLayout);
		panelHLayout.setHorizontalGroup(
				panelHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelHLayout.createSequentialGroup()
						.addContainerGap(107, Short.MAX_VALUE)
						.addComponent(labelH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqualH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxChannelH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(cboxPMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtValH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelHLayout.setVerticalGroup(
				panelHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(labelH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(labelEqualH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(panelHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(cboxChannelH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(cboxPMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtValH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);

		javax.swing.GroupLayout panelDelayLayout = new javax.swing.GroupLayout(panelDelay);
		panelDelay.setLayout(panelDelayLayout);
		panelDelayLayout.setHorizontalGroup(
				panelDelayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelDelayLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelDelayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(panelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(panelH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jPanelT0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(panelDelayLayout.createSequentialGroup()
										.addGap(104, 104, 104)
										.addComponent(labelChannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(61, 61, 61)
										.addComponent(labelDelay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(panelT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(panelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(panelD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(panelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(panelC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(panelF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(panelG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(633, Short.MAX_VALUE))
		);
		panelDelayLayout.setVerticalGroup(
				panelDelayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelDelayLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelDelayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelDelay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelChannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jPanelT0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(30, 30, 30)
								.addComponent(panelA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		return panelDelay;
	}
	
	private javax.swing.JPanel levelPanel()
	{
		//initializing necessary variables
		panelLevel = new javax.swing.JPanel();
		
		levelPolarity = new javax.swing.ButtonGroup();
		
		cboxLevel = new javax.swing.JComboBox();
		labelOffset = new javax.swing.JLabel();
		labelAmp = new javax.swing.JLabel();
		labelPolarity = new javax.swing.JLabel();
		txtOffset = new javax.swing.JFormattedTextField();
		labelv = new javax.swing.JLabel();
		txtAmp = new javax.swing.JFormattedTextField();
		labelv2 = new javax.swing.JLabel();
		buttonPos = new javax.swing.JRadioButton();
		buttonNeg = new javax.swing.JRadioButton();
		//end variable initialization
		
		
		panelLevel.setPreferredSize(new java.awt.Dimension(500, 500));

		cboxLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		cboxLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Output T0", "Output AB", "Output CD", "Output EF", "Output GH" }));
		cboxLevel.setPreferredSize(new java.awt.Dimension(150, 30));
		cboxLevel.addActionListener(action);
			

		labelOffset.setFont(deFont); // NOI18N
		labelOffset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelOffset.setText("Offset");
		labelOffset.setPreferredSize(new java.awt.Dimension(100, 30));
		labelOffset.setRequestFocusEnabled(false);

		labelAmp.setFont(deFont); // NOI18N
		labelAmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelAmp.setText("Amplitude");
		labelAmp.setPreferredSize(new java.awt.Dimension(100, 30));

		labelPolarity.setFont(deFont); // NOI18N
		labelPolarity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelPolarity.setText("Polarity");
		labelPolarity.setPreferredSize(new java.awt.Dimension(100, 30));

		mConn.writeLine("LOFF?" + cboxLevel.getSelectedIndex());
		temp = mConn.readLine();
		try {
			txtOffset.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*#.##")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtOffset.setText(temp);
		txtOffset.setToolTipText("ranging over +- 2.0 V");
		txtOffset.setFont(deFont); // NOI18N
		txtOffset.setPreferredSize(new java.awt.Dimension(60, 30));
		txtOffset.addPropertyChangeListener(action);
		

		labelv.setFont(deFont); // NOI18N
		labelv.setText("V");
		labelv.setPreferredSize(new java.awt.Dimension(15, 30));

		mConn.writeLine("LAMP?" + cboxLevel.getSelectedIndex());
		temp = mConn.readLine().substring(1);
		temp = ("0.00" + temp).substring(temp.length());
		try {
			txtAmp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#.##")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtAmp.setText(temp);
		txtAmp.setToolTipText("ranging over 0.50V to 4.00V");
		txtAmp.setFont(deFont); // NOI18N
		txtAmp.setPreferredSize(new java.awt.Dimension(60, 30));
		txtAmp.addPropertyChangeListener(action);
		

		labelv2.setFont(deFont); // NOI18N
		labelv2.setText("V");
		labelv2.setPreferredSize(new java.awt.Dimension(15, 30));

		levelPolarity.add(buttonPos);
		buttonPos.setFont(deFont); // NOI18N
		buttonPos.setText("Positive");
		buttonPos.setPreferredSize(new java.awt.Dimension(100, 30));
		buttonPos.addActionListener(action);

		levelPolarity.add(buttonNeg);
		buttonNeg.setFont(deFont); // NOI18N
		buttonNeg.setText("Negative");
		buttonNeg.setPreferredSize(new java.awt.Dimension(100, 30));
		buttonNeg.addActionListener(action);
		mConn.writeLine("LPOL?" + cboxLevel.getSelectedIndex());
		switch(Integer.parseInt(mConn.readLine())) {
		case 0: levelPolarity.setSelected(buttonNeg.getModel(), true);
		break;
		case 1: levelPolarity.setSelected(buttonPos.getModel(), true);
		break;
		default: System.out.println("Invalid LPOL");
		}

		javax.swing.GroupLayout panelLevelLayout = new javax.swing.GroupLayout(panelLevel);
		panelLevel.setLayout(panelLevelLayout);
		panelLevelLayout.setHorizontalGroup(
				panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLevelLayout.createSequentialGroup()
						.addGap(159, 159, 159)
						.addGroup(panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(cboxLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(panelLevelLayout.createSequentialGroup()
										.addGap(22, 22, 22)
										.addGroup(panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(labelOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelPolarity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(buttonNeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(buttonPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(panelLevelLayout.createSequentialGroup()
																.addComponent(txtAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(labelv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(panelLevelLayout.createSequentialGroup()
																		.addComponent(txtOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(labelv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
																		.addContainerGap(759, Short.MAX_VALUE))
		);
		panelLevelLayout.setVerticalGroup(
				panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLevelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(cboxLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtOffset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(labelv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(labelAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtAmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(labelv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(labelPolarity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(buttonNeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap(249, Short.MAX_VALUE))
		);
		
		return panelLevel;
		
	}
	
	//ACTIONS - MOVE TO OWN CLASS LATER
	private void menuSavePopupMousePressed(java.awt.event.MouseEvent evt) {                                           
		// TODO add your handling code here:
	}                                          

	private void menuSavePopupMouseReleased(java.awt.event.MouseEvent evt) {                                            
		// TODO add your handling code here:
	}                                           

	private void menuSavePopupPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {                                                         
		// TODO add your handling code here:
	}                                                        

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(DG645Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(DG645Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(DG645Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(DG645Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new DG645Gui().setVisible(true);
			}
		});
	}
	
	
	// Variables declaration                    
	public javax.swing.ButtonGroup burstOnOff;
	public javax.swing.ButtonGroup burstOutput;
	public javax.swing.JRadioButton buttonExtF;
	public javax.swing.JRadioButton buttonExtR;
	public javax.swing.JRadioButton buttonInt;
	public javax.swing.JRadioButton buttonLine;
	public javax.swing.JRadioButton buttonNeg;
	public javax.swing.JRadioButton buttonOff;
	public javax.swing.JRadioButton buttonOn;
	public javax.swing.JRadioButton buttonOutputAll;
	public javax.swing.JRadioButton buttonOutputFirst;
	public javax.swing.JRadioButton buttonPos;
	public javax.swing.JRadioButton buttonSngl;
	public javax.swing.JRadioButton buttonSnglExtF;
	public javax.swing.JRadioButton buttonSnglExtR;
	public javax.swing.JRadioButton buttonTrigOff;
	public javax.swing.JRadioButton buttonTrigOn;
	public javax.swing.JComboBox cboxChannelA;
	public javax.swing.JComboBox cboxChannelB;
	public javax.swing.JComboBox cboxChannelC;
	public javax.swing.JComboBox cboxChannelD;
	public javax.swing.JComboBox cboxChannelE;
	public javax.swing.JComboBox cboxChannelF;
	public javax.swing.JComboBox cboxChannelG;
	public javax.swing.JComboBox cboxChannelH;
	public javax.swing.JComboBox cboxLevel;
	public javax.swing.JComboBox cboxPMA;
	public javax.swing.JComboBox cboxPMB;
	public javax.swing.JComboBox cboxPMC;
	public javax.swing.JComboBox cboxPMD;
	public javax.swing.JComboBox cboxPME;
	public javax.swing.JComboBox cboxPMF;
	public javax.swing.JComboBox cboxPMG;
	public javax.swing.JComboBox cboxPMH;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JPanel jPanelT0;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JTabbedPane jTabMenus;
	private javax.swing.JLabel labelA;
	private javax.swing.JLabel labelA1;
	private javax.swing.JLabel labelABphase;
	private javax.swing.JLabel labelABpres;
	private javax.swing.JLabel labelAdvTrig;
	private javax.swing.JLabel labelAmp;
	private javax.swing.JLabel labelB;
	private javax.swing.JLabel labelB1;
	private javax.swing.JLabel labelC;
	private javax.swing.JLabel labelC1;
	private javax.swing.JLabel labelCDphase;
	private javax.swing.JLabel labelCDpres;
	private javax.swing.JLabel labelCNT;
	private javax.swing.JLabel labelChannel;
	private javax.swing.JLabel labelChannel0;
	private javax.swing.JLabel labelChannel1;
	private javax.swing.JLabel labelD;
	private javax.swing.JLabel labelD1;
	private javax.swing.JLabel labelDelay;
	private javax.swing.JLabel labelDelay2;
	private javax.swing.JLabel labelE;
	private javax.swing.JLabel labelE1;
	private javax.swing.JLabel labelEFphase;
	private javax.swing.JLabel labelEFpres;
	private javax.swing.JLabel labelEdge;
	private javax.swing.JLabel labelEdge2;
	private javax.swing.JLabel labelEqual;
	private javax.swing.JLabel labelEqual1;
	private javax.swing.JLabel labelEqualA;
	private javax.swing.JLabel labelEqualB;
	private javax.swing.JLabel labelEqualC;
	private javax.swing.JLabel labelEqualD;
	private javax.swing.JLabel labelEqualE;
	private javax.swing.JLabel labelEqualF;
	private javax.swing.JLabel labelEqualG;
	private javax.swing.JLabel labelEqualH;
	private javax.swing.JLabel labelExt;
	private javax.swing.JLabel labelExt2;
	private javax.swing.JLabel labelF;
	private javax.swing.JLabel labelF1;
	private javax.swing.JLabel labelG;
	private javax.swing.JLabel labelG1;
	private javax.swing.JLabel labelGHphase;
	private javax.swing.JLabel labelGHpres;
	private javax.swing.JLabel labelH;
	private javax.swing.JLabel labelH1;
	private javax.swing.JLabel labelHold;
	private javax.swing.JLabel labelHost;
	private javax.swing.JLabel labelIP;
	private javax.swing.JLabel labelInt;
	private javax.swing.JLabel labelLine;
	private javax.swing.JLabel labelMode;
	private javax.swing.JLabel labelOffset;
	private javax.swing.JLabel labelPeriod;
	private javax.swing.JLabel labelPolarity;
	private javax.swing.JLabel labelPort;
	private javax.swing.JLabel labelPresConfig;
	private javax.swing.JLabel labelSngl;
	private javax.swing.JLabel labelSnglExt;
	private javax.swing.JLabel labelSnglExt2;
	private javax.swing.JLabel labelT0;
	private javax.swing.JLabel labelT0val;
	private javax.swing.JLabel labelT1;
	private javax.swing.JLabel labelT1val;
	private javax.swing.JLabel labelT2;
	private javax.swing.JLabel labelTrigPS;
	private javax.swing.JLabel labelTrigRate;
	private javax.swing.JLabel labelTrigThres;
	private javax.swing.JLabel labelv;
	private javax.swing.JLabel labelv2;
	public javax.swing.ButtonGroup levelPolarity;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenu menuEdit;
	private javax.swing.JMenuItem menuExit;
	private javax.swing.JMenu menuFile;
	private javax.swing.JMenuItem menuSave;
	private javax.swing.JPopupMenu menuSavePopup;
	private javax.swing.JMenu menuTools;
	private javax.swing.JMenu menuView;
	private javax.swing.JPanel panelA;
	private javax.swing.JPanel panelB;
	private javax.swing.JPanel panelBurst;
	private javax.swing.JPanel panelC;
	private javax.swing.JPanel panelD;
	private javax.swing.JPanel panelDelay;
	private javax.swing.JPanel panelE;
	private javax.swing.JPanel panelF;
	private javax.swing.JPanel panelG;
	private javax.swing.JPanel panelH;
	private javax.swing.JPanel panelLevel;
	private javax.swing.JPanel panelMain;
	private javax.swing.JPanel panelModes;
	private javax.swing.JPanel panelPresConfig;
	private javax.swing.JPanel panelSettings;
	private javax.swing.JPanel panelT1;
	private javax.swing.JPanel panelTrigger;
	private javax.swing.ButtonGroup triggerAdv;
	private javax.swing.ButtonGroup triggerModes;
	public javax.swing.JFormattedTextField txtABphase;
	public javax.swing.JFormattedTextField txtABpres;
	public javax.swing.JFormattedTextField txtAmp;
	public javax.swing.JFormattedTextField txtCDphase;
	public javax.swing.JFormattedTextField txtCDpres;
	public javax.swing.JFormattedTextField txtCNT;
	public javax.swing.JFormattedTextField txtDelay;
	public javax.swing.JFormattedTextField txtEFphase;
	public javax.swing.JFormattedTextField txtEFpres;
	public javax.swing.JFormattedTextField txtGHphase;
	public javax.swing.JFormattedTextField txtGHpres;
	public javax.swing.JFormattedTextField txtHold;
	public javax.swing.JTextField txtHost;
	public javax.swing.JTextField txtIP;
	public javax.swing.JFormattedTextField txtOffset;
	public javax.swing.JFormattedTextField txtPeriod;
	public javax.swing.JTextField txtPort;
	public javax.swing.JFormattedTextField txtTrigPres;
	public javax.swing.JFormattedTextField txtTrigRate;
	public javax.swing.JFormattedTextField txtTrigThres;
	public javax.swing.JFormattedTextField txtValA;
	public javax.swing.JFormattedTextField txtValB;
	public javax.swing.JFormattedTextField txtValC;
	public javax.swing.JFormattedTextField txtValD;
	public javax.swing.JFormattedTextField txtValE;
	public javax.swing.JFormattedTextField txtValF;
	public javax.swing.JFormattedTextField txtValG;
	public javax.swing.JFormattedTextField txtValH;
	// End of variables declaration    
}

