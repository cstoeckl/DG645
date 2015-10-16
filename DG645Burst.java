import java.awt.*;
import java.text.*;

import javax.swing.*;
import javax.swing.text.*;

public class DG645Burst extends DG645Menu{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DG645Burst(DG645Gui parent) {
		super(parent);	

		if(gui.settings.dg645.mConn != null)
			burstPanel();
	/*	else
			System.out.println("mconn is null in burst");*/
	}
	
	private void burstPanel()
	{
		//initializing necessary variables
		
		burstOnOff = new ButtonGroup();
		burstOutput = new ButtonGroup();
		
		buttonOn = new JRadioButton();
		buttonOff = new JRadioButton();
		buttonOutputAll = new JRadioButton();
		buttonOutputFirst = new JRadioButton();
		txtCNT = new JFormattedTextField();
		txtPeriod = new JFormattedTextField();
		txtDelay = new JFormattedTextField();
		labelCNT = new JLabel();
		labelPeriod = new JLabel();
		labelDelay = new JLabel();
		//end variable initialization
		
		this.setPreferredSize(new Dimension(500, 500));

		burstOnOff.add(buttonOn);
		buttonOn.setFont(deFont); 
		buttonOn.setText("On");
		buttonOn.setPreferredSize(new Dimension(60, textHeight));
		buttonOn.addActionListener(action);

		burstOnOff.add(buttonOff);
		buttonOff.setFont(deFont); 
		buttonOff.setText("Off");
		buttonOff.setPreferredSize(new Dimension(60, textHeight));
		buttonOff.addActionListener(action);
		gui.settings.dg645.mConn.writeLine("BURM?");
		switch(Integer.parseInt(gui.settings.dg645.mConn.readLine())) {
		case 0: burstOnOff.setSelected(buttonOff.getModel(), true);
		break;
		case 1: burstOnOff.setSelected(buttonOn.getModel(), true);
		break;
		default: System.out.println("Invalid BURM");
		}

		burstOutput.add(buttonOutputAll);
		buttonOutputAll.setFont(deFont); 
		buttonOutputAll.setText("T0 fire on all delay cycles of burst");
		buttonOutputAll.setPreferredSize(new Dimension(300, textHeight));
		buttonOutputAll.addActionListener(action);

		burstOutput.add(buttonOutputFirst);
		buttonOutputFirst.setFont(deFont); 
		buttonOutputFirst.setText("T0 fire on first delay cycle of burst");
		buttonOutputFirst.setPreferredSize(new Dimension(300, textHeight));
		buttonOutputFirst.addActionListener(action);
		gui.settings.dg645.mConn.writeLine("BURT?");
		switch(Integer.parseInt(gui.settings.dg645.mConn.readLine())) {
		case 0: burstOutput.setSelected(buttonOutputAll.getModel(), true);
		break;
		case 1: burstOutput.setSelected(buttonOutputFirst.getModel(), true);
		break;
		default: System.out.println("Invalid BURT");
		}

		gui.settings.dg645.mConn.writeLine("BURC?");
		temp = gui.settings.dg645.mConn.readLine();
		try {
			txtCNT.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##########")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtCNT.setText(temp);
		txtCNT.setToolTipText("range 1 to 2^32 - 1");
		txtCNT.setFont(deFont); 
		txtCNT.setPreferredSize(new Dimension(50, textHeight));
		txtCNT.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("BURP?");
		//temp = gui.settings.dg645.mConn.readLine();
		temp = gui.settings.dg645.mConn.readLine().substring(1);
		temp = ("0.00000000" + temp).substring(temp.length());
		try {
			txtPeriod.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#.########")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtPeriod.setText(temp);
		txtPeriod.setToolTipText("10ns resolution");
		txtPeriod.setFont(deFont); 
		txtPeriod.setPreferredSize(new Dimension(50, textHeight));
		txtPeriod.addPropertyChangeListener(action);

		gui.settings.dg645.mConn.writeLine("BURD?");
		//temp = gui.settings.dg645.mConn.readLine();
		temp = gui.settings.dg645.mConn.readLine().substring(1);
		temp = ("0.000000000000" + temp).substring(temp.length());
		try {
			txtDelay.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#.############")));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		txtDelay.setText(temp);
		txtDelay.setToolTipText("5ps resolution");
		txtDelay.setFont(deFont); 
		txtDelay.setPreferredSize(new Dimension(100, textHeight));
		txtDelay.addPropertyChangeListener(action);

		labelCNT.setFont(deFont); 
		labelCNT.setText("CNT");
		labelCNT.setPreferredSize(new Dimension(50, textHeight));

		labelPeriod.setFont(deFont); 
		labelPeriod.setText("Period");
		labelPeriod.setPreferredSize(new Dimension(50, textHeight));

		labelDelay.setFont(deFont); 
		labelDelay.setText("Delay");
		labelDelay.setPreferredSize(new Dimension(50, textHeight));

		GroupLayout panelBurstLayout = new GroupLayout(this);
		this.setLayout(panelBurstLayout);
		panelBurstLayout.setHorizontalGroup(
				panelBurstLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelBurstLayout.createSequentialGroup()
						.addGap(64, 64, 64)
						.addGroup(panelBurstLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(buttonOutputAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonOff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonOn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonOutputFirst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(panelBurstLayout.createSequentialGroup()
										.addGap(6, 6, 6)
										.addGroup(panelBurstLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(labelCNT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(panelBurstLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addGroup(panelBurstLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
																.addComponent(txtCNT, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(txtPeriod, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE))
																.addComponent(txtDelay, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))))
																.addContainerGap(781, Short.MAX_VALUE))
		);
		panelBurstLayout.setVerticalGroup(
				panelBurstLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelBurstLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(buttonOn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(buttonOff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(buttonOutputAll, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(buttonOutputFirst, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(27, 27, 27)
						.addGroup(panelBurstLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(txtCNT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelCNT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(panelBurstLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(txtPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelPeriod, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(panelBurstLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(txtDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelDelay, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
	}
	
    
	public ButtonGroup burstOnOff;
	public ButtonGroup burstOutput;
	
	public JRadioButton buttonOff;
	public JRadioButton buttonOn;
	public JRadioButton buttonOutputAll;
	public JRadioButton buttonOutputFirst;
	
	public JFormattedTextField txtCNT;
	public JFormattedTextField txtDelay;
	public JFormattedTextField txtPeriod;
	
	private JLabel labelDelay;
	private JLabel labelPeriod;
	
	private JLabel labelCNT;
}