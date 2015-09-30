package edu.ncsu.csc216.movie101.quiz;

import edu.ncsu.csc216.movie101.question.MovieQuestions;
import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.QuestionReader;

/**
 * Specifies behaviors required for getting questions
 * and their possible answers, processing the user's answers, and
 * keeping track of the number of questions attempted and number
 * answered correctly.
 * @author Thomas Ortiz, Corey Colberg
 */
public class MovieQuiz implements QuizMaster {
	
	/** Reads in the questions from a file */
	@SuppressWarnings("unused")
	private QuestionReader reader;
	/** The questions */
	private MovieQuestions questions;
	
	/**
	 * Constructs the MovieQuiz object.
	 * @param s File name
	 * @throws QuestionException if there is an error with QuestionReader
	 */
	public MovieQuiz(String s) throws QuestionException {
		QuestionReader read = null;
		read = new QuestionReader(s);
		this.questions = new MovieQuestions(read.getStandardQuestions(), 
						read.getElementaryQuestions(), read.getAdvancedQuestions());
	}
	
	/**
	 * Tests if there are any more questions.
	 * @return true if there are, false if there are not
	 */
	public boolean hasMoreQuestions(){
		return questions.hasMoreQuestions();
	}
	
	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException{
		if(questions.getCurrentState().getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return questions.getCurrentQuestionText();
	}
	
	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException{
		if(questions.getCurrentState().getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return questions.getCurrentQuestionChoices();
	}
	
	/**
	 * Process the user's answer to the current question.
	 * @param s the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String processAnswer(String s) throws EmptyQuestionListException{
		if(questions.getCurrentState().getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return questions.processAnswer(s);
	}
	
	/**
	 * Returns the questions the user answered correctly.
	 * @return the number of correct answers
	 */
	public int getNumCorrectQuestions(){
		return questions.getNumCorrectQuestions() ;
	}
	
	/**
	 * Return the number of questions the user has attempted.
	 * @return the number of attempted questions.
	 */
	public int getNumAttemptedQuestions(){
		return questions.getNumAttemptedQuestions();
	}

	/**
	 * Returns the MovieQuestions object questions.
	 * @return MovieQuestions
	 */
	public MovieQuestions getQuestions(){
		return this.questions;
	}
}
