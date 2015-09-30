package edu.ncsu.csc216.movie101.quiz;

import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;


/**
 * QuizMaster specifies behaviors required for getting questions
 * and their possible answers, processing the user's answers, and
 * keeping track of the number of questions attempted and number
 * answered correctly.
 * 
 * @author Jo Perry
 */
public interface QuizMaster {
	/**
	 * Are there any more questions remaining in this test?
	 * @return true if there are, false if there are not
	 */
	public boolean hasMoreQuestions();
	
	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException;

	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * * @throws EmptyQuestionListException if there is no current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException;
	
	/**
	 * Process the user's answer to the current question.
	 * @param answer  the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String processAnswer(String answer) throws EmptyQuestionListException;
	
	/**
	 * How many questions has the user answered correctly?
	 * @return the number of correct answers
	 */
	public int getNumCorrectQuestions();
	
	/**
	 * How many questions has the user attempted to answer.
	 * @return the number of attempts
	 */
	public int getNumAttemptedQuestions();

}
