package edu.ncsu.csc216.wolf_library.patron;

import edu.ncsu.csc216.wolf_library.inventory.Book;
import edu.ncsu.csc216.wolf_library.util.Constants;
import edu.ncsu.csc216.wolf_library.util.MultiPurposeList;

/**
 * A Patron of Wolf Library.
 * @author Corey Colberg, Thomas Ortiz
 */
public class Patron extends User{
	/** The maximum allowable number of books this patron is allowed to check out. */
	private int maxCheckedOut;
	/** The number of books this patron currently has checked out. */
	private int numCheckedOut;
	/** List of Books that this patron has checkedOut */
	private MultiPurposeList checkedOut;
	/** List of Books that this patron has on his/her reserve Queue */
	private MultiPurposeList reserveQueue;
	
	
	/** 
	 * Constructor to create a patron. 
	 * @param id of patron
	 * @param password of patron
	 * @param maxBooks maximum numbers of books checked out at one time
	 * @throws IllegalArugmentException if the arguments are null or of length 0 after
	 * trimming whitespace or if the arguments contain any whitespace after the trim or if the
	 * maximum number of books is less than 1 or if the patron's id is "admin".
	 */
	public Patron(String id, String password, int maxBooks){
		id = id.trim();
		password = password.trim();
		if( id.equals(null) || password.equals(null) || id.length() == 0 || password.length() == 0){
			throw new IllegalArgumentException(Constants.EXP_PATRON_NULL);
		}
		
		if(maxBooks < 1){
			throw new IllegalArgumentException(Constants.EXP_PATRON_MAX);
			
		}
		if(id.equals("admin")){
			throw new IllegalArgumentException(Constants.EXP_PATRON_ADMIN);
		}
		
		setId(id);
		setPassword(password);
		this.maxCheckedOut = maxBooks;
		this.numCheckedOut = 0;
		
		checkedOut = new MultiPurposeList();
		reserveQueue = new MultiPurposeList();
	}
	
	/**
	 * Returns a string of books in the reserve queue in the order that they were reserved. 
	 * Books are shown by info and successive books are separated by newlines,
	 * including a trailing newline.
	 * @return reserve queue of patron
	 */
	public String traverseReserveQueue(){
		return traverseQueue(reserveQueue);
	}
	
	/**
	 * Returns a string of checked out books in the order in which they were checked out. 
	 * Books are shown by info and successive books are separated by newlines,
	 * including a trailing newline
	 * @return checked out books of patron
	 */
	public String traverseCheckedOut(){
		return traverseQueue(checkedOut);
	}
	
	/**
	 * Closes the patrons account and returns all checked out books.
	 */
	public void closeAccount(){
		for(int i = 0; i < numCheckedOut; i++){
			returnBook(i);
		}
		
	}
	
	/**
	 * Removes the book of given index from checked out list and returns it to the 
	 * library inventory. 
	 * @param index
	 * @throws if the position is out of bounds. 
	 */
	public void returnBook(int index){
		if(index >= numCheckedOut || index < 0 ){ //||index >= checkedOut.size()
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		checkedOut.lookAtItem(index).backToInventory();
		checkedOut.remove(index);
		numCheckedOut--;
		
		if(numCheckedOut < maxCheckedOut && reserveQueue.size() > 0 && isAnyAvailable()){
			checkOut();
		}
	}
	
	/**
	 * Moves the book in the given position ahead one position in reserve queue. 
	 * If the position is 0, there is no exception but there is also no change in the list.
	 * @param index of book to move
	 * @throws if the position is out of bounds. 
	 */
	public void moveAheadOneInReserve(int index){
		if(index >= reserveQueue.size() || index < 0) {
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		} if(index != 0) {
			Book temp = reserveQueue.lookAtItem(index);
			reserveQueue.remove(index);
			reserveQueue.addItem(index - 1, temp);
		} if(index == 0){}
	}
	
	/**
	 * Removes the book at the given position from the reserve queue. 
	 * @param index
	 * @throws if position is out of bounds.
	 */
	public void unReserve(int index){
		if(index < 0 || index >= reserveQueue.size()){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);	
		}
		reserveQueue.remove(index);
	}
	
	/**
	 * Places the book at the end of the reserve queue.
	 * @param b Book to add to reserve queue
	 * @throws if the argument is null.
	 */
	public void reserve(Book b) {
		if(b == null){
			throw new IllegalArgumentException(Constants.EXP_LIST_ITEM_NULL);
		}
		reserveQueue.addToRear(b);
		
		if(numCheckedOut < maxCheckedOut && reserveQueue.size() > 0 && isAnyAvailable()) {
			checkOut();
		}
	}
	
	/**
	 * Makes a string of books
	 * @param l  the list needed to be traversed
	 * @return a string with the books
	 */
	private String traverseQueue(MultiPurposeList l){
		l.resetIterator();
		String s = "";
		int i = 0;
		
		while(i < l.size()){
			s += l.next().toString() + "\n";
			i++;
		}
		
		return s;
	}
	
	/**
	 * Checks out the first book in the reserveQueue list
	 */
	private void checkOut() { 
		Book b = removeFirstAvailable();
		checkedOut.addToRear(b);
		numCheckedOut++;
		b.removeOneCopyFromInventory();
	}
	
	/**
	 * Removes first available book from reserveQueue
	 * @return returns the book removed from Queue
	 */
	private Book removeFirstAvailable(){ 
		int i = 0;
		while(i < reserveQueue.size()){
			Book r = reserveQueue.lookAtItem(i);
			if(r.getNumAvailable() >= 1){
				reserveQueue.remove(i);
				return r;
			}
			i++;
		}
		return null; // Should never get here
	}
	
	/**
	 * Returns the reserveQueue List
	 * @return reserveQueue list 
	 */
	public MultiPurposeList getReserveQueue(){
		return this.reserveQueue;
	}
	
	/**
	 * Returns the CheckedOut List
	 * @return reserveQueue list 
	 */
	public MultiPurposeList getCheckedOut(){
		return this.checkedOut;
	}

	/**
	 * Checks if any of the books in reserveQueue are available.
	 * @return true/false if any books in reserveQueue are available.
	 */
	public boolean isAnyAvailable(){
		for(Book b: reserveQueue.getList()){
			if(b.isAvailable()){
				return true;
			}
		}
		return false;
	}
}
