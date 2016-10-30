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

import java.awt.BorderLayout;
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
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
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
	private JMenu menu, subMenuSettings, subMenuAnimation, subMenuMaxNumbers, subMenuArithmeticChoise, subMenuCompetition;
	private JCheckBox boxItemAnimationOnOff, boxItemAllowNegativeNumbers, boxItemButtonAutoRestart;
	private JMenuItem buttonCompetition;
	private JRadioButton radioButtonMaxNumber10, radioButtonMaxNumber20, radioButtonMaxNumber30, radioButtonMaxNumber40, radioButtonMaxNumber50, 
						radioButtonMinNum10MaxNumber50, radioButtonMinNum10MaxNumber40, radioButtonMinNum10MaxNumber30,radioButtonMinNum10MaxNumber20,
						radioButtonMinNum20MaxNumber50, radioButtonMinNum20MaxNumber40, radioButtonMinNum20MaxNumber30, 
						radioButtonMinNum30MaxNumber50,	radioButtonMinNum30MaxNumber40,
						radioButtonMinNum40MaxNumber50,
						radioButtonAddition, radioButtonSubtraction, radioButtonDivision, radioButtonMultiplication;
	private ButtonGroup numberGroup, arithmeticChoiseGroup;
	private JTextField textFieldExpression, textFieldTime, textFieldScoreCount, textFieldCompetition, countDownText, congratzText, congratzTextTop,
						congratzText2nd, congratzText3rd;
	private ArrayList<Integer> listButtonOrder;
	private JPanel row0, row1, row2, row3, row4, expressionScreen, competitionCountDown, congratzScreen;
	private JMenuItem newExpression;
	private int correctAnswer = -1, competitionExpressionCount = 0, comptetitionCorrectCount = 0;
	private long startTime, totalTime = 0, totalTimeTmp = 0;
	private boolean timeCountStarted, allowNumberButtonClick = true, competitionActive = false;
	
	/**
	 * @author Nollan
	 * Construct for class Window
	 */
	public Window() {
		super("Tiokompisar");
		createMenu();
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		
		expressionScreen = new JPanel(new GridLayout(5, 1)); //Adds expressionscreen and its properties
		expressionScreen.setSize(600,600);
		expressionScreen.setBackground(Color.CYAN);
		
		
		competitionCountDown = new JPanel(new FlowLayout()); //Adds competitionscreen and its properties
		competitionCountDown.setBackground(Color.BLACK);
		competitionCountDown.setSize(600,600);
		competitionCountDown.setVisible(false);				//Hides competition panel
		this.add(competitionCountDown);	
		
		congratzScreen = new JPanel(new FlowLayout());		//Adds congratzscreen and its properties
		congratzScreen.setBackground(Color.YELLOW);
		congratzScreen.setSize(600,600);
		congratzScreen.setVisible(false);
		this.add(congratzScreen);
		
		congratzTextTop = new JTextField("Grattis");					//Adds textfield for congratztexttop and its properties
		congratzTextTop.setEnabled(false);
		congratzTextTop.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 100));
		congratzTextTop.setDisabledTextColor(Color.BLACK);
		congratzTextTop.setBackground(Color.YELLOW);
		congratzTextTop.setBorder(null);
		congratzScreen.add(congratzTextTop);
		
		congratzText = new JTextField();					//Adds textfield for congratztext and its properties
		congratzText.setEnabled(false);
		congratzText.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		congratzText.setDisabledTextColor(Color.BLACK);
		congratzText.setBackground(Color.YELLOW);
		congratzText.setBorder(null);
		congratzScreen.add(congratzText);
		
		congratzText2nd = new JTextField();					//Adds textfield for congratztext2nd and its properties
		congratzText2nd.setEnabled(false);
		congratzText2nd.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		congratzText2nd.setDisabledTextColor(Color.BLACK);
		congratzText2nd.setBackground(Color.YELLOW);
		congratzText2nd.setBorder(null);
		congratzScreen.add(congratzText2nd);
		
		congratzText3rd = new JTextField("Klicka för att komma tillbaka så du kan prova igen."); //Adds textfield for congratztext3rd and its properties
		congratzText3rd.setEnabled(false);
		congratzText3rd.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		congratzText3rd.setDisabledTextColor(Color.BLACK);
		congratzText3rd.setBackground(Color.YELLOW);
		congratzText3rd.setBorder(null);
		congratzScreen.add(congratzText3rd);
		
		countDownText = new JTextField("3"); 				//Adds textfield for countdown and its properties
		countDownText.setEnabled(false);
		countDownText.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 400));
		countDownText.setDisabledTextColor(Color.WHITE);
		countDownText.setBackground(Color.BLACK);
		countDownText.setBorder(null);
		competitionCountDown.add(countDownText);			//Adds text to jpanel
			
		this.add(expressionScreen);
		
		row0 = new JPanel();								//Skapar översta raden
		row0.setLayout(new FlowLayout(FlowLayout.CENTER));	//Layout på raden
		row0.setBackground(Color.CYAN);						//Bakgrundsfärg
		expressionScreen.add(row0);										//Lägger till första raden till frame
		
		textFieldScoreCount = new JTextField("");
		textFieldScoreCount.setPreferredSize(new Dimension(110,50));
		textFieldScoreCount.setEnabled(false);
		textFieldScoreCount.setOpaque(false);
		textFieldScoreCount.setBorder(null);
		textFieldScoreCount.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		textFieldScoreCount.setDisabledTextColor(Color.black);
		row0.add(textFieldScoreCount);
		row0.add(Box.createRigidArea(new Dimension(24,0)));
		
		textFieldExpression = new JTextField("");			//Skapar textrutan som uttrycket ska visas i
		textFieldExpression.setPreferredSize(new Dimension(300,50));	//Sätter storlek och andra formateringar
		textFieldExpression.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 34));
		textFieldExpression.setDisabledTextColor(Color.black);
		textFieldExpression.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldExpression.setEnabled(false);
		textFieldExpression.setBorder(BorderFactory.createCompoundBorder(textFieldExpression.getBorder(),BorderFactory.createEmptyBorder(5,5,5,5)));
		row0.add(textFieldExpression);						//Lägger till textrutan
		row0.add(Box.createRigidArea(new Dimension(34,0)));
		
		textFieldTime = new JTextField("");
		textFieldTime.setPreferredSize(new Dimension(100,50));
		textFieldTime.setEnabled(false);
		textFieldTime.setOpaque(false);
		textFieldTime.setBorder(null);
		textFieldTime.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		textFieldTime.setDisabledTextColor(Color.black);
		row0.add(textFieldTime);
		
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
		expressionScreen.add(row1);
		
		row2 = new JPanel();
		row2.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		row2.setBackground(Color.CYAN);
		expressionScreen.add(row2);

		row3 = new JPanel();
		row3.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		row3.setBackground(Color.CYAN);
		expressionScreen.add(row3);

		row4 = new JPanel();
		row4.setLayout(new FlowLayout(FlowLayout.CENTER,1,1));
		row4.setBackground(Color.CYAN);
		expressionScreen.add(row4);
		
		initButtons();
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		randomizeButtonValues();
		setButtonImages();
		
	}
	
	/**
	 * @author Nollan
	 * Function initiate buttons and adds them to the panel
	 */
	public void initButtons(){
		btnZero = new JLabel();	//Initializes "buttons"
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
		
		buttons[0] = btnZero;	//Adds "buttons" to array
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
		for (JLabel jLabel : buttons) {		//Loops array with "buttons"
			jLabel.setBorder(new EmptyBorder(5, 5, 5, 5));	//Sets border
			addButtonListener(jLabel);		//Adds buttonListener (calls function)
			if (count >= 0 && count < 3){	//Add "button" to correct row
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
	
	public void disableButtonClicks(){
		allowNumberButtonClick = false;
	}
	
	public void enableButtonClicks(){
		allowNumberButtonClick = true;
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
				if (allowNumberButtonClick){
					gradingChoise(jLabel.getName());
				}
				
			}
		});
	}
	
	/**
	 * @author Nollan
	 * @param clickValue: Contains the name of the jLabel clicked
	 * Function will check if clicked value is the correct answer
	 */
	public void gradingChoise(String clickValue){
		if (competitionActive){
			competitionExpressionCount++;	
		}	
		if (correctAnswer != -1){
			if (clickValue == buttons[listButtonOrder.get(correctAnswer)].getName()){
				stopTimeCount();
				setCorrectAnswerImage();
				if (competitionActive){
					comptetitionCorrectCount++;
					textFieldScoreCount.setText(Integer.toString(comptetitionCorrectCount)+ "/" + textFieldCompetition.getText());
				} 
				if (boxItemButtonAutoRestart.isSelected()){
					createExpression();
				}
			} else if(clickValue != buttons[listButtonOrder.get(correctAnswer)].getName()){
				if (competitionActive){
					textFieldScoreCount.setText(Integer.toString(comptetitionCorrectCount)+ "/" + textFieldCompetition.getText());
					stopTimeCount();
					createExpression();
				} 
				setIncorrectAnswerImage();
			}	
		}	
		
	}
	
	/**
	 * @author Nollan
	 * Function switches visible image from incorrect to correct
	 */
	public void setCorrectAnswerImage(){
		incorrectImg.setVisible(false);
		Double timeElapsed = Double.parseDouble(textFieldTime.getText().toString());
		System.out.println(timeElapsed);
		try {
			if (timeElapsed < 2){
				correctImg.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("5star.png"))));
			} else if (timeElapsed < 4){
				correctImg.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("4star.png"))));
			} else if (timeElapsed < 6){
				correctImg.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("3star.png"))));
			} else if (timeElapsed < 8){
				correctImg.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("2star.png"))));
			} else {
				correctImg.setIcon(new ImageIcon(ImageIO.read(ClassLoader.getSystemResourceAsStream("1star.png"))));
			}
			correctImg.setVisible(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @author Nollan
	 * Function switches visible image from correct to incorrect
	 */
	public void setIncorrectAnswerImage(){
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
		
		boxItemButtonAutoRestart = new JCheckBox("Nytt tal automatiskt");
		boxItemButtonAutoRestart.setSelected(false);
		subMenuSettings.add(boxItemButtonAutoRestart);
		
		subMenuCompetition = new JMenu("Tävling..");
		subMenuSettings.add(subMenuCompetition);
		buttonCompetition = new JMenuItem("Starta tävling!");
		subMenuCompetition.add(buttonCompetition);
		textFieldCompetition = new JTextField("10");
		textFieldCompetition.setToolTipText("Antal uttryck..");
		subMenuCompetition.add(textFieldCompetition);
		
		subMenuMaxNumbers = new JMenu("Nummer intervall");	//Declares a subMenu (Numbers interval)
		subMenuSettings.add(subMenuMaxNumbers);			//Adds subMenuNumbers to subMenuSettings
		subMenuArithmeticChoise = new JMenu("Välj räknesätt");	//Declares a submenu for arithmetic choise
		subMenuSettings.add(subMenuArithmeticChoise);		//Adds submenu to menu
		
		boxItemAllowNegativeNumbers = new JCheckBox("Tillåt negativa tal");	//Declares a checkboxitem for allowing negative numbers
		subMenuSettings.add(boxItemAllowNegativeNumbers);	//Add the checkboxitem to menu
		
		subMenuAnimation = new JMenu("Animering");		//Declares a subMenu (Animation)
		//subMenuSettings.add(subMenuAnimation);			//Adds subMenuNumbers to subMenuSettings
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
					stopTimeCount();
					createExpression();
				}
			});
		}
		
		for  (Enumeration<AbstractButton> buttons = arithmeticChoiseGroup.getElements(); buttons.hasMoreElements();){	//Loops all buttons in numberGroup
			AbstractButton button = buttons.nextElement();
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					stopTimeCount();
					createExpression();	
				}
			});
		}
		
		newExpression.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTimeCount();
				createExpression();	
			}
		});
		
		boxItemButtonAutoRestart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				stopTimeCount();
				createExpression();		
			}
		});
		
		boxItemAllowNegativeNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTimeCount();
				createExpression();				
			}
		});
			
		buttonCompetition.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				competitionActive = true;
				stopTimeCount();
				startCompetition();
			}
		});;
		
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
		if (!competitionActive || (competitionActive && competitionExpressionCount < Integer.parseInt(textFieldCompetition.getText()))){
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
				arithmetic = " \u2219 ";								//Gives the value of multiplication sign
																		//Calculates the answer and stores it in variable
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
			setButtonImages();											//Calls function setButtonImages
			startTimeCount();											//Calls function startTimeCount
		} else {
			endCompetition();
		}
		
	}
	
	/**
	 * @author Nollan
	 * Function handles the time counting
	 */
	public void updateTime(){
		new Thread(new Runnable() {										//Create new thread for timecount
			
			@Override
			public void run() {
				while (timeCountStarted){								//If timecount is started
					long timeElapsed = System.nanoTime() - startTime;	//Current elapsed time is calculated
					totalTimeTmp = timeElapsed;
					timeElapsed = TimeUnit.MILLISECONDS.convert(timeElapsed, TimeUnit.NANOSECONDS);	//Convert to correct format
					long seconds = TimeUnit.MILLISECONDS.toSeconds(timeElapsed);
					timeElapsed -= TimeUnit.SECONDS.toMillis(seconds);
					textFieldTime.setText(String.format("%01d.%02d", seconds,timeElapsed));	//Set text with correct format
				}
			}
		}).start();
	}
	
	/**
	 * @author Nollan
	 * Function start the time count
	 */
	public void startTimeCount(){
		timeCountStarted = true;					//Sets variable timeCountStarted to true
		startTime = System.nanoTime();				//Current time
		updateTime();								//Calls function
	}
	
	/**
	 * @author Nollan
	 * Function stops the time count
	 */
	public void stopTimeCount(){
		if (competitionExpressionCount != 1){
			totalTime += totalTimeTmp;
		}
		timeCountStarted = false;					//Sets to false
	}
	
	/**
	 * @author Nollan
	 * Function handle the competition start screen and enabling the correct GUI
	 */
	public void startCompetition(){	
		disableButtonClicks();
		comptetitionCorrectCount = 0;
		competitionExpressionCount = 0;
		correctImg.setVisible(false);
		incorrectImg.setVisible(false);
		new Thread(new Runnable() {
			public void run() {				
				try {
					expressionScreen.setVisible(false);			//Hides the main screen (called expression)
					competitionCountDown.setVisible(true);		//Shows the countdown for competition
					textFieldScoreCount.setVisible(true);		//Shows the counter
										
					if (!textFieldCompetition.equals("")){		//IF the textfield isn't empty
						textFieldScoreCount.setText(Integer.toString(comptetitionCorrectCount)+ "/" + textFieldCompetition.getText());
					} 
					countDownText.setText("3");					//Countdown
					Thread.sleep(1000);
					countDownText.setText("2");
					Thread.sleep(1000);
					countDownText.setText("1");
					Thread.sleep(1000);
					competitionCountDown.setVisible(false);		//Switches back to main screen
					expressionScreen.setVisible(true);
					boxItemButtonAutoRestart.setSelected(true); //Enables autorestart so the competition will generate new expressions.
					createExpression();							//Calls function
					enableButtonClicks();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}).start();	
		
	}
	
	public void endCompetition(){
		disableButtonClicks();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1500);
					totalTime = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);	//Convert to correct format
					long seconds = TimeUnit.MILLISECONDS.toSeconds(totalTime);
					totalTime -= TimeUnit.SECONDS.toMillis(seconds);
					congratzText.setText("Du har precis avklarat tävlingen. Du hade " + textFieldScoreCount.getText() + " rätt.");
					congratzText2nd.setText("Total tid för detta var " + String.format("%01d.%02d", seconds,totalTime) + "s.");
					correctImg.setVisible(false);
					incorrectImg.setVisible(false);
					comptetitionCorrectCount = 0;
					competitionExpressionCount = 0;
					expressionScreen.setVisible(false);
					correctImg.setVisible(false);
					textFieldExpression.setText("");
					congratzScreen.setVisible(true);
					textFieldTime.setText("");
					boxItemButtonAutoRestart.setSelected(false);
					textFieldScoreCount.setText("");
					competitionActive = false;
					
					addMouseListener(new MouseListener() {
						
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
							congratzScreen.setVisible(false);
							expressionScreen.setVisible(true);
							enableButtonClicks();
						}});
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				}
			}
		}).start();
		
		//Här vill vi visa en bra jobbat skärm. Summan av tid och antal korrekta/antal frågor.
		//Även slå av competition och autorestart
	}
}
