package tenbuddies;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class Window extends JFrame implements ActionListener{
	
	private JButton btnOne;
	private JButton btnTwo;
	private JButton btnThree;
	private JButton btnFour;
	private JButton btnFive;
	private JButton btnSix;
	private JButton btnSeven;
	private JButton btnEight;
	private JButton btnNine;
	private JButton btnTen;
	private JButton btnAnswer;
	private JMenuBar menuBar;
	private JMenu menu, subMenuSettings, subMenuAnimation, subMenuNumbers;
	private JCheckBoxMenuItem boxItemAnimationOnOff;
	private JRadioButtonMenuItem radioButtonMaxNumber, boxItemAllowNegativeNumbers, boxItemArithmeticChoise;
	

	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
	
	public Window() {
		super("Tiokompisar");
		createMenu();
		initButtons();
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		
	}
	/**
	 * @author Nollan
	 * Function initiate buttons and assigns values to them
	 */
	public void initButtons(){
		
	}
	
	/**
	 * @author Nollan
	 * Function creates and sets a menubar with settings
	 */
	public void createMenu(){
		menuBar = new JMenuBar();						//Declares a menubar
		menu = new JMenu("Menu");						//Declares rootmenu
		menuBar.add(menu);								//Adds rootmenu to menubar
		subMenuSettings = new JMenu("Settings");		//Declares a submenu
		menu.add(subMenuSettings);						//Adds a submenu called settings to rootmenu
		subMenuNumbers = new JMenu("Number interval");	//Declares a subMenu (Numbers interval)
		subMenuSettings.add(subMenuNumbers);			//Adds subMenuNumbers to subMenuSettings
		subMenuAnimation = new JMenu("Animation");		//Declares a subMenu (Animation)
		subMenuSettings.add(subMenuAnimation);			//Adds subMenuNumbers to subMenuSettings
		
		
	}
	
	
}
