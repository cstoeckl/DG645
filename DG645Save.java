import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DG645Save extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;


	private String fname = "DG645Save";
	
	private Properties saveProperties=new Properties();
	private String file = fname + ".properties";
//	private final String s = File.separator;
	private JTextField saveField = new JTextField(4); 
	private JTextField loadField = new JTextField(4);
	

	private DG645Gui gui;
	
	/** Creates new DG645Save */
	public DG645Save(DG645Gui parent) {
		super(parent);
		gui = parent;
		this.setTitle("DG645 Save Menu");

		// create panel with border and layout for path information
		JPanel pathPane = new JPanel();
		pathPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		pathPane.setLayout(new GridLayout(0, 2));

		//text field for save
		//saveField = new JTextField(4);
		saveField.setHorizontalAlignment(JTextField.RIGHT);
		//	saveField.setText(DG645Control.dg645.save);
		pathPane.add(new JLabel("Save #:  "));
		pathPane.add(saveField);
		pathPane.add(new JLabel(""));
		pathPane.add(new JLabel(""));

		//text field for load
		//loadField = new JTextField(4);
		loadField.setHorizontalAlignment(JTextField.RIGHT);
		//loadField.setText("" + DG645Control.dg645.load);
		pathPane.add(new JLabel("Load #:  "));
		pathPane.add(loadField);
		pathPane.add(new JLabel(""));
		pathPane.add(new JLabel(""));

		// create panel with border and layout for buttons
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BorderLayout());
		
		JButton save, load, sfile, close;
		save = new JButton("Save");
		load = new JButton("Load");
		sfile = new JButton("Save to File");
		close = new JButton("Close");
		save.addActionListener(this);
		load.addActionListener(this);
		sfile.addActionListener(this);
		close.addActionListener(this);
		buttonPane.add("West", save);
		buttonPane.add("Center", load);
		buttonPane.add("East", close);
		buttonPane.add("South", sfile);
		
		// create panel with border and layout to put everything together
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());    
		pane.add("Center",pathPane);
		pane.add("South",buttonPane);

		this.getContentPane().add(pane);
		this.pack();

		// managing properties
		try {
			FileInputStream in =
				new FileInputStream(file);
			saveProperties.load(in);
			in.close();
			saveProperties.list(System.out);
		} catch (Exception e0) {};

		// set values in dialog
	}

    public void getFile(){
    	try{
    		
    		FileInputStream in = new FileInputStream(file);
    		saveProperties.load(in);
    		
    		if(!saveProperties.isEmpty())
    		{
    			saveField.setText(saveProperties.getProperty("Save"));
        		loadField.setText(saveProperties.getProperty("Load"));
    		}

    		in.close();
    	} catch (IOException e) {};
    }
    
	public void actionPerformed(java.awt.event.ActionEvent e) {
		String action = e.getActionCommand();
		String text;

		/*	if (action.startsWith("Apply") || action.startsWith("Save")) {
			saveProperties.put("Host Name",saveField.getText());
			saveProperties.put("Port",loadField.getText());
			saveProperties.list(System.out);
			try {
				FileOutputStream out = new FileOutputStream(file);
				saveProperties.store(out,"DG645 Save Properties");
				out.close();
			} catch (IOException e1) {};
		}
		 */

		if(action.startsWith("Save to File"))
		{
			text = saveField.getText();
			
			if(validNum(text))
			{
				file = fname + text + ".properties";
				
				try{
					FileOutputStream out = new FileOutputStream(file);
					saveProperties.store(out, "DG645 Save" + text + " Properties");
					out.close();
				} catch (IOException e2) {};
			}
			else
				System.out.println("Invalid save number. Must be from 1-9.");
		}
		
		if(action.startsWith("Save"))
		{
			text = saveField.getText();

			if(validNum(text))
			{	
				gui.settings.dg645.mConn.writeLine("*SAV " + text);
				System.out.println("Saved to location " + text);
			}
			else
				System.out.println("Invalid save number. Must be from 1-9.");
		}

		if(action.startsWith("Load"))
		{
			text = loadField.getText();

			if(validNum(text))
			{
				gui.settings.dg645.mConn.writeLine("*RCL " + text);
			//	DG645Control.restartGui();
				
			//	SwingUtilities.updateComponentTreeUI(DG645Control.dg645Gui);
				//DG645Control.dg645Gui.repaint();

				System.out.println("Loaded from location " + text + ". Please restart.");
				System.exit(0);
			}
			else
				System.out.println("Invalid load number. Must be from 1-9.");
		}

		if (action.startsWith("Close")) {
			this.setVisible(false);
		}
	}

	private boolean validNum(String text)
	{
		int num = Integer.valueOf(text).intValue();

		if(num > 0 && num < 10)
			return true;
		return false;
	}
}
