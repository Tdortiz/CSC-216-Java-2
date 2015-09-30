package edu.ncsu.csc216.wolf_library.inventory;
import java.util.*;

import edu.ncsu.csc216.wolf_library.util.Constants;

/** 
 * This class creates a library book 
 * @author Thomas Ortiz, Corey Colberg
 */
public class Book {
	/** Title and author of book. */
	private String info;
	/** Number of avaiable copies. */
	private int numAvailable; 

	/**
	 * Constructs a Book from a string of the format <number-in-stock><whitespace><book-info>. 
	 * be read or if the book-info is an empty string after trimming.
	 * If <number-in-stock> is negative, this constructor sets it to 0.
	 * @param info of current book
	 * @throws IllegalArgumentException if the number-in-stock cannot or there is a bad file
	 */
	public Book(String info){
		Scanner readLine = new Scanner(info);
		if(readLine.hasNextInt()){
			int num = readLine.nextInt(); //number of copies 
			if(num < 0){
				this.numAvailable = 0;
			}
			if(num > 0 ){
				this.numAvailable = num; 
			}
		} else {
			readLine.close();
			throw new IllegalArgumentException(Constants.EXP_BAD_FILE);
		}
		if(readLine.hasNextLine()){
			String bookInfo = readLine.nextLine().trim(); //Book title and author
			if(bookInfo != ""){
				this.info  = bookInfo; 
			} else {
				readLine.close();
				throw new IllegalArgumentException(Constants.EXP_PATRON_WHITESPACE);
			}
		}
		readLine.close();	
	}

	/**
	 * Returns the book information
	 * @return Title and Author
	 */
	public String getInfo(){
		return this.info;
	}
	
	/**
	 * Returns the number of copies available.
	 * @return number of copies available
	 */
	public int getNumAvailable(){
		return this.numAvailable;
	}
	
	/** 
	 * Formats the book info into a string, prepended by "* " if there 
	 * are no copies of the book currently in the library inventory.
	 */
	public String toString(){
		if(numAvailable <= 0){
			return "* " + getInfo(); 
		} else {
			return getInfo();
		}
	}
	
	/**
	 * returns if there are any copies of the book.
	 * @return true if there are copies or false if there is none.
	 */
	public boolean isAvailable(){
		if(this.numAvailable > 0){ 
			return true;
		} else {
			return false; 
		}
	}
	
	/**
	 * Puts a copy of the book back into the inventory.
	 */
	public void backToInventory(){
		this.numAvailable++; 
	}
	
	/** 
	 * Removes a copy of the book from the inventory stock.
	 * of the book currently in the inventory.
	 * @throws an IllegalStateException if there are no copies
	 */
	public void removeOneCopyFromInventory(){
		if(this.numAvailable > 0){
			this.numAvailable--; 
		} else {
			throw new IllegalStateException(Constants.EXP_BOOK_UNAVAILABLE);
		}
	}
	
	/** 
	 * Compares two books.
	 * @param b Book to compare 
	 * @return the value 0 if the argument string is equal to this string; a value less than 0 
	 * 		if this string is lexicographically less than the string argument; and a value 
	 * 		greater than 0 if this string is lexicographically greater than the string argument.
	 * @throws NullPointerException if object is null
	 */
	public int compareTo(Book b){
		if(b == null){
			throw new NullPointerException(Constants.EXP_CANNOT_COMPARE);
		} else {
				
			String aInfo = this.info.toLowerCase(); //Info of Book A
			Scanner processA = new Scanner(aInfo);
			String newTitleA = ""; //Title of Book A
			String word = processA.next(); //word of Book A
			if(word.equals("a") || word.equals("an") || word.equals("the")){
				//Don't add it
			} else {
				newTitleA += word + " ";
			}
				
			while(processA.hasNext()){
				word = processA.next();
				newTitleA += word + " ";
			}
			newTitleA = newTitleA.trim();
			//System.out.println("***: " + newTitleA);
			processA.close();
				
			//B title
			String bInfo = b.getInfo().toLowerCase(); //Info of Book B
			Scanner processB = new Scanner(bInfo);
			String newTitleB = ""; //Title of Book B
			word = processB.next();
			if(word.equals("a") || word.equals("an") || word.equals("the")){
				//Don't add it
			} else {
				newTitleB += word + " ";
			}
				
			while(processB.hasNext()){
				word = processB.next();
				newTitleB += word + " ";
			}
			newTitleB = newTitleB.trim();
			//System.out.println("***: " + newTitleB);
			processB.close();
				
			// the value 0 if the argument string is equal to this string; a value less than 0 if this string is
			// lexicographically less than the string argument; and a value greater than 0 if this string is 
			// lexicographically greater than the string argument.
			return newTitleA.compareToIgnoreCase(newTitleB);
		}
	}
}
