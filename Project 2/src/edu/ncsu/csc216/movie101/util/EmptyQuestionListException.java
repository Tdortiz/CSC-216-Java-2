package edu.ncsu.csc216.movie101.util;

/**
 * Class that represents an exception whenever a Question List
 * is empty.
 * @author Thomas Ortiz, Corey Colberg
 */
public class EmptyQuestionListException extends Exception {
	/** Empty Question List message to user. */
	private static final String MESSAGE = "Empty Question List";
	/** Serial Version. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for EmptyQuestionListException.
	 */
	public EmptyQuestionListException(){
		super.getMessage();
	}
	
	/**
	 * Overloaded constructor for EmptyQuestionListException.
	 * @param s String passed
	 */
	public EmptyQuestionListException(String s){
		super(MESSAGE);
	}
}
