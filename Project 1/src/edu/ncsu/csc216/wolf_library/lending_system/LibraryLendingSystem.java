package edu.ncsu.csc216.wolf_library.lending_system;

import edu.ncsu.csc216.wolf_library.inventory.Book;
import edu.ncsu.csc216.wolf_library.inventory.BookDB;
import edu.ncsu.csc216.wolf_library.patron.AccountManager;
import edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem;
import edu.ncsu.csc216.wolf_library.util.Constants;

/** 
 * Describes the library system operations. 
 * @author Thomas Ortiz, Corey Colberg
 *
 */
public class LibraryLendingSystem implements LendingManager {
	
	/** The patron account part of  the system */
	private AccountManager accounts;
	/** The database of books in the system */
	private BookDB bookInventory; 
	
	/**
	 * Creates a new BookDB from a string s.
	 * Creates a new Patron/Admin account system. 
	 * @param s file of book database
	 */
	public LibraryLendingSystem(String s){
		this.bookInventory = new BookDB(s);
		this.accounts = new LibraryAccountSystem();	
	}
	
	/**
	 * Set the user for the current context to a given value.
	 * @param id user's id
	 * @param password user's password
	 * @throws IllegalStateException if a patron or the admin is already logged in
	 * @throws IllegalArgumentException if the patron account does not exist
	 */
	public void login(String id, String pass){
		if(accounts.isAdminLoggedIn() || accounts.isPatronLoggedIn()){
			throw new IllegalStateException(Constants.EXP_ACCESS_DENIED);
		}

		if((!id.equals("admin") && !pass.equals("admin")) && !accounts.listAccounts().contains(id)){ //if patron is not in the string returned ->
			throw new IllegalArgumentException(Constants.EXP_INCORRECT);
		}	
		
		accounts.login(id, pass);
	}
	
	/**
	 * Logs the current user out of the system.
	 */
	public void logout(){
		accounts.logout();
	}
	
	/**
	 * Add a new account to the patron database. The administrator must be logged in.
	 * @param id new patron's id
	 * @param password new patron's password
	 * @param num number/max limit associated with this patron
	 * @throws IllegalStateException if the database is full or the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be added to the patron database
	 */
	public void addNewPatron(String id, String pass, int maxBooks){
		if(!accounts.isAdminLoggedIn()){
			throw new IllegalStateException(Constants.EXP_ACCESS_DENIED);
		}
		// Other exceptions thrown by method addNewPatron in PatronDB
		accounts.addNewPatron(id, pass, maxBooks);
	}
	
	/**
	 * Cancel a patron account. 
	 * @param id patron's id
	 * @throws IllegalStateException if the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be removed due to some error
	 */
	public void cancelAccount(String id){
		if(!accounts.isAdminLoggedIn()){
			throw new IllegalStateException(Constants.EXP_ACCESS_DENIED);
		}
		accounts.cancelAccount(id);
		if(accounts.listAccounts().contains(id)){
			throw new IllegalArgumentException("Patron was not removed due to some error.");
		}
	}
	
	/**
	 * Traverse all items in the inventory.
	 * @return the string representing the items in the inventory
	 */
	public String showInventory(){
		return bookInventory.traverse();
	}
	
	/**
	 * Returns the current user's id.  If there is no user logged in, an
	 * empty string is returned.
	 * @return the current user's id.
	 */
	public String getCurrentUserId(){
		if(accounts.isPatronLoggedIn()){
			return accounts.getCurrentPatron().getId();
		}
		if(accounts.isAdminLoggedIn()){
			return "admin";
		}
		return "";
	}
	
	/**
	 * Reserve the selected item for the reserve queue. 
	 * @param position position of the selected item in the inventory
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	public void reserveItem(int i){
		if(!accounts.isPatronLoggedIn()){
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		
		if(i < 0 || i >= bookInventory.getList().size() ){ //might have to make it just >
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		
		Book r = bookInventory.findItemAt(i);
		accounts.getCurrentPatron().reserve(r);
	}
	
	/**
	 * Move the item in the given position up 1 in the reserve queue. 
	 * @param position current position of item to move up one
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	public void reserveMoveAheadOne(int i){		
		int size = accounts.getCurrentPatron().getReserveQueue().size();
		
		if(i < 0 || i > size){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		if(!accounts.isPatronLoggedIn()){
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		
		accounts.getCurrentPatron().moveAheadOneInReserve(i);
	}
	
	/**
	 * Remove the item in the given position from the reserve queue.
	 * @param position position of the item in the queue
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	public void removeSelectedFromReserves(int i){
		int size = accounts.getCurrentPatron().getReserveQueue().size();
		
		if(i < 0 || i > size){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}
		if(!accounts.isPatronLoggedIn()){
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		
		accounts.getCurrentPatron().unReserve(i);
	}
	
	/**
	 * Traverse all items in the reserve queue.
	 * @return string representation of items in the queue
	 * @throws IllegalStateException if no patron is logged in
	 */
	public String traverseReserveQueue(){
		if(!accounts.isPatronLoggedIn()){
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		return accounts.getCurrentPatron().traverseReserveQueue();	
	}
	
	/**
	 * Traverse all items in the list of items checked out.
	 * @return string representation of checked out items
	 * @throws IllegalStateException if no patron is logged in
	 */
	public String traverseCheckedOut(){
		if(!accounts.isPatronLoggedIn()){
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		return accounts.getCurrentPatron().traverseCheckedOut();
	}
	
	/**
	 * Return the selected item to the inventory.
	 * @param i position location in the list of items checked out of the item to return
	 * @throws IllegalStateException if no patron is logged in
	 * @throws IndexOutOfBoundsException if position is out of bounds
	 */
	public void returnItem(int i){
		if(!accounts.isPatronLoggedIn()){
			throw new IllegalStateException(Constants.EXP_LLS_PATRON_NOT_LOGGED_IN);
		}
		int size = accounts.getCurrentPatron().getCheckedOut().size();
		if(i < 0 || i >= size){
			throw new IndexOutOfBoundsException(Constants.EXP_INDEX_OUT_OF_BOUNDS);
		}

		accounts.getCurrentPatron().returnBook(i);
	}
	
	/**
	 * Returns Accounts
	 * @return Accounts
	 */
	public AccountManager getAccounts(){
		return this.accounts;
	}
	
	/**
	 * Returns Book Database
	 * @return BookDB
	 */
	public BookDB getBookDB(){
		return this.bookInventory;
	}
}