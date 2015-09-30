package edu.ncsu.csc216.movie101.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import edu.ncsu.csc216.movie101.quiz.MovieQuiz;
import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.QuestionException;

/**
 * Movie101GUI is the user interface for the program. 
 * 
 * @author Corey Colberg, Thomas Ortiz
 */
public class Movie101GUI extends JFrame{
	/** ID for serialization */
	private static final long serialVersionUID = 1L;
	/**
	 * Container for the GUI
	 */
	private Container contents;
	/** Four radio buttons, one for each answer choice */
	private JRadioButton answer1, answer2, answer3, answer4;
	/** Labels for to display the question and answer */
	private JLabel questionsLbl, answersLbl;
	/** Button group so only one answer at a time can be selected*/
	private ButtonGroup answers;
	/** The three buttons on the bottom of the GUI to check the answer
	 * go to the next question, and quit the program */
	private JButton submitButton, nextButton, quitButton;

	/** Sole data member for not used in GUI. Gets and displays all the questions,
	 * answers, and checks the answers, and gets attempts and correct answers.*/
	private MovieQuiz mq;
	
	/**
	 * Constructor for the GUI. Uses try catch to make sure the MovieQuiz is created
	 * correctly. Uses GridBagLayout to put all the labels buttons and RadioButtons
	 * in the GUI. Creates and adds all the labels, buttons, and radiobuttons, then adds 
	 * them into the container. 
	 * @param filename  Optional name of the questions file.
	 */
	public Movie101GUI(String filename) {
		super ("Movies 101");

		//Taken from project one GUI (Jo Perry) and updated slightly
		try {
			if (filename == null) {
				String userPickFilename = null;
				JFileChooser fc = new JFileChooser(".");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int returnVal = fc.showOpenDialog(this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					userPickFilename = fc.getSelectedFile().getAbsolutePath();
				}
				mq = new MovieQuiz(userPickFilename);
			} else {
				mq = new MovieQuiz(filename);
			}
			
			
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
			System.exit(-1);
		} catch (QuestionException ie) {
			JOptionPane.showMessageDialog(new JFrame(), "QuestionException - "
					+ "error processing intelligent tutoring question file");
			System.exit(-1);
		} catch(IndexOutOfBoundsException e){
			JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
			System.exit(-1);
		} 
		
		
		//
		contents=getContentPane();
		contents.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		
		try{
			questionsLbl = new JLabel(mq.getCurrentQuestionText());
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridx = 0;
			c.gridy = 0;
			contents.add(questionsLbl, c);
		} catch(EmptyQuestionListException EQLE) {
			EQLE.getMessage();
		}

		String[] answersArray = null;
		try{
			answersArray = mq.getCurrentQuestionChoices();
		} catch(EmptyQuestionListException EQLE) {
			EQLE.getMessage();
		}
		
		try{
			answer1 = new JRadioButton(answersArray[0]);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridx = 0;
			c.gridy = 1;
			contents.add(answer1, c);
	
			answer2 = new JRadioButton(answersArray[1]);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridx = 0;
			c.gridy = 2;
			contents.add(answer2, c);
			
			answer3 = new JRadioButton(answersArray[2]);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridx = 0;
			c.gridy = 3;
			contents.add(answer3, c);
			
			answer4 = new JRadioButton(answersArray[3]);
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.5;
			c.gridx = 0;
			c.gridy = 4;
			contents.add(answer4, c);
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			aiobe.getMessage();
		}
		
		
		answersLbl = new JLabel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 5;
		contents.add(answersLbl, c);
		answersLbl.setVisible(false);
		
		submitButton = new JButton("Submit Answer");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 6;
		contents.add(submitButton, c);
		submitButton.setEnabled(false);

		nextButton = new JButton("Next Question");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 6;
		contents.add(nextButton, c);
		
		quitButton = new JButton("Quit");
		c.fill = GridBagConstraints.RELATIVE;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 6;
		contents.add(quitButton, c);
		
		answers = new ButtonGroup();
		answers.add(answer1);
		answers.add(answer2);
		answers.add(answer3);
		answers.add(answer4);
		
		
		ItemSelectedHandler ish = new ItemSelectedHandler();
		ButtonPressedHandler bph = new ButtonPressedHandler();
		
		quitButton.addActionListener(bph);
		submitButton.addActionListener(bph);
		nextButton.addActionListener(bph);
		
		answer1.addItemListener(ish);
		answer2.addItemListener(ish);
		answer3.addItemListener(ish);
		answer4.addItemListener(ish);

		nextButton.setEnabled(false);
		
		setSize(600,200);
		setVisible(true);
		
	}
	
