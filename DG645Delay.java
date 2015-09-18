import java.awt.*;
import java.text.*;

import javax.swing.*;
import javax.swing.text.*;

public class DG645Delay extends JPanel {
	DG645Gui parent;
	DG645Action action;

	//for uniform style
	Font deFont;
	int textHeight;

	private String temp;
	private int intemp;

	public DG645Delay(DG645Gui parent) {
		this.parent = parent;

		this.action = parent.action;
		this.deFont = parent.deFont;
		this.textHeight = parent.textHeight;

		delayPanel();
	}

	public void updateDelays(String channel)
	{
		intemp = Integer.parseInt(channel);
		
		switch(intemp){
		case 2: panelA.updateValue(); break;
		case 3: panelB.updateValue(); break;
		case 4: panelC.updateValue(); break;
		case 5: panelD.updateValue(); break;
		case 6: panelE.updateValue(); break;
		case 7: panelF.updateValue(); break;
		case 8: panelG.updateValue(); break;
		case 9: panelH.updateValue(); break;
		default: System.out.println("Invalid Channel");
		}

		updateT1();
		parent.checkError();
	}

	public void updateT1()
	{
		DG645Control.dg645.mConn.writeLine("DLAY? 1");
		temp = DG645Control.dg645.mConn.readLine().substring(2);
		labelT1val.setText(temp);
	}

	public void updateT1(String delay)
	{
		temp = delay.substring(2);
		labelT1val.setText(temp);
	}
	
