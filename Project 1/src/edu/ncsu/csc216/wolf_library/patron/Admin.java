package edu.ncsu.csc216.wolf_library.patron;

/**
 * Creates an Administrator for the Wolf Library.
 * @author Thomas Ortiz, Corey Colberg
 */
public class Admin extends User {
	
	/**
	 * Constructs an Admin.
	 */
	public Admin(){
		//username and password is admin
		setId("admin");
		setPassword("admin");
	}
}
