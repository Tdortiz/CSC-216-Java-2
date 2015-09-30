package edu.ncsu.csc216.wolf_library.patron;

import edu.ncsu.csc216.wolf_library.util.*;

import java.util.*;

/**
 * Maintains a list of library patrons as a custom array-based list.
 * @author Thomas Ortiz, Corey Colberg
 *
 */
public class PatronDB {
	/** Maximum number of patrons the system can support */
	public static final int MAX_SIZE = 20;
	/** Number of patrons currently in the system */
	@SuppressWarnings("unused")
	private int size; 
	/** List of patrons currently in the system */
	private ArrayList<Patron> list; 
	
	/** 
	 * Constructs a PatronDB.
	 */
	public PatronDB(){
		this.list = new ArrayList<Patron>(0);
		
	}
	
	/**
	 * Returns the patron in the list whose id matches the first parameter
	 * and password matches the second.
	 * @param id of patron
	 * @param pass of patron
	 * @return matching patron
	 * @throws IllegalArgumentException if the id or password are null or if the password 
	 * is incorrect for the given id or if the patron is not in the database.
	 */
	public Patron verifyPatron(String id, String pass){
		int i = findMatchingAccount(id);
		if(i == -1){
			throw new IllegalArgumentException("Account information is incorrect.");
		}
		if(list.get(i).verifyPassword(pass)){
			return list.get(i);
		} else {
			throw new IllegalArgumentException("Account information is incorrect.");				
		}
	}
	
	/**
	 *  Used only for testing. Returns a string of ids of patrons in the list in list order. 
	 *  Successive ids are separated by newlines, including a trailing newline.
	 * @return String of ids of patrons
	 */
	public String listAccounts(){
		String listPatronString = "";
		for(int i = 0; i < list.size(); i++) {
			listPatronString += list.get(i).getId() + "\n";
		}
		return listPatronString;
	}
	
	/**
	 * Adds a new patron to the list
	 * @param id of new Patron
	 * @param pass of new Patron 
	 * @param maxBooks of new Patron
	 * @throws IllegalStateException if the database is full.
	 * @throws IllegalArgumentException if there is whitespace in the id or password, or if the id
	 * or password are empty, or if there is already a patron in the database with the same id.
	 */
	public void addNewPatron(String id, String pass, int maxBooks){
		if(!isNewPatron(id)) {
			throw new IllegalArgumentException(Constants.EXP_PATRON_DB_ACCOUNT_EXISTS);
		}
		if(list.size() >= MAX_SIZE){
			throw new IllegalStateException("There is no room for additional patrons.");
		} else {
			if(id.equals(null) || pass.equals(null) || id.length() == 0 || pass.length() == 0){
				throw new IllegalArgumentException(Constants.EXP_PATRON_NULL);
			} else if(id.lastIndexOf(' ') != -1 || pass.lastIndexOf(' ') != -1) {
				throw new IllegalArgumentException(Constants.EXP_PATRON_WHITESPACE);
			}
			Patron n = new Patron(id, pass, maxBooks);
			insert(n);
		}
	}
	
	/**
	 * Removes the patron with the given id from the list and returns
	 * any books that patron had checked out.
	 * @param id of account to cancel
	 * @throws IllegalArgumentException if the account does not exist.
	 */
	public void cancelAccount(String id){
		int i = findMatchingAccount(id);
		if(i == -1){
			throw new IllegalArgumentException();
		}
		list.get(i).closeAccount();
		list.remove(i);
	} 
	
	/** 
	 * Looks to see if the id has already been used by another Patron
	 * @param id
	 * @return whether or not the id has been used
	 */
	private boolean isNewPatron(String id){
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Inserts patron into the back of the ArrayList
	 * @param patron
	 */
	private void insert(Patron patron){
		list.add(patron);
	}
	
	/**
	 * Finds the index of the account with the same id as the parameter
	 * @param id  String passed in to check with the Patron's id
	 * @return returns index of the matching account, or -1 if no matching account
	 */
	private int findMatchingAccount(String id){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getId().equals(id)){
				return i;
			}
		}
		return -1;
	}
	
	
}
