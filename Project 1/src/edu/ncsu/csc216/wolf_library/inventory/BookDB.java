package edu.ncsu.csc216.wolf_library.inventory;
import java.io.*;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_library.util.Constants;
import edu.ncsu.csc216.wolf_library.util.MultiPurposeList;

/**
 * BookDB represents an internal database of books. 
 * @author Thomas Ortiz, Corey Colberg
 *
 */
public class BookDB {
	/** A list for maintaining the collection of books in the library inventory */
	private MultiPurposeList books;
	
	/** 
	 * Constructs a database from a file passed in.
	 * Throws an IllegalArgumentException if the file cannot be read.
	 * @param name file name
	 */
	public BookDB(String name){
		File f = new File(name);
		if(!f.canRead()){
			throw new IllegalArgumentException(Constants.EXP_BAD_FILE);
		}
		readFromFile(name);
	}
	
	/**
	 * Returns a string corresponding to the books in the database in the proper order. 
	 * Each book will be separated by newlines, including the last book of the list.
	 * @return String of books in database
	 */
	public String traverse(){
		int length = books.size();
		
		
		for(int i = 0; i < length - 1; i++){
			Book a = books.lookAtItem(i);
			Book b = books.lookAtItem(i+1);
			
			if(a.compareTo(b) > 0){
				/**
				books.remove(i+1);
				books.addItem(i+1, a);
				books.remove(i);
				books.addItem(i, b);
				*/
				insertInOrder(a);
				return traverse();
			}
		}
		return books.toString();
	}
	
	/**
	 * Returns the book at the given position.
	 * Throws IndexOutOfBoundsException if position is out of range (less than 0 or >= size).
	 * @param index of book
	 * @return Book at index
	 */
	public Book findItemAt(int index){
		if(index < 0 || index >= books.size()){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
			return books.lookAtItem(index);
	}
	
	/**
	 * Reads in the books from a file. 
	 * @param name of file
	 */
	private void readFromFile(String name){
		Scanner read = null;
		File f = new File(name);
		
		try{
			read = new Scanner(f);
		}catch (FileNotFoundException FNFE){
			FNFE.getMessage();
		}
		
		books = new MultiPurposeList();
		int index = 0;
		
		while(read.hasNextLine()){
			String info = read.nextLine();
			Book b = new Book(info);
			books.addItem(index, b);
			index++;
		}
		
		read.close();
	}
	
	/**
	 * Orders the books in alphabetical order.
	 * @param a
	 */
	private void insertInOrder(Book a){
		int i = books.indexOf(a);
		Book b = books.lookAtItem(i+1);
		books.remove(i+1);
		books.addItem(i+1, a);
		books.remove(i);
		books.addItem(i, b);
	}
	
	public MultiPurposeList getList(){
		return this.books;
	}
}
