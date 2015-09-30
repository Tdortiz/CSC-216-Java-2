package edu.ncsu.csc216.movie101.question;

import java.util.*;
import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.Question;
import edu.ncsu.csc216.question_library.StandardQuestion;

/**
 * Handles the question aspect of the program by keeping track of the number of correct answers,
 * number attempts questions, and uses a finite state machine to keep track of the lists of 
 * questions.
 * @author Thomas Ortiz, Corey Colberg
 */
public class MovieQuestions{
	
	/** Number of correct answers */
	private int numCorrectAnswers;
	/** Number of attempted questions */
	private int numAttemptQuests;
	/** Correct! string */
	private static final String CORRECT = "Correct!";
	/** Incorrect! string */
	private static final String INCORRECT = "Incorrect!";
	/** Empty Space */
	private static final String SEPARATOR = " ";
	/** The Advance Question State of the finite state machine */
	private AdvancedQuestionState advQuestionState;
	/** The Standard Question State of the finite state machine */
	private StandardQuestionState staQuestionState;
	/** The Elementary Question State of the finite state machine */
	private ElementaryQuestionState eleQuestionState;
	/** The current Question State */
	private QuestionState currentQuestionState;
	/** A list of advanced questions */
	private List<Question> advQuestion;
	/** A list of standard questions */
	private List<Question> staQuestion;
	/** A list of elementary questions */
	private List<Question> eleQuestion;
	/** A list of elementary questions to retrieve hints */
	private List<ElementaryQuestion> eleHint;
	/** A list of standard questsion */
	private List<StandardQuestion> stans;
	/** A list of advanced questions to retrieve victory messages */
	private List<AdvancedQuestion> advMssg;
	
	/**
	 * Constructor for MovieQuestions.
	 * @param standard List of Standard Questions
	 * @param elementary List of Elementary Questions
	 * @param advanced List of Advanced Questions
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MovieQuestions(List<StandardQuestion> standard, List<ElementaryQuestion> elementary
															, List<AdvancedQuestion> advanced){
		this.eleHint = elementary;
		this.stans = standard;
		this.advMssg = advanced;
		
		advQuestion = new ArrayList(advanced.size());
		for(Question i: advanced){
			advQuestion.add(i);
		}
		staQuestion = new ArrayList(standard.size());
		for(Question i: standard){
			staQuestion.add(i);
		}
		eleQuestion = new ArrayList(elementary.size());
		for(Question i: elementary){
			eleQuestion.add(i);
		}
		
		this.advQuestionState = new AdvancedQuestionState(advQuestion);
		this.staQuestionState = new StandardQuestionState(staQuestion);
		this.eleQuestionState = new ElementaryQuestionState(eleQuestion);
		
		currentQuestionState = eleQuestionState;
		numCorrectAnswers = 0;
		numAttemptQuests = 0;
	}
	
	/**
	 * Tests if there are any more questions.
	 * @return true if there are, false if there are not
	 */
	public boolean hasMoreQuestions(){
		return currentQuestionState.hasMoreQuestions();
	}
	
	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException{
		if(currentQuestionState.getCurrentQuestion() == null){
			throw new EmptyQuestionListException("Question Is Null");
		}
		return currentQuestionState.getCurrentQuestionText();
	}
	
	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		if(currentQuestionState.getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return currentQuestionState.getCurrentQuestionChoices();
	}
	
	/**
	 * Process the user's answer to the current question.
	 * @param s the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String processAnswer(String s) throws EmptyQuestionListException{
		if(currentQuestionState == advQuestionState){
			return advQuestionState.processAnswer(s);
		} else if(currentQuestionState == staQuestionState){
			return staQuestionState.processAnswer(s);
		} else{
			return eleQuestionState.processAnswer(s);
		}
	}
	
	/** 
	 * Returns the number of correct answered questions.
	 * @return number of correct questions
	 */
	public int getNumCorrectQuestions(){
		return numCorrectAnswers;
	}
	
	/**
	 * Returns the number of attempted questions.
	 * @return number of attempted questions
	 */
	public int getNumAttemptedQuestions(){
		return numAttemptQuests;
	}

	/**
	 * Returns the current state of the Finite State Machine.
	 * @return the current QuestionState
	 */
	public QuestionState getCurrentState(){
		return this.currentQuestionState;
	}
	
	/** 
	 * Returns eleQuestionState.
	 * @return eleQuestionState
	 */
	public ElementaryQuestionState getEleQuestionState(){
		return this.eleQuestionState;
	}

	//---------------------------------------------------------------------------------------------
	/**
	 * The Advanced Question State that handles the Advanced Questions of the program.
	 * @author Thomas Ortiz, Corey Colberg
	 */
	public class AdvancedQuestionState extends QuestionState{
	
		/**
		 * Constructor for AdvancedQuestionState.
		 * @param list of Advanced Questions
		 */
		public AdvancedQuestionState(List<Question> list){
			super(list);
		}
		
