package edu.ncsu.csc216.movie101.quiz;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.QuestionException;

/**
 * Tests the MovieQuiz class.
 * @author Thomas Ortiz, Corey Colberg
 */
public class MovieQuizTest {
	
	/** Tests the constructor of MovieQuiz. */
	@Test
	public void testConstructor(){
		MovieQuiz mq = null;
		String s = " ";
		try{
			mq = new MovieQuiz("questions1.xml");
			s = mq.getCurrentQuestionText();
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(s, "Elementary Question 1");
	}
	
	/** Tests hasMoreQuestions(). */
	@Test
	public void testHasMoreQuestions(){
		MovieQuiz mq = null;

		try{
			mq = new MovieQuiz("questions1.xml");
		} catch(QuestionException e){}
		assertEquals(true, mq.hasMoreQuestions());
	}
	
	/** Tests getCurrentQuestionText() with an EmptyQuestionList. */
	@Test(expected=EmptyQuestionListException.class)
	public void testGetCurrentQuestionText() throws EmptyQuestionListException{
		MovieQuiz mq = null;
		try{
			mq = new MovieQuiz("questions1.xml");
		} catch(QuestionException e){}
		mq.getQuestions().getCurrentState().setCurrentQuestionToNull();
		mq.getCurrentQuestionText();
	}
	
	/** Tests getCurrentState(). */
	@Test
	public void testGetCurrentState(){
		MovieQuiz mq = null;
		String s = " ";
		try{
			mq = new MovieQuiz("questions1.xml");
			s = mq.getCurrentQuestionText();
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(s, "Elementary Question 1");
	}

	/** Tests getCurrentQuestionChoices(). */
	@Test
	public void testGetCurrentQuestionChoices(){
		MovieQuiz mq = null;
		String[] s = null;
		try{
			mq = new MovieQuiz("questions1.xml");
			s = mq.getCurrentQuestionChoices();
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		String[] b = new String[4];
		b[0] = "E1 a";
		b[1] = "E1 b";
		b[2] = "E1 c";
		b[3] = "E1 d";
		assertTrue(Arrays.equals(s, b));
	}
	
	/** Tests processAnswer(). */
	@Test
	public void testProcessAnswer(){
		MovieQuiz mq = null;
		String s = " ";
		try{
			mq = new MovieQuiz("questions1.xml");
			s = mq.processAnswer("d");
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(s, "Correct!");
	}
	
	/** Tests proccessAnswer() with throwing an exception. 
	 * @throws EmptyQuestionListException */
	@Test(expected = EmptyQuestionListException.class)
	public void testProcessAnswerException() throws EmptyQuestionListException{
		MovieQuiz mq = null;
		Boolean s = false;
		try{
			mq = new MovieQuiz("questions1.xml");
			mq.processAnswer("d");
			mq.getQuestions().getCurrentState().setCurrentQuestionToNull();
			mq.processAnswer("c"); // should throw exception
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){
			s = true; 
			if(s == true){
				throw new EmptyQuestionListException("asdf");
			}
		}
	}
	
	/** Tests getNumCorrectQuestions(). */
	@Test
	public void testGetNumCorrectQuestions(){
		MovieQuiz mq = null;
		try{
			mq = new MovieQuiz("questions1.xml");
			mq.processAnswer("d");
			mq.processAnswer("c");
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(2, mq.getNumCorrectQuestions());
	}
	
	/** Tests getNumAttempted(). */
	@Test
	public void testGetNumAttempted(){
		MovieQuiz mq = null;
		try{
			mq = new MovieQuiz("questions1.xml");
			mq.processAnswer("d"); // ele 1
			mq.processAnswer("a"); // ele 2
			mq.processAnswer("c"); 
			mq.processAnswer("a"); // ele 3 
			mq.processAnswer("d"); // St 1
			mq.processAnswer("c"); // St 2
			mq.processAnswer("d"); // adv 1
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(6, mq.getNumAttemptedQuestions());
	}
	
	
	
}
