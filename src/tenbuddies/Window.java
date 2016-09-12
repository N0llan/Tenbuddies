package tenbuddies;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.util.Collections;

public class Window extends JFrame implements ActionListener{
	
	private JLabel[] buttons = new JLabel[10];
	private JLabel btnZero;
	private JLabel btnOne;
	private JLabel btnTwo;
	private JLabel btnThree;
	private JLabel btnFour;
	private JLabel btnFive;
	private JLabel btnSix;
	private JLabel btnSeven;
	private JLabel btnEight;
	private JLabel btnNine;
	private JMenuBar menuBar;
	private JMenu menu, subMenuSettings, subMenuAnimation, subMenuMaxNumbers, subMenuArithmeticChoise;
	private JCheckBox boxItemAnimationOnOff, boxItemAllowNegativeNumbers;
	private JRadioButton radioButtonMaxNumber10, radioButtonMaxNumber20, radioButtonMaxNumber30, radioButtonMaxNumber40, radioButtonMaxNumber50, 
						radioButtonAddition, radioButtonSubtraction, radioButtonDivision, radioButtonMultiplication;
	private ButtonGroup maxNumberGroup, arithmeticChoiseGroup;
	private int correctAnswer;
	private JTextField textFieldExpression;
	private ArrayList<Integer> listButtonOrder;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
	}
	/**
	 * @author Nollan
	 * Construct for class Window
	 */
	public Window() {
		super("Tiokompisar");
		this.setLayout(new FlowLayout());
		createMenu();
		initButtons();
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
		textFieldExpression = new JTextField("Expression will be here..");
		textFieldExpression.setEnabled(false);
		textFieldExpression.setBorder(BorderFactory.createCompoundBorder(textFieldExpression.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));		
		this.add(textFieldExpression);
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		
	}
	/**
	 * @author Nollan
	 * Function initiate buttons and assigns values to them
	 */
	public void initButtons(){
		btnZero = new JLabel();
		btnOne = new JLabel();
		btnTwo = new JLabel();
		btnThree = new JLabel();
		btnFour = new JLabel();
		btnFive = new JLabel();
		btnSix = new JLabel();
		btnSeven = new JLabel();
		btnEight = new JLabel();
		btnNine = new JLabel();
		
		buttons[0] = btnZero;
		buttons[1] = btnOne;
		buttons[2] = btnTwo;
		buttons[3] = btnThree;
		buttons[4] = btnFour;
		buttons[5] = btnFive;
		buttons[6] = btnSix;
		buttons[7] = btnSeven;
		buttons[8] = btnEight;
		buttons[9] = btnNine;
		
		randomizeButtonValues();
		
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
		
		subMenuMaxNumbers = new JMenu("Number interval");	//Declares a subMenu (Numbers interval)
		subMenuSettings.add(subMenuMaxNumbers);			//Adds subMenuNumbers to subMenuSettings
		subMenuArithmeticChoise = new JMenu("Choose arithmetic");	//Declares a submenu for arithmetic choise
		subMenuSettings.add(subMenuArithmeticChoise);		//Adds submenu to menu
		
		boxItemAllowNegativeNumbers = new JCheckBox("Allow negative numbers");	//Declares a checkboxitem for allowing negative numbers
		subMenuSettings.add(boxItemAllowNegativeNumbers);	//Add the checkboxitem to menu
		
		subMenuAnimation = new JMenu("Animation");		//Declares a subMenu (Animation)
		subMenuSettings.add(subMenuAnimation);			//Adds subMenuNumbers to subMenuSettings
		boxItemAnimationOnOff = new JCheckBox("Animation ON/OFF",true);	//Declares a checkboxitem for animation on/off
		subMenuAnimation.add(boxItemAnimationOnOff);	//Adds the checkboxitem to menu
		
		radioButtonMaxNumber10 = new JRadioButton("0-10", true);	//Delclares buttonchoises for numberinterval
		subMenuMaxNumbers.add(radioButtonMaxNumber10);				//Adds them to menu
		radioButtonMaxNumber20 = new JRadioButton("0-20");
		subMenuMaxNumbers.add(radioButtonMaxNumber20);
		radioButtonMaxNumber30 = new JRadioButton("0-30");
		subMenuMaxNumbers.add(radioButtonMaxNumber30);
		radioButtonMaxNumber40 = new JRadioButton("0-40");
		subMenuMaxNumbers.add(radioButtonMaxNumber40);
		radioButtonMaxNumber50 = new JRadioButton("0-50");
		subMenuMaxNumbers.add(radioButtonMaxNumber50);
		
		maxNumberGroup = new ButtonGroup();				//Adds buttons to group so only one choise can be active at once
		maxNumberGroup.add(radioButtonMaxNumber10);
		maxNumberGroup.add(radioButtonMaxNumber20);
		maxNumberGroup.add(radioButtonMaxNumber30);
		maxNumberGroup.add(radioButtonMaxNumber40);
		maxNumberGroup.add(radioButtonMaxNumber50);
		
		radioButtonAddition = new JRadioButton("Addition", true);
		subMenuArithmeticChoise.add(radioButtonAddition);
		radioButtonSubtraction = new JRadioButton("Subraction");
		subMenuArithmeticChoise.add(radioButtonSubtraction);
		radioButtonDivision = new JRadioButton("Division");
		subMenuArithmeticChoise.add(radioButtonDivision);
		radioButtonMultiplication = new JRadioButton("Multiplication");
		subMenuArithmeticChoise.add(radioButtonMultiplication);
		
		arithmeticChoiseGroup = new ButtonGroup();
		arithmeticChoiseGroup.add(radioButtonAddition);
		arithmeticChoiseGroup.add(radioButtonSubtraction);
		arithmeticChoiseGroup.add(radioButtonDivision);
		arithmeticChoiseGroup.add(radioButtonMultiplication);				
		
	}
	
	/**
	 * @author Nollan
	 * Function to set button images depending on their values
	 * After this function all button should have a image corresponding to their value
	 */
	public void setButtonImages(){
		for (JLabel jLabel : buttons) {
			try {
				BufferedImage buttonicon = ImageIO.read(new File("path" +jLabel.getText()));
				jLabel.setIcon(new ImageIcon(buttonicon));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @author Nollan
	 * Function gives every button (JLabel) a value
	 */
	public void randomizeButtonValues(){
		int min = 0, max = 0;		//Declares variables that hold min and max value
		for  (Enumeration<AbstractButton> buttons = maxNumberGroup.getElements(); buttons.hasMoreElements();){	//Loops all buttons in maxNumberGroup
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()){			//If the button is selected
				max = Integer.parseInt(button.getText().substring(button.getText().indexOf("-") +1));	//Set max to the choosen maxvalue
				break;				//Break the loop
			}
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();	//List with int
		for (int count = 0; count<max; count++){			//Loops the number of numbers we want
			list.add(count);								//Adds the number to the list
		}
		Collections.shuffle(list);							//Shuffles the list
		int idx = 0;										//Index counter
		for (JLabel button : buttons) {						//Loops buttons
			button.setText(String.valueOf(list.get(idx)));;	//adds the value in the list to the button
			idx++;											//increases with 1
		}
	}
	
	/**
	 * @author Nollan
	 * Function creates the expression that the user is supposed to solve.
	 */
	public void createExpression(){
		randomizeButtonValues();
		//KOLLA!! Kommer bli att knapp 1 eller 0 alltid är rätt.
		listButtonOrder = new ArrayList<Integer>();	//List with int
		for (int count = 0; count<11; count++){			//Loops the number of numbers we want
			listButtonOrder.add(count);								//Adds the number to the list
		}
		Collections.shuffle(listButtonOrder);							//Shuffles the list
		correctAnswer = Integer.parseInt(buttons[listButtonOrder.get(0)].getText()) + Integer.parseInt(buttons[listButtonOrder.get(1)].getText());
		if (Integer.parseInt(buttons[0].getText()) > Integer.parseInt(buttons[1].getText())){
			textFieldExpression.setText(buttons[listButtonOrder.get(0)] + " + ____ = " + String.valueOf(correctAnswer) );
		} else {
			textFieldExpression.setText("____ " + buttons[listButtonOrder.get(0)] + " = " + String.valueOf(correctAnswer) );
		}
		//setButtonImages();
		
		
		
	}
}