	/**
	 * Private class used for RadioButton events
	 */
	private class ItemSelectedHandler implements ItemListener{
		
		/**
		 * Handles all actions for the various RadioButtons for the GUI.
		 * @param e the RaddioButton click
		 */
		public void itemStateChanged(ItemEvent e) {
			if(answers.isSelected(null)){
				submitButton.setEnabled(false);
			} else {
				submitButton.setEnabled(true);
			}
		}
	}
	
	/**
	 * Used for all button pressed events
	 */
	private class ButtonPressedHandler implements ActionListener {
		/**
		 * Handles all actions for the various buttons for the GUI.
		 * @param e the button click
		 */
		public void actionPerformed(ActionEvent e){
			
			if(e.getSource().equals(quitButton)){ // If the quit button is pressed
				JOptionPane.showMessageDialog(new JFrame(), "You answered " + mq.getNumCorrectQuestions() 
						+ " questions correctly out of " + mq.getNumAttemptedQuestions() + " attempts.");
				System.exit(0);
			} else if (e.getSource().equals(nextButton)){ // If NEXT button is pressed 
				submitButton.setEnabled(false);
				if(mq.hasMoreQuestions()){
					answers.clearSelection();
					answersLbl.setVisible(false);
					
					try {
						questionsLbl.setText(mq.getCurrentQuestionText());
						answer1.setText(mq.getCurrentQuestionChoices()[0]);
						answer2.setText(mq.getCurrentQuestionChoices()[1]);
						answer3.setText(mq.getCurrentQuestionChoices()[2]);
						answer4.setText(mq.getCurrentQuestionChoices()[3]);
					} catch (EmptyQuestionListException e1) {
						e1.printStackTrace();
					}
					nextButton.setEnabled(false);
					// Re enables the answer buttons when moving to new question.
					answer1.setEnabled(true);
					answer2.setEnabled(true);
					answer3.setEnabled(true);
					answer4.setEnabled(true);
					
				} else { // IF there are no more questions in test
					JOptionPane.showMessageDialog(new JFrame(), "You answered " + mq.getNumCorrectQuestions() 
							+ " questions correctly out of " + mq.getNumAttemptedQuestions() + " attempts.");
					System.exit(1);
				}
				
			} else if (e.getSource().equals(submitButton)) { // if SUBMIT is pressed 
				answersLbl.setVisible(true);
				nextButton.setEnabled(true);
				submitButton.setEnabled(false); //
				
				if(mq.getQuestions().getCurrentState() == mq.getQuestions().getEleQuestionState()){
					submitButton.setEnabled(false);
				}
				try{
					if(answer1.isSelected()){
						answersLbl.setText(mq.processAnswer("a"));
					} else if(answer2.isSelected()){
						answersLbl.setText(mq.processAnswer("b"));
					} else if(answer3.isSelected()){
						answersLbl.setText(mq.processAnswer("c"));
					} else if(answer4.isSelected()){
						answersLbl.setText(mq.processAnswer("d"));
					}
					submitButton.setEnabled(false);
					// Disables the answer buttons while moving to new question.
					answer1.setEnabled(false);
					answer2.setEnabled(false);
					answer3.setEnabled(false);
					answer4.setEnabled(false);
				} catch (EmptyQuestionListException EQLE) {
					EQLE.getMessage();
				} catch (IndexOutOfBoundsException ee){  
					// Added this to stop the annoying stack trace at end of program even though
					// it works fine.
				}
			}
		}
	}

	/**
	 * Starts the program.  
	 * @param args command line argument for filename.
	 */
	public static void main(String[] args){
		Movie101GUI movieGUI;
		if(args.length != 0){
			movieGUI = new Movie101GUI(args[0]);
		} else {
			movieGUI = new Movie101GUI(null);
		}
		movieGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
}