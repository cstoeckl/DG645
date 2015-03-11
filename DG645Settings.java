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
	
	static Properties microscopeProperties=new Properties();
    static String file = "Microscope.properties";
    static final String s = File.separator;
    public JTextField propField,integField,derivField,gainField,veloField;
    
    /** Creates new CCDSettings */
    public DG645Settings(JFrame parent) {
        super(parent);
        this.setTitle("Scint Control Settings");
        
        // create panel with border and layout for path infomation
        JPanel pathPane = new JPanel();
        pathPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pathPane.setLayout(new GridLayout(0, 2));
        
         // text field for setup path
        propField = new JTextField(4);
        propField.setHorizontalAlignment(JTextField.RIGHT);
        
        pathPane.add(new JLabel("Proportional:  "));
        pathPane.add(propField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
        // text field to set remote save path;
        integField = new JTextField(4);
        integField.setHorizontalAlignment(JTextField.RIGHT);
        
        pathPane.add(new JLabel("Integral:  "));
        pathPane.add(integField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
        // text field to set local save path;
        derivField = new JTextField(4);
        derivField.setHorizontalAlignment(JTextField.RIGHT);
        
        pathPane.add(new JLabel("Differential:  "));
        pathPane.add(derivField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
        // text field to set local save path;
        veloField = new JTextField(4);
        veloField.setHorizontalAlignment(JTextField.RIGHT);
        
        pathPane.add(new JLabel("Velocity:  "));
        pathPane.add(veloField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
//      text field to set local save path;
        gainField = new JTextField(4);
        gainField.setHorizontalAlignment(JTextField.RIGHT);
        
        pathPane.add(new JLabel("Gain:  "));
        pathPane.add(gainField);
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
        
        // create panel with border and layout to put everthing together
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
            microscopeProperties.load(in);
            in.close();
            microscopeProperties.list(System.out);
        } catch (Exception e0) {};
        
        // set values in dialog
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.startsWith("Apply") || action.startsWith("Ok")) {
            microscopeProperties.put("Proportional",propField.getText());
            microscopeProperties.put("Integral",integField.getText());
            microscopeProperties.put("Derivative",derivField.getText());
            microscopeProperties.put("Velocity",veloField.getText());
            microscopeProperties.put("Gain",gainField.getText());
            microscopeProperties.list(System.out);
            try {
                FileOutputStream out = new FileOutputStream(file);
                microscopeProperties.store(out,"Scint Properties");
                out.close();
            } catch (IOException e1) {};
        }
        
        if (!action.startsWith("Apply")) {
            this.setVisible(false);
        }
    }
}