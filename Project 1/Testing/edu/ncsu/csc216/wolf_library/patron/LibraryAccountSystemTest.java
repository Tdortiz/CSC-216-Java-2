package edu.ncsu.csc216.wolf_library.patron;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.ncsu.csc216.wolf_library.inventory.Book;
import edu.ncsu.csc216.wolf_library.inventory.BookDB;
import edu.ncsu.csc216.wolf_library.patron.LibraryAccountSystem;

/**
 * Tests the LibraryAccountSystem Class.
 * @author Corey Colberg, Thomas Ortiz
 *
 */
public class LibraryAccountSystemTest {

	/**
	 * Test to see if administrator can log in and if the isAdminLoggedIn works
	 */
	@Test
	public void testLogIn() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		las.login("admin", "admin");
		assertEquals(las.isAdminLoggedIn(), true);
	}

	/**
	 * Test add account, by adding 5 accounts then listing them using the 
	 * listAccounts method
	 */
	@Test
	public void testAddAcounts() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		las.login("admin", "admin");
		
		las.addNewPatron("User1", "abc", 1);
		las.addNewPatron("User2", "abc", 2);
		las.addNewPatron("User3", "abc", 3);
		las.addNewPatron("User4", "abc", 4);
		las.addNewPatron("User5", "abc", 5);
		assertEquals(las.listAccounts(), "User1\nUser2\nUser3\nUser4\nUser5\n");
	}
	
	/**
	 * Test to see if IllegalStateException is thrown if addNewPatron is
	 * called when an administrator is not logged in.
	 */
	@Test(expected=IllegalStateException.class)
	public void testNoAdminAndAddAccounts() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		las.addNewPatron("User1", "abc", 1);
		las.addNewPatron("User2", "abc", 2);
		las.addNewPatron("User3", "abc", 3);
		las.addNewPatron("User4", "abc", 4);
		las.addNewPatron("User5", "abc", 5);
	}
	
	/** 
	 * Test to see if an newly created patron can log in
	 */
	@Test
	public void testLogIntoPatron() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		las.login("admin", "admin");
		
		las.addNewPatron("User1", "abc", 1);
		las.addNewPatron("User2", "abc", 2);
		
		las.logout();
		
		las.login("User2", "abc");
		
		assertEquals(las.getCurrentPatron().getId(), "User2");
	}
	
	/**
	 * Test to see if isAdminLoggedIn and isPatronLoggedIn work
	 */
	@Test
	public void testPatronLogoutAndLoginBoolValues() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		las.login("admin", "admin");
		
		las.addNewPatron("User1", "abc", 1);
		las.addNewPatron("User2", "abc", 2);
		
		las.logout();
		
		las.login("User2", "abc");
		
		assertEquals(!las.isAdminLoggedIn() && las.isPatronLoggedIn(), true);
	}
	
	/**
	 * Test to see if IllegalArgumentException is thrown if a invalid 
	 * patron account is added
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddBadAccounts1() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		las.login("admin", "admin");
		las.addNewPatron("U se  r5", "abc", 5);	
	}
	
	/**
	 * Test again to see if IllegalArgumentException is thrown if a invalid 
	 * patron account is added
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testAddBadAccounts2() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		las.login("admin", "admin");
		las.addNewPatron("User5", "", 5);	
	}
	
	/**
	 * Test to see if the cancelAccount method works
	 */
	@Test
	public void testCancelAccount() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		las.login("admin", "admin");
		
		las.addNewPatron("User1", "abc", 1);
		las.addNewPatron("User2", "abc", 2);
		
		las.cancelAccount("User1");
		
		assertEquals(las.listAccounts(), "User2\n");
	}
	
	/**
	 * Test to see if a patron can be created, then logged on, then reserve a 
	 * book or two. Then checks checkedOut.
	 */
	@Test
	public void testAccountBookCheckOut() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		Book b1 = new Book("1 Book1");
		Book b2 = new Book("4 Book2");
		
		
		las.login("admin", "admin");
		
		las.addNewPatron("User1", "abc", 1);
		las.addNewPatron("User2", "abc", 2);
		
		
		las.logout();
		
		las.login("User1", "abc");
		
		las.getCurrentPatron().reserve(b2);
		las.getCurrentPatron().reserve(b1);
				
		
		assertEquals(las.getCurrentPatron().traverseCheckedOut(), "Book2\n");
	}
	
	/**
	 * Test to see if a patron can be created, then logged on, then reserve a 
	 * book or two. Then checks reserveQueue.
	 */
	@Test
	public void testAccountBookReserve() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		BookDB bdb = new BookDB("Book.txt");
		
		las.login("admin", "admin");
		
		las.addNewPatron("User1", "abc", 1);
		las.logout();
		las.login("User1", "abc");
	
		las.getCurrentPatron().reserve(bdb.findItemAt(0));
		las.getCurrentPatron().reserve(bdb.findItemAt(1));
		
		assertEquals(las.getCurrentPatron().traverseReserveQueue(), 
				"Love in the Time of Cholera by Gabriel Garcia Marquez\n");
	}
		
	/**
	 * Test to see if a patron can be created, then logged on, then reserve a 
	 * book and then successfully return it.
	 */
	@Test
	public void testAccountBookReturn() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		BookDB bdb = new BookDB("Book.txt");	
		
		las.login("admin", "admin");
		las.addNewPatron("User1", "abc", 4);
		las.addNewPatron("User2", "abc", 2);
		las.logout();
		las.login("User1", "abc");
		
		las.getCurrentPatron().reserve(bdb.findItemAt(0));
		las.getCurrentPatron().reserve(bdb.findItemAt(1));		
		las.getCurrentPatron().returnBook(0);
		
		assertEquals(bdb.findItemAt(0).getNumAvailable(), 3);
	}
	
	/**
	 * Test to see if all the books are returned from when a patrons account
	 * is canceled
	 */
	@Test
	public void testCancelAccountBookReturn() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		
		BookDB bdb = new BookDB("Book.txt");	
		
		las.login("admin", "admin");
		las.addNewPatron("User1", "abc", 1);
		las.logout();
		las.login("User1", "abc");
		
		las.getCurrentPatron().reserve(bdb.findItemAt(0));
		
		las.logout();
		
		las.login("admin", "admin");	
		las.cancelAccount("User1");
		
		assertEquals(bdb.findItemAt(0).getNumAvailable(), 3);
	}
	
	/**
	 * Test to see if the user can log in, logout, then relog in.
	 */
	@Test
	public void testLoginAndRelogIn() {
		LibraryAccountSystem las = new LibraryAccountSystem();
		BookDB bdb = new BookDB("Book.txt");	
		
		las.login("admin", "admin");
		las.addNewPatron("User1", "abc", 1);
		las.logout();
		
		las.login("User1", "abc");
		las.getCurrentPatron().reserve(bdb.findItemAt(0));
		las.getCurrentPatron().reserve(bdb.findItemAt(1));
		las.logout();
		
		las.login("admin", "admin");
		
		las.logout();
		
		las.login("User1", "abc");
		
		assertEquals(las.getCurrentPatron().traverseCheckedOut(), "Pride and Prejudice by Jane Austin\n");
	}
	
	
}
