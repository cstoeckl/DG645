/*
 * CCDSettings.java
 *
 * Created on January 23, 2001, 8:27 AM
 */
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
/**
 *
 * @author  csto
 * @version
 */
public class DG645Settings extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	static Properties generatorProperties=new Properties();
    static String file = "Generator.properties";
    static final String s = File.separator;
    public JTextField hostField, portField;
    
    /** Creates new DG645Settings */
    public DG645Settings(JFrame parent) {
        super(parent);
        this.setTitle("DG645 Control Settings");
        
        // create panel with border and layout for path information
        JPanel pathPane = new JPanel();
        pathPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pathPane.setLayout(new GridLayout(0, 2));
        
        
        //text field for host name
        hostField = new JTextField(4);
        hostField.setHorizontalAlignment(JTextField.RIGHT);
        hostField.setText(DG645Control.dg645.host);
        pathPane.add(new JLabel("Host Name:  "));
        pathPane.add(hostField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
      //text field for port
        portField = new JTextField(4);
        portField.setHorizontalAlignment(JTextField.RIGHT);
        portField.setText("" + DG645Control.dg645.port);
        pathPane.add(new JLabel("Port:  "));
        pathPane.add(portField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
        // create panel with border and layout for buttons
        JPanel buttonPane = new JPanel();
        
        JButton ok,apply, cancel;
        ok = new JButton("Ok");
        apply = new JButton("Apply");
        cancel = new JButton("Cancel");
        ok.addActionListener(this);
        apply.addActionListener(this);
        cancel.addActionListener(this);
        buttonPane.add(ok);
        buttonPane.add(apply);
        buttonPane.add(cancel);
        
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
            generatorProperties.load(in);
            in.close();
            generatorProperties.list(System.out);
        } catch (Exception e0) {};
        
        // set values in dialog
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.startsWith("Apply") || action.startsWith("Ok")) {
        	generatorProperties.put("Host Name",hostField.getText());
        	generatorProperties.put("Port",portField.getText());
        	generatorProperties.list(System.out);
            try {
                FileOutputStream out = new FileOutputStream(file);
                generatorProperties.store(out,"DG645 Properties");
                out.close();
            } catch (IOException e1) {};
        }
        
        if (!action.startsWith("Apply")) {
            this.setVisible(false);
        }
    }
}