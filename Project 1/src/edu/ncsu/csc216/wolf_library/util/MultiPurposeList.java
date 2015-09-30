package edu.ncsu.csc216.wolf_library.util;
import java.util.*;

import edu.ncsu.csc216.wolf_library.inventory.Book;

/**
 * A generic class that supports the underlying list operations for the library inventory,
 * reserve queues, and checked out queues. MultiPurposeList has a nested class named Node 
 * and is a custom implementation of a linked list.
 * 
 * DISCLAIMER***: We were told we could use ArrayLists instead of MultiPurposeLists for this class.
 * As such, we tried out best to keep MultiPurposeList as close to what it was. 
 * @author Thomas Ortiz, Corey Colberg
 */
public class MultiPurposeList {
	
	/** ArrayList of books */
	private ArrayList<Book> list;
	/** Current Index */
	public int index; 
	
	/** 
	 * Constructs an empty list.
	 */
	public MultiPurposeList(){
		this.list = new ArrayList<Book>(0); 
		this.index = 0;
	}
	
	/**
	 * Sets iterator to point to the first element in the list.
	 */
	public void resetIterator(){
		this.index = 0;
	}
	
	/**
	 * Returns true whenever iterator is pointing to a list element.
	 * @return true/false boolean if the list has another element
	 */
	public boolean hasNext(){
		if(index < list.size()){
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Returns the element iterator is pointing to and moves iterator to point to the next 
	 * element in the list. 
	 * Throws a NoSuchElementException if iterator is null or not pointing to a list element.
	 */
	public Book next(){
		Book b = list.get(index);
		
		if(b == null || index < 0 || index >= list.size()){
			throw new NoSuchElementException(Constants.EXP_LIST_ITEM_NULL);
		}
		
		index++;
		return b; 
	}
	
	/**
	 * Adds an element at the given position. 
	 * Throws a NullPointerException if the item is null.
	 * Throws an IndexOutOfBoundsException if the position
	 * is negative or greater than the list size. 
	 * @param index to add element to
	 * @param element to add
	 */
	public void addItem(int index, Book b){
		if(b == null){
			throw new NullPointerException(Constants.EXP_LIST_ITEM_NULL);
		}
		if(index < 0 || index > list.size()) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		list.add(index,b);
	}
	
	/**
	 * Returns true if the list contains no elements.
	 * @return boolean if list is empty or false. 
	 */
	public boolean isEmpty(){
		if(list.size() == 0){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 *  Returns the element at the given position. 
	 *  Throws an IndexOutOfBoundsException if the position is 
	 *  negative or greater than or equal to size.
	 * @param index of item to look at
	 */
	public Book lookAtItem(int index){
		if(index < 0 || index > list.size()){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		return list.get(index);
	}
	
	/**
	 * Adds an element to the rear of the list. 
	 * Throws a NullPointerException if the item is null.
	 * @param element to add
	 */
	public void addToRear(Book b){
		if(b == null){
			throw new NullPointerException(Constants.EXP_LIST_ITEM_NULL);
		}
		list.add(b);
	}
	
	/**
	 * Removes and returns the element in the given position. 
	 * Throws an IndexOutOfBoundsException if the position is 
	 * negative or greater than or equal to size.
	 * @param index
	 */
	public void remove(int index){
		if(index < 0 || index > list.size()){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		list.remove(index);
	}
	
	/**
	 * Moves the element at the given position ahead one position in the list. Does nothing
	 * if the element is already at the front of the list. 
	 * Throws an IndexOutOfBoundsException if the position
	 * is negative or greater than or equal to size.
	 * @param index of element to move
	 */
	public void moveAheadOne(int index){
		if(index < 0 || index > list.size()){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		
		if(index == 0){
			//Do nothing
		} else {
			Book a = list.get(index);
			Book b = list.get(index-1);
			list.set(index - 1, a);
			list.set(index, b);
			
		}
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return size of list
	 */
	public int size(){
		return list.size();
	}
	
	/**
	 * Returns the current index.
	 * @return current index
	 */
	public int getIndex(){
		return this.index;
	}
	
	/**
	 * Formats the List object into a String.
	 * @return string of list
	 */
	public String toString(){
		//return list.toString();
		
		String s = "";
		for(Book b: list){
			s += b + "\n";
		}
		return s;
	}

	/**
	 * Returns the index of the Book passed.
	 * @param a Book to examine
	 * @return index of book
	 */
	public int indexOf(Book a){
		for(int i = 0; i< list.size(); i++){
			if(a.equals(list.get(i))){
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the ArrayList list.
	 * @return ArrayList<Book>
	 */
	public ArrayList<Book> getList(){
		return this.list;
	}
}
