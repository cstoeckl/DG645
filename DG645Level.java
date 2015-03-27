import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class DG645Level extends JPanel {

DG645Action action;
	
	//for uniform style
	Font deFont;
	int textHeight;
	
	private String temp;
	
	public DG645Level(DG645Gui parent) {
		this.action = parent.action;
		
		this.deFont = parent.deFont;
		this.textHeight = parent.textHeight;

		
		levelPanel();
		
	}
	private void levelPanel()
	{
		//initializing necessary variables
		
		levelPolarity = new ButtonGroup();
		
		cboxLevel = new JComboBox();
		labelOffset = new JLabel();
		labelAmp = new JLabel();
		labelPolarity = new JLabel();
		txtOffset = new JFormattedTextField();
		labelv = new JLabel();
		txtAmp = new JFormattedTextField();
		labelv2 = new JLabel();
		buttonPos = new JRadioButton();
		buttonNeg = new JRadioButton();
		//end variable initialization
		
		this.setPreferredSize(new java.awt.Dimension(500, 500));

		cboxLevel.setFont(new java.awt.Font("Tahoma", 1, 14)); 
		cboxLevel.setModel(new DefaultComboBoxModel(new String[] { "Output T0", "Output AB", "Output CD", "Output EF", "Output GH" }));
		cboxLevel.setPreferredSize(new java.awt.Dimension(150, textHeight));
		cboxLevel.addActionListener(action);

		labelOffset.setFont(deFont); 
		labelOffset.setHorizontalAlignment(SwingConstants.CENTER);
		labelOffset.setText("Offset");
		labelOffset.setPreferredSize(new java.awt.Dimension(100, textHeight));
		labelOffset.setRequestFocusEnabled(false);

		labelAmp.setFont(deFont); 
		labelAmp.setHorizontalAlignment(SwingConstants.CENTER);
		labelAmp.setText("Amplitude");
		labelAmp.setPreferredSize(new java.awt.Dimension(100, textHeight));

		labelPolarity.setFont(deFont); 
		labelPolarity.setHorizontalAlignment(SwingConstants.CENTER);
		labelPolarity.setText("Polarity");
		labelPolarity.setPreferredSize(new java.awt.Dimension(100, textHeight));

		DG645Control.dg645.mConn.writeLine("LOFF?" + cboxLevel.getSelectedIndex());
		temp = DG645Control.dg645.mConn.readLine();
		try {
			txtOffset.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("*#.##")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtOffset.setText(temp);
		txtOffset.setToolTipText("ranging over +- 2.0 V");
		txtOffset.setFont(deFont); 
		txtOffset.setPreferredSize(new java.awt.Dimension(60, textHeight));
		txtOffset.addPropertyChangeListener(action);
		
		labelv.setFont(deFont); 
		labelv.setText("V");
		labelv.setPreferredSize(new java.awt.Dimension(15, textHeight));

		DG645Control.dg645.mConn.writeLine("LAMP?" + cboxLevel.getSelectedIndex());
		temp = DG645Control.dg645.mConn.readLine().substring(1);
		temp = ("0.00" + temp).substring(temp.length());
		try {
			txtAmp.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#.##")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		txtAmp.setText(temp);
		txtAmp.setToolTipText("ranging over 0.50V to 4.00V");
		txtAmp.setFont(deFont); 
		txtAmp.setPreferredSize(new java.awt.Dimension(60, textHeight));
		txtAmp.addPropertyChangeListener(action);
		

		labelv2.setFont(deFont); 
		labelv2.setText("V");
		labelv2.setPreferredSize(new java.awt.Dimension(15, textHeight));

		levelPolarity.add(buttonPos);
		buttonPos.setFont(deFont); 
		buttonPos.setText("Positive");
		buttonPos.setPreferredSize(new java.awt.Dimension(100, textHeight));
		buttonPos.addActionListener(action);

		levelPolarity.add(buttonNeg);
		buttonNeg.setFont(deFont); 
		buttonNeg.setText("Negative");
		buttonNeg.setPreferredSize(new java.awt.Dimension(100, textHeight));
		buttonNeg.addActionListener(action);
		DG645Control.dg645.mConn.writeLine("LPOL?" + cboxLevel.getSelectedIndex());
		switch(Integer.parseInt(DG645Control.dg645.mConn.readLine())) {
		case 0: levelPolarity.setSelected(buttonNeg.getModel(), true);
		break;
		case 1: levelPolarity.setSelected(buttonPos.getModel(), true);
		break;
		default: System.out.println("Invalid LPOL");
		}

		GroupLayout panelLevelLayout = new GroupLayout(this);
		this.setLayout(panelLevelLayout);
		panelLevelLayout.setHorizontalGroup(
				panelLevelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelLevelLayout.createSequentialGroup()
						.addGap(159, 159, 159)
						.addGroup(panelLevelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(cboxLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(panelLevelLayout.createSequentialGroup()
										.addGap(22, 22, 22)
										.addGroup(panelLevelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(labelOffset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelAmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(labelPolarity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(panelLevelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(buttonNeg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addComponent(buttonPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGroup(panelLevelLayout.createSequentialGroup()
																.addComponent(txtAmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(labelv2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addGroup(panelLevelLayout.createSequentialGroup()
																		.addComponent(txtOffset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(labelv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
																		.addContainerGap(759, Short.MAX_VALUE))
		);
		panelLevelLayout.setVerticalGroup(
				panelLevelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelLevelLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(cboxLevel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addGroup(panelLevelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelOffset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtOffset, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelv, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(panelLevelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(labelAmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtAmp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(labelv2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(panelLevelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(labelPolarity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(buttonPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(buttonNeg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addContainerGap(249, Short.MAX_VALUE))
		);
		
	}
	
	public JComboBox cboxLevel;
	private JLabel labelAmp;
	private JLabel labelOffset;
	private JLabel labelPolarity;
	private JLabel labelv;
	private JLabel labelv2;
	public ButtonGroup levelPolarity;

	public JFormattedTextField txtAmp;

	public JFormattedTextField txtOffset;
	
	public JRadioButton buttonNeg;
	public JRadioButton buttonPos;
}
