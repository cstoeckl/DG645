import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DG645Settings extends JDialog implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	static Properties generatorProperties = new Properties();
    static String file = "DG645.properties";
    static final String s = File.separator;
    private JTextField hostField = new JTextField(4);
    private JTextField portField = new JTextField(4);
    
    public int port = 5025;
    public String ip = "172.20.34.210";		//inside room
	//public String ip = "172.21.37.92";		//inside lab
	
    public DG645Gui gui;
    public DG645 dg645;
    
    public boolean initRun = true;
    
    /** Creates new DG645Settings */
 /*   public DG645Settings(JFrame parent) {
        super(parent);
        
        initComponents();
    }*/
    
    public DG645Settings() {
       // getFile();
    	initComponents();
    }
    
    public void initComponents()
    {
    	
        this.setTitle("DG645 Control Settings");
        
        // create panel with border and layout for path information
        JPanel pathPane = new JPanel();
        pathPane.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        pathPane.setLayout(new GridLayout(0, 2));
        
        //text field for host name
       // hostField = new JTextField(4);
        hostField.setHorizontalAlignment(JTextField.RIGHT);
      //  hostField.setText(DG645Control.dg645.host);
        hostField.setText(ip);
        pathPane.add(new JLabel("Host Name:  "));
        pathPane.add(hostField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
      //text field for port
      //  portField = new JTextField(4);
        portField.setHorizontalAlignment(JTextField.RIGHT);
       // portField.setText("" + DG645Control.dg645.port);
        portField.setText("" + port);
        pathPane.add(new JLabel("Port:  "));
        pathPane.add(portField);
        pathPane.add(new JLabel(""));
        pathPane.add(new JLabel(""));
        
        // create panel with border and layout for buttons
        JPanel buttonPane = new JPanel();
        
        JButton save,apply, close;
        save = new JButton("Save");
        apply = new JButton("Apply");
        close = new JButton("Close");
        save.addActionListener(this);
        apply.addActionListener(this);
        close.addActionListener(this);
        buttonPane.add(save);
        buttonPane.add(apply);
        buttonPane.add(close);
        
        // create panel with border and layout to put everything together
        JPanel pane = new JPanel();
        pane.setLayout(new BorderLayout());    
        pane.add("Center",pathPane);
        pane.add("South",buttonPane);
        
        this.getContentPane().add(pane);
        this.pack();
        
        // managing properties
       /* try {
            FileInputStream in =
            new FileInputStream(file);
            generatorProperties.load(in);
            in.close();
            generatorProperties.list(System.out);
        } catch (Exception e0) {};*/
        
        // set values in dialog
    }
    
    public void getFile(){
    	try{
    		
    		FileInputStream in = new FileInputStream(file);
    		generatorProperties.load(in);
    		
    		if(!generatorProperties.isEmpty())
    		{
    			ip = generatorProperties.getProperty("Host Name");
        		port = Integer.valueOf(generatorProperties.getProperty("Port")).intValue();
    		}
    		
    		in.close();
    	} catch (IOException e) {};
    }
    
    public void actionPerformed(java.awt.event.ActionEvent e) {
        String action = e.getActionCommand();
        
        if (action.startsWith("Apply") || action.startsWith("Save")) {
        	generatorProperties.put("Host Name",hostField.getText());
        	generatorProperties.put("Port",portField.getText());
        	generatorProperties.list(System.out);
        	      	
            try {
                FileOutputStream out = new FileOutputStream(file);
                generatorProperties.store(out,"DG645 Properties");
                out.close();
            } catch (IOException e1) {};
        }
        
        if(action.startsWith("Apply"))
        {
        	try {   		
        		ip = hostField.getText();
        		port = Integer.valueOf(portField.getText()).intValue();
        		
        		WindowListener l = new WindowAdapter() {
        			public void windowClosing(WindowEvent e) {
        				System.exit(0);
        			}
        		};
        		
        		dg645 = new DG645(this);
        		dg645.connect();
        		
        		gui = new DG645Gui(this);
        		gui.addWindowListener(l);
        		gui.initComponents();

        		initRun = false;
      
        		gui.showGui();

        		this.setVisible(false);
        	} catch (Exception e2){
        		System.out.println("Error at initializing components.");
        		e2.printStackTrace();
        		
        		//System.out.println(e2.getCause().getMessage());
        	}
        }
        
        if (action.startsWith("Close")) {
            this.setVisible(false);
        }
    }
}