	private void delayPanel()
	{
		//initializing variables
		labelChannel = new JLabel();
		labelDelay2 = new JLabel();
		panelT0 = new JPanel();
		labelT0 = new JLabel();
		labelChannel0 = new JLabel();
		labelT0val = new JLabel();
		panelT1 = new JPanel();
		labelChannel1 = new JLabel();
		labelT1 = new JLabel();
		labelT1val = new JLabel();
		labelEqual = new JLabel();
		labelEqual1 = new JLabel();
		
		panelA = new Channel(2);
		panelB = new Channel(3);
		panelC = new Channel(4);
		panelD = new Channel(5);
		panelE = new Channel(6);
		panelF = new Channel(7);
		panelG = new Channel(8);
		panelH = new Channel(9);
		//end variable initialization

		this.setPreferredSize(new Dimension(500, 500));

		labelChannel.setFont(new Font("Tahoma", 1, 14)); 
		labelChannel.setText("Channel");
		labelChannel.setPreferredSize(new Dimension(100, textHeight));

		labelDelay2.setFont(new Font("Tahoma", 1, 14)); 
		labelDelay2.setHorizontalAlignment(SwingConstants.CENTER);
		labelDelay2.setText("Delay");
		labelDelay2.setPreferredSize(new Dimension(100, textHeight));

		//organizing T0 panel
		panelT0.setPreferredSize(new Dimension(500, textHeight));
		panelT0.setRequestFocusEnabled(false);

		labelEqual.setFont(deFont); 
		labelEqual.setText("=");
		labelEqual.setPreferredSize(new Dimension(15, textHeight));

		labelT0.setFont(new Font("Tahoma", 1, 14)); 
		labelT0.setHorizontalAlignment(SwingConstants.CENTER);
		labelT0.setText("T0");
		labelT0.setPreferredSize(new Dimension(30, textHeight));

		labelChannel0.setFont(deFont); 
		labelChannel0.setHorizontalAlignment(SwingConstants.CENTER);
		labelChannel0.setText("0");
		labelChannel0.setPreferredSize(new Dimension(55, textHeight));

		DG645Control.dg645.mConn.writeLine("DLAY? 0");  
		temp = DG645Control.dg645.mConn.readLine().substring(2);
		labelT0val.setFont(deFont); 
		labelT0val.setHorizontalAlignment(SwingConstants.CENTER);
		labelT0val.setText(temp);
		labelT0val.setPreferredSize(new Dimension(200, textHeight));

		//setting layout of T0 panel;
		GroupLayout panelT0Layout = new GroupLayout(panelT0);
		panelT0.setLayout(panelT0Layout);
		panelT0Layout.setHorizontalGroup(
				panelT0Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, panelT0Layout.createSequentialGroup()
						.addContainerGap(105, Short.MAX_VALUE)
						.addComponent(labelT0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelChannel0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(labelT0val, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(64, 64, 64))
				);
		panelT0Layout.setVerticalGroup(
				panelT0Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelT0Layout.createSequentialGroup()
						.addGroup(panelT0Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelEqual, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelChannel0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT0val, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGap(0, 0, Short.MAX_VALUE))
				);

		//organizing T1 panel
		panelT1.setPreferredSize(new Dimension(500, textHeight));

		labelChannel1.setFont(deFont); 
		labelChannel1.setHorizontalAlignment(SwingConstants.CENTER);
		labelChannel1.setText("0");
		labelChannel1.setPreferredSize(new Dimension(55, textHeight));

		labelEqual1.setFont(deFont); 
		labelEqual1.setText("=");
		labelEqual1.setPreferredSize(new Dimension(15, textHeight));

		labelT1.setFont(new Font("Tahoma", 1, 14)); 
		labelT1.setHorizontalAlignment(SwingConstants.CENTER);
		labelT1.setText("T1");
		labelT1.setPreferredSize(new Dimension(30, textHeight));

		DG645Control.dg645.mConn.writeLine("DLAY? 1");  
		temp = DG645Control.dg645.mConn.readLine().substring(2);
		labelT1val.setFont(deFont); 
		labelT1val.setHorizontalAlignment(SwingConstants.CENTER);
		labelT1val.setText(temp);
		labelT1val.setPreferredSize(new Dimension(200, textHeight));

		//setting layout of T1 panel
		GroupLayout panelT1Layout = new GroupLayout(panelT1);
		panelT1.setLayout(panelT1Layout);
		panelT1Layout.setHorizontalGroup(
				panelT1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelT1Layout.createSequentialGroup()
						.addContainerGap(105, Short.MAX_VALUE)
						.addComponent(labelT1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelEqual1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(labelChannel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(labelT1val, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(64, 64, 64))
				);
		panelT1Layout.setVerticalGroup(
				panelT1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelT1Layout.createSequentialGroup()
						.addGroup(panelT1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelChannel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelEqual1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelT1val, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		
		//setting layout of delay panel
		GroupLayout panelDelayLayout = new GroupLayout(this);
		this.setLayout(panelDelayLayout);
		panelDelayLayout.setHorizontalGroup(
				panelDelayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelDelayLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelDelayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(panelDelayLayout.createSequentialGroup()
										.addGap(104, 104, 104)
										.addComponent(labelChannel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(61, 61, 61)
										.addComponent(labelDelay2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										
										.addComponent(panelT0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelT1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						
										.addComponent(panelA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(panelH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addContainerGap(633, Short.MAX_VALUE))
				);
		panelDelayLayout.setVerticalGroup(
				panelDelayLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(panelDelayLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(panelDelayLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(labelDelay2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelChannel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelT0, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelT1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(30, 30, 30)
								.addComponent(panelA, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelD, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelF, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelG, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(panelH, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

	}

	//Start variables declaration
	private JLabel labelChannel;
	private JLabel labelChannel0;
	private JLabel labelChannel1;
	private JLabel labelDelay2;
	private JLabel labelEqual;
	private JLabel labelEqual1;
	private JLabel labelT0;
	private JLabel labelT0val;
	private JLabel labelT1;
	private JLabel labelT1val;
	
	private JPanel panelT0;
	private JPanel panelT1;
	
	public Channel panelA;
	public Channel panelB;
	public Channel panelC;
	public Channel panelD;
	public Channel panelE;
	public Channel panelF;
	public Channel panelG;
	public Channel panelH;
	// End variables declaration    

	class Channel extends JPanel{


		private JLabel equal;
		private JLabel chan;
		public JComboBox cbox;
		public JFormattedTextField value;

		private int selected;
		private String[] allChan = {"T0", "T1", "A", "B", "C", "D", "E", "F", "G", "H" };

		private String response;
		
		public void updateValue()
		{
			//selected should always have a value at this point
			DG645Control.dg645.mConn.writeLine("DLAY? " + selected);
			response = DG645Control.dg645.mConn.readLine();
			
			temp = response.substring(3);
			temp = ("0.000000000000" + temp).substring(temp.length());
			
			//value.setText(response.substring(2, 3) + temp);
			
			value.setText(temp);
			
			System.out.println("HERE udpate Temp " + temp);
		}
		
		public Channel(int selected) {

			this.selected = selected;
			
			chan = new JLabel();
			cbox = new JComboBox();
			equal = new JLabel();
			value = new JFormattedTextField();

			this.setPreferredSize(new Dimension(430, textHeight));

			DG645Control.dg645.mConn.writeLine("DLAY? " + selected);
			response = DG645Control.dg645.mConn.readLine();

			initcbox();

			equal.setFont(deFont); 
			equal.setText("=");
			equal.setPreferredSize(new Dimension(15, textHeight));

			chan.setFont(new Font("Tahoma", 1, 14)); 
			chan.setHorizontalAlignment(SwingConstants.CENTER);
			chan.setText(allChan[selected]);
			chan.setPreferredSize(new Dimension(30, textHeight));

			temp = response.substring(3);  
			temp = ("0.000000000000" + temp).substring(temp.length());
			
			System.out.println("Here Temp " + temp);
			System.out.println("Here response " + response);

			try {
				value.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#.############")));
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
			//System.out.println("value "+ value.getText());

			//value.setText(response.substring(2, 3) + temp); IF WANT TO SHOW SIGN
			value.setText(temp);
			value.setFont(deFont); 
			value.setPreferredSize(new Dimension(180, textHeight));
			value.addPropertyChangeListener(action);
			//System.out.println("value 2 "+ value.getText());

			GroupLayout chanLayout = new GroupLayout(this);
			this.setLayout(chanLayout);
			chanLayout.setHorizontalGroup(
					chanLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(GroupLayout.Alignment.TRAILING, chanLayout.createSequentialGroup()
							.addGap(0, 107, Short.MAX_VALUE)
							.addComponent(chan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(equal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(cbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
							.addComponent(value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					);
			chanLayout.setVerticalGroup(
					chanLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
					.addGroup(chanLayout.createSequentialGroup()
							.addGroup(chanLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
									.addComponent(cbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(equal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(chan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(value, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					);
		}
		
		private void initcbox(){

			cbox.setFont(deFont); 

			//cannot select self as channel
			switch(selected){
			case 2: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "B", "C", "D", "E", "F", "G", "H" })); break;
			case 3: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "A", "C", "D", "E", "F", "G", "H" })); break;
			case 4: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "A", "B", "D", "E", "F", "G", "H" })); break;
			case 5: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "E", "F", "G", "H" })); break;
			case 6: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "F", "G", "H" })); break;
			case 7: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "G", "H" })); break;
			case 8: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "F", "H" })); break;
			case 9: cbox.setModel(new DefaultComboBoxModel(new String[] { "T0", "A", "B", "C", "D", "E", "F", "G" })); break;
			default: System.out.println("Invalid selected channel"); break;
			}

			temp = response.substring(0,1);
			intemp = Integer.parseInt(temp);
			if(intemp == 0)
			{
				//do nothing
			}
			else if(intemp < selected)
				intemp = intemp - 1;
			else
				intemp = intemp - 2;

			cbox.setSelectedIndex(intemp);
			cbox.setPreferredSize(new Dimension(55, textHeight));
			cbox.addActionListener(action);
		}
	}
}