		/**
		 * Processes the answer for Advanced Questions.
		 * @param s user's answer
		 * @throws EmptyQuestionListException if question is null
		 */
		public String processAnswer(String s) throws EmptyQuestionListException{
			if(currentQuestionState.getCurrentQuestion() == null){
				throw new EmptyQuestionListException();
			}
			// IF ANSWER IS CORRECT
			if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer().toLowerCase())){
				numCorrectAnswers++;
				numAttemptQuests++;
				currentQuestionState = advQuestionState;
				currentQuestionState.nextQuestion();
				
				String mssg = advMssg.get(advQuestion.indexOf(
						  currentQuestionState.getCurrentQuestion())).getComment();
				
				return CORRECT + SEPARATOR + mssg;
			// IF ANSWER IS WRONG
			} else {
				currentQuestionState = staQuestionState;
				numAttemptQuests++;
				currentQuestionState.nextQuestion();
				return INCORRECT;
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------
	/**
	 * The Standard Question State that handles the Standard Questions of the program.
	 * @author Thomas Ortiz, Corey Colberg
	 */
	public class StandardQuestionState extends QuestionState{
		
		/** Number of Standard Questions correct in a row */
		private int numCorrectInRow;
		
		/**
		 * Constructor for StandardQuestionState.
		 * @param list of Standard Questions
		 */
		public StandardQuestionState(List<Question> list){
			super(list);
			numCorrectInRow = 0;
		}
		
		/**
		 * Processes the answer for Standard Questions.
		 * @param s user's answer
		 * @throws EmptyQuestionListException if question is null
		 */
		public String processAnswer(String s) throws EmptyQuestionListException{
			if(currentQuestionState.getCurrentQuestion() == null){
				throw new EmptyQuestionListException();
			}
			// IF THE ANSWER WAS CORRECT
			if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer().toLowerCase())){
				numCorrectAnswers++;
				numAttemptQuests++;
				numCorrectInRow++; //Tracks Correct In Row 
				
				if(numCorrectInRow >= 2){
					currentQuestionState = advQuestionState;
					numCorrectInRow = 0;
					if(!(currentQuestionState.getCurrentQuestion() == advMssg.get(0))){
						currentQuestionState.nextQuestion();
					} else if(ansFirstAdvAlready){ // Check this out!!@#!#@
						currentQuestionState.nextQuestion(); //
					}
					ansFirstAdvAlready = true;
					
				} else {
					currentQuestionState = staQuestionState;
					currentQuestionState.nextQuestion();
				}
				return CORRECT;
				
			// IF THE ANSWER WAS WRONG	
			} else {
				numAttemptQuests++;
				numCorrectInRow = 0;
				currentQuestionState = eleQuestionState;
				currentQuestionState.nextQuestion();
				return INCORRECT;
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------
	/**
	 * The Elementary Question State that handles the Elementary Questions of the program.
	 * @author Thomas Ortiz, Corey Colberg
	 */
	public class ElementaryQuestionState extends QuestionState{
		/** Number of attempts on a question */
		private int attempts;
		/** Number of questions right in a row */
		private int numCorrectInRow;
		
		/**
		 * Constructor for ElementaryQuestionState.
		 * @param list of Elementary Questions
		 */
		public ElementaryQuestionState(List<Question> list){
			super(list);
			attempts = 0;
			numCorrectInRow = 0;
		}
		
		/**
		 * Processes the answer for Elementary Questions.
		 * @param s user's answer
		 * @throws EmptyQuestionListException if question is null
		 */
		public String processAnswer(String s) throws EmptyQuestionListException{
			if(currentQuestionState.getCurrentQuestion() == null){
				throw new EmptyQuestionListException();
			}  
			
			// IF THE ANSWER IS CORRECT
			if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer().toLowerCase())){
				numCorrectAnswers++;
				numCorrectInRow++;
				
				if(attempts == 0) {
					numAttemptQuests++;
				}
						// IF USER HAS ANSWERED 2 ELE QUESTIONS CORRECT IN A ROW GO TO STANDARD 
				if(numCorrectInRow >= 2){  
					
					currentQuestionState = staQuestionState;
					attempts = 0;
					numCorrectInRow = 0;
					
					// if the current question of staState is not the first question, move it. 
					if(!(currentQuestionState.getCurrentQuestion() == stans.get(0))){
						currentQuestionState.nextQuestion();	
					} else if(ansFirstStanAlready){ //
						currentQuestionState.nextQuestion(); //
					}
					ansFirstStanAlready = true;
					
				} else { // IF USER **HASNT** ANSWER 2 ELE QUESTIONS CORRECT IN A ROW -->
					currentQuestionState = eleQuestionState;
					if(attempts == 0){
						numCorrectInRow++;
					}
					attempts = 0;
					currentQuestionState.nextQuestion();
				}
				return CORRECT;
				
			// IF THE ANSWER IS WRONG	
			} else {
				attempts++;
				numCorrectInRow = 0;
				String hint = "";
				if(attempts >= 2){ 
					currentQuestionState = eleQuestionState;
					currentQuestionState.nextQuestion();
					attempts = 0;
				} else {
					currentQuestionState = eleQuestionState;
					numAttemptQuests++;
					hint = eleHint.get(eleQuestion.indexOf(
							  currentQuestionState.getCurrentQuestion())).getHint();
				}
				
				return INCORRECT + SEPARATOR + hint; 
			}
		}

	}
}
