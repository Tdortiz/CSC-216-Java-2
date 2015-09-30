package edu.ncsu.csc216.wolf_library.patron;

import edu.ncsu.csc216.wolf_library.util.Constants;

/**
 * Describes the patron management part of the Wolf Library.
 * @author Thomas Ortiz, Corey Colberg
 */
public class LibraryAccountSystem implements AccountManager {
	/** A list of patrons. */
	private PatronDB patronList;
	@SuppressWarnings("unused")
	/** The Admin */
	private static Admin adminUser;
	/** The current Patron. */
	private Patron currentPatron;
	/** True if and only if the administrator is logged into the system */
	private boolean adminLoggedIn;
	/** True if and only if a patron is logged into the system */
	private boolean patronLoggedIn;
	
	/**
	 * Constructs the LibraryAccountSystem.
	 */
	public LibraryAccountSystem(){
		this.patronList = new PatronDB();
		adminUser = new Admin();
		patronLoggedIn = false;
		adminLoggedIn = false;
	}
	
	/**
	 * Logs a user into the system. 
	 * @param id user's id
	 * @param password user's password
	 * @throws IllegalStateException if a patron or the administrator is already logged in
	 * @throws IllegalArgumentException if the patron account does not exist
	 */
	public void login(String id, String pass){
		if(adminLoggedIn || patronLoggedIn) {
			throw new IllegalStateException(Constants.EXP_LAS_USER_ALREADY_LOGGED_IN);
		}
		
		if (id.equals("admin") && pass.equals("admin")){
			adminLoggedIn = true;
			patronLoggedIn = false;
		} else {
			try{
				currentPatron = patronList.verifyPatron(id, pass);
				patronLoggedIn = true;
				adminLoggedIn = false;
			} catch (IllegalArgumentException IAE) {
				IAE.getMessage();
			}
		}
	}
	
	/**
	 * Logs the current patron or administrator out of the system.
	 */
	public void logout(){
		currentPatron = null;
		adminLoggedIn = false;
		patronLoggedIn = false;
	}
	
	/**
	 * Returns the currently logged in user.
	 * @return the currently logged in user.
	 */
	public Patron getCurrentPatron(){
		return this.currentPatron;
	}
	
	/**
	 * Is an administrator logged into the system?
	 * @return true if yes, false if no
	 */
	public boolean isAdminLoggedIn(){
		return adminLoggedIn;
	}
	
	/**
	 * Is a patron logged into the system?
	 * @return true if yes, false if no
	 */
	public boolean isPatronLoggedIn(){
		return patronLoggedIn;
	}
	
	/**
	 * Add a new patron to the patron database. The administrator must be logged in.
	 * @param id new patron's id
	 * @param password new patron's password
	 * @param num number associated with this patron
	 * @throws IllegalStateException if the database is full or the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be added to the patron database
	 */
	public void addNewPatron(String id, String pass, int maxBooks){
		if(adminLoggedIn){
			patronList.addNewPatron(id, pass, maxBooks);
		} else {
			throw new IllegalStateException("Admin must be logged in.");
		}
	}
	
	/**
	 * Cancel a patron account. 
	 * @param id patron's id
	 * @throws IllegalStateException if the administrator is not logged in
	 * @throws IllegalArgumentException if patron cannot be removed due to some error
	 */
	public void cancelAccount(String id){
		if(adminLoggedIn){
			patronList.cancelAccount(id);
		} else {
			throw new IllegalStateException("Admin must be logged in.");
		}
	}
	
	/**
	 * List all patron accounts. 
	 * @return string of patron id's separated by newlines
	 */
	public String listAccounts(){
		return patronList.listAccounts();
	}
}
