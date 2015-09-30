package edu.ncsu.csc216.movie101.question;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import edu.ncsu.csc216.movie101.util.EmptyQuestionListException;
import edu.ncsu.csc216.question_library.AdvancedQuestion;
import edu.ncsu.csc216.question_library.ElementaryQuestion;
import edu.ncsu.csc216.question_library.QuestionException;
import edu.ncsu.csc216.question_library.QuestionReader;
import edu.ncsu.csc216.question_library.StandardQuestion;

/** Tests the MovieQuestions class. */
public class MovieQuestionsTest {

	/** Tests the constructor of MovieQuestions. */
	@Test
	public void testConstructor(){
		QuestionReader reader = null;
		@SuppressWarnings("unused")
		MovieQuestions mq = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
		} catch(QuestionException e){
			e.getMessage();
		}
	}

	/** Tests the hasMoreQuestions() method. */
	@Test
	public void testHasMoreQuestions(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
		} catch(QuestionException e){
			e.getMessage();
		}
		
		assertEquals(true, mq.hasMoreQuestions());
	}
	
	/** Tests the getCurrentQuestionText() method. */
	@Test
	public void testGetCurrentQuestionText() throws EmptyQuestionListException{
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
			s = mq.getCurrentQuestionText();
		} catch(QuestionException e){
			e.getMessage();
		} //catch(EmptyQuestionListException e){}
		
		assertEquals("Elementary Question 1", s);
	}
	
	/** Tests the getCurrentQuestionText() method with a null question. */
	@Test(expected=EmptyQuestionListException.class)
	public void testGetCurrentQuestionTextNull() throws EmptyQuestionListException {
		QuestionReader reader = null;
		MovieQuestions mq = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
		} catch(QuestionException e){}
		mq.getCurrentState().setCurrentQuestionToNull();
		@SuppressWarnings("unused")
		String s = mq.getCurrentQuestionText();
	}
	
	/** Tests the getCurrentQuestionChoices() method. */
	@Test
	public void testGetCurrentQuestionChoices(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String[] s = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
			s = mq.getCurrentQuestionChoices();
		} catch(QuestionException e){
			e.getMessage();
		} catch(EmptyQuestionListException e){}
		String[] b = new String[4];
		b[0] = "E1 a";
		b[1] = "E1 b";
		b[2] = "E1 c";
		b[3] = "E1 d";
		assertTrue(Arrays.equals(s, b));
	}
	
	/** Tests getCurrentChoices() with a null question */
	@Test(expected=EmptyQuestionListException.class)
	public void testGetCurrentQuestionChoicesNull() throws EmptyQuestionListException{
		QuestionReader reader = null;
		MovieQuestions mq = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
		} catch(QuestionException e){}
		
		mq.getCurrentState().setCurrentQuestionToNull();
		@SuppressWarnings("unused")
		String[] s = mq.getCurrentQuestionChoices();
	}
	
	/** Tests processAnswer() by testing from Ele -> Standard. */
	@Test
	public void testProcessAnswer(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
			mq.processAnswer("d");
			s= mq.processAnswer("c");
			//System.out.println(s);
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals("Correct!", s);
	}
	
	/** Tests processAnswer() by testing from Standard -> Standard. */
	@Test
	public void testProcessAnswer1(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d");
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c");
				//System.out.println(mq.getCurrentQuestionText());
			s = mq.processAnswer("d");
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(s, "Correct!");
	}
	
	/** Tests processAnswer() by testing from StandardState -> AdvancedState. */
	@Test
	public void testProcessAnswer2(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // standard 1
			mq.processAnswer("c"); //Sta 2 and switch to adv
			s = mq.getCurrentState().getCurrentQuestionText();
				//System.out.println(s);
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(s, "Advanced Question 1");
	}
	
	/** Tests processAnswer() by testing from Adv -> Adv. */
	@Test 
	public void testProcessAnswer3(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // standard 1
			mq.processAnswer("c"); //Sta 2 and switch to adv
			mq.processAnswer("d");
			s = mq.getCurrentState().getCurrentQuestionAnswer();
				//System.out.println(s);
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals("c", s);
	}
	
	/** Tests processAnswer() by testing Ele -> Ele (repeating the question). */
	@Test
	public void testProcessAnswer4(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
			mq.processAnswer("a");
			s= mq.getCurrentQuestionText();
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals("Elementary Question 1", s);
	}
	
	/** Tests processAnswer() by testing from Standard -> Elementary*/
	@Test
	public void testProcessAnswer5(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1 Correct
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2 Correct
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("b"); // standard 1 Wrong
				//System.out.print(mq.getCurrentQuestionText());
			s = mq.getCurrentState().getCurrentQuestionAnswer();

		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(s, "a");
	}
	
	/** Tests processAnswer() by testing from AdvancedState -> StandardState. */
	@Test
	public void testProcessAnswer6(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1 C
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2 C -> Sta 1
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // standard 1 C
			mq.processAnswer("c"); //Sta 2 C and switch to adv
			mq.processAnswer("a"); // the wrong answer
			s = mq.getCurrentQuestionText();
				//System.out.println(s);
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals("Standard Question 3", s);
	}
	
	/** Tests processAnswer() by testing from AdvancedState -> StandardState. */
	@Test
	public void testProcessAnswer7(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
	
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1 C
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2 C
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // standard 1 C
			mq.processAnswer("c"); // Sta 2 Correct
			mq.processAnswer("d"); // Adv 1 Correct
			mq.processAnswer("a"); // Adv 2 wrong -> Standard 3
			mq.processAnswer("a"); // Sta 3 Correct -> Sta 4
				s = mq.getCurrentQuestionText();
				//System.out.println(s);
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){
		} //catch(IndexOutOfBoundsException e){}
		assertEquals("Standard Question 4", s);
	}
	
	/** Tests processAnswer() by testing from StandardState -> AdvancedState for a second time. */
	@Test
	public void testProcessAnswer8(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1 C
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2 C -> Sta 1
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // sta 1 --> sta 2
			mq.processAnswer("c"); //Sta 2 -> Adv 1
			mq.processAnswer("a"); // A 1 -> St 3
			mq.processAnswer("a"); //St3 -> St4
			mq.processAnswer("b"); //st4 -> adv 2
			s = mq.getCurrentQuestionText();
				//System.out.println(s);
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals("Advanced Question 2", s);
	}
	
	/** Tests processAnswer() by testing from StandardState -> AdvancedState for a second time. */
	@Test
	public void testProcessAnswer9(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		String s = "";
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
			//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1 C
			mq.processAnswer("c"); // ele 2 C -> Sta 1
			mq.processAnswer("d"); // sta 1 --> sta 2
			mq.processAnswer("c"); //Sta 2 -> Adv 1
			mq.processAnswer("d"); // A 1 -> A2
			mq.processAnswer("a"); // A2 -> st3 -----
			//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("a"); //St3 -> St4
			//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("b"); //st4 -> adv 3
			//System.out.println(mq.getCurrentQuestionText());
			
			s = mq.getCurrentQuestionText();
				//System.out.println(s);
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){	
		} catch(IndexOutOfBoundsException e){
		}
		assertEquals("Advanced Question 3", s);
	}
	
	
	/** Tests the getNumCorrect method. */
	@Test 
	public void testgetNumCorrect(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // standard 1
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); //Sta 2 and switch to adv
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d");
				//System.out.println(mq.getNumCorrectQuestions());	
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){}
		assertEquals(5, mq.getNumCorrectQuestions());
	}
	
	/** Tests the getNumAttempted method. */
	@Test 
	public void testgetNumAttempted(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // ele 1
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("c"); // ele 2
				//System.out.println(mq.getCurrentQuestionText());
			mq.processAnswer("d"); // standard 1
			mq.processAnswer("c"); //Sta 2 and switch to adv
			mq.processAnswer("d");
			
		} catch(QuestionException e){
		} catch(EmptyQuestionListException e){} catch(IndexOutOfBoundsException eee){}
		assertEquals(5, mq.getNumAttemptedQuestions());
	}
	
	/** Tests getEleQuestionState(). */
	@Test
	public void testGetEleQuestionState(){
		QuestionReader reader = null;
		MovieQuestions mq = null;
		QuestionState b = null;
		QuestionState a = null;
		try {
			//Create the QuestionReader
			reader = new QuestionReader("questions1.xml");
			//Get the lists of Questions
			List<StandardQuestion> stdQuestions = reader.getStandardQuestions();
			List<ElementaryQuestion> elemQuestions = reader.getElementaryQuestions();
			List<AdvancedQuestion> advQuestions = reader.getAdvancedQuestions();
			mq = new MovieQuestions(stdQuestions, elemQuestions, advQuestions);
			b = mq.getCurrentState();
			a = mq.getEleQuestionState();
		} catch(QuestionException e){
		}
		assertEquals(b,a);
	}

}
