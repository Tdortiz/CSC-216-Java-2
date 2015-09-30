package edu.ncsu.csc216.movie101.question;

import java.util.List;
import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.Question;

/**
 * An abstract class that represents the state in the Finite State Machine.
 * @author Thomas Ortiz, Corey Colberg
 */
public abstract class QuestionState {
	
		/** The front of the List. */
		private static final int FRONT = 0;;
		/** A list of questions. */
		public List<Question> questions;
		/** The current question. */ 
		private Question currentQuestion;
		/** Interator for list */
		private int i = 0;
		/** Checks if the user has answered the first standard question yet. */
		public boolean ansFirstStanAlready = false;
		/** Checks if the user has answered the first advanced question yet. */
		public boolean ansFirstAdvAlready = false;
		
		/**
		 * Constructor for QuestionState.
		 * @param list of Questions
		 */
		public QuestionState(List<Question> list){
			this.questions = list;
			currentQuestion = questions.get(FRONT);
		}
				
		/**
		 * Abstract method to process the user's answer; throws an EmptyQuestionListExceptions 
		 * if currentQuestion is null. This method corresponds to transitions in the FSM. Each
		 * concrete state (nested classes inside MovieQuestions) defines this method according
		 * to the transitions that go from that state.
		 * @param s answer of user
		 * @throws EmptyQuestionListException if question is null
		 * @return String representing if user was right or wrong
		 */
		public abstract String processAnswer(String s) throws EmptyQuestionListException;
		
		/**
		 * True if there are more items in the questions list.
		 * @return true/false value if there are more questions in the test.
		 */
		public boolean hasMoreQuestions(){
			//if(questions.indexOf(currentQuestion) < questions.size()-1){ 
			if(i < questions.size()){ //Get rid of =
				return true;
			} else {
				return false;
			}
		}
		
		/**
		 * Get the text for the current question.
		 * @return the current question text
		 * @throws EmptyQuestionListException if there is no current question
		 */
		public String getCurrentQuestionText() throws EmptyQuestionListException {
			if(currentQuestion == null){
				throw new EmptyQuestionListException();
			}
			return currentQuestion.getQuestion();
		}
		
		/**
		 * Get the possible answers for the current question
		 * @return the possible answers for the current question -- each answer
		 *         is a separate array element
		 * @throws EmptyQuestionListException if there is no current question
		 */
		public String[] getCurrentQuestionChoices() throws EmptyQuestionListException { 
			if(currentQuestion == null){
				throw new EmptyQuestionListException();
			}
			String[] s = new String[4];
			s[0] = currentQuestion.getChoiceA();
			s[1] = currentQuestion.getChoiceB();
			s[2] = currentQuestion.getChoiceC();
			s[3] = currentQuestion.getChoiceD();
			return s;
		}
		
		/**
		 * Returns the current question's answer.
		 * @return String representing the answer to the question
		 * @throws EmptyQuestionListException if question is null
		 */
		public String getCurrentQuestionAnswer() throws EmptyQuestionListException{
			if(currentQuestion == null){
				throw new EmptyQuestionListException();
			}
			System.out.println(currentQuestion.getAnswer().toLowerCase());
			String answer = currentQuestion.getAnswer().toLowerCase();
			return answer;
		}
		
		/**
		 * Returns the current question.
		 * @return the question of the test
		 * @throws EmptyQuestionListException if question is null
		 */
		public Question getCurrentQuestion() throws EmptyQuestionListException{
			if(currentQuestion == null){
				throw new EmptyQuestionListException();
			}
			return currentQuestion;
		}
		
		/**
		 * Sets currentQuestion to the next item in the questions list, or null if there
		 * are no more questions in the list.
		 */
		public void nextQuestion(){  
			if(!hasMoreQuestions()){
				this.currentQuestion = null;
			} else {
				i++;
				this.currentQuestion = questions.get(i);
				//this.currentQuestion = questions.get(questions.indexOf(currentQuestion)+1);
			}
			
		}
		
		/**
		 * Makes the currentQuestion null for testing purposes.
		 */
		public void setCurrentQuestionToNull(){
			this.currentQuestion = null;
		}
		
		/**
		 * Makes the currentQuestion into the selected param a for testing purposes
		 * @param a Question
		 */
		public void setCurrentQuestionToArg(Question a){
			this.currentQuestion = a;
		}
}
