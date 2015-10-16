import java.awt.*;
import java.text.*;
import javax.swing.*;

import javax.swing.text.*;

public class DG645Trigger extends DG645Menu{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DG645Trigger(DG645Gui parent) {
		super(parent);
		
		if(gui.settings.dg645.mConn != null)
			triggerPanel();
/*		else
			System.out.println("mconn is null in trigger");*/
	}

	private void triggerPanel()
	{
		//initializing necessary variables
		
		//trigger mode variables
		triggerModes = new ButtonGroup();
		buttonInt = new JRadioButton();
		buttonExtR = new JRadioButton();
		buttonExtF = new JRadioButton();
		buttonSngl = new JRadioButton();
		buttonSnglExtR = new JRadioButton();
		buttonSnglExtF = new JRadioButton();
		buttonLine = new JRadioButton();
		
		panelModes = new JPanel();
		labelMode = new JLabel();
		labelInt = new JLabel();
		labelExt = new JLabel();
		labelExt2 = new JLabel();
		labelSnglExt = new JLabel();
		labelSnglExt2 = new JLabel();
		labelLine = new JLabel();
		labelSngl = new JLabel();
		
		//adv trigger variables
		triggerAdv = new ButtonGroup();
		buttonTrigOn = new JRadioButton();
		buttonTrigOff = new JRadioButton();

		txtHold = new JFormattedTextField();
		labelAdvTrig = new JLabel();
		labelHold = new JLabel();
		
		//trigger rate, threshold
		txtTrigRate = new JFormattedTextField();
		txtTrigThres = new JFormattedTextField();
		labelTrigRate = new JLabel();
		labelTrigThres = new JLabel();
		
		//prescaler variables
		panelPresConfig = new JPanel();
		labelPresConfig = new JLabel();
		labelT2 = new JLabel();
		labelA1 = new JLabel();
		labelC1 = new JLabel();
		labelE1 = new JLabel();
		labelG1 = new JLabel();
		labelB1 = new JLabel();
		labelD1 = new JLabel();
		labelF1 = new JLabel();
		labelH1 = new JLabel();
		labelTrigPS = new JLabel();
		txtTrigPres = new JFormattedTextField();
		labelEdge = new JLabel();
		labelEdge2 = new JLabel();
		labelABpres = new JLabel();
		labelCDpres = new JLabel();
		labelEFpres = new JLabel();
		labelGHpres = new JLabel();
		labelABphase = new JLabel();
		labelCDphase = new JLabel();
		labelEFphase = new JLabel();
		labelGHphase = new JLabel();
		txtABpres = new JFormattedTextField();
		txtCDpres = new JFormattedTextField();
		txtEFpres = new JFormattedTextField();
		txtGHpres = new JFormattedTextField();
		txtABphase = new JFormattedTextField();
		txtCDphase = new JFormattedTextField();
		txtEFphase = new JFormattedTextField();
		txtGHphase = new JFormattedTextField();
		
		separator = new JSeparator();
		//end variable initialization
		
		this.setPreferredSize(new Dimension(1200, 500));

		System.out.println("dg645" + gui.settings.dg645.toString());
		if(gui.settings.dg645.mConn == null)
			System.out.println("mconn is null in trigger");
		gui.settings.dg645.mConn.writeLine("TRAT?");

		temp = gui.settings.dg645.mConn.readLine().substring(1);
		temp = ("000000000000000" + temp).substring(temp.length());
		try {
			txtTrigRate.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###,###.######")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtTrigRate.setText(temp);
		txtTrigRate.setToolTipText("Internal Triggering; Range 0.000100 to 10,000,000.000000");
		txtTrigRate.setAutoscrolls(false);
		txtTrigRate.setFont(deFont); 
		txtTrigRate.setPreferredSize(new Dimension(150, textHeight));
		txtTrigRate.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("TLVL?");
		temp = gui.settings.dg645.mConn.readLine(); //.substring(1);
		//temp = ("0.00" + temp).substring(temp.length());
		try {
			MaskFormatter formatter = new MaskFormatter("*#.##");
			formatter.setValidCharacters("1234567890.+-");
			txtTrigThres.setFormatterFactory(new DefaultFormatterFactory(formatter));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		
		txtTrigThres.setText(temp);
		txtTrigThres.setToolTipText("External Triggering; Range -3.50 to +3.50");
		txtTrigThres.setFont(deFont); 
		txtTrigThres.setPreferredSize(new Dimension(60, textHeight));
		txtTrigThres.addPropertyChangeListener(action);

		triggerAdv.add(buttonTrigOn);
		buttonTrigOn.setFont(deFont); 
		buttonTrigOn.setText("On");
		buttonTrigOn.setPreferredSize(new Dimension(50, textHeight));
		buttonTrigOn.addActionListener(action);

		triggerAdv.add(buttonTrigOff);
		buttonTrigOff.setFont(deFont); 
		buttonTrigOff.setText("Off");
		buttonTrigOff.setPreferredSize(new Dimension(50, textHeight));
		buttonTrigOff.addActionListener(action);
		gui.settings.dg645.mConn.writeLine("ADVT?");
		switch(Integer.parseInt(gui.settings.dg645.mConn.readLine())) {
		case 0: triggerAdv.setSelected(buttonTrigOff.getModel(), true);
		break;
		case 1: triggerAdv.setSelected(buttonTrigOn.getModel(), true);
		break;
		default: System.out.println("Invalid ADVT");
		}

		gui.settings.dg645.mConn.writeLine("HOLD?");
		temp = gui.settings.dg645.mConn.readLine().substring(1);
		temp = ("0.000000000000" + temp).substring(temp.length());
		try {
			txtHold.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#.############")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtHold.setText(temp);
		txtHold.setFont(deFont); 
		txtHold.setPreferredSize(new Dimension(160, textHeight));
		txtHold.addPropertyChangeListener(action);
		
		labelTrigRate.setFont(new Font("Tahoma", 1, 14)); 
		labelTrigRate.setText("Trig Rate");
		labelTrigRate.setPreferredSize(new Dimension(90, textHeight));

		labelTrigThres.setFont(new Font("Tahoma", 1, 14)); 
		labelTrigThres.setText("Trig Thres");
		labelTrigThres.setPreferredSize(new Dimension(90, textHeight));

		labelAdvTrig.setFont(new Font("Tahoma", 1, 14)); 
		labelAdvTrig.setText("Adv. Trig");
		labelAdvTrig.setPreferredSize(new Dimension(90, textHeight));

		labelHold.setFont(deFont); 
		labelHold.setText("Hold");
		labelHold.setPreferredSize(new Dimension(70, textHeight));

		labelPresConfig.setFont(new Font("Tahoma", 1, 14)); 
		labelPresConfig.setText("Prescaler Configuration");
		labelPresConfig.setPreferredSize(new Dimension(200, textHeight));

		labelT2.setFont(deFont); 
		labelT2.setText("T0");
		labelT2.setPreferredSize(new Dimension(30, textHeight));

		labelA1.setFont(deFont); 
		labelA1.setText("A");
		labelA1.setPreferredSize(new Dimension(20, textHeight));
		labelA1.setRequestFocusEnabled(false);

		labelC1.setFont(deFont); 
		labelC1.setText("C");
		labelC1.setPreferredSize(new Dimension(20, textHeight));

		labelE1.setFont(deFont); 
		labelE1.setText("E");
		labelE1.setPreferredSize(new Dimension(20, textHeight));

		labelG1.setFont(deFont); 
		labelG1.setText("G");
		labelG1.setPreferredSize(new Dimension(20, textHeight));

		labelB1.setFont(deFont); 
		labelB1.setText("B");
		labelB1.setPreferredSize(new Dimension(20, textHeight));

		labelD1.setFont(deFont); 
		labelD1.setText("D");
		labelD1.setPreferredSize(new Dimension(20, textHeight));

		labelF1.setFont(deFont); 
		labelF1.setText("F");
		labelF1.setPreferredSize(new Dimension(20, textHeight));

		labelH1.setFont(deFont); 
		labelH1.setText("H");
		labelH1.setPreferredSize(new Dimension(20, textHeight));

		labelTrigPS.setFont(deFont); 
		labelTrigPS.setText("TRG Prescale");
		labelTrigPS.setPreferredSize(new Dimension(100, textHeight));

		gui.settings.dg645.mConn.writeLine("PRES? 0");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("0000000000" + temp).substring(temp.length());
		try {
			txtTrigPres.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#,###,###,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtTrigPres.setText(temp);
		txtTrigPres.setToolTipText("max value 1073741823");
		txtTrigPres.setFont(deFont); 
		txtTrigPres.setPreferredSize(new Dimension(150, textHeight));
		txtTrigPres.addPropertyChangeListener(action);

		labelEdge.setFont(deFont); 
		labelEdge.setText("Edge");
		labelEdge.setPreferredSize(new Dimension(50, textHeight));

		labelEdge2.setFont(deFont); 
		labelEdge2.setText("Edge");
		labelEdge2.setPreferredSize(new Dimension(50, textHeight));

		labelABpres.setFont(deFont); 
		labelABpres.setText("AB Prescale");
		labelABpres.setPreferredSize(new Dimension(100, textHeight));

		labelCDpres.setFont(deFont); 
		labelCDpres.setText("CD Prescale");
		labelCDpres.setPreferredSize(new Dimension(100, textHeight));

		labelEFpres.setFont(deFont); 
		labelEFpres.setText("EF Prescale");
		labelEFpres.setPreferredSize(new Dimension(100, textHeight));

		labelGHpres.setFont(deFont); 
		labelGHpres.setText("GH Prescale");
		labelGHpres.setPreferredSize(new Dimension(100, textHeight));

		labelABphase.setFont(deFont); 
		labelABphase.setText("AB Phase");
		labelABphase.setPreferredSize(new Dimension(85, textHeight));

		labelCDphase.setFont(deFont); 
		labelCDphase.setText("CD Phase");
		labelCDphase.setPreferredSize(new Dimension(85, textHeight));

		labelEFphase.setFont(deFont); 
		labelEFphase.setText("EF Phase");
		labelEFphase.setPreferredSize(new Dimension(85, textHeight));

		labelGHphase.setFont(deFont); 
		labelGHphase.setText("GH Phase");
		labelGHphase.setPreferredSize(new Dimension(85, textHeight));

		gui.settings.dg645.mConn.writeLine("PRES? 1");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtABpres.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtABpres.setText(temp);
		txtABpres.setToolTipText("max value 65535");
		txtABpres.setFont(deFont); 
		txtABpres.setPreferredSize(new Dimension(100, textHeight));
		txtABpres.addPropertyChangeListener(action);
		

		gui.settings.dg645.mConn.writeLine("PRES? 2");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtCDpres.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtCDpres.setText(temp);
		txtCDpres.setToolTipText("max value 65535");
		txtCDpres.setFont(deFont); 
		txtCDpres.setPreferredSize(new Dimension(100, textHeight));
		txtCDpres.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("PRES? 3");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtEFpres.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtEFpres.setText(temp);
		txtEFpres.setToolTipText("max value 65535");
		txtEFpres.setFont(deFont); 
		txtEFpres.setPreferredSize(new Dimension(100, textHeight));
		txtEFpres.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("PRES? 4");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtGHpres.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtGHpres.setText(temp);
		txtGHpres.setToolTipText("max value 65535");
		txtGHpres.setFont(deFont); 
		txtGHpres.setPreferredSize(new Dimension(100, textHeight));
		txtGHpres.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("PHAS? 1");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtABphase.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtABphase.setText(temp);
		txtABphase.setToolTipText("max value 65534");
		txtABphase.setFont(deFont); 
		txtABphase.setPreferredSize(new Dimension(100, textHeight));
		txtABphase.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("PHAS? 2");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtCDphase.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtCDphase.setText(temp);
		txtCDphase.setToolTipText("max value 65534");
		txtCDphase.setFont(deFont); 
		txtCDphase.setPreferredSize(new Dimension(100, textHeight));
		txtCDphase.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("PHAS? 3");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtEFphase.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtEFphase.setText(temp);
		txtEFphase.setToolTipText("max value 65534");
		txtEFphase.setFont(deFont); 
		txtEFphase.setPreferredSize(new Dimension(100, textHeight));
		txtEFphase.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("PHAS? 4");
		temp = gui.settings.dg645.mConn.readLine();
		temp = ("00000" + temp).substring(temp.length());
		try {
			txtGHphase.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##,###")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtGHphase.setText(temp);
		txtGHphase.setToolTipText("max value 65534");
		txtGHphase.setFont(deFont); 
		txtGHphase.setPreferredSize(new Dimension(100, textHeight));
		txtGHphase.addPropertyChangeListener(action);

		GroupLayout panelPresConfigLayout = new GroupLayout(panelPresConfig);
		panelPresConfig.setLayout(panelPresConfigLayout);
		panelPresConfigLayout.setHorizontalGroup(
				panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelPresConfigLayout.createSequentialGroup()
						.addComponent(labelPresConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(panelPresConfigLayout.createSequentialGroup()
								.addContainerGap()
								.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(panelPresConfigLayout.createSequentialGroup()
												.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(labelEdge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(panelPresConfigLayout.createSequentialGroup()
																.addGap(10, 10, 10)
																.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																		.addGroup(panelPresConfigLayout.createSequentialGroup()
																				.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																						.addComponent(labelA1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(labelC1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(labelE1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(labelG1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																						.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																								.addComponent(labelCDpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelABpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelEFpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelGHpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																										.addComponent(txtEFpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																										.addComponent(txtGHpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																										.addGroup(GroupLayout.Alignment.TRAILING, panelPresConfigLayout.createSequentialGroup()
																												.addGap(132, 132, 132)
																												.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																														.addComponent(txtABpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																														.addComponent(txtCDpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
																														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
																														.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																																.addComponent(labelEdge2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																.addGroup(panelPresConfigLayout.createSequentialGroup()
																																		.addGap(10, 10, 10)
																																		.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																																				.addGroup(panelPresConfigLayout.createSequentialGroup()
																																						.addComponent(labelD1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																						.addComponent(labelCDphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																																						.addGroup(panelPresConfigLayout.createSequentialGroup()
																																								.addComponent(labelF1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																								.addComponent(labelEFphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																																								.addGroup(panelPresConfigLayout.createSequentialGroup()
																																										.addComponent(labelH1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																										.addComponent(labelGHphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																																										.addGroup(panelPresConfigLayout.createSequentialGroup()
																																												.addComponent(labelB1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																												.addComponent(labelABphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
																																												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																												.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																																														.addComponent(txtCDphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																														.addComponent(txtABphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																														.addComponent(txtEFphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																														.addComponent(txtGHphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																																														.addContainerGap(25, Short.MAX_VALUE))
																																														.addGroup(panelPresConfigLayout.createSequentialGroup()
																																																.addComponent(labelT2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																																.addComponent(labelTrigPS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																																																.addComponent(txtTrigPres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																																																.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
		);
		panelPresConfigLayout.setVerticalGroup(
				panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelPresConfigLayout.createSequentialGroup()
						.addComponent(labelPresConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelT2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTrigPS, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtTrigPres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(labelEdge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelEdge2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(labelA1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelABpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtABpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelB1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelABphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtABphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(panelPresConfigLayout.createSequentialGroup()
																.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																		.addComponent(labelC1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(labelCDpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(txtCDpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																				.addComponent(labelE1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(labelEFpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(txtEFpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																				.addGroup(panelPresConfigLayout.createSequentialGroup()
																						.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																								.addComponent(labelD1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(labelCDphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																								.addComponent(txtCDphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																										.addComponent(labelF1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																										.addComponent(labelEFphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																										.addComponent(txtEFphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
																										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																										.addGroup(panelPresConfigLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																												.addComponent(labelG1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																												.addComponent(labelGHpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																												.addComponent(txtGHpres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																												.addComponent(labelH1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																												.addComponent(labelGHphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																												.addComponent(txtGHphase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

	
		//labels for mode buttons
		labelMode.setFont(new Font("Tahoma", 1, 14)); 
		labelMode.setText("Select Mode");
		labelMode.setPreferredSize(new Dimension(100, textHeight));

		labelInt.setFont(deFont); 
		labelInt.setText("Internal triggering at rates from 100 uHz to MHz");
		labelInt.setPreferredSize(new Dimension(300, textHeight));

		labelExt.setFont(deFont); 
		labelExt.setText("External triggering on rising edges");
		labelExt.setPreferredSize(new Dimension(300, textHeight));

		labelExt2.setFont(deFont); 
		labelExt2.setText("External triggering on falling edges");
		labelExt2.setPreferredSize(new Dimension(300, textHeight));

		labelSngl.setFont(deFont); 
		labelSngl.setText("Single shot triggering");
		labelSngl.setPreferredSize(new Dimension(300, textHeight));
		
		labelSnglExt.setFont(deFont); 
		labelSnglExt.setText("Externally triggered single shot on a rising edge");
		labelSnglExt.setPreferredSize(new Dimension(300, textHeight));
		
		labelSnglExt2.setFont(deFont); 
		labelSnglExt2.setText("Externally triggered single shot on a falling edge");
		labelSnglExt2.setPreferredSize(new Dimension(300, textHeight));
		
		labelLine.setFont(deFont); 
		labelLine.setText("Trigger at the power line frequency");
		labelLine.setPreferredSize(new Dimension(300, textHeight));

		//setting mode buttons and adding to button group
		triggerModes.add(buttonInt);
		buttonInt.setFont(deFont); 
		buttonInt.setText("INT");
		buttonInt.setPreferredSize(new Dimension(120, textHeight));
		buttonInt.addActionListener(action);
		
		triggerModes.add(buttonExtR);
		buttonExtR.setFont(deFont); 
		buttonExtR.setText("EXT ↑");
		buttonExtR.setPreferredSize(new Dimension(120, textHeight));
		buttonExtR.addActionListener(action);
		
		triggerModes.add(buttonExtF);
		buttonExtF.setFont(deFont); 
		buttonExtF.setText("EXT ↓");
		buttonExtF.setPreferredSize(new Dimension(120, textHeight));
		buttonExtF.addActionListener(action);
		
		triggerModes.add(buttonSngl);
		buttonSngl.setFont(deFont); 
		buttonSngl.setText("SNGL");
		buttonSngl.setPreferredSize(new Dimension(120, textHeight));
		buttonSngl.addActionListener(action);

		triggerModes.add(buttonSnglExtR);
		buttonSnglExtR.setFont(deFont); 
		buttonSnglExtR.setText("SNGL EXT ↑");
		buttonSnglExtR.setPreferredSize(new Dimension(120, textHeight));
		buttonSnglExtR.addActionListener(action);
		
		triggerModes.add(buttonSnglExtF);
		buttonSnglExtF.setFont(deFont); 
		buttonSnglExtF.setText("SNGL EXT ↓");
		buttonSnglExtF.setPreferredSize(new Dimension(120, textHeight));
		buttonSnglExtF.addActionListener(action);
		
		triggerModes.add(buttonLine);
		buttonLine.setFont(deFont); 
		buttonLine.setText("LINE");
		buttonLine.setPreferredSize(new Dimension(120, textHeight));
		buttonLine.addActionListener(action);

		//initial selected mode
		gui.settings.dg645.mConn.writeLine("TSRC?");
		
		switch(Integer.parseInt(gui.settings.dg645.mConn.readLine())) {
		case 0: triggerModes.setSelected(buttonInt.getModel(), true); break;
		case 1: triggerModes.setSelected(buttonExtR.getModel(), true); break;
		case 2: triggerModes.setSelected(buttonExtF.getModel(), true); break;
		case 3: triggerModes.setSelected(buttonSnglExtR.getModel(), true); break;
		case 4: triggerModes.setSelected(buttonSnglExtF.getModel(), true); break;
		case 5: triggerModes.setSelected(buttonSngl.getModel(), true); break;
		case 6: triggerModes.setSelected(buttonLine.getModel(), true); break;
		default: System.out.println("Invalid ISRC");
		}
		
		GroupLayout panelModesLayout = new GroupLayout(panelModes);
		panelModes.setLayout(panelModesLayout);
		panelModesLayout.setHorizontalGroup(
				panelModesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelModesLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(labelMode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(panelModesLayout.createSequentialGroup()
										.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(buttonInt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonExtR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonExtF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonSnglExtR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonSnglExtF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonSngl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
														.addComponent(labelSngl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelSnglExt2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelSnglExt, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelExt2, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelExt, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelInt, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(labelLine, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
														.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelModesLayout.setVerticalGroup(
				panelModesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelModesLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(labelMode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(buttonInt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelInt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(buttonExtR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelExt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(buttonExtF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelExt2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(buttonSnglExtR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(labelSnglExt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(buttonSnglExtF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(labelSnglExt2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																		.addComponent(buttonSngl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addComponent(labelSngl, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelModesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																				.addComponent(buttonLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(labelLine, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		separator.setOrientation(SwingConstants.VERTICAL);

		GroupLayout panelTriggerLayout = new GroupLayout(this);
		this.setLayout(panelTriggerLayout);
		panelTriggerLayout.setHorizontalGroup(
				panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelTriggerLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(panelModes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(panelTriggerLayout.createSequentialGroup()
										.addGap(10, 10, 10)
										.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(labelTrigThres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelTrigRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(txtTrigRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(txtTrigThres, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
														.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(separator, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																.addGroup(panelTriggerLayout.createSequentialGroup()
																		.addComponent(labelAdvTrig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
																				.addComponent(buttonTrigOff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(buttonTrigOn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addGroup(panelTriggerLayout.createSequentialGroup()
																						.addComponent(labelHold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(txtHold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
																						.addComponent(panelPresConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																						.addContainerGap(98, Short.MAX_VALUE))
		);
		panelTriggerLayout.setVerticalGroup(
				panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelTriggerLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(separator)
								.addGroup(panelTriggerLayout.createSequentialGroup()
										.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(panelTriggerLayout.createSequentialGroup()
														.addComponent(buttonTrigOn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(buttonTrigOff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(labelHold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addComponent(txtHold, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(panelPresConfig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addComponent(labelAdvTrig, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addGroup(panelTriggerLayout.createSequentialGroup()
																		.addComponent(panelModes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																				.addComponent(labelTrigRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																				.addComponent(txtTrigRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																				.addGroup(panelTriggerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																						.addComponent(labelTrigThres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																						.addComponent(txtTrigThres, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
																						.addGap(0, 0, Short.MAX_VALUE)))
																						.addContainerGap())
		);
	}
	
	public JRadioButton buttonExtF;
	public JRadioButton buttonExtR;
	public JRadioButton buttonInt;
	public JRadioButton buttonLine;
	public JRadioButton buttonSngl;
	public JRadioButton buttonSnglExtF;
	public JRadioButton buttonSnglExtR;
	public JRadioButton buttonTrigOff;
	public JRadioButton buttonTrigOn;
	
	public JFormattedTextField txtABphase;
	public JFormattedTextField txtABpres;
	public JFormattedTextField txtCDphase;
	public JFormattedTextField txtCDpres;
	public JFormattedTextField txtEFphase;
	public JFormattedTextField txtEFpres;
	public JFormattedTextField txtGHphase;
	public JFormattedTextField txtGHpres;
	public JFormattedTextField txtHold;
	public JFormattedTextField txtTrigPres;
	public JFormattedTextField txtTrigRate;
	public JFormattedTextField txtTrigThres;
	
	private JLabel labelA1;
	private JLabel labelABphase;
	private JLabel labelABpres;
	private JLabel labelAdvTrig;
	private JLabel labelB1;
	private JLabel labelC1;
	private JLabel labelCDphase;
	private JLabel labelCDpres;
	private JLabel labelD1;
	private JLabel labelE1;
	private JLabel labelEFphase;
	private JLabel labelEFpres;
	private JLabel labelEdge;
	private JLabel labelEdge2;
	private JLabel labelExt;
	private JLabel labelExt2;
	private JLabel labelF1;
	private JLabel labelG1;
	private JLabel labelGHphase;
	private JLabel labelGHpres;
	private JLabel labelH1;
	private JLabel labelHold;
	private JLabel labelInt;
	private JLabel labelLine;
	private JLabel labelMode;
	private JLabel labelPresConfig;
	private JLabel labelSngl;
	private JLabel labelSnglExt;
	private JLabel labelSnglExt2;
	private JLabel labelT2;
	private JLabel labelTrigPS;
	private JLabel labelTrigRate;
	private JLabel labelTrigThres;
	private ButtonGroup triggerAdv;
	private ButtonGroup triggerModes;
	private JPanel panelModes;
	private JPanel panelPresConfig;
	private JSeparator separator;
}