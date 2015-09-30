package edu.ncsu.csc216.wolf_library.patron;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.wolf_library.patron.PatronDB;

/**
 * Tests the PatronDB class.
 * @author Corey Colberg, Thomas Ortiz
 *
 */
public class PatronDBTest {

	/**
	 * Test listAccount method which prints user id to a string
	 */
	@Test
	public void testListAccounts() {

		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 1);
		pdb.addNewPatron("User2", "abc", 2);
		pdb.addNewPatron("User3", "abc", 3);
		pdb.addNewPatron("User4", "abc", 4);
		pdb.addNewPatron("User5", "abc", 5);

		assertEquals(pdb.listAccounts(), "User1\nUser2\nUser3\nUser4\nUser5\n");
	}
	
	/**
	 * Test to see if two accounts with the same id will throw
	 * IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testMultipleSameAccounts() {

		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 1);
		pdb.addNewPatron("User2", "abc", 2);
		pdb.addNewPatron("User2", "abc", 3);
		pdb.addNewPatron("User4", "abc", 4);
		pdb.addNewPatron("User5", "abc", 5);

	}
	
	/**
	 * Test to see if too many accounts will throw IllegalStateException
	 */
	@Test(expected=IllegalStateException.class)
	public void testMaxAccounts() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 1);
		pdb.addNewPatron("User2", "abc", 2);
		pdb.addNewPatron("User3", "abc", 3);
		pdb.addNewPatron("User4", "abc", 4);
		pdb.addNewPatron("User5", "abc", 5);
		pdb.addNewPatron("User6", "abc", 1);
		pdb.addNewPatron("User7", "abc", 2);
		pdb.addNewPatron("User8", "abc", 3);
		pdb.addNewPatron("User9", "abc", 4);
		pdb.addNewPatron("User10", "abc", 5);
		pdb.addNewPatron("User11", "abc", 1);
		pdb.addNewPatron("User12", "abc", 2);
		pdb.addNewPatron("User13", "abc", 3);
		pdb.addNewPatron("User14", "abc", 4);
		pdb.addNewPatron("User15", "abc", 5);
		pdb.addNewPatron("User16", "abc", 1);
		pdb.addNewPatron("User17", "abc", 2);
		pdb.addNewPatron("User18", "abc", 3);
		pdb.addNewPatron("User19", "abc", 4);
		pdb.addNewPatron("User20", "abc", 5);
		pdb.addNewPatron("User21", "abc", 5);
	}
	
	/**
	 * Test to see if an invalid user name will throw an IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBadUsernames1() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("Use  r1", "abc", 1);
	}
	
	/**
	 * Test to see if an invalid password will throw an IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBadPassword2() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "", 1);
	}
	
	/**
	 * Test to see if an invalid user name will throw an IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBadUsernames3() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("", "abc", 1);
	}
	
	/**
	 * Test to see if a user created with a limit of three books will
	 * throw an IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBadUsernames4() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 0);
	}
	
	/** 
	 * Test to see if the user name "admin" will throw an IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBadUsernames5() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("admin", "abc", 1);
	}
	
	/**
	 * Test to see if removeAccount method works
	 */
	@Test
	public void testRemoveAccount() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 1);
		pdb.addNewPatron("User2", "abc", 2);
		pdb.addNewPatron("User3", "abc", 3);
		pdb.addNewPatron("User4", "abc", 4);
		pdb.addNewPatron("User5", "abc", 5);
		
		pdb.cancelAccount("User3");

		assertEquals(pdb.listAccounts(), "User1\nUser2\nUser4\nUser5\n");
	}
	
	/**
	 * Checks to see if removeAccount throws an IllegalArgumentException 
	 * if the index is invalid
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testRemoveAccountWithNoAccount() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 1);
		pdb.addNewPatron("User2", "abc", 2);
		pdb.addNewPatron("User3", "abc", 3);
		pdb.addNewPatron("User4", "abc", 4);
		pdb.addNewPatron("User5", "abc", 5);
		
		pdb.cancelAccount("User6");
		
	}
	
	/**
	 * Test to see if verifyPatron method will return an existing account
	 */
	@Test
	public void testVerifyPatron() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 1);
		pdb.addNewPatron("User2", "abc", 2);
		pdb.addNewPatron("User3", "abc", 3);
		pdb.addNewPatron("User4", "abc", 4);
		pdb.addNewPatron("User5", "abc", 5);
				
		assertEquals(pdb.verifyPatron("User4", "abc").getId(), "User4");
	}
	
	/** 
	 * Test to see if verifyPatron throw IllegalArgumentException
	 * if the password or user name is incorrect 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testVerifyPatronAccountThatDoesNotExist() {
		PatronDB pdb = new PatronDB();
		
		pdb.addNewPatron("User1", "abc", 1);
		pdb.addNewPatron("User2", "abc", 2);
		pdb.addNewPatron("User3", "abc", 3);
		pdb.addNewPatron("User4", "abc", 4);
		pdb.addNewPatron("User5", "abc", 5);
		
		pdb.verifyPatron("User4", "123");
	}

}
