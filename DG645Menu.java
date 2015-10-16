import java.awt.*;
import javax.swing.*;


public class DG645Menu extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected DG645Gui gui;
	protected DG645Action action;

	//for uniform style
	protected Font deFont;
	protected int textHeight;

	protected String temp;
	protected int intemp;

	public DG645Menu(DG645Gui parent) {
		this.gui = parent;
		this.action = gui.action;
		this.deFont = gui.deFont;
		this.textHeight = gui.textHeight;
	}
}
