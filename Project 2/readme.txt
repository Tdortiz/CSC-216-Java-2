How to use. First 
1.   Save QuestionLibrary.jar to a directory named lib/ in your project. If you create the directory and add the file through the file system, refresh your project in Eclipse so the changes are reflected in your workspace.
3.   Right click the project and select Build Path > Configure Build Path. A dialog box opens.
4.   In the resulting dialog box, open the Libraries tab.
5.   Click Add JARs .
6.   Select QuestionLibrary.jar in the lib/ directory, then click OK. (From Project 2 part 2  rubric)
Make sure you have the questions (as a .xml file) in a place you can find the file.
Then run Movie101GUI.java and select the .xml file and you are good to go.
If you miss an Elementary question then you must click  next question to retry that question.


edu.ncsu.csc216.movie101.question  
* MovieQuestions.java- Handles the question aspect of the program by keeping track of the number of correct answers, number attempts questions, and uses a finite state machine to keep track of the lists of questions. 
			Constructor for MovieQuestions takes a List of Standard Questions, Advanced Questions, and Elementary Questions. hasMoreQuestions returns whether there are more questions. getCurrentQuestionText 
			returns the string of the text of the question. getCurrentQuestionChoices returns a String array of the question choices. processAnswer takes in String as a parameter and returns the String answer. 
			getNumCorrectQuestions, getNumAttemptedQuestions returns the int numbers correct and attempted questions. getCurrentState returns the current state of the Finite State Machine. The Advanced Question State, 
			Standard Question State, Elementary Question State, that handles the three different Questions of the program.
* QuestionState.java- QuestionState is an abstract class that represents the state in the Finite State Machine. The constructor for QuestionState takes a list of questions as the parameter. The abstract method processAnswer 
			will process the user's answers. hasMoreQuestions returns True if there are more items in the questions list. To get the text for the current question getCurrentQuestionText. getCurrentQuestionChoices
			returns an String array of the current answer choices. getCurrentQuestionAnswer returns the string of the correct answer. getCurrentQuestion returns the string with current question. nextQuestion moves 
			to the next question on the list. setCurrentQuestionToNull Makes the currentQuestion null for testing purposes.


edu.ncsu.csc216.movie101.quiz
* MovieQuiz.java- Handles the question aspect of the program by keeping track of the number of correct answers, number attempts questions, and uses a finite state machine to keep track of the lists of questions. 
			The constructor creates a QuestionReader and reads in the questions into a MovieQuestions object named questions. Then it has seven methods used in the GUI. The first one is hasMoreQuestions
			which simply returns the boolean value of whether or not there are more questions. The next method getCurrentQuestionText returns a string of the next quiz question. The next method 
			getCurrentQuestionChoices returns a array string of the 4 answer choices. processAnswer takes the users answer as the string parameter and processes the user's answer to the current question. 
			getNumCorrectQuestions and getNumAttemptedQuestions returns the int value of number of correct answers and number of attempted questions. The final method is getQuestions which returns the 
			current question from movie questions.
* QuizMaster.java- Author: Jo Perry


edu.ncsu.csc216.movie101.ui
* Movie101GUI.java- This is the graphic user interface that we created to show the questions, display the answers, and have the user select and answer and submit it. It will also tell the user how many questions 
			they got right out of total questions answered when they quit the program or it runs out of questions.


edu.ncsu.csc216.movie101.util
* EmptyQuestionListException.java-  Class that represents an exception whenever a Question List is empty. It extends Exception.