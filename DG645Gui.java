import java.awt.Font;

import javax.swing.UIManager;

import lib.DeviceConnection;


public class DG645Gui extends javax.swing.JFrame {

	private DeviceConnection mConn=new DeviceConnection("172.20.34.210",5025,System.out);
	private String temp;
	private int intemp;
	private boolean initRun = true;

	/**
	 * Creates new form DG645Gui
	 */
	public DG645Gui() {

		mConn.writeLine("*CLS");

		mConn.writeLine("*IDN?"); 
		System.out.println("reply: "+ mConn.readLine());

		initComponents();
		initRun = false;

	}

	private void checkError()
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

	private void updateDelays(String channel)
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

	private void updateT1()
	{
		mConn.writeLine("DLAY? 1");
		temp = mConn.readLine().substring(2);
		labelT1val.setText(temp);
		//labelT1val.repaint();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {

		triggerModes = new javax.swing.ButtonGroup();
		triggerAdv = new javax.swing.ButtonGroup();
		burstOnOff = new javax.swing.ButtonGroup();
		burstOutput = new javax.swing.ButtonGroup();
		levelPolarity = new javax.swing.ButtonGroup();
		panelMain = new javax.swing.JPanel();
		jTabMenus = new javax.swing.JTabbedPane();
		panelTrigger = new javax.swing.JPanel();
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
		panelBurst = new javax.swing.JPanel();
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
		panelLevel = new javax.swing.JPanel();
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

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("DG645 Settings");

		jTabMenus.setTabPlacement(javax.swing.JTabbedPane.RIGHT);
		jTabMenus.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
		jTabMenus.setPreferredSize(new java.awt.Dimension(750, 1000));

		panelTrigger.setPreferredSize(new java.awt.Dimension(750, 900));

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
		txtTrigRate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtTrigRate.setPreferredSize(new java.awt.Dimension(120, 30));
		txtTrigRate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtTrigRatePropertyChange(evt);
			}
		});

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
		txtTrigThres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtTrigThres.setPreferredSize(new java.awt.Dimension(60, 30));
		txtTrigThres.setRequestFocusEnabled(false);
		txtTrigThres.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtTrigThresPropertyChange(evt);
			}
		});

		triggerAdv.add(buttonTrigOn);
		buttonTrigOn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonTrigOn.setText("On");
		buttonTrigOn.setPreferredSize(new java.awt.Dimension(50, 30));
		buttonTrigOn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonTrigOnActionPerformed(evt);
			}
		});

		triggerAdv.add(buttonTrigOff);
		buttonTrigOff.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonTrigOff.setText("Off");
		buttonTrigOff.setPreferredSize(new java.awt.Dimension(50, 30));
		buttonTrigOff.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonTrigOffActionPerformed(evt);
			}
		});
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
		txtHold.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtHold.setPreferredSize(new java.awt.Dimension(160, 30));
		txtHold.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtHoldPropertyChange(evt);
			}
		});

		labelTrigRate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelTrigRate.setText("Trig Rate");
		labelTrigRate.setPreferredSize(new java.awt.Dimension(90, 30));

		labelTrigThres.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelTrigThres.setText("Trig Thres");
		labelTrigThres.setPreferredSize(new java.awt.Dimension(90, 30));

		labelAdvTrig.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelAdvTrig.setText("Adv. Trig");
		labelAdvTrig.setPreferredSize(new java.awt.Dimension(90, 30));

		labelHold.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelHold.setText("Hold");
		labelHold.setPreferredSize(new java.awt.Dimension(70, 30));

		labelPresConfig.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelPresConfig.setText("Prescaler Configuration");
		labelPresConfig.setPreferredSize(new java.awt.Dimension(200, 30));

		labelT2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelT2.setText("T0");
		labelT2.setPreferredSize(new java.awt.Dimension(30, 30));

		labelA1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelA1.setText("A");
		labelA1.setPreferredSize(new java.awt.Dimension(20, 30));
		labelA1.setRequestFocusEnabled(false);

		labelC1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelC1.setText("C");
		labelC1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelE1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelE1.setText("E");
		labelE1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelG1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelG1.setText("G");
		labelG1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelB1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelB1.setText("B");
		labelB1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelD1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelD1.setText("D");
		labelD1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelF1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelF1.setText("F");
		labelF1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelH1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelH1.setText("H");
		labelH1.setPreferredSize(new java.awt.Dimension(20, 30));

		labelTrigPS.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtTrigPres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtTrigPres.setPreferredSize(new java.awt.Dimension(150, 30));
		txtTrigPres.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtTrigPresPropertyChange(evt);
			}
		});

		labelEdge.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelEdge.setText("Edge");
		labelEdge.setPreferredSize(new java.awt.Dimension(50, 30));

		labelEdge2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelEdge2.setText("Edge");
		labelEdge2.setPreferredSize(new java.awt.Dimension(50, 30));

		labelABpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelABpres.setText("AB Prescale");
		labelABpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelCDpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelCDpres.setText("CD Prescale");
		labelCDpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelEFpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelEFpres.setText("EF Prescale");
		labelEFpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelGHpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelGHpres.setText("GH Prescale");
		labelGHpres.setPreferredSize(new java.awt.Dimension(100, 30));

		labelABphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelABphase.setText("AB Phase");
		labelABphase.setPreferredSize(new java.awt.Dimension(85, 30));

		labelCDphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelCDphase.setText("CD Phase");
		labelCDphase.setPreferredSize(new java.awt.Dimension(85, 30));

		labelEFphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelEFphase.setText("EF Phase");
		labelEFphase.setPreferredSize(new java.awt.Dimension(85, 30));

		labelGHphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtABpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtABpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtABpres.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtABpresPropertyChange(evt);
			}
		});

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
		txtCDpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtCDpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtCDpres.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtCDpresPropertyChange(evt);
			}
		});

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
		txtEFpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtEFpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtEFpres.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtEFpresPropertyChange(evt);
			}
		});

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
		txtGHpres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtGHpres.setPreferredSize(new java.awt.Dimension(100, 30));
		txtGHpres.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtGHpresPropertyChange(evt);
			}
		});

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
		txtABphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtABphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtABphase.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtABphasePropertyChange(evt);
			}
		});

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
		txtCDphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtCDphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtCDphase.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtCDphasePropertyChange(evt);
			}
		});

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
		txtEFphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtEFphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtEFphase.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtEFphasePropertyChange(evt);
			}
		});

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
		txtGHphase.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtGHphase.setPreferredSize(new java.awt.Dimension(100, 30));
		txtGHphase.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtGHphasePropertyChange(evt);
			}
		});

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
																												.addGap(0, 55, Short.MAX_VALUE))
		);

		triggerModes.add(buttonLine);
		buttonLine.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonLine.setText("LINE");
		buttonLine.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonLine.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonLineActionPerformed(evt);
			}
		});
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
		buttonSnglExtF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonSnglExtF.setText("SNGL EXT ↓");
		buttonSnglExtF.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonSnglExtF.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSnglExtFActionPerformed(evt);
			}
		});

		triggerModes.add(buttonSngl);
		buttonSngl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonSngl.setText("SNGL");
		buttonSngl.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonSngl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSnglActionPerformed(evt);
			}
		});

		labelMode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelMode.setText("Select Mode");
		labelMode.setPreferredSize(new java.awt.Dimension(100, 30));

		labelInt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelInt.setText("Internal triggering at rates from 100 uHz to MHz");
		labelInt.setPreferredSize(new java.awt.Dimension(400, 30));
		labelInt.setRequestFocusEnabled(false);

		labelExt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelExt.setText("External triggering on rising edges");
		labelExt.setPreferredSize(new java.awt.Dimension(400, 30));

		labelExt2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelExt2.setText("External triggering on falling edges");
		labelExt2.setPreferredSize(new java.awt.Dimension(400, 30));

		labelSnglExt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelSnglExt.setText("Externally triggered single shot on a rising edge");
		labelSnglExt.setPreferredSize(new java.awt.Dimension(400, 30));

		triggerModes.add(buttonSnglExtR);
		buttonSnglExtR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonSnglExtR.setText("SNGL EXT ↑");
		buttonSnglExtR.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonSnglExtR.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonSnglExtRActionPerformed(evt);
			}
		});

		labelSnglExt2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelSnglExt2.setText("Externally triggered single shot on a falling edge");
		labelSnglExt2.setPreferredSize(new java.awt.Dimension(400, 30));

		triggerModes.add(buttonInt);
		buttonInt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonInt.setText("INT");
		buttonInt.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonInt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonIntActionPerformed(evt);
			}
		});

		labelLine.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelLine.setText("Trigger at the power line frequency");
		labelLine.setPreferredSize(new java.awt.Dimension(400, 30));

		labelSngl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelSngl.setText("Single shot triggering");
		labelSngl.setPreferredSize(new java.awt.Dimension(400, 30));

		triggerModes.add(buttonExtF);
		buttonExtF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonExtF.setText("EXT ↓");
		buttonExtF.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonExtF.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonExtFActionPerformed(evt);
			}
		});

		triggerModes.add(buttonExtR);
		buttonExtR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonExtR.setText("EXT ↑");
		buttonExtR.setPreferredSize(new java.awt.Dimension(120, 30));
		buttonExtR.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonExtRActionPerformed(evt);
			}
		});

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
												.addGroup(panelModesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(labelExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelExt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelSnglExt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelSnglExt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelSngl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(labelLine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

		javax.swing.GroupLayout panelTriggerLayout = new javax.swing.GroupLayout(panelTrigger);
		panelTrigger.setLayout(panelTriggerLayout);
		panelTriggerLayout.setHorizontalGroup(
				panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelTriggerLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(panelTriggerLayout.createSequentialGroup()
										.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(labelTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelAdvTrig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(labelTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(buttonTrigOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(txtTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(buttonTrigOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(panelTriggerLayout.createSequentialGroup()
																.addComponent(labelHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(txtHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addComponent(txtTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addComponent(panelModes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addContainerGap(103, Short.MAX_VALUE))
																.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTriggerLayout.createSequentialGroup()
																		.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																		.addComponent(panelPresConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addGap(24, 24, 24))
		);
		panelTriggerLayout.setVerticalGroup(
				panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelTriggerLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(panelModes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(12, 12, 12)
						.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(labelTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTrigRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(labelTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(txtTrigThres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(labelAdvTrig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonTrigOn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(buttonTrigOff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addGroup(panelTriggerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(labelHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(txtHold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(18, 18, 18)
														.addComponent(panelPresConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addContainerGap(119, Short.MAX_VALUE))
		);

		jTabMenus.addTab("Trigger", panelTrigger);

		burstOnOff.add(buttonOn);
		buttonOn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonOn.setText("On");
		buttonOn.setPreferredSize(new java.awt.Dimension(60, 30));
		buttonOn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonOnActionPerformed(evt);
			}
		});

		burstOnOff.add(buttonOff);
		buttonOff.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonOff.setText("Off");
		buttonOff.setPreferredSize(new java.awt.Dimension(60, 30));
		buttonOff.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonOffActionPerformed(evt);
			}
		});
		mConn.writeLine("BURM?");
		switch(Integer.parseInt(mConn.readLine())) {
		case 0: burstOnOff.setSelected(buttonOff.getModel(), true);
		break;
		case 1: burstOnOff.setSelected(buttonOn.getModel(), true);
		break;
		default: System.out.println("Invalid BURM");
		}

		burstOutput.add(buttonOutputAll);
		buttonOutputAll.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonOutputAll.setText("T0 fire on all delay cycles of burst");
		buttonOutputAll.setPreferredSize(new java.awt.Dimension(300, 30));
		buttonOutputAll.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonOutputAllActionPerformed(evt);
			}
		});

		burstOutput.add(buttonOutputFirst);
		buttonOutputFirst.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonOutputFirst.setText("T0 fire on first delay cycle of burst");
		buttonOutputFirst.setPreferredSize(new java.awt.Dimension(300, 30));
		buttonOutputFirst.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonOutputFirstActionPerformed(evt);
			}
		});
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
		txtCNT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtCNT.setPreferredSize(new java.awt.Dimension(50, 30));
		txtCNT.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtCNTPropertyChange(evt);
			}
		});

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
		txtPeriod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtPeriod.setPreferredSize(new java.awt.Dimension(50, 30));
		txtPeriod.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtPeriodPropertyChange(evt);
			}
		});

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
		txtDelay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtDelay.setPreferredSize(new java.awt.Dimension(100, 30));
		txtDelay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtDelayPropertyChange(evt);
			}
		});

		labelCNT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelCNT.setText("CNT");
		labelCNT.setPreferredSize(new java.awt.Dimension(50, 30));

		labelPeriod.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelPeriod.setText("Period");
		labelPeriod.setPreferredSize(new java.awt.Dimension(50, 30));

		labelDelay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelDelay.setText("Delay");
		labelDelay.setPreferredSize(new java.awt.Dimension(50, 30));

		javax.swing.GroupLayout panelBurstLayout = new javax.swing.GroupLayout(panelBurst);
		panelBurst.setLayout(panelBurstLayout);
		panelBurstLayout.setHorizontalGroup(
				panelBurstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBurstLayout.createSequentialGroup()
						.addContainerGap(233, Short.MAX_VALUE)
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
																.addGap(144, 144, 144))
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

		jTabMenus.addTab("Burst", panelBurst);

		panelDelay.setPreferredSize(new java.awt.Dimension(350, 40));

		labelChannel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelChannel.setText("Channel");
		labelChannel.setPreferredSize(new java.awt.Dimension(100, 30));

		labelDelay2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelDelay2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelDelay2.setText("Delay");
		labelDelay2.setPreferredSize(new java.awt.Dimension(100, 30));

		jPanelT0.setPreferredSize(new java.awt.Dimension(500, 30));
		jPanelT0.setRequestFocusEnabled(false);

		labelEqual.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelEqual.setText("=");
		labelEqual.setPreferredSize(new java.awt.Dimension(15, 30));

		labelT0.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelT0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelT0.setText("T0");
		labelT0.setPreferredSize(new java.awt.Dimension(30, 30));

		labelChannel0.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelChannel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelChannel0.setText("0");
		labelChannel0.setPreferredSize(new java.awt.Dimension(55, 30));

		mConn.writeLine("DLAY? 0");  
		temp = mConn.readLine().substring(2);
		labelT0val.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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

		labelChannel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelChannel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelChannel1.setText("0");
		labelChannel1.setPreferredSize(new java.awt.Dimension(55, 30));

		labelEqual1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelEqual1.setText("=");
		labelEqual1.setPreferredSize(new java.awt.Dimension(15, 30));

		labelT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelT1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelT1.setText("T1");
		labelT1.setPreferredSize(new java.awt.Dimension(30, 30));

		mConn.writeLine("DLAY? 1");  
		temp = mConn.readLine().substring(2);
		labelT1val.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		cboxChannelA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "B", "C", "D", "E", "F", "G", "H" }));
		cboxChannelA.setSelectedIndex(intemp);
		cboxChannelA.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelA.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelAActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 2");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPMA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMA.setSelectedIndex(intemp);
		cboxPMA.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMA.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMAActionPerformed(evt);
			}
		});

		labelEqualA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtValA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValA.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValA.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValAPropertyChange(evt);
			}
		});

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
		cboxPMB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPMB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMB.setSelectedIndex(intemp);
		cboxPMB.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMBActionPerformed(evt);
			}
		});

		labelB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelB.setText("B");
		labelB.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtValB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValB.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValB.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValBPropertyChange(evt);
			}
		});

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
		cboxChannelB.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "C", "D", "E", "F", "G", "H" }));
		cboxChannelB.setSelectedIndex(intemp);
		cboxChannelB.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelB.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelBActionPerformed(evt);
			}
		});

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
		cboxChannelC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "D", "E", "F", "G", "H" }));
		cboxChannelC.setSelectedIndex(intemp);
		cboxChannelC.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelCActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 4");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPMC.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMC.setSelectedIndex(intemp);
		cboxPMC.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMCActionPerformed(evt);
			}
		});

		labelC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelC.setText("C");
		labelC.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtValC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValC.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValC.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValCPropertyChange(evt);
			}
		});

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
		cboxChannelD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "E", "F", "G", "H" }));
		cboxChannelD.setSelectedIndex(intemp);
		cboxChannelD.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelD.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelDActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 5");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPMD.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMD.setSelectedIndex(intemp);
		cboxPMD.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMD.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMDActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 5");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValD.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValD.setText(temp);
		txtValD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValD.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValD.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValDPropertyChange(evt);
			}
		});

		labelD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelD.setText("D");
		labelD.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		cboxChannelE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "F", "G", "H" }));
		cboxChannelE.setSelectedIndex(intemp);
		cboxChannelE.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelE.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelEActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 6");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPME.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPME.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPME.setSelectedIndex(intemp);
		cboxPME.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPME.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMEActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 6");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValE.setText(temp);
		txtValE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValE.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValE.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValEPropertyChange(evt);
			}
		});

		labelE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelE.setText("E");
		labelE.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualE.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		cboxChannelF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "G", "H" }));
		cboxChannelF.setSelectedIndex(intemp);
		cboxChannelF.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelF.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelFActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 7");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPMF.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMF.setSelectedIndex(intemp);
		cboxPMF.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMF.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMFActionPerformed(evt);
			}
		});

		labelF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelF.setText("F");
		labelF.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtValF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValF.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValF.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValFPropertyChange(evt);
			}
		});

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
		cboxChannelG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "F", "H" }));
		cboxChannelG.setSelectedIndex(intemp);
		cboxChannelG.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelG.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelGActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 8");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPMG.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMG.setSelectedIndex(intemp);
		cboxPMG.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMG.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMGActionPerformed(evt);
			}
		});

		labelG.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelG.setText("G");
		labelG.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtValG.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValG.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValG.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValGPropertyChange(evt);
			}
		});

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
		cboxChannelH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxChannelH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "F", "G" }));
		cboxChannelH.setSelectedIndex(intemp);
		cboxChannelH.setPreferredSize(new java.awt.Dimension(55, 30));
		cboxChannelH.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxChannelHActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 9");  
		temp = mConn.readLine().substring(2,3);  
		if(temp.equals("+"))      
			intemp = 0;
		else
			intemp = 1;
		cboxPMH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		cboxPMH.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-" }));
		cboxPMH.setSelectedIndex(intemp);
		cboxPMH.setPreferredSize(new java.awt.Dimension(45, 30));
		cboxPMH.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxPMHActionPerformed(evt);
			}
		});

		mConn.writeLine("DLAY? 9");  
		temp = mConn.readLine().substring(3);  
		temp = ("000.000000000000" + temp).substring(temp.length());
		try {
			txtValH.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.############")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtValH.setText(temp);
		txtValH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtValH.setPreferredSize(new java.awt.Dimension(200, 30));
		txtValH.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtValHPropertyChange(evt);
			}
		});

		labelH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		labelH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelH.setText("H");
		labelH.setPreferredSize(new java.awt.Dimension(30, 30));

		labelEqualH.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
						.addGap(33, 33, 33)
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
										.addContainerGap(144, Short.MAX_VALUE))
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
								.addContainerGap(559, Short.MAX_VALUE))
		);

		jTabMenus.addTab("Delay", panelDelay);

		cboxLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
		cboxLevel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Output T0", "Output AB", "Output CD", "Output EF", "Output GH" }));
		cboxLevel.setPreferredSize(new java.awt.Dimension(150, 30));
		cboxLevel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cboxLevelActionPerformed(evt);
			}
		});

		labelOffset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelOffset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelOffset.setText("Offset");
		labelOffset.setPreferredSize(new java.awt.Dimension(100, 30));
		labelOffset.setRequestFocusEnabled(false);

		labelAmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelAmp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		labelAmp.setText("Amplitude");
		labelAmp.setPreferredSize(new java.awt.Dimension(100, 30));

		labelPolarity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtOffset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtOffset.setPreferredSize(new java.awt.Dimension(60, 30));
		txtOffset.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtOffsetPropertyChange(evt);
			}
		});

		labelv.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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
		txtAmp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		txtAmp.setPreferredSize(new java.awt.Dimension(60, 30));
		txtAmp.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
			public void propertyChange(java.beans.PropertyChangeEvent evt) {
				txtAmpPropertyChange(evt);
			}
		});

		labelv2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelv2.setText("V");
		labelv2.setPreferredSize(new java.awt.Dimension(15, 30));

		levelPolarity.add(buttonPos);
		buttonPos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonPos.setText("Positive");
		buttonPos.setPreferredSize(new java.awt.Dimension(100, 30));
		buttonPos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonPosActionPerformed(evt);
			}
		});

		levelPolarity.add(buttonNeg);
		buttonNeg.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		buttonNeg.setText("Negative");
		buttonNeg.setPreferredSize(new java.awt.Dimension(100, 30));
		buttonNeg.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				buttonNegActionPerformed(evt);
			}
		});
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
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLevelLayout.createSequentialGroup()
						.addContainerGap(239, Short.MAX_VALUE)
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
																		.addGap(211, 211, 211))
		);
		panelLevelLayout.setVerticalGroup(
				panelLevelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelLevelLayout.createSequentialGroup()
						.addGap(36, 36, 36)
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
												.addContainerGap(755, Short.MAX_VALUE))
		);

		jTabMenus.addTab("Level", panelLevel);

		javax.swing.GroupLayout panelMainLayout = new javax.swing.GroupLayout(panelMain);
		panelMain.setLayout(panelMainLayout);
		panelMainLayout.setHorizontalGroup(
				panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(panelMainLayout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jTabMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
		panelMainLayout.setVerticalGroup(
				panelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMainLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jTabMenus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(panelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
		);

		pack();
	}// </editor-fold>                        

	private void buttonOutputFirstActionPerformed(java.awt.event.ActionEvent evt) {                                                  
		mConn.writeLine("BURT 1");
	}                                                 

	private void txtGHpresPropertyChange(java.beans.PropertyChangeEvent evt) {                                         

		try{
			mConn.writeLine("PRES 4," + txtGHpres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}

	}                                        

	private void txtEFpresPropertyChange(java.beans.PropertyChangeEvent evt) {                                         
		try{
			mConn.writeLine("PRES 3," + txtEFpres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                        

	private void txtCDpresPropertyChange(java.beans.PropertyChangeEvent evt) {                                         
		try{
			mConn.writeLine("PRES 2," + txtCDpres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                        

	private void txtABpresPropertyChange(java.beans.PropertyChangeEvent evt) {                                         
		try{
			mConn.writeLine("PRES 1," + txtABpres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                        

	private void txtTrigPresPropertyChange(java.beans.PropertyChangeEvent evt) {                                           
		try{
			mConn.writeLine("PRES 0," + txtTrigPres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                          

	private void txtHoldPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("HOLD " + txtHold.getValue().toString());
		}catch(Exception e){

		}
	}                                      

	private void buttonTrigOffActionPerformed(java.awt.event.ActionEvent evt) {                                              
		mConn.writeLine("ADVT 0");
	}                                             

	private void buttonTrigOnActionPerformed(java.awt.event.ActionEvent evt) {                                             
		mConn.writeLine("ADVT 1");
	}                                            

	private void txtTrigRatePropertyChange(java.beans.PropertyChangeEvent evt) {                                           

		try{
			mConn.writeLine("TRAT " + txtTrigRate.getValue().toString());
		}catch(Exception e){

		}
	}                                          

	private void buttonLineActionPerformed(java.awt.event.ActionEvent evt) {                                           
		mConn.writeLine("TSRC 6");
	}                                          

	private void buttonSnglActionPerformed(java.awt.event.ActionEvent evt) {                                           
		mConn.writeLine("TSRC 5");
	}                                          

	private void buttonSnglExtFActionPerformed(java.awt.event.ActionEvent evt) {                                               
		mConn.writeLine("TSRC 4");
	}                                              

	private void buttonSnglExtRActionPerformed(java.awt.event.ActionEvent evt) {                                               
		mConn.writeLine("TSRC 3");
	}                                              

	private void buttonExtFActionPerformed(java.awt.event.ActionEvent evt) {                                           
		mConn.writeLine("TSRC 2");
	}                                          

	private void buttonExtRActionPerformed(java.awt.event.ActionEvent evt) {                                           
		mConn.writeLine("TSRC 1");
	}                                          

	private void buttonIntActionPerformed(java.awt.event.ActionEvent evt) {                                          
		mConn.writeLine("TSRC 0");
	}                                         

	private void txtTrigThresPropertyChange(java.beans.PropertyChangeEvent evt) {                                            
		try{
			mConn.writeLine("TLVL," + txtTrigThres.getValue().toString());
		}catch(Exception e){

		}
	}                                           

	private void txtABphasePropertyChange(java.beans.PropertyChangeEvent evt) {                                          
		try{
			mConn.writeLine("PHAS 0," + txtTrigPres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}

	}                                         

	private void txtCDphasePropertyChange(java.beans.PropertyChangeEvent evt) {                                          
		try{
			mConn.writeLine("PHAS 2," + txtTrigPres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                         

	private void txtEFphasePropertyChange(java.beans.PropertyChangeEvent evt) {                                          
		try{
			mConn.writeLine("PHAS 3," + txtTrigPres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                         

	private void txtGHphasePropertyChange(java.beans.PropertyChangeEvent evt) {                                          
		try{
			mConn.writeLine("PHAS 4," + txtTrigPres.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                         

	private void buttonOnActionPerformed(java.awt.event.ActionEvent evt) {                                         
		mConn.writeLine("BURM 1");
	}                                        

	private void buttonOffActionPerformed(java.awt.event.ActionEvent evt) {                                          
		mConn.writeLine("BURM 0");
	}                                         

	private void buttonOutputAllActionPerformed(java.awt.event.ActionEvent evt) {                                                
		mConn.writeLine("BURT 0");
	}                                               

	private void txtCNTPropertyChange(java.beans.PropertyChangeEvent evt) {                                      

		try{
			mConn.writeLine("BURC " + txtCNT.getValue().toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                     

	private void txtPeriodPropertyChange(java.beans.PropertyChangeEvent evt) {                                         
		try{
			mConn.writeLine("BURP " + txtPeriod.getValue());//.toString().replace(",", ""));
		}catch(Exception e){

		}
	}                                        

	private void txtDelayPropertyChange(java.beans.PropertyChangeEvent evt) {                                        
		try{
			mConn.writeLine("BURD " + txtDelay.getValue());//.toString())//.replace(",", ""));

			mConn.writeLine("BURD?");

			temp = mConn.readLine().substring(1);
			temp = ("0.000000000000" + temp).substring(temp.length());

			txtDelay.setText(temp);
		}catch(Exception e){

		}
	}                                       

	private void cboxChannelAActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelA.getSelectedIndex();
			if(index < 1)
				mConn.writeLine("LINK 2," + index);
			else
				mConn.writeLine("LINK 2," + (index+2));

			checkError();

			updateDelays("2");

		}catch(Exception e){

		}
	}                                            

	private void cboxChannelBActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelB.getSelectedIndex();
			if(index == 0)
				mConn.writeLine("Link 3," + index);
			else if(index < 2)
				mConn.writeLine("LINK 3," + (index+1));
			else
				mConn.writeLine("LINK 3," + (index+2));

			checkError();

			updateDelays("3");
		}catch(Exception e){

		}
	}                                            

	private void cboxChannelCActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelC.getSelectedIndex();
			if(index == 0)
				mConn.writeLine("LINK 4," + index);
			else if(index < 3)
				mConn.writeLine("LINK 4," + (index+1));
			else
				mConn.writeLine("LINK 4," + (index+2));

			checkError();    
			updateDelays("4");
		}catch(Exception e){

		}
	}                                            

	private void cboxChannelDActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelD.getSelectedIndex();
			if(index == 0)
				mConn.writeLine("LINK 5," + index);
			else if(index < 4)
				mConn.writeLine("LINK 5," + (index+1));
			else
				mConn.writeLine("LINK 5," + (index+2));

			checkError();
			updateDelays("5");
		}catch(Exception e){

		}
	}                                            

	private void cboxChannelEActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelE.getSelectedIndex();
			if(index == 0)
				mConn.writeLine("LINK 6," + index);
			else if(index < 5)
				mConn.writeLine("LINK 6," + (index+1));
			else
				mConn.writeLine("LINK 6," + (index+2));

			checkError();
			updateDelays("6");
		}catch(Exception e){

		}
	}                                            

	private void cboxChannelFActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelF.getSelectedIndex();
			if(index == 0)
				mConn.writeLine("LINK 7," + index);
			else if(index < 6)
				mConn.writeLine("LINK 7," + (index+1));
			else
				mConn.writeLine("LINK 7," + (index+2));

			checkError();
			updateDelays("7");
		}catch(Exception e){

		}
	}                                            

	private void cboxChannelGActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelG.getSelectedIndex();
			if(index == 0)
				mConn.writeLine("LINK 8," + index);
			else if(index < 7)
				mConn.writeLine("LINK 8," + (index+1));
			else
				mConn.writeLine("LINK 8," + (index+2));

			checkError();
			updateDelays("8");
		}catch(Exception e){

		}
	}                                            

	private void cboxChannelHActionPerformed(java.awt.event.ActionEvent evt) {                                             
		try{
			int index = cboxChannelH.getSelectedIndex();
			if(index == 0)
				mConn.writeLine("LINK 9," + index);
			else if(index < 9)
				mConn.writeLine("LINK 9," + (index+1));
			else
				mConn.writeLine("LINK 9," + (index+2));

			checkError();
			updateDelays("9");
		}catch(Exception e){

		}
	}                                            

	private void cboxPMAActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{
			mConn.writeLine("DLAY? 2");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPMA.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);

			mConn.writeLine("DLAY 2," + replace);

			updateT1();
			checkError();
		}catch(Exception e){

		}
	}                                       

	private void cboxPMBActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{

			mConn.writeLine("DLAY? 3");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPMB.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);

			mConn.writeLine("DLAY 3," + replace);


			updateT1();
			checkError();  
		}catch(Exception e){

		}
	}                                       

	private void cboxPMCActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{

			mConn.writeLine("DLAY? 4");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPMC.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);

			mConn.writeLine("DLAY 4," + replace);


			updateT1();
			checkError();  
		}catch(Exception e){

		}
	}                                       

	private void cboxPMDActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{

			mConn.writeLine("DLAY? 5");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPMD.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);

			mConn.writeLine("DLAY 5," + replace);


			updateT1();
			checkError();
		}catch(Exception e){

		}
	}                                       

	private void cboxPMEActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{

			mConn.writeLine("DLAY? 6");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPME.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);

			mConn.writeLine("DLAY 6," + replace);


			updateT1();
			checkError();
		}catch(Exception e){

		}
	}                                       

	private void cboxPMFActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{

			mConn.writeLine("DLAY? 7");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPMF.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);

			mConn.writeLine("DLAY 7," + replace);


			updateT1();
			checkError();  
		}catch(Exception e){

		}
	}                                       

	private void cboxPMGActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{

			mConn.writeLine("DLAY? 8");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPMG.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);

			mConn.writeLine("DLAY 8," + replace);


			updateT1();
			checkError();

		}catch(Exception e){

		}
	}                                       

	private void cboxPMHActionPerformed(java.awt.event.ActionEvent evt) {                                        
		try{

			mConn.writeLine("DLAY? 9");
			String current = mConn.readLine();
			String replace = current.substring(0, 2);

			int index = cboxPMH.getSelectedIndex();
			if(index == 0)
				replace = replace + "+";
			else
				replace = replace + "-";

			replace = replace + current.substring(3);


			mConn.writeLine("DLAY 9," + replace);


			updateT1();
			checkError();
		}catch(Exception e){

		}
	}                                       

	private void cboxLevelActionPerformed(java.awt.event.ActionEvent evt) {                                          
		try{

			int index = cboxLevel.getSelectedIndex();

			switch(index){
			case 0:
				mConn.writeLine("LAMP? 0");
				txtAmp.setValue(mConn.readLine().substring(1));
				mConn.writeLine("LOFF? 0");
				txtOffset.setValue(mConn.readLine());//.substring(1));
				mConn.writeLine("LPOL? 0");
				temp = mConn.readLine();
				if(temp.equals("0"))
					levelPolarity.setSelected(buttonNeg.getModel(), true);
				else
					levelPolarity.setSelected(buttonPos.getModel(), true);
				break;
			case 1:
				mConn.writeLine("LAMP? 1");
				txtAmp.setValue(mConn.readLine().substring(1));
				mConn.writeLine("LOFF? 1");
				txtOffset.setValue(mConn.readLine());//.substring(1));
				mConn.writeLine("LPOL? 1");
				temp = mConn.readLine();
				if(temp.equals("0"))
					levelPolarity.setSelected(buttonNeg.getModel(), true);
				else
					levelPolarity.setSelected(buttonPos.getModel(), true);
				break;
			case 2:
				mConn.writeLine("LAMP? 2");
				txtAmp.setValue(mConn.readLine().substring(1));
				mConn.writeLine("LOFF? 2");
				txtOffset.setValue(mConn.readLine());//.substring(1));
				mConn.writeLine("LPOL? 2");
				temp = mConn.readLine();
				if(temp.equals("0"))
					levelPolarity.setSelected(buttonNeg.getModel(), true);
				else
					levelPolarity.setSelected(buttonPos.getModel(), true);
				break;
			case 3:
				mConn.writeLine("LAMP? 3");
				txtAmp.setValue(mConn.readLine().substring(1));
				mConn.writeLine("LOFF? 3");
				txtOffset.setValue(mConn.readLine());//.substring(1));
				mConn.writeLine("LPOL? 3");
				temp = mConn.readLine();
				if(temp.equals("0"))
					levelPolarity.setSelected(buttonNeg.getModel(), true);
				else
					levelPolarity.setSelected(buttonPos.getModel(), true);
				break;
			case 4:
				mConn.writeLine("LAMP? 4");
				txtAmp.setValue(mConn.readLine().substring(1));
				mConn.writeLine("LOFF? 4");
				txtOffset.setValue(mConn.readLine());//.substring(1));
				mConn.writeLine("LPOL? 4");
				temp = mConn.readLine();
				if(temp.equals("0"))
					levelPolarity.setSelected(buttonNeg.getModel(), true);
				else
					levelPolarity.setSelected(buttonPos.getModel(), true);
				break;
			default: System.out.println("Invalid Index");
			}

		}catch(Exception e){

		}
	}                                         

	private void txtOffsetPropertyChange(java.beans.PropertyChangeEvent evt) {                                         
		try{

			mConn.writeLine("LOFF " + cboxLevel.getSelectedIndex() + "," + txtOffset.getValue());

			// checkError();
		}catch(Exception e){

		}
	}                                        

	private void txtAmpPropertyChange(java.beans.PropertyChangeEvent evt) {                                      
		try{

			mConn.writeLine("LAMP " + cboxLevel.getSelectedIndex() + "," + txtAmp.getValue());

			// checkError();
		}catch(Exception e){

		}
	}                                     

	private void buttonPosActionPerformed(java.awt.event.ActionEvent evt) {                                          
		mConn.writeLine("LPOL " + cboxLevel.getSelectedIndex() + ",1");
	}                                         

	private void buttonNegActionPerformed(java.awt.event.ActionEvent evt) {                                          
		mConn.writeLine("LPOL " + cboxLevel.getSelectedIndex() + ",0");
	}                                         

	private void txtValAPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 2");
			mConn.writeLine("DLAY 2," + mConn.readLine().substring(0, 3) + txtValA.getValue());

			updateT1();
			checkError();
		}catch(Exception e){

		}
	}                                      

	private void txtValBPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 3");
			mConn.writeLine("DLAY 3," + mConn.readLine().substring(0, 3) + txtValB.getValue());
			updateT1();        
			checkError();   
		}catch(Exception e){

		}
	}                                      

	private void txtValCPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 4");
			mConn.writeLine("DLAY 4," + mConn.readLine().substring(0, 3) + txtValC.getValue());
			updateT1();      
			checkError();   
		}catch(Exception e){

		}
	}                                      

	private void txtValDPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 5");
			mConn.writeLine("DLAY 5," + mConn.readLine().substring(0, 3) + txtValD.getValue());
			updateT1();     
			checkError();    
		}catch(Exception e){

		}
	}                                      

	private void txtValEPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 6");
			mConn.writeLine("DLAY 6," + mConn.readLine().substring(0, 3) + txtValE.getValue());
			updateT1();     
			checkError();    
		}catch(Exception e){

		}
	}                                      

	private void txtValFPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 7");
			mConn.writeLine("DLAY 7," + mConn.readLine().substring(0, 3) + txtValF.getValue());
			updateT1();  
			checkError();
		}catch(Exception e){

		}
	}                                      

	private void txtValGPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 8");
			mConn.writeLine("DLAY 8," + mConn.readLine().substring(0, 3) + txtValG.getValue());
			updateT1();  
			checkError();  
		}catch(Exception e){

		}
	}                                      

	private void txtValHPropertyChange(java.beans.PropertyChangeEvent evt) {                                       
		try{
			mConn.writeLine("DLAY? 9");
			mConn.writeLine("DLAY 9," + mConn.readLine().substring(0, 3) + txtValH.getValue());
			updateT1();   
			checkError();   
		}catch(Exception e){

		}
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

	// Variables declaration - do not modify                     
	private javax.swing.ButtonGroup burstOnOff;
	private javax.swing.ButtonGroup burstOutput;
	private javax.swing.JRadioButton buttonExtF;
	private javax.swing.JRadioButton buttonExtR;
	private javax.swing.JRadioButton buttonInt;
	private javax.swing.JRadioButton buttonLine;
	private javax.swing.JRadioButton buttonNeg;
	private javax.swing.JRadioButton buttonOff;
	private javax.swing.JRadioButton buttonOn;
	private javax.swing.JRadioButton buttonOutputAll;
	private javax.swing.JRadioButton buttonOutputFirst;
	private javax.swing.JRadioButton buttonPos;
	private javax.swing.JRadioButton buttonSngl;
	private javax.swing.JRadioButton buttonSnglExtF;
	private javax.swing.JRadioButton buttonSnglExtR;
	private javax.swing.JRadioButton buttonTrigOff;
	private javax.swing.JRadioButton buttonTrigOn;
	private javax.swing.JComboBox cboxChannelA;
	private javax.swing.JComboBox cboxChannelB;
	private javax.swing.JComboBox cboxChannelC;
	private javax.swing.JComboBox cboxChannelD;
	private javax.swing.JComboBox cboxChannelE;
	private javax.swing.JComboBox cboxChannelF;
	private javax.swing.JComboBox cboxChannelG;
	private javax.swing.JComboBox cboxChannelH;
	private javax.swing.JComboBox cboxLevel;
	private javax.swing.JComboBox cboxPMA;
	private javax.swing.JComboBox cboxPMB;
	private javax.swing.JComboBox cboxPMC;
	private javax.swing.JComboBox cboxPMD;
	private javax.swing.JComboBox cboxPME;
	private javax.swing.JComboBox cboxPMF;
	private javax.swing.JComboBox cboxPMG;
	private javax.swing.JComboBox cboxPMH;
	private javax.swing.JPanel jPanelT0;
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
	private javax.swing.JLabel labelInt;
	private javax.swing.JLabel labelLine;
	private javax.swing.JLabel labelMode;
	private javax.swing.JLabel labelOffset;
	private javax.swing.JLabel labelPeriod;
	private javax.swing.JLabel labelPolarity;
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
	private javax.swing.ButtonGroup levelPolarity;
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
	private javax.swing.JPanel panelT1;
	private javax.swing.JPanel panelTrigger;
	private javax.swing.ButtonGroup triggerAdv;
	private javax.swing.ButtonGroup triggerModes;
	private javax.swing.JFormattedTextField txtABphase;
	private javax.swing.JFormattedTextField txtABpres;
	private javax.swing.JFormattedTextField txtAmp;
	private javax.swing.JFormattedTextField txtCDphase;
	private javax.swing.JFormattedTextField txtCDpres;
	private javax.swing.JFormattedTextField txtCNT;
	private javax.swing.JFormattedTextField txtDelay;
	private javax.swing.JFormattedTextField txtEFphase;
	private javax.swing.JFormattedTextField txtEFpres;
	private javax.swing.JFormattedTextField txtGHphase;
	private javax.swing.JFormattedTextField txtGHpres;
	private javax.swing.JFormattedTextField txtHold;
	private javax.swing.JFormattedTextField txtOffset;
	private javax.swing.JFormattedTextField txtPeriod;
	private javax.swing.JFormattedTextField txtTrigPres;
	private javax.swing.JFormattedTextField txtTrigRate;
	private javax.swing.JFormattedTextField txtTrigThres;
	private javax.swing.JFormattedTextField txtValA;
	private javax.swing.JFormattedTextField txtValB;
	private javax.swing.JFormattedTextField txtValC;
	private javax.swing.JFormattedTextField txtValD;
	private javax.swing.JFormattedTextField txtValE;
	private javax.swing.JFormattedTextField txtValF;
	private javax.swing.JFormattedTextField txtValG;
	private javax.swing.JFormattedTextField txtValH;
	// End of variables declaration                   
}