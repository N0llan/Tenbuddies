/**
 * @author Nollan
 * Date: 2016-09-14
 * Description:
 * 		This class handles all logic and GUI functionality. With this program you can practice your mathskills
 * 		and become better at calculating in your head. 
 * 		The program was made to practice my skills in programming and the idea of the program in from my sister who
 * 		is a teacher. 
 */

package tenbuddies;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.KeyEvent;

import java.util.Collections;

@SuppressWarnings("serial")
public class Window extends JFrame {

	private JLabel[] buttons = new JLabel[11];
	private JLabel btnZero,btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnTen, correctImg, incorrectImg;
	private JMenuBar menuBar;
	private JMenu menu, subMenuSettings, subMenuAnimation, subMenuMaxNumbers, subMenuArithmeticChoise;
	private JCheckBox boxItemAnimationOnOff, boxItemAllowNegativeNumbers;
	private JRadioButton radioButtonMaxNumber10, radioButtonMaxNumber20, radioButtonMaxNumber30, radioButtonMaxNumber40, radioButtonMaxNumber50, 
						radioButtonMinNum10MaxNumber50, radioButtonMinNum10MaxNumber40, radioButtonMinNum10MaxNumber30,radioButtonMinNum10MaxNumber20,
						radioButtonMinNum20MaxNumber50, radioButtonMinNum20MaxNumber40, radioButtonMinNum20MaxNumber30, 
						radioButtonMinNum30MaxNumber50,	radioButtonMinNum30MaxNumber40,
						radioButtonMinNum40MaxNumber50,
						radioButtonAddition, radioButtonSubtraction, radioButtonDivision, radioButtonMultiplication;
	private ButtonGroup numberGroup, arithmeticChoiseGroup;
	private JTextField textFieldExpression;
	private ArrayList<Integer> listButtonOrder;
	private JPanel row0, row1, row2, row3, row4;
	private JMenuItem newExpression;
	private int correctAnswer;
	
	
	/**
	 * @author Nollan
	 * Construct for class Window
	 */
	public Window() {
		super("Tiokompisar");
		createMenu();
		this.setLayout(new GridLayout(5, 1));
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		
		row0 = new JPanel();								//Skapar en rad
		row0.setLayout(new FlowLayout(FlowLayout.CENTER));	//Layout på raden
		row0.setBackground(Color.CYAN);						//Bakgrundsfärg
		this.add(row0);
		textFieldExpression = new JTextField("Expression will be here..");
		textFieldExpression.setPreferredSize(new Dimension(300,50));
		textFieldExpression.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
		textFieldExpression.setDisabledTextColor(Color.black);
		textFieldExpression.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldExpression.setEnabled(false);
		textFieldExpression.setBorder(BorderFactory.createCompoundBorder(textFieldExpression.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));	
		row0.add(textFieldExpression);
		
		
		try {
			correctImg = new JLabel();
			incorrectImg = new JLabel();
			correctImg.setVisible(false);
			incorrectImg.setVisible(false);
			row0.add(correctImg);
			row0.add(incorrectImg);
			correctImg.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("correct.png"))));
			incorrectImg.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("incorrect.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		row1 = new JPanel();							
		row1.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		row1.setBackground(Color.CYAN);
		this.add(row1);
		
		row2 = new JPanel();
		row2.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		row2.setBackground(Color.CYAN);
		this.add(row2);

		row3 = new JPanel();
		row3.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		row3.setBackground(Color.CYAN);
		this.add(row3);

		row4 = new JPanel();
		row4.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		row4.setBackground(Color.CYAN);
		this.add(row4);
		
		initButtons();
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		createExpression();
		
	}
	
	/**
	 * @author Nollan
	 * Function initiate buttons and adds them to the panel
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
		btnTen = new JLabel();
		
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
		buttons[10] = btnTen;
				
		int count = 0;
		for (JLabel jLabel : buttons) {
			jLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
			addButtonListener(jLabel);
			if (count >= 0 && count < 3){
				row1.add(jLabel);
			} else if (count >= 3 && count < 6){
				row2.add(jLabel);
			} else if (count >= 6 && count < 9){
				row3.add(jLabel);
			} else {
				row4.add(jLabel);
			}
			count++;
		}
	}
	
	/**
	 * @author Nollan
	 * @param jLabel: Is a button that will be clickable
	 * Function makes a jLabel clickable
	 */
	public void addButtonListener(JLabel jLabel){
		jLabel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				gradingChoise(jLabel.getName());		
			}
		});
	}
	
	/**
	 * @author Nollan
	 * @param clickValue: Contains the namevalue of the jLabel clicked
	 * Function will check if clicked value is the correct answer
	 */
	public void gradingChoise(String clickValue){
		if (clickValue == buttons[listButtonOrder.get(correctAnswer)].getName()){
			correctChoiseBox();
			createExpression();
		} else {
			incorrectChoiseBox();
		}
	}
	
	/**
	 * @author Nollan
	 * Function switches visible image from incorrect to correct
	 */
	public void correctChoiseBox(){
		incorrectImg.setVisible(false);
		correctImg.setVisible(true);
	}
	
	/**
	 * @author Nollan
	 * Function switches visible image from correct to incorrect
	 */
	public void incorrectChoiseBox(){
		correctImg.setVisible(false);
		incorrectImg.setVisible(true);
	}
	
	/**
	 * @author Nollan
	 * Function creates and sets a menubar with settings
	 */
	public void createMenu(){
		menuBar = new JMenuBar();						//Declares a menubar
		menu = new JMenu("Meny");						//Declares rootmenu
		menuBar.add(menu);								//Adds rootmenu to menubar
		newExpression = new JMenuItem("Nytt uttryck");
		newExpression.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, KeyEvent.MODIFIER_NONE));
		menu.add(newExpression);
		
		
		subMenuSettings = new JMenu("Inställningar");		//Declares a submenu
		menu.add(subMenuSettings);						//Adds a submenu called settings to rootmenu
		
		subMenuMaxNumbers = new JMenu("Nummer intervall");	//Declares a subMenu (Numbers interval)
		subMenuSettings.add(subMenuMaxNumbers);			//Adds subMenuNumbers to subMenuSettings
		subMenuArithmeticChoise = new JMenu("Välj räknesätt");	//Declares a submenu for arithmetic choise
		subMenuSettings.add(subMenuArithmeticChoise);		//Adds submenu to menu
		
		boxItemAllowNegativeNumbers = new JCheckBox("Tillåt negativa tal");	//Declares a checkboxitem for allowing negative numbers
		subMenuSettings.add(boxItemAllowNegativeNumbers);	//Add the checkboxitem to menu
		
		subMenuAnimation = new JMenu("Animering");		//Declares a subMenu (Animation)
		subMenuSettings.add(subMenuAnimation);			//Adds subMenuNumbers to subMenuSettings
		boxItemAnimationOnOff = new JCheckBox("Animering PÅ/AV",true);	//Declares a checkboxitem for animation on/off
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
		radioButtonMinNum10MaxNumber20 = new JRadioButton("10-20");
		subMenuMaxNumbers.add(radioButtonMinNum10MaxNumber20);
		radioButtonMinNum10MaxNumber30 = new JRadioButton("10-30");
		subMenuMaxNumbers.add(radioButtonMinNum10MaxNumber30);
		radioButtonMinNum10MaxNumber40 = new JRadioButton("10-40");
		subMenuMaxNumbers.add(radioButtonMinNum10MaxNumber40);
		radioButtonMinNum10MaxNumber50 = new JRadioButton("10-50");
		subMenuMaxNumbers.add(radioButtonMinNum10MaxNumber50);
		radioButtonMinNum20MaxNumber30 = new JRadioButton("20-30");
		subMenuMaxNumbers.add(radioButtonMinNum20MaxNumber30);
		radioButtonMinNum20MaxNumber40 = new JRadioButton("20-40");
		subMenuMaxNumbers.add(radioButtonMinNum20MaxNumber40);
		radioButtonMinNum20MaxNumber50 = new JRadioButton("20-50");
		subMenuMaxNumbers.add(radioButtonMinNum20MaxNumber50);
		radioButtonMinNum30MaxNumber40 = new JRadioButton("30-40");
		subMenuMaxNumbers.add(radioButtonMinNum30MaxNumber40);
		radioButtonMinNum30MaxNumber50 = new JRadioButton("30-50");
		subMenuMaxNumbers.add(radioButtonMinNum30MaxNumber50);
		radioButtonMinNum40MaxNumber50 = new JRadioButton("40-50");
		subMenuMaxNumbers.add(radioButtonMinNum40MaxNumber50);
		
		numberGroup = new ButtonGroup();				//Adds buttons to group so only one choise can be active at once
		numberGroup.add(radioButtonMaxNumber10);
		numberGroup.add(radioButtonMaxNumber20);
		numberGroup.add(radioButtonMaxNumber30);
		numberGroup.add(radioButtonMaxNumber40);
		numberGroup.add(radioButtonMaxNumber50);
		numberGroup.add(radioButtonMinNum10MaxNumber20);
		numberGroup.add(radioButtonMinNum10MaxNumber30);
		numberGroup.add(radioButtonMinNum10MaxNumber40);
		numberGroup.add(radioButtonMinNum10MaxNumber50);
		numberGroup.add(radioButtonMinNum20MaxNumber30);
		numberGroup.add(radioButtonMinNum20MaxNumber40);
		numberGroup.add(radioButtonMinNum20MaxNumber50);
		numberGroup.add(radioButtonMinNum30MaxNumber40);
		numberGroup.add(radioButtonMinNum30MaxNumber50);
		numberGroup.add(radioButtonMinNum40MaxNumber50);	
		
		radioButtonAddition = new JRadioButton("Addition", true);
		subMenuArithmeticChoise.add(radioButtonAddition);
		radioButtonSubtraction = new JRadioButton("Subtraktion");
		subMenuArithmeticChoise.add(radioButtonSubtraction);
		radioButtonDivision = new JRadioButton("Division");
		//subMenuArithmeticChoise.add(radioButtonDivision);
		radioButtonMultiplication = new JRadioButton("Multiplikation");
		subMenuArithmeticChoise.add(radioButtonMultiplication);
		
		arithmeticChoiseGroup = new ButtonGroup();
		arithmeticChoiseGroup.add(radioButtonAddition);
		arithmeticChoiseGroup.add(radioButtonSubtraction);
		arithmeticChoiseGroup.add(radioButtonDivision);
		arithmeticChoiseGroup.add(radioButtonMultiplication);				
		addNewExpressionListener();
	}
	
	/**
	 * @author Nollan
	 * Function adds listener to all radiobuttons in numberGroup and arithmeticChoiseGroup
	 */
	public void addNewExpressionListener(){
		for  (Enumeration<AbstractButton> buttons = numberGroup.getElements(); buttons.hasMoreElements();){	//Loops all buttons in numberGroup
			AbstractButton button = buttons.nextElement();
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createExpression();
				}
			});
		}
		
		for  (Enumeration<AbstractButton> buttons = arithmeticChoiseGroup.getElements(); buttons.hasMoreElements();){	//Loops all buttons in numberGroup
			AbstractButton button = buttons.nextElement();
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createExpression();	
				}
			});
		}
		
		newExpression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createExpression();	
			}
		});
		
		boxItemAllowNegativeNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createExpression();				
			}
		});
		
	}
	
	/**
	 * @author Nollan
	 * Function to set button images depending on their values
	 * After this function all button should have a image corresponding to their value
	 */
	public void setButtonImages(){
		for (JLabel jLabel : buttons) {		//Loops all buttons (numbers)
			try {							//Creates a img from imputstream and scales it to be 100x100
				InputStream inputStream = ClassLoader.getSystemResourceAsStream(jLabel.getName()+".png");
				Image buttonicon = ImageIO.read(inputStream).getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				jLabel.setIcon(new ImageIcon(buttonicon));	//Sets the jLabel (buttons) image
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
		int min = 0, max = 0, negMin = 0, negMax = 0;		//Declares variables that hold min and max value and negative min max
		for  (Enumeration<AbstractButton> buttons = numberGroup.getElements(); buttons.hasMoreElements();){	//Loops all buttons in numberGroup
			AbstractButton button = buttons.nextElement();
			if (button.isSelected()){			//If the button is selected
				max = Integer.parseInt(button.getText().substring(button.getText().indexOf("-") +1));	//Set max to the choosen maxvalue
				min = Integer.parseInt(button.getText().substring(0,button.getText().indexOf("-")));	//Set min to the choosen minvalue
				if (boxItemAllowNegativeNumbers.isSelected()){											//If user want to allow negative numbers
					negMin = Integer.parseInt("-"+String.valueOf(max));									//Invert max and min to negative numbers
					negMax = Integer.parseInt("-"+String.valueOf(min));									//and save them in variables
				}
				break;				//Break the loop
			}
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>();	//List with int
		for (int count = min; count<=max; count++){			//Loops the number of numbers we want
			list.add(count);								//Adds the number to the list
		}
		if (boxItemAllowNegativeNumbers.isSelected()){		
			if (negMax == 0){
				negMax--;
			}
			for (int count = negMin; count<= negMax; count++){	//Loops the negativenumbers we want
				list.add(count);							//Adds these to the list
			}
		}
		Collections.shuffle(list);							//Shuffles the list
		int idx = 0;										//Index counter
		for (JLabel button : buttons) {						//Loops buttons
			button.setName(String.valueOf(list.get(idx)));;	//adds the value in the list to the button
			idx++;											//increases with 1
		}
	}
	
	/**
	 * @author Nollan
	 * Function creates the expression that the user is supposed to solve.
	 */
	public void createExpression(){
		randomizeButtonValues();
		setButtonImages();
		int answer = 0;
		listButtonOrder = new ArrayList<Integer>();	//List with int
		for (int count = 0; count< buttons.length; count++){			//Loops the number of numbers we want
			listButtonOrder.add(count);								//Adds the number to the list
		}
		Collections.shuffle(listButtonOrder);							//Shuffles the list
		
		String arithmetic = "";
		if(radioButtonAddition.isSelected()){						//Checks if we are dealing with addition
			arithmetic = " + ";										//Calculates the answer and stores it in variable
			answer = Integer.parseInt(buttons[listButtonOrder.get(0)].getName().toString()) + Integer.parseInt(buttons[listButtonOrder.get(1)].getName().toString());
		} else if (radioButtonSubtraction.isSelected()) {			//Checks if we are dealing with subtraction
			arithmetic = " - ";										//Calculates the answer and stores it in variable
			answer = Integer.parseInt(buttons[listButtonOrder.get(0)].getName().toString()) - Integer.parseInt(buttons[listButtonOrder.get(1)].getName().toString());
			while (!boxItemAllowNegativeNumbers.isSelected() && answer < 0){
				randomizeButtonValues();
				answer = Integer.parseInt(buttons[listButtonOrder.get(0)].getName().toString()) - Integer.parseInt(buttons[listButtonOrder.get(1)].getName().toString());
			}
																	
		} else if (radioButtonMultiplication.isSelected()){			//Checks if we are dealing with multiplication
			arithmetic = " \u2219 ";								//Calculates the answer and stores it in variable
			answer = Integer.parseInt(buttons[listButtonOrder.get(0)].getName().toString()) * Integer.parseInt(buttons[listButtonOrder.get(1)].getName().toString());
		} else if (radioButtonDivision.isSelected()){				//Checks if we are dealing with division
			arithmetic = " / ";										//Calculates the answer and stores it in variable
			answer = Integer.parseInt(buttons[listButtonOrder.get(0)].getName().toString()) / Integer.parseInt(buttons[listButtonOrder.get(1)].getName().toString());
		}
		
		if (Integer.parseInt(buttons[0].getName()) > Integer.parseInt(buttons[1].getName())){
			textFieldExpression.setText(buttons[listButtonOrder.get(0)].getName() + arithmetic + "____ = " + String.valueOf(answer) );	//Adds expression to textbox
			correctAnswer = 1;										//Stores the correct index of answer
		} else {
			textFieldExpression.setText("____" + arithmetic + buttons[listButtonOrder.get(1)].getName() + " = " + String.valueOf(answer) );	//Adds expression to textbox
			correctAnswer = 0;										//Stores the correct index of answer
		}
		setButtonImages();
		
		
		
	}
}
