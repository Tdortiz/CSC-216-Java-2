package edu.ncsu.csc216.wolf_library.patron;

/**
 * Represents a User of the Wolf Library.
 * @author Thomas Ortiz, Corey Colberg
 *
 */
public abstract class User {
	/** Username of user */
	private String id;
	/** Password of user stored as the password's hashCode() */
	private int password;
	
	/** 
	 * Base constructor for User
	 */
	public User(){}
	
	/**
	 * Constructor for User. Uses two strings to create the users id and password.
	 * Throws an IllegalArgumentException if the arguments are null or of length 0 after trimming
	 * whitespace from the ends or if the arguments contain any whitespace after the trim.
	 * @param id of user
	 * @param pass password of user
	 */
	public User(String id, String pass){
		id = id.trim();
		pass = pass.trim();
		if(!containsWhitespace(id) || !containsWhitespace(pass) || id == null || pass == null || id.length() == 0 || pass.length() == 0){
			throw new IllegalArgumentException();
		}
		this.id = id;
		int password = pass.hashCode(); //hashCode form of String password
		this.password = password;
	}	
	
	/**
	 * Returns true if the hashCode() of the parameter matches the password which is 
	 * also stored as hashCode()
	 * @param password to verify
	 * @return true if password matches or false if it does not
	 */
	public boolean verifyPassword(String password){
		if(password.hashCode() == this.password){
			return true;
		} else {
			return false; 
		}
	}
	
	/**
	 * Returns the user's id
	 * @return id of user
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * Checks if the String contains any whitespace
	 * @param s String to check
	 * @return true if it does contain whitespace or false if it does not
	 */
	private boolean containsWhitespace(String s){
		if(s.contains(" ")){
			return true;
		} else { 
			return false;
		}
	}
	
	/**
	 * Compares two User's by ID
	 * @param b User B
	 * @return 0 if false; 1 if true
	 */
	public int compareTo(User b){
		String idA = this.id.toLowerCase();  // String id of user A
		String idB = b.getId().toLowerCase(); // String id of user B 
		
		// the value 0 if the argument string is equal to this string; a value less than 0 if this string is
		// lexicographically less than the string argument; and a value greater than 0 if this string is 
		// lexicographically greater than the string argument.
		return idA.compareToIgnoreCase(idB);
	}

	/**
	 * Sets the id of the user
	 * @param id of user
	 */
	public void setId(String id){
		this.id = id;
	}
	
	/**
	 * Sets the password of the user
	 * @param pass of user
	 */
	public void setPassword(String pass){
		int password = pass.hashCode(); //hasCode form of String password
		this.password = password;
	}
}
